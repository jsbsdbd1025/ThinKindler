package com.jiang.meizi.ui.main;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.jiang.common.widget.multistatuslayout.MultiStatusLayout;
import com.jiang.meizi.R;
import com.jiang.meizi.base.BaseFragment;
import com.jiang.meizi.entity.bean.PhotoBean;
import com.jiang.meizi.injector.component.fragment.DaggerMeiziComponent;
import com.jiang.meizi.injector.module.fragment.MeiziMainModule;
import com.jiang.meizi.injector.module.http.GankHttpModule;

import java.util.List;


/**
 * Created by jiang on 2017/7/26.
 */

public class MeiziMainFragment extends BaseFragment<MeiziMainPresenter>
        implements MeiziMainContract.View {

    private static final int SPAN_COUNT = 2;

    RecyclerView recyclerView;

    private MeiziAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.meizi_frag_main;
    }

    @Override
    protected void init(View view) {

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(SPAN_COUNT,
                StaggeredGridLayoutManager.VERTICAL);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_meizi);
        recyclerView.setLayoutManager(manager);

        mAdapter = new MeiziAdapter(R.layout.item_meizi);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initInjector() {

        DaggerMeiziComponent.builder()
                .gankHttpModule(new GankHttpModule())
                .meiziMainModule(new MeiziMainModule(this))
                .build().inject(this);

    }

    @Override
    public void returnDatas(boolean isRefresh, List<PhotoBean> photos) {
        if (isRefresh) {
            mAdapter.setNewData(photos);
        } else {
            mAdapter.addData(photos);
        }

    }
}