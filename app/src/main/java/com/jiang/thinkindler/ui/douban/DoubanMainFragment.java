package com.jiang.thinkindler.ui.douban;

/**
 * Created by jiang on 2017/4/14.
 */

//public class DoubanMainFragment extends BaseFragment<DoubanMainPresenter>
//        implements DoubanMainContract.View {
//
//    @BindView(R.id.rv_book)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.msl_book)
//    MultiStatusLayout mMultiStatusLayout;
//
//    @BindView(R.id.edt_search)
//    protected AutoCompleteTextView edtSearch;
//
//    private List<BookBean> datas = new ArrayList<>();
//    private String[] historys;
//
//    protected VBookAdapter mAdapter;
//
//    protected int mStatus;
//
//    // The Idling Resource which will be null in production.
//    @Nullable
//    private SimpleIdlingResource mIdlingResource;
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.frag_main_douban;
//    }
//
//    @Override
//    protected void init(View view) {
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        mAdapter = new VBookAdapter(datas);
//        recyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(itemClickListener);
//
//
//        edtSearch.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
//                    KeyBoardUtil.closeKeybord(edtSearch, mContext);
//
//                    if (mIdlingResource != null) {
//                        mIdlingResource.setIdleState(false);
//                    }
//
//                    mPresenter.doSearch(getSearchContent(), 0);
//                }
//                return false;
//            }
//        });
//
//        HistoryUtil.loadAll()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String[]>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String[] strings) {
//                        historys = strings;
//
//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
//                                R.layout.item_search_history, historys);
//
//                        edtSearch.setAdapter(adapter);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
//        mMultiStatusLayout.setRetryListenter(() -> mPresenter.doSearch(getSearchContent(), 0));
//
//    }
//
//    @Override
//    protected void initInjector(AppComponent appComponent) {
//        DaggerDoubanComponent.builder()
//                .appComponent(appComponent)
//                .doubanHttpModule(new DoubanHttpModule())
//                .doubanMainModule(new DoubanMainModule(this))
//                .build()
//                .inject(this);
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        setHasOptionsMenu(true);
////        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color
//// .colorPrimary));
//    }
//
//
//    BaseDelegateAdapter.OnRecyclerViewItemClickListener itemClickListener = (view, position) -> {
//        ToastUtil.showShort("position: " + position);
//        BookDetailActivity.startAction((BaseActivity) mContext, datas.get(position).getId());
//    };
//
//    @Override
//    public void returnDatas(boolean isRefresh, List<BookBean> books) {
//
//        if (mIdlingResource != null) {
//            mIdlingResource.setIdleState(true);
//        }
//
//        if (isRefresh) {
//            mAdapter.replaceAll(books);
//        } else {
//            mAdapter.addList(books);
//        }
//
//    }
//
//    @Override
//    public String getSearchContent() {
//        return edtSearch.getText().toString();
//    }
//
//    /**
//     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
//     */
//    @VisibleForTesting
//    @NonNull
//    public IdlingResource getIdlingResource() {
//        if (mIdlingResource == null) {
//            mIdlingResource = new SimpleIdlingResource();
//        }
//        return mIdlingResource;
//    }
//
//
//    public void setStatus(int status) {
//        if (mStatus == status) {
//            return;
//        }
//        mStatus = status;
//        mMultiStatusLayout.setStatus(mStatus);
//    }
//}
