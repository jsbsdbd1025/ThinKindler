package com.jiang.meizi.entity.bean;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by jiang on 2017/7/31.
 */

public class ResponseBean {


    /**
     * error : false results : [{"_id":"5979848e421aa90ca209c4f7","createdAt":"2017-07-27T14:13:34.914Z","desc":"7-27","publishedAt":"2017-07-27T14:16:33.773Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034ly1fhyeyv5qwkj20u00u0q56.jpg","used":true,"who":"代码家"},{"_id":"597858e3421aa97de5c7c9b5","createdAt":"2017-07-26T16:54:59.321Z","desc":"7-26","publishedAt":"2017-07-26T16:57:39.343Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034ly1fhxe0hfzr0j20u011in1q.jpg","used":true,"who":"daimajia"},{"_id":"59761946421aa90ca209c4d5","createdAt":"2017-07-24T23:59:02.992Z","desc":"7-25","publishedAt":"2017-07-25T15:25:42.391Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034gy1fhvf13o2eoj20u011hjx6.jpg","used":true,"who":"daimajia"},{"_id":"59754e41421aa97de5c7c99d","createdAt":"2017-07-24T09:32:49.583Z","desc":"7-24","publishedAt":"2017-07-24T12:13:11.280Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034gy1fhupzs0awwj20u00u0tcf.jpg","used":true,"who":"daimajia"},{"_id":"5971760e421aa90ca209c4af","createdAt":"2017-07-21T11:33:34.104Z","desc":"7-21","publishedAt":"2017-07-21T12:39:43.370Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034ly1fhrcmgo6p0j20u00u00uu.jpg","used":true,"who":"daimajia"},{"_id":"596ffd1e421aa97de5c7c975","createdAt":"2017-07-20T08:45:18.437Z","desc":"7-20","publishedAt":"2017-07-20T15:11:16.10Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhq25406waj20u00u0b29.jpg","used":true,"who":"daimajia"},{"_id":"596ea620421aa90c9203d3bc","createdAt":"2017-07-19T08:21:52.67Z","desc":"7-19","publishedAt":"2017-07-19T13:23:20.375Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhovjwwphfj20u00u04qp.jpg","used":true,"who":"代码家"},{"_id":"596d5739421aa97de5c7c959","createdAt":"2017-07-18T08:32:57.632Z","desc":"7-18","publishedAt":"2017-07-18T16:12:55.381Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhnqjm1vczj20rs0rswia.jpg","used":true,"who":"daimajia"},{"_id":"59681987421aa97de5c7c92f","createdAt":"2017-07-14T09:08:23.898Z","desc":"7
     * 月 17 日","publishedAt":"2017-07-17T12:22:21.307Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhj5228gwdj20u00u0qv5.jpg","used":true,"who":"带马甲"},{"_id":"596819b7421aa90ca209c45f","createdAt":"2017-07-14T09:09:11.591Z","desc":"RIP","publishedAt":"2017-07-14T13:24:31.177Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhj53yz5aoj21hc0xcn41.jpg","used":true,"who":"代码家"}]
     */

    private boolean error;
    private JsonArray results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public JsonArray getResults() {
        return results;
    }

    public void setResults(JsonArray results) {
        this.results = results;
    }
}
