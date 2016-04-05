package com.thangtv.dkmh.services;

import android.content.Context;

import com.thangtv.dkmh.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by uendno on 04/04/2016.
 */
public class DKMHClient {

    private static Retrofit retrofit;

    public static Retrofit getInstance(Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(context.getResources().getString(R.string.api_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
