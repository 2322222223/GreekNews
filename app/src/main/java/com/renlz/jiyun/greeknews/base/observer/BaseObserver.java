package com.renlz.jiyun.greeknews.base.observer;

import com.renlz.jiyun.greeknews.base.model.InModel;

import java.util.logging.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by Administrator on 2018/12/26.
 */

public abstract class BaseObserver<T> implements Observer<T> {


    private InModel mInModel;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public BaseObserver(InModel inModel) {

        mInModel = inModel;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mCompositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            mInModel.setShowError("网络异常");
        } else {
            mInModel.setShowError("其他异常");
        }
        Logger.getLogger(e.getMessage());
        mInModel.setHideProGressBar();
    }

    @Override
    public void onComplete() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }

        if (mInModel != null) {
            mInModel = null;
        }
    }
}
