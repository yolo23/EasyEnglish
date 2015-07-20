package com.github.andrdev.easyenglish.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.model.GrammarLinks;

import java.util.List;

/**
 * Created by taiyokaze on 7/16/15.
 */
public class GrammarAdapter extends RecyclerView.Adapter<GrammarAdapter.GrammarHolder> {

    List<GrammarLinks> data;
    LayoutInflater inflater;

    public GrammarAdapter(Context context,List<GrammarLinks> data){
        this.data = data;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public GrammarHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view=inflater.inflate(R.layout.row_grammar_item, viewGroup, false);

        GrammarHolder holder = new GrammarHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GrammarHolder viewHolder, int i) {
        viewHolder.title.setText(data.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class GrammarHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        OnGrammarClick clicker;

        public GrammarHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.text);
        }

        @Override
        public void onClick(View v) {
            clicker.grammarClick();
        }

        interface OnGrammarClick {
            void grammarClick();
        }
    }
}
