package com.github.andrdev.easyenglish.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taiyokaze on 7/18/15.
 */
public class EnglishAudioItem {

    @SerializedName("stream_url")
    String audioPath;

    @SerializedName("download_url")
    String downloadUrl;

    String title;

    @SerializedName("artwork_url")
    String image;

    @SerializedName("original_format")
    String originalFormat;

    public EnglishAudioItem() {
    }

    public EnglishAudioItem(String audioPath, String image, String title) {
        this.audioPath = audioPath;
        this.image = image;
        this.title = title;
    }

    public String getOriginalFormat() {
        return originalFormat;
    }

    public void setOriginalFormat(String originalFormat) {
        this.originalFormat = originalFormat;
    }

    public String getDownloadUrl() {
        return downloadUrl+"?client_id=05efcdb21ae9dd466b7c7ba0589a8419";
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStreamPath() {
        if(audioPath.contains("http")) {
            return audioPath + "?client_id=05efcdb21ae9dd466b7c7ba0589a8419";
        }
        return audioPath;
    }

    public String getPath() {
        return audioPath;
    }

    public void setStreamPath(String streamUrl) {
        this.audioPath = streamUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
