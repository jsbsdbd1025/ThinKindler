package com.jiang.douban.entity.bean;

/**
 * Created by jiang on 2017/3/25.
 */

public class ImageBean {
    /**
     * small : https://img1.doubanio.com/spic/s1082988.jpg
     * large : https://img1.doubanio.com/lpic/s1082988.jpg
     * medium : https://img1.doubanio.com/mpic/s1082988.jpg
     */

    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
