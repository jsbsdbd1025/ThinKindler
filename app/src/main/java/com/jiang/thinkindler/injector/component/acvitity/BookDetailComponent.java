
package com.jiang.thinkindler.injector.component.acvitity;

import com.jiang.thinkindler.injector.module.activity.BookDetailModule;
import com.jiang.thinkindler.injector.module.http.DoubanHttpModule;
import com.jiang.thinkindler.injector.scope.FragmentScope;
import com.jiang.thinkindler.ui.douban.BookDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jiang on 2017/4/14.
 */
@FragmentScope
@Singleton
@Component(modules = {BookDetailModule.class, DoubanHttpModule.class})
public interface BookDetailComponent {
    void inject(BookDetailActivity activity);
}
