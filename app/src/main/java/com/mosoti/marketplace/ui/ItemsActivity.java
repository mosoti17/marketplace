package com.mosoti.marketplace.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.mosoti.marketplace.models.Item;
import com.mosoti.marketplace.R;
import com.mosoti.marketplace.services.StoreService;
import com.mosoti.marketplace.adapters.itemListAdapter;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class ItemsActivity extends AppCompatActivity {
    @BindView(R.id.itemTextView) TextView mItemView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private itemListAdapter mAdapter;



    public ArrayList<Item> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String item =intent.getStringExtra("item");
        getItems(item);

        mItemView.setText("Search Results For: " +item);

    }

    public void getItems(String item){
        final StoreService storeService= new StoreService();
        storeService.findItems(item, new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException{
//                                String jsonData = response.body().string();
//                                Log.v("data", jsonData);

                mItems=storeService.processResults(response);
                ItemsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter = new itemListAdapter(getApplicationContext(), mItems);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(ItemsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        for(Item item:mItems){
                           Log.v("name",item.getName());
                            Log.v("image",item.getImage());
                            Log.v("available",item.getAvailability());
                            Log.v("price",String.valueOf(item.getPrice()));
                            Log.v("id",String.valueOf(item.getItemId()));
                            Log.v("stock",item.getStock());
                            Log.v("url",item.getUrl());
                        }

                    }
                });
            }

        });
    }
}
