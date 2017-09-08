package com.mosoti.marketplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.serchedItemText) EditText mItemText;
    @Bind(R.id.findItemButton) Button mItemButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ButterKnife.bind(this);

        mItemButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String item = mItemText.getText().toString();
                Intent intent = new Intent(MainActivity.this, ItemsActivity.class);
                intent.putExtra("item",item);
                startActivity(intent);
            }
        });
    }
    }

