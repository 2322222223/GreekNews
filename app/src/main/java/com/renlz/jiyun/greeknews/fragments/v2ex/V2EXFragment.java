package com.renlz.jiyun.greeknews.fragments.v2ex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.adapters.Vp2Adapter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.beans.NodeBean;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.V2EXPresenter;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.V2EXView;
import com.renlz.jiyun.greeknews.view.ZhiHuView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/12/27.
 */

public class V2EXFragment extends BaseFragment<V2EXView, V2EXPresenter<V2EXView>> implements V2EXView, View.OnClickListener {
    private View view;
    private TabLayout mTabV2;
    private ImageView mMenuV2;
    private ViewPager mVpV2;
    private V2EXPresenter<V2EXView> mPresenter;
    private Vp2Adapter mAdapter;
    private ArrayList<String> mList;
    private ArrayList<String> mTitlelist;

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProGressBar() {

    }

    @Override
    public void showError(String error) {
        Utils.getInstance().getToast(1,error);
    }

    @Override
    public void getData(Object o, Object o1) {
        EnumApi enumApi = (EnumApi) o1;
        switch (enumApi) {
            case V2EXTYPE:
                NodeBean bean = (NodeBean) o;
                ArrayList<Fragment> flist = new ArrayList<>();

//                mAdapter = new Vp2Adapter(getChildFragmentManager(), flist, mList);
//                mVpV2.setAdapter(mAdapter);
//                mTabV2.setupWithViewPager(mVpV2);
                break;
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected V2EXPresenter<V2EXView> createPresenter() {
        return new V2EXPresenter<>();
    }

    @Override
    protected void initData() {
        mPresenter = createPresenter();
        mList = new ArrayList<>();
        mList.add("tech");
        mList.add("creative");
        mList.add("play");
        mList.add("apple");
        mList.add("jobs");
        mList.add("deals");
        mList.add("city");
        mList.add("qna");
        mList.add("hot");
        mList.add("all");
        mList.add("r2");
        mTitlelist = new ArrayList<>();
        mTitlelist.add("技术");
        mTitlelist.add("创意");
        mTitlelist.add("好玩");
        mTitlelist.add("Apple");
        mTitlelist.add("酷工作");
        mTitlelist.add("交易");
        mTitlelist.add("城市");
        mTitlelist.add("问与答");
        mTitlelist.add("最热");
        mTitlelist.add("全部");
        mTitlelist.add("R2");
        ArrayList<Fragment>flist=new ArrayList<>();
        for (int i = 0; i < mTitlelist.size(); i++) {
            V2EXFragments v2EXFragments = V2EXFragments.newInstance(mList.get(i));
            flist.add(v2EXFragments);
        }

        Vp2Adapter vp2Adapter = new Vp2Adapter(getChildFragmentManager(), flist, mTitlelist);
        mVpV2.setAdapter(vp2Adapter);
        mTabV2.setupWithViewPager(mVpV2);
    }

    @Override
    protected void initView(View view) {
        mTabV2 = (TabLayout) view.findViewById(R.id.tab_v2);
        mMenuV2 = (ImageView) view.findViewById(R.id.menu_v2);
        mMenuV2.setOnClickListener(this);
        mVpV2 = (ViewPager) view.findViewById(R.id.vp_v2);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_v2ex;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.menu_v2:

                break;
        }
    }
}
