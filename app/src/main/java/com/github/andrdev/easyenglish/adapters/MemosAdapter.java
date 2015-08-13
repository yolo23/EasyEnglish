package com.github.andrdev.easyenglish.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.model.MemoAbs;
import com.github.andrdev.easyenglish.model.MemoFour;
import com.github.andrdev.easyenglish.model.MemoItem;

import java.util.List;

/**
 * Created by taiyokaze on 8/10/15.
 */
public class MemosAdapter extends RecyclerView.Adapter<MemosAdapter.MemosHolder> {

        List<? extends MemoAbs> data;
        LayoutInflater inflater;

    public MemosAdapter(Context context,List<? extends MemoAbs> data){
        this.data = data;
        inflater=LayoutInflater.from(context);
        }
    @Override
    public MemosHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view;
        switch (i) {
            case 1:
                view = inflater.inflate(R.layout.idiom_list_item, viewGroup, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.idiom_center_item, viewGroup, false);
                break;
            case 3:
                view = inflater.inflate(R.layout.idiom_left_item, viewGroup, false);
                break;
            case 4:
                view = inflater.inflate(R.layout.four_words_row, viewGroup, false);
                break;
            default:
                view = inflater.inflate(R.layout.idiom_list_item, viewGroup, false);
        }

        return new MemosHolder(view);
        }

    @Override
    public void onBindViewHolder(MemosHolder drawerHolder, int i) {
        MemoAbs current = data.get(i);
        drawerHolder.tv1.setText(current.getTv1());
        if(current.getType() == 1) {
            MemoItem item = (MemoItem) current;
            drawerHolder.tv2.setText(item.getTextRight());
        } else if(current.getType()==4){
            MemoFour item = (MemoFour) current;
            drawerHolder.tv2.setText(item.getTv2());
            drawerHolder.tv3.setText(item.getTv3());
            drawerHolder.tv4.setText(item.getTv4());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
        }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    class MemosHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;

        public MemosHolder(View itemView) {
            super(itemView);
            tv1= (TextView) itemView.findViewById(R.id.tv1);
            tv2= (TextView) itemView.findViewById(R.id.tv2);
            tv3= (TextView) itemView.findViewById(R.id.tv3);
            tv4= (TextView) itemView.findViewById(R.id.tv4);

        }
    }
}
