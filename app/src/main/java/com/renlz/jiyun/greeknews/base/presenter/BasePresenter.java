package com.renlz.jiyun.greeknews.base.presenter;

/**
 * Created by Administrator on 2018/12/26.
 */

public interface BasePresenter<V> {

    void attachView(V v);
    void detachView();
}
