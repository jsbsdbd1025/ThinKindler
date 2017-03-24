package com.jiang.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.common.utils.ToastUtil;
import com.jiang.common.widget.LoadingDialog;

public abstract class CommonFragment extends Fragment {

    protected View rootView;
    protected Context mContext = null;

    protected String TAG = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TAG = CommonFragment.class.getSimpleName();
        this.mContext = inflater.getContext();
        return rootView;
    }

    public void update() {
    }

    /**
     * 开启加载进度条
     */
    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(getActivity());
    }

    /**
     * 开启加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoadingDialog(getActivity(), msg, true);
    }

    /**
     * 停止加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }


    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUtil.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUtil.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUtil.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUtil.showLong(text);
    }


    public void showToastWithImg(String text, int res) {
        ToastUtil.showToastWithImg(text, res);
    }

    /**
     * 带图片的toast
     *
     * @param text 操作成功提示
     */
    public void showToastWithImgAndSuc(String text) {
        ToastUtil.showToastWithImgAndSuc(text);
    }

}
