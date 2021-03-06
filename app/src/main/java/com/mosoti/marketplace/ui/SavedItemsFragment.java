package com.mosoti.marketplace.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mosoti.marketplace.Constants;
import com.mosoti.marketplace.R;
import com.mosoti.marketplace.adapters.FirebaseItemViewHolder;
import com.mosoti.marketplace.adapters.FirebaseListAdapter;
import com.mosoti.marketplace.models.Item;
import com.mosoti.marketplace.util.OnStartDragListener;
import com.mosoti.marketplace.util.SimpleItemTouchHelperCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedItemsFragment extends Fragment implements View.OnClickListener,OnStartDragListener{
    private DatabaseReference mRestaurantReference;
    private FirebaseListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.sign_in) TextView mTextView;

    private FirebaseAuth mAuth;


    public SavedItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_saved_items, container, false);

        ButterKnife.bind(this,view);

        mAuth = FirebaseAuth.getInstance();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (mAuth.getCurrentUser()==null){

            mTextView.setText("This feature is only available to logged in members" +"\n" +"Log in");
            mTextView.setOnClickListener(this);
        }else{
            String uid = user.getUid();
            mTextView.setVisibility(View.INVISIBLE);
            mRestaurantReference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_ITEMS)
                    .child(uid);
            setUpFirebaseAdapter();

        }

        return view;
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mRestaurantReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_ITEMS)
                .child(uid);

        mFirebaseAdapter = new FirebaseListAdapter(
                Item.class, R.layout.item_saved_drag, FirebaseItemViewHolder.class,
                        mRestaurantReference,this,this);

        mRecyclerView.setHasFixedSize(false);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
    @Override
    public void onClick(View v){
        if (v ==mTextView){
            Intent intent= new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

}
