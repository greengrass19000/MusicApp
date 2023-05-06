package com.example.musicapp.Activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    String[] permissions = new String[]{
            Manifest.permission.POST_NOTIFICATIONS
    };
    Button buttonchecknotification;
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

    private boolean permission_post_notification = false;
    public static final String CHANNEL_ID_1 = "1";
    public static final String CHANNEL_ID_2 = "2";
    public static final String NOTIFY_PREVIOUS = "com.example.musicapp.previous";
    public static final String NOTIFY_DELETE = "com.example.musicapp.delete";
    public static final String NOTIFY_PAUSE = "com.example.musicapp.pause";
    public static final String NOTIFY_PLAY = "com.example.musicapp.play";
    public static final String NOTIFY_NEXT = "com.example.musicapp.next";

    //    MediaSessionCompat mediaSession;
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

        imgsleeptimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BBB", "hehehehhe");
                showNotification(view);
            }
        });
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
                        UpdateTime();
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
                        UpdateTime();
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
    private ActivityResultLauncher<String> requestPermissionLauncherNotification = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            permission_post_notification = true;
            Log.d("PERMISSION", "Granted");
        } else {
            permission_post_notification = false;
            showPermissionDialog();
        }
    });
    public void showPermissionDialog() {
        new AlertDialog.Builder(
                PlayerActivity.this
        ).setTitle("Alert for permission")
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent rintent = new Intent();
                        rintent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        rintent.setData(uri);
                        startActivity(rintent);
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
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
        buttonchecknotification = findViewById(R.id.buttonchecknotification);
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
        if (songArrayList.size() > 0) {
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
            UpdateTime();
        }


    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }
        }, 300);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
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
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);


    }

    public void showNotification(View view) {
        Intent notifyIntent = new Intent(this, PlayerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.spotify_icon_green)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("BBB", "not granted");
            requestPermissionLauncherNotification.launch(permissions[0]);
        }
        Log.d("BBB", "granted");
        notificationManager.notify(1, builder.build());

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel);
            String description = "Default";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}