package com.example.musicapp.Services;

import com.example.musicapp.Model.Song;
import com.example.musicapp.Model.Album;
import com.example.musicapp.Model.Introduction;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.TopicCategoryToday;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("songbanner.php")
    Call<List<Introduction>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("topicandcategory.php")
    Call<TopicCategoryToday> GetTopicCategoryToday();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @FormUrlEncoded
    @POST("playlist.php")
    Call<List<Song>> GetPlaylistFromIntroduction(@Field("idquangcao") int idquangcao);

    @FormUrlEncoded
    @POST("playlist.php")
    Call<List<Song>> GetPlaylist(@Field("idplaylist") int idplaylist);

    @GET("favouritesong.php")
    Call<List<Song>> GetFavouriteSong();
}
