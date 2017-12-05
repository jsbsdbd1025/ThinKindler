package com.jiang.common.utils.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.jiang.common.utils.DisplayUtil;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


/**
 * Created by jiang on 2017/7/18.
 */

public class PicassoImageLoader implements ImageLoader {

    @Override
    public void display(ImageView imageView, String url, Context context, ImageLoaderOptions options) {
        Picasso.with(context)
                .load(url)
                .fit()
                .transform(new RoundedCornersTransformation(DisplayUtil.dip2px(6f), 0))
                .into(imageView);
    }

    @Override
    public void display(ImageView imageView, int resId, Context context, ImageLoaderOptions options) {
        Picasso.with(context)
                .load(resId)
                .into(imageView);
    }
}
