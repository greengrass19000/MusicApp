package com.example.musicapp.Data;

public class HomeArtists {

    private int image;
    private String name;

    public HomeArtists(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
