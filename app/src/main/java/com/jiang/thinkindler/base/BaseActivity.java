package com.jiang.thinkindler.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jiang.common.base.CommonActivity;
import com.jiang.common.utils.AppManager;
import com.jiang.thinkindler.app.BaseApplication;
import com.jiang.thinkindler.injector.component.AppComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity<T extends BasePresenter> extends CommonActivity {
    @Inject
    public T mPresenter;
    public Context mContext;
    private Unbinder unbinder;
    protected String TAG = null;
    private CompositeDisposable disposables2Stop; //管理stop取消订阅者
    private CompositeDisposable disposables2Destroy; //管理Destroy取消订阅者

    private BaseApplication mApplication;

    protected int mStatus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (disposables2Destroy != null) {
            throw new IllegalStateException("onCreate called multiple times");
        }
        disposables2Destroy = new CompositeDisposable();
        setContentView(getLayoutId());
        if (unbinder == null) {
            unbinder = ButterKnife.bind(this);
        }
        mContext = this;

        //init()中只进行初始化动作
        init();

        mApplication = (BaseApplication) getApplication();

        initInjector(mApplication.getAppComponent());

        TAG = getClass().getSimpleName();

    }

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

    protected void onStart() {
        super.onStart();
        if (disposables2Stop != null) {
            throw new IllegalStateException("onStart called multiple times");
        }
        disposables2Stop = new CompositeDisposable();
    }

    protected void onStop() {
        super.onStop();
        if (disposables2Stop == null) {
            throw new IllegalStateException("onStop called multiple times or onStart not called");
        }
        disposables2Stop.dispose();
        disposables2Stop = null;
    }

    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        AppManager.getAppManager().removeActivity(this);
        super.onDestroy();
        if (disposables2Destroy == null) {
            throw new IllegalStateException(
                    "onDestroy called multiple times or onCreate not called");
        }
        disposables2Destroy.dispose();
        disposables2Destroy = null;
    }

    /*********************
     * 子类实现
     *****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //初始化view
    protected abstract void init();

    // dagger 注入
    protected abstract void initInjector(AppComponent appComponent);


    public void setStatus(int status) {
        if (mStatus == status) {
            return;
        }
        mStatus = status;
    }


}
