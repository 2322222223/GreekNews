package com.renlz.jiyun.greeknews.interceptor;

import com.renlz.jiyun.greeknews.myapp.MyApp;
import com.renlz.jiyun.greeknews.utils.Utils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/12/27.
 */

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        if (!Utils.getInstance().isNetworkAvailable(MyApp.sMyApp)) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();

        }
        Response originalResponse = chain.proceed(request);
        if (Utils.getInstance().isNetworkAvailable(MyApp.sMyApp)) {
            int maxAge=0;
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", cacheControl+maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 7;
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }
}
