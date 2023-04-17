package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.musicapp.Model.Introduction;
import com.example.musicapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlaylistActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewPlaylist;
    FloatingActionButton floatingActionButton;
    Introduction introduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        DataIntent();
        init();
    }

    private void init() {
        coordinatorLayout = findViewById(R.id.coordinatorlayoutplaylist);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbarplaylist);
        recyclerViewPlaylist = findViewById(R.id.recyclerviewplaylist);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
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