package cn.techflowing.one.ali.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;

/**
 * 类描述
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/20 11:15 下午
 */
public class AliOssClient {

    private final OSS oss;

    private AliOssClient() {
        oss = new OSSClientBuilder()
                .build(AliOssConfig.ENDPOINT, AliOssConfig.ACCESS_KEY, AliOssConfig.ACCESS_SECRET);
    }

    public static AliOssClient get() {
        return AliOssClientHolder.INSTANCE;
    }

    public static String getUrl(String key) {
        return AliOssConfig.DOMAIN + key;
    }

    public boolean uploadFile(String bucketName, String key, InputStream input) {
        try {
            oss.putObject(bucketName, key, input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void release() {
        if (oss != null) {
            oss.shutdown();
        }
    }

    private static class AliOssClientHolder {
        private static AliOssClient INSTANCE = new AliOssClient();
    }
}
