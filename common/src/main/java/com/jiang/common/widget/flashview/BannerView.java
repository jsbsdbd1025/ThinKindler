package com.jiang.common.widget.flashview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jiang.common.R;
import com.jiang.common.utils.imageloader.ImageLoaderUtils;

/**
 * Created by lenovo on 2017/2/8.
 */

public class BannerView extends FrameLayout {

    private Context context;
    private ImageView imgBackgroud;
    private BannerBean data;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        //读取该自定义控件自定义的属性
        this.context = context;

        LayoutInflater.from(context).inflate(R.layout.layout_banner, this, true);
        imgBackgroud = (ImageView) findViewById(R.id.img_banner);
    }

    public ImageView getImageView() {
        return imgBackgroud;
    }

    public void bindData(BannerBean bean) {

        if (bean == null)
            return;
        this.data = bean;

        setUpView();
    }

    private void setUpView() {

        if (TextUtils.isEmpty(data.getImageUrl())) {
            imgBackgroud.setImageResource(R.drawable.ph_error);
        } else {
            ImageLoaderUtils.display(context, imgBackgroud, data.getImageUrl());
        }
    }
}
