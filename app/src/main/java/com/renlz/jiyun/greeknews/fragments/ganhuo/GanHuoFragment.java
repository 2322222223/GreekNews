package com.renlz.jiyun.greeknews.fragments.ganhuo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.adapters.VpAdapter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.fragments.ganhuo.ganhuofragment.GanHuoFragments;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.view.ZhiHuView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/12/27.
 */

public class GanHuoFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView {

    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private View view;
    private TabLayout mTabGanhuo;
    private ViewPager mVpGanhuo;

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProGressBar() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void getNewData(Object o, Object o1) {

    }

    @Override
    protected ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        ArrayList<Fragment> list = new ArrayList<>();
        ArrayList<String> titlelist = new ArrayList<>();
        titlelist.add("Android");
        titlelist.add("iOS");
        titlelist.add("前端");

        for (int i = 0; i < titlelist.size(); i++) {
            GanHuoFragments ganHuoFragments = GanHuoFragments.newInstance(titlelist.get(i));
            list.add(ganHuoFragments);
        }
        VpAdapter vpAdapter = new VpAdapter(getChildFragmentManager(), list);
        mVpGanhuo.setAdapter(vpAdapter);
        mTabGanhuo.setupWithViewPager(mVpGanhuo);
        for (int i = 0; i < titlelist.size(); i++) {
            mTabGanhuo.getTabAt(i).setText(titlelist.get(i));
        }
    }

    @Override
    protected void initView(View view) {
        mTabGanhuo = (TabLayout) view.findViewById(R.id.tab_ganhuo);
        mVpGanhuo = (ViewPager) view.findViewById(R.id.vp_ganhuo);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_ganhuo;
    }
}
