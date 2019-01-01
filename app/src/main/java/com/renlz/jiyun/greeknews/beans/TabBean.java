package com.renlz.jiyun.greeknews.beans;

/**
 * Created by Administrator on 2018/12/31.
 */

public class TabBean {
    String title;
    boolean isck;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIsck() {
        return isck;
    }

    public void setIsck(boolean isck) {
        this.isck = isck;
    }

    public TabBean(String title, boolean isck) {
        this.title = title;
        this.isck = isck;
    }
}

