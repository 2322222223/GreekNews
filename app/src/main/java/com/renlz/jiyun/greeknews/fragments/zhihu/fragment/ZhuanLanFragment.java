package com.renlz.jiyun.greeknews.fragments.zhihu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.activitys.ZlInfoActivity;
import com.renlz.jiyun.greeknews.adapters.ZhuanLanAdapter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.beans.SectionList;
import com.renlz.jiyun.greeknews.beans.ZhuanLanList;
import com.renlz.jiyun.greeknews.itemtouchhelper.MyItemTouchHelperAdapter;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/27.
 */

public class ZhuanLanFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView {

    private ZhiHuPresenter<ZhiHuView> mPresenter;
    ArrayList<SectionList.DataBean> mList = new ArrayList<>();
    private XRecyclerView mRlvZhuanlan;
    private ZhuanLanAdapter mAdapter;

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
            case SECTIONLIST:
                SectionList sectionList = (SectionList) o;
                List<SectionList.DataBean> list = sectionList.getData();
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
        if(mAdapter!= null){
            mAdapter.setOnItemClickListener(new ZhuanLanAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                    Intent intent = new Intent(mContext, ZlInfoActivity.class);
                    intent.putExtra("zltitle",mAdapter.mList.get(position).getName());
                    intent.putExtra("zlid",mAdapter.mList.get(position).getId());
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    protected void initAdapter() {
        mAdapter = new ZhuanLanAdapter(mContext, mList);
        mRlvZhuanlan.setAdapter(mAdapter);
        MyItemTouchHelperAdapter helperAdapter = new MyItemTouchHelperAdapter(mAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(helperAdapter);
        helper.attachToRecyclerView(mRlvZhuanlan);
    }

    @Override
    protected void initData() {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        mPresenter.setNewsData(null, null, EnumApi.SECTIONLIST);
        mRlvZhuanlan.setLayoutManager(new GridLayoutManager(mContext,2));
    }

    @Override
    protected void initView(View view) {

        mRlvZhuanlan = (XRecyclerView) view.findViewById(R.id.rlv_zhuanlan);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_zhuanlan;
    }
}
