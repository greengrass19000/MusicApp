package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.musicapp.Model.Introduction;
import com.example.musicapp.R;

public class PlaylistActivity extends Activity {
    Introduction introduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        DataIntent();
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