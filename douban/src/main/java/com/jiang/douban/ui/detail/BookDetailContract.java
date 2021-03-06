package com.jiang.douban.ui.detail;

import com.jiang.common.base.BasePresenter;
import com.jiang.common.base.BaseView;
import com.jiang.common.entity.bean.BookBean;

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
