package com.jiang.douban.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.jiang.common.entity.bean.BookBean;
import com.jiang.common.recyclerview.BaseDelegateAdapter;
import com.jiang.common.utils.KeyBoardUtil;
import com.jiang.douban.R;
import com.jiang.douban.R2;
import com.jiang.douban.base.BaseFragment;
import com.jiang.douban.data.db.HistoryUtil;
import com.jiang.douban.injector.component.fragment.DaggerDoubanComponent;
import com.jiang.douban.injector.module.fragment.DoubanMainModule;
import com.jiang.douban.injector.module.http.DoubanHttpModule;
import com.jiang.douban.ui.detail.BookDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jiang on 2017/7/26.
 */

public class DoubanMainFragment extends BaseFragment<DoubanMainPresenter>
        implements DoubanMainContract.View {

    @BindView(R2.id.rv_book)
    RecyclerView recyclerView;

    @BindView(R2.id.edt_search)
    protected AutoCompleteTextView edtSearch;

    private List<BookBean> datas = new ArrayList<>();
    private String[] historys;

    protected VBookAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.douban_frag_main;
    }

    @Override
    protected void init(View view) {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new VBookAdapter(datas);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(itemClickListener);

        edtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    KeyBoardUtil.closeKeybord(edtSearch, mContext);

                    mPresenter.doSearch(getSearchContent(), 0);
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


    BaseDelegateAdapter.OnRecyclerViewItemClickListener itemClickListener = (view, position) -> {
//        ToastUtil.showShort("position: " + position);
        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
        intent.putExtra("id", datas.get(position).getId());
        getActivity().startActivity(intent);
//        BookDetailActivity.startAction((BaseActivity) mContext, datas.get(position).getId());
    };

    @Override
    public void returnDatas(boolean isRefresh, List<BookBean> books) {

        if (isRefresh) {
            mAdapter.replaceAll(books);
        } else {
            mAdapter.addList(books);
        }

    }

    @Override
    public String getSearchContent() {
        return edtSearch.getText().toString();
    }

}
