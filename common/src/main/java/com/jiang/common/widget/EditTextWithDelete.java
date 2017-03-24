package com.jiang.common.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.jiang.common.R;


/**
 * Created by Administrator on 2016/11/30.
 */

public class EditTextWithDelete extends EditText {

    private Drawable imgEnable;
    private Context context;

    public EditTextWithDelete(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public EditTextWithDelete(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public EditTextWithDelete(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }


    private void init() {
        imgEnable = context.getResources().getDrawable(R.drawable.ic_clear);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });
    }

    //设置删除图片
    private void setDrawable() {
        if (length() > 0 && hasFocus()) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgEnable, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgEnable != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 50;
            if (rect.contains(eventX, eventY))
                setText("");
        }
        return super.onTouchEvent(event);
    }


    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (hasFocus()) {
            setDrawable();
        } else {
            setCompoundDrawables(null, null, null, null);
        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }
}
