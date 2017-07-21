package com.jiang.common.utils.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by jiang on 2017/7/18.
 */

public class ImageLoaderUtil implements ImageLoader {

    private static volatile ImageLoaderUtil instance = null;
    ImageLoader mImageLoader = null;

    protected ImageLoaderUtil() {
        mImageLoader = new PicassoImageLoader();
    }

    public static ImageLoaderUtil getInstance() {
        if (instance == null) {
            synchronized (ImageLoaderUtil.class) {
                if (instance == null) {
                    instance = new ImageLoaderUtil();
                }
            }
        }
        return instance;
    }


    public void setImageLoader(ImageLoader imageLoader) {
        if (imageLoader != null) {
            mImageLoader = imageLoader;
        }
    }

    @Override
    public void display(ImageView imageView, String url, Context context, ImageLoaderOptions options) {
        mImageLoader.display(imageView, url, context, options);
    }

    @Override
    public void display(ImageView imageView, int resId, Context context, ImageLoaderOptions options) {
        mImageLoader.display(imageView, resId, context, options);
    }
}
