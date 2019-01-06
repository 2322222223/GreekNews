package com.renlz.jiyun.greeknews.fragments.ganhuo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.adapters.VpAdapter;
import com.renlz.jiyun.greeknews.base.fragment.SimpleFragment;
import com.renlz.jiyun.greeknews.fragments.ganhuo.ganhuofragment.AndroidFragment;
import com.renlz.jiyun.greeknews.fragments.ganhuo.ganhuofragment.FrontFragment;
import com.renlz.jiyun.greeknews.fragments.ganhuo.ganhuofragment.FuLiFragment;
import com.renlz.jiyun.greeknews.fragments.ganhuo.ganhuofragment.IosFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/12/27.
 */

public class GanHuoFragment extends SimpleFragment {

    private TabLayout mTabGanhuo;
    private ViewPager mVpGanhuo;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {
        ArrayList<Fragment> list = new ArrayList<>();
        ArrayList<String> titlelist = new ArrayList<>();
        list.add(new AndroidFragment());
        list.add(new IosFragment());
        list.add(new FrontFragment());
        list.add(new FuLiFragment());
        VpAdapter vpAdapter = new VpAdapter(getChildFragmentManager(), list);
        mVpGanhuo.setAdapter(vpAdapter);
        mTabGanhuo.setupWithViewPager(mVpGanhuo);
        mTabGanhuo.getTabAt(0).setText("Android");
        mTabGanhuo.getTabAt(1).setText("iOS");
        mTabGanhuo.getTabAt(2).setText("前端");
        mTabGanhuo.getTabAt(3).setText("福利");
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
