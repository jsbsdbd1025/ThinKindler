package com.jiang.media.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by knowing on 2017/12/6.
 */

public class BiliBiliRecommendBody {

    /**
     * title : 【大触来了】赛璐璐基础上色技法（DAdonika）
     * style : gm_av
     * cover : http://i0.hdslb.com/bfs/archive/0ee32766c5f47a44dcda532b9b99e981d595637d.jpg
     * param : 16775278
     * goto : av
     * width : 350
     * height : 219
     * play : 3.2万
     * danmaku : 106
     * up : 绘画大触
     */

    private String title;
    private String style;
    private String cover;
    private String param;
    @SerializedName("goto")
    private String gotoX;
    private int width;
    private int height;
    private String play;
    private String danmaku;
    private String up;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getGotoX() {
        return gotoX;
    }

    public void setGotoX(String gotoX) {
        this.gotoX = gotoX;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getDanmaku() {
        return danmaku;
    }

    public void setDanmaku(String danmaku) {
        this.danmaku = danmaku;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }
}
