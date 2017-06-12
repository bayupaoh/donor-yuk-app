package me.bayupaoh.donoryuk.data;

/**
 * Created by codelabsunikom on 6/12/17.
 */

public class ContentDao {
    private String value;
    private String content;

    public ContentDao(String value, String content) {
        this.value = value;
        this.content = content;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
