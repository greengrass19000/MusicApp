package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.PlayerActivity;
import com.example.musicapp.Adapter.PlayerAdapter;
import com.example.musicapp.R;

public class Fragment_Play_SongList extends Fragment {
    View view;

    RecyclerView recyclerView;
    PlayerAdapter playerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_songlist, container, false);
        recyclerView = view.findViewById(R.id.recyclerviewplayer);
        if(PlayerActivity.songArrayList.size() > 0) {
            playerAdapter = new PlayerAdapter(getActivity(), PlayerActivity.songArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(playerAdapter);
        }
        return view;
    }


}
