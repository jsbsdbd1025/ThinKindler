package debug;

import com.jiang.common.widget.ToolBarBuilder;
import com.jiang.meizi.R;
import com.jiang.meizi.base.BaseActivity;

/**
 * Created by jiang on 2017/7/26.
 */

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    protected void init() {
        new ToolBarBuilder(this)
                .setTitle("首页");
    }

    @Override
    protected void initInjector() {

    }
}
