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
import androidx.viewpager.widget.PagerAdapter;

import com.example.musicapp.Activity.PlaylistActivity;
import com.example.musicapp.Model.Introduction;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList <Introduction> arrayListbanner;

    public BannerAdapter(Context context, ArrayList<Introduction> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    @Override
    public int getCount() {
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_banner, null);

        ImageView background = view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView image = view.findViewById(R.id.imageviewbanner);
        TextView title = view.findViewById(R.id.textviewtitlebannersong);
        TextView content = view.findViewById(R.id.textviewcontent);

        Picasso.get().load(arrayListbanner.get(position).getImage()).into(background);
        Picasso.get().load(arrayListbanner.get(position).getSongImage()).into(image);
        title.setText(arrayListbanner.get(position).getSongName());
        content.setText(arrayListbanner.get(position).getContent());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "da click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, PlaylistActivity.class);
                intent.putExtra("banner", arrayListbanner.get(position));
                context.startActivity(intent);
            }

        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
