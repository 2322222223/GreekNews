package com.renlz.jiyun.greeknews.utils;

import com.renlz.jiyun.greeknews.beandao.IsChecks;
import com.renlz.jiyun.greeknews.dao.DaoMaster;
import com.renlz.jiyun.greeknews.dao.IsChecksDao;
import com.renlz.jiyun.greeknews.myapp.MyApp;

import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public class DaoUtil {
    private static DaoUtil sDaoUtil;
    private static IsChecksDao sDao;

    private DaoUtil() {
    }


    public static DaoUtil getInstance() {
        if (sDaoUtil == null) {
            synchronized (DaoUtil.class) {
                if (sDaoUtil == null) {
                    sDaoUtil = new DaoUtil();
                }
            }
            if (sDao == null) {
                sDao = new DaoMaster(new DaoMaster.DevOpenHelper(MyApp.sMyApp, "isck.db").getWritableDatabase()).newSession().getIsChecksDao();
            }
        }

        return sDaoUtil;
    }

    public void ckInsert(IsChecks isChecks){
        sDao.insert(isChecks);
    }

    public List<IsChecks> ckSelect(){
        return sDao.queryBuilder().list();
    }
}
