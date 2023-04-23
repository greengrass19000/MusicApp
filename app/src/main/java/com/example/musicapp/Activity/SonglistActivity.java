package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.musicapp.Adapter.SonglistAdapter;
import com.example.musicapp.Model.Album;
import com.example.musicapp.Model.Category;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.Song;
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

public class SonglistActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewPlaylist;
    FloatingActionButton floatingActionButton;
    Introduction introduction;
    ImageView imageViewPlaylist;
    ArrayList<Song> songList;
    SonglistAdapter songlistAdapter;
    Playlist playlist;
    Category category;
    Album album;
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

        if (playlist != null && !playlist.getName().equals("")) {
            setValueInView(playlist.getName(), playlist.getImg());
            GetDataPlaylist(playlist.getIdPlaylist());
        }

        if(category !=  null && !category.getName().equals("")) {
            setValueInView(category.getName(), category.getImage());
            GetDataCategory(category.getCategoryId());
        }
        if (album != null && !album.getName().equals("")) {
            setValueInView(album.getName(), album.getImage());
            GetDataAlbum(album.getAlbumId());
        }
    }

    private void GetDataAlbum(String albumId) {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> callback = dataservice.GetAlbum(Integer.parseInt(albumId));
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songList = (ArrayList<Song>) response.body();
                songlistAdapter = new SonglistAdapter(SonglistActivity.this, songList);
                recyclerViewPlaylist.setLayoutManager(new LinearLayoutManager(SonglistActivity.this));
                recyclerViewPlaylist.setAdapter(songlistAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataCategory(String categoryId) {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> callback = dataservice.GetCategory(Integer.parseInt(categoryId));
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songList = (ArrayList<Song>) response.body();
                songlistAdapter = new SonglistAdapter(SonglistActivity.this, songList);
                recyclerViewPlaylist.setLayoutManager(new LinearLayoutManager(SonglistActivity.this));
                recyclerViewPlaylist.setAdapter(songlistAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> callback = dataservice.GetPlaylist(Integer.parseInt(idplaylist));
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songList = (ArrayList<Song>) response.body();
                songlistAdapter = new SonglistAdapter(SonglistActivity.this, songList);
                recyclerViewPlaylist.setLayoutManager(new LinearLayoutManager(SonglistActivity.this));
                recyclerViewPlaylist.setAdapter(songlistAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void GetIntroductionData(String introductionId) {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> callback = dataservice.GetPlaylistFromIntroduction(Integer.parseInt(introductionId));
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songList = (ArrayList<Song>) response.body();
                Log.d("BBB", songList.get(0).getSongName());
                songlistAdapter = new SonglistAdapter(SonglistActivity.this, songList);
                recyclerViewPlaylist.setLayoutManager(new LinearLayoutManager(SonglistActivity.this));
                recyclerViewPlaylist.setAdapter(songlistAdapter);

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
            if (intent.hasExtra("itemplaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");

            }
            if (intent.hasExtra("categoryId")) {
                category = (Category) intent.getSerializableExtra("categoryId");
            }
            if (intent.hasExtra("album")) {
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }
}