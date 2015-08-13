package com.github.andrdev.easyenglish.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.SeekBar;
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
public class AudioActivity extends BaseSlidingActivity implements MediaController.MediaPlayerControl,
        SeekBar.OnSeekBarChangeListener{


    //service
    private AudioPlayerService musicSrv;
    private Intent playIntent;
    private boolean musicBound=false;
    private boolean paused=false, playbackPaused=true;
    private Handler mHandler = new Handler();
    TextView titleSong;

    RecyclerView recyclerView;

    List<EnglishAudioItem> audioItems;

    TextView online;
    TextView offline;
    TextView currentTime;
    TextView durration;
    ImageView play;
    ImageView back;
    ImageView next;
    ImageView download;

    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initAudioList();
        setOnline();
        initControls();
        findViewById(R.id.progressAudio).setVisibility(View.INVISIBLE);
    }

    @Override
    int getMainLayout() {
        return R.layout.activity_audio;
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
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        currentTime = (TextView) findViewById(R.id.timePlayed);
        durration = (TextView) findViewById(R.id.timeTotal);
        seekBar.setOnSeekBarChangeListener(this);
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
        seekBar.setProgress(songPercent(getCurrentPosition(), getDuration()));
        seekBar.setMax(100);
        play.setImageResource(R.mipmap.pause);
        updateSeekBar();
        playbackPaused = false;
    }

    int songPercent(long currPos, long durration) {
        if(currPos==0){
            return 0;
        }
        return (int)(currPos/(durration / 100));
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



    @Override
    int getCurrentActivityPostion() {
        return 2;
    }

    private void initToolbar() {
        online = (TextView)getToolbar().findViewById(R.id.online);
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnline();
            }
        });

        offline = (TextView)getToolbar().findViewById(R.id.offline);
        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOffline();
            }
        });
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
            paused=false;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        unbindService(musicConnection);
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

    void updateSeekBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }
    public int progressToTimer(int progress, int totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double)progress) / 100) * totalDuration);

        // return current duration in milliseconds
        return currentDuration * 1000;
    }
    public String milliSecondsToTimer(long milliseconds){
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int)( milliseconds / (1000*60*60));
        int minutes = (int)(milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000*60*60)) % (1000*60) / 1000);
        // Add hours if there
        if(hours > 0){
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if(seconds < 10){
            secondsString = "0" + seconds;
        }else{
            secondsString = "" + seconds;}

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = getDuration();
            long currentDuration = getCurrentPosition();

            // Displaying Total Duration time
            durration.setText(""+milliSecondsToTimer(totalDuration));
            // Displaying time completed playing
            currentTime.setText(""+milliSecondsToTimer(currentDuration));

            // Updating progress bar
            int progress = songPercent(currentDuration, totalDuration);
            //Log.d("Progress", ""+progress);
            seekBar.setProgress(progress);

            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = getDuration();
        int currentPosition = progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        seekTo(currentPosition);

        // update timer progress again
        updateSeekBar();
    }

    public class DownloadFile extends AsyncTask<EnglishAudioItem, Void, String> {
        @Override
        protected String doInBackground(EnglishAudioItem... params) {
            new File(getFilesDir()+"/songs/").mkdir();
            new File(getFilesDir()+"/images/").mkdir();
            File file = new File(getFilesDir()+"/songs/"+params[0].getTitle()+"."+params[0].getOriginalFormat());

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
            File [] files = file.listFiles();
            List<EnglishAudioItem> items = new ArrayList<>();
            if(files == null){
                return items;
            }
            for(File f:files){
                String filepath = f.getPath();
                String title = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.lastIndexOf("."));
                String picturePath = getPicture(title);
                items.add(new EnglishAudioItem(filepath, picturePath, title));
            }
            return items;
        }

        private String getPicture(String mark) {
            File file = new File(getFilesDir()+"/images/");
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
            input = connection.getInputStream();
            output = new FileOutputStream(getFilesDir()+folder+fileName);

            byte data[] = new byte[4096];
            int count;
            while ((count = input.read(data)) != -1) {
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
