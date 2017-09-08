package com.mosoti.marketplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button mItemButton;
    private EditText mItemText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mItemText = (EditText) findViewById(R.id.serchedItemText);
        mItemButton= (Button) findViewById(R.id.findItemButton);
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

