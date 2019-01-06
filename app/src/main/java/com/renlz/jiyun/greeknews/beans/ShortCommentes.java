package com.renlz.jiyun.greeknews.beans;

import java.util.List;

/**
 * Created by Administrator on 2019/1/2.
 */

public class ShortCommentes {
    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * author : 申泰铭
         * content : 饮鸩止渴啊
         * avatar : http://pic3.zhimg.com/5e60330f3466ef9390e56e201a756c6a_im.jpg
         * time : 1546408712
         * reply_to : {"content":"我感觉也是这样 不过没有数据支持 现在终于有数据啦 《地球》上映前大家就有分析出来这部影片的消费群体发生了颠覆变化 导演这种个人风格明显的小众文艺片 明显不适合普罗大众的口味啊 却还要搞一个噱头那么大的宣发 好了 不管是单身狗还是情侣大家都抢着去电影院跨年 结果就是跨年夜过了之后再也没人想去看这部电影了 ヾ(-_-)ゞ 何况年终期末大家都忙成狗....哪有心情再去消遣一部口碑不好的文艺片","status":0,"id":32745181,"author":"澄婪"}
         * id : 32745897
         * likes : 0
         */

        private String author;
        private String content;
        private String avatar;
        private int time;
        private ReplyToBean reply_to;
        private int id;
        private int likes;

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

        public ReplyToBean getReply_to() {
            return reply_to;
        }

        public void setReply_to(ReplyToBean reply_to) {
            this.reply_to = reply_to;
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

        public static class ReplyToBean {
            /**
             * content : 我感觉也是这样 不过没有数据支持 现在终于有数据啦 《地球》上映前大家就有分析出来这部影片的消费群体发生了颠覆变化 导演这种个人风格明显的小众文艺片 明显不适合普罗大众的口味啊 却还要搞一个噱头那么大的宣发 好了 不管是单身狗还是情侣大家都抢着去电影院跨年 结果就是跨年夜过了之后再也没人想去看这部电影了 ヾ(-_-)ゞ 何况年终期末大家都忙成狗....哪有心情再去消遣一部口碑不好的文艺片
             * status : 0
             * id : 32745181
             * author : 澄婪
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
