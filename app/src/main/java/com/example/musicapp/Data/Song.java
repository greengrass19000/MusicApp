package com.example.musicapp.Data;
public class Song {
    private int song_id;
    private String name;
    private String image;
    private String link;

    public Song(){ }

    public Song(int song_id, String name, String image, String link){
        this.song_id = song_id;
        this.name = name;
        this.image = image;
        this.link = link;
    }

    public String getSongName() {
        return name;
    }

    public int getId() {
        return song_id;
    }

    public String getLink() {
        return link;
    }
    public String getImage() {
        return image;
    }
}
