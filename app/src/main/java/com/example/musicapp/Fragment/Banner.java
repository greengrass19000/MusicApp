package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.musicapp.Adapter.BannerAdapter;
import com.example.musicapp.Data.Introduction;
import com.example.musicapp.R;
import com.example.musicapp.Services.APIService;
import com.example.musicapp.Services.Dataservice;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Banner extends Fragment {

    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int curItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        mapping();
        GetData();
        return view;
    }

    private void mapping() {
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }
    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Introduction>> callback = dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<Introduction>>() {
            @Override
            public void onResponse(Call<List<Introduction>> call, Response<List<Introduction>> response) {
                ArrayList<Introduction> banners = (ArrayList<Introduction>) response.body();
                Log.d("BBB", banners.get(0).getImage());
                bannerAdapter = new BannerAdapter(getActivity(), banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        curItem = viewPager.getCurrentItem();
                        curItem++;
                        if (curItem >= viewPager.getAdapter().getCount()) {
                            curItem = 0;
                        }
                        viewPager.setCurrentItem(curItem, true);
                        handler.postDelayed(runnable, 4500);
                    }
                };
                handler.postDelayed(runnable, 4500);
            }

            @Override
            public void onFailure(Call<List<Introduction>> call, Throwable t) {

            }
        });
    }
}
