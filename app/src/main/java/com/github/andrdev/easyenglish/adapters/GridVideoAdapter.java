package com.github.andrdev.easyenglish.adapters;

/**
 * Created by taiyokaze on 7/18/15.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.andrdev.easyenglish.CircleTransform;
import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.model.EnglishVideoItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by taiyokaze on 7/18/15.
 */
public class GridVideoAdapter extends RecyclerView.Adapter<GridVideoAdapter.GridHolder> {

    List<EnglishVideoItem> data;
    LayoutInflater inflater;

    public GridVideoAdapter(Context context, List<EnglishVideoItem> data){
        this.data = data;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public GridHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.row_grid_item, viewGroup,false);
        GridHolder holder = new GridHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final GridHolder drawerHolder, int i) {
        Callback callback = new Callback() {
            @Override
            public void onSuccess() {
                drawerHolder.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {
                drawerHolder.progressBar.setVisibility(View.INVISIBLE);
            }
        };
        EnglishVideoItem current = data.get(i);
        drawerHolder.title.setText(current.getTitle());
        Picasso.with(inflater.getContext()).load(current.getPhoto())
                    .transform(new CircleTransform(2, 0)).into(drawerHolder.icon, callback);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public EnglishVideoItem getItem(int i) {
        return data.get(i);
    }

    class GridHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;
        ProgressBar progressBar;
        public GridHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.title);
            icon= (ImageView) itemView.findViewById(R.id.picture);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressGrid);
        }
    }

}

