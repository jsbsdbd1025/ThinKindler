package com.jiang.common.base;

import com.google.gson.Gson;

public interface BasePresenter {

    Gson gson = new Gson();

    void start();

}
