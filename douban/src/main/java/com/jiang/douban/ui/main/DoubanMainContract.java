package com.jiang.douban.ui.main;

import com.jiang.common.base.BasePresenter;
import com.jiang.common.base.BaseView;
import com.jiang.common.entity.bean.BookBean;

import java.util.List;

/**
 * Created by jiang on 2017/4/14.
 */

public interface DoubanMainContract {

    interface View extends BaseView {

        void returnDatas(boolean isRefresh, List<BookBean> books);

        String getSearchContent();
    }

    interface Presenter extends BasePresenter {
        void doSearch(String content, int start);
    }

}
