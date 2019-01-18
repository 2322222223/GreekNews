package com.renlz.jiyun.greeknews.activitys;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.base.activity.BaseActivity;
import com.renlz.jiyun.greeknews.beandao.DataBean;
import com.renlz.jiyun.greeknews.beans.ShuJuZhiHuiList;
import com.renlz.jiyun.greeknews.beans.ShuJuZhiHuiNewInfo;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.Constants;
import com.renlz.jiyun.greeknews.utils.DaoUtil;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.umeng.socialize.bean.SHARE_MEDIA.QQ;
import static com.umeng.socialize.bean.SHARE_MEDIA.SINA;
import static com.umeng.socialize.bean.SHARE_MEDIA.WEIXIN;

/**
 * Created by Administrator on 2019/1/4.
 */

public class ShuJuZhiHuiNewsInfoActivity extends BaseActivity<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView, View.OnClickListener {

    private ZhiHuPresenter mPresenter;
    private String mNewid;
    private ShuJuZhiHuiNewInfo.RESULTBean mNews;
    private String TAG = "数据传送      ";
    private ImageView mImInfo;
    private TextView mTvInfoTitle;
    private ImageView mImInfoHeader;
    private TextView mTvInfoName;
    private TextView mTvInfoTime;
    private TextView mInfoTitle;
    private Toolbar mToolbarSjzh;
    private CollapsingToolbarLayout mToolbarLayout;
    private AppBarLayout mAppBar;
    private TextView mTvcontentsjzh;
    private NestedScrollView mScroll;
    private ImageView mLikeSjzh;
    private boolean isLike;
    private ShuJuZhiHuiList.RESULTBean.NewsListBean mNews1;
    private TextView mInfoTitleSjzh;
    private TextView mTvContentSjzh;

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProGressBar() {

    }

    @Override
    public void showError(String error) {
        Utils.getInstance().getToast(1, error);
    }

    Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            CharSequence str = (CharSequence) msg.obj;
            mTvcontentsjzh.setText(str);

        }
    };


    @Override
    public void getNewData(Object o, Object o1) {
        EnumApi enumApi = (EnumApi) o1;
        switch (enumApi) {
            case SHUJUZHIHUIINFO:
                ShuJuZhiHuiNewInfo newInfo = (ShuJuZhiHuiNewInfo) o;
                mNews = newInfo.getRESULT();
                setDatas();
                break;
        }
    }

    private void setDatas() {
        Glide.with(this).load(mNews.getImgUrl().get(0)).apply(new RequestOptions().circleCrop()).into(mImInfoHeader);
        mTvInfoName.setText(mNews.getSource());
        mTvInfoTime.setText(mNews.getPublishTime());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Html.ImageGetter imgGetter = new Html.ImageGetter() {
                    @Nullable
                    public Drawable getDrawable(String source) {
                        Drawable drawable = null;
                        URL url;
                        try {
                            url = new URL(source);
                            drawable = Drawable.createFromStream(url.openStream(), "img"); // 获取网路图片
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                                drawable.getIntrinsicHeight());
                        return drawable;
                    }
                };
                CharSequence spanned = Html.fromHtml(mNews.getContent(), imgGetter, null);
                Message msg = Message.obtain();
                msg.obj = spanned;
                hd.sendMessage(msg);
            }
        }).start();
    }


    @Override
    protected ZhiHuPresenter createPresenter() {
        return new ZhiHuPresenter();
    }

    @Override
    protected void initListener() {
        mToolbarSjzh.setNavigationOnClickListener(v -> finish());
        mScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY - oldScrollY > 1) {
                    mInfoTitle.setText(mNews.getTitle());
                    mLikeSjzh.setVisibility(View.VISIBLE);
                } else if (scrollX - oldScrollX < 1) {
                    mInfoTitle.setText("");
                    mLikeSjzh.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "选项");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                showPop();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showPop() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.pop_shujuzhihui, null);
        View qq = inflate.findViewById(R.id.s_qq);
        View wx = inflate.findViewById(R.id.s_wx);
        View wb = inflate.findViewById(R.id.s_wb);
        TextView dismiss = inflate.findViewById(R.id.dismiss);
        PopupWindow window = new PopupWindow();
        window.setContentView(inflate);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        window.showAtLocation(inflate, Gravity.BOTTOM, 1, 1);
        qq.setOnClickListener(v -> {
            setShare(QQ);
        });

        wx.setOnClickListener(v -> {
            setShare(WEIXIN);
        });

        wb.setOnClickListener(v -> {
            setShare(SINA);
        });
        dismiss.setOnClickListener(v -> {
            window.dismiss();
        });
    }

    private void setShare(SHARE_MEDIA type) {
        UMWeb web = new UMWeb(mNews.getImgUrl().get(0));
        web.setTitle(mNews.getTitle());
        switch (type) {
            case QQ:
                new ShareAction(ShuJuZhiHuiNewsInfoActivity.this)
                        .withMedia(web)
                        .setPlatform(QQ)//传入平台
                        .withText(mNews.getTitle())//分享内容
                        .setCallback(new UMShareListener() {
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
                        })//回调监听器
                        .share();
                break;
            case WEIXIN:
                new ShareAction(ShuJuZhiHuiNewsInfoActivity.this)
                        .withMedia(web)
                        .setPlatform(WEIXIN)//传入平台
                        .withText(mNews.getTitle())//分享内容
                        .setCallback(new UMShareListener() {
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
                        })//回调监听器
                        .share();
                break;
            case SINA:
                new ShareAction(ShuJuZhiHuiNewsInfoActivity.this)
                        .withMedia(web)
                        .setPlatform(SINA)//传入平台
                        .withText(mNews.getTitle())//分享内容
                        .setCallback(new UMShareListener() {
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
                        })//回调监听器
                        .share();
                break;
        }

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        mNews1 = (ShuJuZhiHuiList.RESULTBean.NewsListBean) getIntent().getSerializableExtra("newid");
        mNewid = mNews1.getNewsId();
        Log.d(TAG, "initData: " + mNewid);
        Map<String, Object> map = new HashMap<>();
        map.put("appKey", "e2250569c2ca47a283cc00b0df971ad1");
        map.put("newsId", mNewid);
        mPresenter.setNewsData("", map, EnumApi.SHUJUZHIHUIINFO);
        List<DataBean> beans = DaoUtil.getInstance().dataSelect();
        boolean lk = false;
        if (beans.size() > 0) {
            for (DataBean bean : beans) {
                if (mNews1.getTitle().equals(bean.getTitle())) {
                    lk = true;
                } else {
                    lk = false;
                }
            }
        }
        IsLiiked(lk);
    }

    @Override
    protected void initView() {
        mImInfo = (ImageView) findViewById(R.id.im_info);
        mTvInfoTitle = (TextView) findViewById(R.id.tv_info_title);
        mImInfoHeader = (ImageView) findViewById(R.id.im_info_header);
        mTvInfoName = (TextView) findViewById(R.id.tv_info_name);
        mTvInfoTime = (TextView) findViewById(R.id.tv_info_time);
        mInfoTitle = (TextView) findViewById(R.id.info_title_sjzh);
        mToolbarSjzh = (Toolbar) findViewById(R.id.toolbar_sjzh);
        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mAppBar = (AppBarLayout) findViewById(R.id.app_bar);
        mTvcontentsjzh = (TextView) findViewById(R.id.tv_content_sjzh);
        mScroll = (NestedScrollView) findViewById(R.id.scroll);
        mLikeSjzh = (ImageView) findViewById(R.id.like_sjzh);
        mToolbarSjzh.setTitle("");
        setSupportActionBar(mToolbarSjzh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mInfoTitleSjzh = (TextView) findViewById(R.id.info_title_sjzh);
        mLikeSjzh.setOnClickListener(this);
        mTvContentSjzh = (TextView) findViewById(R.id.tv_content_sjzh);
        mLikeSjzh = (ImageView) findViewById(R.id.like_sjzh);
        mLikeSjzh.setOnClickListener(this);

    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_shujuzhihuinewsinfo;
    }


    public void IsLiiked(boolean lk) {
        if (lk) {
            isLike = true;
            mLikeSjzh.setImageResource(R.drawable.ic_toolbar_like_p);
        } else {
            isLike = false;
            mLikeSjzh.setImageResource(R.drawable.ic_toolbar_like_n);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.like_sjzh:
                if (isLike) {
                    isLike = false;
                    mLikeSjzh.setImageResource(R.drawable.ic_toolbar_like_n);
                    List<DataBean> beans = DaoUtil.getInstance().dataSelect();
                    if (beans.size() > 0) {
                        for (DataBean bean : beans) {
                            if (mNews1.getTitle().equals(bean.getTitle())) {
                                DaoUtil.getInstance().dataDelete(bean);
                            }
                        }
                    }
                } else {
                    mLikeSjzh.setImageResource(R.drawable.ic_toolbar_like_p);
                    isLike = true;
                    DataBean dataBean = new DataBean(null, 0, Constants.TYPE_XITU,null, mNews1.getNewsImg(), mNews1.getTitle());
                    DaoUtil.getInstance().DataInsert(dataBean);
                }
                break;
        }
    }
}
