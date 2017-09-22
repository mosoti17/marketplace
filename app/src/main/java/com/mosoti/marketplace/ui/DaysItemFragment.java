package com.mosoti.marketplace.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mosoti.marketplace.R;
import com.mosoti.marketplace.adapters.ItemsPageAdapter;
import com.mosoti.marketplace.adapters.itemListAdapter;
import com.mosoti.marketplace.models.VOD;
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
public class DaysItemFragment extends Fragment {

    private ItemsPageAdapter dayItem;


    public static final String TAG = DaysItemFragment.class.getSimpleName();
    public VOD item;

    @BindView(R.id.imageView2) ImageView mImageView;
    @BindView(R.id.nameView) TextView mInameView;
    @BindView(R.id.priceView) TextView mPriceView;
    @BindView(R.id.availabilityView) TextView mAvailabilityView;
    @BindView(R.id.stockView) TextView mStockView;
    @BindView(R.id.urlView) TextView mUrlView;


    public DaysItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_daysitem, container, false);
        ButterKnife.bind(this, view);
        getVOD();


        //mUrlView.setOnClickListener(this);


        //mUrlView.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;


    }

    public void getVOD() {
        final VODservice vodservice = new VODservice();
        vodservice.findVOD(new Callback() {
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

                item = VODservice.processResults(response);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Picasso.with(getContext()).load(item.getImage()).into(mImageView);
                        mInameView.setText(item.getName());
                        mPriceView.setText(String.valueOf(item.getPrice()));
                        mAvailabilityView.setText(item.getAvailability());
                        mStockView.setText(item.getStock());
                        mUrlView.setText("Go To Website");

                            Log.v("name", item.getName());
                            Log.v("image", item.getImage());
                            Log.v("available", item.getAvailability());
                            Log.v("price", String.valueOf(item.getPrice()));
                            Log.v("id", String.valueOf(item.getItemId()));
                            Log.v("stock", item.getStock());
                            Log.v("url", item.getUrl());


                    }
                });
            }

        });
    }


}
