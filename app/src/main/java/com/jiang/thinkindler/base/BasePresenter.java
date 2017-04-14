package com.jiang.thinkindler.base;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

public abstract class BasePresenter {

    protected Gson gson = new Gson();


    // TODO: 2017/3/11  数据解析统一处理方法

//    protected void handleFailMsg(String error) {
//        mView.stopLoading();
//        if (error == BaseApplication.getAppResources().getString(R.string.error_network_time_out)) {
////            mView.showError(PlaceHolderType.NONETWORK);
//        } else if (error == BaseApplication.getAppResources().getString(R.string.error_server)) {
////            mView.showError(PlaceHolderType.SERVER_ERROR);
//        } else {
//            mView.showMeaasge(error);
//        }
//    }

//    public boolean hasMore(PageBean pageBean) {
//        if (pageBean != null && pageBean.getPageNum() != 0)
//            return pageBean.hasNextPage();
//        return false;
//    }

}
