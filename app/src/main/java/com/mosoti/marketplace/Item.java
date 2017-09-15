package com.mosoti.marketplace;

/**
 * Created by mosoti on 9/15/17.
 */

public class Item {
    public Integer mItemId;
    public Integer mPrice;
    public String mName;
    public String mImage;

    public String mStock;
    public String mAvailability;


    public Item(Integer itemid,Integer price, String name,String image,String stock,String availability){

        this.mItemId=itemid;
        this.mPrice=price;
        this.mName=name;
        this.mImage=image;
        this.mStock=stock;
        this.mAvailability=availability;

    }

    public Integer getItemId(){
        return mItemId;
    }
    public Integer getPrice(){
        return mItemId;
    }
    public String getImage(){
        return mImage;
    }
    public String getStock(){
        return mStock;
    }
    public String getAvailability(){
        return mAvailability;
    }
    public String getName(){
        return mName;
    }
}
