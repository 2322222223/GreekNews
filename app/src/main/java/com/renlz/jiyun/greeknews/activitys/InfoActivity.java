package com.renlz.jiyun.greeknews.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.base.activity.BaseActivity;
import com.renlz.jiyun.greeknews.beandao.DataBean;
import com.renlz.jiyun.greeknews.beans.CountList;
import com.renlz.jiyun.greeknews.beans.NewestNew;
import com.renlz.jiyun.greeknews.beans.NewsInfo;
import com.renlz.jiyun.greeknews.myapp.MyApp;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Constants;
import com.renlz.jiyun.greeknews.utils.DaoUtil;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import java.util.List;

/**
 * Created by Administrator on 2018/12/28.
 */

public class InfoActivity extends BaseActivity<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView, View.OnClickListener {

    private int mBean;
    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private Toolbar mToolbar;
    private ImageView mImInfo;
    private TextView mTvInfo;
    private TextView mTvType;
    private CollapsingToolbarLayout mToolbarLayout;
    private AppBarLayout mAppBar;
    private TextView mTvContent;
    private FloatingActionButton mFab;
    private String TAG = "Info";
    private NewsInfo mNewsInfo;
    private WebView mWebview;
    private TextView mInfoTitle;
    private TextView mTvDz;
    private TextView mTvPl;
    boolean isScroll;
    boolean isLike;
    /**
     * 分享
     */
    private TextView mTvFx;
    private LinearLayout mLayBottom;
    private NestedScrollView mScroll;
    private CountList mCountList;
    private ImageView mImPl;
    private ImageView mImFx;
    private SharedPreferences mLikeSp;
    private DataBean mDataBean;
    private int isID;

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
        EnumApi enumApi = (EnumApi) o1;
        switch (enumApi) {
            case DETAILEXTRAINFO:
                mNewsInfo = (NewsInfo) o;
                setData();
                break;
            case COUNTLIST:
                mCountList = (CountList) o;
                setCountData();
                break;

        }
    }

    private void setCountData() {
        mTvDz.setText(mCountList.getPopularity() + "个赞");
        mTvPl.setText(mCountList.getComments() + "条评论");
    }


    private void setData() {
        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDefaultFontSize(18);
        settings.setJavaScriptEnabled(true);
        mWebview.loadData(getHtmlData(mNewsInfo.getBody()), "text/html; charset=UTF-8", null);
        Glide.with(MyApp.sMyApp).load(mNewsInfo.getImage()).into(mImInfo);
        mTvInfo.setText(mNewsInfo.getTitle());
        mTvType.setText(mNewsInfo.getImage_source());
    }

    @Override
    protected ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
    }

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(v->finish());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY - oldScrollY > 0) {
                        int dp = Utils.getInstance().px2dp(InfoActivity.this, Utils.getInstance().dip2px(InfoActivity.this, scrollY));
                        if (dp > 1) {
                            mInfoTitle.setText(mNewsInfo.getTitle());
                        }
                        mLayBottom.setVisibility(View.GONE);
                    } else if (scrollX - oldScrollY < 0) {
                        mInfoTitle.setText("");
                        mLayBottom.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {
        setToolBar();
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        Intent in = getIntent();
        mBean=in.getIntExtra("bean",0);
        mPresenter.setNewsData(null, mBean, EnumApi.DETAILEXTRAINFO);
        mPresenter.setNewsData("", mBean, EnumApi.COUNTLIST);
        List<DataBean> list = DaoUtil.getInstance().dataSelect();
        boolean lk = false;
        for (DataBean bean : list) {
            if (bean.getMid() == mBean) {
                lk = true;
                isID = bean.getMid();
            } else {
                lk = false;
            }
        }
        isLiked(lk);
    }

    private void setToolBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initView() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar_info);
        mImInfo = (ImageView) findViewById(R.id.im_info);
        mTvInfo = (TextView) findViewById(R.id.tv_info);
        mTvType = (TextView) findViewById(R.id.tv_type);
        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mAppBar = (AppBarLayout) findViewById(R.id.app_bar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(this);
        mWebview = (WebView) findViewById(R.id.webview);
        mInfoTitle = (TextView) findViewById(R.id.info_title);
        mTvDz = (TextView) findViewById(R.id.tv_dz);
        mTvDz.setOnClickListener(this);
        mTvPl = (TextView) findViewById(R.id.tv_pl);
        mTvPl.setOnClickListener(this);
        mTvFx = (TextView) findViewById(R.id.tv_fx);
        mTvFx.setOnClickListener(this);
        mLayBottom = (LinearLayout) findViewById(R.id.lay_bottom);
        mScroll = (NestedScrollView) findViewById(R.id.scroll);
        mImPl = (ImageView) findViewById(R.id.im_pl);
        mImPl.setOnClickListener(this);
        mImFx = (ImageView) findViewById(R.id.im_fx);
        mImFx.setOnClickListener(this);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_info;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.fab:
                setLikes();
                break;
            case R.id.tv_dz:
                break;
            case R.id.im_pl:
                Intent intent = new Intent(InfoActivity.this, CommentsActivity.class);
                intent.putExtra("comments", mCountList);
                intent.putExtra("id", mBean);
                startActivity(intent);
                break;
            case R.id.im_fx:
                setShare();
                break;
        }
    }

    private void setLikes() {
        if (isLike) {
            mFab.setImageResource(R.drawable.ic_toolbar_like_n);
            List<DataBean> beans = DaoUtil.getInstance().dataSelect();
            if (beans.size() > 0) {
                for (DataBean bean : beans) {
                    if (bean.getMid() == isID) {
                        DaoUtil.getInstance().dataDelete(bean);
                    }
                }
            }
            isLike = false;
        } else {
            isLike = true;
            mFab.setImageResource(R.drawable.ic_toolbar_like_p);
            DataBean dataBean = new DataBean(null, mBean, Constants.TYPE_ZHIHU,null, mNewsInfo.getImage(), mNewsInfo.getTitle());
            DaoUtil.getInstance().DataInsert(dataBean);
        }
    }

    public void isLiked(boolean lk) {
        if (lk) {
            isLike = true;
            mFab.setImageResource(R.drawable.ic_toolbar_like_p);
        } else {
            isLike = false;
            mFab.setImageResource(R.drawable.ic_toolbar_like_n);
        }
    }

    private void setShare() {
        UMWeb web = new UMWeb(mNewsInfo.getShare_url());
        web.setTitle("分享给好友");
        web.setDescription("");
        ShareAction shareAction = new ShareAction(this);
        shareAction.withMedia(web).withText("分享给好友").setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.SINA, SHARE_MEDIA.WEIXIN).setCallback(new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onResult(SHARE_MEDIA share_media) {

            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {

            }
        }).open();
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
