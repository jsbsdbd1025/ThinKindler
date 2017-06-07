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

import butterknife.BindView;

/**
 * Created by jiang on 2017/4/14.
 */

public class DoubanMainFragment extends BaseFragment<DoubanMainPresenter>
        implements DoubanMainContract.View {

    @BindView(R.id.rv_book)
    RecyclerView recyclerView;

    @BindView(R.id.edt_search)
    protected AutoCompleteTextView edtSearch;
    private String[] historys;

    protected BookAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.frag_main_douban;
    }

    @Override
    protected void init(View view) {

//        VirtualLayoutManager layoutManager = new VirtualLayoutManager(mContext);
//
//        recyclerView.setLayoutManager(layoutManager);
//
//        final List<LayoutHelper> helpers = new LinkedList<>();
//
//        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
//        helpers.add(gridLayoutHelper);
//        layoutManager.setLayoutHelpers(helpers);
//
//        mAdapter = new VBookAdapter(layoutManager, new ArrayList<>());
//
//        recyclerView.setAdapter(mAdapter);


        mAdapter = new BookAdapter(new ArrayList<>());

        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setAdapter(mAdapter);

        recyclerView.setNestedScrollingEnabled(true);
//        mAdapter.setOnItemClickListener(itemClickListener);

        edtSearch.setOnKeyListener((v, keyCode, event) -> {
            // 修改回车键功能
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                KeyBoardUtil.closeKeybord(edtSearch, mContext);
                mPresenter.doSearch(getSearchContent());
            }
            return false;
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
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
//        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    BaseRecyclerAdapter.OnRecyclerViewItemClickListener itemClickListener = (view, position) -> {

    };

    private String getSearchContent() {
        return edtSearch.getText().toString();
    }

    @Override
    public void returnDatas(List<BookBean> books) {
        mAdapter.clear();
        mAdapter.addList(books);
    }


    static class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View itemView) {
            super(itemView);
        }
    }
}
