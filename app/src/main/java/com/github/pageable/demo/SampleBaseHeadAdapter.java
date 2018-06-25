package com.github.pageable.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.pageable.adapter.BaseAdapter;
import com.github.pageable.adapter.HeaderBaseAdapter;

public class SampleBaseHeadAdapter extends HeaderBaseAdapter<Sample> {


    public SampleBaseHeadAdapter(Context context) {
        super(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolderHeader(@NonNull ViewGroup parent) {
        return SampleItemHolder.getHeadHolder(context,parent);
    }
    @Override
    public void onBindViewHolderHeader(@NonNull RecyclerView.ViewHolder holder) {

        SampleItemHolder sampleItemHolder = (SampleItemHolder)holder;
        sampleItemHolder.title.setText("这是头");
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolderItem(@NonNull ViewGroup parent) {
        return SampleItemHolder.getViewHolder(context,parent);
    }

    @Override
    public void onBindViewHolderItem(@NonNull RecyclerView.ViewHolder holder, int position, Sample item) {
        SampleItemHolder sampleItemHolder = (SampleItemHolder)holder;
        sampleItemHolder.title.setText(getItemData(position).getTitle());
        sampleItemHolder.des.setText(getItemData(position).getDes());
    }

    @Override
    public void onBindViewHolderItemClick(int position, Sample item) {

    }
}
