package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import com.example.musicapp.Adapter.AllPlaylistsAdapter;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.R;
import com.example.musicapp.Services.APIService;
import com.example.musicapp.Services.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewPlaylists;

    AllPlaylistsAdapter allPlaylistsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist2);
        mapping();
        init();
        GetData();
    }

    private void mapping() {
        toolbar = findViewById(R.id.toolbarplaylists);
        recyclerViewPlaylists = findViewById(R.id.recyclerviewplaylist);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.purple));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.GetAllPlaylists();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> playlistArrayList = (ArrayList<Playlist>)  response.body();
//                Log.d("ALLplaylists", playlistArrayList.get(0).getName());
                allPlaylistsAdapter = new AllPlaylistsAdapter(PlaylistActivity.this, playlistArrayList);
                recyclerViewPlaylists.setLayoutManager(new GridLayoutManager(PlaylistActivity.this, 2));
                recyclerViewPlaylists.setAdapter(allPlaylistsAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
}