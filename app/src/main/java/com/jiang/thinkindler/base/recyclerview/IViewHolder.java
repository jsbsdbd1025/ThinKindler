package com.jiang.thinkindler.base.recyclerview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiang.common.utils.imageloader.ImageLoaderUtils;
import com.jiang.common.utils.imageloader.PlaceHolder;



/**
 * Created by aspsine on 16/3/12.
 */
public abstract class IViewHolder<M extends Object> extends RecyclerView.ViewHolder {


    private SparseArray<View> mViews;
    private View mConvertView;
    protected M data;
    protected Context mContext;

    public IViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        this.mViews = new SparseArray<>();
        this.mConvertView = itemView;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }

    public void setData(M data) {
        this.data = data;
    }

    @Deprecated
    public final int getIPosition() {
        return getPosition() - 2;
    }

    public final int getILayoutPosition() {
        return getLayoutPosition() - 2;
    }

    public final int getIAdapterPosition() {
        return getAdapterPosition() - 2;
    }

    public final int getIOldPosition() {
        return getOldPosition() - 2;
    }

    public final long getIItemId() {
        return getItemId();
    }

    public final int getIItemViewType() {
        return getItemViewType();
    }


    public IViewHolder<M> setText(int viewId, Spanned spanned) {
        TextView textView = getView(viewId);
        textView.setText(spanned, TextView.BufferType.SPANNABLE);
        return this;
    }

    public IViewHolder<M> setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    public IViewHolder<M> setText(int viewId, int resId) {
        TextView textView = getView(viewId);
        textView.setText(resId);
        return this;
    }

    public IViewHolder<M> setTextColor(int viewId, int color) {
        TextView textView = getView(viewId);
        textView.setTextColor(color);
        return this;
    }

    public IViewHolder<M> setTextResource(int viewId, int resId) {
        TextView textView = getView(viewId);
        textView.setText(resId);
        return this;
    }

    public IViewHolder<M> setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public IViewHolder<M> setBackgroundResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    public IViewHolder<M> setImageLoder(int viewId, String path, Context context) {
        ImageView circleImageView = getView(viewId);
        ImageLoaderUtils.display(context, circleImageView, path);
        return this;
    }

    public IViewHolder<M> setSquareAvatar(int viewId, String path, String name, Context context) {
        ImageView avatar = getView(viewId);
        ImageLoaderUtils.displayRoundedCornersAvatar(context, avatar, path
                , PlaceHolder.createDrawable(mContext, name));
        return this;
    }

    public IViewHolder<M> setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public IViewHolder<M> setChecked(int viewId, boolean checked) {
        CheckBox checkBox = getView(viewId);
        checkBox.setChecked(checked);
        return this;
    }


    public IViewHolder<M> setCheckable(int viewId, boolean checkable) {
        View view = getView(viewId);
        view.setClickable(checkable);
        return this;
    }


    /**
     * 关于事件监听
     */
    public IViewHolder<M> setOnClickListener(int viewId, View.OnClickListener listener) {

        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public IViewHolder<M> setOnClickListener(View view, View.OnClickListener listener) {
        view.setOnClickListener(listener);
        return this;
    }


    public IViewHolder<M> setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public IViewHolder<M> setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }
}
