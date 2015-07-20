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

public class MainActivity extends AppCompatActivity {


    SlidingPaneLayout mSlidingPanel;
    RecyclerView mMenuList;
    ImageView appImage;
    TextView TitleText;
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
        setContentView(R.layout.activity_main);
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
        WebView webView = (WebView)findViewById(R.id.mainFragment);
        WebSettings settings = webView.getSettings();
        webView.loadUrl("file:///android_asset/basic_grammar.html");
        settings.setDefaultTextEncodingName("utf-8");
        toolbar.findViewById(R.id.goGrammar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GrammarActivity.class);
                startActivity(intent);
            }
        });
        mSlidingPanel.setParallaxDistance(200);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void onRowClick(int position) {
        Intent intent;
        switch (position){
            case 0:
                mSlidingPanel.closePane();

                return;
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
