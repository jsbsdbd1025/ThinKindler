package com.jiang.common.utils.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

/**
 * Created by jiang on 2017/7/18.
 */

public class PicassoImageLoader implements ImageLoader {

    @Override
    public void display(ImageView imageView, String url, Context context, ImageLoaderOptions options) {
        Picasso.with(context)
                .load(url)
                .into(imageView);
    }

    @Override
    public void display(ImageView imageView, int resId, Context context, ImageLoaderOptions options) {
        Picasso.with(context)
                .load(resId)
                .into(imageView);
    }
}
