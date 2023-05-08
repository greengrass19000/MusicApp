package com.example.musicapp.Services;

import com.example.musicapp.Model.Category;
import com.example.musicapp.Model.Song;
import com.example.musicapp.Model.Album;
import com.example.musicapp.Model.Introduction;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.Topic;
import com.example.musicapp.Model.TopicCategoryToday;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @FormUrlEncoded
    @POST("search.php")
    Call<List<Song>> Search(@Field("tukhoa") String tukhoa);
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

    @FormUrlEncoded
    @POST("playlist.php")
    Call<List<Song>> GetCategory(@Field("idcategory") int idcategory);

    @FormUrlEncoded
    @POST("categoriesbytopic.php")
    Call<List<Category>> GetCategoryByTopic(@Field("idchude") int idchude);

    @GET("allplaylists.php")
    Call<List<Playlist>> GetAllPlaylists();

    @GET("alltopics.php")
    Call<List<Topic>> GetAllTopics();

    @GET("favouritesong.php")
    Call<List<Song>> GetFavouriteSong();

    @GET("allalbum.php")
    Call<List<Album>> GetAllAlbum();

    @FormUrlEncoded
    @POST("playlist.php")
    Call<List<Song>> GetAlbum(@Field("idalbum") int idalbum);

    @FormUrlEncoded
    @POST("updatelikerate.php")
    Call<String> UpdateLikerate(@Field("likerate") String likerate, @Field("song_id") int song_id);
}
