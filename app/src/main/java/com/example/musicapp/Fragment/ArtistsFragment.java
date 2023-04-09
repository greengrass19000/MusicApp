package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.example.musicapp.R;

import java.util.ArrayList;

public class ArtistsFragment  extends Fragment  {

    private ArrayList<String> artistNameList = new ArrayList<>();;
    private RequestQueue q;
    private ArrayAdapter arrayAdapter;
    View view;
    ListView artistListView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.artists_fragment, container,false);

        artistListView = view.findViewById(R.id.Artist_listview);

        arrayAdapter = new ArrayAdapter<>(
            getActivity(),
            R.layout.row_artist,
            artistNameList
        );

        return view;
    }


}
