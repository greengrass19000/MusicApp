package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

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

    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean random = false;
    boolean next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        init();
        GetDataFromIntent();
        eventClick();
    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterPlaylist.getItem(1) != null) {
                    if (songArrayList.size() > 0) {
                        fragment_song_view.PlayMusic(songArrayList.get(0).getSongImage());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                } else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false) {
                    if (random == true) {
                        random = false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgshuffle.setImageResource(R.drawable.iconsuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        imgshuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (random == false) {
                    if (repeat == true) {
                        repeat = false;
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                        imgshuffle.setImageResource(R.drawable.iconshuffled);
                    }
                    imgshuffle.setImageResource(R.drawable.iconshuffled);
                    random = true;
                } else {
                    imgshuffle.setImageResource(R.drawable.iconsuffle);
                    random = false;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer = null;
                    }
                    if (position < (songArrayList.size())) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = songArrayList.size();
                            }
                            position -= 1;
                        }
                        if (random == true) {
                            Random random1 = new Random();
                            int index = random1.nextInt(songArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > songArrayList.size() - 1) {
                            position = 0;
                        }
                        new PlayMusic().execute(songArrayList.get(position).getLink());
                        fragment_song_view.PlayMusic(songArrayList.get(position).getSongImage());
                        getSupportActionBar().setTitle(songArrayList.get(position).getSongName());
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgnext.setClickable(true);
                        imgpre.setClickable(true);
                    }
                }, 5000);
            }
        });
        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer = null;
                    }
                    if (position < (songArrayList.size())) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position < 0) {
                            position = songArrayList.size() - 1;
                        }
                        if (repeat == true) {
                            position += 1;
                        }
                        if (random == true) {
                            Random random1 = new Random();
                            int index = random1.nextInt(songArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMusic().execute(songArrayList.get(position).getLink());
                        fragment_song_view.PlayMusic(songArrayList.get(position).getSongImage());
                        getSupportActionBar().setTitle(songArrayList.get(position).getSongName());
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgnext.setClickable(true);
                        imgpre.setClickable(true);
                    }
                }, 5000);
            }
        });
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
            for (int i = 0; i < songArrayList.size(); i++) {
                Log.d("asdf", songArrayList.get(i).getSongName());
            }
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
                mediaPlayer.stop();
                songArrayList.clear();
            }
        });
        toolbarplayer.setTitleTextColor(Color.WHITE);
        fragment_play_songList = new Fragment_Play_SongList();
        fragment_song_view = new Fragment_Song_View();
        adapterPlaylist = new ViewPagerPlaylist(getSupportFragmentManager());
        adapterPlaylist.AddFragment(fragment_play_songList);
        adapterPlaylist.AddFragment(fragment_song_view);
        viewPagerPlayer.setAdapter(adapterPlaylist);
        fragment_song_view = (Fragment_Song_View) adapterPlaylist.getItem(1);
        if(songArrayList.size() > 0) {
            getSupportActionBar().setTitle(songArrayList.get(0).getSongName());
            new PlayMusic().execute(songArrayList.get(0).getLink());
            imgplay.setImageResource(R.drawable.iconpause);
        }
    }

    class PlayMusic extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String song) {
            super.onPostExecute(song);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(song);
                mediaPlayer.prepare();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer.start();
            TimeSong();
        }


    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
}