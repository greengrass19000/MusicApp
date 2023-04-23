package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.resources.Compatibility;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.musicapp.Adapter.AllAlbumAdapter;
import com.example.musicapp.Model.Album;
import com.example.musicapp.R;
import com.example.musicapp.Services.APIService;
import com.example.musicapp.Services.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAlbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllAlbum;
    Toolbar toolbarAllAlbum;
    AllAlbumAdapter allAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_album);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumList = (ArrayList<Album>) response.body();
                allAlbumAdapter = new AllAlbumAdapter(AllAlbumActivity.this, albumList);
                recyclerViewAllAlbum.setLayoutManager(new GridLayoutManager(AllAlbumActivity.this, 2));
                recyclerViewAllAlbum.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewAllAlbum = findViewById(R.id.recyclerviewAllAlbum);
        toolbarAllAlbum= findViewById(R.id.toolbarAllAlbum);
        setSupportActionBar(toolbarAllAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả Album");
        toolbarAllAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}