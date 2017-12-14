package com.jiang.meizi.entity.event;

/**
 * Created by knowing on 2017/12/14.
 */

public class ImageStatusEvent {
    private boolean visiable;

    public ImageStatusEvent(boolean visiable) {
        this.visiable = visiable;
    }

    public boolean isVisiable() {
        return visiable;
    }

    public void setVisiable(boolean visiable) {
        this.visiable = visiable;
    }
}
