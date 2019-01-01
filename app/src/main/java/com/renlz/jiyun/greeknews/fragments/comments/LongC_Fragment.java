package com.renlz.jiyun.greeknews.fragments.comments;

import android.view.View;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.view.ZhiHuView;

/**
 * Created by Administrator on 2018/12/31.
 */

public class LongC_Fragment extends BaseFragment<ZhiHuView,ZhiHuPresenter<ZhiHuView>>implements ZhiHuView{
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
        return null;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_longc;
    }
}
