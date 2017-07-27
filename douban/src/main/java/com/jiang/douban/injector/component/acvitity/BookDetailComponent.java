
package com.jiang.douban.injector.component.acvitity;

import com.jiang.common.injector.scope.FragmentScope;
import com.jiang.douban.injector.module.activity.BookDetailModule;
import com.jiang.douban.injector.module.http.DoubanHttpModule;
import com.jiang.douban.ui.detail.BookDetailActivity;

import dagger.Component;

/**
 * Created by jiang on 2017/4/14.
 */
@FragmentScope
@Component(modules = {BookDetailModule.class, DoubanHttpModule.class})
public interface BookDetailComponent {
    void inject(BookDetailActivity activity);
}
