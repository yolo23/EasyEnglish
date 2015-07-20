package com.github.andrdev.easyenglish.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.RecyclerItemClickListener;
import com.github.andrdev.easyenglish.adapters.DrawerAdapter;
import com.github.andrdev.easyenglish.model.DrawerLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taiyokaze on 7/20/15.
 */
abstract public class BaseSlidingActivity extends AppCompatActivity {


    SlidingPaneLayout mSlidingPanel;
    RecyclerView mMenuList;

    private Toolbar toolbar;

    static List<DrawerLine> drawerLines = new ArrayList<>();
    static{
        drawerLines.add(new DrawerLine(R.mipmap.gramm, "Грамматика"));
        drawerLines.add(new DrawerLine(R.mipmap.irregular, "Неправильные глаголы"));
        drawerLines.add(new DrawerLine(R.mipmap.audio, "Аудио-курс"));
        drawerLines.add(new DrawerLine(R.mipmap.video, "Видео-курс"));
        drawerLines.add(new DrawerLine(R.mipmap.memo, "Памятки"));
        drawerLines.add(new DrawerLine(R.mipmap.facts, "А вы знали?"));
        drawerLines.add(new DrawerLine(R.mipmap.about, "О приложении"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getMainLayout());
        initToolbar();
        initDrawer();
    }

    abstract int getMainLayout();

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.eeToolbar);
        toolbar.findViewById(R.id.burger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSlidingPanel.isOpen()) {
                    mSlidingPanel.closePane();
                } else {
                    mSlidingPanel.openPane();
                }
            }
        });
    }

    protected Toolbar getToolbar() {
        return toolbar;
    }

    private void initDrawer() {
        mSlidingPanel = (SlidingPaneLayout) findViewById(R.id.SlidingPanel);
        mMenuList = (RecyclerView) findViewById(R.id.menuList);
        mMenuList.setLayoutManager(new LinearLayoutManager(this));

        mMenuList.setAdapter(new DrawerAdapter(this, drawerLines));
        mMenuList.getAdapter().notifyDataSetChanged();
        mMenuList.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        onDrawerRowClick(position);
                    }
                }));
        mSlidingPanel.setParallaxDistance(200);
    }

    abstract int getCurrentActivityPostion();

    private void onDrawerRowClick(int position) {
        if(getCurrentActivityPostion() == position){
            mSlidingPanel.closePane();
            return;
        }

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
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        finish();
    }
}
