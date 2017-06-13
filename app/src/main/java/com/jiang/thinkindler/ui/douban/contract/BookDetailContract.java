package com.jiang.thinkindler.ui.douban.contract;

import com.jiang.thinkindler.base.BasePresenter;
import com.jiang.thinkindler.base.BaseView;
import com.jiang.thinkindler.entity.bean.BookBean;

/**
 * Created by jiang on 2017/4/14.
 */

public interface BookDetailContract {

    interface View extends BaseView {
        void setupView(BookBean books);

        String getId();
    }

    interface Presenter extends BasePresenter {
        void getDetail(String id);
    }
}
