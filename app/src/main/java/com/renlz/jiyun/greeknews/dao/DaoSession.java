package com.renlz.jiyun.greeknews.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.renlz.jiyun.greeknews.beandao.IsChecks;

import com.renlz.jiyun.greeknews.dao.IsChecksDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig isChecksDaoConfig;

    private final IsChecksDao isChecksDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        isChecksDaoConfig = daoConfigMap.get(IsChecksDao.class).clone();
        isChecksDaoConfig.initIdentityScope(type);

        isChecksDao = new IsChecksDao(isChecksDaoConfig, this);

        registerDao(IsChecks.class, isChecksDao);
    }
    
    public void clear() {
        isChecksDaoConfig.clearIdentityScope();
    }

    public IsChecksDao getIsChecksDao() {
        return isChecksDao;
    }

}
