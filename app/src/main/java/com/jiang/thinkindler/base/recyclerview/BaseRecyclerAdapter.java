package com.jiang.thinkindler.base.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.thinkindler.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DB on 2016/11/1.
 */

public class BaseRecyclerAdapter<O extends Object> extends RecyclerView.Adapter<IViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {


    protected PlaceHolderType status = null;
    protected int EMPTY_TYPE = -1;
    protected List<O> list = new ArrayList<>();
    protected LayoutInflater mLayoutInflater;
    protected Context context;
    public List<IViewHolder> holderList = new ArrayList<>();

    protected OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private OnRecyclerViewItemLongClickListener mOnItemLongClickListener = null;

    /**
     * 这是一个添加一条数据并刷新界面的方法
     *
     * @param data
     */
    public void addData(O data) {
        list.add(list.size(), data);
        notifyItemInserted(list.size() - 1);
    }

    public void removeData(O data) {
        if (list.contains(data)) {
            list.remove(data);
        }
        notifyDataSetChanged();
    }


    public void addList(List<O> datas) {
        list.addAll(datas);
        notifyDataSetChanged();
    }

    public void replaceAll(List<O> datas) {
        list.clear();
        list.addAll(datas);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public BaseRecyclerAdapter(Context context, List<O> list) {
        this.context = context;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (EMPTY_TYPE == viewType) {
            View v = mLayoutInflater.inflate(R.layout.layout_placeholder, parent, false);
            return new EmptyHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, int position) {
        if (list.size() <= 0) {
            if (status != null) {
                ((EmptyHolder) holder).setErrorType(status);
            }
        } else {
            if (holderList.size() > position) {
                holderList.set(position, holder);
            } else {
                holderList.add(position, holder);
            }
            holder.itemView.setTag(position);
            holder.setData(list.get(position));
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (list.size() <= 0) {
            return EMPTY_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size() > 0 ? list.size() : 1;
    }

    public void setErrorType(PlaceHolderType status) {
        this.status = status;
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
