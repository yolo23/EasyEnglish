package com.github.andrdev.easyenglish.model;

/**
 * Created by taiyokaze on 8/10/15.
 */
public class MemoItem extends MemoAbs {

    String tv2;

    public MemoItem(String textLeft, String textRight, int type) {
        super(textLeft, type);
        this.tv2 = textRight;
    }

    public String getTextRight() {
        return tv2;
    }

    public void setTextRight(String textRight) {
        this.tv2 = textRight;
    }
}
