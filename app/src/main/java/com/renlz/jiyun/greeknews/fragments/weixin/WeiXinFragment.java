package com.renlz.jiyun.greeknews.fragments.weixin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.activitys.WxInfoActivity;
import com.renlz.jiyun.greeknews.adapters.WxnewAdapter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.beans.WxNews;
import com.renlz.jiyun.greeknews.itemtouchhelper.MyItemTouchHelperAdapter;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Constants;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/27.
 */

public class WeiXinFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView {
    private PullLoadMoreRecyclerView mWxrecyclerview;
    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private int mPage = 1;
    private WxnewAdapter mAdapter;
    ArrayList<WxNews.NewslistBean> mList = new ArrayList<>();

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
            case WXNEWS:
                WxNews wxNews = (WxNews) o;
                List<WxNews.NewslistBean> newslist = wxNews.getNewslist();
                mAdapter.addList(newslist);
                break;
            case WEIXINSEARCH:
                WxNews news = (WxNews) o;
                List<WxNews.NewslistBean> searchlist = news.getNewslist();
                mAdapter.setList(searchlist);
                break;
        }
    }

    @Override
    protected ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
    }

    @Override
    protected void initListener() {
        mWxrecyclerview.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                setHttp();
                mWxrecyclerview.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                mPage++;
                setHttp();
                mWxrecyclerview.setPullLoadMoreCompleted();
            }
        });

        MyItemTouchHelperAdapter callback = new MyItemTouchHelperAdapter(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mWxrecyclerview.getRecyclerView());

        if (mAdapter != null) {
            mAdapter.setOnItemClickListener(position -> {
                WxNews.NewslistBean bean = mAdapter.mList.get(position);
                Intent intent = new Intent(mContext, WxInfoActivity.class);
                intent.putExtra("image", bean.getPicUrl());
                intent.putExtra("url", bean.getUrl());
                intent.putExtra("title", bean.getTitle());
                intent.putExtra("type", Constants.TYPE_WEIXIN);
                startActivity(intent);
            });

        }

        ((MainActivity) mActivity).mSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Map<String, Object> map = new HashMap<>();
                map.put("key", "52b7ec3471ac3bec6846577e79f20e4c");
                map.put("num", "10");
                map.put("page", "1");
                map.put("word", query);
                mPresenter.setNewsData("wxnew/?", map, EnumApi.WEIXINSEARCH);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });
    }


    @Override
    protected void initAdapter() {
        mAdapter = new WxnewAdapter(mContext, mList);
        mWxrecyclerview.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        setHttp();
    }

    private void setHttp() {
        Map<String, Object> map = new HashMap();
        map.put("key", "52b7ec3471ac3bec6846577e79f20e4c");
        map.put("num", "15");
        map.put("page", mPage);
        mPresenter.setNewsData("wxnew/?", map, EnumApi.WXNEWS);
    }

    @Override
    protected void initView(View view) {

        mWxrecyclerview = (PullLoadMoreRecyclerView) view.findViewById(R.id.Wxrecyclerview);
        mWxrecyclerview.setLinearLayout();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_weixin;
    }
}
