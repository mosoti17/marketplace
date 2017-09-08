package com.mosoti.marketplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ItemsActivity extends AppCompatActivity {
    private TextView mItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        mItemView =(TextView) findViewById(R.id.itemTextView);
        Intent intent = getIntent();
        String item =intent.getStringExtra("item");
        mItemView.setText("Here are all the items that match: " +item);
    }
}
