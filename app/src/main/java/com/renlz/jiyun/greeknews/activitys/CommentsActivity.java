package com.renlz.jiyun.greeknews.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.adapters.VpAdapter;
import com.renlz.jiyun.greeknews.base.activity.BaseActivity;
import com.renlz.jiyun.greeknews.beans.CountList;
import com.renlz.jiyun.greeknews.fragments.comments.LongC_Fragment;
import com.renlz.jiyun.greeknews.fragments.comments.ShortC_Fragment;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.view.ZhiHuView;

import java.util.ArrayList;


/**
 * Created by Administrator on 2018/12/31.
 */

public class CommentsActivity extends BaseActivity<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView {

    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private TextView mComTitle;
    private Toolbar mToolbarCom;
    private TabLayout mTabCom;
    private ViewPager mVpCom;
    private CountList mComments;

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
        mToolbarCom.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {
        mToolbarCom.setTitle("");
        setSupportActionBar(mToolbarCom);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        Intent intent = getIntent();
        mComments = (CountList) intent.getSerializableExtra("comments");
        initFragment();
        mTabCom.setupWithViewPager(mVpCom);
        mTabCom.getTabAt(0).setText("短评论("+mComments.getShort_comments()+")");
        mTabCom.getTabAt(1).setText("长评论("+mComments.getLong_comments()+")");
        mComTitle.setText(mComments.getComments()+"条评论");
    }

    private void initFragment() {
        ArrayList<Fragment>list=new ArrayList<>();
        list.add(new ShortC_Fragment());
        list.add(new LongC_Fragment());
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), list);
        mVpCom.setAdapter(vpAdapter);

    }

    @Override
    protected void initView() {

        mComTitle = (TextView) findViewById(R.id.com_title);
        mToolbarCom = (Toolbar) findViewById(R.id.toolbar_com);
        mTabCom = (TabLayout) findViewById(R.id.tab_com);
        mVpCom = (ViewPager) findViewById(R.id.vp_com);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_comments;
    }

}
