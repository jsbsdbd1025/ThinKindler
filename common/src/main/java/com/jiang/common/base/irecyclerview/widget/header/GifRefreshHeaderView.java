package com.jiang.common.base.irecyclerview.widget.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jiang.common.R;
import com.jiang.common.base.irecyclerview.RefreshTrigger;
import com.jiang.common.widget.GifView;


/**
 * Created by lenovo on 2016/12/9.
 */
public class GifRefreshHeaderView extends RelativeLayout implements RefreshTrigger {

    private GifView gifLoading;
    private boolean rotated = false;
    private int mHeight;

    public GifRefreshHeaderView(Context context) {
        this(context, null);
    }

    public GifRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GifRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.layout_custom_gif_header, this);

        gifLoading = (GifView) findViewById(R.id.gif_loading);
    }

    @Override
    public void onStart(boolean automatic, int headerHeight, int finalHeight) {
        this.mHeight = headerHeight;
    }

    @Override
    public void onMove(boolean finished, boolean automatic, int moved) {
        if (!finished) {
            gifLoading.setVisibility(VISIBLE);
            if (moved <= mHeight) {
                if (rotated) {
                    if (gifLoading.isPlaying())
                        gifLoading.pause();
                    rotated = false;
                } else {
                    gifLoading.setGifResource(R.drawable.loading);
                    gifLoading.play();
                    rotated = true;
                }
            }
        }
    }

    @Override
    public void onRefresh() {

        gifLoading.setGifResource(R.drawable.loading);
        gifLoading.play();
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        rotated = false;

        if (gifLoading.isPlaying())
            gifLoading.pause();
    }

    @Override
    public void onReset() {
        rotated = false;
        if (gifLoading.isPlaying())
            gifLoading.pause();
    }
}
