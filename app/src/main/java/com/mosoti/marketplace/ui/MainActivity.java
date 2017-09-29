package com.mosoti.marketplace.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.mosoti.marketplace.R;
import com.mosoti.marketplace.adapters.MainActivityAdapter;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  {
    @BindView(R.id.dealofday) TabLayout dealofday;
    @BindView(R.id.trending) TabLayout trending;
    @BindView(R.id.saveditems) TabLayout saveditems;



    private MainActivityAdapter mainActivityAdapter;
    @BindView(R.id.container) ViewPager mViewPager;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ButterKnife.bind(this);

        mainActivityAdapter = new MainActivityAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mainActivityAdapter);
        mAuth = FirebaseAuth.getInstance();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.setCurrentItem(1);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);


        MenuItem menuItem = menu.findItem(R.id.action_search);

        if(mAuth.getCurrentUser()==null){
           MenuItem item= menu.findItem(R.id.action_logout);
           item.setVisible(false);

        }else{
            MenuItem item= menu.findItem(R.id.action_login);
            item.setVisible(false);
        }
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {


                Intent intent = new Intent(MainActivity.this, ItemsActivity.class);
                intent.putExtra("item",query);
                startActivity(intent);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.cart){
            Intent intent = new Intent(MainActivity.this, WebviewActivity.class);
            intent.putExtra("url","https://www.walmart.com/cart/");
            startActivity(intent);
        }
        if (id == R.id.action_login) {
            Intent intent= new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);

            return true;
        }
        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    }

