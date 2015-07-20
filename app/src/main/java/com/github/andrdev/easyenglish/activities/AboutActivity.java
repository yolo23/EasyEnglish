package com.github.andrdev.easyenglish.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.github.andrdev.easyenglish.adapters.DrawerAdapter;
import com.github.andrdev.easyenglish.model.DrawerLine;
import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taiyokaze on 7/17/15.
 */
public class AboutActivity  extends BaseSlidingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFeedbackButton();
        initShareButton();
    }

    private void initShareButton() {
        getToolbar().findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Учим английский";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Учим английский вместе");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Поделиться"));
            }
        });
    }

    private void initFeedbackButton() {
        findViewById(R.id.sendFeedBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("text/email");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"admin@hotmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "EasyEnglish отклик");
                email.putExtra(Intent.EXTRA_TEXT, "Привет, " + "");
                startActivity(Intent.createChooser(email, "Послать отклик:"));
            }
        });
    }

    @Override
    int getMainLayout() {
        return R.layout.activity_about;
    }

    @Override
    int getCurrentActivityPostion() {
        return 6;
    }
}
