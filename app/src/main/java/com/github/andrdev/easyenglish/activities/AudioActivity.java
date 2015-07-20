package com.github.andrdev.easyenglish.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import com.github.andrdev.easyenglish.AudioPlayerService;
import com.github.andrdev.easyenglish.MusicController;
import com.github.andrdev.easyenglish.adapters.DrawerAdapter;
import com.github.andrdev.easyenglish.model.DrawerLine;
import com.github.andrdev.easyenglish.model.EnglishAudioItem;
import com.github.andrdev.easyenglish.adapters.GridAudioAdapter;
import com.github.andrdev.easyenglish.R;
import com.github.andrdev.easyenglish.RecyclerItemClickListener;
import com.github.andrdev.easyenglish.networking.EasyEnglishRetroWorker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by taiyokaze on 7/18/15.
 */
public class AudioActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {


    //service
    private AudioPlayerService musicSrv;
    private Intent playIntent;
    private boolean musicBound=false;
    private MusicController controller;
    private boolean paused=false, playbackPaused=true;

    SlidingPaneLayout mSlidingPanel;
    RecyclerView mMenuList;
    ImageView appImage;
    TextView titleSong;

    RecyclerView recyclerView;

    List<EnglishAudioItem> audioItems;

    TextView online;
    TextView offline;
    
    ImageView play;
    ImageView back;
    ImageView next;
    ImageView download;

    private Toolbar toolbar;
    static List<DrawerLine> dividerLines = new ArrayList<>();

    static {
        dividerLines.add(new DrawerLine(R.mipmap.gramm, "Грамматика"));
        dividerLines.add(new DrawerLine(R.mipmap.irregular, "Неправильные глаголы"));
        dividerLines.add(new DrawerLine(R.mipmap.audio, "Аудио-курс"));
        dividerLines.add(new DrawerLine(R.mipmap.video, "Видео-курс"));
        dividerLines.add(new DrawerLine(R.mipmap.memo, "Памятки"));
        dividerLines.add(new DrawerLine(R.mipmap.facts, "А вы знали?"));
        dividerLines.add(new DrawerLine(R.mipmap.about, "О приложении"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        initToolbar();
        initDrawer();
        initAudioList();
        setOnline();
        initControls();
        findViewById(R.id.progressAudio).setVisibility(View.INVISIBLE);

    }

    private void initControls() {
        titleSong = (TextView) findViewById(R.id.titleSong);
        play = (ImageView) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPauseSong();
            }
        });
        next = (ImageView) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNext();
            }
        });
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPrev();
            }
        });
        download = (ImageView) findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadSong(audioItems.get(musicSrv.getCurrentSongPosition()));
            }
        });
    }

    private void playPauseSong() {
        if(playbackPaused){
            musicSrv.playSong();
            setPlay();
        } else {
            pause();
            setPause();
        }
    }

    private void setPause() {
        play.setImageResource(R.mipmap.play);
        playbackPaused = true;
    }

    private void setPlay() {
        titleSong.setText(audioItems.get(musicSrv.getCurrentSongPosition()).getTitle());
        play.setImageResource(R.mipmap.pause);
        playbackPaused = false;
    }

    private void downloadSong(EnglishAudioItem item) {
        new DownloadFile().execute(item);
        findViewById(R.id.progressAudio).setVisibility(View.VISIBLE);

    }

    private void initAudioList() {
        audioItems = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new GridAudioAdapter(this, audioItems));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        songPicked(position);
                    }
                }));
    }

    private void getAudioList() {
        Callback<List<EnglishAudioItem>> callback = new Callback<List<EnglishAudioItem>>() {
            @Override
            public void success(List<EnglishAudioItem> englishAudioItems, Response response) {
                audioItems.clear();
                audioItems.addAll(englishAudioItems);
                recyclerView.getAdapter().notifyDataSetChanged();
                download.setVisibility(View.VISIBLE);
                musicSrv.setList(englishAudioItems);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        };

        EasyEnglishRetroWorker.getInstance().getSongs(callback);
    }

    private void initDrawer() {
        mSlidingPanel = (SlidingPaneLayout) findViewById(R.id.SlidingPanel);
        mSlidingPanel.setParallaxDistance(200);
        mMenuList = (RecyclerView) findViewById(R.id.menuList);
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
    }

    private void initToolbar() {
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
        online = (TextView)toolbar.findViewById(R.id.online);
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnline();
            }
        });

        offline = (TextView)toolbar.findViewById(R.id.offline);
        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOffline();
            }
        });
        setOnline();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(playIntent==null){
            playIntent = new Intent(this, AudioPlayerService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        paused=true;
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(paused){
//            setController();
            paused=false;
        }
    }
    @Override
    protected void onStop() {
//        controller.hide();
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        stopService(playIntent);
        musicSrv = null;
        super.onDestroy();
    }

    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AudioPlayerService.MusicBinder binder = (AudioPlayerService.MusicBinder)service;
            //get service
            musicSrv = binder.getService();
            //pass list
            musicSrv.setList(audioItems);
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    private void setOffline() {
        online.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_but));
        online.setTextColor(Color.WHITE);

        offline.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_sel_but));
        offline.setTextColor(0xFF428093);
        getDriveList();
    }

    private void getDriveList() {
        new GetDriveFileList().execute();
    }

    private void setOnline(){
        offline.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_right_but));
        offline.setTextColor(Color.WHITE);

        online.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_left_sel_but));
        online.setTextColor(0xFF428093);
        getAudioList();
    }

    public void songPicked(int position){
        musicSrv.setSong(position);
        musicSrv.playSong();
        if(playbackPaused){
//            setController();
        }
        setPlay();

//        controller.show(0);
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
                mSlidingPanel.closePane();

                return;
            case 3:
                intent = new Intent(this, VideoActivity.class);

                startActivity(intent);
                break;
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

    @Override
    public void start() {
        musicSrv.go();
    }

    @Override
    public void pause() {
        playbackPaused=true;
        musicSrv.pausePlayer();
    }

    @Override
    public int getDuration() {
        if(musicSrv!=null && musicBound && musicSrv.isPng()) {
            return musicSrv.getDur();
        } else {
            return 0;
        }
    }

    @Override
    public int getCurrentPosition() {
        if(musicSrv!=null && musicBound && musicSrv.isPng()) {
            return musicSrv.getPosn();
        } else {
            return 0;
        }
    }

    @Override
    public void seekTo(int pos) {
        musicSrv.seek(pos);
    }

    @Override
    public boolean isPlaying() {
        if(musicSrv!=null && musicBound) {
            return musicSrv.isPng();
        }
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    private void setController(){
//        controller = new MusicController(this);
//        controller.setPrevNextListeners(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playNext();
//            }
//        }, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playPrev();
//            }
//        });
//        controller.setMediaPlayer(this);
//        controller.setAnchorView(findViewById(R.id.recyclerView));
//        controller.setEnabled(true);
    }

    //play next
    private void playNext(){
        musicSrv.playNext();
        if(playbackPaused){
//            setController();
            setPlay();
        }
//        controller.show(0);
    }

    //play previous
    private void playPrev(){
        musicSrv.playPrev();
        if(playbackPaused){
//            setController();
            setPlay();
        }
//        controller.show(0);
    }

    public class DownloadFile extends AsyncTask<EnglishAudioItem, Void, String> {



        @Override
        protected String doInBackground(EnglishAudioItem... params) {
            new File(getFilesDir()+"/songs/").mkdir();
            new File(getFilesDir()+"/images/").mkdir();
            File file = new File(getFilesDir()+"/songs/"+params[0].getTitle()+"."+params[0].getOriginalFormat());
            Log.d("dree", Arrays.toString(getFilesDir().listFiles()));

            if(!file.exists()) {
                downloadFromUrl(params[0].getDownloadUrl(), "/songs/", params[0].getTitle() + "." + params[0].getOriginalFormat());
                if(params[0].getImage() != null) {
                    downloadFromUrl(params[0].getImage(), "/images/",
                            params[0].getTitle() + params[0].getImage().substring(params[0].getImage().lastIndexOf(".")));
                }
                return "Скачивание "+params[0].getTitle()+" окончено";
            }
            return params[0].getTitle() + " уже закачан";
        }

        @Override
        protected void onPostExecute(String title) {
            Toast.makeText(AudioActivity.this, title, Toast.LENGTH_SHORT).show();
            AudioActivity.this.findViewById(R.id.progressAudio).setVisibility(View.INVISIBLE);

        }
    }

    public class GetDriveFileList extends AsyncTask<Void, Void, List<EnglishAudioItem>> {

        @Override
        protected List<EnglishAudioItem> doInBackground(Void... params) {
            File file = new File(getFilesDir()+"/songs/");
            Log.d("dree", Arrays.toString(file.listFiles()));
//            file.{"3gp", "mp4", "m4a", "mp3", "wav"};
            File [] files = file.listFiles();
            List<EnglishAudioItem> items = new ArrayList<>();
            if(files == null){
                return items;
            }
            for(File f:files){
                String filepath = f.getPath();
                Log.d("dree", f.getPath());
                Log.d("dree", f.getName());
                String title = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.lastIndexOf("."));
                Log.d("dreeTitle", title);

                String picturePath = getPicture(title);

                items.add(new EnglishAudioItem(filepath, picturePath, title));
            }
            return items;
        }

        private String getPicture(String mark) {
            File file = new File(getFilesDir()+"/images/");
            Log.d("dree", Arrays.toString(file.listFiles()));

            File [] files = file.listFiles();
            for(File f:files){{
                String path = f.getPath();
                if(path.substring(path.lastIndexOf("/")+1, path.lastIndexOf(".")).equals(mark)){
                    return f.getPath();
                }
            }
            }
            return null;

        }

        @Override
        protected void onPostExecute(List<EnglishAudioItem> items) {
            audioItems.clear();
            audioItems.addAll(items);
            recyclerView.getAdapter().notifyDataSetChanged();
            download.setVisibility(View.GONE);
            musicSrv.setList(items);
        }
    }


    public void downloadFromUrl(String downloadUrl, String folder, String fileName) {
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(downloadUrl);

            connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(true);
            connection.connect();

            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if(connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP){
                url = new URL(connection.getHeaderField("Location"));
                connection = (HttpURLConnection) url.openConnection();
                connection.setInstanceFollowRedirects(true);
                connection.connect();
            }
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {

                Log.d("dree", ""+connection.getHeaderFields());
            }
            Log.d("dree", "" + getFilesDir() + folder + fileName);
            // this will be useful to display download percentage
            // might be -1: server did not report the length
//            int fileLength = connection.getContentLength();

            // download the file
            input = connection.getInputStream();
            output = new FileOutputStream(getFilesDir()+folder+fileName);

            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
//                // allow canceling with back button
//                if (isCancelled()) {
//                    input.close();
//                    return null;
//                }
                total += count;
                Log.d("dree", ""+total);

                // publishing the progress....
//                if (fileLength > 0) // only if total length is known
//                    publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }
        } catch (Exception e) {
                            Log.d("dree", ""+e.toString());

        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }

            if (connection != null)
                connection.disconnect();
        }
    }
}
