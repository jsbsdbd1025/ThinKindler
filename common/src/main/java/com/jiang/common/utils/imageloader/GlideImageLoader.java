package com.jiang.common.utils.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by jiang on 2017/7/18.
 */

public class GlideImageLoader implements ImageLoader {

    @Override
    public void display(ImageView imageView, String url, Context context, ImageLoaderOptions options) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    @Override
    public void display(ImageView imageView, int resId, Context context, ImageLoaderOptions options) {
        Glide.with(context)
                .load(resId)
                .into(imageView);
    }
}
