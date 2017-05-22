package com.jiang.thinkindler.ui.douban.contract;

import com.jiang.thinkindler.base.BasePresenter;
import com.jiang.thinkindler.base.BaseView;
import com.jiang.thinkindler.entity.bean.BookBean;

import java.util.List;

/**
 * Created by jiang on 2017/4/14.
 */

public interface DoubanMainContract {

    interface View extends BaseView<Presenter> {
        void returnDatas(List<BookBean> books);
    }

    interface Presenter extends BasePresenter {
        void doSearch(String content);
    }

}
