package com.renlz.jiyun.greeknews.fragments.setting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ldoublem.loadingviewlib.view.LVEatBeans;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.base.fragment.SimpleFragment;
import com.renlz.jiyun.greeknews.interceptor.MyCache;
import com.renlz.jiyun.greeknews.myapp.MyApp;
import com.renlz.jiyun.greeknews.utils.DataCleanCache;
import com.renlz.jiyun.greeknews.utils.SendEmail;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.tuyenmonkey.mkloader.MKLoader;

/**
 * Created by Administrator on 2018/12/27.
 */

public class SettingFragment extends SimpleFragment implements View.OnClickListener {
    private static ThemeNight sThemeNight;
    private View view;
    /**
     * 常规
     */
    private TextView mTvCg;
    private CheckBox mCbHc;
    private CheckBox mCbWt;
    private CheckBox mCbYj;
    private TextView mFankui;
    private LinearLayout mLayYijian;
    private TextView mQchc;
    private LinearLayout mLayHuancun;
    /**
     * 当前版本号:v1.0.0
     */
    private TextView mJcgx;
    private LinearLayout mLayGengxin;
    private LinearLayout mR1;
    private MyCache mMyCache;
    private SharedPreferences mCache;
    private String TAG = "setting          ";
    private PopupWindow mWindow;
    private String mCacheSize;
    private PopupWindow mWindowTwo;

    @Override
    protected void initListener() {
        mCbHc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor edit = mCache.edit();
                edit.putBoolean("isck", isChecked);
                edit.commit();
            }
        });


        mCbYj.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (sThemeNight != null) {
                        sThemeNight.setNight(true);
                        MainActivity activity = (MainActivity) getActivity();
                        activity.recreate();
                    }
                } else {
                    if (sThemeNight != null) {
                        sThemeNight.setNight(false);
                        MainActivity activity = (MainActivity) getActivity();
                        activity.recreate();
                    }
                }
            }
        });

        mCbWt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                }
            }
        });
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {
        mCache = ((MainActivity) mActivity).getSharedPreferences("cache", Context.MODE_PRIVATE);
        mCbHc.setChecked(mCache.getBoolean("isck", true));
        try {
            mQchc.setText(DataCleanCache.getTotalCacheSize(MyApp.sMyApp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView(View view) {

        mTvCg = (TextView) view.findViewById(R.id.tv_cg);
        mCbHc = (CheckBox) view.findViewById(R.id.cb_hc);
        mCbWt = (CheckBox) view.findViewById(R.id.cb_wt);
        mCbYj = (CheckBox) view.findViewById(R.id.cb_yj);
        mFankui = (TextView) view.findViewById(R.id.fankui);
        mLayYijian = (LinearLayout) view.findViewById(R.id.lay_yijian);
        mLayYijian.setOnClickListener(this);
        mQchc = (TextView) view.findViewById(R.id.qchc);
        mLayHuancun = (LinearLayout) view.findViewById(R.id.lay_huancun);
        mLayHuancun.setOnClickListener(this);
        mJcgx = (TextView) view.findViewById(R.id.jcgx);
        mLayGengxin = (LinearLayout) view.findViewById(R.id.lay_gengxin);
        mLayGengxin.setOnClickListener(this);
        mR1 = (LinearLayout) view.findViewById(R.id.r1);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(getActivity(), R.layout.fragment_setting, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.lay_yijian:
                sendEmail();
                break;
            case R.id.lay_huancun:
                showAler();
                break;
            case R.id.lay_gengxin:
                showAlerTwo();
                break;
        }
    }

    private void sendEmail() {
        SendEmail.senEmail(mContext, "2322222223@qq.com", "我是###", "哈哈哈哈");
    }

    private void showAlerTwo() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.pop_genxin, null);
        TextView tv = inflate.findViewById(R.id.tv_gengxin);
        LinearLayout laygx = inflate.findViewById(R.id.lay_gx);
        if (mWindowTwo == null) {
            mWindowTwo = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        }
        mWindowTwo.showAtLocation(inflate, Gravity.CENTER, 0, 0);
        mWindowTwo.setContentView(inflate);
        tv.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv.setText("已经是最新版本了");
            }
        }, 2000);

        laygx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindowTwo.dismiss();
            }
        });
    }

    private void showAler() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.pop_cache, null);
        TextView tv = inflate.findViewById(R.id.tv_cache);
        try {
            if (DataCleanCache.getTotalCacheSize(MyApp.sMyApp).equals("0")) {
                tv.setText("当前没有缓存数据哦");
            }
            tv.setText("当前缓存大小:" + DataCleanCache.getTotalCacheSize(MyApp.sMyApp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        MKLoader lay = inflate.findViewById(R.id.anim);
        lay.setVisibility(View.VISIBLE);
        inflate.findViewById(R.id.enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCleanCache.clearAllCache(MyApp.sMyApp);
                try {
                    mQchc.setText(DataCleanCache.getTotalCacheSize(MyApp.sMyApp));
                    mWindow.dismiss();
                    Utils.getInstance().getToast(2, "清除完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        inflate.findViewById(R.id.esc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindow.dismiss();
            }
        });
        mWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);
        mWindow.setContentView(inflate);

    }


    public interface ThemeNight {
        void setNight(boolean night);
    }

    public static void setIsNight(ThemeNight themeNight) {

        sThemeNight = themeNight;
    }
}
