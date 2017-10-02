package com.mosoti.marketplace.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mosoti.marketplace.R;
import com.mosoti.marketplace.models.Item;
import com.mosoti.marketplace.ui.ItemDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mosoti on 9/15/17.
 */

public class itemListAdapter extends RecyclerView.Adapter<itemListAdapter.itemViewHolder> {
    private ArrayList<Item> mItems = new ArrayList<>();
    private Context mContext;

    public itemListAdapter(Context context,ArrayList<Item> items){
        mContext=context;
        mItems=items;

    }
    @Override
    public itemListAdapter.itemViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list2, parent, false);
        itemViewHolder viewHolder=new itemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(itemListAdapter.itemViewHolder holder, int position) {
        holder.bindItem(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class itemViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener

    {
            @BindView(R.id.imageView) ImageView itemImageView;
            @BindView(R.id.nameTextView) TextView nameTextView;
        @BindView(R.id.priceTextView) TextView priceTextView;

        private Context mContext;

        public itemViewHolder(View itemView){
            super(itemView);


            ButterKnife.bind(this, itemView);
            mContext=itemView.getContext();

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, ItemDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("items", Parcels.wrap(mItems));
            intent.putExtra("source","search");
            mContext.startActivity(intent);
        }
        public void bindItem(Item item){
            Picasso.with(mContext).load(item.getImage()).into(itemImageView);
            nameTextView.setText(item.getName());
            priceTextView.setText("Price $"+String.valueOf(item.getPrice()));
        }
    }
}
