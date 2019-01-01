package com.renlz.jiyun.greeknews.http;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/12/26.
 */

public interface ZhiHuNet {
    String URL = "http://news-at.zhihu.com/api/4/";

    /**
     * 启动界面图片
     */
//    @GET("start-image/{res}")
//    Observable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    /**
     * 最新日报DailyListBean
     */
    @GET("news/latest")
    Observable<String> getDailyList();

    /**
     * 往期日报DailyBeforeListBean
     */
    @GET("news/before/{date}")
    Observable<String> getDailyBeforeList(@Path("date") String date);

    /**
     * 主题日报ThemeListBean
     */
    @GET("themes")
    Observable<String> getThemeList();

    /**
     * 主题日报详情ThemeChildListBean
     */
    @GET("theme/{id}")
    Observable<String> getThemeChildList(@Path("id") int id);

    /**
     * 专栏日报SectionListBean
     */
    @GET("sections")
    Observable<String> getSectionList();

    /**
     * 专栏日报详情SectionChildListBean
     */
    @GET("section/{id}")
    Observable<String> getSectionChildList(@Path("id") int id);

    /**
     * 热门日报HotListBean
     */
    @GET("news/hot")
    Observable<String> getHotList();

    /**
     * 日报详情ZhihuDetailBean
     */
    @GET("news/{id}")
    Observable<String> getDetailInfo(@Path("id") int id);

    /**
     * 日报的额外信息DetailExtraBean
     */
    @GET("story-extra/{id}")
    Observable<String> getCountList(@Path("id") int id);

    /**
     * 日报的长评论CommentBean
     */
    @GET("story/{id}/long-comments")
    Observable<String> getLongCommentInfo(@Path("id") int id);

    /**
     * 日报的短评论CommentBean
     */
    @GET("story/{id}/short-comments")
    Observable<String> getShortCommentInfo(@Path("id") int id);
}
