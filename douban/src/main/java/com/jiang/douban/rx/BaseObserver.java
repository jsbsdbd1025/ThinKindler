package com.jiang.douban.rx;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.jiang.common.utils.LogUtils;
import com.jiang.douban.base.BaseView;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.adapter.rxjava2.HttpException;


/**
 * Created by jiang on 2017/5/23.
 */

public abstract class BaseObserver<T> implements Observer<T> {
    private static final String TAG = BaseObserver.class.getName();

    private BaseView mBaseImpl;
    //  Activity 是否在执行onStop()时取消 订阅
    private boolean isAddInStop = false;

    public BaseObserver(BaseView baseImpl) {
        mBaseImpl = baseImpl;
    }

    @Override
    public void onSubscribe(Disposable d) {

        if (mBaseImpl == null) {
            return;
        }
        if (isAddInStop) {    //  在onStop中取消订阅
            mBaseImpl.addRxStop(d);
        } else { //  在onDestroy中取消订阅
            mBaseImpl.addRxDestroy(d);
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.loge(e.getMessage());
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {
    }

    protected abstract void _onNext(T body);

    protected abstract void _onError(String message);

    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                _onError("连接错误");
                break;
            case CONNECT_TIMEOUT:
                _onError("连接超时");
                break;

            case BAD_NETWORK:
                _onError("服务器出错");
                break;

            case PARSE_ERROR:
                _onError("解析数据失败");
                break;
            case UNKNOWN_ERROR:
            default:
                _onError("未知错误");
                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 操作错误
         */
        HANGLE_ERROR,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
