package com.renlz.jiyun.greeknews.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.utils.Event;
import com.renlz.jiyun.greeknews.utils.EventBusUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/12/26.
 */

public abstract class SimpleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createLayoutId());
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        viewCreated();
        initView();
        initData();
        initAdapter();
        initListener();
    }


    protected abstract void initListener();

    protected abstract void initAdapter();

    protected abstract void initData();

    protected abstract void initView();

    public void viewCreated() {

    }


    protected abstract int createLayoutId();


    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    protected void receiveEvent(Event event) {

    }


    protected void receiveStickyEvent(Event event) {

    }
}
