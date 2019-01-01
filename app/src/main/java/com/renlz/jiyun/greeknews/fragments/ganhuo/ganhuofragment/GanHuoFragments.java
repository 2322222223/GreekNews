package com.renlz.jiyun.greeknews.fragments.ganhuo.ganhuofragment;

import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.adapters.GanHuoAdapter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.beans.GanHuoList;
import com.renlz.jiyun.greeknews.beans.SisterList;
import com.renlz.jiyun.greeknews.itemtouchhelper.MyItemTouchHelperAdapter;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/29.
 */

public class GanHuoFragments extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView {

    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private String mTitle;
    int num = 15;
    int page = 1;
    private GanHuoAdapter mGanHuoAdapter;
    private View view;
    private PullLoadMoreRecyclerView mRecyclerviewGanhuo;
    private String TAG="GanHuoFragments";


    @Override
    public void showProgressBar() {
        if (page == 1)
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
                mGanHuoAdapter.addList(list);
                break;
            case RANDOMSISTER:
                SisterList sister = (SisterList) o;
                List<SisterList.ResultsBean> results = sister.getResults();
                String url = results.get((int) (Math.random() * 10) + 1).getUrl();
                mGanHuoAdapter.addString(url);
                break;
        }
    }

    @Override
    protected ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
    }

    @Override
    protected void initListener() {
        MyItemTouchHelperAdapter callback = new MyItemTouchHelperAdapter(mGanHuoAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerviewGanhuo.getRecyclerView());
        mRecyclerviewGanhuo.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.setNewsData("", new String[]{mTitle, num + "", page + ""}, EnumApi.GANHUOS);
                mRecyclerviewGanhuo.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.setNewsData("", new String[]{mTitle, num + "", page + ""}, EnumApi.GANHUOS);
                mRecyclerviewGanhuo.setPullLoadMoreCompleted();
            }
        });
    }

    @Override
    protected void initAdapter() {
        mGanHuoAdapter = new GanHuoAdapter(mContext, new ArrayList<GanHuoList.ResultsBean>(),new ArrayList<SisterList.ResultsBean>());
        mRecyclerviewGanhuo.setAdapter(mGanHuoAdapter);

    }

    @Override
    protected void initData() {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        mTitle = mBundle.getString("title");
        mPresenter.setNewsData("random/data/福利/20", null, EnumApi.RANDOMSISTER);
        mPresenter.setNewsData("", new String[]{mTitle, num + "", page + ""}, EnumApi.GANHUOS);
    }

    @Override
    protected void initView(View view) {

        mRecyclerviewGanhuo = (PullLoadMoreRecyclerView) view.findViewById(R.id.recyclerview_ganhuo);
        mRecyclerviewGanhuo.setLinearLayout();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_ganhuos;
    }

    public static GanHuoFragments newInstance(String s) {

        Bundle args = new Bundle();
        args.putString("title", s);
        GanHuoFragments fragment = new GanHuoFragments();
        fragment.setArguments(args);
        return fragment;
    }
}
