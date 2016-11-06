package gobyfragment.we.com.gobyfragments;

import java.io.Serializable;

/**
 * Created by hekeji on 16/11/5.
 */
public class PageModel implements Serializable {
    private String nomal;
    private String select;

    private String content;

    private boolean isSelected = false;

    public String getNomal() {
        return nomal;
    }

    public void setNomal(String nomal) {
        this.nomal = nomal;
    }

    public String getSelect() {
        return select;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
