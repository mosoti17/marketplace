package com.mosoti.marketplace.services;

import com.mosoti.marketplace.Constants;
import com.mosoti.marketplace.models.Item;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mosoti on 9/21/17.
 */

public class VODservice {
    private static Item item;

    public static void findVOD( Callback callback) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api.walmartlabs.com/v1/vod?").newBuilder();

        urlBuilder.addQueryParameter(Constants.API_KEY_BASE,Constants.API_KEY);
        urlBuilder.addQueryParameter("format","json");
        String url = urlBuilder.build().toString();


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()

                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public static Item processResults(Response response) {


        try {
            String jsonData = response.body().string();

            if (response.isSuccessful()) {

                JSONObject itemJSON=  new JSONObject(jsonData);

                    Integer itemid=itemJSON.getInt("itemId");
                    double price =itemJSON.getDouble("salePrice");
                    String name=itemJSON.getString("name");
                    String image=itemJSON.getString("mediumImage");
                    String stock=itemJSON.getString("stock");
                String url;
                if (itemJSON.has("productUrl")) {


                    url=itemJSON.getString("addToCartUrl");

                }else{
                    url=itemJSON.getString("productUrl");
                }
                    String availability=itemJSON.getString("offerType");
                     item= new Item(itemid,price,name,image,stock,availability,url);





            }


        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();

        }
        return item;
    }
}
