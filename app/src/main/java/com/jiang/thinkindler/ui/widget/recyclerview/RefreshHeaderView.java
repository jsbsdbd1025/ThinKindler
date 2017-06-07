package com.jiang.thinkindler.ui.widget.recyclerview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jiang.common.widget.swipetoloadlayout.SwipeRefreshTrigger;
import com.jiang.common.widget.swipetoloadlayout.SwipeTrigger;
import com.jiang.thinkindler.R;


/**
 * Created by Aspsine on 2015/11/5.
 */
public class RefreshHeaderView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {


    private ImageView ivRefresh;

    private AnimationDrawable mAnimDrawable;


    private int mTriggerOffset;


    public RefreshHeaderView(Context context) {
        super(context);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTriggerOffset = context.getResources().getDimensionPixelOffset(R.dimen.refresh_header_height);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivRefresh = (ImageView) findViewById(R.id.ivRefresh);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
    }

    @Override
    public void onRefresh() {
        if (!mAnimDrawable.isRunning()){
            mAnimDrawable.start();
        }
    }

    @Override
    public void onPrepare() {
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
    }

    @Override
    public void onRelease() {
        if (!mAnimDrawable.isRunning()){
            mAnimDrawable.start();
        }
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onReset() {
        mAnimDrawable.stop();
    }
}
