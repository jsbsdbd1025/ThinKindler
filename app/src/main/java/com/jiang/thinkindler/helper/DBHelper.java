package com.jiang.thinkindler.helper;

import android.database.sqlite.SQLiteDatabase;

import com.jiang.thinkindler.app.BaseApplication;
import com.jiang.thinkindler.gen.DaoMaster;
import com.jiang.thinkindler.gen.DaoSession;

/**
 * Created by jiang on 2017/3/24.
 */

public class DBHelper {
    private static final String TAG = DBHelper.class.getSimpleName();
    private static volatile DBHelper instance = null;
    private static DaoMaster.DevOpenHelper mHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static SQLiteDatabase db;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (null == instance) {
            synchronized (DBHelper.class) {
                if (null == instance) {
                    instance = new DBHelper();
                }
            }
        }
        return instance;
    }

    /**
     * 判断数据库是否存在，如果不存在则创建
     *
     * @return
     */
    public DaoMaster getDaoMaster() {
        if (null == mDaoMaster) {
            mHelper = new DaoMaster.DevOpenHelper(BaseApplication.getAppContext(), "user" + ".db", null);
            mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        }
        return mDaoMaster;
    }

    /**
     * 完成对数据库的增删查找
     *
     * @return
     */
    public DaoSession getDaoSession() {
        if (null == mDaoSession) {
            if (null == mDaoMaster) {
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }
}
