package com.renlz.jiyun.greeknews.beans;

import java.util.List;

/**
 * Created by Administrator on 2018/12/29.
 */

public class GanHuoList {
    /**
     * error : false
     * results : [{"_id":"5c25d4e79d21221e8ada8663","createdAt":"2018-12-28T07:46:47.830Z","desc":"在iOS中将自定义视图显示为弹出窗口。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fymj3wu73jg30om1hcgoi","https://ww1.sinaimg.cn/large/0073sXn7ly1fymj3x5wueg30om1hcju4","https://ww1.sinaimg.cn/large/0073sXn7ly1fymj3xn7gkg30om1hcaet"],"publishedAt":"2018-12-28T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/JonyFang/FFPopup","used":true,"who":"lijinshanmx"},{"_id":"5c121d4a9d21223f60727cc8","createdAt":"2018-12-13T08:50:18.658Z","desc":"一个提供高级API来控制WKWebView的Swift库。","publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/LinusU/Marionette","used":true,"who":"lijinshanmx"},{"_id":"5c121e1b9d21223f60727cca","createdAt":"2018-12-13T08:53:47.716Z","desc":"集合视图布局是一组用于iOS的自定义流布局，它模仿移动应用程序的通用数据网格方法。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fy58galdavj30n01dsmzs"],"publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/ahmedAlmasri/SNCollectionViewLayout","used":true,"who":"lijinshanmx"},{"_id":"5c121e889d21223f60727ccb","createdAt":"2018-12-13T08:55:36.985Z","desc":"一个仿Apple的Apple Music播放器和Shortcuts组件视图的自定义UI组件.","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fy58k2wu7tg30dc0s97wm"],"publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/jVirus/drawer-view","used":true,"who":"lijinshanmx"},{"_id":"5c121f2c9d21223f60727ccc","createdAt":"2018-12-13T08:58:20.378Z","desc":"由微信开发的高效，小型移动键值存储框架。 适用于iOS，macOS和Android。","publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/Tencent/MMKV","used":true,"who":"lijinshanmx"},{"_id":"5bfe14999d2122308e7445bd","createdAt":"2018-11-28T04:07:53.345Z","desc":"iOS文本视图（UIView），可正确显示LaTeX，HTML，Markdown和YouTube / Vimeo链接。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxnobsosp4j30u00u0anj"],"publishedAt":"2018-11-28T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/tophat/RichTextView","used":true,"who":"lijinshanmx"},{"_id":"5bfe14c79d2122309ed76719","createdAt":"2018-11-28T04:08:39.488Z","desc":"ContainerView旨在通过手势的帮助，通过缩放的效果从底部添加到游戏的动态游戏，scrollView也可以控制。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxnod2aicjg30b40jqb2d"],"publishedAt":"2018-11-28T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/mrustaa/ContainerView","used":true,"who":"lijinshanmx"},{"_id":"5bfe17599d2122308e7445bf","createdAt":"2018-11-28T04:19:37.77Z","desc":"CrownControl是一个小巧的配件，可以在不抬起拇指的情况下滚动滚动内容。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxnod3otnug305k0c1qv6","https://ww1.sinaimg.cn/large/0073sXn7gy1fxnodaeedfg305k0c1b2a","https://ww1.sinaimg.cn/large/0073sXn7gy1fxnodfmhvsg305k0c1u12"],"publishedAt":"2018-11-28T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/huri000/CrownControl","used":true,"who":"lijinshanmx"},{"_id":"5bfe17799d2122308e7445c0","createdAt":"2018-11-28T04:20:09.26Z","desc":"快速实现控制器的转场和View的快速popover显示，并支持自定义动画、手势退场","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fxnodg826dg3057099h3j","https://ww1.sinaimg.cn/large/0073sXn7gy1fxnodhe5c9g30570994qq","https://ww1.sinaimg.cn/large/0073sXn7gy1fxnodits2eg3057099b2a"],"publishedAt":"2018-11-28T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/LoongerTao/TLTransitions","used":true,"who":"lijinshanmx"},{"_id":"5bfe17c39d2122309ed7671a","createdAt":"2018-11-28T04:21:23.423Z","desc":"适用于iOS的声明式，可链式和轻量级自动布局约束框架。","publishedAt":"2018-11-28T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/jVirus/constraints-kit","used":true,"who":"lijinshanmx"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5c25d4e79d21221e8ada8663
         * createdAt : 2018-12-28T07:46:47.830Z
         * desc : 在iOS中将自定义视图显示为弹出窗口。
         * images : ["https://ww1.sinaimg.cn/large/0073sXn7ly1fymj3wu73jg30om1hcgoi","https://ww1.sinaimg.cn/large/0073sXn7ly1fymj3x5wueg30om1hcju4","https://ww1.sinaimg.cn/large/0073sXn7ly1fymj3xn7gkg30om1hcaet"]
         * publishedAt : 2018-12-28T00:00:00.0Z
         * source : chrome
         * type : iOS
         * url : https://github.com/JonyFang/FFPopup
         * used : true
         * who : lijinshanmx
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
