package com.jiang.thinkindler.ui.douban.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.jiang.thinkindler.R;
import com.jiang.thinkindler.base.recyclerview.IViewHolder;
import com.jiang.thinkindler.entity.bean.BookBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiang on 2017/5/27.
 */

public class VBookAdapter extends DelegateAdapter.Adapter<IViewHolder> {

    @NonNull
    protected VirtualLayoutManager mLayoutManager;
    protected List<BookBean> list = new ArrayList<>();
    protected LayoutInflater mLayoutInflater;
    protected Context mContext;

    public VBookAdapter(@NonNull VirtualLayoutManager layoutManager, List<BookBean> list) {
        this.mLayoutManager = layoutManager;
        this.list = list;
    }

    public void addList(List<BookBean> datas) {
        list.addAll(datas);
        getLayoutHelpers().get(0).setItemCount(20);
        notifyDataSetChanged();
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View v = mLayoutInflater.inflate(R.layout.item_grid_book, parent, false);
        return new BookHolder(v);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new GridLayoutHelper(3);
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, int position) {
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 300);
        holder.itemView.setLayoutParams(layoutParams);

        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        List<LayoutHelper> helpers = getLayoutHelpers();
        if (helpers == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0, size = helpers.size(); i < size; i++) {
            count += helpers.get(i).getItemCount();
        }
        return count;
    }

    @NonNull
    public List<LayoutHelper> getLayoutHelpers() {
        return this.mLayoutManager.getLayoutHelpers();
    }

    private class BookHolder extends IViewHolder<BookBean> {

        public BookHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(BookBean data) {
            super.setData(data);
            this.setImageLoder(R.id.img_item_pic, data.getImage(), mContext)
                    .setText(R.id.tv_item_name, data.getTitle());
        }
    }
}
