package com.renlz.jiyun.greeknews.fragments.comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.adapters.LongAdapter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.beans.LongComments;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public class LongC_Fragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView {

    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private View view;
    private RecyclerView mLongRecyxlerview;
    private LongAdapter mAdapter;

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
            case LONGCOMMENTINFO:
                LongComments comments = (LongComments) o;
                List<LongComments.CommentsBean> list = comments.getComments();
                mAdapter.addList(list);
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
        mAdapter = new LongAdapter(mContext, new ArrayList<LongComments.CommentsBean>());
        mLongRecyxlerview.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        mPresenter.setNewsData("", id, EnumApi.LONGCOMMENTINFO);
    }

    @Override
    protected void initView(View view) {

        mLongRecyxlerview = (RecyclerView) view.findViewById(R.id.long_recyxlerview);
        mLongRecyxlerview.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_longc;
    }
}
