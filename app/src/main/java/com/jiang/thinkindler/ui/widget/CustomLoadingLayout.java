package com.jiang.thinkindler.ui.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.jiang.common.widget.multistatuslayout.MultiStatusLayout;
import com.jiang.thinkindler.R;


/**
 * Created by jiang on 2017/7/17.
 */

public class CustomLoadingLayout extends MultiStatusLayout.LoadingLayout {

    private static final String TAG = CustomLoadingLayout.class.getName();
    private AnimationDrawable anim;
    private Drawable overDrawable;
    private ImageView imgLoading;


    public CustomLoadingLayout(Context context) {
        super(context);
    }

    public CustomLoadingLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLoadingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_custom_loading;
    }

    @Override
    protected void initLoadingView() {
        anim = new AnimationDrawable();
        imgLoading = (ImageView) findViewById(R.id.image);
        for (int i = 0; i < 4; i++) {
            int id = getResources().getIdentifier("app_loading" + i, "drawable", context.getPackageName());
            Drawable drawable = ContextCompat.getDrawable(context, id);
            anim.addFrame(drawable, 100);
            if (i == 3) {
                overDrawable = drawable;
            }
        }
        anim.setOneShot(false);
        imgLoading.setImageDrawable(overDrawable);
    }

    @Override
    protected void onStart() {
        imgLoading.setImageDrawable(anim);
        anim.start();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onPause() {

    }

    @Override
    protected void onStop() {
        imgLoading.setImageDrawable(overDrawable);
        anim.stop();
        Log.e(TAG, "onStop: ");
    }
}
