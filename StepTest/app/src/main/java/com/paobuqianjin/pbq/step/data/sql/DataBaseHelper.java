package com.paobuqianjin.pbq.step.data.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.paobuqianjin.pbq.step.data.bean.table.FriendCircle;
import com.paobuqianjin.pbq.step.data.bean.table.User;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pbq on 2017/12/4.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private final static String TAG = DataBaseHelper.class.getSimpleName();
    private final static String DADA_NAME = "pbq.db";
    private static DataBaseHelper userDataHelper;
    private Map<String, Dao> daoMap = new HashMap<String, Dao>();

    private DataBaseHelper(Context context) {
        super(context, DADA_NAME, null, 0);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, FriendCircle.class);
        } catch (SQLException e) {
            LocalLog.e(TAG, "onCreate()  创建数据库表失败");
            e.printStackTrace();
        } finally {
            //TODO
        }
    }

    //TODO 数据库升级，先备份，再删除，创建新数据库，拷贝数据，删除备份
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

    public static synchronized DataBaseHelper getUserDataHelper(Context context) {

        if (userDataHelper == null) {
            userDataHelper = new DataBaseHelper(context);
        }
        return userDataHelper;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daoMap.containsKey(className)) {
            dao = daoMap.get(className);
        }

        if (dao == null) {
            dao = super.getDao(clazz);
            daoMap.put(className, dao);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        for (String key : daoMap.keySet()) {
            Dao dao = daoMap.get(key);
            dao = null;
        }
    }
}
