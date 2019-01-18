package com.renlz.jiyun.greeknews.http;

import android.util.Log;

import com.renlz.jiyun.greeknews.interceptor.MyInterceptor;
import com.renlz.jiyun.greeknews.myapp.MyApp;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2018/12/26.
 */

public class MyRetrofit {


    private static ZhiHuNet sZhiHuNet;
    private static OkHttpClient sOkHttpClient = new OkHttpClient.Builder()
            .cache(new Cache(new File(MyApp.sMyApp.getCacheDir(), "nwescache"), 1024 * 1204 * 50))
            .addInterceptor(new MyInterceptor())
            .addNetworkInterceptor(new MyInterceptor())
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();

    public static ZhiHuNet getZhiHuNet(boolean isCache, String baseUrl) {
        if (isCache) {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(sOkHttpClient)
                    .build().create(ZhiHuNet.class);
        } else {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build().create(ZhiHuNet.class);
        }
    }


    public static GanHuoNet getGanHuoNet(boolean isCache, String baseUrl) {
        if (isCache) {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(sOkHttpClient)
                    .build().create(GanHuoNet.class);
        } else {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build().create(GanHuoNet.class);
        }
    }

    public static ShuJuZhiHuiNet getShuJuZhiHuiNet(boolean isCache, String baseUrl) {
        if (isCache) {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(sOkHttpClient)
                    .build().create(ShuJuZhiHuiNet.class);
        } else {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build().create(ShuJuZhiHuiNet.class);
        }
    }


    public static WeiXinNet getWxNews(boolean isCache, String baseUrl) {
        if (isCache) {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(sOkHttpClient)
                    .build().create(WeiXinNet.class);
        } else {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build().create(WeiXinNet.class);
        }
    }



    public static V2EXNet getV2EXNet(boolean isCache, String baseUrl) {
        if (isCache) {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(sOkHttpClient)
                    .build().create(V2EXNet.class);
        } else {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build().create(V2EXNet.class);
        }
    }


}
