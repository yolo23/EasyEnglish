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

import com.github.andrdev.easyenglish.Idioms;
import com.github.andrdev.easyenglish.PhVerb;
import com.github.andrdev.easyenglish.Slang;
import com.github.andrdev.easyenglish.TalkPhrases;
import com.github.andrdev.easyenglish.adapters.DrawerAdapter;
import com.github.andrdev.easyenglish.adapters.GridVideoAdapter;
import com.github.andrdev.easyenglish.adapters.MemosAdapter;
import com.github.andrdev.easyenglish.model.DrawerLine;
import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.RecyclerItemClickListener;
import com.github.andrdev.easyenglish.model.MemoAbs;
import com.github.andrdev.easyenglish.model.MemoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taiyokaze on 7/17/15.
 */
public class MemoActivity extends BaseSlidingActivity implements Slang, PhVerb, TalkPhrases, Idioms{

    RecyclerView recyclerView;

    TextView verbsTv;
    TextView idiomsTv;
    TextView phrasesTv;
    TextView slangTv;
    static List<MemoAbs> itemSlangList = new ArrayList<>();
    static List<MemoAbs> itemPhVerbList = new ArrayList<>();
//    static List<MemoAbs> itemVerbsList = new ArrayList<>();
    static List<MemoAbs> itemTalkPhList = new ArrayList<>();
    static List<MemoAbs> itemIdiomList = new ArrayList<>();


    static {
        itemSlangList.add(new MemoAbs("Сленг", 2));
        for (int i = 0; i< slang.length; i++){
            itemSlangList.add(new MemoItem(slang[i], slang_translate[i], 1));
        }
    }
    static {
        itemTalkPhList.add(new MemoAbs(talking_header_1, 2));
        for (int i = 0; i< talking_verbs_1.length; i++){
            itemTalkPhList.add(new MemoItem(talking_verbs_1[i], talking_verbs_translate_1[i], 1));
        }

        itemTalkPhList.add(new MemoAbs(talking_header_2, 2));
        for (int i = 0; i< talking_verbs_2.length; i++){
            itemTalkPhList.add(new MemoItem(talking_verbs_2[i], talking_verbs_translate_2[i], 1));
        }

        itemTalkPhList.add(new MemoAbs(talking_header_3, 2));
        for (int i = 0; i< talking_verbs_3.length; i++){
            itemTalkPhList.add(new MemoItem(talking_verbs_3[i], talking_verbs_translate_3[i], 1));
        }


        itemTalkPhList.add(new MemoAbs(talking_header_4, 2));
        for (int i = 0; i< talking_verbs_4.length; i++){
            itemTalkPhList.add(new MemoItem(talking_verbs_4[i], talking_verbs_translate_4[i], 1));
        }

        itemTalkPhList.add(new MemoAbs(talking_header_5, 2));
        for (int i = 0; i< talking_verbs_5.length; i++){
            itemTalkPhList.add(new MemoItem(talking_verbs_5[i], talking_verbs_translate_5[i], 1));
        }
    }
    static {

        itemPhVerbList.add(new MemoAbs("A", 3));
        for (int i = 0; i < phrasal_verbs_a.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_a[i], phrasal_verbs_translate_a[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("B", 3));
        for (int i = 0; i < phrasal_verbs_b.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_b[i], phrasal_verbs_translate_b[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("C", 3));
        for (int i = 0; i < phrasal_verbs_c.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_c[i], phrasal_verbs_translate_c[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("D", 3));
        for (int i = 0; i < phrasal_verbs_d.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_d[i], phrasal_verbs_translate_d[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("E", 3));
        for (int i = 0; i < phrasal_verbs_e.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_e[i], phrasal_verbs_translate_e[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("F", 3));
        for (int i = 0; i < phrasal_verbs_f.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_f[i], phrasal_verbs_translate_f[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("G", 3));
        for (int i = 0; i < phrasal_verbs_g.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_g[i], phrasal_verbs_translate_g[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("H", 3));
        for (int i = 0; i < phrasal_verbs_h.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_h[i], phrasal_verbs_translate_h[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("I", 3));
        for (int i = 0; i < phrasal_verbs_i.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_i[i], phrasal_verbs_translate_i[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("J", 3));
        for (int i = 0; i < phrasal_verbs_j.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_j[i], phrasal_verbs_translate_j[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("K", 3));
        for (int i = 0; i < phrasal_verbs_k.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_k[i], phrasal_verbs_translate_k[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("L", 3));
        for (int i = 0; i < phrasal_verbs_l.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_l[i], phrasal_verbs_translate_l[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("M", 3));
        for (int i = 0; i < phrasal_verbs_m.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_m[i], phrasal_verbs_translate_m[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("N", 3));
        for (int i = 0; i < phrasal_verbs_n.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_n[i], phrasal_verbs_translate_n[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("O", 3));
        for (int i = 0; i < phrasal_verbs_o.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_o[i], phrasal_verbs_translate_o[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("P", 3));
        for (int i = 0; i < phrasal_verbs_p.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_p[i], phrasal_verbs_translate_p[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("Q", 3));
        for (int i = 0; i < phrasal_verbs_q.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_q[i], phrasal_verbs_translate_q[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("R", 3));
        for (int i = 0; i < phrasal_verbs_r.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_r[i], phrasal_verbs_translate_r[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("S", 3));
        for (int i = 0; i < phrasal_verbs_s.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_s[i], phrasal_verbs_translate_s[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("T", 3));
        for (int i = 0; i < phrasal_verbs_t.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_t[i], phrasal_verbs_translate_t[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("U", 3));
        for (int i = 0; i < phrasal_verbs_u.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_u[i], phrasal_verbs_translate_u[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("V", 3));
        for (int i = 0; i < phrasal_verbs_v.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_v[i], phrasal_verbs_translate_v[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("W", 3));
        for (int i = 0; i < phrasal_verbs_w.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_w[i], phrasal_verbs_translate_w[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("Y", 3));
        for (int i = 0; i < phrasal_verbs_y.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_y[i], phrasal_verbs_translate_y[i], 1));
        }

        itemPhVerbList.add(new MemoAbs("Z", 3));
        for (int i = 0; i < phrasal_verbs_z.length; i++) {
            itemPhVerbList.add(new MemoItem(phrasal_verbs_z[i], phrasal_verbs_translate_z[i], 1));
        }

    }
    static {
        itemIdiomList.add(new MemoAbs(type_1, 2));
        for (int i = 0; i< idioms_type_1.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_1[i], idioms_type_1_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_2, 2));
        for (int i = 0; i< idioms_type_2.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_2[i], idioms_type_2_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_3, 2));
        for (int i = 0; i< idioms_type_3.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_3[i], idioms_type_3_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_4, 2));
        for (int i = 0; i< idioms_type_4.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_4[i], idioms_type_4_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_5, 2));
        for (int i = 0; i< idioms_type_5.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_5[i], idioms_type_5_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_6, 2));
        for (int i = 0; i< idioms_type_6.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_6[i], idioms_type_6_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_7, 2));
        for (int i = 0; i< idioms_type_7.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_7[i], idioms_type_7_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_8, 2));
        for (int i = 0; i< idioms_type_8.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_8[i], idioms_type_8_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_9, 2));
        for (int i = 0; i< idioms_type_9.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_9[i], idioms_type_9_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_10, 2));
        for (int i = 0; i< idioms_type_10.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_10[i], idioms_type_10_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_10, 2));
        for (int i = 0; i< idioms_type_10.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_10[i], idioms_type_10_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_10, 2));
        for (int i = 0; i< idioms_type_10.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_10[i], idioms_type_10_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_11, 2));
        for (int i = 0; i< idioms_type_11.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_11[i], idioms_type_11_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_12, 2));
        for (int i = 0; i< idioms_type_12.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_12[i], idioms_type_12_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_13, 2));
        for (int i = 0; i< idioms_type_13.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_13[i], idioms_type_13_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_14, 2));
        for (int i = 0; i< idioms_type_14.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_14[i], idioms_type_14_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_15, 2));
        for (int i = 0; i< idioms_type_15.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_15[i], idioms_type_15_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_16, 2));
        for (int i = 0; i< idioms_type_16.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_16[i], idioms_type_16_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_17, 2));
        for (int i = 0; i< idioms_type_17.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_17[i], idioms_type_17_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_18, 2));
        for (int i = 0; i< idioms_type_18.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_18[i], idioms_type_18_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_19, 2));
        for (int i = 0; i< idioms_type_19.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_19[i], idioms_type_19_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_20, 2));
        for (int i = 0; i< idioms_type_20.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_20[i], idioms_type_20_translate[i], 1));
        }

        itemIdiomList.add(new MemoAbs(type_21, 2));
        for (int i = 0; i< idioms_type_21.length; i++){
            itemIdiomList.add(new MemoItem(idioms_type_21[i], idioms_type_21_translate[i], 1));
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRecycler();
        initToolbarButtons();
        setVerbs();
    }

    private void initRecycler() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        return 5;
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

        recyclerView.setAdapter(new MemosAdapter(this, itemPhVerbList));
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

        recyclerView.setAdapter(new MemosAdapter(this, itemIdiomList));
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

        recyclerView.setAdapter(new MemosAdapter(this, itemTalkPhList));
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

        recyclerView.setAdapter(new MemosAdapter(this, itemSlangList));
    }
}