package com.renlz.jiyun.greeknews.utils;

import com.renlz.jiyun.greeknews.beandao.DataBean;
import com.renlz.jiyun.greeknews.beandao.IsChecks;
import com.renlz.jiyun.greeknews.dao.DaoMaster;
import com.renlz.jiyun.greeknews.dao.DaoSession;
import com.renlz.jiyun.greeknews.dao.DataBeanDao;
import com.renlz.jiyun.greeknews.dao.IsChecksDao;
import com.renlz.jiyun.greeknews.myapp.MyApp;

import org.greenrobot.greendao.database.Database;

import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * Created by Administrator on 2018/12/31.
 */

public class DaoUtil {
    private static DaoUtil sDaoUtil;
    private static IsChecksDao sDao;
    private static DataBeanDao sDataBeanDao;

    private DaoUtil() {
    }


    public static DaoUtil getInstance() {
        if (sDaoUtil == null) {
            synchronized (DaoUtil.class) {
                if (sDaoUtil == null) {
                    sDaoUtil = new DaoUtil();
                }
            }
            if (sDataBeanDao == null) {
                DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApp.sMyApp, "databean.db");
                DaoMaster daoMaster = new DaoMaster(devOpenHelper.getReadableDatabase());
                DaoSession daoSession = daoMaster.newSession();
                sDataBeanDao = daoSession.getDataBeanDao();
            }
            if (sDao == null) {
                sDao = new DaoMaster(new DaoMaster.DevOpenHelper(MyApp.sMyApp, "isck.db").getWritableDatabase()).newSession().getIsChecksDao();
            }
        }
        return sDaoUtil;
    }

    public void ckInsert(IsChecks isChecks) {
        sDao.insert(isChecks);
    }

    public List<IsChecks> ckSelect() {
        return sDao.queryBuilder().list();
    }


    public List<DataBean> dataSelect() {
        return sDataBeanDao.queryBuilder().list();
    }

    public void dataDelete(DataBean dataBean) {
        sDataBeanDao.delete(dataBean);
    }

    public void DataInsert(DataBean dataBean) {
        sDataBeanDao.insert(dataBean);
    }

}
