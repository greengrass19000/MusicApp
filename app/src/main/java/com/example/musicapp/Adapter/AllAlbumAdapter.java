package com.example.musicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Model.Album;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> albumList;

    public AllAlbumAdapter(Context context, ArrayList<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_allalbum, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumList.get(position);
        Picasso.get().load(album.getImage()).into(holder.imgAllAlbum);
        holder.txtAllAlbumName.setText(album.getName());
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAllAlbum;
        TextView txtAllAlbumName;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAllAlbum = itemView.findViewById(R.id.imageviewallalbum);
            txtAllAlbumName = itemView.findViewById(R.id.textviewallalbumname);
        }
    }
}
