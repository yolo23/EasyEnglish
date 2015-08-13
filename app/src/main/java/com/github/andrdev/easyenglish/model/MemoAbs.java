package com.github.andrdev.easyenglish.model;

/**
 * Created by taiyokaze on 8/10/15.
 */
public class MemoAbs {
    int type;
    String tv1;
    public MemoAbs(String tv1, int type) {
        this.type = type;
        this.tv1 = tv1;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTv1() {
        return tv1;
    }

    public void setTv1(String tv1) {
        this.tv1 = tv1;
    }
}
