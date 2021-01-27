package im.silen.vesper.lib.tree;

import java.util.List;

public class TreeNode {
    private String code;
    private String text;
    private List<TreeNode> children;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return children == null || children.isEmpty();
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
