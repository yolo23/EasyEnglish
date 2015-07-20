package com.github.andrdev.easyenglish;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.andrdev.easyenglish.activities.GrammarLinkActivity;
import com.github.andrdev.easyenglish.adapters.GrammarAdapter;
import com.github.andrdev.easyenglish.model.GrammarLinks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taiyokaze on 7/17/15.
 */
public class TableFragment extends Fragment {
    static List<GrammarLinks> grammarTableLinksList = new ArrayList<>();

    static{
        grammarTableLinksList.add(new GrammarLinks("Английские существительные в таблице", "file:///android_asset/nouns.html"));
        grammarTableLinksList.add(new GrammarLinks("Определители английских существительных в таблице", "file:///android_asset/nouns_cho.html"));
        grammarTableLinksList.add(new GrammarLinks("Английские местоимения в таблице", "file:///android_asset/pronouns.html"));
        grammarTableLinksList.add(new GrammarLinks("Английские прилагательные в таблице", "file:///android_asset/adjectives.html"));
        grammarTableLinksList.add(new GrammarLinks("Английские глаголы в таблице", "file:///android_asset/verbs.html"));
        grammarTableLinksList.add(new GrammarLinks("Английские наречия в таблице", "file:///android_asset/"));
        grammarTableLinksList.add(new GrammarLinks("Вопросы(действия)", "file:///android_asset/questions_actions.html"));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_table, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        onRowClick(position);
                    }
        }));
        recyclerView.setAdapter(new GrammarAdapter(getActivity(), grammarTableLinksList));
        return view;
    }
    void onRowClick(int position) {
        Intent intent = new Intent(getActivity(), GrammarLinkActivity.class);
        intent.putExtra("url", grammarTableLinksList.get(position).getDocPath());
        Log.d("dree", "" + grammarTableLinksList.get(position).getDocPath());
        intent.putExtra("title", grammarTableLinksList.get(position).getTitle());
        Log.d("dree", "" + grammarTableLinksList.size());
        getActivity().startActivity(intent);
    }
}
