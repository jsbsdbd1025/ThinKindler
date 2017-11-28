package com.jiang.douban.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jiang.common.base.BasePresenter;
import com.jiang.common.base.CommonActivity;
import com.jiang.common.base.CommonApplication;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity<P extends BasePresenter> extends CommonActivity {

    @Inject
    protected P mPresenter;

    public Context mContext;

    protected String TAG = null;

    private CompositeDisposable disposables2Stop; //管理stop取消订阅者
    private CompositeDisposable disposables2Destroy; //管理Destroy取消订阅者

    private CommonApplication mApplication;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (disposables2Destroy != null) {
            throw new IllegalStateException("onCreate called multiple times");
        }
        disposables2Destroy = new CompositeDisposable();
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


    public boolean addRxStop(Disposable disposable) {
        if (disposables2Stop == null) {
            throw new IllegalStateException(
                    "addUtilStop should be called between onStart and onStop");
        }
        disposables2Stop.add(disposable);
        return true;
    }

    public boolean addRxDestroy(Disposable disposable) {
        if (disposables2Destroy == null) {
            throw new IllegalStateException(
                    "addUtilDestroy should be called between onCreate and onDestroy");
        }
        disposables2Destroy.add(disposable);
        return true;
    }

    public void remove(Disposable disposable) {
        if (disposables2Stop == null && disposables2Destroy == null) {
            throw new IllegalStateException("remove should not be called after onDestroy");
        }
        if (disposables2Stop != null) {
            disposables2Stop.remove(disposable);
        }
        if (disposables2Destroy != null) {
            disposables2Destroy.remove(disposable);
        }
    }

    public void onStart() {
        super.onStart();
        if (disposables2Stop != null) {
            throw new IllegalStateException("onStart called multiple times");
        }
        disposables2Stop = new CompositeDisposable();
    }

    public void onStop() {
        super.onStop();
        if (disposables2Stop == null) {
            throw new IllegalStateException("onStop called multiple times or onStart not called");
        }
        disposables2Stop.dispose();
        disposables2Stop = null;
    }

    public void onDestroy() {
        super.onDestroy();
        if (disposables2Destroy == null) {
            throw new IllegalStateException(
                    "onDestroy called multiple times or onCreate not called");
        }
        disposables2Destroy.dispose();
        disposables2Destroy = null;
    }


}
