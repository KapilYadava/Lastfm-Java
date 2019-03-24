package com.example.lastfm;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Endpoint {

    @GET("/2.0")
    Call<String> getAlbums(@Query("method") String method,
                           @Query("album") String album,
                           @Query("api_key") String api_key,
                           @Query("format") String format);

    @GET("/2.0")
    Call<String> getArtists(@Query("method") String method,
                            @Query("artist") String artist,
                            @Query("api_key") String api_key,
                            @Query("format") String format);

    @GET("/2.0")
    Call<String> getTracks(@Query("method") String method,
                           @Query("track") String artist,
                           @Query("api_key") String api_key,
                           @Query("format") String format);
}