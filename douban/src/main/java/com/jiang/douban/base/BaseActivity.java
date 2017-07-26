package com.jiang.douban.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jiang.common.base.CommonActivity;

public abstract class BaseActivity extends CommonActivity {

    public Context mContext;

    protected String TAG = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        mContext = this;

        //init()中只进行初始化动作
        init();

        initInjector();

        TAG = getClass().getSimpleName();

    }

    /*********************
     * 子类实现
     *****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //初始化view
    protected abstract void init();


    // dagger 注入
    protected abstract void initInjector();

}
