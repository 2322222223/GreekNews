package com.renlz.jiyun.greeknews.fragments.zhihu.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldoublem.loadingviewlib.view.LVEatBeans;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.adapters.HostAdapter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.beans.HostList;
import com.renlz.jiyun.greeknews.itemtouchhelper.MyItemTouchHelperAdapter;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/27.
 */

public class ReMenFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView {

    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private View view;
    private PullLoadMoreRecyclerView mPull;
    ArrayList<HostList.RecentBean> mList=new ArrayList<>();
    private HostAdapter mAdapter;

    @Override
    public void showProgressBar() {
        ((MainActivity)mActivity).showProgressAnim();
    }

    @Override
    public void hideProGressBar() {
        ((MainActivity)mActivity).hideProgressAnim();
    }

    @Override
    public void showError(String error) {
        Utils.getInstance().getToast(1,error);
    }

    @Override
    public void getNewData(Object o, Object o1) {
        EnumApi enumApi = (EnumApi) o1;
        switch (enumApi) {
            case HOTLIST:
                HostList hostList = (HostList) o;
                List<HostList.RecentBean> list = hostList.getRecent();
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
        mPull.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPull.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                mPull.setPullLoadMoreCompleted();
            }
        });
    }

    @Override
    protected void initAdapter() {
        mAdapter = new HostAdapter(mContext, mList);
        mPull.setAdapter(mAdapter);
        MyItemTouchHelperAdapter callback = new MyItemTouchHelperAdapter(mAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mPull.getRecyclerView());
    }

    @Override
    protected void initData() {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        mPresenter.setNewsData(null, null, EnumApi.HOTLIST);

    }

    @Override
    protected void initView(View view) {

        mPull = (PullLoadMoreRecyclerView) view.findViewById(R.id.pull);
        mPull.setLinearLayout();
        mPull.setFooterViewText("热门正在加载");
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_remen;
    }
}
