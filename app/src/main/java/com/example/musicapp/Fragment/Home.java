package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.*;
import com.example.musicapp.Adapter.SongsAdapter;
import com.example.musicapp.Data.HomeSongs;

public class Home extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        RecyclerView topSongsRecycleView = view.findViewById(R.id.topSongsRecycleView);

        HomeSongs[] songs = {
                new HomeSongs(R.drawable.spotify_icon_green, "So Far Away", "Avenged Sevenfold"),
                new HomeSongs(R.drawable.spotify_icon_green, "Ahora", "Yeika"),
                new HomeSongs(R.drawable.spotify_icon_green, "It Was a Good Day", "Ice Cub"),
                new HomeSongs(R.drawable.spotify_icon_green, "The Law", "Leonard Cohen"),
                new HomeSongs(R.drawable.spotify_icon_green, "Lovely Day", "Bill Withers"),
        };

        topSongsRecycleView.setAdapter(new SongsAdapter(songs));

        topSongsRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }
}