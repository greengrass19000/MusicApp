package com.example.musicapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Song implements Parcelable {

    @SerializedName("songId")
    @Expose
    private int songId;
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

    protected Song(Parcel in) {
        songId = in.readInt();
        songName = in.readString();
        songImage = in.readString();
        singer = in.readString();
        link = in.readString();
        likerate = in.readInt();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public int getLikerate() {
        return likerate;
    }

    public int getSongId() {
        return songId;
    }


    public String getLink() {
        return link;
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


    public void setLikerate(int likerate) {
        this.likerate = likerate;
    }

    public void setLink(String link) {
        this.link = link;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(songId);
        parcel.writeString(songName);
        parcel.writeString(songImage);
        parcel.writeString(singer);
        parcel.writeString(link);
        parcel.writeInt(likerate);
    }
}
