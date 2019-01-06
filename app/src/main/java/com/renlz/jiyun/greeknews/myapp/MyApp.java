package com.renlz.jiyun.greeknews.myapp;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.renlz.jiyun.greeknews.fragments.setting.SettingFragment;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by Administrator on 2018/12/27.
 */

public class MyApp extends Application {

    public static MyApp sMyApp;
    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp = this;
        UMConfigure.init(this, "5be3fd81b465f551570000d0"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0

        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");//QQ
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");//微信
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");//新浪微博
        setNightTheme();

    }

    private void setNightTheme() {
        SettingFragment.setIsNight(new SettingFragment.ThemeNight() {
            @Override
            public void setNight(boolean night) {
                if (night) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }
}
