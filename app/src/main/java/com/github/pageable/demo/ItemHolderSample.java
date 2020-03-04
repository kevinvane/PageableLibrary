package com.github.pageable.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ItemHolderSample extends  RecyclerView.ViewHolder{

    public TextView title;
    public TextView des;
    public ItemHolderSample(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        des = itemView.findViewById(R.id.des);
    }
    public static RecyclerView.ViewHolder getViewHolder(Context context, ViewGroup parent){

        return new ItemHolderSample(LayoutInflater.from(
                context).inflate(R.layout.item_sample, parent,
                false));
    }
    public static RecyclerView.ViewHolder getHeadHolder(Context context,ViewGroup parent){

        return new ItemHolderSample(LayoutInflater.from(
                context).inflate(R.layout.item_sample_header, parent,
                false));
    }
}
