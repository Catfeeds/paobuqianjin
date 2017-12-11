package com.paobuqianjin.pbq.step.data.dao;

import android.content.Context;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.paobuqianjin.pbq.step.data.Bean.User;
import com.paobuqianjin.pbq.step.data.sql.DataBaseHelper;
import com.paobuqianjin.pbq.step.utils.LocalLog;


/**
 * Created by pbq on 2017/12/4.
 */

public final class UserDao {
    private final static String TAG = UserDao.class.getSimpleName();
    private Context context;
    private Dao<User, Integer> userDao;
    private DataBaseHelper helper;

    public UserDao(Context context) {
        this.context = context;
        try {
            helper = DataBaseHelper.getUserDataHelper(context);
            userDao = helper.getDao(User.class);
        } catch (SQLException e) {
            LocalLog.e(TAG, "UserDao() constructor 失败!");
            e.printStackTrace();
        } finally {

        }
    }

    public void addUser(User user) {
        try {
            userDao.create(user);
        } catch (SQLException e) {
            LocalLog.d(TAG, "addUser() faild");
            e.printStackTrace();
        }finally {

        }
    }

}
