package com.example.musicapp.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.AllAlbumActivity;
import com.example.musicapp.Activity.CreatePlaylistActivity;
import com.example.musicapp.Adapter.CustomPlaylistAdapter;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.R;
import com.example.musicapp.Services.APIService;
import com.example.musicapp.Services.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_CustomPlaylist extends Fragment {
    View view;
    RecyclerView recyclerView;
    RelativeLayout createPlaylist;
    CustomPlaylistAdapter customPlaylistAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customed_playlist, container, false);
        recyclerView = view.findViewById(R.id.recyclerviewcustomplaylist);
        createPlaylist = view.findViewById(R.id.relativecustomplaylist);
        createPlaylist.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreatePlaylistActivity.class);
            startActivity(intent);
            updateList();
        });
        GetData();
        return view;
    }

    private void updateList() {

    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.GetCustomPlaylists();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> playlists = (ArrayList<Playlist>) response.body();
                customPlaylistAdapter = new CustomPlaylistAdapter(getActivity(), playlists);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(customPlaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
}
