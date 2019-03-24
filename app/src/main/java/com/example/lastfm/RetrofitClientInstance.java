package com.example.lastfm;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClientInstance {

    private static final String BASE_URL = "http://ws.audioscrobbler.com";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}