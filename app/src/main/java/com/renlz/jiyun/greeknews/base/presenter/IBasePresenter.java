package com.renlz.jiyun.greeknews.base.presenter;

/**
 * Created by Administrator on 2018/12/26.
 */

public class IBasePresenter<V> implements BasePresenter<V> {
    public V mView;

    @Override
    public void attachView(V v) {
        if (mView == null) {
            mView = v;
        }
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
