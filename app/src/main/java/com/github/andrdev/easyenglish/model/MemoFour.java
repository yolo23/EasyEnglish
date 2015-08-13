package com.github.andrdev.easyenglish.model;

/**
 * Created by taiyokaze on 8/11/15.
 */
public class MemoFour extends MemoAbs {
    String tv2;
    String tv3;
    String tv4;
    public MemoFour(String tv1, String tv2, String tv3, String tv4, int type) {
        super(tv1, type);
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tv4 = tv4;
    }

    public String getTv2() {
        return tv2;
    }

    public void setTv2(String tv2) {
        this.tv2 = tv2;
    }

    public String getTv3() {
        return tv3;
    }

    public void setTv3(String tv3) {
        this.tv3 = tv3;
    }

    public String getTv4() {
        return tv4;
    }

    public void setTv4(String tv4) {
        this.tv4 = tv4;
    }
}
