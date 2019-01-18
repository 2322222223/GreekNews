package com.renlz.jiyun.greeknews.activitys;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ldoublem.loadingviewlib.view.LVEatBeans;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.base.activity.BaseActivity;
import com.renlz.jiyun.greeknews.fragments.about.AboutFragment;
import com.renlz.jiyun.greeknews.fragments.ganhuo.GanHuoFragment;
import com.renlz.jiyun.greeknews.fragments.like.LikeFragment;
import com.renlz.jiyun.greeknews.fragments.setting.SettingFragment;
import com.renlz.jiyun.greeknews.fragments.v2ex.V2EXFragment;
import com.renlz.jiyun.greeknews.fragments.weixin.WeiXinFragment;
import com.renlz.jiyun.greeknews.fragments.xitu.XiTuFragment;
import com.renlz.jiyun.greeknews.fragments.zhihu.ZhiHuFragment;
import com.renlz.jiyun.greeknews.fragments.zhihu.fragment.RiBaoFragment;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.view.ZhiHuView;

public class MainActivity extends BaseActivity<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView, View.OnClickListener {

    private ZhiHuPresenter<ZhiHuView> mPresenter;
    /**
     * 知乎日报
     */
    private TextView mToolbarTitle;
    private Toolbar mToolbar;
    private TabLayout mTab1;
    private ViewPager mVp;
    private NavigationView mNv;
    private DrawerLayout mDl;
    private FragmentManager mManager;
    private RiBaoFragment mRiBaoFragment;
    private ZhiHuFragment mZhiHuFragment;
    private WeiXinFragment mWeiXinFragment;
    private GanHuoFragment mGanHuoFragment;
    private XiTuFragment mXiTuFragment;
    private V2EXFragment mV2EXFragment;
    private LikeFragment mLikeFragment;
    private SettingFragment mSettingFragment;
    private AboutFragment mAboutFragment;
    public SearchView mSv;
    private Toolbar mToolbar2;
    private FrameLayout mFrLay;
    private ImageView mSearchIm;
    private ProgressWheel mProgressWheel;
    private LinearLayout mLayPro;
    private LinearLayout mLayAll;
    private String TAG = "      ";


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
        menuListener();

    }

    private void menuListener() {
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_zh:
                        mToolbarTitle.setText("知乎日报");
                        setFragment(mZhiHuFragment);
                        mSearchIm.setVisibility(View.GONE);
                        break;
                    case R.id.menu_wx:
                        mToolbarTitle.setText("微信精选");
                        setFragment(mWeiXinFragment);
                        mSearchIm.setVisibility(View.VISIBLE);
                        break;
                    case R.id.menu_gh:
                        mToolbarTitle.setText("干货集中营");
                        setFragment(mGanHuoFragment);
                        mSearchIm.setVisibility(View.VISIBLE);
                        break;
                    case R.id.menu_wj:
                        mToolbarTitle.setText("稀土挖金");
                        setFragment(mXiTuFragment);
                        mSearchIm.setVisibility(View.GONE);
                        break;
                    case R.id.menu_v2:
                        mToolbarTitle.setText("V2EX");
                        setFragment(mV2EXFragment);
                        mSearchIm.setVisibility(View.GONE);
                        break;
                    case R.id.menu_sc:
                        mToolbarTitle.setText("收藏");
                        setFragment(mLikeFragment);
                        mSearchIm.setVisibility(View.GONE);
                        break;
                    case R.id.menu_sz:
                        mToolbarTitle.setText("设置");
                        setFragment(mSettingFragment);
                        mSearchIm.setVisibility(View.GONE);
                        break;
                    case R.id.menu_gy:
                        mToolbarTitle.setText("关于");
                        setFragment(mAboutFragment);
                        mSearchIm.setVisibility(View.GONE);
                        break;
                }
                mDl.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }


    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        setToolBar();
        findFragment();
        mManager = getSupportFragmentManager();
        setFragment(mZhiHuFragment);
    }

    private void findFragment() {
        mZhiHuFragment = new ZhiHuFragment();
        mWeiXinFragment = new WeiXinFragment();
        mGanHuoFragment = new GanHuoFragment();
        mXiTuFragment = new XiTuFragment();
        mV2EXFragment = new V2EXFragment();
        mLikeFragment = new LikeFragment();
        mSettingFragment = new SettingFragment();
        mAboutFragment = new AboutFragment();
    }

    @Override
    protected void initView() {

        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTab1 = (TabLayout) findViewById(R.id.tab_1);
        mVp = (ViewPager) findViewById(R.id.vp);
        mNv = (NavigationView) findViewById(R.id.nv);
        mDl = (DrawerLayout) findViewById(R.id.dl);
        mSv = (SearchView) findViewById(R.id.sv);
        mToolbar2 = (Toolbar) findViewById(R.id.toolbar_2);
        mFrLay = (FrameLayout) findViewById(R.id.fr_lay);
        mSearchIm = (ImageView) findViewById(R.id.search_im);
        mSearchIm.setOnClickListener(this);
        mSv.setIconified(false);
        mProgressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);
        mLayPro = (LinearLayout) findViewById(R.id.lay_pro);
        mLayAll = (LinearLayout) findViewById(R.id.lay_all);
    }

    private void setToolBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolbar, R.string.app_name, R.string.app_name);
        mDl.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_main;
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.replace(R.id.fr_lay, fragment);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.search_im:
                setSearch();
                break;
        }
    }

    private void setSearch() {
        mToolbar.setVisibility(View.GONE);
        mToolbar2.setVisibility(View.VISIBLE);
        mToolbar2.setTitle("");
        setSupportActionBar(mToolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolbar.setVisibility(View.VISIBLE);
                mToolbar2.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long time = 0;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - time > 1000) {
                showAler();
                time = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void showAler() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确定退出GeekNews么")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create().show();
    }

    public void showProgressAnim() {
        mProgressWheel.setProgress(1f);
        mProgressWheel.spin();
        mLayAll.setVisibility(View.GONE);
        mLayPro.setVisibility(View.VISIBLE);
    }

    public void hideProgressAnim() {
        mLayAll.setVisibility(View.VISIBLE);
        mLayPro.setVisibility(View.GONE);
        mProgressWheel.stopSpinning();
    }
}
