package com.mosoti.marketplace;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.imageView2)
    ImageView mImageView;
    @Bind(R.id.nameView)
    TextView mInameView;
    @Bind(R.id.priceView)
    TextView mPriceView;
    @Bind(R.id.availabilityView)
    TextView mAvailabilityView;
    @Bind(R.id.stockView)
    TextView mStockView;
    @Bind(R.id.urlView)
    TextView mUrlView;

    private Item mItem;

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

        Picasso.with(view.getContext()).load(mItem.getImage()).into(mImageView);
        mInameView.setText(mItem.getName());
        mPriceView.setText(String.valueOf(mItem.getPrice()));
        mAvailabilityView.setText(mItem.getAvailability());
        mStockView.setText(mItem.getStock());
        mUrlView.setText("Go To Website");

            mUrlView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(mItem.getUrl()));
        startActivity(webIntent);
    }

}

