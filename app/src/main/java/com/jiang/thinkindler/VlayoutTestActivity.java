package com.jiang.thinkindler;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.jiang.thinkindler.base.BaseActivity;
import com.jiang.thinkindler.injector.component.AppComponent;

import butterknife.BindView;

/**
 * Created by jiang on 2017/6/8.
 */

public class VlayoutTestActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.act_vlayout;
    }

    @Override
    protected void init() {
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        DelegateAdapter adapter = new DelegateAdapter(manager, true);
        adapter.addAdapter(new DelegateDemoAdapter(this, new LinearLayoutHelper()));
        adapter.addAdapter(new DelegateDemoAdapter(this, new GridLayoutHelper(2)));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

}
