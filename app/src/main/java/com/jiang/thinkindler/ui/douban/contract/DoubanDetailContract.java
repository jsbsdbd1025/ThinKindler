package com.jiang.thinkindler.ui.douban.contract;

import com.jiang.thinkindler.base.BasePresenter;
import com.jiang.thinkindler.base.BaseView;
import com.jiang.thinkindler.entity.bean.BookBean;

import java.util.List;

/**
 * Created by jiang on 2017/4/14.
 */

public interface DoubanDetailContract {

    interface View extends BaseView {
        void setupView(List<BookBean> books);
    }

    interface Presenter extends BasePresenter {
        void getDetail();
    }
}
