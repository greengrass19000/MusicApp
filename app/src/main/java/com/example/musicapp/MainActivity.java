package com.example.musicapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.musicapp.Fragment.Library;
import com.example.musicapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.musicapp.Fragment.Home;
import com.example.musicapp.Fragment.Search;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navView;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new Home()).commit();
        navView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    fragment = new Home();
                    break;
                case R.id.search:
                    fragment = new Search();
                    break;
                case R.id.library:
                    fragment = new Library();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
            return true;
        });

    }
}
