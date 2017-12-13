package com.jiang.douban.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiang.common.entity.bean.BookBean;
import com.jiang.common.utils.KeyBoardUtil;
import com.jiang.douban.R;
import com.jiang.douban.base.BaseFragment;
import com.jiang.douban.data.db.HistoryUtil;
import com.jiang.douban.injector.component.fragment.DaggerDoubanComponent;
import com.jiang.douban.injector.module.fragment.DoubanMainModule;
import com.jiang.douban.injector.module.http.DoubanHttpModule;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jiang on 2017/7/26.
 */

@Route(path = "/douban/main")
public class DoubanMainFragment extends BaseFragment<DoubanMainPresenter>
        implements DoubanMainContract.View {

    RecyclerView recyclerView;

    AutoCompleteTextView edtSearch;

    private String[] historys;

    protected BookAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.douban_frag_main;
    }

    @Override
    protected void init(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_book);

        edtSearch = (AutoCompleteTextView) view.findViewById(R.id.edt_search);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new BookAdapter(R.layout.item_grid_book);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(itemClickListener);

        edtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    KeyBoardUtil.closeKeybord(edtSearch, mContext);

                    if (mPresenter != null) {
                        mPresenter.doSearch(getSearchContent(), 0);
                    }
                }
                return false;
            }
        });

        HistoryUtil.loadAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String[] strings) {
                        historys = strings;

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                R.layout.item_search_history, historys);

                        edtSearch.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    protected void initInjector() {
        DaggerDoubanComponent.builder()
                .doubanHttpModule(new DoubanHttpModule())
                .doubanMainModule(new DoubanMainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
//        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color
// .colorPrimary));
    }


    BaseQuickAdapter.OnItemClickListener itemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            ARouter.getInstance().build("/douban/detail")
                    .withString("id", mAdapter.getData().get(position).getId())
                    .navigation();
        }
    };


    @Override
    public void returnDatas(boolean isRefresh, List<BookBean> books) {

        if (isRefresh) {
            mAdapter.setNewData(books);
        } else {
            mAdapter.addData(books);
        }

    }

    @Override
    public String getSearchContent() {
        return edtSearch.getText().toString();
    }

}
