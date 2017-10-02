package com.mosoti.marketplace.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mosoti.marketplace.Constants;
import com.mosoti.marketplace.R;
import com.mosoti.marketplace.models.Item;
import com.mosoti.marketplace.ui.ItemDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by mosoti on 9/22/17.
 */

public class FirebaseItemViewHolder extends RecyclerView.ViewHolder  {

    View mView;
    Context mContext;
    public ImageView drag;

    public FirebaseItemViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();

    }

    public void bindItem( Item item) {

       // Log.v("name",item.getName());

        ImageView itemImageView =(ImageView) mView.findViewById(R.id.imageView);
        drag=(ImageView)mView.findViewById(R.id.imageView3);

        TextView nameTextView =(TextView) mView.findViewById(R.id.nameTextView);
        TextView priceTextView =(TextView) mView.findViewById(R.id.priceTextView);


        Picasso.with(mContext).load(item.getImage()).into(itemImageView);

        nameTextView.setText(item.getName());
        priceTextView.setText(String.valueOf(item.getPrice()));

    }


}

