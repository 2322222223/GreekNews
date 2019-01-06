package com.renlz.jiyun.greeknews.beans;

import java.util.List;

/**
 * Created by Administrator on 2019/1/2.
 */

public class LongComments {
    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * author : 牟勇
         * content : 从来不看首映式的冒个泡，比较支持答主的观点。

         反正我是不赶首映的，最主要原因还是首映都在零点，而我是过了零点就睡不着觉且1第二天逮谁骂谁的。

         先听听人家的评价，然后再决定看不看。

         一边倒的骂的，基本是不会看的，偶尔例外。

         除了几个ip，比如漫威的，迪斯尼经典童话改编剧，指环王，只要时间合适一定看，没时间就过上几个月，在网上看。不管人家骂不骂。

         国内的看导演，冯小刚的一定看，张艺谋的有机会就看。

         最近的新导演不认识几个了，感觉都不成熟，有些剧情太突然，感觉很随意，不合逻辑。最后结局，眨眨眼就知道了。
         * avatar : http://pic2.zhimg.com/f86a29a4fa4749276cdb9c402312a04d_im.jpg
         * time : 1546402201
         * id : 32745386
         * likes : 6
         * reply_to : {"content":"作者数据没有问题但其实分析角度不太合理\u201c如果两者口碑的平均值都是 7 分，那么大家都给 7 分的那一部电影，要比有人给 10 分有人给 0 分的那部电影，票房高出 45%。\u201d就这段话，预设了一个「得分相同的电影票房应该基本一致」这个不合理的前提。7.8分600w的路边野餐，和7.8分20亿的海王，可以放在一起比较吗？宣发公司不是傻子，相反他们鸡贼的很：错配的影片，无一例外都是原本受众很小的影片，把大家骗进电影院捞一笔，吃亏的永远不是发行方和宣发公司，而是看的莫名其妙一脸懵逼的观众们。","status":0,"id":32745234,"author":"善一"}
         */

        private String author;
        private String content;
        private String avatar;
        private int time;
        private int id;
        private int likes;
        private ReplyToBean reply_to;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public ReplyToBean getReply_to() {
            return reply_to;
        }

        public void setReply_to(ReplyToBean reply_to) {
            this.reply_to = reply_to;
        }

        public static class ReplyToBean {
            /**
             * content : 作者数据没有问题但其实分析角度不太合理“如果两者口碑的平均值都是 7 分，那么大家都给 7 分的那一部电影，要比有人给 10 分有人给 0 分的那部电影，票房高出 45%。”就这段话，预设了一个「得分相同的电影票房应该基本一致」这个不合理的前提。7.8分600w的路边野餐，和7.8分20亿的海王，可以放在一起比较吗？宣发公司不是傻子，相反他们鸡贼的很：错配的影片，无一例外都是原本受众很小的影片，把大家骗进电影院捞一笔，吃亏的永远不是发行方和宣发公司，而是看的莫名其妙一脸懵逼的观众们。
             * status : 0
             * id : 32745234
             * author : 善一
             */

            private String content;
            private int status;
            private int id;
            private String author;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
        }
    }
}
