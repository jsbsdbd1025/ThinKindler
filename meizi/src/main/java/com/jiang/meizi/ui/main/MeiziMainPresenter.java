package com.jiang.meizi.ui.main;

import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import com.jiang.common.rx.RxSchedulers;
import com.jiang.meizi.base.GankObserver;
import com.jiang.meizi.entity.bean.PhotoBean;
import com.jiang.meizi.net.model.GankModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jiang on 2017/7/31.
 */

public class MeiziMainPresenter implements MeiziMainContract.Presenter {

    protected MeiziMainContract.View mView;
    protected GankModel mModel;

    @Inject
    public MeiziMainPresenter(MeiziMainContract.View view, GankModel model) {
        mView = view;
        mModel = model;
        start();
    }

    @Override
    public void start() {
        getMeiziList();
    }

    private void getMeiziList() {

        mView.startProgressDialog();

        mModel.getMeizi(1)
                .compose(RxSchedulers.compose())
                .subscribe(new GankObserver(mView) {
                    @Override
                    protected void _onError(String message) {
                        mView.stopProgressDialog();
                        mView.showShortToast(message);
                    }

                    @Override
                    protected void _onNext(JsonArray data) {

                        List<PhotoBean> datas = gson.fromJson(data, new TypeToken<List<PhotoBean>>() {
                        }.getType());
                        mView.returnDatas(true, datas);

                        mView.stopProgressDialog();
                    }
                });

    }


}
