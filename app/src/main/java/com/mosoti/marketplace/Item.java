package com.mosoti.marketplace;

import org.parceler.Parcel;

/**
 * Created by mosoti on 9/15/17.
 */
@Parcel
public class Item {
    public Integer mItemId;
    public double mPrice;
    public String mName;
    public String mImage;

    public String mStock;
    public String mAvailability;
    public String mUrl;

    public Item(){}


    public Item(Integer itemid,double price, String name,String image,String stock,String availability,String url){

        this.mItemId=itemid;
        this.mPrice=price;
        this.mName=name;
        this.mImage=image;
        this.mStock=stock;
        this.mAvailability=availability;
        this.mUrl=url;

    }

    public Integer getItemId(){
        return mItemId;
    }
    public double getPrice(){
        return mPrice;
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
    public String getUrl(){
        return mUrl;
    }
}
