package com.renlz.jiyun.greeknews.fragments.zhihu;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.adapters.VpAdapter;
import com.renlz.jiyun.greeknews.base.fragment.SimpleFragment;
import com.renlz.jiyun.greeknews.fragments.zhihu.fragment.ReMenFragment;
import com.renlz.jiyun.greeknews.fragments.zhihu.fragment.RiBaoFragment;
import com.renlz.jiyun.greeknews.fragments.zhihu.fragment.ZhuTiFragment;
import com.renlz.jiyun.greeknews.fragments.zhihu.fragment.ZhuanLanFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/12/27.
 */

public class ZhiHuFragment extends SimpleFragment {

    private ArrayList<Fragment> mList;
    private TabLayout mTabZhihu;
    private ViewPager mVpZhihu;
    private MainActivity mActivity;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {
        VpAdapter vpAdapter = new VpAdapter(getChildFragmentManager(), mList);
        mVpZhihu.setAdapter(vpAdapter);
        mTabZhihu.setupWithViewPager(mVpZhihu);
        mTabZhihu.getTabAt(0).setText("日报");
        mTabZhihu.getTabAt(1).setText("主题");
        mTabZhihu.getTabAt(2).setText("专栏");
        mTabZhihu.getTabAt(3).setText("热门");
    }

    @Override
    protected void initData() {
        mActivity = (MainActivity) getActivity();
        mList = new ArrayList<>();
        mList.add(new RiBaoFragment());
        mList.add(new ZhuTiFragment());
        mList.add(new ZhuanLanFragment());
        mList.add(new ReMenFragment());
    }

    @Override
    protected void initView(View view) {
        mTabZhihu = (TabLayout) view.findViewById(R.id.tab_zhihu);
        mVpZhihu = (ViewPager) view.findViewById(R.id.vp_zhihu);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_zhihu;
    }
}
