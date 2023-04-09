package com.example.musicapp.Data;

public class Artists {
    private String name ;
    private String id;

    public Artists() {
    }

    public Artists(String name) {
        this.name = name;
    }

    public Artists(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
