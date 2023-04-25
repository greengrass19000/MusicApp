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
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;
import com.example.musicapp.Services.APIService;
import com.example.musicapp.Services.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayerActivity.class);
                    intent.putExtra("song", songList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgLikerate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLikerate.setImageResource(R.drawable.iconloved);
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
                    imgLikerate.setEnabled(false);
                }
            });
        }
    }
}
