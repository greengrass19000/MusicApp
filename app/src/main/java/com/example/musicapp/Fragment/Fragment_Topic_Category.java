package com.example.musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.musicapp.Activity.TopicActivity;
import com.example.musicapp.Activity.SonglistActivity;
import com.example.musicapp.Model.Category;
import com.example.musicapp.Model.Topic;
import com.example.musicapp.Model.TopicCategoryToday;
import com.example.musicapp.R;
import com.example.musicapp.Services.APIService;
import com.example.musicapp.Services.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Topic_Category extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtViewMore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_topic_category, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalscrollview);
        txtViewMore = view.findViewById(R.id.textviewviewmoretopiccategory);
        txtViewMore.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), TopicActivity.class);
            startActivity(intent);
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<TopicCategoryToday> callback = dataservice.GetTopicCategoryToday();
        callback.enqueue(new Callback<TopicCategoryToday>() {
            @Override
            public void onResponse(Call<TopicCategoryToday> call, Response<TopicCategoryToday> response) {
                TopicCategoryToday topicCategoryToday = response.body();

                ArrayList<Topic> topicList = new ArrayList<>();
                topicList.addAll(topicCategoryToday.getTopic());

                 ArrayList<Category> categoryList = new ArrayList<>();
                categoryList.addAll(topicCategoryToday.getCategory());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580, 580);
                layout.setMargins(10, 20, 10, 30);
                for (int i = 0; i < topicList.size(); ++i) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (topicList.get(i).getImage() != null) {
                        Picasso.get().load(topicList.get(i).getImage()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }
                for (int i = 0; i < categoryList.size(); ++i) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (categoryList.get(i).getImage() != null) {
                        Picasso.get().load(categoryList.get(i).getImage()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalI = i;
                    imageView.setOnClickListener(view -> {
                        Intent intent = new Intent(getActivity(), SonglistActivity.class);
                        intent.putExtra("categoryId", categoryList.get(finalI));
                        startActivity(intent);
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<TopicCategoryToday> call, Throwable t) {

            }
        });
    }


}
