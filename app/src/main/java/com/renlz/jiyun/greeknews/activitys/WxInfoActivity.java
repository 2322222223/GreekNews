package com.renlz.jiyun.greeknews.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.base.activity.BaseActivity;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;

/**
 * Created by Administrator on 2018/12/28.
 */

public class WxInfoActivity extends BaseActivity<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView, View.OnClickListener {

    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private TextView mWxinfoTitle;
    private WebView mWebviewWx;
    private String mUrl;
    private TextView mTbTitle;
    private ImageView mTbLike;
    private Toolbar mToolbarWx;
    private boolean isLike;
    private String mTitle;

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProGressBar() {

    }

    @Override
    public void showError(String error) {
        Utils.getInstance().getToast(2, error);
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
        mToolbarWx.setNavigationOnClickListener(new View.OnClickListener() {
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
        mToolbarWx.setTitle("");
        setSupportActionBar(mToolbarWx);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent in = getIntent();
        mUrl = in.getStringExtra("url");
        mTitle = in.getStringExtra("title");
        mTbTitle.setText(mTitle);
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        WebSettings settings = mWebviewWx.getSettings();
        settings.setDefaultFontSize(18);
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
        settings.setDefaultTextEncodingName("utf-8");//设置编码格式
        settings.setJavaScriptEnabled(true);//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        mWebviewWx.loadUrl(mUrl);
    }

    @Override
    protected void initView() {
        mWebviewWx = (WebView) findViewById(R.id.webview_wx);
        mTbTitle = (TextView) findViewById(R.id.tb_title);
        mTbLike = (ImageView) findViewById(R.id.tb_like);
        mTbLike.setOnClickListener(this);
        mToolbarWx = (Toolbar) findViewById(R.id.toolbar_wx);

    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_wxinfo;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tb_like:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "复制链接到剪贴板");
        menu.add(2, 2, 2, "分享链接");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Utils.getInstance().copyToClipBoard(this, mUrl);
                Utils.getInstance().getToast(2, "已复制到剪贴板");
                break;
            case 2:
                Utils.getInstance().getToast(1, "还没做呢");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
