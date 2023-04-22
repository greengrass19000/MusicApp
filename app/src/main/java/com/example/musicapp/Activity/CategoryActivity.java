package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.musicapp.Adapter.CategoryByTopicAdapter;
import com.example.musicapp.Model.Category;
import com.example.musicapp.Model.Topic;
import com.example.musicapp.R;
import com.example.musicapp.Services.APIService;
import com.example.musicapp.Services.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    Topic topic;
    RecyclerView recyclerViewCategoryByTopic;
    Toolbar toolbarCategoryByTopic;
    CategoryByTopicAdapter categoryByTopicAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        GetIntent();
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List< Category>> callback = dataservice.GetCategoryByTopic(Integer.parseInt(topic.getTopicId()));
        callback.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                ArrayList<Category> categories = (ArrayList<Category>) response.body();
//                Log.d("categorybytopic", categories.get(0).getName());
                categoryByTopicAdapter = new CategoryByTopicAdapter(CategoryActivity.this, categories);
                recyclerViewCategoryByTopic.setLayoutManager(new GridLayoutManager(CategoryActivity.this, 2));
                recyclerViewCategoryByTopic.setAdapter(categoryByTopicAdapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("category")) {
            topic = (Topic) intent.getSerializableExtra("category");
            Toast.makeText(this, topic.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        recyclerViewCategoryByTopic = findViewById(R.id.recycleviewcategorybytopic);
        toolbarCategoryByTopic = findViewById(R.id.toolbarcategorybytopic);
        setSupportActionBar(toolbarCategoryByTopic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(topic.getName());
        toolbarCategoryByTopic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}