package com.renlz.jiyun.greeknews.beandao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/12/31.
 */

@Entity
public class IsChecks {

    int position;
    boolean isck;
    @Generated(hash = 1807776680)
    public IsChecks(int position, boolean isck) {
        this.position = position;
        this.isck = isck;
    }
    @Generated(hash = 1646933497)
    public IsChecks() {
    }
    public int getPosition() {
        return this.position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public boolean getIsck() {
        return this.isck;
    }
    public void setIsck(boolean isck) {
        this.isck = isck;
    }
}
