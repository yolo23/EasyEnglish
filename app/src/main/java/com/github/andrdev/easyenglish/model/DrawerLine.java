package com.github.andrdev.easyenglish.model;

/**
 * Created by taiyokaze on 7/16/15.
 */
public class DrawerLine {
    String title;
    int icon;

    public DrawerLine(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
