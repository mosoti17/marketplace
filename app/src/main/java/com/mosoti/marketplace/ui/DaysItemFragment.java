package com.mosoti.marketplace.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mosoti.marketplace.R;
import com.mosoti.marketplace.adapters.itemListAdapter;
import com.mosoti.marketplace.services.VODservice;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public  class DaysItemFragment extends Fragment {
    public static final String TAG = DaysItemFragment.class.getSimpleName();


    public DaysItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getVOD();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daysitem, container, false);


    }

    public void getVOD(){
        final VODservice vodservice= new VODservice();
        vodservice.findVOD( new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
          @Override
           public void onResponse(Call call, Response response) throws IOException{
              try {
                  String jsonData = response.body().string();
                  Log.v(TAG, jsonData);
              } catch (IOException e) {
                  e.printStackTrace();
              }

//              mItems=storeService.processResults(response);
//                ItemsActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        mAdapter = new itemListAdapter(getApplicationContext(), mItems);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager =
//                                new LinearLayoutManager(ItemsActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//
//                        for(Item item:mItems){
//                            Log.v("name",item.getName());
//                            Log.v("image",item.getImage());
//                            Log.v("available",item.getAvailability());
//                            Log.v("price",String.valueOf(item.getPrice()));
//                            Log.v("id",String.valueOf(item.getItemId()));
//                            Log.v("stock",item.getStock());
//                            Log.v("url",item.getUrl());
//                        }
//
//                    }
            //   });
            }

        });
    }

}
