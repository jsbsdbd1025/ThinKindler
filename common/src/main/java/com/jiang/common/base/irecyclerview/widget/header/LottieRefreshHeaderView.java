package com.jiang.common.base.irecyclerview.widget.header;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.jiang.common.R;
import com.jiang.common.base.irecyclerview.RefreshTrigger;

/**
 * Created by jiang on 2017/3/11.
 */

public class LottieRefreshHeaderView extends RelativeLayout implements RefreshTrigger {

    private LottieAnimationView animationView;
    private int mHeight;

    public LottieRefreshHeaderView(Context context) {
        this(context, null);
    }

    public LottieRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public LottieRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.layout_lottie_header, this);

        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
//        animationView.setAnimation("WeAccept.json");
//        animationView.setProgress(0f);
//        animationView.loop(true);
    }

    @Override
    public void onStart(boolean automatic, int headerHeight, int finalHeight) {
        this.mHeight = headerHeight;
    }

    @Override
    public void onMove(boolean finished, boolean automatic, int moved) {
        if (finished) {
            animationView.setVisibility(VISIBLE);
            if (moved <= mHeight) {
                if (animationView.isAnimating()) {
                    stopAnimation();
                }
            } else {
                startAnimation();
            }
        } else {
            if (animationView.isAnimating()) {
                stopAnimation();
            }
        }
    }

    @Override
    public void onRefresh() {
        startAnimation();
    }

    @Override
    public void onRelease() {
        stopAnimation();
    }

    @Override
    public void onComplete() {
        stopAnimation();
    }

    @Override
    public void onReset() {
        stopAnimation();
    }

    private void startAnimation() {
        animationView.setProgress(0f);
        animationView.playAnimation();
    }

    private void stopAnimation() {
        animationView.cancelAnimation();
        animationView.setProgress(0f);
    }
}
