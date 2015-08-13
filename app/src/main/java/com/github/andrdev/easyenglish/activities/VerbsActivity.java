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

import com.github.andrdev.easyenglish.Verbs;
import com.github.andrdev.easyenglish.adapters.DrawerAdapter;
import com.github.andrdev.easyenglish.adapters.GrammarAdapter;
import com.github.andrdev.easyenglish.adapters.MemosAdapter;
import com.github.andrdev.easyenglish.model.DrawerLine;
import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.RecyclerItemClickListener;
import com.github.andrdev.easyenglish.model.MemoAbs;
import com.github.andrdev.easyenglish.model.MemoFour;
import com.github.andrdev.easyenglish.model.MemoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taiyokaze on 7/17/15.
 */
public class VerbsActivity extends BaseSlidingActivity implements Verbs {


    SlidingPaneLayout mSlidingPanel;
    RecyclerView recycler;
    TextView oftenUsed;
    TextView fullTable;
    static List<MemoAbs> itemVerbsList = new ArrayList<>();
    static List<MemoAbs> itemVerbsFullList = new ArrayList<>();

    static {
        itemVerbsList.add(new MemoAbs(first_header, 2));
        for (int i = 0; i< infinitive_type_one.length; i++){
            itemVerbsList.add(new MemoFour(infinitive_type_one[i], past_indefinite_type_one[i],
                    past_participle_type_one[i], translate_type_one[i], 4));
        }

        itemVerbsList.add(new MemoAbs(second_header, 2));
        for (int i = 0; i< infinitive_type_two.length; i++){
            itemVerbsList.add(new MemoFour(infinitive_type_two[i], past_indefinite_type_two[i],
                    past_participle_type_two[i], translate_type_two[i], 4));
        }

        itemVerbsList.add(new MemoAbs(third_header, 2));
        for (int i = 0; i< infinitive_type_three.length; i++){
            itemVerbsList.add(new MemoFour(infinitive_type_three[i], past_indefinite_type_three[i],
                    past_participle_type_three[i], translate_type_three[i], 4));
        }

        itemVerbsList.add(new MemoAbs(fourth_header, 2));
        for (int i = 0; i< infinitive_type_four.length; i++){
            itemVerbsList.add(new MemoFour(infinitive_type_four[i], past_indefinite_type_four[i],
                    past_participle_type_four[i], translate_type_four[i], 4));
        }

        itemVerbsList.add(new MemoAbs(fifth_header, 2));
        for (int i = 0; i< infinitive_type_five.length; i++){
            itemVerbsList.add(new MemoFour(infinitive_type_five[i], past_indefinite_type_five[i],
                    past_participle_type_five[i], translate_type_five[i], 4));
        }

        itemVerbsList.add(new MemoAbs(sixth_header, 2));
        for (int i = 0; i< infinitive_type_six.length; i++){
            itemVerbsList.add(new MemoFour(infinitive_type_six[i], past_indefinite_type_six[i],
                    past_participle_type_six[i], translate_type_six[i], 4));
        }

        itemVerbsList.add(new MemoAbs(seventh_header, 2));
        for (int i = 0; i< infinitive_type_seven.length; i++){
            itemVerbsList.add(new MemoFour(infinitive_type_seven[i], past_indefinite_type_seven[i],
                    past_participle_type_seven[i], translate_type_seven[i], 4));
        }

        itemVerbsList.add(new MemoAbs(eighth_header, 2));
        for (int i = 0; i< infinitive_type_eight.length; i++){
            itemVerbsList.add(new MemoFour(infinitive_type_eight[i], past_indefinite_type_eight[i],
                    past_participle_type_eight[i], translate_type_eight[i], 4));
        }

        itemVerbsList.add(new MemoAbs(ninth_header, 2));
        for (int i = 0; i< infinitive_type_nine.length; i++){
            itemVerbsList.add(new MemoFour(infinitive_type_nine[i], past_indefinite_type_nine[i],
                    past_participle_type_nine[i], translate_type_nine[i], 4));
        }
    }

    static {
        itemVerbsFullList.add(new MemoAbs(a_header, 3));
        for (int i = 0; i < full_infinitive_type_a.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_a[i], full_past_indefinite_type_a[i],
                    full_participle_type_a[i], translate_type_a[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(b_header, 3));
        for (int i = 0; i < full_infinitive_type_b.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_b[i], full_past_indefinite_type_b[i],
                    full_participle_type_b[i], translate_type_b[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(c_header, 3));
        for (int i = 0; i < full_infinitive_type_c.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_c[i], full_past_indefinite_type_c[i],
                    full_participle_type_c[i], translate_type_c[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(d_header, 3));
        for (int i = 0; i < full_infinitive_type_d.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_d[i], full_past_indefinite_type_d[i],
                    full_participle_type_d[i], translate_type_d[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(e_header, 3));
        for (int i = 0; i < full_infinitive_type_e.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_e[i], full_past_indefinite_type_e[i],
                    full_participle_type_e[i], translate_type_e[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(f_header, 3));
        for (int i = 0; i < full_infinitive_type_f.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_f[i], full_past_indefinite_type_f[i],
                    full_participle_type_f[i], translate_type_f[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(g_header, 3));
        for (int i = 0; i < full_infinitive_type_g.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_g[i], full_past_indefinite_type_g[i],
                    full_participle_type_g[i], translate_type_g[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(h_header, 3));
        for (int i = 0; i < full_infinitive_type_h.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_h[i], full_past_indefinite_type_h[i],
                    full_participle_type_h[i], translate_type_h[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(i_header, 3));
        for (int i = 0; i < full_infinitive_type_i.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_i[i], full_past_indefinite_type_i[i],
                    full_participle_type_i[i], translate_type_i[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(k_header, 3));
        for (int i = 0; i < full_infinitive_type_k.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_k[i], full_past_indefinite_type_k[i],
                    full_participle_type_k[i], translate_type_k[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(l_header, 3));
        for (int i = 0; i < full_infinitive_type_l.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_l[i], full_past_indefinite_type_l[i],
                    full_participle_type_l[i], translate_type_l[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(m_header, 3));
        for (int i = 0; i < full_infinitive_type_m.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_m[i], full_past_indefinite_type_m[i],
                    full_participle_type_m[i], translate_type_m[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(o_header, 3));
        for (int i = 0; i < full_infinitive_type_o.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_o[i], full_past_indefinite_type_o[i],
                    full_participle_type_o[i], translate_type_o[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(p_header, 3));
        for (int i = 0; i < full_infinitive_type_p.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_p[i], full_past_indefinite_type_p[i],
                    full_participle_type_p[i], translate_type_p[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(q_header, 3));
        for (int i = 0; i < full_infinitive_type_q.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_q[i], full_past_indefinite_type_q[i],
                    full_participle_type_q[i], translate_type_q[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(r_header, 3));
        for (int i = 0; i < full_infinitive_type_r.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_r[i], full_past_indefinite_type_r[i],
                    full_participle_type_r[i], translate_type_r[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(s_header, 3));
        for (int i = 0; i < full_infinitive_type_s.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_s[i], full_past_indefinite_type_s[i],
                    full_participle_type_s[i], translate_type_s[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(t_header, 3));
        for (int i = 0; i < full_infinitive_type_t.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_t[i], full_past_indefinite_type_t[i],
                    full_participle_type_t[i], translate_type_t[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(u_header, 3));
        for (int i = 0; i < full_infinitive_type_u.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_u[i], full_past_indefinite_type_u[i],
                    full_participle_type_u[i], translate_type_u[i], 4));
        }

        itemVerbsFullList.add(new MemoAbs(w_header, 3));
        for (int i = 0; i < full_infinitive_type_w.length; i++) {
            itemVerbsFullList.add(new MemoFour(full_infinitive_type_w[i], full_past_indefinite_type_w[i],
                    full_participle_type_w[i], translate_type_w[i], 4));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initrecycler();
        initToolbarButtons();
        setOftenUsed();
    }

    private void initToolbarButtons() {
        oftenUsed = (TextView)getToolbar().findViewById(R.id.oftenUsed);
        oftenUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOftenUsed();
            }});

        fullTable = (TextView)getToolbar().findViewById(R.id.table);
        fullTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFulltable();
            }
        });
    }

    private void initrecycler() {
        recycler = (RecyclerView)findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    int getMainLayout() {
        return R.layout.activity_verb;
    }

    void setFulltable() {
        oftenUsed.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_but));
        oftenUsed.setTextColor(Color.WHITE);

        fullTable.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_sel_but));
        fullTable.setTextColor(0xFF428093);

        recycler.setAdapter(new MemosAdapter(this, itemVerbsFullList));
    }

    void setOftenUsed() {
        fullTable.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_but));
        fullTable.setTextColor(Color.WHITE);

        oftenUsed.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_sel_but));
        oftenUsed.setTextColor(0xFF428093);

        recycler.setAdapter(new MemosAdapter(this, itemVerbsList));
    }

    @Override
    int getCurrentActivityPostion() {
        return 1;
    }
}
