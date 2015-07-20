package com.github.andrdev.easyenglish.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.github.andrdev.easyenglish.R;

/**
 * Created by taiyokaze on 7/17/15.
 */
public class GrammarLinkActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_open);
        toolbar = (Toolbar) findViewById(R.id.eeToolbar);
//        getFragmentManager().beginTransaction().add(R.id.mainFragment, fragment).commit();
        ((TextView)toolbar.findViewById(R.id.title)).setText(getIntent().getExtras().getString("title"));
        WebView webView = (WebView)findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        webView.loadUrl(getIntent().getExtras().getString("url"));
        settings.setDefaultTextEncodingName("utf-8");
        toolbar.findViewById(R.id.backIv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.findViewById(R.id.backText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
