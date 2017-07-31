package com.jiang.meizi.ui.main;

import com.jiang.common.base.BasePresenter;
import com.jiang.common.base.BaseView;
import com.jiang.meizi.entity.bean.PhotoBean;

import java.util.List;

/**
 * Created by jiang on 2017/7/31.
 */

public interface MeiziMainContract {
    interface View extends BaseView {

        void returnDatas(boolean isRefresh, List<PhotoBean> photos);

    }

    interface Presenter extends BasePresenter {

    }

}
