package com.github.andrdev.easyenglish.activities;

import android.content.Intent;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.andrdev.easyenglish.adapters.DrawerAdapter;
import com.github.andrdev.easyenglish.model.DrawerLine;
import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseSlidingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWebView();
        getToolbar().findViewById(R.id.goGrammar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GrammarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initWebView() {
        WebView webView = (WebView)findViewById(R.id.mainFragment);
        WebSettings settings = webView.getSettings();
        webView.loadUrl("file:///android_asset/basic_grammar.html");
        settings.setDefaultTextEncodingName("utf-8");
    }

    @Override
    int getMainLayout() {
        return R.layout.activity_main;
    }

    @Override
    int getCurrentActivityPostion() {
        return 0;
    }
}
