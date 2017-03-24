package com.jiang.thinkindler.entity.bean;

import java.util.List;

/**
 * Created by jiang on 2017/3/24.
 */

public class BookBean {
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
     1、全部是希腊人
     2、奥古斯都与令人作哎的作品
     3、严寒的北部
     4、亚洲的作品
     5、淑女与绅士
     6、峰巅但丁
     7、鲁特琴与下等人
     8、高贵的诗歌，低贱的散文
     9、全世界是一座舞台
     10、为上帝，为床第
     11、弥尔顿的问题
     12、让我们去理智
     13、角色塑造
     14、批评的群众
     15、天才人物
     盈亏结算一览表
     索引
     照片来源
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

    private RatingBean rating;
    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String pages;
    private ImagesBean images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private SeriesBean series;
    private String price;
    private List<String> author;
    private List<TagsBean> tags;
    private List<String> translator;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public SeriesBean getSeries() {
        return series;
    }

    public void setSeries(SeriesBean series) {
        this.series = series;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public static class RatingBean {
        /**
         * max : 10
         * numRaters : 78
         * average : 7.3
         * min : 0
         */

        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img1.doubanio.com/spic/s1082988.jpg
         * large : https://img1.doubanio.com/lpic/s1082988.jpg
         * medium : https://img1.doubanio.com/mpic/s1082988.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class SeriesBean {
        /**
         * id : 3387
         * title : 速成读本系列
         */

        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class TagsBean {
        /**
         * count : 21
         * name : 文学
         * title : 文学
         */

        private int count;
        private String name;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
