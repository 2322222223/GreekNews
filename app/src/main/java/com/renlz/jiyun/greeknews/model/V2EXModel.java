package com.renlz.jiyun.greeknews.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.renlz.jiyun.greeknews.activitys.InfoActivity;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.base.model.InModel;
import com.renlz.jiyun.greeknews.base.observer.BaseObserver;
import com.renlz.jiyun.greeknews.beans.NodeBean;
import com.renlz.jiyun.greeknews.http.MyRetrofit;
import com.renlz.jiyun.greeknews.http.V2EXNet;
import com.renlz.jiyun.greeknews.myapp.MyApp;
import com.renlz.jiyun.greeknews.myenums.EnumApi;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

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
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
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


                break;
            case V2EXTOP:
                break;
            case V2RXLIST:
                break;
            case V2EXTHEME:
                break;
        }
    }
}
