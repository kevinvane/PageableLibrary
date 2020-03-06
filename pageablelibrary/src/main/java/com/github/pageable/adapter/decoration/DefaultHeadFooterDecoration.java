package com.github.pageable.adapter.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class DefaultHeadFooterDecoration extends DefaultDecoration{

    public DefaultHeadFooterDecoration(Context context) {
        super(context);

    }
    public DefaultHeadFooterDecoration(Context context, int colorId) {
        super(context,colorId);

    }


    /**
     *  getItemOffsets(),可以实现类似padding的效果
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        //全部Item都有一条分割线
        outRect.bottom = dividerHeight;
    }


    /**
     * 可以实现类似绘制背景的效果，内容在上面
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int childCount = parent.getChildCount();
        int left = margin_horizontal;
        int right = parent.getWidth() - margin_horizontal;

        //除了头尾，其他item绘制分割线
        for (int i = 1; i < childCount - 1; i++) {

            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }

    /**
     * 可以绘制在内容的上面，覆盖内容
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
