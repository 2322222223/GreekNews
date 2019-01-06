package com.renlz.jiyun.greeknews.presenter;

import com.renlz.jiyun.greeknews.base.model.InModel;
import com.renlz.jiyun.greeknews.base.presenter.IBasePresenter;
import com.renlz.jiyun.greeknews.base.view.BaseView;
import com.renlz.jiyun.greeknews.model.V2EXModel;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.view.V2EXView;

/**
 * Created by Administrator on 2019/1/3.
 */

public class V2EXPresenter<V extends V2EXView> extends IBasePresenter<V> implements InModel {
    private V2EXModel mV2EXModel = new V2EXModel();

    public void setData(String url, Object o, EnumApi enumApi) {
        mV2EXModel.getV2EXData(url, o, this, enumApi);
    }

    @Override
    public void setShowProgressBar() {
        if (mView != null) {
            mView.showProgressBar();
        }
    }

    @Override
    public void setHideProGressBar() {
        if (mView != null) {
            mView.hideProGressBar();
        }
    }

    @Override
    public void setShowError(String error) {
        if (mView != null) {
            mView.showError(error);
        }
    }

    @Override
    public void getNewsData(Object o, Object o1) {
        if (mView != null) {
            mView.getData(o,o1);
        }
    }
}
