package com.jiang.thinkindler.net;

public enum ApiType {

    DOUBAN(0, "https://api.douban.com/"),
    //    DOMAIN(0, "http://192.168.8.104:8081/"),
    //        DOMAIN(0, "http://172.17.2.1:8081/"),
//    DOMAIN(0, "http://192.168.8.104:8081/"),
//    DOMAIN(0, "http://mobile.dev.idaqi.com/"),
    WECHAT(1, "https://api.weixin.qq.com/");

    private final int id;
    private final String url;

    ApiType(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public static ApiType[] Array() {
        return new ApiType[]{DOUBAN, WECHAT};
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
