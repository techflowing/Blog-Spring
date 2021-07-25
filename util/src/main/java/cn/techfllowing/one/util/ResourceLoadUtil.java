package cn.techfllowing.one.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Resource 文件加载工具类
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/4/1 6:27 下午
 */
public class ResourceLoadUtil {

    /** 临时文件夹，用于 copy 文件 */
    private static File temporaryDir;

    private ResourceLoadUtil() {
    }

    /**
     * 加载文件，从 resource 目录copy出来
     *
     * @param path 文件路径，要在 resources/ 路径下
     * @throws IOException
     */
    public static File loadFile(String path) throws IOException {
        if (temporaryDir == null) {
            temporaryDir = createTempDirectory();
            temporaryDir.deleteOnExit();
        }
        String[] parts = path.split("/");
        String filename = (parts.length > 1) ? parts[parts.length - 1] : null;
        if (filename == null) {
            throw new IllegalArgumentException("The filename empty");
        }
        File temp = new File(temporaryDir, filename);
        try (InputStream is = ResourceLoadUtil.class.getClassLoader().getResourceAsStream(path)) {
            Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            temp.delete();
            throw e;
        } catch (NullPointerException e) {
            temp.delete();
            throw new FileNotFoundException("File " + path + " was not found inside JAR.");
        }
        return temp;
    }

    /**
     * 创建临时目录
     */
    private static File createTempDirectory() throws IOException {
        File generatedDir = new File(FileUtil.getRuntimeTempDir(), "BdpUtilsResourceTemp-" + System.nanoTime());
        if (!generatedDir.mkdir()) {
            throw new IOException("Failed to create temp directory " + generatedDir.getName());
        }
        return generatedDir;
    }
}
