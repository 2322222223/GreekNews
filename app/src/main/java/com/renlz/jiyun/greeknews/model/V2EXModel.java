package com.renlz.jiyun.greeknews.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.InfoActivity;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.base.model.InModel;
import com.renlz.jiyun.greeknews.base.observer.BaseObserver;
import com.renlz.jiyun.greeknews.beans.NodeBean;
import com.renlz.jiyun.greeknews.beans.TopicListBean;
import com.renlz.jiyun.greeknews.http.MyRetrofit;
import com.renlz.jiyun.greeknews.http.V2EXNet;
import com.renlz.jiyun.greeknews.myapp.MyApp;
import com.renlz.jiyun.greeknews.myenums.EnumApi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2019/1/3.
 */

public class V2EXModel {

    private String TAG = "v2exModel";

    public void getV2EXData(String url, Object o, final InModel inModel, final EnumApi enumApi) {
        inModel.setShowProgressBar();

        MainActivity activity = new MainActivity();
        SharedPreferences cache = MyApp.sMyApp.getSharedPreferences("cache", Context.MODE_PRIVATE);
        boolean isck = cache.getBoolean("isck", false);
        switch (enumApi) {
            case V2EXTYPE:
                Flowable.just(V2EXNet.TAB_HOST + (String)o)
                        .subscribeOn(Schedulers.io())
                        .map(new Function<String, Document>() {
                            @Override
                            public Document apply(String s) {
                                try {
                                    return Jsoup.connect(s).timeout(10000).get();
                                } catch (IOException e) {
                                    Log.d(TAG, "apply: eeeee"+e.getMessage());
                                    e.printStackTrace();
                                }
                                return null;
                            }
                        })
                        .filter(new Predicate<Document>() {
                            @Override
                            public boolean test(@NonNull Document document) throws Exception {
                                return document != null;
                            }
                        })
                        .map(new Function<Document, List<TopicListBean>>() {
                            @Override
                            public List<TopicListBean> apply(Document doc) {
                                List<TopicListBean> mList = new ArrayList<>();
                                Elements itemElements = doc.select("div.cell.item");    //item根节点
                                int count = itemElements.size();
                                for (int i = 0; i < count; i++) {
                                    Elements titleElements = itemElements.get(i).select("div.cell.item table tr td span.item_title > a");   //标题
                                    Elements imgElements = itemElements.get(i).select("div.cell.item table tr td img.avatar");              //头像
                                    Elements nodeElements = itemElements.get(i).select("div.cell.item table tr span.small.fade a.node");    //节点
                                    Elements commentElements = itemElements.get(i).select("div.cell.item table tr a.count_livid");          //评论数
                                    Elements nameElements = itemElements.get(i).select("div.cell.item table tr span.small.fade strong a");  //作者 & 最后回复
                                    Elements timeElements = itemElements.get(i).select("div.cell.item table tr span.small.fade");           //更新时间

                                    TopicListBean bean = new TopicListBean();

                                    if (titleElements.size() > 0) {
                                        bean.setTitle(titleElements.get(0).text());
                                        bean.setTopicId(parseId(titleElements.get(0).attr("href")));
                                    }
                                    if (imgElements.size() > 0) {
                                        bean.setImgUrl(parseImg(imgElements.get(0).attr("src")));
                                    }
                                    if (nodeElements.size() > 0) {
                                        bean.setNode(nodeElements.get(0).text());
                                    }
                                    if (nameElements.size() > 0) {
                                        bean.setName(nameElements.get(0).text());
                                    }
                                    //存在没有 最后回复者、评论数、更新时间的情况
                                    if (nameElements.size() > 1) {
                                        bean.setLastUser(nameElements.get(1).text());
                                    }
                                    if (commentElements.size() > 0) {
                                        bean.setCommentNum(Integer.valueOf(commentElements.get(0).text()));
                                    }
                                    if (timeElements.size() > 1) {
                                        bean.setUpdateTime(parseTime(timeElements.get(1).text()));
                                    }

                                    mList.add(bean);
                                }
                                return mList;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new Subscriber<List<TopicListBean>>() {
                            @Override
                            public void onSubscribe(Subscription s) {

                            }

                            @Override
                            public void onNext(List<TopicListBean> topicListBeans) {
                                Log.d(TAG, "onNext: "+topicListBeans.toString());
                            }

                            @Override
                            public void onError(Throwable t) {
                                Log.d(TAG, "onError: TTTT"+t.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case V2EXTOP:
                break;
            case V2RXLIST:
                break;
            case V2EXTHEME:
                break;
        }
    }

    private String parseId(String str) {
        int idEnd = str.indexOf("#");
        return str.substring(3, idEnd);
    }

    private String parseTime(String str) {
        int timeEnd = str.indexOf("  •");
        if (timeEnd == -1) {
            return str;
        }
        return str.substring(0, timeEnd);
    }

    public static String parseImg(String str) {
        return "http:" + str;
    }
}
