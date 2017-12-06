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

    public final static int DEFAULT_ROUNDED_CORNE_RADIUS = 6;

    private int shape = SHAPE_RECTANGLE;

    private int targetWidth;

    private int targetHeight;

    private int radius = DEFAULT_ROUNDED_CORNE_RADIUS;
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

    public int getTargetWidth() {
        return targetWidth;
    }

    public void setTargetWidth(int targetWidth) {
        this.targetWidth = targetWidth;
    }

    public int getTargetHeight() {
        return targetHeight;
    }

    public void setTargetHeight(int targetHeight) {
        this.targetHeight = targetHeight;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
