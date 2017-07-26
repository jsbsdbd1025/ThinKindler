
package com.jiang.douban.injector.component.acvitity;

import com.jiang.common.injector.scope.FragmentScope;
import com.jiang.thinkindler.injector.app.AppComponent;
import com.jiang.thinkindler.injector.module.activity.BookDetailModule;
import com.jiang.thinkindler.injector.module.http.DoubanHttpModule;
import com.jiang.thinkindler.ui.douban.BookDetailActivity;

import dagger.Component;

/**
 * Created by jiang on 2017/4/14.
 */
@FragmentScope
@Component(modules = {BookDetailModule.class, DoubanHttpModule.class}, dependencies = AppComponent.class)
public interface BookDetailComponent {
    void inject(BookDetailActivity activity);
}
