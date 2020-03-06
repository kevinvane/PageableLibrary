package com.github.pageable.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.pageable.adapter.holder.PageableHolder;

import java.util.List;


public abstract class PageHeaderBaseAdapter<T> extends HeaderBaseAdapter<T> implements PageAdapterInterface{

    private boolean hasMore = true;
    private int pageStart = 0;

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public PageHeaderBaseAdapter(Context context) {
        super(context);
        onRefresh();
    }
    @Override
    public void clearList() {
        getLists().clear();
        getLists().add(null);
        getLists().add(null);
    }
    @Override
    public void onRefresh() {
        //clearList();
    }

    public void updateList(int page,List<T> data){

        if(page == getPageStart()){
            clearList();
        }
        if(data != null){
            int index = getLists().size() - 1;
            getLists().addAll(index,data);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderFooter(@NonNull ViewGroup parent){
        return PageableHolder.getViewHolder(context,parent);
    }

    @Override
    public void onBindViewHolderFooter(@NonNull RecyclerView.ViewHolder holder){
        PageableHolder loadMoreHolder = (PageableHolder)holder;
        if(isHasMore()){
            loadMoreHolder.showLoading();
        }else{
            loadMoreHolder.dismissLoading();
        }
    }


    /**
     * 默认有 head 和 food
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if(position == 0){
            return VIEW_TYPE_HEADER;
        }else if(position == getItemCount()-1){
            return VIEW_TYPE_FOOTER;
        }
        return VIEW_TYPE_ITEM;
    }

    /**
     * 返回当item数量为n时，当前是空视图，如有header时，是1.
     * @return
     */
    @Override
    public int getEmptyItemSize(){
        return 2;
    }
    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    private boolean isHasMore() {
        return hasMore;
    }

}
