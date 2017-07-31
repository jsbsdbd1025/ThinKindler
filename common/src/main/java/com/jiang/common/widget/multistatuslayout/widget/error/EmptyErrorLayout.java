package com.jiang.common.widget.multistatuslayout.widget.error;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.jiang.common.R;
import com.jiang.common.widget.multistatuslayout.ErrorType;
import com.jiang.common.widget.multistatuslayout.MultiStatusLayout;

/**
 * Created by jiang on 2017/7/17.
 */

public class EmptyErrorLayout extends MultiStatusLayout.ErrorLayout {

    public EmptyErrorLayout(Context context) {
        super(context);
    }

    public EmptyErrorLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyErrorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initErrorView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_custom_empty;
    }

    @Override
    protected void setErrorType(ErrorType type) {

    }
}
