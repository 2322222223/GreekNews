package com.renlz.jiyun.greeknews.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.base.model.InModel;
import com.renlz.jiyun.greeknews.base.observer.BaseObserver;
import com.renlz.jiyun.greeknews.beans.BeforeNews;
import com.renlz.jiyun.greeknews.beans.CountList;
import com.renlz.jiyun.greeknews.beans.GanHuoList;
import com.renlz.jiyun.greeknews.beans.HostList;
import com.renlz.jiyun.greeknews.beans.NewestNew;
import com.renlz.jiyun.greeknews.beans.NewsInfo;
import com.renlz.jiyun.greeknews.beans.SectionList;
import com.renlz.jiyun.greeknews.beans.ShuJuZHiHuiList;
import com.renlz.jiyun.greeknews.beans.ShuJuZhiHuiType;
import com.renlz.jiyun.greeknews.beans.SisterList;
import com.renlz.jiyun.greeknews.beans.WxNews;
import com.renlz.jiyun.greeknews.fragments.setting.SettingFragment;
import com.renlz.jiyun.greeknews.http.GanHuoNet;
import com.renlz.jiyun.greeknews.http.MyRetrofit;
import com.renlz.jiyun.greeknews.http.ShuJuZhiHuiNet;
import com.renlz.jiyun.greeknews.http.WeiXinNet;
import com.renlz.jiyun.greeknews.http.ZhiHuNet;
import com.renlz.jiyun.greeknews.interceptor.MyCache;
import com.renlz.jiyun.greeknews.myapp.MyApp;
import com.renlz.jiyun.greeknews.myenums.EnumApi;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/26.
 */

public class ZhiHuModel {

    private String TAG = "My  Model";

    public void getData(String url, Object o, final InModel inModel, final EnumApi enumApi) {
        inModel.setShowProgressBar();
        MainActivity activity = new MainActivity();
        SharedPreferences cache = MyApp.sMyApp.getSharedPreferences("cache", Context.MODE_PRIVATE);
        boolean isck = cache.getBoolean("isck", false);

        switch (enumApi) {
            case NEWSETNEW:
                MyRetrofit.getZhiHuNet(isck, ZhiHuNet.URL).getDailyList().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver(inModel) {
                            @Override
                            public void onNext(Object value) {
                                inModel.getNewsData(new Gson().fromJson((String) value, NewestNew.class), enumApi);
                                inModel.setHideProGressBar();
                            }
                        });
                break;
            case SECTIONLIST:
                MyRetrofit.getZhiHuNet(isck, ZhiHuNet.URL).getSectionList().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<String>(inModel) {
                            @Override
                            public void onNext(String value) {
                                inModel.getNewsData(new Gson().fromJson((String) value, SectionList.class), enumApi);
                                inModel.setHideProGressBar();
                            }
                        });
                break;
            case HOTLIST:
                MyRetrofit.getZhiHuNet(isck, ZhiHuNet.URL).getHotList().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<String>(inModel) {
                            @Override
                            public void onNext(String value) {
                                inModel.getNewsData(new Gson().fromJson(value, HostList.class), enumApi);
                                inModel.setHideProGressBar();
                            }
                        });
                break;
            case DETAILEXTRAINFO:
                MyRetrofit.getZhiHuNet(isck, ZhiHuNet.URL).getDetailInfo((int) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<String>(inModel) {
                            @Override
                            public void onNext(String value) {
                                inModel.getNewsData(new Gson().fromJson(value, NewsInfo.class), enumApi);
                                inModel.setHideProGressBar();
                            }
                        });
                break;
            case WXNEWS:
                MyRetrofit.getWxNews(isck, WeiXinNet.URL).getWxNews(url, (Map<String, Object>) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<String>(inModel) {
                            @Override
                            public void onNext(String value) {
                                inModel.getNewsData(new Gson().fromJson(value, WxNews.class), enumApi);
                                inModel.setHideProGressBar();
                            }
                        });
                break;
            case GANHUOS:
                String[] strings = (String[]) o;
                MyRetrofit.getGanHuoNet(isck, GanHuoNet.URL).getGanHuo(strings[0], strings[1], strings[2]).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<String>(inModel) {
                            @Override
                            public void onNext(String value) {
                                inModel.getNewsData(new Gson().fromJson(value, GanHuoList.class), enumApi);
                                inModel.setHideProGressBar();
                            }
                        });
                break;
            case RANDOMSISTER:
                MyRetrofit.getGanHuoNet(isck, GanHuoNet.URL).getRandomSister(url).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<String>(inModel) {
                            @Override
                            public void onNext(String value) {
                                SisterList sisterList = new Gson().fromJson(value, SisterList.class);
                                inModel.getNewsData(sisterList, enumApi);
                                inModel.setHideProGressBar();
                            }
                        });
                break;
            case BEFORELIST:
                MyRetrofit.getZhiHuNet(isck, ZhiHuNet.URL).getDailyBeforeList((String) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<String>(inModel) {
                            @Override
                            public void onNext(String value) {
                                inModel.getNewsData(new Gson().fromJson(value, NewestNew.class), enumApi);
                                inModel.setHideProGressBar();
                            }
                        });
                break;
            case COUNTLIST:
                MyRetrofit.getZhiHuNet(isck, ZhiHuNet.URL).getCountList((int) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<String>(inModel) {
                            @Override
                            public void onNext(String value) {
                                inModel.getNewsData(new Gson().fromJson(value, CountList.class), enumApi);
                                inModel.setHideProGressBar();
                            }
                        });
                break;
            case SJZHNEWSTYPE:
                MyRetrofit.getShuJuZhiHuiNet(isck, ShuJuZhiHuiNet.URL).getNews((Map<String, Object>) o)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<String>(inModel) {
                            @Override
                            public void onNext(String value) {
                                inModel.getNewsData(new Gson().fromJson(value, ShuJuZhiHuiType.class), enumApi);
                                inModel.setHideProGressBar();
                            }
                        });
                break;
            case SJZHNEWS:
                MyRetrofit.getShuJuZhiHuiNet(isck, ShuJuZhiHuiNet.URL).getNews1((Map<String, Object>) o)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<String>(inModel) {
                            @Override
                            public void onNext(String value) {
                                inModel.getNewsData(new Gson().fromJson(value, ShuJuZHiHuiList.class), enumApi);
                                inModel.setHideProGressBar();
                            }
                        });
                break;
        }
    }
}
