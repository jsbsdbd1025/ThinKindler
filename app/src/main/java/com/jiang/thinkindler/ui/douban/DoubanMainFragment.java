package com.jiang.thinkindler.ui.douban;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.jiang.common.base.irecyclerview.IRecyclerView;
import com.jiang.common.utils.KeyBoardUtil;
import com.jiang.common.utils.ToastUtil;
import com.jiang.thinkindler.R;
import com.jiang.thinkindler.base.BaseActivity;
import com.jiang.thinkindler.base.BaseFragment;
import com.jiang.thinkindler.base.recyclerview.BaseDelegateAdapter;
import com.jiang.thinkindler.data.db.HistoryUtil;
import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.injector.component.fragment.DaggerDoubanComponent;
import com.jiang.thinkindler.injector.module.fragment.DoubanMainModule;
import com.jiang.thinkindler.injector.module.http.DoubanHttpModule;
import com.jiang.thinkindler.ui.douban.adapter.VBookAdapter;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;
import com.jiang.thinkindler.ui.douban.presenter.DoubanMainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jiang on 2017/4/14.
 */

public class DoubanMainFragment extends BaseFragment<DoubanMainPresenter>
        implements DoubanMainContract.View {

    @BindView(R.id.rv_book)
    IRecyclerView recyclerView;

    @BindView(R.id.edt_search)
    protected AutoCompleteTextView edtSearch;

    private List<BookBean> datas = new ArrayList<>();
    private String[] historys;

    protected VBookAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.frag_main_douban;
    }

    @Override
    protected void init(View view) {

//        VirtualLayoutManager manager = new VirtualLayoutManager(mContext);
//        recyclerView.setLayoutManager(manager);
//        DelegateAdapter delegateAdapter = new DelegateAdapter(manager, true);
//
//        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
//        gridLayoutHelper.setAutoExpand(false);
//        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (position == 0)
//                    return 3;
//                if (position == 1)
//                    return 0;
//                return 1;
//            }
//        });

//        mAdapter = new VBookAdapter(new ArrayList<>(), gridLayoutHelper);
//        delegateAdapter.addAdapter(mAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new VBookAdapter(datas);
        recyclerView.setIAdapter(mAdapter);
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
                .doubanMainModule(new DoubanMainModule(this, recyclerView))
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
        ToastUtil.showShort("position: " + position);
        BookDetailActivity.startAction((BaseActivity) mContext, datas.get(position).getId());
    };

    @Override
    public String getSearchContent() {
        return edtSearch.getText().toString();
    }


    @Override
    public void returnDatas(List<BookBean> books) {
        mAdapter.addList(books);
    }

}
