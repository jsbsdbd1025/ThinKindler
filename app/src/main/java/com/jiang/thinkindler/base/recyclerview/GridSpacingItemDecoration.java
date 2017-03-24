package com.jiang.thinkindler.base.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jiang on 2017/3/14.
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;

    public GridSpacingItemDecoration(int spanCount, int spacing) {
        this.spanCount = spanCount;
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (spanCount == 0)
            return;

        outRect.left = spacing / 2; // column * ((1f / spanCount) * spacing)
        outRect.right = spacing / 2; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
        if (position >= spanCount) {
            outRect.top = spacing; // item top
        }

        //如果是第一个或者最后一个 左右边距都为0
        if (position % spanCount == 0) {
            outRect.left = 0;
        }
        if ((position + 1) % spanCount == 0) {
            outRect.right = 0;
        }
    }
}