package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.PlayerActivity;
import com.example.musicapp.Activity.SonglistActivity;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;
import com.example.musicapp.Services.APIService;
import com.example.musicapp.Services.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            imglikerate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imglikerate.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLikerate("1", songs.get(getPosition()).getSongId());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String res = response.body();
                            if (res.equals("Success")) {
                                Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imglikerate.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayerActivity.class);
                    intent.putExtra("song", songs.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
