package com.github.pageable.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import com.github.pageable.adapter.PageBaseAdapter;

public class SampleAdapter extends PageBaseAdapter<Sample>{

    public SampleAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderHeader(@NonNull ViewGroup parent) {
        return SampleItemHolder.getHeadHolder(context,parent);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderItem(@NonNull ViewGroup parent) {
        return SampleItemHolder.getViewHolder(context,parent);
    }

    @Override
    public void onBindViewHolderHeader(@NonNull RecyclerView.ViewHolder holder) {
        SampleItemHolder sampleItemHolder = (SampleItemHolder)holder;
        sampleItemHolder.title.setText("头部");
    }

    @Override
    public void onBindViewHolderItem(@NonNull RecyclerView.ViewHolder holder, int position, Sample item) {

        SampleItemHolder sampleItemHolder = (SampleItemHolder)holder;
        sampleItemHolder.title.setText(item.getTitle());
        sampleItemHolder.des.setText("position="+position);
    }


}
