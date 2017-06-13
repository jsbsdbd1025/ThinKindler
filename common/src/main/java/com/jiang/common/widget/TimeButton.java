package com.jiang.common.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jiang.common.base.CommonApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by CJ on 2016/12/2.
 */

public class TimeButton extends Button implements OnClickListener {

    private long lenght = 60 * 1000; // 倒计时长度,这里给了默认60秒
    private String textafter = "重新发送";
    private String textbefore = "获取验证码";
    private final String TIME = "time";
    private final String CTIME = "ctime";
    private OnClickListener mOnclickListener;
    private Timer t;
    private TimerTask tt;
    private long time;
    Map<String, Long> map = new HashMap<String, Long>();

    public TimeButton(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public TimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (mOnclickListener != null) {
            mOnclickListener.onClick(v);
        } else {
            toClick();
        }
    }

    public void toClick() {
        initTimer();
        this.setText(textafter + time / 1000 + "s");
        this.setEnabled(false);
        t.schedule(tt, 0, 1000);
    }

    public void setTime(long time) {
        this.time = time;
    }

    Handler han = new Handler() {
        public void handleMessage(android.os.Message msg) {
            TimeButton.this.setText(textafter + time / 1000 + "s");
            time -= 1000;
            if (time < 0) {
                TimeButton.this.setEnabled(true);
                TimeButton.this.setText(textbefore);
                clearTimer();
            }
        }
    };

    private void initTimer() {
        time = lenght;
        t = new Timer();
        tt = new TimerTask() {

            @Override
            public void run() {
                han.sendEmptyMessage(0x01);
            }
        };
    }

    private void clearTimer() {
        if (tt != null) {
            tt.cancel();
            tt = null;
        }
        if (t != null) {
            t.cancel();
        }
        t = null;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        if (l instanceof TimeButton) {
            super.setOnClickListener(l);
        } else {
            this.mOnclickListener = l;
        }
    }

    /**
     * 和activity的onDestroy()方法同步
     */
    public void onDestroy() {
        if (CommonApplication.map == null) {
            CommonApplication.map = new HashMap<String, Long>();
        }
        CommonApplication.map.put(TIME, time);
        CommonApplication.map.put(CTIME, System.currentTimeMillis());
        clearTimer();
    }

    /**
     * 和activity的onCreate()方法同步
     */
    public void onCreate(Bundle bundle) {
        Log.e("yung", CommonApplication.map + "");
        if (CommonApplication.map == null) {
            return;
        }
        if (CommonApplication.map.size() <= 0) {
            return;
        }
        long time = System.currentTimeMillis() - CommonApplication.map.get(CTIME)
                - CommonApplication.map.get(TIME);
        CommonApplication.map.clear();
        if (time > 0) {
            return;
        } else {
            initTimer();
            this.time = Math.abs(time);
            t.schedule(tt, 0, 1000);
            this.setText(textafter + time + "s");
            this.setEnabled(false);
        }
    }

    /**
     * 设置计时时候显示的文本
     */
    public TimeButton setTextAfter(String text1) {
        this.textafter = text1;
        return this;
    }

    /**
     * 设置点击之前的文本
     */
    public TimeButton setTextBefore(String text0) {
        this.textbefore = text0;
        this.setText(textbefore);
        return this;
    }

    /**
     * 设置到计时长度
     *
     * @param lenght 时间 默认毫秒
     */
    public TimeButton setLenght(long lenght) {
        this.lenght = lenght;
        return this;
    }
}
