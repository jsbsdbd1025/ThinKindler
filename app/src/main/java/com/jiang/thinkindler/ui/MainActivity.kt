package com.jiang.thinkindler.ui

import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.alibaba.android.arouter.launcher.ARouter
import com.jiang.common.base.CommonFragment
import com.jiang.douban.ui.detail.BookDetailActivity
import com.jiang.douban.ui.main.DoubanMainFragment
import com.jiang.meizi.ui.main.MeiziMainFragment
import com.jiang.thinkindler.R
import com.jiang.thinkindler.base.BaseActivity
import org.jetbrains.anko.find
import android.support.v4.view.GravityCompat


class MainActivity : BaseActivity() {

    private lateinit var doubanFragment: DoubanMainFragment
    private lateinit var meiziFragment: MeiziMainFragment

    private lateinit var mFragments: Array<CommonFragment?>

    private val navigationView by lazy { find<NavigationView>(R.id.nav_main) }

    private val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private val drawer by lazy { find<DrawerLayout>(R.id.drawer_layout) }
    override fun getLayoutId(): Int {
        return R.layout.act_main
    }

    override fun init() {

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        toolbar.inflateMenu(R.menu.menu_clear)

        mFragments = arrayOfNulls<CommonFragment>(2)

        doubanFragment = supportFragmentManager
                .findFragmentById(R.id.frag_main_douban) as DoubanMainFragment
        mFragments[0] = doubanFragment

        meiziFragment = supportFragmentManager
                .findFragmentById(R.id.frag_main_meizi) as MeiziMainFragment
        mFragments[1] = meiziFragment

        displayFragmentByIndex(0)

//        ARouter.getInstance().build("/douban/detail").navigation()

//        startActivity(Intent(this, BookDetailActivity::class.java))
        navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_douban -> displayFragmentByIndex(0)
                R.id.nav_meizi -> displayFragmentByIndex(1)
                else -> {
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        })

    }

    override fun initInjector() {

    }

    private fun displayFragmentByIndex(index: Int) {
        // 通过这个底部容器Item的index能够获取到对应的Fragment，需要将所有的Fragment对号放好（使用集合）
        val ft = supportFragmentManager.beginTransaction()
        for (i in mFragments.indices) {
            if (i == index) {
                ft.show(mFragments[i])
            } else {
                ft.hide(mFragments[i])
            }
        }
        ft.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.action_clear -> showShortToast(R.string.action_clear)
            else -> {
            }
        }
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

    }

}


