package com.jiang.common.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiang.common.R;

/**
 * Created by jiang on 2017/2/28.
 */

public class ToolBarBuilder {
    private Toolbar toolbar;
    private TextView tvTitle;
    private Context mContext;
    private LinearLayout viewgroup;

    private final static int TITLE_TXT_SEZE = 16;

    public ToolBarBuilder(Activity context) {
        this.mContext = context;
        toolbar = (Toolbar) context.findViewById(R.id.toolbar);
        init();
    }

    public ToolBarBuilder(View view) {
        this.mContext = view.getContext();
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        init();
    }

    private void init() {
        toolbar.setTitle("");
        tvTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tvTitle.setVisibility(View.GONE);
        viewgroup = (LinearLayout) toolbar.findViewById(R.id.toolbar_viewgroup);
        viewgroup.setVisibility(View.GONE);
    }

    public ToolBarBuilder setTitle(@StringRes int resId) {
        setTitle(mContext.getText(resId));
        return this;
    }

    public ToolBarBuilder setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
            viewgroup.setVisibility(View.GONE);
        }
        return this;
    }

    public ToolBarBuilder setView(View view) {
        if (null != view) {
            viewgroup.addView(view);
            tvTitle.setVisibility(View.GONE);
            viewgroup.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public ToolBarBuilder inflateMenu(@MenuRes int resId) {
        toolbar.inflateMenu(resId);
        return this;
    }

    public ToolBarBuilder setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener listener) {
        toolbar.setOnMenuItemClickListener(listener);
        return this;
    }


    public ToolBarBuilder setNavigationIcon(@DrawableRes int resId) {
        setNavigationIcon(AppCompatResources.getDrawable(mContext, resId));
        return this;
    }

    public ToolBarBuilder setNavigationIcon(@Nullable Drawable icon) {
        toolbar.setNavigationIcon(icon);
        return this;
    }

    public ToolBarBuilder setNavigationOnClickListener(View.OnClickListener listener) {
        toolbar.setNavigationOnClickListener(listener);
        return this;
    }

    public Toolbar getRootView() {
        return toolbar;
    }

}
