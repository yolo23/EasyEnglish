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
public class MemoActivity extends BaseSlidingActivity {

    WebView webView;

    TextView verbsTv;
    TextView idiomsTv;
    TextView phrasesTv;
    TextView slangTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWebView();
        initToolbarButtons();
        setVerbs();
    }

    private void initWebView() {
        webView = (WebView)findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
    }

    private void initToolbarButtons() {
        verbsTv = (TextView)getToolbar().findViewById(R.id.verbsTv);
        verbsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVerbs();
            }
        });

        idiomsTv = (TextView)getToolbar().findViewById(R.id.idiomsTv);
        idiomsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIdioms();
            }
        });

        phrasesTv = (TextView)getToolbar().findViewById(R.id.phrasesTv);
        phrasesTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPhrases();
            }
        });

        slangTv = (TextView)getToolbar().findViewById(R.id.slangTv);
        slangTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSlang();
            }
        });
    }

    @Override
    int getMainLayout() {
        return R.layout.activity_memo;
    }

    @Override
    int getCurrentActivityPostion() {
        return 4;
    }

    void setVerbs() {
        idiomsTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_middle));
        idiomsTv.setTextColor(Color.WHITE);

        phrasesTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_middle));
        phrasesTv.setTextColor(Color.WHITE);

        slangTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_but));
        slangTv.setTextColor(Color.WHITE);

        verbsTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_sel_but));
        verbsTv.setTextColor(0xFF428093);

        webView.loadUrl("file:///android_asset/verbs.html");
    }

    void setIdioms() {
        verbsTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_but));
        verbsTv.setTextColor(Color.WHITE);

        phrasesTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_middle));
        phrasesTv.setTextColor(Color.WHITE);

        slangTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_but));
        slangTv.setTextColor(Color.WHITE);

        idiomsTv.setBackgroundColor(Color.WHITE);
        idiomsTv.setTextColor(0xFF428093);

        webView.loadUrl("file:///android_asset/verb.html");
    }

    void setPhrases() {
        verbsTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_but));
        verbsTv.setTextColor(Color.WHITE);

        idiomsTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_middle));
        idiomsTv.setTextColor(Color.WHITE);

        slangTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_but));
        slangTv.setTextColor(Color.WHITE);

        phrasesTv.setBackgroundColor(Color.WHITE);
        phrasesTv.setTextColor(0xFF428093);

        webView.loadUrl("file:///android_asset/phrases.rtf");
    }

    void setSlang() {
        verbsTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_but));
        verbsTv.setTextColor(Color.WHITE);

        idiomsTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_middle));
        idiomsTv.setTextColor(Color.WHITE);

        phrasesTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_middle));
        phrasesTv.setTextColor(Color.WHITE);

        slangTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_sel_but));
        slangTv.setTextColor(0xFF428093);

        webView.loadUrl("file:///android_asset/verb.html");
    }
}