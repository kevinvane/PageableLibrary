package com.github.pageable.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.github.pageable.adapter.BaseAdapter;
import com.github.pageable.fragment.EmptyRecyclerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 空视图列表
 */
public class FragmentEmptyRecyclerSample extends EmptyRecyclerFragment {


    @Override
    protected void requestNetData() {

        List<DataSample> data = new ArrayList<>();
        for (int i = 0; i < 12; i++) {

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
    public SampleBaseAdapter getmAdapter() {
        return (SampleBaseAdapter)super.getmAdapter();
    }

    @Override
    public BaseAdapter getAdapterInstance() {
        return new SampleBaseAdapter(getContext());
    }

    @Override
    public int getEmptyViewImageResource() {
        return R.drawable.ic_launcher_background;
    }
    public int getEmptyViewTextResource(){
        return R.string.empty;
    }

    @Override
    public void onEmptyViewClick() {

    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        // requestNetData();
    }


    class SampleBaseAdapter extends BaseAdapter<DataSample> {

        public SampleBaseAdapter(Context context) {
            super(context);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return ItemHolderSample.getViewHolder(context,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ItemHolderSample sampleItemHolder = (ItemHolderSample)holder;
            sampleItemHolder.title.setText(getItemData(position).getTitle());
            sampleItemHolder.des.setText(getItemData(position).getDes());
        }
    }

}
