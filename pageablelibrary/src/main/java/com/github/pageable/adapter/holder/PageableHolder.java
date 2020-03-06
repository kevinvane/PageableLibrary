package com.github.pageable.adapter.holder;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.pageable.R;


public class PageableHolder extends RecyclerView.ViewHolder{

    public ProgressBar progressBar;
    public TextView tv_content;
    private View itemView;

    public PageableHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        progressBar = itemView.findViewById(R.id.progressBar);
        tv_content = itemView.findViewById(R.id.tv_content);
    }

    public void showLoading(){

        itemView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        tv_content.setText(R.string.load_more);
    }
    public void dismissLoading(){

        itemView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        tv_content.setText(R.string.load_more_none);
    }
    public void disMissFood(){
        itemView.setVisibility(View.GONE);
    }
    public static RecyclerView.ViewHolder getViewHolder(Context context, ViewGroup parent){

        return new PageableHolder(LayoutInflater.from(
                context).inflate(R.layout.item_loadmore, parent,
                false));
    }

}
