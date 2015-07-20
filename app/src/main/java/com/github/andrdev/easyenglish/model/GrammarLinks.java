package com.github.andrdev.easyenglish.model;

/**
 * Created by taiyokaze on 7/16/15.
 */
public class GrammarLinks {

    String title;
    String docPath;

    public GrammarLinks(String title, String docPath) {
        this.docPath = docPath;
        this.title = title;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
