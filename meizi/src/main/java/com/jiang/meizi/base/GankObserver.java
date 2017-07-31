package com.jiang.meizi.base;

import com.google.gson.JsonArray;

import com.jiang.common.base.BaseView;
import com.jiang.common.rx.BaseObserver;
import com.jiang.meizi.entity.bean.ResponseBean;


/**
 * Created by jiang on 2017/5/23.
 */

public abstract class GankObserver extends BaseObserver<ResponseBean> {
    private static final String TAG = GankObserver.class.getName();

    public GankObserver(BaseView baseImpl) {
        super(baseImpl);
    }

    @Override
    public void onNext(ResponseBean tRequestBean) {
        mBaseImpl.stopProgressDialog();
        if (tRequestBean.isError()) {
            _onError("error");
        } else {
            _onNext(tRequestBean.getResults());
        }

    }

    protected abstract void _onNext(JsonArray data);


}
