package com.mosoti.marketplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class ItemsActivity extends AppCompatActivity {
    @Bind(R.id.itemTextView) TextView mItemView;




    public ArrayList<Item> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String item =intent.getStringExtra("item");
        getItems(item);

        mItemView.setText("Here are all the items that match: " +item);

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

                        for(Item item:mItems){
                           Log.v("name",item.getName());
                            Log.v("image",item.getImage());
                            Log.v("available",item.getAvailability());
                            Log.v("price",String.valueOf(item.getPrice()));
                            Log.v("id",String.valueOf(item.getItemId()));
                            Log.v("stock",item.getStock());
                        }

                    }
                });
            }

        });
    }
}
