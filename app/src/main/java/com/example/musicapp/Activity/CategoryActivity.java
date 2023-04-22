package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.musicapp.Adapter.CategoryAdapter;
import com.example.musicapp.Model.Category;
import com.example.musicapp.R;
import com.example.musicapp.Services.APIService;
import com.example.musicapp.Services.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllCategories;
    Toolbar toolbarAllCategories;

    CategoryAdapter categoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        init();
        GetData();
    }


    private void init() {
        recyclerViewAllCategories = findViewById(R.id.recyclerviewAllCategories);
        toolbarAllCategories = findViewById(R.id.toolbarAllCategories);
        setSupportActionBar(toolbarAllCategories);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả các chủ đề");
        toolbarAllCategories.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 finish();
            }
        });
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Category>> callback = dataservice.GetAllCategories();
        callback.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                ArrayList<Category> categories = (ArrayList<Category>) response.body();
//                Log.d("ALLCATEGORIES", categories.get(0).getName());
                categoryAdapter = new CategoryAdapter(CategoryActivity.this, categories);
                recyclerViewAllCategories.setLayoutManager(new GridLayoutManager(CategoryActivity.this, 1));
                recyclerViewAllCategories.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }
}