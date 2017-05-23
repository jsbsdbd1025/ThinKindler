package com.jiang.thinkindler.ui.live;

import android.view.View;
import android.view.ViewGroup;

import com.jiang.thinkindler.R;
import com.jiang.thinkindler.base.recyclerview.BaseRecyclerAdapter;
import com.jiang.thinkindler.base.recyclerview.IViewHolder;
import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.ui.douban.adapter.BookAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jiang on 2017/5/23.
 */

public class LiveAdapter extends BaseRecyclerAdapter<BookBean> {

    @Inject
    public LiveAdapter(List<BookBean> list) {
        super(list);
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder != null) {
            return holder;
        }
        View v = mLayoutInflater.inflate(R.layout.item_list_live, parent, false);
        v.setOnClickListener(this);
        return new LiveHolder(v);
    }

    private class LiveHolder extends IViewHolder<BookBean> {

        public LiveHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(BookBean data) {
            super.setData(data);
            this.setImageLoder(R.id.img_item_pic, data.getImages().getMedium(), mContext)
                    .setText(R.id.tv_item_name, data.getTitle());

//                      .setText(R.id.btn_video, data.getFiletypestr())
//                    .setText(R.id.btn_video_type, data.getLebusiness())
        }
    }
}

