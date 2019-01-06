package com.renlz.jiyun.greeknews.fragments.ganhuo.ganhuofragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.activitys.WxInfoActivity;
import com.renlz.jiyun.greeknews.adapters.GanHuoAdapter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.beans.GanHuoList;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Constants;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/29.
 */

public class FrontFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView {


    private ZhiHuPresenter<ZhiHuView> mPresenter;
    int mPage = 1;
    int num = 10;
    String mTitle = "前端";
    private View view;
    GanHuoAdapter mAdapter;
    private PullLoadMoreRecyclerView mRecyclerviewIos;
    private PullLoadMoreRecyclerView mRecyclerviewFront;

    @Override
    public void showProgressBar() {
        ((MainActivity) mActivity).showProgressAnim();
    }

    @Override
    public void hideProGressBar() {
        ((MainActivity) mActivity).hideProgressAnim();
    }

    @Override
    public void showError(String error) {
        Utils.getInstance().getToast(2, error);
    }

    @Override
    public void getNewData(Object o, Object o1) {
        EnumApi enumApi = (EnumApi) o1;
        switch (enumApi) {
            case GANHUOS:
                GanHuoList huoList = (GanHuoList) o;
                List<GanHuoList.ResultsBean> list = huoList.getResults();
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
        if (mAdapter != null) {
            mAdapter.setOnItemClickListener(new GanHuoAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(int poisiton) {
                    Intent intent = new Intent(mContext, WxInfoActivity.class);
                    intent.putExtra("image", mAdapter.mList.get(poisiton).getImages().get(0));
                    intent.putExtra("title", mAdapter.mList.get(poisiton).getDesc());
                    intent.putExtra("url", mAdapter.mList.get(poisiton).getUrl());
                    intent.putExtra("type", Constants.TYPE_FRONT);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void initAdapter() {
        mAdapter = new GanHuoAdapter(mContext, new ArrayList<GanHuoList.ResultsBean>());
        mRecyclerviewFront.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        mPresenter.setNewsData("", new String[]{mTitle, num + "", mPage + ""}, EnumApi.GANHUOS);
        mPresenter.setNewsData("random/data/福利/20", null, EnumApi.RANDOMSISTER);
    }

    @Override
    protected void initView(View view) {


        mRecyclerviewFront = (PullLoadMoreRecyclerView) view.findViewById(R.id.recyclerview_front);
        mRecyclerviewFront.setLinearLayout();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_front;
    }

}
