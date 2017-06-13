package com.jiang.thinkindler.utils.pagelist;


import com.jiang.common.base.irecyclerview.IRecyclerView;
import com.jiang.common.base.irecyclerview.OnLoadMoreListener;
import com.jiang.common.base.irecyclerview.OnRefreshListener;
import com.jiang.common.base.irecyclerview.widget.footer.LoadMoreFooterView;
import com.jiang.thinkindler.entity.bean.PageList;

/**
 * Created by jiang on 2017/3/11.
 */

public class PageListHelper {
    private IRecyclerView iRecyclerView;
    private PageListListener pageListListener;
    private LoadMoreFooterView loadMoreFooterView;
    private PageList pageBean;
    private boolean isRefresh = false;

    public PageListHelper(IRecyclerView recyclerView) {
        this.iRecyclerView = recyclerView;

        pageBean = new PageList();
        if (iRecyclerView.getRefreshEnabled()) {
            iRecyclerView.setOnRefreshListener(onRefreshListener);
        }

        if (iRecyclerView.getLoadMoreEnabled()) {
            loadMoreFooterView = (LoadMoreFooterView) iRecyclerView.getLoadMoreFooterView();
            iRecyclerView.setOnLoadMoreListener(onLoadMoreListener);
        }

//        默认为不加载  进入页面自动刷新请在onResume中         mRecyclerView.setRefreshing(true);
//        iRecyclerView.setRefreshing(true);
    }

    public void setPageListListener(PageListListener listener) {
        this.pageListListener = listener;
    }

    OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            isRefresh = true;
            pageBean = new PageList();
            pageListListener.getDatas(pageBean.nextPage());
        }
    };

    OnLoadMoreListener onLoadMoreListener = new OnLoadMoreListener() {

        @Override
        public void onLoadMore() {
            if (loadMoreFooterView.canLoadMore() && iRecyclerView.getChildCount() > 0 && hasMore()) {
                loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
                isRefresh = false;
                pageListListener.getDatas(pageBean.nextPage());
            }
        }
    };

    public void stopLoading() {
        if (isRefresh) {
            iRecyclerView.setRefreshing(false);
        }

        if (null == loadMoreFooterView) {
            return;
        }

        if (hasMore()) {
            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
        } else {
            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
        }
    }

//    public void showError(PlaceHolderType type) {
//        BaseRecyclerAdapter adapter = (BaseRecyclerAdapter) iRecyclerView.getIAdapter();
//        adapter.clear();
//        adapter.setErrorType(type);
//        if (null == loadMoreFooterView)
//            return;
//        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
//    }

    public void setPageBean(PageList pageBean) {
        this.pageBean = pageBean;
    }

    public boolean hasMore() {
        if (pageBean != null) {
            return pageBean.hasmore();
        }
        return false;
    }

    public boolean isRefresh() {
        return isRefresh | pageBean.getStart() == 0;
    }

    public void clear() {
        iRecyclerView.getIAdapter().notifyItemRangeRemoved(0, iRecyclerView.getIAdapter().getItemCount());
    }

}
