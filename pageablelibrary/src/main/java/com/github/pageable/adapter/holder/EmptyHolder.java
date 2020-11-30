package com.github.pageable.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pageable.R;


public class EmptyHolder extends RecyclerView.ViewHolder{
    public EmptyHolder(View itemView) {
        super(itemView);
    }

    public static RecyclerView.ViewHolder getHolder(ViewGroup parent) {

        //item_wallet_head
        return new EmptyHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_head_emtpy, parent,
                false));
    }
}
