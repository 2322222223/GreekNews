package com.renlz.jiyun.greeknews.presenter;

import com.renlz.jiyun.greeknews.base.model.InModel;
import com.renlz.jiyun.greeknews.base.presenter.IBasePresenter;
import com.renlz.jiyun.greeknews.model.ZhiHuModel;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.view.ZhiHuView;

/**
 * Created by Administrator on 2018/12/26.
 */

public class ZhiHuPresenter<V extends ZhiHuView> extends IBasePresenter<V> implements InModel {


    private ZhiHuModel mZhiHuModel = new ZhiHuModel();

    public void setNewsData(String url, Object o, EnumApi enumApi) {
        mZhiHuModel.getData(url, o, this, enumApi);
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
            mView.getNewData(o, o1);
        }
    }
}
