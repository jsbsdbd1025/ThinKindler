package com.jiang.thinkindler.ui.douban;

/**
 * Created by jiang on 2017/5/27.
 */

//public class BookDetailActivity extends BaseActivity<BookDetailPresenter>
//        implements BookDetailContract.View {
//
//    @BindView(R.id.msl_book_detail)
//    MultiStatusLayout mMultiStatusLayout;
//
//    @BindView(R.id.sv_book_detail)
//    NestedScrollView mScrollView;
//
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//
//    @BindView(R.id.toolbar_title)
//    TextView tvToolTitle;
//
//    @BindView(R.id.img_detail_cover)
//    ImageView imgCover;
//    @BindView(R.id.tv_detail_title)
//    TextView tvTitle;
//    @BindView(R.id.tv_detail_subtitle)
//    TextView tvSubtitle;
//    @BindView(R.id.tv_detail_publisher)
//    TextView tvPublisher;
//    @BindView(R.id.tv_detail_pubdate)
//    TextView tvPubdate;
//
//    @BindView(R.id.tv_detail_author)
//    TextView tvAuthor;
//    @BindView(R.id.tv_detail_author_intro)
//    TextView tvAuthorIntro;
//    @BindView(R.id.tv_detail_desc)
//    TextView tvDesc;
//    @BindView(R.id.tv_detail_catalog)
//    TextView tvCatalog;
//
//    private String id;
//
//    public static void startAction(BaseActivity activity, String id) {
//        Intent intent = new Intent(activity, BookDetailActivity.class);
//        intent.putExtra("id", id);
//        activity.startActivity(intent);
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.act_book_detail;
//    }
//
//    @Override
//    protected void init() {
//
//
//        id = getIntent().getStringExtra("id");
//
//        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_arrow_left_white_48dp);
//        toolbar.setNavigationOnClickListener(v -> onBackPressed());
//
//
//        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                float alpha = getAlpha();
//
//                tvToolTitle.setAlpha(alpha);
//            }
//        });
//    }
//
//
//    private float getAlpha() {
//        int[] location = new int[2];
//        tvTitle.getLocationOnScreen(location);
//
//        if (location[1] <= 0) {
//            return 1;
//        }
//
//        float diff = location[1] - (toolbar.getHeight() + DisplayUtil.getStatusBarHeight(this));
//
//        if (diff <= 0) {
//
//            float alpha = -diff / location[1];
//
//            if (alpha > 1) {
//                alpha = 1;
//            }
//
//            return alpha;
//        }
//        return 0;
//    }
//
//    @Override
//    protected void initInjector(AppComponent appComponent) {
//        DaggerBookDetailComponent.builder()
//                .appComponent(appComponent)
//                .bookDetailModule(new BookDetailModule(this))
//                .doubanHttpModule(new DoubanHttpModule())
//                .build().inject(this);
//    }
//
//    @Override
//    public void setupView(BookBean book) {
//
//        ImageLoaderUtil.getInstance().display(imgCover, book.getImages().getLarge(), this, null);
//        tvToolTitle.setText(book.getTitle());
//        tvToolTitle.setAlpha(0);
//        tvTitle.setText(book.getTitle());
//        tvSubtitle.setText(book.getSubtitle());
//        tvPublisher.setText("出版社:" + book.getPublisher());
//        tvPubdate.setText("出版时间:" + book.getPubdate());
//        if (book.getAuthor() != null && book.getAuthor().size() > 0) {
//            tvAuthor.setText(book.getAuthor().get(0));
//            tvAuthorIntro.setText(book.getAuthor_intro());
//        }
//        tvDesc.setText(book.getSummary());
//        tvCatalog.setText(book.getCatalog());
//    }
//
//    @Override
//    public String getId() {
////        return id;
//        return "1873231";
//    }
//
//    public void setStatus(int status) {
//        if (mStatus == status) {
//            return;
//        }
//        mStatus = status;
//        mMultiStatusLayout.setStatus(mStatus);
//    }
//
//}
