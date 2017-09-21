package com.mosoti.marketplace.services;

import com.mosoti.marketplace.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by mosoti on 9/21/17.
 */

public class VODservice {

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
}
