package com.jiang.thinkindler.entity.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PageList<T> implements Serializable {


    /**
     * count : 2
     * start : 0
     * total : 91685
     * books : [{"rating":{"max":10,"numRaters":78,"average":"7.3","min":0},"subtitle":"文学","author":["科里·贝尔","著"],"pubdate":"2002-02-01","tags":[{"count":21,"name":"文学","title":"文学"},{"count":16,"name":"三联","title":"三联"},{"count":11,"name":"外国文学","title":"外国文学"},{"count":10,"name":"文学史","title":"文学史"},{"count":4,"name":"三联速成读本","title":"三联速成读本"},{"count":4,"name":"百科知识","title":"百科知识"},{"count":4,"name":"艺术","title":"艺术"},{"count":3,"name":"历史","title":"历史"}],"origin_title":"","image":"https://img1.doubanio.com/mpic/s1082988.jpg","binding":"精装","translator":["苏福忠"],"catalog":"概述\n1、全部是希腊人\n2、奥古斯都与令人作哎的作品\n3、严寒的北部\n4、亚洲的作品\n5、淑女与绅士\n6、峰巅但丁\n7、鲁特琴与下等人\n8、高贵的诗歌，低贱的散文\n9、全世界是一座舞台\n10、为上帝，为床第\n11、弥尔顿的问题\n12、让我们去理智\n13、角色塑造\n14、批评的群众\n15、天才人物\n盈亏结算一览表\n索引\n照片来源","pages":"143","images":{"small":"https://img1.doubanio.com/spic/s1082988.jpg","large":"https://img1.doubanio.com/lpic/s1082988.jpg","medium":"https://img1.doubanio.com/mpic/s1082988.jpg"},"alt":"https://book.douban.com/subject/1052976/","id":"1052976","publisher":"生活·读书·新知三联书店","isbn10":"7108016672","isbn13":"9787108016676","title":"文学","url":"https://api.douban.com/v2/book/1052976","alt_title":"","author_intro":"科里·贝尔  在牛津大学攻读英国文学，后彻底放弃文学作了画家。现在他两不耽误，双双风光，写画家的事，为作家作画，他是布卢姆斯伯里文化圈中的一员，所以这是真正的\u201c马口料\u201d。请多加注意，读者\u2014\u2014比这更权威的材料再难得到。","summary":"如果真的用书籍来装饰一间房间，对于把一个自家组装的扁平外壳改装成一个定制的大型书橱，道行不深的读者如何说得清呢？是用基本的材料（如木头、胶、钉子、人物、情节、对话等）？还是大谈工艺技术（一种表达优美的隐喻或一种明喻的机敏放置）？《文学-速成读本》为了初次购买者提供了一份指南\u2014\u2014请读一读它，读过之后你再不会把里尔克和兰波弄混了。各种流派与风格均划分清楚，举例说明。谈及各大陆的所有重要作家。各家学派、各种运动和种种尝试都一一探索。各种文字大奖附表列出（以便你决定摘取某项）。","series":{"id":"3387","title":"速成读本系列"},"price":"38.00元"},{"rating":{"max":10,"numRaters":6,"average":"0.0","min":0},"subtitle":"阅读.反应.写作","author":["柯斯兹纳[LaurieG"],"pubdate":"2006-7","tags":[{"count":12,"name":"写作","title":"写作"},{"count":9,"name":"文学","title":"文学"},{"count":4,"name":"Literature","title":"Literature"},{"count":4,"name":"文艺理论","title":"文艺理论"},{"count":3,"name":"英语","title":"英语"},{"count":3,"name":"柯斯兹纳","title":"柯斯兹纳"},{"count":3,"name":"文学批评","title":"文学批评"},{"count":2,"name":"文学理论","title":"文学理论"}],"origin_title":"","image":"https://img1.doubanio.com/mpic/s1890677.jpg","binding":"","translator":[],"catalog":"","pages":"2234","images":{"small":"https://img1.doubanio.com/spic/s1890677.jpg","large":"https://img1.doubanio.com/lpic/s1890677.jpg","medium":"https://img1.doubanio.com/mpic/s1890677.jpg"},"alt":"https://book.douban.com/subject/1886248/","id":"1886248","publisher":"北京大学","isbn10":"7301108133","isbn13":"9787301108130","title":"文学","url":"https://api.douban.com/v2/book/1886248","alt_title":"","author_intro":"","summary":"随着第一版的面世，柯斯兹纳和芒代尔编写的《文学：阅读、反应、写作》为各个层次的学生揭开了文学的神秘面纱，使文学成为了他们生活的一部分。现在第五版的《文学：阅读、反应、写作》（戏剧和文学批评写作卷）收录了更多的当代作家和名著精粹，并随书附赠《Literature in the 21st Century》CD-ROM，将文学经典领出了文学象牙塔，领进了学生的日常生活。\n    由美国著名学者柯斯兹纳和芒代尔编写、汤姆森公司出版的《文学：阅读、反应、写作》的戏剧和文学批评写作卷，是一部很好的英语文学入门教材。它的观念时新，内容丰富，文学作品和文学理论有机结合，文学欣赏和文学研究紧密相连，生动活泼，深入浅出，对于学生培养文学兴趣，消除文学欣赏的神秘感，打好文学研究的基础，是非常有益的。","series":{"id":"944","title":"西方文学原版影印系列丛书"},"price":"79.80元"}]
     */

    private int count;
    private int start;
    private int total;
    @SerializedName(value = "datas", alternate = {"books"})
    private List<T> datas;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
