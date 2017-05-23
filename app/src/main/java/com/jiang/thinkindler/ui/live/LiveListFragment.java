package com.jiang.thinkindler.ui.live;

import android.view.View;

import com.jiang.thinkindler.base.BaseFragment;
import com.jiang.thinkindler.entity.bean.BookBean;

import java.util.List;

/**
 * Created by jiang on 2017/5/23.
 */

public class LiveListFragment extends BaseFragment<LiveLivePresenter>
        implements LiveListContract.View {
    @Override
    public void returnDatas(List<BookBean> books) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void initInjector() {

    }
}
