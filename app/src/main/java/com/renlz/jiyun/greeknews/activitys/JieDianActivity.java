package com.renlz.jiyun.greeknews.activitys;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.base.activity.BaseActivity;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.view.ZhiHuView;

/**
 * Created by Administrator on 2019/1/2.
 */

public class JieDianActivity extends BaseActivity<ZhiHuView,ZhiHuPresenter<ZhiHuView>>implements ZhiHuView {

    private ZhiHuPresenter<ZhiHuView> mPresenter;

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProGressBar() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void getNewData(Object o, Object o1) {

    }

    @Override
    protected ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_jiedian;
    }
}
