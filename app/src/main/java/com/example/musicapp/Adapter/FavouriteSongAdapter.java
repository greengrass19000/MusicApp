package com.example.musicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Model.Song;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavouriteSongAdapter extends RecyclerView.Adapter<FavouriteSongAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> songList;

    public FavouriteSongAdapter(Context context, ArrayList<Song> songList) {
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_favouritesong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.txtSinger.setText(song.getSinger());
        holder.txtName.setText(song.getSongName());
        Picasso.get().load(song.getSongImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtSinger;
        ImageView img, imgLikerate;
        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.textviewfavouritesongname);
            txtSinger = itemView.findViewById(R.id.textviewfavouritesongsinger);
            img = itemView.findViewById(R.id.imageviewfavouritesong);
            imgLikerate = itemView.findViewById(R.id.imageviewlikerate);
        }
    }
}
