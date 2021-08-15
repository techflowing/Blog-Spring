package cn.techflowing.one.blog.wiki.service;

import cn.techflowing.one.blog.wiki.mapper.WikiDocumentMapper;
import cn.techflowing.one.blog.wiki.model.WikiDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Wiki 文档
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/11 1:03 上午
 */
@Service
public class WikiDocumentService {

    @Autowired
    WikiDocumentMapper documentMapper;

    /**
     * 构建文档树
     *
     * @param projectKey 文档HashKey
     * @return 树结构
     */
    public List<WikiDocument> queryDocumentTreeByProjectName(String projectKey) {
        List<WikiDocument> documentRowList = documentMapper.queryDocumentByProjectKey(projectKey);
        if (documentRowList == null || documentRowList.isEmpty()) {
            return null;
        }
        List<WikiDocument> treeList = new ArrayList<>();
        // 构建树结构，搜集每个节点的子节点
        Map<Integer, List<WikiDocument>> map = new HashMap<>();
        documentRowList.forEach(item -> {
            List<WikiDocument> children = map.getOrDefault(item.getParentId(), new ArrayList<>());
            children.add(item);
            map.put(item.getParentId(), children);
        });
        documentRowList.forEach(item -> {
            item.setChildren(map.get(item.getId()));
            if (item.getParentId() == 0) {
                treeList.add(item);
            }
        });
        // 跟目录排序
        treeList.sort(Comparator.comparingInt(WikiDocument::getSort));
        return treeList;
    }

    /**
     * 查询文章内容
     *
     * @param hashKey 唯一HashKey
     */
    public String queryContentByHashKey(String hashKey) {
        return documentMapper.queryContentByHashKey(hashKey);
    }
}
