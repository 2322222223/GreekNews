package com.renlz.jiyun.greeknews.beandao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2019/1/1.
 */

@Entity
public class DataBean {

    @Id(autoincrement = true)
    Long id;
    int mid;
    int itemTpe;
    String webUrl;
    String url;
    String title;
    @Generated(hash = 1138902513)
    public DataBean(Long id, int mid, int itemTpe, String webUrl, String url,
            String title) {
        this.id = id;
        this.mid = mid;
        this.itemTpe = itemTpe;
        this.webUrl = webUrl;
        this.url = url;
        this.title = title;
    }
    @Generated(hash = 908697775)
    public DataBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getMid() {
        return this.mid;
    }
    public void setMid(int mid) {
        this.mid = mid;
    }
    public int getItemTpe() {
        return this.itemTpe;
    }
    public void setItemTpe(int itemTpe) {
        this.itemTpe = itemTpe;
    }
    public String getWebUrl() {
        return this.webUrl;
    }
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
