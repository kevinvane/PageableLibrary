package com.github.pageable.adapter;


public interface PageAdapterInterface{

    /**
     * 此方法预先清理数据，在数据加载过程中，如果Recycler滑动，就会闪退
     */
    @Deprecated
    void onRefresh();
}
