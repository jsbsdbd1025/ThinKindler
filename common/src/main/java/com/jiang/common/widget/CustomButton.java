package com.jiang.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiang.common.R;

/**
 * Created by jiang on 2017/3/3.
 */

public class CustomButton extends FrameLayout {
    private Context mContext;
    private LinearLayout viewGroup;
    private ImageView imageView;
    private TextView textView;
    protected Drawable drawable;
    protected Drawable background;
    protected String string;
    protected int textsize = 16;
    protected int textcolor;

    public CustomButton(Context context) {
        this(context, null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);
        drawable = mTypedArray.getDrawable(R.styleable.CustomButton_src);
        background = mTypedArray.getDrawable(R.styleable.CustomButton_background);
        string = mTypedArray.getString(R.styleable.CustomButton_text);
        textcolor = mTypedArray.getColor(R.styleable.CustomButton_textcolor, getResources().getColor(R.color.colorPrimary));
        init();
        mTypedArray.recycle();
    }


    private void init() {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.layout_custom_button, null);
        viewGroup = (LinearLayout) view.findViewById(R.id.ly_button);
        imageView = (ImageView) view.findViewById(R.id.img_button);
        textView = (TextView) view.findViewById(R.id.tv_button);
        imageView.setImageDrawable(drawable);
        textView.setText(string);
        textView.setTextColor(textcolor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            viewGroup.setBackground(background);
        } else {
            viewGroup.setBackgroundDrawable(background);
        }
        addView(view);
    }


}
