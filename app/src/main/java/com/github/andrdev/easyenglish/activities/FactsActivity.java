package com.github.andrdev.easyenglish.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.RecyclerItemClickListener;
import com.github.andrdev.easyenglish.activities.AboutActivity;
import com.github.andrdev.easyenglish.activities.AudioActivity;
import com.github.andrdev.easyenglish.activities.MainActivity;
import com.github.andrdev.easyenglish.activities.MemoActivity;
import com.github.andrdev.easyenglish.activities.VerbsActivity;
import com.github.andrdev.easyenglish.activities.VideoActivity;
import com.github.andrdev.easyenglish.adapters.DrawerAdapter;
import com.github.andrdev.easyenglish.model.DrawerLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taiyokaze on 7/17/15.
 */
public class FactsActivity extends BaseSlidingActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWebView();
    }

    private void initWebView() {
        webView = (WebView)findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        webView.loadUrl("file:///android_asset/facts.html");
    }

    @Override
    int getMainLayout() {
        return R.layout.activity_facts;
    }

    @Override
    int getCurrentActivityPostion() {
        return 5;
    }
}
