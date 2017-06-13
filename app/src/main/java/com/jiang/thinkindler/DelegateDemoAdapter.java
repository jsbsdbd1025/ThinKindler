package com.jiang.thinkindler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.jiang.thinkindler.base.recyclerview.IViewHolder;

/**
 * Created by jiang on 2017/6/8.
 */

public class DelegateDemoAdapter extends DelegateAdapter.Adapter<IViewHolder> {

    private Context context;
    private LayoutHelper layoutHelper;
    private LayoutInflater inflater;


    public DelegateDemoAdapter(Context context, LayoutHelper layoutHelper) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.layoutHelper = layoutHelper;
    }

    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, int position) {
        holder.setData(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return 60;
    }


    public class ViewHolder extends IViewHolder<String> {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(String data) {
            super.setData(data);
            setText(R.id.title, data);
        }
    }
}
