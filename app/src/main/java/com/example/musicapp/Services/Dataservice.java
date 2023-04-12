package com.example.musicapp.Services;

import com.example.musicapp.Model.Introduction;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.TopicCategoryToday;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dataservice {
    @GET("songbanner.php")
    Call<List<Introduction>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("topicandcategory.php")
    Call<TopicCategoryToday> GetTopicCategoryToday();
}
