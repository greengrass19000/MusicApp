package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Introduction implements Serializable {

    @SerializedName("introduction_id")
    @Expose
    private String introductionId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("song_id")
    @Expose
    private String songId;
    @SerializedName("song_name")
    @Expose
    private String songName;
    @SerializedName("song_image")
    @Expose
    private String songImage;

    public String getIntroductionId() {
        return introductionId;
    }

    public void setIntroductionId(String introductionId) {
        this.introductionId = introductionId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }
}
