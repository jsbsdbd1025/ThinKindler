package com.jiang.common.widget.flashview;

/**
 * Created by lenovo on 2017/2/8.
 */

public class BannerBean {
    private String imageUrl;

    private String title;

    private String value;

    public BannerBean() {
    }

    public BannerBean(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
