package com.github.andrdev.easyenglish;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


public class RecyclerViewGrid extends RecyclerView {

    public RecyclerViewGrid(Context context) {
        this(context, null);
    }

    public RecyclerViewGrid(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerViewGrid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);

//        layoutManager.setTotalColumnCount(3);
        layoutManager.offsetChildrenVertical(2);
        layoutManager.offsetChildrenHorizontal(2);

        setLayoutManager(layoutManager);
    }


}

