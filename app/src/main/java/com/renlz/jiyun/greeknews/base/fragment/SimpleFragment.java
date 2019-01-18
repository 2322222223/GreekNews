package com.renlz.jiyun.greeknews.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public abstract class SimpleFragment extends Fragment {


    public Context mContext;
    public Activity mActivity;
    public Bundle mBundle;
    public boolean load;
    private boolean mRegisterEventBus;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mContext = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(createLayoutId(), null);
        mBundle = getArguments();
        load = true;
        initView(view);
        initPresenter();
        initData();
        initAdapter();
        initListener();
        return view;
    }

    public void initPresenter() {

    }

    protected abstract void initListener();

    protected abstract void initAdapter();

    protected abstract void initData();

    protected abstract void initView(View view);

    protected abstract int createLayoutId();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (getUserVisibleHint() && load) {
            userLoad();
        }
    }

    private void userLoad() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
    }

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
