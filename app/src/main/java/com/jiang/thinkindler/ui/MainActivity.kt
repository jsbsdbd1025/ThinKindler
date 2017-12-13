package com.jiang.thinkindler.ui

import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.util.ArrayMap
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.alibaba.android.arouter.launcher.ARouter
import com.jiang.thinkindler.R
import com.jiang.thinkindler.base.BaseActivity
import org.jetbrains.anko.find


class MainActivity : BaseActivity() {

    private val navigationView by lazy { find<NavigationView>(R.id.nav_main) }

    private val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private val drawer by lazy { find<DrawerLayout>(R.id.drawer_layout) }

    private var fragments = ArrayMap<String, Fragment>()

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

        navigationView.setNavigationItemSelectedListener({
            when (it.itemId) {
                R.id.nav_douban -> displayFragmentByURL("/douban/main")
                R.id.nav_meizi -> displayFragmentByURL("/meizi/main")
                R.id.nav_bilibili -> displayFragmentByURL("/bilibili/main")
                else -> {
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        })

        //默认首页
        navigationView.post({
            navigationView.menu.findItem(R.id.nav_douban)?.isChecked = true
            displayFragmentByURL("/douban/main")
        })

    }

    override fun initInjector() {

    }

    private fun displayFragmentByURL(url: String) {

        val transaction = supportFragmentManager.beginTransaction()
        if (fragments[url] == null) {
            val fragment = (ARouter.getInstance().build(url).navigation() as Fragment?) ?: return
            fragments.put(url, fragment)
            transaction.add(R.id.ly_main_container, fragments[url])
        }

        for (entry in fragments.entries) {
            if (entry.key == url) {
                transaction.show(entry.value)
            } else {
                transaction.hide(entry.value)
            }
        }

        transaction.commit()

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


