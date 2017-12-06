package com.jiang.media.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by knowing on 2017/12/6.
 */

public class BiliBiliRecommend {


    /**
     * type : recommend
     * head : {"param":"","goto":"","style":"gm_av","title":"热门焦点"}
     * body : [{"title":"【大触来了】赛璐璐基础上色技法（DAdonika）","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/0ee32766c5f47a44dcda532b9b99e981d595637d.jpg","param":"16775278","goto":"av","width":350,"height":219,"play":"3.2万","danmaku":"106","up":"绘画大触"},{"title":"【初音ミク】Unite As One【Q'ulle×八王子P】","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/26c3e6e23e0aa777fa035d8fd5d273a622b61c75.jpg","param":"16806353","goto":"av","width":350,"height":219,"play":"7.3万","danmaku":"883","up":"初音未来"},{"title":"【无论何时我们的恋情都是10厘米。 OP曲】┗|∵|┓Nonfantasy／LIP×LIP(CV：内山昂輝・島﨑信長)","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/b920a0c70472c1996983cbd41c007cc35d165bc2.jpg","param":"16834054","goto":"av","width":350,"height":219,"play":"3.5万","danmaku":"440","up":"よんあか"},{"title":"┗-∵-┓東京冬日相會／ HoneyWorks","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/01b975e7ad9b0242478fd64d02f2475b7ef637ce.png","param":"16848684","goto":"av","width":350,"height":219,"play":"2.2万","danmaku":"332","up":"モノクロ_汐"}]
     */

    private String type;
    private HeadBean head;
    private List<BiliBiliRecommendBody> body;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public List<BiliBiliRecommendBody> getBody() {
        return body;
    }

    public void setBody(List<BiliBiliRecommendBody> body) {
        this.body = body;
    }

    public static class HeadBean {
        /**
         * param :
         * goto :
         * style : gm_av
         * title : 热门焦点
         */

        private String param;
        @SerializedName("goto")
        private String gotoX;
        private String style;
        private String title;

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

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
