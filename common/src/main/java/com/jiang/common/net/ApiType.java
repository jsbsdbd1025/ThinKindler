package com.jiang.common.net;

public enum ApiType {

    DOUBAN(0, "https://api.douban.com/");

    private final int id;
    private final String url;

    ApiType(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public static ApiType[] Array() {
        return new ApiType[]{DOUBAN};
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
