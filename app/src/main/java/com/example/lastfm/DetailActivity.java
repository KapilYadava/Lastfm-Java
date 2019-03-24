package com.example.lastfm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView imageView = findViewById(R.id.image);
        TextView textName = findViewById(R.id.name);
        TextView textArtist = findViewById(R.id.artist);
        TextView textURL = findViewById(R.id.url);
        TextView textListener = findViewById(R.id.listeners);
        TextView textStream = findViewById(R.id.streamable);

        Intent intent = getIntent();
        CommonResult item = (CommonResult) intent.getSerializableExtra("ITEM");
        textName.setText(item.getName());
        textArtist.setText(item.getArtist());
        textURL.setText(item.getUrl());
        textListener.setText(item.getListeners());
        textStream.setText(item.getStreamable());

        String imgURL = item.getImage().get(3).getText();

        if (imgURL.length() > 0) {
            Picasso.with(this)
                    .load(imgURL).placeholder(R.color.cardview_dark_background)
                    .error(R.color.cardview_dark_background)
                    .into(imageView);
        }

    }
}
