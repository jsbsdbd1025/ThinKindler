package com.jiang.thinkindler.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.jiang.thinkindler.R;
import com.jiang.thinkindler.base.recyclerview.BaseRecyclerAdapter;
import com.jiang.thinkindler.base.recyclerview.IViewHolder;
import com.jiang.thinkindler.entity.bean.BookBean;

import java.util.List;

/**
 * Created by jiang on 2017/3/9.
 */

public class BookAdapter extends BaseRecyclerAdapter<BookBean> {

    public BookAdapter(Context context, List<BookBean> list) {
        super(context, list);
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder != null) {
            return holder;
        }
        View v = mLayoutInflater.inflate(R.layout.item_grid_book, parent, false);
        v.setOnClickListener(this);
        return new BookHolder(v);
    }

    private class BookHolder extends IViewHolder<BookBean> {

        public BookHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(BookBean data) {
            super.setData(data);
            this.setImageLoder(R.id.img_item_pic, data.getImages().getMedium(), mContext)
                    .setText(R.id.tv_item_name, data.getTitle());
        }
    }
}

