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
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllPlaylistsAdapter extends RecyclerView.Adapter<AllPlaylistsAdapter.ViewHolder> {

    Context context;
    ArrayList<Playlist> playlistArrayList;

    public AllPlaylistsAdapter(Context context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_all_playlists, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = playlistArrayList.get(position);
        Picasso.get().load(playlist.getImg()).into(holder.imgBackground);
        holder.txtName.setText(playlist.getName());
    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgBackground;
        TextView txtName;

        public ViewHolder(View itemView) {
            super(itemView);
            imgBackground = itemView.findViewById(R.id.imageviewplaylists);
            txtName = itemView.findViewById(R.id.textviewallplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SonglistActivity.class);
                    intent.putExtra("itemplaylist", playlistArrayList.get(getPosition()));
                    context.startActivity(intent);

                }
            });
        }
    }
}
