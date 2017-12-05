package com.jiang.meizi.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiang.common.utils.imageloader.ImageLoaderOptions;
import com.jiang.common.utils.imageloader.ImageLoaderUtil;
import com.jiang.common.utils.imageloader.ImageLoaderUtils;
import com.jiang.meizi.R;
import com.jiang.meizi.entity.bean.PhotoBean;


import java.util.List;

/**
 * Created by jiang on 2017/7/31.
 */

public class MeiziAdapter extends BaseQuickAdapter<PhotoBean, BaseViewHolder> {

    public MeiziAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhotoBean item) {

        ImageView imageView = helper.getView(R.id.image);

        ImageLoaderUtils.display(mContext, imageView, item.getUrl(), new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (imageView == null) {
                    return false;
                }
                if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                float scale = (float) vw / (float) resource.getIntrinsicWidth();
                int vh = Math.round(resource.getIntrinsicHeight() * scale);
                params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                imageView.setLayoutParams(params);
                return false;
            }
        });


    }
}

