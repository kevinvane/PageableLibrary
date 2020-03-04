package com.github.pageable.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.github.pageable.adapter.BaseAdapter;
import com.github.pageable.adapter.HeaderBaseAdapter;
import com.github.pageable.fragment.EmptyRecyclerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 带有头部视图的空视图列表
 */
public class FragmentEmptyRecyclerHeadSample extends EmptyRecyclerFragment {


    @Override
    protected void requestNetData() {

        List<DataSample> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            DataSample sample = new DataSample();
            sample.setTitle("test");
            sample.setDes("des"+i);
            data.add(sample);
        }
        getmAdapter().updateList(data);

        //请求完成调用
        disMissSwipeLayout();
    }

    @Override
    public SampleBaseHeadAdapter getmAdapter() {
        return (SampleBaseHeadAdapter)super.getmAdapter();
    }

    @Override
    public BaseAdapter getAdapterInstance() {
        return new SampleBaseHeadAdapter(getContext());
    }

    @Override
    public int getEmptyViewImageResource() {
        return R.drawable.ic_launcher_background;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        // requestNetData();
    }


    class SampleBaseHeadAdapter extends HeaderBaseAdapter<DataSample> {


        public SampleBaseHeadAdapter(Context context) {
            super(context);
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolderHeader(@NonNull ViewGroup parent) {
            return ItemHolderSample.getHeadHolder(context,parent);
        }
        @Override
        public void onBindViewHolderHeader(@NonNull RecyclerView.ViewHolder holder) {

            ItemHolderSample sampleItemHolder = (ItemHolderSample)holder;
            sampleItemHolder.title.setText("这是头");
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolderItem(@NonNull ViewGroup parent) {
            return ItemHolderSample.getViewHolder(context,parent);
        }

        @Override
        public void onBindViewHolderItem(@NonNull RecyclerView.ViewHolder holder, int position, DataSample item) {
            ItemHolderSample sampleItemHolder = (ItemHolderSample)holder;
            sampleItemHolder.title.setText(getItemData(position).getTitle());
            sampleItemHolder.des.setText(getItemData(position).getDes());
        }

        @Override
        public void onBindViewHolderItemClick(int position, DataSample item) {

        }
    }
}
