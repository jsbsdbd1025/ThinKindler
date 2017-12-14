package com.jiang.meizi.ui.main;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewTreeObserver;

import com.alexvasilkov.gestures.animation.ViewPosition;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiang.common.base.CommonActivity;
import com.jiang.meizi.R;
import com.jiang.meizi.base.BaseFragment;
import com.jiang.meizi.entity.bean.PhotoBean;
import com.jiang.meizi.entity.event.ImageStatusEvent;
import com.jiang.meizi.entity.event.ViewPositionEvent;
import com.jiang.meizi.injector.component.fragment.DaggerMeiziComponent;
import com.jiang.meizi.injector.module.fragment.MeiziMainModule;
import com.jiang.meizi.injector.module.http.GankHttpModule;
import com.jiang.meizi.ui.detail.MeiziHDActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


/**
 * Created by jiang on 2017/7/26.
 */
@Route(path = "/meizi/main")
public class MeiziMainFragment extends BaseFragment<MeiziMainPresenter>
        implements MeiziMainContract.View {

    private static final int SPAN_COUNT = 2;

    RecyclerView recyclerView;

    private MeiziAdapter mAdapter;


    private int curPosition = -1;

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
        mAdapter.bindToRecyclerView(recyclerView);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                curPosition = position;
                ViewPosition viewPosition = ViewPosition.from(mAdapter.getViewByPosition(curPosition, R.id.image));

                MeiziHDActivity.startAction((CommonActivity) mContext, viewPosition,
                        mAdapter.getData().get(curPosition).getUrl());
            }
        });

        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                if (curPosition == -1)
                    return;
                ViewPosition position = ViewPosition.from(mAdapter.getViewByPosition(curPosition, R.id.image));

                EventBus.getDefault().post(new ViewPositionEvent(position));

            }
        });
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showImage(ImageStatusEvent event) {
        // Fullscreen activity requested to show or hide original image
        mMultiStatusLayout.setVisibility(event.isVisiable() ? View.VISIBLE : View.INVISIBLE);
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}