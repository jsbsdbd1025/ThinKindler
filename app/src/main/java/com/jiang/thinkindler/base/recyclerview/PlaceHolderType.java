package com.jiang.thinkindler.base.recyclerview;


/**
 * Created by lenovo on 2016/12/16.
 */

public enum PlaceHolderType {
    LOADING(0, 0, 0);
    private final int icon;
    private final int title;
    private final int content;

    PlaceHolderType(int drawableid, int titleResid, int contentResid) {
        icon = drawableid;
        title = titleResid;
        content = contentResid;
    }

    public int getIcon() {
        return icon;
    }

    public int getTitle() {
        return title;
    }

    public int getContent() {
        return content;
    }

}
