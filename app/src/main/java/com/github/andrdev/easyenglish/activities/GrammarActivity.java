package com.github.andrdev.easyenglish.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.andrdev.easyenglish.GrammarFragment;
import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.TableFragment;

/**
 * Created by taiyokaze on 7/16/15.
 */
public class GrammarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView grammarTv;
    private TextView tableTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        toolbar = (Toolbar) findViewById(R.id.eeToolbar);

        getSupportFragmentManager().beginTransaction().add(R.id.frame, new GrammarFragment()).commit();
        grammarTv = (TextView)toolbar.findViewById(R.id.grammar);
        grammarTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGrammarLinksFragment();
        }});
        grammarTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_sel_but));
        grammarTv.setTextColor(0xFF428093);
        tableTv = (TextView)toolbar.findViewById(R.id.tables);
        tableTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTableLinksFragment();
            }
        });
        toolbar.findViewById(R.id.backIv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    void setTableLinksFragment(){
        grammarTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_but));

        grammarTv.setTextColor(Color.WHITE);
        tableTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_sel_but));
        tableTv.setTextColor(0xFF428093);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new TableFragment()).commit();

    }

    void setGrammarLinksFragment(){
        tableTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_but));
        tableTv.setTextColor(Color.WHITE);
        grammarTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_sel_but));
        grammarTv.setTextColor(0xFF428093);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new GrammarFragment()).commit();

    }
}
