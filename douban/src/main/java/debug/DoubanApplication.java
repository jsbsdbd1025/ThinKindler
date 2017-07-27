package debug;

import android.content.Context;

import com.jiang.common.base.CommonApplication;
import com.jiang.common.utils.LogUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by jiang on 2017/7/26.
 */

public class DoubanApplication extends CommonApplication {


    public static CommonApplication get(Context context) {
        return (CommonApplication) context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.logInit(true);
        LeakCanary.install(this);

    }

}
