package com.jiang.thinkindler.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Window;

import com.jiang.common.base.CommonActivity;
import com.jiang.common.utils.AppManager;
import com.jiang.thinkindler.app.BaseApplication;
import com.jiang.thinkindler.injector.component.ActivityComponent;
import com.jiang.thinkindler.injector.component.DaggerActivityComponent;
import com.jiang.thinkindler.injector.module.ActivityModule;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.RxActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

public abstract class BaseActivity<T extends BasePresenter> extends CommonActivity {
    @Inject
    public T mPresenter;
    public Context mContext;
    private Unbinder unbinder;
    protected String TAG = null;

    private CompositeDisposable disposables2Stop; //管理stop取消订阅者
    private CompositeDisposable disposables2Destroy;//管理Destroy取消订阅者

    private ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (disposables2Destroy != null) {
            throw new IllegalStateException("onCreate called multiple times");
        }
        disposables2Destroy = new CompositeDisposable();
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        if (unbinder == null)
            unbinder = ButterKnife.bind(this);

        mContext = this;

        activityComponent = DaggerActivityComponent.builder()
                .appComponent(((BaseApplication) getApplication()).getAppComponent())
                .activityModule(new ActivityModule(this)).build();



        //init()中只进行初始化动作
        init();

        initInjector();
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

    private void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        // 默认着色状态栏
//        SetStatusBarColor();
//        SetTranslanteBar();
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
