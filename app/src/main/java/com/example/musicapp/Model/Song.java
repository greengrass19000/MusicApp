package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Song implements Serializable {

    @SerializedName("song_id")
    @Expose
    private int songId;
    @SerializedName("album_id")
    @Expose
    private String albumId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("playlist_id")
    @Expose
    private String playlistId;
    @SerializedName("name")
    @Expose
    private String songName;
    @SerializedName("image")
    @Expose
    private String songImage;
    @SerializedName("singer")
    @Expose
    private String singer;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("likerate")
    @Expose
    private int likerate;

    public int getLikerate() {
        return likerate;
    }

    public int getSongId() {
        return songId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getLink() {
        return link;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public String getSongImage() {
        return songImage;
    }

    public String getSinger() {
        return singer;
    }

    public String getSongName() {
        return songName;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setLikerate(int likerate) {
        this.likerate = likerate;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
