package com.renlz.jiyun.greeknews.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.renlz.jiyun.greeknews.base.presenter.BasePresenter;
import com.renlz.jiyun.greeknews.utils.EventBusUtil;

/**
 * Created by Administrator on 2018/12/26.
 */

public abstract class BaseFragment<V, P extends BasePresenter<V>> extends SimpleFragment {

    public P mPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void initPresenter() {
        super.initPresenter();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter = null;
        }
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }
}
