package com.jiang.media.ui.main;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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
        ImageLoaderUtil.getInstance().display(helper.getView(R.id.img_item_header), item.getPic(), mContext,
                new ImageLoaderOptions(ImageLoaderOptions.SHAPE_ROUND_CORNER));

        helper.setText(R.id.tv_item_title, item.getTitle())
                .setText(R.id.tv_item_author, item.getAuthor())
                .setText(R.id.tv_item_play, String.valueOf(item.getPlay()))
                .setText(R.id.tv_item_review, String.valueOf(item.getVideo_review()));
    }
}
