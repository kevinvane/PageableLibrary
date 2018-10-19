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
import android.widget.TextView;

import com.github.pageable.R;
import com.github.pageable.adapter.BaseAdapter;
import com.github.pageable.adapter.DefaultDecoration;
import com.github.pageable.adapter.DefaultHeadFoodDecoration;
import com.github.pageable.view.EmptyRecyclerView;
import com.github.pageable.view.LinearLayoutWrapManager;

public abstract class RecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private View mRootView;
    SwipeRefreshLayout swipeLayout;
    private BaseAdapter mAdapter;
    protected abstract void requestNetData();
    public abstract BaseAdapter getAdapterInstance();
    public abstract int getEmptyViewImageResource();

    private EmptyRecyclerView recyclerView;
    private LinearLayoutWrapManager linearLayoutManager;

    public BaseAdapter getmAdapter() {
        return mAdapter;
    }

    public EmptyRecyclerView getRecyclerView() {
        return recyclerView;
    }

    public int getLayout(){
        return R.layout.layout_page;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(mRootView == null){

            mRootView = inflater.inflate(getLayout(), container, false);
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
        if(emptyViewImage != null){
            emptyViewImage.setImageResource(getEmptyViewImageResource());
        }
        TextView emptyViewText = rootView.findViewById(R.id.emptyViewText);
        if(emptyViewText != null){
            emptyViewText.setText(getEmptyViewTextResource());
        }

        linearLayoutManager = new LinearLayoutWrapManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setEmptyView(emptyView);

        if(getDivideDecoration() != null){
            recyclerView.addItemDecoration(getDivideDecoration());
        }
        mAdapter = getAdapterInstance();
        recyclerView.setAdapter(mAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public int getEmptyViewTextResource(){
        return R.string.empty;
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

    public boolean isRefreshing(){
        return swipeLayout.isRefreshing();
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

}
