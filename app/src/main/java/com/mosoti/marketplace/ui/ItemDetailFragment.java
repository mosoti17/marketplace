package com.mosoti.marketplace.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
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
import com.mosoti.marketplace.models.Item;
import com.mosoti.marketplace.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.imageView2) ImageView mImageView;
    @BindView(R.id.nameView) TextView mInameView;
    @BindView(R.id.priceView) TextView mPriceView;
    @BindView(R.id.availabilityView) TextView mAvailabilityView;
    @BindView(R.id.stockView) TextView mStockView;
    @BindView(R.id.urlView) TextView mUrlView;
    @BindView(R.id.save) Button mSaveButton;

    private Item mItem;


    private FirebaseAuth mAuth;

    public static ItemDetailFragment newInstance(Item item) {
        // Required empty public constructor
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("item", Parcels.wrap(item));
        itemDetailFragment.setArguments(args);
        return itemDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItem = Parcels.unwrap(getArguments().getParcelable("item"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        ButterKnife.bind(this, view);
        mAuth = FirebaseAuth.getInstance();

        Picasso.with(view.getContext()).load(mItem.getImage()).into(mImageView);
        mInameView.setText(mItem.getName());
        mPriceView.setText(String.valueOf(mItem.getPrice()));
        mAvailabilityView.setText(mItem.getAvailability());
        mStockView.setText(mItem.getStock());
        mUrlView.setText("Go To Website");

            mUrlView.setOnClickListener(this);
            mSaveButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v== mUrlView){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mItem.getUrl()));
            startActivity(webIntent);

        }
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
                mItem.setPushId(pushId);
                pushRef.setValue(mItem);

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

