package com.github.andrdev.easyenglish.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
public class AboutActivity  extends AppCompatActivity {


    SlidingPaneLayout mSlidingPanel;
    RecyclerView mMenuList;
    TextView grammar;

    WebView webView;

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
        setContentView(R.layout.activity_about);
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

        mSlidingPanel.setParallaxDistance(200);
        findViewById(R.id.sendFeedBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("text/email");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"admin@hotmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "EasyEnglish отклик");
                email.putExtra(Intent.EXTRA_TEXT, "Привет, " + "");
                startActivity(Intent.createChooser(email, "Послать отклик:"));
            }
        });
        toolbar.findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Учим английский";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Учим английский вместе");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Поделиться"));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);
        // Locate MenuItem with ShareActionProvider
//        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
//        mShareActionProvider = (ShareActionProvider) item.getActionProvider();

        // Return true to display menu
        return true;
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
                intent = new Intent(this, MemoActivity.class);

                startActivity(intent);
                break;
            case 5:
                intent = new Intent(this, FactsActivity.class);

                startActivity(intent);
                break;
            case 6:
                mSlidingPanel.closePane();

                return;
            default:
                break;
        }
        finish();
    }
}
