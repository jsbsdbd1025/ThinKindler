package com.jiang.thinkindler.ui.live;

import com.jiang.thinkindler.base.BasePresenter;
import com.jiang.thinkindler.base.BaseView;
import com.jiang.thinkindler.entity.bean.BookBean;

import java.util.List;

/**
 * Created by jiang on 2017/5/23.
 */

public interface LiveListContract {

    interface View extends BaseView {
        void returnDatas(List<BookBean> books);
    }

    interface Presenter extends BasePresenter {
        void loadData();
    }
}
