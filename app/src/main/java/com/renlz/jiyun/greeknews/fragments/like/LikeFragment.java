package com.renlz.jiyun.greeknews.fragments.like;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.adapters.LikeAdapter;
import com.renlz.jiyun.greeknews.base.fragment.SimpleFragment;
import com.renlz.jiyun.greeknews.beandao.DataBean;
import com.renlz.jiyun.greeknews.itemtouchhelper.MyItemTouchHelperAdapter;
import com.renlz.jiyun.greeknews.utils.DaoUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/27.
 */

public class LikeFragment extends SimpleFragment {
    private PullLoadMoreRecyclerView mRecyclerviewlike;
    private List<DataBean> mList;
    private LikeAdapter mLikeAdapter;

    @Override
    protected void initListener() {
        MyItemTouchHelperAdapter callback = new MyItemTouchHelperAdapter(mLikeAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerviewlike.getRecyclerView());
        mRecyclerviewlike.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                initData();
                mRecyclerviewlike.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                mRecyclerviewlike.setPullLoadMoreCompleted();
            }
        });
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {
        if (DaoUtil.getInstance().dataSelect().size() > 0) {
            mList = DaoUtil.getInstance().dataSelect();
            mLikeAdapter = new LikeAdapter(mContext, mList);
            mRecyclerviewlike.setAdapter(mLikeAdapter);
        }
    }

    @Override
    protected void initView(View view) {

        mRecyclerviewlike = (PullLoadMoreRecyclerView) view.findViewById(R.id.recyclerviewlike);
        mRecyclerviewlike.setLinearLayout();
        mRecyclerviewlike.setFooterViewText("------我是有底线的------");
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_like;
    }
}
