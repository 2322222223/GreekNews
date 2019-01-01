package com.renlz.jiyun.greeknews.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/31.
 */

public class CountList implements Serializable {
    /**
     * long_comments : 20
     * popularity : 681
     * short_comments : 86
     * comments : 106
     */

    private int long_comments;
    private int popularity;
    private int short_comments;
    private int comments;

    public int getLong_comments() {
        return long_comments;
    }

    public void setLong_comments(int long_comments) {
        this.long_comments = long_comments;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getShort_comments() {
        return short_comments;
    }

    public void setShort_comments(int short_comments) {
        this.short_comments = short_comments;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
