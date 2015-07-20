package com.github.andrdev.easyenglish.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.andrdev.easyenglish.model.DrawerLine;
import com.github.andrdev.easyenglish.R;

import java.util.List;

/**
 * Created by taiyokaze on 7/16/15.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerHolder> {

    List<DrawerLine> data;
    LayoutInflater inflater;

    public DrawerAdapter(Context context,List<DrawerLine> data){
        this.data = data;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public DrawerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.row_drawer_item, viewGroup,false);
        DrawerHolder holder = new DrawerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DrawerHolder drawerHolder, int i) {
        DrawerLine current = data.get(i);
        drawerHolder.title.setText(current.getTitle());
        drawerHolder.icon.setImageResource(current.getIcon());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DrawerHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;
        public DrawerHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.listText);
            icon= (ImageView) itemView.findViewById(R.id.listIcon);
        }
    }
}
