package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.PlaylistActivity;
import com.example.musicapp.Activity.SonglistActivity;
import com.example.musicapp.Model.Album;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> albumList;

    public AlbumAdapter(Context context, ArrayList<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.txtAlbumSinger.setText(album.getSinger());
        holder.txtAlbumName.setText(album.getName());
        Picasso.get().load(album.getImage()).into(holder.albumImg);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView albumImg;
        TextView txtAlbumName, txtAlbumSinger;
        public ViewHolder(View itemView) {
            super(itemView);
            albumImg = itemView.findViewById(R.id.imageviewalbum);
            txtAlbumName = itemView.findViewById(R.id.textviewalbumname);
            txtAlbumSinger = itemView.findViewById(R.id.textviewalbumsinger);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SonglistActivity.class);
                    intent.putExtra("album", albumList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
