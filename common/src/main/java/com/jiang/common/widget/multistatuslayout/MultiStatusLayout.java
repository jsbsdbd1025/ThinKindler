package com.jiang.common.widget.multistatuslayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jiang.common.R;


/**
 * Created by jiang on 2017/7/14.
 */

public class MultiStatusLayout extends FrameLayout {


    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_ERROR = 2;
    public static final int STATUS_RETRY = 3;


    private static final int[] STATUS_ENUM =
            {STATUS_NORMAL, STATUS_LOADING, STATUS_ERROR, STATUS_RETRY};

    private int status = STATUS_NORMAL;

    public static final int DEFAULT_LOADING_RESID = R.layout.multi_status_loading;
    public static final int DEFAULT_ERROR_RESID = R.layout.multi_status_error;
    public static final int DEFAULT_RETRY_RESID = R.layout.multi_status_retry;

    ViewGroup contentLayout;
    LoadingLayout loadingLayout;
    ErrorLayout errorLayout;
    RetryLayout retryLayout;

    LayoutInflater layoutInflater;

    ViewStub viewStubLoading;

    ViewStub viewStubError;

    ViewStub viewStubRetry;

    ErrorType errorType = ErrorType.EMPTY;

    private OnRetryListener mRetryListener;

    public MultiStatusLayout(@NonNull Context context) {
        super(context);
        initView(context, null);
    }

    public MultiStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MultiStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {

        layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.multi_status_layout, this);
        contentLayout = (FrameLayout) findViewById(R.id.layout_content);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MultiStatusLayout);

        int loadinglayoutId = ta.getResourceId(R.styleable.MultiStatusLayout_loadingLayout, DEFAULT_LOADING_RESID);

        viewStubLoading = (ViewStub) findViewById(R.id.viewstub_loading);
        viewStubLoading.setLayoutResource(loadinglayoutId);

        int errorlayoutId = ta.getResourceId(R.styleable.MultiStatusLayout_errorLayout, DEFAULT_ERROR_RESID);

        viewStubError = (ViewStub) findViewById(R.id.viewstub_error);
        viewStubError.setLayoutResource(errorlayoutId);


        int retrylayoutId = ta.getResourceId(R.styleable.MultiStatusLayout_retryLayout, DEFAULT_RETRY_RESID);

        viewStubRetry = (ViewStub) findViewById(R.id.viewstub_retry);
        viewStubRetry.setLayoutResource(retrylayoutId);


        status = ta.getInt(R.styleable.MultiStatusLayout_status, STATUS_NORMAL);

        ta.recycle();

        setStatus(status);

    }

//    @Override
//    public void addView(View child, int index, ViewGroup.LayoutParams params) {
//        if (contentLayout == null) {
//            super.addView(child, index, params);
//        } else {
//            if(child instanceof LoadingLayout
//                    || child instanceof ErrorLayout
//                    || child instanceof RetryLayout){
//                super.addView(child, index, params);
//            }else {
//                contentLayout.addView(child, index, params);
//            }
//        }
//    }


    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (contentLayout == null) {
            super.addView(child, index, params);
        } else {
            if(contentLayout.getChildCount() == 0) {
                contentLayout.addView(child,index,params);
            }else{
                super.addView(child, index, params);
            }
        }
    }

    public void setStatus(int status) {

        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.setVisibility(GONE);
        }

        if (this.status == STATUS_LOADING) {
            loadingLayout.onStop();
        }

        this.status = status;

        switch (status) {
            case STATUS_NORMAL:
                showNormal();
                break;
            case STATUS_LOADING:
                showLoading();
                break;
            case STATUS_ERROR:
                showError();
                break;
            case STATUS_RETRY:
                showRetry();
                break;
            default:
                break;
        }
    }

    private void showNormal() {
        contentLayout.setVisibility(VISIBLE);
    }

    private void showLoading() {

        if (loadingLayout == null) {
            loadingLayout = (LoadingLayout) viewStubLoading.inflate();
        } else {
            loadingLayout.setVisibility(VISIBLE);
        }
        loadingLayout.onStart();
    }

    private void showError() {

        if (errorLayout == null) {
            errorLayout = (ErrorLayout) viewStubError.inflate();
        } else {
            errorLayout.setVisibility(VISIBLE);
        }
    }

    private void showRetry() {

        if (retryLayout == null) {
            retryLayout = (RetryLayout) viewStubRetry.inflate();
        } else {
            retryLayout.setVisibility(VISIBLE);
        }

        if (mRetryListener != null) {
            retryLayout.setRetryListener(mRetryListener);
        }
    }

    public void setRetryListenter(OnRetryListener listenter) {

        if (listenter != null) {
            mRetryListener = listenter;
            if (retryLayout != null) {
                retryLayout.setRetryListener(mRetryListener);
            }
        }
    }

    public void setErrorType(ErrorType type) {
        setStatus(STATUS_ERROR);
        this.errorType = type;
        errorLayout.setErrorType(type);
    }

    public static abstract class ErrorLayout extends LinearLayout {

        protected Context context;

        public ErrorLayout(Context context) {
            this(context, null);
        }

        public ErrorLayout(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public ErrorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);

            this.context = context;

            inflate(context, getLayoutId(), this);

            initErrorView();
        }

        protected abstract void initErrorView();

        protected abstract int getLayoutId();

        protected abstract void setErrorType(ErrorType type);
    }

    public static abstract class RetryLayout extends LinearLayout {

        protected Context context;
        private OnRetryListener mListner;

        public RetryLayout(Context context) {
            this(context, null);
        }

        public RetryLayout(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public RetryLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);

            this.context = context;

            inflate(context, getLayoutId(), this);

            initRetryView();

            initRetryListener();
        }

        protected abstract void initRetryView();

        protected abstract void initRetryListener();

        protected abstract int getLayoutId();

        public void setRetryListener(OnRetryListener listener) {
            if (listener != null) {
                mListner = listener;
            }
        }

    }

    public static abstract class LoadingLayout extends LinearLayout {

        protected Context context;
        private OnRetryListener mListner;

        public LoadingLayout(Context context) {
            this(context, null);
        }

        public LoadingLayout(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public LoadingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            this.context = context;
            inflate(context, getLayoutId(), this);

            initLoadingView();

        }

        protected abstract int getLayoutId();

        protected abstract void initLoadingView();

        protected abstract void onStart();

        protected abstract void onPause();

        protected abstract void onStop();

    }
}
