package com.jiang.common.entity.bean

import com.jiang.douban.entity.bean.ImageBean
import com.jiang.douban.entity.bean.RatingBean
import com.jiang.douban.entity.bean.SeriesBean
import com.jiang.douban.entity.bean.TagBean
import java.io.Serializable

/**
 * Created by jiang on 2017/3/24.
 */

class BookBean : Serializable {
    /**
     * rating : {"max":10,"numRaters":78,"average":"7.3","min":0}
     * subtitle : 文学
     * author : ["科里·贝尔","著"]
     * pubdate : 2002-02-01
     * tags : [{"count":21,"name":"文学","title":"文学"},{"count":16,"name":"三联","title":"三联"},{"count":11,"name":"外国文学","title":"外国文学"},{"count":10,"name":"文学史","title":"文学史"},{"count":4,"name":"三联速成读本","title":"三联速成读本"},{"count":4,"name":"百科知识","title":"百科知识"},{"count":4,"name":"艺术","title":"艺术"},{"count":3,"name":"历史","title":"历史"}]
     * origin_title :
     * image : https://img1.doubanio.com/mpic/s1082988.jpg
     * binding : 精装
     * translator : ["苏福忠"]
     * catalog : 概述
     * 1、全部是希腊人
     * 2、奥古斯都与令人作哎的作品
     * 3、严寒的北部
     * 4、亚洲的作品
     * 5、淑女与绅士
     * 6、峰巅但丁
     * 7、鲁特琴与下等人
     * 8、高贵的诗歌，低贱的散文
     * 9、全世界是一座舞台
     * 10、为上帝，为床第
     * 11、弥尔顿的问题
     * 12、让我们去理智
     * 13、角色塑造
     * 14、批评的群众
     * 15、天才人物
     * 盈亏结算一览表
     * 索引
     * 照片来源
     * pages : 143
     * images : {"small":"https://img1.doubanio.com/spic/s1082988.jpg","large":"https://img1.doubanio.com/lpic/s1082988.jpg","medium":"https://img1.doubanio.com/mpic/s1082988.jpg"}
     * alt : https://book.douban.com/subject/1052976/
     * id : 1052976
     * publisher : 生活·读书·新知三联书店
     * isbn10 : 7108016672
     * isbn13 : 9787108016676
     * title : 文学
     * url : https://api.douban.com/v2/book/1052976
     * alt_title :
     * author_intro : 科里·贝尔  在牛津大学攻读英国文学，后彻底放弃文学作了画家。现在他两不耽误，双双风光，写画家的事，为作家作画，他是布卢姆斯伯里文化圈中的一员，所以这是真正的“马口料”。请多加注意，读者——比这更权威的材料再难得到。
     * summary : 如果真的用书籍来装饰一间房间，对于把一个自家组装的扁平外壳改装成一个定制的大型书橱，道行不深的读者如何说得清呢？是用基本的材料（如木头、胶、钉子、人物、情节、对话等）？还是大谈工艺技术（一种表达优美的隐喻或一种明喻的机敏放置）？《文学-速成读本》为了初次购买者提供了一份指南——请读一读它，读过之后你再不会把里尔克和兰波弄混了。各种流派与风格均划分清楚，举例说明。谈及各大陆的所有重要作家。各家学派、各种运动和种种尝试都一一探索。各种文字大奖附表列出（以便你决定摘取某项）。
     * series : {"id":"3387","title":"速成读本系列"}
     * price : 38.00元
     */

    var rating: RatingBean? = null
    var subtitle: String? = null
    var pubdate: String? = null
    var origin_title: String? = null
    var image: String? = null
    var binding: String? = null
    var catalog: String? = null
    var pages: String? = null
    var images: ImageBean? = null
    var alt: String? = null
    var id: String? = null
    var publisher: String? = null
    var isbn10: String? = null
    var isbn13: String? = null
    var title: String? = null
    var url: String? = null
    var alt_title: String? = null
    var author_intro: String? = null
    var summary: String? = null
    var series: SeriesBean? = null
    var price: String? = null
    var author: List<String>? = null
    var tags: List<TagBean>? = null
    var translator: List<String>? = null


}
