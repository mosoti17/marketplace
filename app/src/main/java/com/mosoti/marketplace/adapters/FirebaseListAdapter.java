package com.mosoti.marketplace.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import com.mosoti.marketplace.models.Item;

import com.mosoti.marketplace.ui.ItemDetailActivity;
import com.mosoti.marketplace.ui.SavedItemsFragment;
import com.mosoti.marketplace.util.ItemTouchHelperAdapter;
import com.mosoti.marketplace.util.OnStartDragListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by mosoti on 10/2/17.
 */

public class FirebaseListAdapter extends FirebaseRecyclerAdapter<Item,FirebaseItemViewHolder> implements ItemTouchHelperAdapter {

   private Context mContext;

    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private ChildEventListener mChildEventListener;
    private ArrayList<Item> mItems = new ArrayList<>();


//    public FirebaseListAdapter(Context context, Class<Item> modelClass, int modelLayout,
//                               Class<FirebaseItemViewHolder> viewHolderClass,
//                               Query ref, OnStartDragListener onStartDragListener){
//        super(modelClass, modelLayout, viewHolderClass, ref);
//        mContext=context;
//        mRef = ref.getRef();
//        mOnStartDragListener = onStartDragListener;
//        mContext = context;
//
//
//    }

    public FirebaseListAdapter(Class<Item> itemClass, int item_saved_drag,
                               Class<FirebaseItemViewHolder> firebaseItemViewHolderClass,
                               DatabaseReference mRestaurantReference,
                               SavedItemsFragment savedItemsFragment, SavedItemsFragment onStartDragListener) {
       super(itemClass,item_saved_drag,firebaseItemViewHolderClass,mRestaurantReference);
       mOnStartDragListener=onStartDragListener;
       mRef= mRestaurantReference.getRef();
       mContext=savedItemsFragment.getContext();


        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mItems.add(dataSnapshot.getValue(Item.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void populateViewHolder(final FirebaseItemViewHolder viewHolder, Item model, int position) {
        viewHolder.bindItem(model);
        viewHolder.drag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ItemDetailActivity.class);
               intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("items", Parcels.wrap(mItems));
                intent.putExtra("source","saved");

              mContext.startActivity(intent);

            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        getRef(position).removeValue();

    }

    private void setIndexInFirebase() {
        for (Item restaurant : mItems) {
            int index = mItems.indexOf(restaurant);
            DatabaseReference ref = getRef(index);
            restaurant.setIndex(Integer.toString(index));
            ref.setValue(restaurant);
        }
    }
    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }

}

