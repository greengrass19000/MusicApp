package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.Model.Introduction;
import com.squareup.picasso.Picasso;

public class SongListActivity extends AppCompatActivity {

    Introduction introduction;

    //Custom
    Toolbar toolbarplayer;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgrepear, imgnext, imgsuffle;
    ViewPager viewPagerPlayer;

//    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        init();
        DateIntent();
    }

    private void DateIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                introduction = (Introduction) intent.getSerializableExtra("banner");
                Toast.makeText(this, introduction.getSongName(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void init() {
        toolbarplayer = findViewById(R.id.toolbarplayer);
        txtTimesong = findViewById(R.id.textviewtimesong);
        txtTotaltimesong = findViewById(R.id.textviewtimetotalsong);
        sktime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgrepear = findViewById(R.id.imagebuttonrepeat);
        imgsuffle = findViewById(R.id.imagebuttonsuffle);
        viewPagerPlayer = findViewById(R.id.viewpagerplayer);
//        imageView = findViewById(R.id.imageView);
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