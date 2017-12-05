package com.jiang.media.entity;

import java.util.List;

/**
 * Created by knowing on 2017/12/5.
 */

public class RankInfo {


    private RankBean rank;

    public RankBean getRank() {
        return rank;
    }

    public void setRank(RankBean rank) {
        this.rank = rank;
    }

    public static class RankBean {

        private String note;
        private int code;
        private int pages;
        private int num;
        private List<ListBean> list;

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * aid : 16874218
             * author : 某幻君
             * coins : 48651
             * duration : 8:59
             * mid : 1577804
             * pic : http://i1.hdslb.com/bfs/archive/5c9ef086795910be87f2b18c6e0deb1e50f0c6bf.jpg
             * play : 1248920
             * pts : 954112
             * title : 【中文八级】两个国人展开了惊人的英语八级对话
             * trend : null
             * video_review : 7103
             * others : [{"aid":16955477,"play":144136,"video_review":5577,"coins":5477,"pts":152280,"title":"【王老菊】这一百分钟全是细节","pic":"http://i1.hdslb.com/bfs/archive/96ca23839f3b75aa2c71f47cc4b980c655b04675.jpg","duration":"106:17"}]
             */

            private String aid;
            private String author;
            private int coins;
            private String duration;
            private int mid;
            private String pic;
            private int play;
            private int pts;
            private String title;
            private Object trend;
            private int video_review;
            private List<OthersBean> others;

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getCoins() {
                return coins;
            }

            public void setCoins(int coins) {
                this.coins = coins;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getPlay() {
                return play;
            }

            public void setPlay(int play) {
                this.play = play;
            }

            public int getPts() {
                return pts;
            }

            public void setPts(int pts) {
                this.pts = pts;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getTrend() {
                return trend;
            }

            public void setTrend(Object trend) {
                this.trend = trend;
            }

            public int getVideo_review() {
                return video_review;
            }

            public void setVideo_review(int video_review) {
                this.video_review = video_review;
            }

            public List<OthersBean> getOthers() {
                return others;
            }

            public void setOthers(List<OthersBean> others) {
                this.others = others;
            }

            public static class OthersBean {
                /**
                 * aid : 16955477
                 * play : 144136
                 * video_review : 5577
                 * coins : 5477
                 * pts : 152280
                 * title : 【王老菊】这一百分钟全是细节
                 * pic : http://i1.hdslb.com/bfs/archive/96ca23839f3b75aa2c71f47cc4b980c655b04675.jpg
                 * duration : 106:17
                 */

                private int aid;
                private int play;
                private int video_review;
                private int coins;
                private int pts;
                private String title;
                private String pic;
                private String duration;

                public int getAid() {
                    return aid;
                }

                public void setAid(int aid) {
                    this.aid = aid;
                }

                public int getPlay() {
                    return play;
                }

                public void setPlay(int play) {
                    this.play = play;
                }

                public int getVideo_review() {
                    return video_review;
                }

                public void setVideo_review(int video_review) {
                    this.video_review = video_review;
                }

                public int getCoins() {
                    return coins;
                }

                public void setCoins(int coins) {
                    this.coins = coins;
                }

                public int getPts() {
                    return pts;
                }

                public void setPts(int pts) {
                    this.pts = pts;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getDuration() {
                    return duration;
                }

                public void setDuration(String duration) {
                    this.duration = duration;
                }
            }
        }
    }
}
