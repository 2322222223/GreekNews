package com.renlz.jiyun.greeknews.beans;

import java.util.List;

/**
 * Created by Administrator on 2019/1/4.
 */

public class ShuJuZhiHuiNewInfo {
    /**
     * ERRORCODE : 0
     * RESULT : {"imgUrl":["http://inews.gtimg.com/newsapp_bt/0/7138030346/1000","http://inews.gtimg.com/newsapp_bt/0/7138030647/1000","http://inews.gtimg.com/newsapp_bt/0/7138030985/1000","http://inews.gtimg.com/newsapp_bt/0/7138031203/1000","http://inews.gtimg.com/newsapp_bt/0/7138031466/1000","http://inews.gtimg.com/newsapp_bt/0/7138031789/1000"],"publishTime":"2019-01-04 17:59:15","editor":"","source":"辽东春秋","category":"娱乐","title":"鹿晗否认结婚后关晓彤现身机场，披万元围巾，大长腿抢镜","content":"   1月4日，关晓彤现身北京机场，她一身黑色穿搭，大长腿非常抢镜。因为人气很高，关晓彤现身机场就戴着口罩，前几天有传言称鹿晗工作室发通稿透露鹿晗和关晓彤结婚的消息，不过很快鹿晗工作室就发声明否认了。   今天有媒体曝光鹿晗位于北京东五环的新豪宅，据悉鹿晗在2017年年底花了八千多万购置，目前已装修完毕。不少网友都猜测鹿晗这是为和关晓彤结婚准备的婚房。但下午鹿晗工作室就发微博否认了这一消息，看来鹿晗和关晓彤短时间内是不会结婚了。   鹿晗和关晓彤前年10月公开恋情，两人感情稳定又甜蜜，此前还有媒体拍到他们深夜同住的照片。今天现身机场的关晓彤，也是一身奢侈品，光一条LV的围巾，在官网售价就高达10500元。   关晓彤现身北京机场，因为天气寒冷，关晓彤买了一杯咖啡暖手，虽然戴着口罩，但是还是能看出她的颜值。关晓彤和鹿晗恋爱后人气上升很快，和张艺谋一起拍电影，马上还参加常驻综艺《王牌对王牌》第四季，前途无量。反而因为蔡徐坤等人的崛起，鹿晗的人气和影响力下滑厉害。   关晓彤低调现身北京机场，大长腿非常迷人。   关晓彤现身机场。【图文版权归春秋娱乐所有，独家发布于企鹅号辽东春秋，不对外授权，禁止转载搬运】 "}
     */

    private String ERRORCODE;
    private RESULTBean RESULT;

    public String getERRORCODE() {
        return ERRORCODE;
    }

    public void setERRORCODE(String ERRORCODE) {
        this.ERRORCODE = ERRORCODE;
    }

    public RESULTBean getRESULT() {
        return RESULT;
    }

    public void setRESULT(RESULTBean RESULT) {
        this.RESULT = RESULT;
    }

    public static class RESULTBean {
        /**
         * imgUrl : ["http://inews.gtimg.com/newsapp_bt/0/7138030346/1000","http://inews.gtimg.com/newsapp_bt/0/7138030647/1000","http://inews.gtimg.com/newsapp_bt/0/7138030985/1000","http://inews.gtimg.com/newsapp_bt/0/7138031203/1000","http://inews.gtimg.com/newsapp_bt/0/7138031466/1000","http://inews.gtimg.com/newsapp_bt/0/7138031789/1000"]
         * publishTime : 2019-01-04 17:59:15
         * editor :
         * source : 辽东春秋
         * category : 娱乐
         * title : 鹿晗否认结婚后关晓彤现身机场，披万元围巾，大长腿抢镜
         * content :    1月4日，关晓彤现身北京机场，她一身黑色穿搭，大长腿非常抢镜。因为人气很高，关晓彤现身机场就戴着口罩，前几天有传言称鹿晗工作室发通稿透露鹿晗和关晓彤结婚的消息，不过很快鹿晗工作室就发声明否认了。   今天有媒体曝光鹿晗位于北京东五环的新豪宅，据悉鹿晗在2017年年底花了八千多万购置，目前已装修完毕。不少网友都猜测鹿晗这是为和关晓彤结婚准备的婚房。但下午鹿晗工作室就发微博否认了这一消息，看来鹿晗和关晓彤短时间内是不会结婚了。   鹿晗和关晓彤前年10月公开恋情，两人感情稳定又甜蜜，此前还有媒体拍到他们深夜同住的照片。今天现身机场的关晓彤，也是一身奢侈品，光一条LV的围巾，在官网售价就高达10500元。   关晓彤现身北京机场，因为天气寒冷，关晓彤买了一杯咖啡暖手，虽然戴着口罩，但是还是能看出她的颜值。关晓彤和鹿晗恋爱后人气上升很快，和张艺谋一起拍电影，马上还参加常驻综艺《王牌对王牌》第四季，前途无量。反而因为蔡徐坤等人的崛起，鹿晗的人气和影响力下滑厉害。   关晓彤低调现身北京机场，大长腿非常迷人。   关晓彤现身机场。【图文版权归春秋娱乐所有，独家发布于企鹅号辽东春秋，不对外授权，禁止转载搬运】
         */

        private String publishTime;
        private String editor;
        private String source;
        private String category;
        private String title;
        private String content;
        private List<String> imgUrl;

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getEditor() {
            return editor;
        }

        public void setEditor(String editor) {
            this.editor = editor;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(List<String> imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
