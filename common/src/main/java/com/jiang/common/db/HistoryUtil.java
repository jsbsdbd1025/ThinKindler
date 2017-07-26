package com.jiang.thinkindler.data.db;

import com.jiang.common.utils.LogUtils;
import com.jiang.thinkindler.entity.db.SearchHistory;
import com.jiang.thinkindler.gen.SearchHistoryDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by jiang on 2017/3/24.
 */

public class HistoryUtil {

    public static Observable<String[]> loadAll() {
        return Observable.create(new ObservableOnSubscribe<String[]>() {
            @Override
            public void subscribe(ObservableEmitter<String[]> e) throws Exception {
                SearchHistoryDao dao = DBHelper.getInstance().getDaoSession().getSearchHistoryDao();
                List<SearchHistory> datas = dao.loadAll();
                String[] result = new String[datas.size()];
                for (int i = 0; i < datas.size(); i++) {
                    result[i] = datas.get(i).getText();
                }
                e.onNext(result);
            }
        });
    }

    public static void saveHistory(String text) {
        SearchHistoryDao dao = DBHelper.getInstance().getDaoSession().getSearchHistoryDao();
        SearchHistory history = dao.queryBuilder().where(SearchHistoryDao.Properties.Text.eq(text)).unique();
        if (null != history) {
            LogUtils.loge("记录已存在   " + text);
            return;
        } else {
            dao.insert(new SearchHistory(text));
            LogUtils.loge("记录保存成功   " + text);
        }
    }
}
