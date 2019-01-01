package com.renlz.jiyun.greeknews.http;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/12/28.
 */

public interface WeiXinNet {
    String URL="http://api.tianapi.com/";

    @GET
    Observable<String> getWxNews(@Url String url, @QueryMap Map<String,Object> map);
}
