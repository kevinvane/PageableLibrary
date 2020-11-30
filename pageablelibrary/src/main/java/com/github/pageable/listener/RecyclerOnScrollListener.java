package com.github.pageable.listener;

import androidx.recyclerview.widget.RecyclerView;



public abstract class RecyclerOnScrollListener extends RecyclerView.OnScrollListener{

    protected boolean loading = false;
    protected boolean loadMoreEnable = true;

    protected int pageSize = 15;
    protected int pagination = 1;

    //给外部调用，标志是否还有数据
    private boolean hasMore = true;

    public abstract boolean checkCanDoRefresh();
    public abstract void onBottomLoadMore(int pagination, int pageSize);


    public void loadComplete() {
        loading = false;
    }

    public synchronized boolean isLoading() {
        return loading;
    }

    public void setLoadMoreEnable(boolean loadMoreEnable) {
        this.loadMoreEnable = loadMoreEnable;
    }

    public int getPagination() {
        return pagination;
    }

    public void setPagination(int pagination) {
        this.pagination = pagination;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}