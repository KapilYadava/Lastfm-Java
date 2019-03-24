package com.example.lastfm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> implements View.OnClickListener {

    private List<CommonResult> dataList;
    private Context context;
    private RecyclerView recyclerView;

    public CustomAdapter(Context context, List<CommonResult> dataList, RecyclerView recyclerView) {
        this.context = context;
        this.dataList = dataList;
        this.recyclerView = recyclerView;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        view.setOnClickListener(this);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getName());
        holder.txtArtist.setText(dataList.get(position).getArtist());
        holder.txtUrl.setText(dataList.get(position).getUrl());
        String imageUrl = dataList.get(position).getImage().get(2).getText();
        if (imageUrl.length() > 0) {
            Picasso.with(context)
                    .load(imageUrl).placeholder(R.color.cardview_dark_background)
                    .error(R.color.cardview_dark_background)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onClick(View view) {
        int itemPosition = recyclerView.getChildLayoutPosition(view);
        String item = dataList.get(itemPosition).getName();
        CommonResult result = dataList.get(itemPosition);
        Toast.makeText(context, item, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("ITEM", result);
        context.startActivity(intent);
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle, txtArtist, txtUrl;
        ImageView imageView;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            imageView = mView.findViewById(R.id.image);
            txtTitle = mView.findViewById(R.id.title);
            txtArtist = mView.findViewById(R.id.artist);
            txtUrl = mView.findViewById(R.id.url);
        }

    }
}