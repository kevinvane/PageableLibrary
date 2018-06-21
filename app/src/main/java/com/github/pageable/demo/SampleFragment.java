package com.github.pageable.demo;

import android.view.View;

import com.github.pageable.adapter.PageBaseAdapter;
import com.github.pageable.fragment.PageableFragment;
import com.github.pageable.model.PageBean;

import java.util.ArrayList;
import java.util.List;

public class SampleFragment extends PageableFragment {

   private SampleAdapter adapter;

    @Override
    public void onLoadNextPage(int pageNext) {

        List<Sample> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            Sample sample = new Sample();
            sample.setTitle("test"+pageNext);
            sample.setDes("des"+i);
            data.add(sample);
        }
        adapter.updateList(data);

        PageBean pb = new PageBean();
        pb.setLast(pageNext == 5);

        onLoadMoreCheck(pb);

        //请求完成调用
        disMissSwipeLayout();
    }

    @Override
    public PageBaseAdapter getAdapterInstance() {
        if(adapter == null){
            adapter = new SampleAdapter(getContext());
        }
        return adapter;
    }

    @Override
    public int getEmptyViewImageResource() {
        return 0;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        requestNetData();
    }
}
