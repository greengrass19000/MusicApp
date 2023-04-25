package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.Model.Song;
import com.example.musicapp.R;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    Toolbar toolbarplayer;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgrepeat, imgnext, imgpre, imgshuffle, imgsleeptimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        init();
        Intent intent = getIntent();
        if (intent.hasExtra("song")) {
            Song song = intent.getParcelableExtra("song");
            Toast.makeText(this, song.getSongName(), Toast.LENGTH_SHORT).show();
        }
        if (intent.hasExtra("songs")) {
            ArrayList<Song> songs = intent.getParcelableArrayListExtra("songs");
            for (int i = 0; i < songs.size(); i++) {
                Log.d("playsongs", songs.get(i).getSongName());
            }
//            Toast.makeText(this, songs.get(0).getSongName(), Toast.LENGTH_SHORT).show();
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
        setSupportActionBar(toolbarplayer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplayer.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbarplayer.setTitleTextColor(Color.WHITE);
    }

}