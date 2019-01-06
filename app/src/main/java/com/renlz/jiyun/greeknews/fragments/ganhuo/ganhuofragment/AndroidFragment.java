package com.renlz.jiyun.greeknews.fragments.ganhuo.ganhuofragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.activitys.WxInfoActivity;
import com.renlz.jiyun.greeknews.adapters.GanHuoAdapter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.beans.GanHuoList;
import com.renlz.jiyun.greeknews.beans.SisterList;
import com.renlz.jiyun.greeknews.myapp.MyApp;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Constants;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/29.
 */

public class AndroidFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView {

    private ZhiHuPresenter<ZhiHuView> mPresenter;
    int mPage = 1;
    int num = 10;
    String mTitle = "Android";
    private GanHuoAdapter mAdapter;
    private View view;
    private PullLoadMoreRecyclerView mRecyclerviewAndroid;

    @Override
    public void showProgressBar() {
        if (mPage == 1)
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
            case RANDOMSISTER:
                SisterList sister = (SisterList) o;
                List<SisterList.ResultsBean> results = sister.getResults();
                String url = results.get((int) (Math.random() * 10) + 1).getUrl();
                LinearLayout inflate = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_header_image, null);
                ImageView im = inflate.findViewById(R.id.im_header_ganhuo);
                Glide.with(mContext).load(url).into(im);
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
                    intent.putExtra("type", Constants.TYPE_ANDROID);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void initAdapter() {
        mAdapter = new GanHuoAdapter(mContext, new ArrayList<GanHuoList.ResultsBean>());
        mRecyclerviewAndroid.setAdapter(mAdapter);
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

        mRecyclerviewAndroid = (PullLoadMoreRecyclerView) view.findViewById(R.id.recyclerview_android);
        mRecyclerviewAndroid.setLinearLayout();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_android;
    }

}
