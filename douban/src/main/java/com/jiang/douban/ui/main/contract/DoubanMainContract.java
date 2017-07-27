package com.jiang.douban.ui.main.contract;

import com.jiang.common.entity.bean.BookBean;
import com.jiang.douban.base.BasePresenter;
import com.jiang.douban.base.BaseView;

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
