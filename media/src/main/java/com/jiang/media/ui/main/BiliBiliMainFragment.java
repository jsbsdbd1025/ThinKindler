package com.jiang.media.ui.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiang.common.net.Api;
import com.jiang.common.net.ApiType;
import com.jiang.common.rx.RxSchedulers;
import com.jiang.common.widget.flashview.BannerBean;
import com.jiang.common.widget.flashview.FlashView;
import com.jiang.media.R;
import com.jiang.media.base.BaseFragment;
import com.jiang.media.entity.BiliBiliBannerBean;
import com.jiang.media.entity.BiliBiliRecommend;
import com.jiang.media.entity.BiliBiliRecommendBody;
import com.jiang.media.entity.BiliBiliRecommendHead;
import com.jiang.media.entity.BiliBiliResponse;
import com.jiang.media.net.BiliBiliService;
import com.jiang.media.ui.detail.BrowserActivity;
import com.jiang.media.ui.detail.VideoDetailActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by knowing on 2017/12/5.
 */

@Route(path = "/bilibili/main")
public class BiliBiliMainFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private FlashView mFlashView;
//    private RankAdapter mAdapter;

    private RecommentAdapter mAdapter;
    private List<BannerBean> bannerList = new ArrayList<>();

    private List<BiliBiliRecommendHead> mDatas = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.bilibili_frag_main;
    }

    @Override
    protected void init(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_recommend);

        mFlashView = (FlashView) LayoutInflater.from(mContext).inflate(R.layout.layout_recommend_banner, null);

        mFlashView.setOnPageClickListener(position -> {
            Intent intent = new Intent(mContext, BrowserActivity.class);
            intent.putExtra("url", bannerList.get(position).getValue());
            intent.putExtra("title", bannerList.get(position).getTitle());
            startActivity(intent);

        });

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        mAdapter = new RecommentAdapter(R.layout.item_recommend_body, R.layout.item_recomment_head, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.addHeaderView(mFlashView);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, VideoDetailActivity.class);
                startActivity(intent);
            }
        });

        bannerList.clear();
        mDatas.clear();

        Api.getApi(ApiType.BILIBILIAPP, BiliBiliService.class)
                .getRecommendedBannerInfo()
                .map(BiliBiliResponse<List<BiliBiliBannerBean>>::getData)
                .flatMap(new Function<List<BiliBiliBannerBean>, Observable<BiliBiliResponse<List<BiliBiliRecommend>>>>() {

                    @Override
                    public Observable<BiliBiliResponse<List<BiliBiliRecommend>>> apply(List<BiliBiliBannerBean> biliBiliResponse) throws Exception {

                        for (BiliBiliBannerBean biliBannerBean : biliBiliResponse) {
                            BannerBean banner = new BannerBean();
                            banner.setImageUrl(biliBannerBean.getImage());
                            banner.setTitle(biliBannerBean.getTitle());
                            banner.setValue(biliBannerBean.getValue());
                            bannerList.add(banner);
                        }

//                        return Api.getApi(ApiType.BILIBILIWWW, BiliBiliService.class)
//                                .getAllareasRanks("all-03.json");

                        return Api.getApi(ApiType.BILIBILIAPP, BiliBiliService.class).getRecommendedInfo();
                    }
                })
                .compose(RxSchedulers.io_main())
                .map(BiliBiliResponse<List<BiliBiliRecommend>>::getData)
                .subscribe(new Consumer<List<BiliBiliRecommend>>() {
                    @Override
                    public void accept(List<BiliBiliRecommend> biliBiliRecommends) throws Exception {

                        for (BiliBiliRecommend recommend : biliBiliRecommends) {
                            mDatas.add(new BiliBiliRecommendHead(true, recommend.getHead().getTitle()));

                            for (BiliBiliRecommendBody bean : recommend.getBody()) {
                                mDatas.add(new BiliBiliRecommendHead(bean));
                            }
                        }

                        mFlashView.setDatas(bannerList);

                        mAdapter.notifyDataSetChanged();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showShortToast(throwable.getMessage());
                    }
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
