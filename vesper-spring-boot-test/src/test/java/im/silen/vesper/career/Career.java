package im.silen.vesper.career;

import java.util.List;

public class Career {
    private String code;
    private String text;
    private List<Career> children;

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

    public List<Career> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return children == null;
    }

    public void setChildren(List<Career> children) {
        this.children = children;
    }
}
