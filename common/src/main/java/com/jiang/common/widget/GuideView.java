package com.jiang.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jiang.common.R;

/**
 * Created by jiang on 2017/2/28.
 * 用于引导页的viewpaper
 */

public class GuideView extends FrameLayout {
    private Context mContext;
    private ImageView bgGuide;

    public GuideView(Context context) {
        this(context, null);
    }

    public GuideView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public GuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.layou_guide, null);
        bgGuide = (ImageView) view.findViewById(R.id.img_guide);
        addView(view);
    }

    public void setImageResource(int resId) {
        bgGuide.setImageResource(resId);
    }

    public void setImageDrawable(Drawable drawable) {
        bgGuide.setImageDrawable(drawable);
    }
}
