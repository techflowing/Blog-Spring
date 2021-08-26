package cn.techflowing.one.blog.wiki.service;

import cn.techflowing.one.blog.wiki.mapper.WikiDocumentMapper;
import cn.techflowing.one.blog.wiki.mapper.WikiProjectMapper;
import cn.techflowing.one.blog.wiki.model.CreateDocumentBody;
import cn.techflowing.one.blog.wiki.model.DeleteDocumentBody;
import cn.techflowing.one.blog.wiki.model.RenameDocumentBody;
import cn.techflowing.one.blog.wiki.model.WikiDocument;
import cn.techflowing.one.common.mybatis.DbErrorException;
import cn.techflowing.one.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    WikiProjectMapper projectMapper;

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

    /**
     * 创建新文档
     */
    @Transactional
    public boolean createDocument(CreateDocumentBody body) {
        int projectId = projectMapper.queryWikiProjectId(body.getProjectKey());
        if (projectId <= 0) {
            return false;
        }
        String hashKey = Md5Util.getMd5(body.getName() + System.currentTimeMillis() + new Random().nextInt());
        int result = documentMapper.createNewDocument(projectId,
                body.getName(),
                body.getDir() ? WikiDocument.TYPE_DIR : WikiDocument.TYPE_DOC,
                body.getParentId(),
                Integer.MAX_VALUE,
                hashKey);
        if (result <= 0) {
            return false;
        }
        // 更新文档总数
        int update = projectMapper.updateDocCount(projectId);
        if (update <= 0) {
            throw new DbErrorException();
        }
        return true;
    }

    /**
     * 重命名文档
     */
    public boolean renameDocument(RenameDocumentBody body) {
        return documentMapper.renameDocument(body.getName(), body.getDocumentId()) > 0;
    }

    /**
     * 删除文档
     *
     * @param body 数据
     * @return
     */
    @Transactional
    public boolean deleteDocument(DeleteDocumentBody body) {
        int id = projectMapper.queryWikiProjectId(body.getProjectKey());
        if (id <= 0) {
            return false;
        }
        int deleteCount = documentMapper.deleteDocument(body.getDocumentId());
        if (deleteCount <= 0) {
            return false;
        }
        int update = projectMapper.updateDocCount(id);
        if (update <= 0) {
            throw new DbErrorException();
        }
        return true;
    }
}
