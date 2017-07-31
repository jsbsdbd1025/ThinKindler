package com.jiang.meizi.net.model;

import com.jiang.common.net.BaseModel;
import com.jiang.meizi.entity.bean.ResponseBean;
import com.jiang.meizi.net.service.GankService;

import io.reactivex.Observable;


/**
 * Created by jiang on 2017/5/20.
 */

public class GankModel extends BaseModel {

    private static final int DEFAULT_SIZE = 10;
    private GankService mService;

    public GankModel(GankService gankService) {
        this.mService = gankService;
    }

    public Observable<ResponseBean> getMeizi(int pageNum) {
        return mService.getMeizi(DEFAULT_SIZE, pageNum);
    }

}
