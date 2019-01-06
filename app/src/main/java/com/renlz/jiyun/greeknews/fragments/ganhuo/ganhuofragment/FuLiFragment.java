package com.renlz.jiyun.greeknews.fragments.ganhuo.ganhuofragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.activitys.SistersActivity;
import com.renlz.jiyun.greeknews.adapters.FuLiAdapter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.beans.FuliList;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;
import com.umeng.commonsdk.statistics.common.MLog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/29.
 */

public class FuLiFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView, XRecyclerView.LoadingListener {

    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private FuLiAdapter mAdapter;
    private XRecyclerView mRecyclerviewFuli;
    private StaggeredGridLayoutManager mManager;
    private int mPage = 1;

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
            case SISTERLIST:
                FuliList fuliList = (FuliList) o;
                List<FuliList.ResultsBean> list = fuliList.getResults();
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
            mAdapter.setOnItemClicklistener(new FuLiAdapter.OnItemClickListener() {
                @Override
                public void OnItemClilck(int position) {
                    Intent intent = new Intent(mContext, SistersActivity.class);
                    intent.putExtra("id", position);
                    intent.putExtra("sister", mAdapter.mList.get(position).getUrl());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void initAdapter() {
        mAdapter = new FuLiAdapter(mContext, new ArrayList<FuliList.ResultsBean>());
        mRecyclerviewFuli.setAdapter(mAdapter);
        mRecyclerviewFuli.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mManager.invalidateSpanAssignments(); //防止第一行到顶部有空白区域
            }
        });
        mRecyclerviewFuli.setLoadingListener(this);
    }

    @Override
    protected void initData() {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        mPresenter.setNewsData("data/%E7%A6%8F%E5%88%A9/10/" + mPage, null, EnumApi.SISTERLIST);
    }

    @Override
    protected void initView(View view) {

        mRecyclerviewFuli = (XRecyclerView) view.findViewById(R.id.recyclerview_fuli);
        mManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerviewFuli.setLayoutManager(mManager);
        mManager.setItemPrefetchEnabled(false);
        mRecyclerviewFuli.addItemDecoration(new SpaceItemDecoration(1, 5));

    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_fuli;
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        mPresenter.setNewsData("data/%E7%A6%8F%E5%88%A9/10/" + mPage, null, EnumApi.SISTERLIST);
        mRecyclerviewFuli.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        mPage++;
        mPresenter.setNewsData("data/%E7%A6%8F%E5%88%A9/10/" + mPage, null, EnumApi.SISTERLIST);
        mRecyclerviewFuli.loadMoreComplete();
    }


    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int space;
        private boolean includeEdge;


        public SpaceItemDecoration(int spanCount, int space) {
            this.spanCount = spanCount;
            this.space = space;

        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            outRect.left = space;
            outRect.right = space;
            if (position != 0 && position != 1) {
                outRect.top = 2 * space;
            } else {
                outRect.top = space;
            }


        }
    }
}
