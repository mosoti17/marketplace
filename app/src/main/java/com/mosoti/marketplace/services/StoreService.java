package com.mosoti.marketplace.services;

import com.mosoti.marketplace.Constants;
import com.mosoti.marketplace.models.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mosoti on 9/15/17.
 */

public class StoreService {
    public static void findItems(String item, Callback callback) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.QUERY, item);
        urlBuilder.addQueryParameter(Constants.API_KEY_BASE,Constants.API_KEY);
        urlBuilder.addQueryParameter(Constants.API_FORMAT,"json");
        String url = urlBuilder.build().toString();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()

                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Item> processResults(Response response) {
        ArrayList<Item> items = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {

                JSONObject itemsJSON= (JSONObject) new JSONObject(jsonData);
                JSONArray listJSON = itemsJSON.getJSONArray("items");

                for (int i = 0; i < listJSON.length(); i++) {
                    JSONObject itemJSON = listJSON.getJSONObject(i);
                        Integer itemid=itemJSON.getInt("itemId");
                        double price =itemJSON.getDouble("salePrice");
                        String name=itemJSON.getString("name");
                        String image=itemJSON.getString("mediumImage");
                        String stock=itemJSON.getString("stock");
                    String url=itemJSON.getString("productUrl");
                        String availability=itemJSON.getString("offerType");
                Item item= new Item(itemid,price,name,image,stock,availability,url);
                    items.add(item);
                }



            }


        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();

        }
        return items;
    }

}
