package com.jiang.thinkindler.ui;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.jiang.common.utils.KeyBoardUtil;
import com.jiang.thinkindler.R;
import com.jiang.thinkindler.adapter.BookAdapter;
import com.jiang.thinkindler.base.BaseActivity;
import com.jiang.thinkindler.entity.bean.BookBean;
import com.jiang.thinkindler.entity.bean.BookList;
import com.jiang.thinkindler.helper.HistoryUtil;
import com.jiang.thinkindler.net.Api;
import com.jiang.thinkindler.net.ApiService;
import com.jiang.thinkindler.net.ApiType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @BindView(R.id.edt_search)
    AutoCompleteTextView edtSearch;
    private String[] historys;

    @BindView(R.id.rv_book)
    RecyclerView recyclerView;
    private BookAdapter mAdapter;
    private List<BookBean> datas = new ArrayList<>();

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

        edtSearch.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 修改回车键功能
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    doSearch();
                }
                return false;
            }
        });

        historys = HistoryUtil.loadAll();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item_search_history, historys);
        edtSearch.setAdapter(adapter);

        mAdapter = new BookAdapter(this, datas);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(mAdapter);
    }

    private void doSearch() {
        if (getSearchContent().equals(""))
            return;
        // 先隐藏键盘
        KeyBoardUtil.closeKeybord(edtSearch, mContext);
        HistoryUtil.saveHistory(getSearchContent());
        Api.getDefault(ApiType.DOUBAN).search(getSearchContent(), 0)
                .enqueue(new Callback<BookList>() {
                    @Override
                    public void onResponse(Call<BookList> call, Response<BookList> response) {
                        mAdapter.addList(response.body().getBooks());
                    }

                    @Override
                    public void onFailure(Call<BookList> call, Throwable t) {

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_clear, menu);
        return true;
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

    public String getSearchContent() {
        return edtSearch.getText().toString();
    }
}


