package com.github.pageable.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public abstract class PageBaseAdapter<T> extends RecyclerView.Adapter implements PageAdapterInterface<T>{

    public final String TAG = getClass().getName();
    public Context context;
    public final static int VIEW_TYPE_HEADER = 0;
    public final static int VIEW_TYPE_FOODER = 1;
    public final static int VIEW_TYPE_ITEM = 2;

    private boolean hasMore = true;

    public List<T> lists;

    public PageBaseAdapter(Context context) {
       this.context = context;
       this.lists = new ArrayList<>();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        lists.clear();
        lists.add(null);
        lists.add(null);
    }

    @Override
    public void updateList(List<T> data){

        int index = lists.size() - 1;
        lists.addAll(index,data);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;
        switch (viewType){
            case VIEW_TYPE_HEADER:
                holder = onCreateViewHolderHeader(parent);
                break;
            default:
            case VIEW_TYPE_ITEM:
                holder = onCreateViewHolderItem(parent);
                break;
            case VIEW_TYPE_FOODER:
                holder = onCreateViewHolderFooder(parent);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case VIEW_TYPE_HEADER:
                onBindViewHolderHeader(holder);
                break;
            case VIEW_TYPE_ITEM:
                onBindViewHolderItem(holder,position,getItemData(position));
                break;
            case VIEW_TYPE_FOODER:
                onBindViewHolderFooder(holder);
                break;
        }
    }
    private RecyclerView.ViewHolder onCreateViewHolderFooder(@NonNull ViewGroup parent){
        return PageableHolder.getViewHolder(context,parent);
    }

    private void onBindViewHolderFooder(@NonNull RecyclerView.ViewHolder holder){
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
            return VIEW_TYPE_FOODER;
        }
        return VIEW_TYPE_ITEM;
    }

    @Override
    public T getItemData(int position) {
        return lists.get(position);
    }

    @Override
    public int getItemCount() {
        return lists.size();
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
