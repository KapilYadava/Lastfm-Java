package com.example.lastfm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private String keyword;
    private int type;
    private RecyclerView listView;

    //String URL = "http://ws.audioscrobbler.com/2.0/?method=album.search&album=criminal&api_key=c3b522819f98239ec82a72a11d397899&format=json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        Intent intent = getIntent();
        keyword = intent.getStringExtra("KEYWORD");
        type = intent.getIntExtra("TYPE", 0);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        Endpoint service = RetrofitClientInstance.getRetrofitInstance().create(Endpoint.class);
        Call<String> call = null;
        if (type == R.id.byAlbum)
            call = service.getAlbums
                    ("album.search", keyword, getResources().getString(R.string.last_fm_api_key), "json");
        else if (type == R.id.byArtist)
            call = service.getArtists
                    ("artist.search", keyword, getResources().getString(R.string.last_fm_api_key), "json");
        else
            call = service.getTracks
                    ("track.search", keyword, getResources().getString(R.string.last_fm_api_key), "json");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void generateDataList(String response) {
        String cleanedResponse = response.replace("#text", "text");
        Result result = new Gson().fromJson(cleanedResponse, Result.class);
        recyclerView = findViewById(R.id.listView);
        recyclerView.setHasFixedSize(true);
        List<CommonResult> common = null;
        if (type == R.id.byAlbum)
            common = result.getResults().getAlbumMatches().getAlbum();
        else if (type == R.id.byArtist)
            common = result.getResults().getArtistmatches().getArtist();
        else
            common = result.getResults().getTrackmatches().getTrack();

        adapter = new CustomAdapter(this, common, listView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
