package com.example.musicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.SonglistActivity;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SonglistAdapter extends RecyclerView.Adapter<SonglistAdapter.ViewHolder> {

    Context context;
    ArrayList<Song> songs;

    public SonglistAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_songlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.txtSinger.setText(song.getSinger());
        holder.txtSongName.setText(song.getSongName());
        holder.txtIndex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txtSinger, txtSongName;
        ImageView imglikerate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSinger = itemView.findViewById(R.id.textviewsinger);
            txtIndex = itemView.findViewById(R.id.textviewindexlist);
            txtSongName = itemView.findViewById(R.id.textviewsongname);
            imglikerate = itemView.findViewById(R.id.imageviewlikerate);
        }
    }

}
