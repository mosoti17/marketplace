package com.mosoti.marketplace.models;

import org.parceler.Parcel;

/**
 * Created by mosoti on 9/15/17.
 */
@Parcel
public class Item {
    private Integer itemid;
    private double price;
    private String name;
    private String image;

    private String stock;
    private String availability;
    private String url;
    private String pushId;

    public Item(){}


    public Item(Integer itemid,double price, String name,String image,String stock,String availability,String url){

        this.itemid=itemid;
        this.price=price;
        this.name=name;
        this.image=image;
        this.stock=stock;
        this.availability=availability;
        this.url=url;

    }

    public Integer getItemId(){
        return itemid;
    }
    public double getPrice(){
        return price;
    }
    public String getImage(){
        return image;
    }
    public String getStock(){
        return stock;
    }
    public String getAvailability(){
        return availability;
    }
    public String getName(){
        return name;
    }
    public String getUrl(){
        return url;
    }
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
