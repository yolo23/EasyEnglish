package com.github.andrdev.easyenglish.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.andrdev.easyenglish.adapters.DrawerAdapter;
import com.github.andrdev.easyenglish.model.DrawerLine;
import com.github.andrdev.easyenglish.adapters.GridVideoAdapter;
import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.RecyclerItemClickListener;
import com.github.andrdev.easyenglish.model.EnglishVideoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taiyokaze on 7/18/15.
 */
public class VideoActivity extends AppCompatActivity  {


    SlidingPaneLayout mSlidingPanel;
    RecyclerView mMenuList;

    ImageView appImage;


    TextView grammar;
    TextView songs;
    TextView tails;

    RecyclerView recyclerView;

    List<EnglishVideoItem> videoItemList;

    private Toolbar toolbar;
    static List<DrawerLine> dividerLines = new ArrayList<>();
    static{
        dividerLines.add(new DrawerLine(R.mipmap.gramm, "Грамматика"));
        dividerLines.add(new DrawerLine(R.mipmap.irregular, "Неправильные глаголы"));
        dividerLines.add(new DrawerLine(R.mipmap.audio, "Аудио-курс"));
        dividerLines.add(new DrawerLine(R.mipmap.video, "Видео-курс"));
        dividerLines.add(new DrawerLine(R.mipmap.memo, "Памятки"));
        dividerLines.add(new DrawerLine(R.mipmap.facts, "А вы знали?"));
        dividerLines.add(new DrawerLine(R.mipmap.about, "О приложении"));
    }

    static List<EnglishVideoItem> grammarVideos = new ArrayList<>();
    static{
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=Sc4gh5gP1AE", "How to improve your English speaking skills"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=_zhw3BUysUA", "Prepositions of place - in, on, at"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=0Ri3QTT41f8", "Past simple tense"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=X8lu4_5F0hg", "Present Simple verb tense"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=vLIJ9GfpuFc", "Present Perfect tense | Part 1 - Form"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=EOsk-lLy_aU", "When to use \\\"do\\\" and \\\"make\\\""));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=rjWd8U-6jbA", "Present Continuous verb | Present progressive verb"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=uZA6pIrwm-I", "When to use \\\"some\\\" and \\\"any\\\""));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=QpDDWBRHNRM", "Future simple tense - will and shall"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=0DExrsKWvGc", "When to use \"much\" and \"many\""));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=_HHvRwK3YjI", "How to improve your English vocabulary"));
        grammarVideos.add(new EnglishVideoItem("ttps://www.youtube.com/watch?v=aVo83K-GwHc", "Present Perfect tense | Part 2 - Use and meaning"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=YFUztCn_ldU", "Countable and uncountable nouns"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=RDVsQWJLlpM", "This, that, these, those - Demonstratives" ));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=D828Ffnh7pY", "Modal verbs of probability"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=vZsFu3nyUiY", "English future tense | Going to + verb"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=EZopcVLDCHg", "PAST PERFECT TENSE"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=TGwh9BvpE0o", "PAST CONTINUOUS TENSE"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=9p_pdjhPfMs", "How to tell the time in English"));
        grammarVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=4D8_GF2cqHY", "How to agree in English"));
    }

    static List<EnglishVideoItem> songVideos = new ArrayList<>();
    static{
        songVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=Pb00BjNvlkg", "The song about the pronouns"));
        songVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=Ru8W7h7KpYM",  "Good funny song"));
        songVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=k2_1TRuOBuE", "Months song"));
        songVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=TitbVi2ZZGk", "Warm Whispers Cover with Lyrics"));
        songVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=_CQ1aB4oWLk", "What the Water Gave Me (correct Lyrics) - Florence + The Machine"));
        songVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=_zjB6hVFYpE", "Little Black Submarines Lyrics (The Black Keys)"));
        songVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=bc8JMS8m8W4", "I Follow Rivers Cover"));
        songVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=EGVh_iWOfqA", "Fast Car Cover with lyrics"));
        songVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=xENE0wmdlyo",  "Little Black Sandals Cover w/ Lyrics"));
        songVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=RPkytoQROK4", "How to Train Your Dragon Soundtrack - Forbidden Friendship"));
        songVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=Ersyt5ZJF3Y", "Lovely Day (DJ Eleven Remix Do-Over)"));
        songVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=FlsBObg-1BQ", "Adele - Set Fire to the Rain Lyrics"));
        songVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=glGQhjcnnnI", "Breathe Me Cover"));
    }

    static List<EnglishVideoItem> tailsVideos = new ArrayList<>();
    static{
        tailsVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=U3y5Z-1JD7U", "Learn English With Story: The Boy Who Cried Wolf"));
        tailsVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=IwcTmpX-80Q",  "Learn English Through Story: Amazon Rally"));
        tailsVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=19rpKwemheg", "Learn English with Stories: The Locked Room"));
        tailsVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=8hDTgU1337U",  "Learn English Through Picture Story: Black Beard Teach"));
        tailsVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=_GLL4ssGQUI", "Learn English through Fairy Tales: The Grateful Crane"));
        tailsVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=27w7NoUJTio", "Learn English through Story: Robin Hood"));
        tailsVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=hfc8LAEXn-c", "Learn English through Fairy Tales: The Magical Ashes"));
        tailsVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=jRgKVRuTWHA", "Learn English through Stories: King Matthias and the Good Shepherd"));
        tailsVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=C-eEPm3A2N4", "Learn English through Stories: Johnny Appleseed"));
        tailsVideos.add(new EnglishVideoItem("https://www.youtube.com/watch?v=0_gQfqO7oHY", "Learn English through Audio Story: Legend of Sleepy Hollow " ));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mSlidingPanel = (SlidingPaneLayout) findViewById(R.id.SlidingPanel);
        mMenuList = (RecyclerView) findViewById(R.id.menuList);
        toolbar = (Toolbar) findViewById(R.id.eeToolbar);
        toolbar.setNavigationIcon(R.mipmap.menu_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSlidingPanel.isOpen()) {
                    mSlidingPanel.closePane();
                } else {
                    mSlidingPanel.openPane();
                }
            }
        });
        mMenuList.setLayoutManager(new LinearLayoutManager(this));
        mMenuList.setAdapter(new DrawerAdapter(this, dividerLines));
        mMenuList.getAdapter().notifyDataSetChanged();
        mMenuList.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        onRowClick(position);
                    }
                }));

        grammar = (TextView) findViewById(R.id.grammar);
        songs = (TextView) findViewById(R.id.songs);
        tails = (TextView) findViewById(R.id.tails);
        grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGrammar();
            }
        });
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSongs();
            }
        });
        tails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTails();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new GridVideoAdapter(this, grammarVideos));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        EnglishVideoItem item = ((GridVideoAdapter) recyclerView.getAdapter()).getItem(position);
                        englishClick(item);
                    }
                }));
        mSlidingPanel.setParallaxDistance(200);
        setGrammar();
    }

    private void englishClick(EnglishVideoItem item) {
        Intent intent = new Intent(this, TubePlayerActivity.class);
        intent.putExtra("title", item.getTitle());
        intent.putExtra("link", item.getUrl());

        startActivity(intent);
    }

    void setGrammar() {
        songs.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_middle));
        songs.setTextColor(Color.WHITE);

        tails.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_but));
        tails.setTextColor(Color.WHITE);

        grammar.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_sel_but));
        grammar.setTextColor(0xFF428093);
        recyclerView.setAdapter(new GridVideoAdapter(this, grammarVideos));

    }

    void setSongs() {
        grammar.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_but));
        grammar.setTextColor(Color.WHITE);

        tails.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_but));
        tails.setTextColor(Color.WHITE);

        songs.setBackgroundColor(Color.WHITE);
        songs.setTextColor(0xFF428093);
        recyclerView.setAdapter(new GridVideoAdapter(this, songVideos));

    }


    void setTails() {
        grammar.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_but));
        grammar.setTextColor(Color.WHITE);

        songs.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_middle));
        songs.setTextColor(Color.WHITE);

        tails.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_sel_but));
        tails.setTextColor(0xFF428093);
        recyclerView.setAdapter(new GridVideoAdapter(this, tailsVideos));

    }
    private void onRowClick(int position) {
        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(this, MainActivity.class);

                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, VerbsActivity.class);

                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, AudioActivity.class);

                startActivity(intent);
                break;
            case 3:
                mSlidingPanel.closePane();

                return;
            case 4:
                intent = new Intent(this, MemoActivity.class);

                startActivity(intent);
                break;
            case 5:
                intent = new Intent(this, FactsActivity.class);

                startActivity(intent);
                break;
            case 6:
                intent = new Intent(this, AboutActivity.class);

                startActivity(intent);
                break;
            default:
                break;
        }
        finish();
    }
}
