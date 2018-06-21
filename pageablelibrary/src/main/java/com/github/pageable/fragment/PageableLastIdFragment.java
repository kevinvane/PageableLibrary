package com.github.pageable.fragment;

import android.util.Log;
import android.view.View;

import com.github.pageable.adapter.PageBaseAdapter;
import com.github.pageable.listener.BottomOnScrollListener;


/**
 * 适合以最后一个id请求下一页数据的API，本类没调试。
 */
public abstract class PageableLastIdFragment extends RecyclerFragment{

    private BottomOnScrollListener bottomListener;

    private final int AFTER_INIT = 0;
    private int lastId = 0;

    //不暴露after
    private int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        setBottomListener();
    }
    @Override
    public void onRefresh() {

        getAdapterInstance().onRefresh();
        resetLoadMore();
        requestNetData();
    }
    @Override
    protected void requestNetData() {
        onLoadNextPageForAfter(AFTER_INIT);
    }

    public abstract void onLoadNextPageForAfter(int after);

    public void resetLoadMore(){

        setHasMore(true);
        bottomListener.setPagination(0);
    }
    public void loadComplete(){

        if(bottomListener!=null)bottomListener.loadComplete();
    }
    public void setHasMore(boolean more){

        if(bottomListener!=null)bottomListener.setHasMore(more);
        loadComplete();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(bottomListener!=null)bottomListener.releaseHandler();
    }
    public void setBottomListener(){

        bottomListener = new BottomOnScrollListener(getLinearLayoutManager(),0,10) {
            @Override
            public void onBottomLoadMore(final int pagination, int pageSize) {
                Log.i(TAG, "onBottomLoadMore: "+pagination);
                onLoadNextPageForAfter(getLastId());
            }
        };
        getRecyclerView().addOnScrollListener(bottomListener);
    }

    public void checkIsHasMore(int size ,int lastId){

        PageBaseAdapter adapter = getAdapterInstance();

        if(size >= bottomListener.getPageSize()){
            //int lastId = list.get(list.size() -1).getId();
            setLastId(lastId);
            setHasMore(true);
            adapter.setHasMore(true);
        }else {
            setHasMore(false);
            adapter.setHasMore(false);
        }
    }

}
