package com.renlz.jiyun.greeknews.utils;

import android.content.Context;

import com.renlz.jiyun.greeknews.myapp.MyApp;

/**
 * Created by Administrator on 2018/12/31.
 */

public class SizeUtils {
    //将像素转换为px
    public static int dp2px(float dpValue) {
            final float scale = MyApp.sMyApp.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }

    //将px转换为dp
    public static int sp2px( float pxValue) {
            final float scale = MyApp.sMyApp.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        }
}
