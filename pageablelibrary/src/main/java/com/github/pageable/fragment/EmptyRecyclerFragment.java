package com.github.pageable.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.pageable.R;
import com.github.pageable.adapter.EmptyBaseAdapter;
import com.github.pageable.adapter.decoration.DefaultDecoration;
import com.github.pageable.view.EmptyRecyclerView;
import com.github.pageable.view.LinearLayoutWrapManager;
import com.google.android.material.appbar.AppBarLayout;

public abstract class EmptyRecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private View mRootView;
    SwipeRefreshLayout swipeLayout;
    private EmptyBaseAdapter mAdapter;

    /**
     * 请求网络数据
     */
    protected abstract void requestNetData();
    public abstract EmptyBaseAdapter newEmptyAdapterInstance();
    /**
     * 空视图显示的图片资源
     * @return image
     */
    public abstract int getEmptyViewImageResource();

    private EmptyRecyclerView recyclerView;
    private LinearLayoutWrapManager linearLayoutManager;

    public EmptyBaseAdapter getmAdapter() {
        return mAdapter;
    }

    public EmptyRecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 默认布局文件，重写方法使用自定义的布局
     * @return layout id
     */
    public int getLayout(){
        return R.layout.layout_page_library;
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
        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });
        ImageView emptyViewImage = rootView.findViewById(R.id.emptyViewImage);
        if(emptyViewImage != null){
            emptyViewImage.setImageResource(getEmptyViewImageResource());
        }
        TextView emptyViewText = rootView.findViewById(R.id.emptyViewText);
        if(emptyViewText != null){
            if(!TextUtils.isEmpty(getEmptyViewTextString())){
                emptyViewText.setText(getEmptyViewTextString());
            }else{
                emptyViewText.setText(getEmptyViewTextResource());
            }
        }

        linearLayoutManager = new LinearLayoutWrapManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setEmptyView(emptyView);

        if(getDivideDecoration() != null){
            recyclerView.addItemDecoration(getDivideDecoration());
        }
        mAdapter = newEmptyAdapterInstance();
        recyclerView.setAdapter(mAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    /**
     * 重写方法，空视图点击事件
     */
    public void onEmptyViewClick(){

        //TODO 跳转
        Log.d("AA","onEmptyViewClick");
    }

    /**
     * 重写方法，显示空视图的文字，优先级高
     * @return string
     */
    public String getEmptyViewTextString(){
        return "没有数据啦！";
    }
    /**
     * 重写方法，显示空视图的文字，优先级低
     * @return string.xml的资源
     */
    public int getEmptyViewTextResource(){
        return R.string.empty;
    }

    /**
     * 仅支持 LinearLayoutWrapManager
     * @return LinearLayoutWrapManager
     */
    public LinearLayoutWrapManager getLinearLayoutManager() {
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
     * 重写方法，返回自定义的ItemDecoration
     * @return ItemDecoration
     */
    public RecyclerView.ItemDecoration getDivideDecoration(){
        return new DefaultDecoration(getContext());
    }

}
