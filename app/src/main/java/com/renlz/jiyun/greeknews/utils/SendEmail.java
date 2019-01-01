package com.renlz.jiyun.greeknews.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.renlz.jiyun.greeknews.myapp.MyApp;

/**
 * Created by Administrator on 2018/12/31.
 */

public class SendEmail {
    public static void senEmail(Context context,String site,String title, String mes){
        Intent in=new Intent(Intent.ACTION_SENDTO);
        in.setData(Uri.parse(site));
        in.putExtra(Intent.EXTRA_SUBJECT, title);
        in.putExtra(Intent.EXTRA_TEXT, mes);
        context.startActivity(Intent.createChooser(in, "Choose an Email Client"));
    }
}
