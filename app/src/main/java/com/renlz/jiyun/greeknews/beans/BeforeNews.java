package com.renlz.jiyun.greeknews.beans;

import java.util.List;

/**
 * Created by Administrator on 2018/12/26.
 */

public class BeforeNews {

    /**
     * date : 20180208
     * stories : [{"images":["https://pic1.zhimg.com/v2-23100ce6c284adcea90816cf662e975c.jpg"],"type":0,"id":9670331,"ga_prefix":"020822","title":"小事 · 因为老用户 = 狗，所以我把移动给告了"},{"images":["https://pic1.zhimg.com/v2-b1ca5a53eebc064323e7f5b185d52d88.jpg"],"type":0,"id":9670362,"ga_prefix":"020821","title":"长大后，才明白《少年包青天》的结局并不快乐"},{"images":["https://pic4.zhimg.com/v2-394ada27ef323f53ca3e08119d9b5ae3.jpg"],"type":0,"id":9668298,"ga_prefix":"020819","title":"父母把你当成生活的全部，你该怎么办？"},{"images":["https://pic1.zhimg.com/v2-793811acf44c635ee79351ad524c5498.jpg"],"type":0,"id":9669569,"ga_prefix":"020818","title":"「一抹脖子就立马倒地」，影视剧的情节都是假的吧？"},{"images":["https://pic2.zhimg.com/v2-4263541cd73e0371a6bde6185163b451.jpg"],"type":0,"id":9670358,"ga_prefix":"020817","title":"我们潜入吃鸡外挂群看了首场神仙斗法比赛，第一名把锁血挂都打死了"},{"images":["https://pic1.zhimg.com/v2-c01236da5b8cea0d0145b9ac7ffd0874.jpg"],"type":0,"id":9667226,"ga_prefix":"020816","title":"强行吃辣会有怎样的后果？"},{"images":["https://pic2.zhimg.com/v2-b96a40945f7510ee9a7fce4cfa8e2509.jpg"],"type":0,"id":9668079,"ga_prefix":"020814","title":"医生心中的好医生，和患者心中的好医生，也许区别在此"},{"images":["https://pic1.zhimg.com/v2-886244da02f8080b8068c6c399c85bb4.jpg"],"type":0,"id":9670016,"ga_prefix":"020812","title":"大误 · 小伙子，你这样怕是要完的"},{"images":["https://pic4.zhimg.com/v2-78592608c30de9557bd454da2ab7cabb.jpg"],"type":0,"id":9668343,"ga_prefix":"020810","title":"- 经常喝骨头汤最补什么？钙还是蛋白质？\r\n- 补体重"},{"images":["https://pic3.zhimg.com/v2-ec044094e7f2a861a356053c7bbe16de.jpg"],"type":0,"id":9668235,"ga_prefix":"020809","title":"爸妈，能不能别总是在我面前吵架？"},{"images":["https://pic2.zhimg.com/v2-29ca000094dd393ec186678269aedec5.jpg"],"type":0,"id":9666615,"ga_prefix":"020808","title":"乙女和腐女：女性向游戏里的两个宿敌"},{"images":["https://pic2.zhimg.com/v2-ff98274ca15310f6e8ce0ac106ca92f1.jpg"],"type":0,"id":9670268,"ga_prefix":"020807","title":"很多人不懂我们这一行是做什么的，一般我都这么解释"},{"images":["https://pic1.zhimg.com/v2-bf2dfdfa4023fafbe62cc3fb5c7dc67c.jpg"],"type":0,"id":9670210,"ga_prefix":"020807","title":"其实唐僧有五个徒弟，其中两个被妖怪吃了"},{"images":["https://pic3.zhimg.com/v2-addeaf3571c15687326118220e380ca6.jpg"],"type":0,"id":9668353,"ga_prefix":"020807","title":"学心理学的人养孩子，和常人有什么不同？"},{"images":["https://pic1.zhimg.com/v2-a08490666ec35b6de2cf8b45ceb231fc.jpg"],"type":0,"id":9670182,"ga_prefix":"020806","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-23100ce6c284adcea90816cf662e975c.jpg"]
         * type : 0
         * id : 9670331
         * ga_prefix : 020822
         * title : 小事 · 因为老用户 = 狗，所以我把移动给告了
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
