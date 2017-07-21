package com.jiang.common.widget.multistatuslayout.widget.retry;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.jiang.common.R;
import com.jiang.common.widget.multistatuslayout.MultiStatusLayout;
import com.jiang.common.widget.multistatuslayout.OnRetryListener;

/**
 * Created by jiang on 2017/7/15.
 */

public class EasyRetryLayout extends MultiStatusLayout.RetryLayout {

    private Button btnRetry;

    private OnRetryListener mListner;

    public EasyRetryLayout(Context context) {
        super(context);
    }

    public EasyRetryLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EasyRetryLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initRetryView() {
        btnRetry = (Button) findViewById(R.id.btn_retry);
    }

    @Override
    protected void initRetryListener() {
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListner != null) {
                    mListner.onRetry();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_retry;
    }

    public void setRetryListener(OnRetryListener listener) {
        if (listener != null) {
            mListner = listener;
        }
    }

}