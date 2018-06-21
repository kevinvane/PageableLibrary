package com.github.pageable.fragment;

import android.util.Log;
import android.view.View;

import com.github.pageable.adapter.PageBaseAdapter;
import com.github.pageable.listener.BottomOnScrollListener;
import com.github.pageable.model.PageBean;
/**
 * 适合以 页码 来请求下一页的API。
 */
public abstract class PageableFragment extends RecyclerFragment{

    private BottomOnScrollListener bottomListener;

    public abstract void onLoadNextPage(int pageNext);
    private final static int PAGE_SIZE = 20;
    private final int PAGE_START = 0;

    /**
     * 在加载完成数据的时候，检查是否还有下一页
     * @param pageBean
     */
    public void onLoadMoreCheck(PageBean pageBean){

        PageBaseAdapter adapter = getAdapterInstance();
        boolean more = (pageBean != null && !pageBean.isLast());
        setHasMore(more);
        adapter.setHasMore(more);
        loadComplete();
    }
    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        setBottomListener();
    }
    private void setBottomListener(){

        bottomListener = new BottomOnScrollListener(getLinearLayoutManager(),PAGE_START,PAGE_SIZE) {
            @Override
            public void onBottomLoadMore(final int pagination, int pageSize) {
                Log.i(TAG, "onBottomLoadMore: "+pagination);
                if(isHasMore()){
                    onLoadNextPage(pagination);
                }else{
                    Log.e(TAG, "onLoadMore: 没有更多了" );
                }
            }
        };
        getRecyclerView().addOnScrollListener(bottomListener);
    }

    @Override
    public void onRefresh() {

        getAdapterInstance().onRefresh();
        resetLoadMore();
        requestNetData();
    }
    @Override
    protected void requestNetData() {
        onLoadNextPage(PAGE_START);
    }


    private void resetLoadMore(){

        setHasMore(true);
        bottomListener.setPagination(PAGE_START);
    }
    private void loadComplete(){

        if(bottomListener!=null)bottomListener.loadComplete();
    }
    private void setHasMore(boolean more){

        if(bottomListener!=null)bottomListener.setHasMore(more);
        loadComplete();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(bottomListener!=null)bottomListener.releaseHandler();
    }
}
