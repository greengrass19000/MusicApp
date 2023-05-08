package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.PlayerActivity;
import com.example.musicapp.Activity.SonglistActivity;
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;
import com.example.musicapp.Services.APIService;
import com.example.musicapp.Services.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> songList;

    public SearchAdapter(Context context, ArrayList<Song> songList) {
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.txtsinger.setText(song.getSinger());
        holder.txtsongname.setText(song.getSongName());
        Picasso.get().load(song.getSongImage()).into(holder.imgsong);
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgsong, iconlove;
        TextView txtsongname, txtsinger;
        public ViewHolder(View itemView) {
            super(itemView);
            imgsong = itemView.findViewById(R.id.imageviewsearch);
            iconlove = itemView.findViewById(R.id.iconlovesearch);
            txtsongname = itemView.findViewById(R.id.songnamesearch);
            txtsinger = itemView.findViewById(R.id.singersearch);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra("song", songList.get(getPosition()));
                context.startActivity(intent);
            });
            iconlove.setOnClickListener(view -> {
                iconlove.setImageResource(R.drawable.iconloved);
                Dataservice dataservice = APIService.getService();
                Call<String> callback = dataservice.UpdateLikerate("1", songList.get(getPosition()).getSongId());
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
                iconlove.setEnabled(false);
            });
        }
    }
}
