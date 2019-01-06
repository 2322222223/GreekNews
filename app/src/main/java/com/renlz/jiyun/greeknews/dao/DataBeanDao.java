package com.renlz.jiyun.greeknews.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.renlz.jiyun.greeknews.beandao.DataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DATA_BEAN".
*/
public class DataBeanDao extends AbstractDao<DataBean, Long> {

    public static final String TABLENAME = "DATA_BEAN";

    /**
     * Properties of entity DataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Mid = new Property(1, int.class, "mid", false, "MID");
        public final static Property ItemTpe = new Property(2, int.class, "itemTpe", false, "ITEM_TPE");
        public final static Property WebUrl = new Property(3, String.class, "webUrl", false, "WEB_URL");
        public final static Property Url = new Property(4, String.class, "url", false, "URL");
        public final static Property Title = new Property(5, String.class, "title", false, "TITLE");
    }


    public DataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"MID\" INTEGER NOT NULL ," + // 1: mid
                "\"ITEM_TPE\" INTEGER NOT NULL ," + // 2: itemTpe
                "\"WEB_URL\" TEXT," + // 3: webUrl
                "\"URL\" TEXT," + // 4: url
                "\"TITLE\" TEXT);"); // 5: title
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getMid());
        stmt.bindLong(3, entity.getItemTpe());
 
        String webUrl = entity.getWebUrl();
        if (webUrl != null) {
            stmt.bindString(4, webUrl);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(5, url);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(6, title);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getMid());
        stmt.bindLong(3, entity.getItemTpe());
 
        String webUrl = entity.getWebUrl();
        if (webUrl != null) {
            stmt.bindString(4, webUrl);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(5, url);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(6, title);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DataBean readEntity(Cursor cursor, int offset) {
        DataBean entity = new DataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // mid
            cursor.getInt(offset + 2), // itemTpe
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // webUrl
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // url
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // title
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DataBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMid(cursor.getInt(offset + 1));
        entity.setItemTpe(cursor.getInt(offset + 2));
        entity.setWebUrl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setUrl(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTitle(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DataBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DataBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DataBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
