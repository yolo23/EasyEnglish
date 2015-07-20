package com.github.andrdev.easyenglish.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.github.andrdev.easyenglish.adapters.DrawerAdapter;
import com.github.andrdev.easyenglish.model.DrawerLine;
import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taiyokaze on 7/17/15.
 */
public class VerbsActivity extends BaseSlidingActivity {


    SlidingPaneLayout mSlidingPanel;
    RecyclerView mMenuList;
    TextView oftenUsed;
    WebView webView;
    TextView fullTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWebView();
        initToolbarButtons();
        setOftenUsed();
    }

    private void initToolbarButtons() {
        oftenUsed = (TextView)getToolbar().findViewById(R.id.oftenUsed);
        oftenUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOftenUsed();
            }});

        fullTable = (TextView)getToolbar().findViewById(R.id.table);
        fullTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFulltable();
            }
        });
    }

    private void initWebView() {
        webView = (WebView)findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
    }

    @Override
    int getMainLayout() {
        return R.layout.activity_verb;
    }

    void setFulltable() {
        oftenUsed.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_but));
        oftenUsed.setTextColor(Color.WHITE);

        fullTable.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_sel_but));
        fullTable.setTextColor(0xFF428093);

        webView.loadUrl("file:///android_asset/verbs.html");
    }

    void setOftenUsed() {
        fullTable.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_but));
        fullTable.setTextColor(Color.WHITE);

        oftenUsed.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_sel_but));
        oftenUsed.setTextColor(0xFF428093);

        webView.loadUrl("file:///android_asset/verb.html");
    }

    @Override
    int getCurrentActivityPostion() {
        return 1;
    }
}
