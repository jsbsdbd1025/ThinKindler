package com.jiang.douban.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.jiang.common.entity.bean.BookBean;

import com.jiang.douban.R;


import java.util.List;

/**
 * Created by jiang on 2017/5/27.
 */

//public class VBookAdapter extends BaseDelegateAdapter {
//
//    public VBookAdapter(@NotNull List datas) {
//        super(datas);
//    }
//
//    public VBookAdapter(List datas, LayoutHelper layoutHelper) {
//        super(datas, layoutHelper);
//    }
//
//    @Override
//    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        mContext = parent.getContext();
//        mLayoutInflater = LayoutInflater.from(mContext);
//        View v = mLayoutInflater.inflate(R.layout.item_grid_book, parent, false);
//        v.setOnClickListener(this);
//        return new BookHolder(v);
//    }
//
//    private class BookHolder extends IViewHolder<BookBean> {
//
//        public BookHolder(View itemView) {
//            super(itemView);
//        }
//
//        @Override
//        public void setData(BookBean data) {
//            super.setData(data);
//            this.setImageLoder(R.id.img_item_pic, data.getImage(), mContext)
//                    .setText(R.id.tv_item_name, data.getTitle());
//        }
//    }
//}
