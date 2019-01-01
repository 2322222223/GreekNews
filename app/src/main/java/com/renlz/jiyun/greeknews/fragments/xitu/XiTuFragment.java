package com.renlz.jiyun.greeknews.fragments.xitu;

import android.content.Intent;
import android.hardware.SensorEvent;
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
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.activitys.ZhanShiActivity;
import com.renlz.jiyun.greeknews.adapters.VpAdapter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.beans.ShuJuZhiHuiType;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.C;
import com.renlz.jiyun.greeknews.utils.Event;
import com.renlz.jiyun.greeknews.utils.EventBusUtil;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/27.
 */

public class XiTuFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView, View.OnClickListener {

    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private View view;
    private TabLayout mTabXitu;
    private ImageView mMenuXitu;
    private ArrayList<Fragment> mList;
    private ViewPager mVpXitu;
    private ShuJuZhiHuiType mType;
    private List<String> mResult;
    private VpAdapter mVpAdapter;
    private ArrayList<String> mList1;

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
            case SJZHNEWSTYPE:
                mType = (ShuJuZhiHuiType) o;
                mResult = mType.getRESULT();
                setData();
                break;
        }
    }

    private void setData() {
        mList = new ArrayList<>();
        for (int i = 0; i < mResult.size(); i++) {
            XiTuFragments fragments = XiTuFragments.newInstance(mResult.get(i));
            mList.add(fragments);
        }
        mVpAdapter = new VpAdapter(getChildFragmentManager(), mList);
        mVpXitu.setAdapter(mVpAdapter);
        mTabXitu.setupWithViewPager(mVpXitu);
        for (int i = 0; i <mResult.size(); i++) {
            mTabXitu.getTabAt(i).setText(mResult.get(i));
        }
        mList1 = new ArrayList<>();
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
        Map<String, Object> map = new HashMap<>();
        map.put("appKey", "e2250569c2ca47a283cc00b0df971ad1");
        mPresenter.setNewsData("", map, EnumApi.SJZHNEWSTYPE);
    }


    @Override
    protected void initView(View view) {

        mTabXitu = (TabLayout) view.findViewById(R.id.tab_xitu);
        mMenuXitu = (ImageView) view.findViewById(R.id.menu_xitu);
        mMenuXitu.setOnClickListener(this);
        mVpXitu = (ViewPager) view.findViewById(R.id.vp_xitu);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_xitu;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.menu_xitu:
                EventBusUtil.sendStickyEvent(new Event(C.EventCode.B,mResult));
                EventBusUtil.sendStickyEvent(new Event(C.EventCode.C,mList1));
                Intent in = new Intent(mContext, ZhanShiActivity.class);
                startActivity(in);
                break;
        }
    }
}
