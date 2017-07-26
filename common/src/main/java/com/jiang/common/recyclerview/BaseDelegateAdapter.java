package com.jiang.common.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiang on 2017/6/8.
 */

public abstract class BaseDelegateAdapter<O extends Object> extends DelegateAdapter.Adapter<IViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {

    protected List<O> datas = new ArrayList<>();
    protected LayoutInflater mLayoutInflater;
    protected Context mContext;
    public List<IViewHolder> holderList = new ArrayList<>();
    private LayoutHelper layoutHelper;

    protected OnRecyclerViewItemClickListener mOnItemClickListener = null;
    protected OnRecyclerViewItemLongClickListener mOnItemLongClickListener = null;

    public BaseDelegateAdapter(@NotNull List<O> datas) {
        this.datas = datas;
        this.layoutHelper = new LinearLayoutHelper();
    }

    public BaseDelegateAdapter(@NotNull List<O> datas, LayoutHelper layoutHelper) {
        this.datas = datas;
        this.layoutHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, int position) {
        if (holderList.size() > position) {
            holderList.set(position, holder);
        } else {
            holderList.add(position, holder);
        }
        holder.itemView.setTag(position);
        holder.setData(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    /**
     * 这是一个添加一条数据并刷新界面的方法
     *
     * @param data
     */
    public void addData(O data) {
        datas.add(datas.size(), data);
        notifyItemInserted(datas.size() - 1);
    }

    public void removeData(O data) {
        if (datas.contains(data)) {
            datas.remove(data);
        }
        notifyDataSetChanged();
    }

    public void addList(List<O> data) {
        this.datas.addAll(data);
        notifyDataSetChanged();
    }

    public void replaceAll(List<O> data) {
        datas.clear();
        datas.addAll(data);
        notifyDataSetChanged();
    }

    public void clear() {
        datas.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onItemLongClick(v, (int) v.getTag());
            return true;
        }
        return false;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnRecyclerViewItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    //define interface
    public static interface OnRecyclerViewItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
