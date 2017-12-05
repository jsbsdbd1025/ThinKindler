package com.jiang.media.ui.main;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.common.net.Api;
import com.jiang.common.net.ApiType;
import com.jiang.common.rx.RxSchedulers;
import com.jiang.common.widget.flashview.BannerBean;
import com.jiang.common.widget.flashview.FlashView;
import com.jiang.common.widget.recyclerview.SimpleDividerDecoration;
import com.jiang.media.R;
import com.jiang.media.base.BaseFragment;
import com.jiang.media.entity.BiliBiliBannerBean;
import com.jiang.media.entity.BiliBiliResponse;
import com.jiang.media.net.BiliBiliService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by knowing on 2017/12/5.
 */

public class BiliBiliMainFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private FlashView mFlashView;
    private RankAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.bilibili_frag_main;
    }

    @Override
    protected void init(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_recommend);


        mFlashView = (FlashView) LayoutInflater.from(mContext).inflate(R.layout.layout_recommend_banner, null);

        mFlashView.setOnPageClickListener(position -> {


        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.addItemDecoration(new SimpleDividerDecoration(mContext));


        mAdapter = new RankAdapter(R.layout.item_rank);

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.addHeaderView(mFlashView);

        Api.getApi(ApiType.BILIBILIAPP, BiliBiliService.class)
                .getRecommendedBannerInfo()
                .compose(RxSchedulers.io_main())
                .flatMap(new Function<BiliBiliResponse<List<BiliBiliBannerBean>>, Observable<List<BannerBean>>>() {

                    @Override
                    public Observable<List<BannerBean>> apply(BiliBiliResponse<List<BiliBiliBannerBean>> listBiliBiliResponse) throws Exception {
                        List<BannerBean> bannerList = new ArrayList<>();

                        for (BiliBiliBannerBean biliBannerBean : listBiliBiliResponse.getData()) {
                            BannerBean banner = new BannerBean();
                            banner.setImageUrl(biliBannerBean.getImage());

                            bannerList.add(banner);
                        }
                        return Observable.fromArray(bannerList);
                    }
                }).subscribe(new Consumer<List<BannerBean>>() {
            @Override
            public void accept(List<BannerBean> bannerBeans) throws Exception {
                mFlashView.setDatas(bannerBeans);
            }
        });

        Api.getApi(ApiType.BILIBILIWWW, BiliBiliService.class)
                .getAllareasRanks("all-03.json")
                .map(allareasRankInfo -> allareasRankInfo.getRank().getList())
                .compose(RxSchedulers.io_main())
                .subscribe(listBeans -> {

                    mAdapter.setNewData(listBeans);
                });
    }

    @Override
    protected void initInjector() {

    }


    @Override
    public void onResume() {
        super.onResume();

        if (mFlashView.getLayoutParams() != null) {
            ViewGroup.LayoutParams params = mFlashView.getLayoutParams();
            params.height = mContext.getResources().getDimensionPixelSize(R.dimen.flash_height);
        }
    }
}
