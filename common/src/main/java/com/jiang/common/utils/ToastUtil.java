package com.jiang.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiang.common.R;
import com.jiang.common.base.CommonApplication;


/**
 * Toast 工具类
 * 解决Toast连续出现要等前一个时间到
 * 再显示下一个的延迟现象
 *
 * @author DB
 */
public class ToastUtil {


    private static Toast mToast;
    private static Toast mImgToast;

    /**
     * @param message
     * @param duration
     * @return
     */
    private static Toast initToast(CharSequence message, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(CommonApplication.getAppContext(), message, duration);
        } else {
            mToast.setText(message);
            mToast.setDuration(duration);
        }
        return mToast;
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        initToast(message, Toast.LENGTH_SHORT).show();
    }


    /**
     * 短时间显示Toast
     *
     * @param strResId
     */
    public static void showShort(int strResId) {
//		Toast.makeText(context, strResId, Toast.LENGTH_SHORT).show();
        initToast(CommonApplication.getAppContext().getResources().getText(strResId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        initToast(message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param strResId
     */
    public static void showLong(int strResId) {
        initToast(CommonApplication.getAppContext().getResources().getText(strResId), Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration) {
        initToast(message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param strResId
     * @param duration
     */
    public static void show(Context context, int strResId, int duration) {
        initToast(context.getResources().getText(strResId), duration).show();
    }


    /**
     * 显示有image的toast
     *
     * @param tvStr
     * @param imageResource
     * @return
     */
    public static Toast showToastWithImg(final String tvStr, final int imageResource) {
        if (mImgToast == null) {
            mImgToast = new Toast(CommonApplication.getAppContext());
        }
        View view = LayoutInflater.from(CommonApplication.getAppContext()).inflate(R.layout.toast_custom, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
        tv.setText(TextUtils.isEmpty(tvStr) ? "" : tvStr);
        ImageView iv = (ImageView) view.findViewById(R.id.toast_custom_iv);
        if (imageResource > 0) {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(imageResource);
        } else {
            iv.setVisibility(View.GONE);
        }
        mImgToast.setView(view);
        mImgToast.setGravity(Gravity.CENTER, 0, 0);
        mImgToast.show();
        return mImgToast;

    }

    public static Toast showToastWithImgAndSuc(final String tvStr) {
        if (mImgToast == null) {
            mImgToast = new Toast(CommonApplication.getAppContext());
        }
        View view = LayoutInflater.from(CommonApplication.getAppContext()).inflate(R.layout.toast_custom_suc, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
        tv.setText(TextUtils.isEmpty(tvStr) ? "" : tvStr);
        mImgToast.setView(view);
        mImgToast.setGravity(Gravity.CENTER, 0, 0);
        mImgToast.show();
        return mImgToast;

    }
}
