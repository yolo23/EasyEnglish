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
public class MemoActivity extends AppCompatActivity {


    SlidingPaneLayout mSlidingPanel;
    RecyclerView mMenuList;
    WebView webView;

    TextView verbsTv;
    TextView idiomsTv;
    TextView phrasesTv;
    TextView slangTv;

    private Toolbar toolbar;
    static List<DrawerLine> dividerLines = new ArrayList<>();
    static{
        dividerLines.add(new DrawerLine(R.mipmap.gramm, "Грамматика"));
        dividerLines.add(new DrawerLine(R.mipmap.irregular, "Неправильные глаголы"));
        dividerLines.add(new DrawerLine(R.mipmap.audio, "Аудио-курс"));
        dividerLines.add(new DrawerLine(R.mipmap.video, "Видео-курс"));
        dividerLines.add(new DrawerLine(R.mipmap.memo, "Памятки"));
        dividerLines.add(new DrawerLine(R.mipmap.facts, "А вы знали?"));
        dividerLines.add(new DrawerLine(R.mipmap.about, "О приложении"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        mSlidingPanel = (SlidingPaneLayout) findViewById(R.id.SlidingPanel);
        mMenuList = (RecyclerView) findViewById(R.id.menuList);
        toolbar = (Toolbar) findViewById(R.id.eeToolbar);
        toolbar.setNavigationIcon(R.mipmap.menu_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSlidingPanel.isOpen()){
                    mSlidingPanel.closePane();
                } else {
                    mSlidingPanel.openPane();
                }
            }
        });
        mMenuList.setLayoutManager(new LinearLayoutManager(this));

        mMenuList.setAdapter(new DrawerAdapter(this, dividerLines));
        mMenuList.getAdapter().notifyDataSetChanged();
        mMenuList.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        onRowClick(position);
                    }
                }));
        webView = (WebView)findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");

        mSlidingPanel.setParallaxDistance(200);

        verbsTv = (TextView)toolbar.findViewById(R.id.verbsTv);
        verbsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVerbs();
            }
        });


        idiomsTv = (TextView)toolbar.findViewById(R.id.idiomsTv);
        idiomsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIdioms();
            }
        });

        phrasesTv = (TextView)toolbar.findViewById(R.id.phrasesTv);
        phrasesTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPhrases();
            }
        });

        slangTv = (TextView)toolbar.findViewById(R.id.slangTv);
        slangTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSlang();
            }
        });
        setVerbs();
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
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

    private void onRowClick(int position) {
        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(this, MainActivity.class);

                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, VerbsActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, AudioActivity.class);

                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, VideoActivity.class);

                startActivity(intent);
                break;
            case 4:
                mSlidingPanel.closePane();

                return;
            case 5:
                intent = new Intent(this, FactsActivity.class);

                startActivity(intent);
                break;
            case 6:
                intent = new Intent(this, AboutActivity.class);

                startActivity(intent);
                break;
            default:
                break;
        }
        finish();
    }
}