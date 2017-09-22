package com.mosoti.marketplace.models;

import org.parceler.Parcel;

/**
 * Created by mosoti on 9/15/17.
 */
@Parcel
public class Item {
    private Integer ItemId;
    private double Price;
    private String Name;
    private String Image;

    private String Stock;
    private String Availability;
    private String Url;
    private String pushId;

    public Item(){}


    public Item(Integer itemid,double price, String name,String image,String stock,String availability,String url){

        this.ItemId=itemid;
        this.Price=price;
        this.Name=name;
        this.Image=image;
        this.Stock=stock;
        this.Availability=availability;
        this.Url=url;

    }

    public Integer getItemId(){
        return ItemId;
    }
    public double getPrice(){
        return Price;
    }
    public String getImage(){
        return Image;
    }
    public String getStock(){
        return Stock;
    }
    public String getAvailability(){
        return Availability;
    }
    public String getName(){
        return Name;
    }
    public String getUrl(){
        return Url;
    }
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
