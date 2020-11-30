package com.github.pageable.adapter;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

public interface HeaderBaseAdapterInterface<T> extends BaseAdapterInterface<T>{

    RecyclerView.ViewHolder onCreateViewHolderHeader(@NonNull ViewGroup parent);
    RecyclerView.ViewHolder onCreateViewHolderItem(@NonNull ViewGroup parent);
    //RecyclerView.ViewHolder onCreateViewHolderFooter(@NonNull ViewGroup parent);
    void onBindViewHolderHeader(@NonNull RecyclerView.ViewHolder holder);
    //void onBindViewHolderFooter(@NonNull RecyclerView.ViewHolder holder);
    void onBindViewHolderItem(@NonNull RecyclerView.ViewHolder holder, int position, T item);
    void onBindViewHolderItemClick(int position, T item);
}
