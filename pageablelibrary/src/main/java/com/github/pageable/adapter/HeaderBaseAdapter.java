package com.github.pageable.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class HeaderBaseAdapter<T> extends BaseAdapter<T> implements HeaderBaseAdapterInterface<T>{

    public final static int VIEW_TYPE_HEADER = 0;
    public final static int VIEW_TYPE_FOODER = 1;
    public final static int VIEW_TYPE_ITEM = 2;


    public HeaderBaseAdapter(Context context) {
        super(context);
        getLists().add(null);
    }

    @Override
    public void clearList() {
        getLists().clear();
        getLists().add(null);
    }
    @Override
    public void updateList(List<T> data) {

        getLists().clear();
        getLists().add(null);
        getLists().addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        switch (viewType){
            case VIEW_TYPE_HEADER:
                holder = onCreateViewHolderHeader(parent);
                break;
            default:
            case VIEW_TYPE_ITEM:
                holder = onCreateViewHolderItem(parent);
                break;
            case VIEW_TYPE_FOODER:
                holder = onCreateViewHolderFooder(parent);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)){
            case VIEW_TYPE_HEADER:
                onBindViewHolderHeader(holder);
                break;
            case VIEW_TYPE_ITEM:
                final T item = getItemData(position);
                onBindViewHolderItem(holder,position,item);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBindViewHolderItemClick(position,item);
                    }
                });
                break;
            case VIEW_TYPE_FOODER:
                onBindViewHolderFooder(holder);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {

        if(position == 0){
            return VIEW_TYPE_HEADER;
        }
        return VIEW_TYPE_ITEM;
    }
    @Override
    public int getEmptyItemSize(){
        return 1;
    }

    public RecyclerView.ViewHolder onCreateViewHolderFooder(@NonNull ViewGroup parent){
        //等待被分页子类重写
        return null;
    }
    public void onBindViewHolderFooder(@NonNull RecyclerView.ViewHolder holder){
        //等待被分页子类重写
    }

}
