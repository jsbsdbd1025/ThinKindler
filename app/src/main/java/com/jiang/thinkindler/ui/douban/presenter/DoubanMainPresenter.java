package com.jiang.thinkindler.ui.douban.presenter;

import com.jiang.thinkindler.data.db.HistoryUtil;
import com.jiang.thinkindler.entity.bean.BookList;
import com.jiang.thinkindler.injector.scope.FragmentScope;
import com.jiang.thinkindler.net.Api;
import com.jiang.thinkindler.net.ApiType;
import com.jiang.thinkindler.ui.douban.DoubanMainFragment;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jiang on 2017/4/14.
 */

@FragmentScope
public class DoubanMainPresenter extends DoubanMainContract.presenter {

    DoubanMainFragment mView;

    @Inject
    public DoubanMainPresenter(DoubanMainFragment fragment) {
        this.mView = fragment;
        doSearch("Android");
    }

    @Override
    public void doSearch(String content) {
        if (content.equals(""))
            return;

        HistoryUtil.saveHistory(content);
        Api.getDefault(ApiType.DOUBAN).search(content, 0)
                .enqueue(new Callback<BookList>() {
                    @Override
                    public void onResponse(Call<BookList> call, Response<BookList> response) {
                        mView.returnDatas(response.body().getBooks());
                    }

                    @Override
                    public void onFailure(Call<BookList> call, Throwable t) {

                    }
                });
    }
}
