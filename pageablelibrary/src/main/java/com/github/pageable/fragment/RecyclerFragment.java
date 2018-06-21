package com.github.pageable.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.pageable.R;
import com.github.pageable.adapter.DefaultDecoration;
import com.github.pageable.adapter.PageBaseAdapter;
import com.github.pageable.view.EmptyRecyclerView;

public abstract class RecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private View mRootView;
    SwipeRefreshLayout swipeLayout;
    protected abstract void requestNetData();

    public abstract PageBaseAdapter getAdapterInstance();

    private EmptyRecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public EmptyRecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(mRootView == null){

            mRootView = inflater.inflate(R.layout.layout_page, container, false);
            initView(mRootView);
        }
        ViewGroup mViewGroup = (ViewGroup)mRootView.getParent();
        if(mViewGroup!=null){
            mViewGroup.removeView(mRootView);
        }
        return mRootView;
    }

    public void initView(View rootView) {

        swipeLayout = rootView.findViewById(R.id.swipeLayout);
        swipeLayout.setColorSchemeResources(R.color.colorSwipeRefresh);
        swipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeLayout.setProgressViewOffset(true, 0, 100);
        swipeLayout.setOnRefreshListener(this);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        View emptyView = rootView.findViewById(R.id.emptyView);

        ImageView emptyViewImage = rootView.findViewById(R.id.emptyViewImage);
        if(emptyViewImage != null)emptyViewImage.setImageResource(getEmptyViewImageResource());


        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setEmptyView(emptyView);

        if(getDivideDecoration() != null){
            recyclerView.addItemDecoration(getDivideDecoration());
        }

        recyclerView.setAdapter(getAdapterInstance());
        recyclerView.getAdapter().notifyDataSetChanged();
    }
    public LinearLayoutManager getLinearLayoutManager() {
        return linearLayoutManager;
    }

    public void disMissSwipeLayout(){
        if(swipeLayout.isRefreshing()){
            swipeLayout.setRefreshing(false);
        }
    }
    public void showSwipeLayout(){
        if(!swipeLayout.isRefreshing()){
            swipeLayout.setRefreshing(true);
        }
    }



    /**
     * 解决和swipe手势冲突
     *
     * @param appBarLayout
     */
    public void enableAppBarLayout(AppBarLayout appBarLayout){

        //enableAppBarLayout((AppBarLayout)rootView.findViewById(R.id.app_bar));
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    swipeLayout.setEnabled(true);
                } else {
                    swipeLayout.setEnabled(false);
                }
            }
        });
    }
    @Override
    public void onRefresh() {
        requestNetData();
    }


    /**
     *
     * @return
     */
    public RecyclerView.ItemDecoration getDivideDecoration(){
        return new DefaultDecoration(getContext());
    }
    public abstract int getEmptyViewImageResource();

}
