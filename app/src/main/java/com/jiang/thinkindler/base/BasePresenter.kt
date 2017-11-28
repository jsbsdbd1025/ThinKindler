package com.jiang.thinkindler.base

import com.google.gson.Gson

interface BasePresenter {

    fun start()

    companion object {

        val gson = Gson()
    }

}
