package cn.techflowing.one.blog.xmind.service;

import cn.techflowing.one.blog.xmind.mapper.XMindMapper;
import cn.techflowing.one.blog.xmind.model.*;
import cn.techflowing.one.blog.xmind.model.export.Node;
import cn.techflowing.one.blog.xmind.model.export.XMindStruct;
import cn.techflowing.one.common.service.FileService;
import cn.techflowing.one.util.FileUtil;
import cn.techflowing.one.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.xmind.core.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * XMind 思维导图
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/4 3:52 下午
 */
@Service
public class XMindService {

    @Autowired
    XMindMapper mindMapper;
    @Autowired
    FileService fileService;

    /** 数据存储目录 */
    private final File dataDir;

    public XMindService() {
        dataDir = FileUtil.getRuntimeTempDir("xmind");
        FileUtil.makeSureDirExist(dataDir);
    }

    /**
     * 查询思维导图树
     */
    public List<XMind> queryXMindTree() {
        List<XMind> rowList = mindMapper.queryAllXMind();
        if (rowList == null || rowList.isEmpty()) {
            return null;
        }
        List<XMind> treeList = new ArrayList<>();
        Map<Integer, List<XMind>> map = new HashMap<>();
        rowList.forEach(item -> {
            List<XMind> child = map.getOrDefault(item.getParentId(), new ArrayList<>());
            child.add(item);
            map.put(item.getParentId(), child);
        });
        rowList.forEach(item -> {
            item.setChildren(map.get(item.getId()));
            if (item.getParentId() == 0) {
                treeList.add(item);
            }
        });
        treeList.sort(Comparator.comparingInt(XMind::getSort));
        return treeList;
    }

    /**
     * 获取XMind内容
     *
     * @param key XMind Key
     */
    public Object getXMindMapContent(String key) {
        return mindMapper.queryContentByHashKey(key);
    }

    /**
     * 新建
     */
    public boolean createXMind(CreateXMindBody body) {
        String hashKey = Md5Util.getMd5(body.getName() + System.currentTimeMillis() + new Random().nextInt());
        return mindMapper.create(body.getName(),
                body.getDir() ? XMind.TYPE_DIR : XMind.TYPE_MAP,
                body.getParentId(),
                Integer.MAX_VALUE,
                hashKey) > 0;
    }

    /**
     * 删除
     */
    public boolean delete(DeleteXMindBody body) {
        return mindMapper.delete(body.getId()) > 0;
    }

    /**
     * 修改名称
     */
    public boolean rename(RenameXMindtBody body) {
        return mindMapper.rename(body.getName(), body.getId()) > 0;
    }

    /**
     * 更新内容
     */
    public boolean update(UpdateXMindBody body) {
        return mindMapper.updateContent(body.getContent(), body.getHashKey()) > 0;
    }

    /**
     * 获取文件 Resource
     *
     * @param name 文件名称
     * @return
     */
    public Resource getXMindFileResource(String name) {
        return fileService.loadFileAsResource(new File(dataDir, name).getAbsolutePath());
    }

    /**
     * 拖动排序
     */
    public boolean drag(DragXMindBody body) {
        if (body.isDragOver()) { // 拖动到文件夹内，直接放到第一位
            List<Integer> childDocId = mindMapper.queryChildDocument(body.getTargetNode().getId());
            removeId(childDocId, body.getDragNode().getId());
            childDocId.add(0, body.getDragNode().getId());
            return mindMapper.updateXMindSort(childDocId, body.getTargetNode().getId()) > 0;
        } else if (body.isDragOverGapTop()) { // 拖动到节点上部
            List<Integer> childDocId = mindMapper.queryChildDocument(body.getTargetNode().getParentId());
            removeId(childDocId, body.getDragNode().getId());
            childDocId.add(childDocId.indexOf(body.getTargetNode().getId()), body.getDragNode().getId());
            return mindMapper.updateXMindSort(childDocId, body.getTargetNode().getParentId()) > 0;
        } else if (body.isDragOverGapBottom()) { // 拖动到节点下部
            List<Integer> childDocId = mindMapper.queryChildDocument(body.getTargetNode().getParentId());
            removeId(childDocId, body.getDragNode().getId());
            childDocId.add(childDocId.indexOf(body.getTargetNode().getId()) + 1, body.getDragNode().getId());
            return mindMapper.updateXMindSort(childDocId, body.getTargetNode().getParentId()) > 0;
        } else { // 拖动到文件夹内，放到最后一位，有点不确定这个情景！！！
            List<Integer> childDocId = mindMapper.queryChildDocument(body.getTargetNode().getId());
            removeId(childDocId, body.getDragNode().getId());
            childDocId.add(body.getDragNode().getId());
            return mindMapper.updateXMindSort(childDocId, body.getTargetNode().getId()) > 0;
        }
    }

    /**
     * 生成 XMind 文件
     */
    public String exportXMind(XMindStruct body) {
        IWorkbookBuilder builder = Core.getWorkbookBuilder();
        IWorkbook workbook = builder.createWorkbook();
        ISheet sheet = workbook.getPrimarySheet();
        // 根主题
        ITopic root = sheet.getRootTopic();
        // 配置数据
        deepCloneData(workbook, root, body.getRoot());

        String name = String.format("%s-%s.xmind", root.getTitleText(), System.currentTimeMillis());
        String path = new File(dataDir, name).getAbsolutePath();
        try {
            workbook.save(path);
            return name;
        } catch (IOException | CoreException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置数据，JSON 数据转换为 XMind 里的数据
     */
    private void deepCloneData(IWorkbook workbook, ITopic topic, Node node) {
        if (topic == null || node == null) {
            return;
        }
        // 配置数据
        topic.setTitleText(node.getData().getText());
        if (node.getChildren() == null || node.getChildren().isEmpty()) {
            return;
        }
        node.getChildren().forEach(item -> {
            ITopic childTopic = workbook.createTopic();
            deepCloneData(workbook, childTopic, item);
            topic.add(childTopic, ITopic.ATTACHED);
        });
    }

    /**
     * 移除ID
     */
    private void removeId(List<Integer> list, int id) {
        list.removeIf(integer -> integer == id);
    }
}
