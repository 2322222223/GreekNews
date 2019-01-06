package com.renlz.jiyun.greeknews.fragments.about;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.base.fragment.SimpleFragment;

/**
 * Created by Administrator on 2018/12/27.
 */

public class AboutFragment extends SimpleFragment implements View.OnClickListener {
    private View view;
    private ImageView mImInfo;
    private CollapsingToolbarLayout mToolbarLayout;
    private AppBarLayout mAppBar;
    private LinearLayout mChitang;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        mImInfo = (ImageView) view.findViewById(R.id.im_info);
        mToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);
        mAppBar = (AppBarLayout) view.findViewById(R.id.app_bar);
        mChitang = (LinearLayout) view.findViewById(R.id.chitang);
        mChitang.setOnClickListener(this);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.chitang:
                try {
                    PackageManager packageManager
                            = mContext.getApplicationContext().getPackageManager();
                    Intent intent = packageManager.
                            getLaunchIntentForPackage("com.eg.android.AlipayGphone");
                    startActivity(intent);
                } catch (Exception e) {
                    String url = "https://ds.alipay.com/?from=mobileweb";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
                break;
        }
    }
}
