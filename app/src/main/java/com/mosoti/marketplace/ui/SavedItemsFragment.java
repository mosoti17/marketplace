package com.mosoti.marketplace.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mosoti.marketplace.Constants;
import com.mosoti.marketplace.R;
import com.mosoti.marketplace.adapters.FirebaseItemViewHolder;
import com.mosoti.marketplace.models.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedItemsFragment extends Fragment {
    private DatabaseReference mRestaurantReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

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

            Log.v("login","login");
        }else{
            String uid = user.getUid();
            mRestaurantReference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_ITEMS)
                    .child(uid);
            setUpFirebaseAdapter();

        }

        return view;
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Item, FirebaseItemViewHolder>
                (Item.class, R.layout.item_list2, FirebaseItemViewHolder.class,
                        mRestaurantReference) {

            @Override
            protected void populateViewHolder(FirebaseItemViewHolder viewHolder,
                                              Item model, int position) {
                //Log.v("name",model.getName());
                viewHolder.bindItem(model);

            }
        };
        mRecyclerView.setHasFixedSize(false);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
//    }

}
