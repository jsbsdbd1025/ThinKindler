package debug;

import com.dl7.player.media.IjkPlayerView;
import com.jiang.media.R;
import com.jiang.media.base.BaseActivity;

/**
 * Created by jiang on 2017/7/26.
 */

public class MainActivity extends BaseActivity {


//    private IjkPlayerView mPlayerView;
//
//    String VIDEO_URL = "http://tx.acgvideo.com/54/02/16620254/16620254-1-48.mp4?txTime=1512451947&platform=pc&txSecret=969240160b80cd5ab967922995ddbdfd&oi=3736862439&rate=1000&hfb=b99ffc3c5c68f00a33123bb25f882d5b";

    @Override
    public int getLayoutId() {
        return R.layout.bilibili_act_main;
    }

    @Override
    protected void init() {

//        mPlayerView = (IjkPlayerView) findViewById(R.id.player_view);
//
//        mPlayerView.init()              // Initialize, the first to use
//                .setTitle("Title")    // set title
//                .setSkipTip(1000 * 60 * 1)  // set the position you want to skip
//                .enableOrientation()    // enable orientation
//                //      .setVideoPath(VIDEO_URL)    // set video url
//                .setVideoSource(null, VIDEO_URL, VIDEO_URL, VIDEO_URL, null) // set multiple video url
//                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)  // set the initial video url
//                .enableDanmaku()        // enable Danmaku
////                .setDanmakuSource(getResources().openRawResource(R.raw.comments)) // add Danmaku source, you need to use enableDanmaku() first
//                .start();   // Start playing

    }

    @Override
    protected void initInjector() {

    }
}
