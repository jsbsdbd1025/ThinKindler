package com.jiang.thinkindler.ui;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jiang.thinkindler.R;
import com.jiang.thinkindler.base.BaseActivity;
import com.jiang.thinkindler.ui.douban.DoubanMainFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private DoubanMainFragment doubanFragment;

    @BindView(R.id.nav_main)
    NavigationView navigationView;

    @Override
    public int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    protected void init() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        toolbar.inflateMenu(R.menu.menu_clear);

        doubanFragment = (DoubanMainFragment) getSupportFragmentManager().findFragmentById(R.id.frag_main_douban);
        displayFragmentByIndex(0);

        navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }

    NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.nav_zhihu:
                break;
            default:
                break;
        }
        return false;
    };

    @Override
    protected void initInjector() {
    }

    private void displayFragmentByIndex(int index) {
        // 通过这个底部容器Item的index能够获取到对应的Fragment，需要将所有的Fragment对号放好（使用集合）
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        for (int i = 0; i < mFragments.length; i++) {
//            if (i == index) {
//                ft.show(mFragments[i]);
//            } else {
//                ft.hide(mFragments[i]);
//            }
//        }
        ft.show(doubanFragment);
        ft.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_clear:
                showShortToast(R.string.action_clear);
                break;
            default:
                break;
        }
        return true;
    }


}


