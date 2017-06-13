package com.jiang.thinkindler.ui.douban;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiang.common.utils.imageloader.ImageLoaderUtils;
import com.jiang.thinkindler.R;
import com.jiang.thinkindler.base.BaseActivity;
import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.injector.component.acvitity.DaggerBookDetailComponent;
import com.jiang.thinkindler.injector.module.activity.BookDetailModule;
import com.jiang.thinkindler.injector.module.http.DoubanHttpModule;
import com.jiang.thinkindler.ui.douban.contract.BookDetailContract;
import com.jiang.thinkindler.ui.douban.presenter.BookDetailPresenter;

import butterknife.BindView;

/**
 * Created by jiang on 2017/5/27.
 */

public class BookDetailActivity extends BaseActivity<BookDetailPresenter>
        implements BookDetailContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.text)
    TextView textView;

    private String id;

    public static void startAction(BaseActivity activity, String id) {
        Intent intent = new Intent(activity, BookDetailActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_book_detail;
    }

    @Override
    protected void init() {
        id = getIntent().getStringExtra("id");
        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_arrow_left_white_48dp);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void initInjector() {
        DaggerBookDetailComponent.builder()
                .bookDetailModule(new BookDetailModule(this))
                .doubanHttpModule(new DoubanHttpModule())
                .build().inject(this);
    }

    @Override
    public void setupView(BookBean book) {
        ImageLoaderUtils.display(this, imageView, book.getImages().getSmall());
        toolbar.setTitle(book.getTitle());
        textView.setText(book.getAuthor_intro());
    }

    @Override
    public String getId() {
        return id;
    }
}
