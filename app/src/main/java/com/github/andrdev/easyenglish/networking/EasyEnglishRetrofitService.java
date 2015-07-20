package com.github.andrdev.easyenglish.networking;

import com.github.andrdev.easyenglish.model.EnglishAudioItem;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by taiyokaze on 7/18/15.
 */
interface EasyEnglishRetrofitService {

    @GET("/tracks.json")
    void getAudio(@Query("client_id") String userId, Callback<List<EnglishAudioItem>> callback);
}
