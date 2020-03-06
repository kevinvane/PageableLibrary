package com.github.pageable.adapter.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.pageable.R;


public class DefaultDecoration extends RecyclerView.ItemDecoration{

    protected int dividerHeight;
    protected int margin_horizontal;

    protected Paint dividerPaint;

    public DefaultDecoration(Context context) {

        init(context, R.color.colorDecoration);
    }
    public DefaultDecoration(Context context, int colorId) {

        init(context,colorId);
    }

    public void init(Context context,int colorId){
        dividerPaint = new Paint();
        dividerPaint.setColor(context.getResources().getColor(colorId));
        dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.default_decoration_height);
        margin_horizontal = context.getResources().getDimensionPixelSize(R.dimen.default_decoration_height);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


        //全部Item都有一条分割线
        outRect.bottom = dividerHeight;

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int childCount = parent.getChildCount();
        int left = margin_horizontal;
        int right = parent.getWidth() - margin_horizontal;

        //全部Item都有一条分割线
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }

}
