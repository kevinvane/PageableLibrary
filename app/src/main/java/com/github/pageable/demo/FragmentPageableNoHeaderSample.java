package com.github.pageable.demo;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.github.pageable.adapter.PageHeaderBaseAdapter;
import com.github.pageable.adapter.PageNoHeaderBaseAdapter;
import com.github.pageable.adapter.decoration.DefaultHeadFooterDecoration;
import com.github.pageable.fragment.PageableFragment;
import com.github.pageable.model.PageBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 分页，不带有Header
 * */
public class FragmentPageableNoHeaderSample extends PageableFragment {


    private SamplePageNoHeaderAdapter adapter;
    private Handler handler = new Handler();
    @Override
    public void onLoadNextPage(final int pageNext, int pageCount) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                List<DataSample> data = new ArrayList<>();
                for (int i = 0; i < getPageCount(); i++) {

                    DataSample sample = new DataSample();
                    sample.setTitle("test"+pageNext);
                    sample.setDes("des"+i);
                    data.add(sample);
                }
                adapter.updateList(pageNext,data);
                PageBean pb = new PageBean();
                pb.setLast(pageNext >= 2);
                onLoadMoreCheck(pb);
                //请求完成调用
                disMissSwipeLayout();
            }
        },1800);
    }

    @Override
    protected int getPageCount() {
        return 15;
    }

    @Override
    protected int getPageStart() {
        return 0;
    }
    @Override
    public PageHeaderBaseAdapter newEmptyAdapterInstance() {
        if(adapter == null){
            adapter = new SamplePageNoHeaderAdapter(getContext());
        }
        return adapter;
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

    @Override
    public RecyclerView.ItemDecoration getDivideDecoration() {

        return new DefaultHeadFooterDecoration(getContext(),R.color.colorAccent);
    }


    class SamplePageNoHeaderAdapter extends PageNoHeaderBaseAdapter<DataSample> {

        public SamplePageNoHeaderAdapter(Context context) {
            super(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolderItem(@NonNull ViewGroup parent) {
            return ItemHolderSample.getViewHolder(context,parent);
        }

        @Override
        public void onBindViewHolderItem(@NonNull RecyclerView.ViewHolder holder, int position, DataSample item) {

            ItemHolderSample sampleItemHolder = (ItemHolderSample)holder;
            sampleItemHolder.title.setText(item.getTitle());
            sampleItemHolder.des.setText("position="+position);
        }

        @Override
        public void onBindViewHolderItemClick(int position, DataSample item) {

        }


    }
}
