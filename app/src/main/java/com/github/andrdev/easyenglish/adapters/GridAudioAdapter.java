package com.github.andrdev.easyenglish.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.andrdev.easyenglish.CircleTransform;
import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.model.EnglishAudioItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by taiyokaze on 7/18/15.
 */
public class GridAudioAdapter extends RecyclerView.Adapter<GridAudioAdapter.GridHolder> {

    List<EnglishAudioItem> data;
    LayoutInflater inflater;

    public GridAudioAdapter(Context context, List<EnglishAudioItem> data){
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
        EnglishAudioItem current = data.get(i);
        drawerHolder.title.setText(current.getTitle());
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
        if(current.getImage()!=null&&!current.getImage().isEmpty()&&current.getImage().contains("http")) {
//            drawerHolder.icon.setBackgroundResource(R.drawable.circle_image);
            Picasso.with(inflater.getContext()).load(current.getImage())
                    .transform(new CircleTransform(4,0)).into(drawerHolder.icon);
        } else if (current.getImage()!=null&&!current.getImage().isEmpty()){
            Uri uri = Uri.fromFile(new File(current.getImage()));
            Picasso.with(inflater.getContext()).load(uri)
                    .transform(new CircleTransform(4,0)).into(drawerHolder.icon, callback);
        } else {
            drawerHolder.icon.setImageDrawable(null);
            drawerHolder.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
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
