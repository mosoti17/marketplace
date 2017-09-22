package com.mosoti.marketplace.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mosoti.marketplace.R;
import com.mosoti.marketplace.adapters.itemListAdapter;
import com.mosoti.marketplace.models.Item;
import com.mosoti.marketplace.services.TrendingService;
import com.mosoti.marketplace.services.VODservice;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingFragment extends Fragment {
    public static final String TAG = TrendingFragment.class.getSimpleName();


    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private itemListAdapter mAdapter;

    public ArrayList<Item> mItems = new ArrayList<>();

    public TrendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_trending, container, false);
        getTrending();
        ButterKnife.bind(this,view);
        return view;
    }

    public void getTrending() {
        final TrendingService trendservice = new TrendingService();
        trendservice.findVOD(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//              try {
//                  String jsonData = response.body().string();
//                  Log.v(TAG, jsonData);
//              } catch (IOException e) {
//                  e.printStackTrace();
//              }

                mItems = TrendingService.processResults(response);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter = new itemListAdapter(getContext(), mItems);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        for (Item item : mItems) {
                            Log.v("name", item.getName());
                            Log.v("image", item.getImage());
                            Log.v("available", item.getAvailability());
                            Log.v("price", String.valueOf(item.getPrice()));
                            Log.v("id", String.valueOf(item.getItemId()));
                            Log.v("stock", item.getStock());
                            Log.v("url", item.getUrl());
                        }

                    }
                });


            }

        });
    }

}
