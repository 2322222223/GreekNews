package com.renlz.jiyun.greeknews.fragments.comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.adapters.ShortAdapter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.beans.ShortCommentes;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public class ShortC_Fragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView {
    private View view;
    private RecyclerView mShortRecyxlerview;
    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private List<ShortCommentes.CommentsBean> mComments;
    private ShortAdapter mAdapter;

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
    public void getNewData(Object o, Object o1) {
        EnumApi enumApi = (EnumApi) o1;
        switch (enumApi) {
            case SHORTCONMMENTINFO:
                ShortCommentes commentes = (ShortCommentes) o;
                mComments = commentes.getComments();
                mAdapter.addList(mComments);
                break;
        }
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
        mAdapter = new ShortAdapter(mContext, new ArrayList<ShortCommentes.CommentsBean>());
        mShortRecyxlerview.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        int id = getArguments().getInt("id");
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        mPresenter.setNewsData("",id, EnumApi.SHORTCONMMENTINFO);
    }

    @Override
    protected void initView(View view) {

        mShortRecyxlerview = (RecyclerView) view.findViewById(R.id.short_recyxlerview);
        mShortRecyxlerview.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_shortc;
    }
}
