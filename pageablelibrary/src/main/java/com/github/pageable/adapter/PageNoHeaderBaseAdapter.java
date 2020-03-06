package com.github.pageable.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.pageable.adapter.holder.EmptyHolder;


/**
 * 不带有Header的分页，Footder已经有默认的样式，
 * 如果要修改，重写onCreateViewHolderFooder()和 onBindViewHolderFooter()方法
 * @param <T>
 */
public abstract class PageNoHeaderBaseAdapter<T> extends PageHeaderBaseAdapter<T>{

    public PageNoHeaderBaseAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderHeader(@NonNull ViewGroup viewGroup) {
        return EmptyHolder.getHolder(viewGroup);
    }


    @Override
    public void onBindViewHolderHeader(@NonNull RecyclerView.ViewHolder viewHolder) {

        //不用绑定
    }


}
