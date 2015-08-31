package com.github.andrdev.easyenglish.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.RecyclerItemClickListener;
import com.github.andrdev.easyenglish.adapters.GrammarAdapter;
import com.github.andrdev.easyenglish.adapters.MemosAdapter;
import com.github.andrdev.easyenglish.model.GrammarLinks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taiyokaze on 8/31/15.
 */
public class TableActivity extends BaseSlidingActivity {

    static List<GrammarLinks> regularList = new ArrayList<>();

    static {
        regularList.add(new GrammarLinks("Activity (Use)", "file:///android_asset/active_use.html"));
        regularList.add(new GrammarLinks("Passive (Use)", "file:///android_asset/passive_use.html"));
        regularList.add(new GrammarLinks("Active (use) translate",
                "file:///android_asset/active_use_translate.html"));
        regularList.add(new GrammarLinks("Passive (use) translate",
                "file:///android_asset/passive_use_translate.html"));
    }

    static List<GrammarLinks> irregularList = new ArrayList<>();

    static {
        irregularList.add(new GrammarLinks("Active (Write)", "file:///android_asset/active_write.html"));
        irregularList.add(new GrammarLinks("Passive (Write)", "file:///android_asset/passive_write.html"));
        irregularList.add(new GrammarLinks("Active (Write) translate",
                "file:///android_asset/active_write_translate.html"));
        irregularList.add(new GrammarLinks("Passive (Write) translate",
                "file:///android_asset/passive_write_translate.html"));
    }


    TextView regular;
    TextView irregular;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recycler = (RecyclerView)findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        onRowClick(position);
                    }
                }));
        initToolbarItems();
        setRegular();
    }

    private void initToolbarItems() {
        regular = (TextView) getToolbar().findViewById(R.id.regular);
        irregular = (TextView) getToolbar().findViewById(R.id.irregular);

        regular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRegular();
            }
        });
        irregular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIrregular();
            }
        });
    }

    void setRegular() {
        irregular.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_but));
        irregular.setTextColor(Color.WHITE);

        regular.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_sel_but));
        regular.setTextColor(0xFF428093);

        recycler.setAdapter(new GrammarAdapter(this, regularList));
    }

    void setIrregular() {
        regular.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_but));
        regular.setTextColor(Color.WHITE);

        irregular.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_sel_but));
        irregular.setTextColor(0xFF428093);

        recycler.setAdapter(new GrammarAdapter(this, irregularList));
    }

    void onRowClick(int position) {
        GrammarLinks link = ((GrammarAdapter)recycler.getAdapter()).getData().get(position);
        Intent intent = new Intent(this, GrammarLinkActivity.class);
        intent.putExtra("url", link.getDocPath());
        Log.d("dree", "" + link.getDocPath());
        intent.putExtra("title", link.getTitle());
//        Log.d("dree", "" + grammarLinksList.size());
        startActivity(intent);
    }

    @Override
    int getMainLayout() {
        return R.layout.activity_table;
    }

    @Override
    int getCurrentActivityPostion() {
        return 1;
    }
}
