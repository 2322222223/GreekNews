package com.renlz.jiyun.greeknews.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.myapp.MyApp;

/**
 * Created by Administrator on 2018/12/27.
 */

public class Utils {
    private static Utils sUtils;

    private Utils() {
    }

    public static Utils getInstance() {
        if (sUtils == null) {
            synchronized (Utils.class) {
                if (sUtils == null) {
                    sUtils = new Utils();
                }
            }
        }
        return sUtils;
    }


    public boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }


    public void getToast(int type, String mesT) {
        if (type == 1) {
            Toast toast = new Toast(MyApp.sMyApp);
            View inflate = LayoutInflater.from(MyApp.sMyApp).inflate(R.layout.item_toast_one, null);
            TextView tv = inflate.findViewById(R.id.tv_toast);
            tv.setText(mesT);
            toast.setView(inflate);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        } else if (type == 2) {
            Toast toast = new Toast(MyApp.sMyApp);
            View view = LayoutInflater.from(MyApp.sMyApp).inflate(R.layout.item_toast_two, null);
            TextView tvtitle = view.findViewById(R.id.toast_title);
            TextView tvmes = view.findViewById(R.id.toast_mes);
            tvtitle.setText("提示");
            tvmes.setText(mesT);
            toast.setView(view);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    //将像素转换为px
    public  int dip2px(Context context, float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }

    public  int px2dp(Context context, float pxValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        }


    public  void copyToClipBoard(Context context, String text) {
        ClipData clipData = ClipData.newPlainText("url", text);
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(clipData);
       // Toast.makeText(context,"已复制到剪贴板",Toast.LENGTH_SHORT).show();
    }

}
