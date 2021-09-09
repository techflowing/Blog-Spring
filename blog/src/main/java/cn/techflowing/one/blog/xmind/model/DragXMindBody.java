package cn.techflowing.one.blog.xmind.model;

import cn.techflowing.one.common.BaseModel;

/**
 * 拖动节点
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/31 1:56 上午
 */
public class DragXMindBody extends BaseModel {
    /** 节点内部 */
    private boolean dragOver;
    /** 节点上部 */
    private boolean dragOverGapTop;
    /** 节点下部 */
    private boolean dragOverGapBottom;
    /** 目标节点 */
    private Node targetNode;
    /** 被拖动的节点 */
    private Node dragNode;

    public boolean isDragOver() {
        return dragOver;
    }

    public void setDragOver(boolean dragOver) {
        this.dragOver = dragOver;
    }

    public boolean isDragOverGapTop() {
        return dragOverGapTop;
    }

    public void setDragOverGapTop(boolean dragOverGapTop) {
        this.dragOverGapTop = dragOverGapTop;
    }

    public boolean isDragOverGapBottom() {
        return dragOverGapBottom;
    }

    public void setDragOverGapBottom(boolean dragOverGapBottom) {
        this.dragOverGapBottom = dragOverGapBottom;
    }

    public Node getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(Node targetNode) {
        this.targetNode = targetNode;
    }

    public Node getDragNode() {
        return dragNode;
    }

    public void setDragNode(Node dragNode) {
        this.dragNode = dragNode;
    }

    public static class Node {

        private int id;
        private int parentId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }
    }
}
