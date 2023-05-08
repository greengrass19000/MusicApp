package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.musicapp.Adapter.TopicAdapter;
import com.example.musicapp.Model.Topic;
import com.example.musicapp.R;
import com.example.musicapp.Services.APIService;
import com.example.musicapp.Services.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllCategories;
    Toolbar toolbarAllCategories;
    TopicAdapter topicAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        GetData();
        init();
    }


    private void init() {
        recyclerViewAllCategories = findViewById(R.id.recyclerviewAllTopics);
        toolbarAllCategories = findViewById(R.id.toolbarAllCategories);
        setSupportActionBar(toolbarAllCategories);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả các chủ đề");
        toolbarAllCategories.setNavigationOnClickListener(view -> finish());
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Topic>> callback = dataservice.GetAllTopics();
        callback.enqueue(new Callback<List<Topic>>() {
            @Override
            public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                ArrayList<Topic> topics = (ArrayList<Topic>) response.body();
                topicAdapter = new TopicAdapter(TopicActivity.this, topics);
                recyclerViewAllCategories.setLayoutManager(new GridLayoutManager(TopicActivity.this, 1));
                recyclerViewAllCategories.setAdapter(topicAdapter);
            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {

            }
        });
    }
}