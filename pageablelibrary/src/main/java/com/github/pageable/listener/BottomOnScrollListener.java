package com.github.pageable.listener;

import android.os.Handler;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;



public abstract class BottomOnScrollListener extends RecyclerOnScrollListener {


    public final String TAG = BottomOnScrollListener.class.getSimpleName();
    private Handler handler = new Handler();
    private Runnable runnable;


    // The minimum amount of items to have below your current scroll position before loading more.
    private int visibleThreshold = 1;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLinearLayoutManager;

    public abstract void onBottomLoadMore(int pagination, int pageSize);


    public BottomOnScrollListener(LinearLayoutManager linearLayoutManager, int pagination, int pageSize) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.pagination = pagination;
        this.pageSize = pageSize;
    }
    public BottomOnScrollListener(LinearLayoutManager linearLayoutManager, int pageSize) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.pagination = 1;
        this.pageSize = pageSize;
    }
    public BottomOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (!isLoading()) {
            visibleItemCount = recyclerView.getChildCount();
            totalItemCount = mLinearLayoutManager.getItemCount();
            firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

            //totalItemCount > visibleItemCount load more
            if (loadMoreEnable
                    && !loading
                    && totalItemCount > visibleItemCount
                    && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                // End has been reached
                trigger();
            }
        }
    }
    public void trigger(){
        loading = true;
        pagination++;
        if(isHasMore()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    onBottomLoadMore(pagination, pageSize);
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }

    public void releaseHandler(){
        if(handler!=null){
            handler.removeCallbacksAndMessages(null);
        }
    }
    @Override
    public boolean checkCanDoRefresh() {
        if(mLinearLayoutManager.getItemCount() == 0) return true;
        int firstVisiblePosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        if(firstVisiblePosition == 0) {
            View firstVisibleView = mLinearLayoutManager.findViewByPosition(firstVisiblePosition);
            int top = firstVisibleView.getTop();
            return top >= 0;
        } else {
            return false;
        }

    }
}
