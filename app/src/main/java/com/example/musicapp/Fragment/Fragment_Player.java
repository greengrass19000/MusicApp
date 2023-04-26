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

public class Fragment_Player extends Fragment {
    View view;
    RecyclerView recyclerViewPlayer;
    PlayerAdapter playerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_player, container, false);
        recyclerViewPlayer = view.findViewById(R.id.recyclerviewPlayer);
        if (PlayerActivity.songArrayList.size() > 0) {
            playerAdapter = new PlayerAdapter(getActivity(), PlayerActivity.songArrayList);
            recyclerViewPlayer.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlayer.setAdapter(playerAdapter);
        }
        return view;
    }
}
