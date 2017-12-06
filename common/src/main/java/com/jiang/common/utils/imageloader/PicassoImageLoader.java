package com.jiang.common.utils.imageloader;

import android.content.Context;
import android.support.annotation.IdRes;
import android.widget.ImageView;

import com.jiang.common.utils.DisplayUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


/**
 * Created by jiang on 2017/7/18.
 */

public class PicassoImageLoader implements ImageLoader {

    @Override
    public void display(ImageView imageView, String url, Context context, ImageLoaderOptions options) {
        RequestCreator creator = Picasso.with(context)
                .load(url);

        parseOptions(options, creator);

        creator.into(imageView);

    }

    @Override
    public void display(ImageView imageView, @IdRes int resId, Context context, ImageLoaderOptions options) {
        RequestCreator creator = Picasso.with(context)
                .load(resId);

        parseOptions(options, creator);


        creator.into(imageView);
    }

    private void parseOptions(ImageLoaderOptions options, RequestCreator creator) {
        if (options != null) {
            if (options.getTargetHeight() != 0 && options.getTargetWidth() != 0) {
                creator.resize(DisplayUtil.dip2px(150f), DisplayUtil.dip2px(90f));
            }

            switch (options.getShape()) {
                case ImageLoaderOptions.SHAPE_RECTANGLE:
                    break;
                case ImageLoaderOptions.SHAPE_CIRCLE:
                    creator.transform(new CropCircleTransformation());
                    break;
                case ImageLoaderOptions.SHAPE_ROUND_CORNER:
                    creator.transform(new RoundedCornersTransformation(options.getRadius(), 0));
                    break;
                default:
                    break;
            }

        }

    }
}
