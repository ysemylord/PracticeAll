package com.test.viewdemo.learnStructure.mvc;

/**
 * Created by xuyabo on 2018/7/3.
 */

public class SearchRes {
    private String title;
    private String content;

    public SearchRes(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
