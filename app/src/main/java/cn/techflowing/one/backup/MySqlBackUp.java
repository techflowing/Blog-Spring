package cn.techflowing.one.backup;

import cn.techflowing.one.ali.oss.AliOssClient;
import cn.techflowing.one.util.DateUtil;
import cn.techflowing.one.util.FileUtil;
import cn.techflowing.one.util.LogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 数据库备份
 * 1. mysqldump 命令备份数据到本地
 * 2. ZIP 压缩
 * 3. 上传到阿里云 TOS 存储
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/6/30 4:47 下午
 */
@Component
public class MySqlBackUp {

    private static final String DB_URL_PREFIX = "jdbc:mysql://";
    private static final String DIR_NAME = "DB-Backup";
    private static final String TOS_BUCKET_NAME = "blog-backup-techflowing";
    private static final String CMD = "mysqldump -h%s -p%s -u%s -p%s %s | gzip > %s";

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPassWord;


    /**
     * 启动备份
     */
    public void runBackup() {
        log("Start");
        String dbName = getDbName();
        // TODO 获取数据库信息的逻辑需要优化，当前逻辑有点粗糙
        log("数据库名称：" + dbName);
        String dbServer = getDbServer();
        String dbServerPort = getDbServerPort();
        log("数据库Server：" + dbServer);
        log("数据库Server端口：" + dbServerPort);
        String dir = new File(FileUtil.getRuntimeDir(), DIR_NAME).getAbsolutePath();
        if (!FileUtil.makeSureDirExist(dir)) {
            log("备份成功，文件夹创建失败：" + dir);
            return;
        }
        String filePath = getDbBackupFilePath(dir);
        String finalCmd = String.format(CMD, dbServer, dbServerPort, dbUserName, dbPassWord, dbName, filePath);
        log("命令：" + finalCmd);
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", finalCmd});
            int result = process.waitFor();
            if (result == 0) {
                log("备份成功：" + filePath);
                uploadToAliTos(filePath);
            } else {
                log("备份失败：" + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log("备份失败：" + e.getMessage());
        }
    }

    /**
     * 上传到阿里云 TOS存储
     *
     * @param filePath 文件路径
     */
    private void uploadToAliTos(String filePath) {
        log("开始上传到TOS");
        File file = new File(filePath);

        try {
            boolean result = AliOssClient.get().uploadFile(TOS_BUCKET_NAME,
                    String.format("%s/%s", DIR_NAME, file.getName()), new FileInputStream(file));
            if (result) {
                log("TOS 上传成功");
            } else {
                log("TOS 上传失败");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log("TOS 上传失败：" + e.getMessage());
        }
    }

    private String getDbBackupFilePath(String dir) {
        String fileName = DateUtil.getCurrentDateWithTime() + ".back";
        return new File(dir, fileName).getAbsolutePath();
    }

    private String getDbName() {
        String url = getDbSchema();
        return url.substring(url.lastIndexOf("/") + 1);
    }

    private String getDbServer() {
        String url = getDbSchema();
        return url.substring(0, url.indexOf(":"));
    }

    private String getDbServerPort() {
        String url = getDbSchema();
        return url.substring(url.indexOf(":") + 1, url.lastIndexOf("/"));
    }

    private String getDbSchema() {
        String url = dbUrl.replace(DB_URL_PREFIX, "");
        return url.substring(0, url.indexOf("?"));
    }

    private void log(String msg) {
        LogUtil.log("【备份数据库】" + msg);
    }
}
