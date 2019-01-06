package com.renlz.jiyun.greeknews.activitys;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.adapters.LikeAdapter;
import com.renlz.jiyun.greeknews.base.activity.SimpleActivity;
import com.renlz.jiyun.greeknews.beandao.DataBean;
import com.renlz.jiyun.greeknews.beans.FuliList;
import com.renlz.jiyun.greeknews.utils.Constants;
import com.renlz.jiyun.greeknews.utils.DaoUtil;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2019/1/3.
 */

public class SistersActivity extends SimpleActivity implements View.OnClickListener {
    private ImageView mSisterLike;
    private ImageView mSisterShare;
    private ImageView mSisterSave;
    private Toolbar mToolbar;
    private ImageView mBigSister;
    private DataBean mDataBean;
    private String mUrl;
    private Bitmap mBitmap;
    boolean isLiked;
    private int mId;
    private String TAG = "fjsdofhjsdfhjsdhfjkshdfkjhs";
    private int mMid;

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {
        mId = getIntent().getIntExtra("id", 0);
        mUrl = getIntent().getStringExtra("sister");
        Glide.with(this).load(mUrl).into(mBigSister);
        List<DataBean> beans = DaoUtil.getInstance().dataSelect();
        boolean isid = false;
        for (DataBean bean : beans) {
            if (bean.getMid() == (mId)) {
                isid = true;
                mMid = bean.getMid();
            } else {
                isid = false;
            }
        }
        setLikeState(isid);
    }


    private void isChekPer() {
        String[] PERMISSIONS = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"};
        int permission = ContextCompat.checkSelfPermission(this,
                "android.permission.WRITE_EXTERNAL_STORAGE");
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }
    }

    @Override
    protected void initView() {

        mSisterLike = (ImageView) findViewById(R.id.sister_like);
        mSisterLike.setOnClickListener(this);
        mSisterShare = (ImageView) findViewById(R.id.sister_share);
        mSisterShare.setOnClickListener(this);
        mSisterSave = (ImageView) findViewById(R.id.sister_save);
        mSisterSave.setOnClickListener(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_sister);
        mBigSister = (ImageView) findViewById(R.id.big_sister);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_sisters;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.sister_like:
                setlike();
                break;
            case R.id.sister_share:
                setShare();
                break;
            case R.id.sister_save:
                isChekPer();
                setSave();
                break;
        }
    }

    private void setSave() {
        try {
            URL url = new URL(mUrl);
            try {
                InputStream in = url.openStream();
                mBitmap = BitmapFactory.decodeStream(in);
                in.close();
                File filepath = Environment.getExternalStorageDirectory();
                int radom = (int) (Math.random() * 2222) + 1;
                String path = filepath.getCanonicalPath() + "/fuli+" + radom + ".jpeg";
                File file = new File(path);
                FileOutputStream outputStream = new FileOutputStream(file);
                mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                if (mBitmap != null) {
                    Utils.getInstance().getToast(2, "图片保存完成");
                }
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void setShare() {
        UMWeb web = new UMWeb(mUrl);
        web.setTitle("漂亮的小姐姐");
        ShareAction shareAction = new ShareAction(this);
        shareAction.withMedia(web).withText("漂亮的小姐姐").setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.SINA, SHARE_MEDIA.ALIPAY).setCallback(new UMShareListener() {
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

    private void setlike() {
        if (isLiked) {
            mSisterLike.setImageResource(R.drawable.ic_toolbar_like_n);
            List<DataBean> dataBeans = DaoUtil.getInstance().dataSelect();
            if (dataBeans.size() > 0) {
                List<DataBean> dataBeans1 = DaoUtil.getInstance().dataSelect();
                for (DataBean dataBean : dataBeans1) {
                    if (dataBean.getMid() == mMid) {
                        DaoUtil.getInstance().dataDelete(dataBean);
                    }
                }
            }
            isLiked = false;
        } else {
            mSisterLike.setImageResource(R.drawable.ic_toolbar_like_p);
            mDataBean = new DataBean(null, mId, Constants.TYPE_GRIL, null, mUrl, null);
            DaoUtil.getInstance().DataInsert(mDataBean);
            isLiked = true;
        }
    }


    private void setLikeState(boolean state) {
        if (state) {
            mSisterLike.setImageResource(R.drawable.ic_toolbar_like_p);
            isLiked = true;
        } else {
            mSisterLike.setImageResource(R.drawable.ic_toolbar_like_n);
            isLiked = false;
        }
    }
}
