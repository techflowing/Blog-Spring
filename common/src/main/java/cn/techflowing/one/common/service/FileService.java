package cn.techflowing.one.common.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件相关Service
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/9 12:34 上午
 */
@Service
public class FileService {

    /**
     * 加载文件
     *
     * @param filePath 文件完整路径
     * @return 文件
     */
    public Resource loadFileAsResource(String filePath) {
        try {
            Path path = Paths.get(filePath).toAbsolutePath().normalize();
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
