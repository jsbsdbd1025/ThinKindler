package com.jiang.thinkindler.helper;

import com.jiang.common.utils.LogUtils;
import com.jiang.thinkindler.entity.db.SearchHistory;
import com.jiang.thinkindler.gen.SearchHistoryDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiang on 2017/3/24.
 */

public class HistoryUtil {

    public static String[] loadAll() {
        SearchHistoryDao dao = DBHelper.getInstance().getDaoSession().getSearchHistoryDao();
        List<SearchHistory> datas = dao.loadAll();
        String[] result = new String[datas.size()];
        for (int i = 0; i < datas.size(); i++) {
            result[i] = datas.get(i).getText();
        }
        return result;
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
