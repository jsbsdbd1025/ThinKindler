package com.jiang.media.ui.main;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiang.common.utils.imageloader.ImageLoaderOptions;
import com.jiang.common.utils.imageloader.ImageLoaderUtil;
import com.jiang.media.R;
import com.jiang.media.entity.BiliBiliRecommendHead;

import java.util.List;

/**
 * Created by knowing on 2017/12/6.
 */

public class RecommentAdapter extends BaseSectionQuickAdapter<BiliBiliRecommendHead, BaseViewHolder> {

    public RecommentAdapter(int layoutResId, int sectionHeadResId, List<BiliBiliRecommendHead> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, BiliBiliRecommendHead item) {
        helper.setText(R.id.tv_item_head, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, BiliBiliRecommendHead item) {

        ImageView imageView = (ImageView) helper.getView(R.id.img_item_cover);

        ImageLoaderUtil.getInstance().display(imageView, item.t.getCover(), mContext,
                new ImageLoaderOptions());

        helper.setText(R.id.tv_item_title, item.t.getTitle())
                .setText(R.id.tv_item_play, String.valueOf(item.t.getPlay()));

    }
}
