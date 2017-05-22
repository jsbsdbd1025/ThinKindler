package com.jiang.thinkindler.ui.douban;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.jiang.common.utils.KeyBoardUtil;
import com.jiang.thinkindler.R;
import com.jiang.thinkindler.base.BaseFragment;
import com.jiang.thinkindler.base.recyclerview.BaseRecyclerAdapter;
import com.jiang.thinkindler.data.db.HistoryUtil;
import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.injector.component.fragment.DaggerDoubanComponent;
import com.jiang.thinkindler.injector.module.fragment.DoubanMainModule;
import com.jiang.thinkindler.injector.module.http.DoubanHttpModule;
import com.jiang.thinkindler.ui.douban.adapter.BookAdapter;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;
import com.jiang.thinkindler.ui.douban.presenter.DoubanMainPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiang on 2017/4/14.
 */

public class DoubanMainFragment extends BaseFragment<DoubanMainPresenter>
        implements DoubanMainContract.View {

    @BindView(R.id.rv_book)
    RecyclerView recyclerView;

    private List<BookBean> datas = new ArrayList<>();

    @BindView(R.id.edt_search)
    protected AutoCompleteTextView edtSearch;
    private String[] historys;

    @Inject
    protected BookAdapter mAdapter;

    Disposable mDisposable;

    @Override
    public int getLayoutId() {
        return R.layout.frag_main_douban;
    }

    @Override
    protected void init(View view) {

        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));

        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(itemClickListener);

        edtSearch.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 修改回车键功能
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    KeyBoardUtil.closeKeybord(edtSearch, mContext);
                    mPresenter.doSearch(getSearchContent());
                }
                return false;
            }
        });

        historys = HistoryUtil.loadAll();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                R.layout.item_search_history, historys);

        edtSearch.setAdapter(adapter);
    }

    @Override
    protected void initInjector() {
        DaggerDoubanComponent.builder()
                .doubanHttpModule(new DoubanHttpModule())
                .doubanMainModule(new DoubanMainModule(this))
                .build()
                .inject(this);

        mPresenter.subscribe();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
//        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorPrimary));
    }


    BaseRecyclerAdapter.OnRecyclerViewItemClickListener itemClickListener = new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

        }
    };

    private String getSearchContent() {
        return edtSearch.getText().toString();
    }

    @Override
    public void returnDatas(List<BookBean> books) {
        mAdapter.addList(books);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null)
            mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null)
            mPresenter.unsubscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            //取消订阅，释放内存
            mDisposable.dispose();
            mDisposable = null;
        }
    }
}
