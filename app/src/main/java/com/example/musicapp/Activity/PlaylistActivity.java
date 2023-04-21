package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.musicapp.Data.Song;
import com.example.musicapp.Model.Introduction;
import com.example.musicapp.R;
import com.example.musicapp.Services.APIService;
import com.example.musicapp.Services.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewPlaylist;
    FloatingActionButton floatingActionButton;
    Introduction introduction;
    ImageView imageViewPlaylist;
    ArrayList<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        DataIntent();
        mapping();
        init();

        if (introduction != null && !introduction.getSongName().equals("")) {
            setValueInView(introduction.getSongName(), introduction.getImage());
            GetIntroductionData(introduction.getIntroductionId());
        }
    }

    private void GetIntroductionData(String introductionId) {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> callback = dataservice.GetPlaylistFromIntroduction(introductionId);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songList = (ArrayList<Song>) response.body();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String name, String img) {
        collapsingToolbarLayout.setTitle(name);
        try {
//            Log.d("setValueInView", img);
            URL url = new URL(img);
//            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
//            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.get().load(img).into(imageViewPlaylist);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void mapping() {
        coordinatorLayout = findViewById(R.id.coordinatorlayoutplaylist);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbarplaylist);
        recyclerViewPlaylist = findViewById(R.id.recyclerviewplaylist);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imageViewPlaylist = findViewById(R.id.imageviewplaylist);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                introduction = (Introduction) intent.getSerializableExtra("banner");
                Toast.makeText(this, introduction.getSongName(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}