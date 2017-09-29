package com.mosoti.marketplace.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mosoti.marketplace.Constants;
import com.mosoti.marketplace.R;
import com.mosoti.marketplace.adapters.ItemsPageAdapter;
import com.mosoti.marketplace.models.Item;
import com.mosoti.marketplace.services.VODservice;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaysItemFragment extends Fragment implements View.OnClickListener{

    private ItemsPageAdapter dayItem;


    public static final String TAG = DaysItemFragment.class.getSimpleName();
    public Item item;

    @BindView(R.id.imageView2) ImageView mImageView;
    @BindView(R.id.nameView) TextView mInameView;
    @BindView(R.id.priceView) TextView mPriceView;
    @BindView(R.id.availabilityView) TextView mAvailabilityView;
    @BindView(R.id.stockView) TextView mStockView;
    @BindView(R.id.urlView) TextView mUrlView;
    @BindView(R.id.save)Button mSaveButton;

    private FirebaseAuth mAuth;


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
        mAuth = FirebaseAuth.getInstance();
        getVOD();


        mUrlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WebviewActivity.class);
                intent.putExtra("url",item.getUrl());
                startActivity(intent);

            }
        });
        mSaveButton.setOnClickListener(this);




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
                        mPriceView.setText("Price $"+String.valueOf(item.getPrice()));
                        mAvailabilityView.setText("Availability: "+item.getAvailability());
                        mStockView.setText("Stock: "+item.getStock());
                        mUrlView.setText("Add To Cart");

//                            Log.v("name", item.getName());
//                            Log.v("image", item.getImage());
//                            Log.v("available", item.getAvailability());
//                            Log.v("price", String.valueOf(item.getPrice()));
//                            Log.v("id", String.valueOf(item.getItemId()));
//                            Log.v("stock", item.getStock());
//                            Log.v("url", item.getUrl());


                    }
                });
            }

        });
    }

    @Override
    public void onClick(View v) {

        if (v == mSaveButton){
            if (mAuth.getCurrentUser()!=null){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                DatabaseReference restaurantRef = FirebaseDatabase
                        .getInstance()
                        .getReference(Constants.FIREBASE_CHILD_ITEMS)
                        .child(uid);

                DatabaseReference pushRef = restaurantRef.push();

                String pushId = pushRef.getKey();
                item.setPushId(pushId);
                pushRef.setValue(item);

                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

            }else {
                Snackbar.make(v, "Sign in to Save Item", Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.text_undo), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent= new Intent(getActivity(),LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .show();


            }


        }


    }


}
