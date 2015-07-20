package com.github.andrdev.easyenglish.model;

import android.util.Log;

/**
 * Created by taiyokaze on 7/18/15.
 */
public class EnglishVideoItem {
    String title;
    String photo;
    String url;

    public EnglishVideoItem(String url, String title) {
        this.photo = "http://img.youtube.com/vi/"+url.substring(url.lastIndexOf("=")+1)+"/1.jpg";
        Log.d("dree", photo);
        this.title = title;
        this.url = url;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
