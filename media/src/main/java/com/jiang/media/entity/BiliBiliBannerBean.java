package com.jiang.media.entity;

/**
 * Created by knowing on 2017/12/5.
 */

public class BiliBiliBannerBean {


    /**
     * title : 舞蹈1205
     * value : https://www.bilibili.com/read/cv94519?from=1002
     * image : http://i0.hdslb.com/bfs/archive/02dd3cf4652c19f372f77d1eacfcbc49904578a8.jpg
     * type : 0
     * weight : 1
     * remark :
     * hash : 57fc293d6e384b95506d7a01b2e675a3
     */

    private String title;
    private String value;
    private String image;
    private int type;
    private int weight;
    private String remark;
    private String hash;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
