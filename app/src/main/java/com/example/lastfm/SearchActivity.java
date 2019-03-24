package com.example.lastfm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private EditText keyword;
    private RadioGroup radioGroup;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        keyword = findViewById(R.id.keyword);
        radioGroup = findViewById(R.id.radio);
        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        if (keyword.getText().toString().length() == 0) {
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("KEYWORD", keyword.getText().toString());
        intent.putExtra("TYPE", type); // type 0 for search by Album/song, type 1 for search by artist.
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        type = i;
        keyword.setHint(R.string.enter_keyword);
    }
}
