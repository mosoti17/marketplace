package com.mosoti.marketplace;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mosoti on 9/15/17.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.itemViewHolder> {
    private ArrayList<Item> mItems = new ArrayList<>();
    private Context mContext;

    public ItemListAdapter(Context context,ArrayList<Item> items){
        mContext=context;
        mItems=items;

    }
    public class itemViewHolder extends  RecyclerView.ViewHolder{
            @Bind(R.id.imageView) ImageView itemImageView;
            @Bind(R.id.nameTextView) TextView nameTextView;
        @Bind(R.id.priceTextView) TextView priceTextView;

        private Context mContext;

        public itemViewHolder(View itemView){
            super(itemView);

            ButterKnife.bind(this, itemView);
            mContext=itemView.getContext();

        }
        public void bindItem(Item item){

        }
    }
}
