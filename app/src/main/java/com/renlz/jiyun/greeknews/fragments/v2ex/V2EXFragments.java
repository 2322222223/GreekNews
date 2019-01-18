package com.renlz.jiyun.greeknews.fragments.v2ex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.V2EXPresenter;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.V2EXView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * Created by Administrator on 2019/1/2.
 */

public class V2EXFragments extends BaseFragment<V2EXView, V2EXPresenter<V2EXView>> implements V2EXView {

    private String mType;
    private PullLoadMoreRecyclerView mV2exRecyclerview;


    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProGressBar() {

    }

    @Override
    public void showError(String error) {
        Utils.getInstance().getToast(2,error);
    }

    @Override
    public void getData(Object o, Object o1) {

    }

    @Override
    protected V2EXPresenter<V2EXView> createPresenter() {
        return new V2EXPresenter<>();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {
        mType = mBundle.getString("title");
        mPresenter.setData("", mType, EnumApi.V2EXTYPE);
    }

    @Override
    protected void initView(View view) {

        mV2exRecyclerview = (PullLoadMoreRecyclerView) view.findViewById(R.id.v2exRecyclerview);
        mV2exRecyclerview.setLinearLayout();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragments_v2ex;
    }

    public static V2EXFragments newInstance(String s) {

        Bundle args = new Bundle();
        args.putString("title", s);
        V2EXFragments fragment = new V2EXFragments();
        fragment.setArguments(args);
        return fragment;
    }
}
