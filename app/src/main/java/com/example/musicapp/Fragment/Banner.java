package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicapp.Data.Introduction;
import com.example.musicapp.R;
import com.example.musicapp.Services.APIService;
import com.example.musicapp.Services.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Banner extends Fragment {

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Introduction>> callback = dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<Introduction>>() {
            @Override
            public void onResponse(Call<List<Introduction>> call, Response<List<Introduction>> response) {
//                ArrayList<Introduction> banners = (ArrayList<Introduction>) response.body();
//                Log.d("hehe", banners.get(0).getSongName());
            }

            @Override
            public void onFailure(Call<List<Introduction>> call, Throwable t) {

            }
        });
    }
}
