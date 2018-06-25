package com.github.pageable.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public interface PageAdapterInterface<T>{

    void onRefresh();
    void updateList(List<T> data);
    T getItemData(int position);
    int getEmptyItemSize();
    RecyclerView.ViewHolder onCreateViewHolderHeader(@NonNull ViewGroup parent);
    RecyclerView.ViewHolder onCreateViewHolderItem(@NonNull ViewGroup parent);
    void onBindViewHolderHeader(@NonNull RecyclerView.ViewHolder holder);
    void onBindViewHolderItem(@NonNull RecyclerView.ViewHolder holder, int position,T item);
    void onBindViewHolderItemClick(int position,T item);
}
