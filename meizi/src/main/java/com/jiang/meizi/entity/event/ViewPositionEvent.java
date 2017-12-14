package com.jiang.meizi.entity.event;

import com.alexvasilkov.gestures.animation.ViewPosition;

/**
 * Created by knowing on 2017/12/14.
 */

public class ViewPositionEvent {

    ViewPosition position;

    public ViewPositionEvent(ViewPosition position) {
        this.position = position;
    }

    public ViewPosition getPosition() {
        return position;
    }

    public void setPosition(ViewPosition position) {
        this.position = position;
    }
}
