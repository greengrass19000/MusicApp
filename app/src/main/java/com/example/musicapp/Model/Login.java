package com.example.musicapp.Model;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.Services.Authentication;
import com.example.musicapp.R;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_fragment);


    }
    public void onRequestTokenClicked(View view) {
        Intent intent = new Intent(this, Authentication.class);
        startActivity(intent);
    }
}
