package com.example.musicapp.Services;

import com.example.musicapp.Data.Introduction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dataservice {
    @GET("songbanner.php")
    Call<List<Introduction>> GetDataBanner();
}
