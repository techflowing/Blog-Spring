package cn.techfllowing.one.util;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.system.ApplicationHome;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件相关操作类
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2020/11/2 12:00 下午
 */
public class FileUtil {

    private static final int BUFFER_SIZE = 1024;

    /**
     * 获取系统Temp目录
     */
    public static File getSystemTempDir() {
        return new File(System.getProperty("java.io.tmpdir"));
    }

    /**
     * 获取BdpUtil Temp目录
     */
    public static File getRuntimeTempDir() {
        return new File(getRuntimeDir(), "temp");
    }

    /**
     * 获取BdpUtil Temp目录
     */
    public static File getRuntimeTempDir(String business) {
        return new File(getRuntimeDir(), "temp/" + business);
    }

    /**
     * 获取当前运行环境的路径
     *
     * @return 文件夹路径
     */
    public static File getRuntimeDir() {
        ApplicationHome applicationHome = new ApplicationHome(FileUtil.class);
        File jarFile = applicationHome.getSource();
        if (jarFile == null) {
            return null;
        }
        File runtimeDir = jarFile.getParentFile();
        LogUtil.log("当前运行的文件夹位置：" + runtimeDir.getAbsolutePath());
        return runtimeDir;
    }

    /**
     * 确保文件夹存在，不存在则手动创建
     *
     * @param path 文件接路径
     */
    public static boolean makeSureDirExist(String path) {
        File dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            return true;
        }
        return dir.mkdirs();
    }

    /**
     * 确保文件夹存在，不存在则手动创建
     *
     * @param dir 文件夹
     */
    public static boolean makeSureDirExist(File dir) {
        if (dir.exists() && dir.isDirectory()) {
            return true;
        }
        return dir.mkdirs();
    }

    /**
     * 清除文件夹内容
     *
     * @param directory 文件夹
     */
    public static boolean cleanDirectory(File directory) {
        try {
            FileUtils.cleanDirectory(directory);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读取文件内容
     *
     * @param file 文件
     * @return 内容
     */
    public static List<String> readLines(File file) {
        try {
            return FileUtils.readLines(file, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Reads the contents of a file into a String using the default encoding for the VM.
     * The file is always closed.
     *
     * @param file the file to read, must not be {@code null}
     * @return the file contents, never {@code null}
     * @since 1.3.1
     */
    public static String readFileToString(File file) {
        try {
            return FileUtils.readFileToString(file, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Reads the contents of a file into a byte array.
     * The file is always closed.
     *
     * @param file the file to read, must not be {@code null}
     * @return the file contents, never {@code null}
     * @throws IOException in case of an I/O error
     */
    public static byte[] readFileToByteArray(final File file) throws IOException {
        try (InputStream in = openInputStream(file)) {
            final long fileLength = file.length();
            // file.length() may return 0 for system-dependent entities, treat 0 as unknown length - see IO-453
            return fileLength > 0 ? IOUtils.toByteArray(in, fileLength) : IOUtils.toByteArray(in);
        }
    }

    /**
     * 获取某一文件夹下所有文件
     *
     * @param file   文件
     * @param filter 文件过滤器
     * @return 子目录下文件集合
     */
    public static List<File> traverseAllFile(File file, FileFilter filter) {
        if (file == null || !file.exists()) {
            return null;
        }
        List<File> result = new ArrayList<>();
        if (file.isFile()) {
            result.add(file);
            return result;
        }
        if (file.isDirectory()) {
            File[] children = file.listFiles(filter);
            if (children != null && children.length > 0) {
                for (File child : children) {
                    result.addAll(traverseAllFile(child, filter));
                }
            }
        }
        return result;
    }

    /**
     * 删除文件，先rename，再删除
     *
     * @param file 要删除的文件
     */
    public static boolean deleteFile(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        File tempFile = new File(file.getParent(), file.getName() + ".tmp");
        return file.renameTo(tempFile) && tempFile.delete();
    }

    private static FileInputStream openInputStream(final File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        return new FileInputStream(file);
    }
}
