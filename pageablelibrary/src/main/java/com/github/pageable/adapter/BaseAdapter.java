package com.github.pageable.adapter;

import android.content.Context;

/**
 * BaseAdapter 改名为 EmptyBaseAdapter，暂时保留BaseAdapter类，防止一些项目依赖BaseAdapter
 * 新项目请直接使用 EmptyBaseAdapter
 * @param <T>
 */
@Deprecated
public abstract class BaseAdapter<T>  extends EmptyBaseAdapter<T> {
    public BaseAdapter(Context context) {
        super(context);
    }
}
