package com.jiang.media.ui.main;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiang.common.utils.DisplayUtil;
import com.jiang.common.utils.imageloader.ImageLoader;
import com.jiang.common.utils.imageloader.ImageLoaderOptions;
import com.jiang.common.utils.imageloader.ImageLoaderUtil;
import com.jiang.media.R;
import com.jiang.media.entity.RankInfo;

/**
 * Created by knowing on 2017/12/5.
 */

public class RankAdapter extends BaseQuickAdapter<RankInfo.RankBean.ListBean, BaseViewHolder> {
    public RankAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, RankInfo.RankBean.ListBean item) {

        ImageView imageView = helper.getView(R.id.img_item_header);
        ImageLoaderOptions options = new ImageLoaderOptions(ImageLoaderOptions.SHAPE_ROUND_CORNER);
        options.setTargetWidth(DisplayUtil.dip2px(150));
        options.setTargetHeight(DisplayUtil.dip2px(90));

        ImageLoaderUtil.getInstance().display(imageView, item.getPic(), mContext,
                options);

        helper.setText(R.id.tv_item_title, item.getTitle())
                .setText(R.id.tv_item_author, item.getAuthor())
                .setText(R.id.tv_item_play, String.valueOf(item.getPlay()))
                .setText(R.id.tv_item_review, String.valueOf(item.getVideo_review()));
    }
}
