package com.renlz.jiyun.greeknews.http;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/12/29.
 */

public interface GanHuoNet {
    String URL="http://gank.io/api/";

    @GET("data/{tech}/{num}/{page}")
    Observable<String> getGanHuo(@Path("tech")String tech, @Path("num") String num, @Path("page") String page);

    @GET()
    Observable<String> getRandomSister(@Url String url);
}
