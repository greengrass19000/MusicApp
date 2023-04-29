package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.Adapter.ViewPagerPlaylist;
import com.example.musicapp.Fragment.Fragment_Play_SongList;
import com.example.musicapp.Fragment.Fragment_Song_View;
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    Toolbar toolbarplayer;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgrepeat, imgnext, imgpre, imgshuffle, imgsleeptimer;
    ViewPager viewPagerPlayer;
    public static ArrayList<Song> songArrayList = new ArrayList<>();
    public static ViewPagerPlaylist adapterPlaylist;
    Fragment_Song_View fragment_song_view;
    Fragment_Play_SongList fragment_play_songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        init();
        GetDataFromIntent();

    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        songArrayList.clear();
        if (intent == null) return;
        if (intent.hasExtra("song")) {
            Song song = intent.getParcelableExtra("song");
            //Toast.makeText(this, song.getSongName(), Toast.LENGTH_SHORT).show();
            songArrayList.add(song);
        }
        if (intent.hasExtra("songs")) {
            ArrayList<Song> songs = intent.getParcelableArrayListExtra("songs");
            songArrayList = songs;
        }
    }

    private void init() {
        toolbarplayer = findViewById(R.id.toolbarplaynhac);
        txtTimesong = findViewById(R.id.textviewtimesong);
        txtTotaltimesong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgpre = findViewById(R.id.imagebuttonpreview);
        imgshuffle = findViewById(R.id.imagebuttonshuffle);
        imgsleeptimer = findViewById(R.id.imagebuttonsleeptimer);
        viewPagerPlayer = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarplayer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplayer.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbarplayer.setTitleTextColor(Color.WHITE);
        fragment_play_songList = new Fragment_Play_SongList();
        fragment_song_view = new Fragment_Song_View();
        adapterPlaylist = new ViewPagerPlaylist(getSupportFragmentManager());
        adapterPlaylist.AddFragment(fragment_play_songList);
        adapterPlaylist.AddFragment(fragment_song_view);
        viewPagerPlayer.setAdapter(adapterPlaylist);
    }

}