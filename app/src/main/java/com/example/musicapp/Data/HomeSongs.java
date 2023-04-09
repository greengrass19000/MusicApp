package com.example.musicapp.Data;

public class HomeSongs {

    private int songCover;
    private String songName;
    private String songSinger;

    public HomeSongs(int songCover, String songName, String songSinger) {
        this.songCover = songCover;
        this.songName = songName;
        this.songSinger = songSinger;
    }

    public String getSongName() {
        return songName;
    }

    public String getSongSinger() {
        return songSinger;
    }
}
