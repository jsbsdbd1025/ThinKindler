package com.jiang.thinkindler.ui

import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.jiang.common.base.CommonFragment
import com.jiang.douban.ui.main.DoubanMainFragment
import com.jiang.meizi.ui.main.MeiziMainFragment
import com.jiang.thinkindler.R
import com.jiang.thinkindler.base.BaseActivity
import org.jetbrains.anko.find

class MainActivity : BaseActivity() {

    private lateinit var doubanFragment: DoubanMainFragment
    private lateinit var meiziFragment: MeiziMainFragment

    private lateinit var mFragments: Array<CommonFragment?>

    private val navigationView by lazy { find<NavigationView>(R.id.nav_main) }

//    internal var onNavigationItemSelectedListener = { item ->
//        when (item.getItemId()) {
//            R.id.nav_douban -> displayFragmentByIndex(0)
//            R.id.nav_meizi -> displayFragmentByIndex(1)
//            else -> {
//            }
//        }
//        true
//    }

    override fun getLayoutId(): Int {
        return R.layout.act_main
    }

    override fun init() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer?.addDrawerListener(toggle)
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

        //        ARouter.getInstance().build("/douban/detail").navigation();

//        navigationView!!.setNavigationItemSelectedListener(onNavigationItemSelectedListener)

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

}


