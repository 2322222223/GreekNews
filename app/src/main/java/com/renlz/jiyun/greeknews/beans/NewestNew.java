package com.renlz.jiyun.greeknews.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/12/26.
 */

public class NewestNew implements Serializable{

    /**
     * date : 20181227
     * stories : [{"images":["https://pic4.zhimg.com/v2-828bfccfc98a72eac68bb05dff88c533.jpg"],"type":0,"id":9704564,"ga_prefix":"122716","title":"为什么前两年大热的 VR 创业突然冷了？"},{"images":["https://pic4.zhimg.com/v2-101823cc353e0bba418918376b27d0e7.jpg"],"type":0,"id":9704486,"ga_prefix":"122712","title":"大误 · 没有人能抵挡写在煎饼上的情书"},{"title":"- 机长，您能开快一点吗？\r\n- 可以啊，咱们提前 90 秒落地","ga_prefix":"122710","images":["https://pic2.zhimg.com/v2-9978fcef3059459e01c1d8938be2eef1.jpg"],"multipic":true,"type":0,"id":9704525},{"title":"2018 「年度被颠覆公司」：苹果","ga_prefix":"122709","images":["https://pic1.zhimg.com/v2-79fc9292404fc43bd4371fe7bcf2ed40.jpg"],"multipic":true,"type":0,"id":9704547},{"images":["https://pic1.zhimg.com/v2-0999f3784f72c1204fa994f723db21bc.jpg"],"type":0,"id":9704481,"ga_prefix":"122708","title":"乘扶梯应该「左行右立」吗？现在日本开始学习中国了"},{"images":["https://pic3.zhimg.com/v2-3d8dbd09040d5e061aca8993ac2d5366.jpg"],"type":0,"id":9704513,"ga_prefix":"122707","title":"在我看来，权健可能开创了「神药」销售第 5 个时代"},{"images":["https://pic3.zhimg.com/v2-3e5839907b87324effa286da388f424e.jpg"],"type":0,"id":9704549,"ga_prefix":"122707","title":"2018 年度盘点 · 你所在的行业和领域发生了哪些大事？"},{"images":["https://pic3.zhimg.com/v2-9f9a98cd8d6e2c1092d67fffe06e2d12.jpg"],"type":0,"id":9704446,"ga_prefix":"122706","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-93488477d4332a67c3cbff919591ff4c.jpg","type":0,"id":9704547,"ga_prefix":"122709","title":"2018 「年度被颠覆公司」：苹果"},{"image":"https://pic3.zhimg.com/v2-5e0236b83faf75413ae502f573178266.jpg","type":0,"id":9704513,"ga_prefix":"122707","title":"在我看来，权健可能开创了「神药」销售第 5 个时代"},{"image":"https://pic4.zhimg.com/v2-f92aeac0f557e5044d881a45a9feded3.jpg","type":0,"id":9704538,"ga_prefix":"122621","title":"一段被饭勺耽误的历史，成就了这部电影的假装深刻"},{"image":"https://pic3.zhimg.com/v2-7444d626aefcdef8c0f262679c3bdf2a.jpg","type":0,"id":9704498,"ga_prefix":"122615","title":"看了下权健的保健品，我有点想笑"},{"image":"https://pic3.zhimg.com/v2-bee186b065b8e04ddc3cb7e60f245956.jpg","type":0,"id":9704468,"ga_prefix":"122614","title":"少女失踪 6 年，与父子生三孩并精神分裂，暴露了哪些问题？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

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

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean  implements Serializable{
        /**
         * images : ["https://pic4.zhimg.com/v2-828bfccfc98a72eac68bb05dff88c533.jpg"]
         * type : 0
         * id : 9704564
         * ga_prefix : 122716
         * title : 为什么前两年大热的 VR 创业突然冷了？
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
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

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic1.zhimg.com/v2-93488477d4332a67c3cbff919591ff4c.jpg
         * type : 0
         * id : 9704547
         * ga_prefix : 122709
         * title : 2018 「年度被颠覆公司」：苹果
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

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
    }
}
