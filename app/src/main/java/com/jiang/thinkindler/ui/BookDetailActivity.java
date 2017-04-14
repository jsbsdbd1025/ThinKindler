package com.jiang.thinkindler.ui;

import android.content.Intent;

import com.jiang.common.base.CommonActivity;
import com.jiang.thinkindler.R;
import com.jiang.thinkindler.base.BaseActivity;

/**
 * Created by jiang on 2017/3/24.
 */

public class BookDetailActivity extends BaseActivity {

    public static void startAction(CommonActivity activity) {
        Intent intent = new Intent(activity, BookDetailActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_book_detail;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initInjector() {

    }
}
