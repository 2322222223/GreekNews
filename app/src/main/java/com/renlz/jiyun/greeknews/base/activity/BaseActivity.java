package com.renlz.jiyun.greeknews.base.activity;

import com.renlz.jiyun.greeknews.base.presenter.BasePresenter;
import com.renlz.jiyun.greeknews.utils.EventBusUtil;

/**
 * Created by Administrator on 2018/12/26.
 */

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends SimpleActivity {

    private P mPresenter;

    @Override
    public void viewCreated() {
        super.viewCreated();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter = null;
        }

        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }
}
