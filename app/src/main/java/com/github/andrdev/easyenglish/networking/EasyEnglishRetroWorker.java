package com.github.andrdev.easyenglish.networking;

import com.github.andrdev.easyenglish.model.EnglishAudioItem;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;

/**
 * Created by taiyokaze on 7/18/15.
 */
public class EasyEnglishRetroWorker {

    private static final String AUDIO_URL = "http://api.soundcloud.com/users/boichenko-vladimir";

    private static final String AUDIO_USER_ID = "05efcdb21ae9dd466b7c7ba0589a8419";

    Gson gson;

    private static EasyEnglishRetroWorker eeRetroWorker;

    private static volatile EasyEnglishRetrofitService eeRestClient;



    private EasyEnglishRetroWorker() {
        createRestWorker();
    }

    public static EasyEnglishRetroWorker getInstance() {
        if (eeRetroWorker == null) {
            synchronized (EasyEnglishRetroWorker.class) {
                if (eeRetroWorker == null) {
                    eeRetroWorker = new EasyEnglishRetroWorker();
                }
            }
        }
        return eeRetroWorker;
    }

    private void createRestWorker() {
        gson = new Gson();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL).setClient(new OkClient(new OkHttpClient()))
                .setLog(new AndroidLog("DREE"))
                .setEndpoint(AUDIO_URL)
                .build();
        eeRestClient = restAdapter.create(EasyEnglishRetrofitService.class);
    }

    public void getSongs(Callback<List<EnglishAudioItem>> callback) {
        eeRestClient.getAudio(AUDIO_USER_ID, callback);
    }
}
