package com.jiang.common.utils.imageloader;

import android.support.annotation.IdRes;

import com.jiang.common.R;

/**
 * Created by jiang on 2017/7/18.
 */

public class ImageLoaderOptions {

    public final static int SHAPE_RECTANGLE = 0;

    public final static int SHAPE_CIRCLE = 1;

    public final static int SHAPE_ROUND_CORNER = 2;


    private int shape = SHAPE_RECTANGLE;

    @IdRes
    private int placeHolder = R.drawable.ph_error;

    private String drawableText = "";

    public ImageLoaderOptions() {
    }

    public ImageLoaderOptions(int shape) {
        this.shape = shape;
    }


    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(int placeHolder) {
        this.placeHolder = placeHolder;
    }

    public String getDrawableText() {
        return drawableText;
    }

    public void setDrawableText(String drawableText) {
        this.drawableText = drawableText;
    }
}
