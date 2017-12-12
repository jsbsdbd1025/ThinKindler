package com.jiang.douban.ui.main;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiang.common.entity.bean.BookBean;
import com.jiang.common.utils.imageloader.ImageLoaderUtil;
import com.jiang.douban.R;

import java.util.List;

/**
 * Created by jiang on 2017/5/27.
 */

public class BookAdapter extends BaseQuickAdapter<BookBean, BaseViewHolder> {


    public BookAdapter(int layoutResId) {
        super(layoutResId);
    }

    public BookAdapter(int layoutResId, @Nullable List<BookBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookBean item) {
        helper.setText(R.id.tv_item_name, item.getTitle());
        ImageLoaderUtil.getInstance().display(helper.getView(R.id.img_item_pic), item.getImages().getLarge(), mContext, null);

    }

}
