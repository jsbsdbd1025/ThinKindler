package com.jiang.thinkindler.ui.douban.contract;

import com.jiang.common.base.BaseModel;
import com.jiang.common.base.BaseView;
import com.jiang.thinkindler.base.BasePresenter;
import com.jiang.thinkindler.entity.bean.BookBean;

import java.util.List;

/**
 * Created by jiang on 2017/4/14.
 */

public interface DoubanMainContract {

    interface view extends BaseView {
        void returnDatas(List<BookBean> books);
    }

    abstract class presenter extends BasePresenter {
        public abstract void doSearch(String content);
    }

}
