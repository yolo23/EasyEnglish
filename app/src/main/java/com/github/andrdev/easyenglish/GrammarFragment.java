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
public class GrammarFragment extends Fragment {
    static List<GrammarLinks> grammarLinksList = new ArrayList<>();

    static {
        grammarLinksList.add(new GrammarLinks("Артикль", "file:///android_asset/articles.html"));
        grammarLinksList.add(new GrammarLinks("Местоимение", "file:///android_asset/pronoun.html"));
        grammarLinksList.add(new GrammarLinks("Предлоги в английском языке",
                "file:///android_asset/prepositions.html"));
        grammarLinksList.add(new GrammarLinks("Конструкция (оборот) <<There + to be>> (there is, there are)",
                "file:///android_asset/there_be.html"));
        grammarLinksList.add(new GrammarLinks("Образования множественного числа имен существительных",
                "file:///android_asset/nouns_mass.html"));
        grammarLinksList.add(new GrammarLinks("Исчесляемые и неисчесляемые существительные в английском языке",
                "file:///android_asset/count_uncount_nouns.html"));
        grammarLinksList.add(new GrammarLinks("Имя прилагательное", "file:///android_asset/adjective.html"));
        grammarLinksList.add(new GrammarLinks("Имя числительное", "file:///android_asset/numeral.html"));
        grammarLinksList.add(new GrammarLinks("Глагол", "file:///android_asset/verb.html"));
        grammarLinksList.add(new GrammarLinks("Инфинитив (The Infinitive)", "file:///android_asset/infinitive.html"));
        grammarLinksList.add(new GrammarLinks("Герундий (The Gerund)", "file:///android_asset/gerund.html"));
        grammarLinksList.add(new GrammarLinks("Причастие (The Participle)", "file:///android_asset/the_participle.html"));
        grammarLinksList.add(new GrammarLinks("Образование времен в английском языке",
                "file:///android_asset/times_creat.html"));
        grammarLinksList.add(new GrammarLinks("Согласование времен в английском языке",
                "file:///android_asset/sequence_of_tenses.html"));
        grammarLinksList.add(new GrammarLinks("Образование настоящего времени группы (Present Indefinite Tense)",
                "file:///android_asset/indefinite_tenses.html"));
        grammarLinksList.add(new GrammarLinks("Прошедшее время группы (The Past Indefinite Tense, Simple Past)",
                "file:///android_asset/past_tense.html"));
        grammarLinksList.add(new GrammarLinks("Будущее время группы (The Future Indefinite Tense, Simple Future)",
                "file:///android_asset/future_tense.html"));
        grammarLinksList.add(new GrammarLinks("Продолженное время (длительное время) - Continuous Tense",
                "file:///android_asset/continuous_tense.html"));
        grammarLinksList.add(new GrammarLinks("Совершенное время (The Perfect Tense)",
                "file:///android_asset/perfecttense.html"));
        grammarLinksList.add(new GrammarLinks("Совершенное продолженное время (The Perfect Continuous Tense)",
                "file:///android_asset/the_perfect_continuous_tenses.html"));
        grammarLinksList.add(new GrammarLinks("Типы вопросов в английском языке",
                "file:///android_asset/question_types.html"));
        grammarLinksList.add(new GrammarLinks("Страдательный (пасивный) залог (The passive voice)",
                "file:///android_asset/passive_voice.html"));
        grammarLinksList.add(new GrammarLinks("Условные предложения в английском языке",
                "file:///android_asset/conditional_sentences.html"));
        grammarLinksList.add(new GrammarLinks("Наклонение в английском языке)",
                "file:///android_asset/moods.html"));
        grammarLinksList.add(new GrammarLinks("Повелительное наклонение",
                "file:///android_asset/imperative_mood.html"));
        grammarLinksList.add(new GrammarLinks("Предположительное наклонение",
                "file:///android_asset/suppositional_mood.html"));
        grammarLinksList.add(new GrammarLinks("Условное наклонение",
                "file:///android_asset/conditional_mood.html"));
        grammarLinksList.add(new GrammarLinks("Сослагательное наклонение",
                "file:///android_asset/subjunctive_mood.html"));
        grammarLinksList.add(new GrammarLinks("Наречия",
                "file:///android_asset/participle.html"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        onRowClick(position);
                    }
        }));
        recyclerView.setAdapter(new GrammarAdapter(getActivity(), grammarLinksList));
        return view;
    }

    void onRowClick(int position) {
        Intent intent = new Intent(getActivity(), GrammarLinkActivity.class);
        intent.putExtra("url", grammarLinksList.get(position).getDocPath());
        Log.d("dree", "" + grammarLinksList.get(position).getDocPath());
        intent.putExtra("title", grammarLinksList.get(position).getTitle());
        Log.d("dree", "" + grammarLinksList.size());
        getActivity().startActivity(intent);
    }
}
