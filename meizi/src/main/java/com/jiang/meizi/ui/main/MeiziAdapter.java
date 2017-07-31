package com.jiang.meizi.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.jiang.common.recyclerview.BaseDelegateAdapter;
import com.jiang.common.recyclerview.IViewHolder;
import com.jiang.common.utils.imageloader.ImageLoaderOptions;
import com.jiang.common.utils.imageloader.ImageLoaderUtil;
import com.jiang.meizi.R;
import com.jiang.meizi.entity.bean.PhotoBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by jiang on 2017/7/31.
 */

public class MeiziAdapter extends BaseDelegateAdapter {

    public MeiziAdapter(@NotNull List datas) {
        super(datas);
    }

    public MeiziAdapter(List datas, LayoutHelper layoutHelper) {
        super(datas, layoutHelper);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View v = mLayoutInflater.inflate(R.layout.item_meizi, parent, false);
        v.setOnClickListener(this);
        return new MeiziHolder(v);
    }

    private class MeiziHolder extends IViewHolder<PhotoBean> {

        public MeiziHolder(View itemView) {
            super(itemView);
            ViewGroup.LayoutParams params = itemView.getLayoutParams();
            params.height = (int) (300 + (Math.random() * 200));
        }

        @Override
        public void setData(PhotoBean data) {
            super.setData(data);
            ImageLoaderUtil.getInstance().display(getView(R.id.image), data.getUrl(), mContext, new ImageLoaderOptions());
        }
    }

}
