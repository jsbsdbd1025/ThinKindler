package com.jiang.common.utils.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by jiang on 2017/7/18.
 */

public interface ImageLoader {

    void display(ImageView imageView, String url, Context context, ImageLoaderOptions options);

    void display(ImageView imageView, int  resId, Context context, ImageLoaderOptions options);

}
