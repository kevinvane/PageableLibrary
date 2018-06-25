package com.github.pageable.demo;

import android.view.View;

import com.github.pageable.adapter.BaseAdapter;
import com.github.pageable.fragment.RecyclerFragment;

import java.util.ArrayList;
import java.util.List;

public class SampleBaseFragment extends RecyclerFragment {


    @Override
    protected void requestNetData() {

        List<Sample> data = new ArrayList<>();
        for (int i = 0; i < 12; i++) {

            Sample sample = new Sample();
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

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        requestNetData();
    }
}
