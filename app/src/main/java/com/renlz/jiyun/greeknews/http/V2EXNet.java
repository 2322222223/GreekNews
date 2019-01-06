package com.renlz.jiyun.greeknews.http;

import android.widget.Filterable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2019/1/2.
 */

public interface V2EXNet {
    String HOST = "https://www.v2ex.com/";

    String TAB_HOST = "https://www.v2ex.com/?tab=";

    String REPLIES_URL = "https://www.v2ex.com/t/";


    /**
     * 获取节点信息
     *
     * @return
     */
    @GET("/api/nodes/show.json")
    Flowable<String> getNodeInfo(@Query("name") String name);

    /**
     * 获取主题列表
     *
     * @return
     */
    @GET("/api/topics/show.json")
    Observable<String> getTopicList(@Query("node_name") String name);

    /**
     * 获取主题信息
     *
     * @return
     */
    @GET("/api/topics/show.json")
    Observable<String> getTopicInfo(@Query("id") String id);

    /**
     * 获取主题回复
     *
     * @return
     */
    @GET("/api/replies/show.json")
    Observable<String> getRepliesList(@Query("topic_id") String id);
}
