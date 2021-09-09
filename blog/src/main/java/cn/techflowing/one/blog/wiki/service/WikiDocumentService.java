package cn.techflowing.one.blog.wiki.service;

import cn.techflowing.one.blog.wiki.mapper.WikiDocumentMapper;
import cn.techflowing.one.blog.wiki.mapper.WikiProjectMapper;
import cn.techflowing.one.blog.wiki.model.*;
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
        // 根目录排序
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
     * 更新文档内容
     */
    public boolean updateDocumentContent(UpdateDocumentBody body) {
        return documentMapper.updateDocumentContent(body.getContent(), body.getHashKey()) > 0;
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

    /**
     * 拖动文档排序
     *
     * @param body 信息
     */
    @Transactional
    public boolean dragDocument(DragDocumentBody body) {
        int projectId = projectMapper.queryWikiProjectId(body.getProjectKey());
        if (projectId <= 0) {
            return false;
        }
        if (body.isDragOver()) { // 拖动到文件夹内，直接放到第一位
            List<Integer> childDocId = documentMapper.queryChildDocument(projectId, body.getTargetNode().getId());
            removeId(childDocId, body.getDragNode().getId());
            childDocId.add(0, body.getDragNode().getId());
            return documentMapper.updateDocSort(childDocId, body.getTargetNode().getId()) > 0;
        } else if (body.isDragOverGapTop()) { // 拖动到节点上部
            List<Integer> childDocId = documentMapper.queryChildDocument(projectId, body.getTargetNode().getParentId());
            removeId(childDocId, body.getDragNode().getId());
            childDocId.add(childDocId.indexOf(body.getTargetNode().getId()), body.getDragNode().getId());
            return documentMapper.updateDocSort(childDocId, body.getTargetNode().getParentId()) > 0;
        } else if (body.isDragOverGapBottom()) { // 拖动到节点下部
            List<Integer> childDocId = documentMapper.queryChildDocument(projectId, body.getTargetNode().getParentId());
            removeId(childDocId, body.getDragNode().getId());
            childDocId.add(childDocId.indexOf(body.getTargetNode().getId()) + 1, body.getDragNode().getId());
            return documentMapper.updateDocSort(childDocId, body.getTargetNode().getParentId()) > 0;
        } else { // 拖动到文件夹内，放到最后一位，有点不确定这个情景！！！
            List<Integer> childDocId = documentMapper.queryChildDocument(projectId, body.getTargetNode().getId());
            removeId(childDocId, body.getDragNode().getId());
            childDocId.add(body.getDragNode().getId());
            return documentMapper.updateDocSort(childDocId, body.getTargetNode().getId()) > 0;
        }
    }

    /**
     * 移除ID
     */
    private void removeId(List<Integer> list, int id) {
        list.removeIf(integer -> integer == id);
    }
}
