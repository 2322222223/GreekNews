package com.renlz.jiyun.greeknews.http;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2018/12/31.
 */

public interface ShuJuZhiHuiNet {
    String URL = "http://api.shujuzhihui.cn/api/news/";

    @GET("categories")
    Observable<String> getNews(@QueryMap Map<String, Object> map);

    @GET("list")
    Observable<String> getNews1(@QueryMap Map<String, Object> map);

    @POST("detail")
    @FormUrlEncoded
    Observable<String> getNewsInfo(@FieldMap Map<String, Object> map);
}
