package com.github.pageable.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.pageable.adapter.BaseAdapter;
import com.github.pageable.adapter.PageBaseAdapter;

public class SampleBaseAdapter extends BaseAdapter<Sample> {

    public SampleBaseAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return SampleItemHolder.getViewHolder(context,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SampleItemHolder sampleItemHolder = (SampleItemHolder)holder;
        sampleItemHolder.title.setText(getItemData(position).getTitle());
        sampleItemHolder.des.setText(getItemData(position).getDes());
    }
}
