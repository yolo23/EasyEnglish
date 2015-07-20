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
public class VerbsActivity extends AppCompatActivity {


    SlidingPaneLayout mSlidingPanel;
    RecyclerView mMenuList;
    TextView oftenUsed;
    WebView webView;
    TextView fullTable;

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
        setContentView(R.layout.activity_verb);
        mSlidingPanel = (SlidingPaneLayout) findViewById(R.id.SlidingPanel);
        mMenuList = (RecyclerView) findViewById(R.id.menuList);
        toolbar = (Toolbar) findViewById(R.id.eeToolbar);
        toolbar.setNavigationIcon(R.mipmap.menu_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSlidingPanel.isOpen()) {
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

        oftenUsed = (TextView)toolbar.findViewById(R.id.oftenUsed);
        oftenUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOftenUsed();
            }});

        fullTable = (TextView)toolbar.findViewById(R.id.table);
        fullTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFulltable();
            }
        });

        setOftenUsed();
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
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

    private void onRowClick(int position) {
        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(this, MainActivity.class);

                startActivity(intent);
                break;
            case 1:
                mSlidingPanel.closePane();

                return;
            case 2:
                intent = new Intent(this, AudioActivity.class);

                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, VideoActivity.class);

                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this, MemoActivity.class);

                startActivity(intent);
                break;
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
