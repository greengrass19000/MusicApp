package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.musicapp.R;


public class PlaylistsFragment extends Fragment{

    View view;
    LinearLayout playlistLinearLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.playlists_fragment, container, false);
        playlistLinearLayout = view.findViewById(R.id.playlistLinearLayout);

        return view;
    }
}
