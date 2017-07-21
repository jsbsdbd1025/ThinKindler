package com.jiang.common.widget.multistatuslayout.widget.loading;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.jiang.common.R;
import com.jiang.common.widget.multistatuslayout.MultiStatusLayout;


/**
 * Created by jiang on 2017/7/15.
 */

public class LoadingLayout extends MultiStatusLayout.LoadingLayout  {

    private ProgressBar mProgressBar;

    public LoadingLayout(Context context) {
        this(context, null);
    }

    public LoadingLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_loading;
    }

    @Override
    protected void initLoadingView() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onPause() {

    }

    @Override
    protected void onStop() {

    }

}
