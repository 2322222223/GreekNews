package com.renlz.jiyun.greeknews.fragments.xitu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.activitys.ShuJuZhiHuiNewsInfoActivity;
import com.renlz.jiyun.greeknews.adapters.ShuJuZhiHuiApadter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.beans.ShuJuZhiHuiList;
import com.renlz.jiyun.greeknews.itemtouchhelper.MyItemTouchHelperAdapter;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/31.
 */

public class XiTuFragments extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView {

    private String mType;
    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private int mPage = 1;
    private View view;
    private PullLoadMoreRecyclerView mPullrecyclerviewXitu;
    private ShuJuZhiHuiApadter mHuiApadter;

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
        Utils.getInstance().getToast(1, error);
    }

    @Override
    public void getNewData(Object o, Object o1) {
        EnumApi enumApi = (EnumApi) o1;
        switch (enumApi) {
            case SJZHNEWS:
                ShuJuZhiHuiList huiList = (ShuJuZhiHuiList) o;
                List<ShuJuZhiHuiList.RESULTBean.NewsListBean> newsList = huiList.getRESULT().getNewsList();
                mHuiApadter.addList(newsList);
                break;
        }
    }

    @Override
    protected ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
    }

    @Override
    protected void initListener() {
        MyItemTouchHelperAdapter callback = new MyItemTouchHelperAdapter(mHuiApadter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mPullrecyclerviewXitu.getRecyclerView());

        mPullrecyclerviewXitu.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                loadData();
                mPullrecyclerviewXitu.loadMore();
            }

            @Override
            public void onLoadMore() {
                mPage++;
                loadData();
                mPullrecyclerviewXitu.setPullLoadMoreCompleted();
            }
        });

        if (mHuiApadter != null) {
            mHuiApadter.setOnItemClickListener(new ShuJuZhiHuiApadter.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                    Intent intent = new Intent(mContext, ShuJuZhiHuiNewsInfoActivity.class);
                    intent.putExtra("newid",mHuiApadter.mList.get(position));
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void initAdapter() {
        mHuiApadter = new ShuJuZhiHuiApadter(mContext, new ArrayList<ShuJuZhiHuiList.RESULTBean.NewsListBean>());
        mPullrecyclerviewXitu.setAdapter(mHuiApadter);
    }

    @Override
    protected void initData() {
        mType = mBundle.getString("type");
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        loadData();
    }

    private void loadData() {
        Map<String, Object> map = new HashMap<>();
        map.put("appKey", "e2250569c2ca47a283cc00b0df971ad1");
        map.put("category", mType);
        map.put("page", mPage);
        mPresenter.setNewsData("", map, EnumApi.SJZHNEWS);
    }

    @Override
    protected void initView(View view) {

        mPullrecyclerviewXitu = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullrecyclerview_xitu);
        mPullrecyclerviewXitu.setLinearLayout();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragments_xitu;
    }

    public static XiTuFragments newInstance(String s) {

        Bundle args = new Bundle();
        args.putString("type", s);
        XiTuFragments fragment = new XiTuFragments();
        fragment.setArguments(args);
        return fragment;
    }
}
