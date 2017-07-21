package com.jiang.common.widget.multistatuslayout.widget.error;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiang.common.R;
import com.jiang.common.widget.multistatuslayout.ErrorType;
import com.jiang.common.widget.multistatuslayout.MultiStatusLayout;


/**
 * Created by jiang on 2017/7/15.
 */

public class EasyErrorLayout extends MultiStatusLayout.ErrorLayout {

    private TextView tvError;
    private ImageView imgError;

    public EasyErrorLayout(Context context) {
        this(context, null);
    }

    public EasyErrorLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyErrorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void initErrorView() {

        tvError = (TextView) findViewById(R.id.tv_error);
        imgError = (ImageView) findViewById(R.id.img_error);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_error;
    }

    @Override
    protected void setErrorType(ErrorType type) {
        if (type.getText() == 0) {
            tvError.setText("");
        } else {
            tvError.setText(type.getText());
        }

        imgError.setImageResource(type.getIcon());
    }

}
