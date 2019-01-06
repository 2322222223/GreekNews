package com.renlz.jiyun.greeknews.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.adapters.ZhuanLanAdapter;
import com.renlz.jiyun.greeknews.adapters.ZhuanLanListAdapter;
import com.renlz.jiyun.greeknews.base.activity.BaseActivity;
import com.renlz.jiyun.greeknews.beans.NewestNew;
import com.renlz.jiyun.greeknews.beans.ZhuanLanList;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/4.
 */

public class ZlInfoActivity extends BaseActivity<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView {

    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private int mId;
    private ZhuanLanListAdapter mListAdapter;
    /**
     * 知乎日报
     */
    private TextView mTbTitle;
    private Toolbar mToolbarZl;
    private PullLoadMoreRecyclerView mZlrecyclerview;
    private String mZltitle;

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProGressBar() {

    }

    @Override
    public void showError(String error) {
        Utils.getInstance().getToast(1, error);
    }

    @Override
    public void getNewData(Object o, Object o1) {
        EnumApi enumApi = (EnumApi) o1;
        switch (enumApi) {
            case ZHUANLANLIST:
                NewestNew newestNew = (NewestNew) o;
                List<NewestNew.StoriesBean> stories = newestNew.getStories();
                mListAdapter.addList(stories);
                break;
        }
    }

    @Override
    protected ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
    }

    @Override
    protected void initListener() {
        mToolbarZl.setNavigationOnClickListener (v->finish());
        if (mListAdapter != null) {
            mListAdapter.setOnItemClickListener(new ZhuanLanListAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                    Intent intent = new Intent(ZlInfoActivity.this, InfoActivity.class);
                    intent.putExtra("bean", mListAdapter.mList.get(position).getId());
                    startActivity(intent);
                }
            });

        }
    }

    @Override
    protected void initAdapter() {
        mListAdapter = new ZhuanLanListAdapter(this, new ArrayList<NewestNew.StoriesBean>());
        mZlrecyclerview.setAdapter(mListAdapter);
        mZlrecyclerview.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPresenter.setNewsData("", mId, EnumApi.ZHUANLANLIST);
                mZlrecyclerview.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                mZlrecyclerview.setPullLoadMoreCompleted();
            }
        });
    }

    @Override
    protected void initData() {
        mZltitle = getIntent().getStringExtra("zltitle");
        mId = getIntent().getIntExtra("zlid", 0);
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        mPresenter.setNewsData("", mId, EnumApi.ZHUANLANLIST);
        mTbTitle.setText(mZltitle);
    }

    @Override
    protected void initView() {

        mTbTitle = (TextView) findViewById(R.id.tb_title);
        mToolbarZl = (Toolbar) findViewById(R.id.toolbar_zl);
        mZlrecyclerview = (PullLoadMoreRecyclerView) findViewById(R.id.zlrecyclerview);
        mZlrecyclerview.setLinearLayout();
        mToolbarZl.setTitle("");
        setSupportActionBar(mToolbarZl);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mZlrecyclerview.setFooterViewText("我的有底线的");
    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_zlinfo;
    }

}
