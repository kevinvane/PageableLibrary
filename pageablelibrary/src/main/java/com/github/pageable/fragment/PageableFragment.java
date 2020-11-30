package com.github.pageable.fragment;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.pageable.adapter.PageHeaderBaseAdapter;
import com.github.pageable.adapter.decoration.DefaultHeadFooterDecoration;
import com.github.pageable.listener.BottomOnScrollListener;
import com.github.pageable.model.PageBean;
/**
 * 适合以 页码 来请求下一页的API。
 */
public abstract class PageableFragment extends EmptyRecyclerFragment {

    private BottomOnScrollListener bottomListener;

    protected abstract int getPageCount();
    protected abstract int getPageStart();
    public abstract void onLoadNextPage(int pageNext,int pageCount);
    //private final static int PAGE_SIZE = 20;
    //private final int PAGE_START = 0;


    public PageHeaderBaseAdapter getmAdapter() {
        return (PageHeaderBaseAdapter)super.getmAdapter();
    }

    /**
     * 在加载完成数据的时候，检查是否还有下一页
     * @param pageBean
     */
    public void onLoadMoreCheck(PageBean pageBean){

        PageHeaderBaseAdapter adapter = getmAdapter();
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

        bottomListener = new BottomOnScrollListener(getLinearLayoutManager(),getPageStart(),getPageCount()) {
            @Override
            public void onBottomLoadMore(final int pagination, int pageSize) {
                Log.i(TAG, "onBottomLoadMore: "+pagination);
                if(isHasMore()){
                    onLoadNextPage(pagination,getPageCount());
                }else{
                    Log.e(TAG, "onLoadMore: 没有更多了" );
                }
            }
        };
        getRecyclerView().addOnScrollListener(bottomListener);
    }

    @Override
    public void onRefresh() {

        //PageHeaderBaseAdapter adapter = getmAdapter();
        //adapter.onRefresh();
        resetLoadMore();
        requestNetData();
    }
    @Override
    protected void requestNetData() {
        onLoadNextPage(getPageStart(),getPageCount());
    }


    private void resetLoadMore(){

        setHasMore(true);
        bottomListener.setPagination(getPageStart());
    }
    private void loadComplete(){

        if(bottomListener!=null)bottomListener.loadComplete();
    }
    private void setHasMore(boolean more){

        if(bottomListener!=null)bottomListener.setHasMore(more);
        loadComplete();
    }

    @Override
    public RecyclerView.ItemDecoration getDivideDecoration(){
        return new DefaultHeadFooterDecoration(getContext());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(bottomListener!=null)bottomListener.releaseHandler();
    }
}
