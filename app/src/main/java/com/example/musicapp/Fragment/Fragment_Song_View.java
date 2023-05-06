package com.example.musicapp.Fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

public class Fragment_Song_View extends Fragment {
    View view;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_song_image, container, false);
        imageView = view.findViewById(R.id.imageview);

        return view;
    }

    public void PlayMusic(String songImage) {
    }
}
