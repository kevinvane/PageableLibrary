package com.github.pageable.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class EmptyBaseAdapter<T> extends RecyclerView.Adapter implements BaseAdapterInterface<T>{
    public final String TAG = getClass().getName();
    public Context context;
    private List<T> lists;

    public EmptyBaseAdapter(Context context) {
        this.context = context;
        this.lists = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public void clearList() {
        lists.clear();
    }

    @Override
    public void updateList(List<T> data) {

        lists.clear();
        lists.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void removeItem(int position) {
        if(position >=0 && position < getItemCount()){
            lists.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public void addItem(T item) {

        if(!lists.contains(item)){
            lists.add(item);
            notifyItemInserted(getItemCount() -1);
        }
    }

    @Override
    public void updateItem(int position,T item) {

        if(position >=0 && position < getItemCount()){
            lists.set(position,item);
            notifyItemChanged(position);
        }
    }

    @Override
    public int findItem(T item){

        return lists.indexOf(item);
    }

    public List<T> getLists() {
        return lists;
    }
    @Override
    public T getItemData(int position) {
        return lists.get(position);
    }

    @Override
    public int getEmptyItemSize() {
        return 0;
    }

    public void startAct(Bundle bundle, Class clazz){

        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
