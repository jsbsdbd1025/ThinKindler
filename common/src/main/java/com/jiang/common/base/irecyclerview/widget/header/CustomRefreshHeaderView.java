package com.jiang.common.base.irecyclerview.widget.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jiang.common.R;
import com.jiang.common.base.irecyclerview.RefreshTrigger;


/**
 * Created by lenovo on 2016/12/9.
 */
public class CustomRefreshHeaderView extends RelativeLayout implements RefreshTrigger {

    private AnimationDrawable anim = new AnimationDrawable();
    private Drawable overDrawable;
    private ImageView imgLoading;

    private boolean rotated = false;

    private int mHeight;

    public CustomRefreshHeaderView(Context context) {
        this(context, null);
    }

    public CustomRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.layout_custom_header, this);

        imgLoading = (ImageView) findViewById(R.id.img_loading);
//        for (int i = 0; i <= 8; i++) {
//            int id = getResources().getIdentifier("home_bg_new" + i, "drawable", context.getPackageName());
//            Drawable drawable = ContextCompat.getDrawable(context, id);
//            anim.addFrame(drawable, 100);
//            if (i == 0) {
//                overDrawable = drawable;
//            }
//        }
        anim.setOneShot(false);
        imgLoading.setImageDrawable(overDrawable);
    }

    @Override
    public void onStart(boolean automatic, int headerHeight, int finalHeight) {
        this.mHeight = headerHeight;
    }

    @Override
    public void onMove(boolean finished, boolean automatic, int moved) {
        if (!finished) {
            imgLoading.setVisibility(VISIBLE);
            if (moved <= mHeight) {
                if (rotated) {
                    imgLoading.setImageDrawable(overDrawable);
                    anim.stop();
                    rotated = false;
                } else {
                    imgLoading.setImageDrawable(overDrawable);
                    anim.stop();
                    rotated = true;
                }
            }
        }
    }

    @Override
    public void onRefresh() {

        imgLoading.setImageDrawable(anim);
        anim.start();
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        rotated = false;

        imgLoading.setImageDrawable(overDrawable);
        anim.stop();
    }

    @Override
    public void onReset() {
        rotated = false;
        imgLoading.setImageDrawable(overDrawable);
        anim.stop();
    }
}
