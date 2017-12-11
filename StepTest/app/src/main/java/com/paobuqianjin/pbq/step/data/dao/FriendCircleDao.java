package com.paobuqianjin.pbq.step.data.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.paobuqianjin.pbq.step.data.Bean.FriendCircle;
import com.paobuqianjin.pbq.step.data.Bean.User;
import com.paobuqianjin.pbq.step.data.sql.DataBaseHelper;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pbq on 2017/12/4.
 */
/*
@className :FriendCircleDao
*@date 2017/12/4
*@author
*@description 用户圈子表
*/
public class FriendCircleDao {
    private final static String TAG = FriendCircle.class.getSimpleName();
    private Context context;
    private Dao<FriendCircle, Integer> friedCircleDao;
    private DataBaseHelper dataBaseHelper;

    public FriendCircleDao(Context context) {

        try {
            dataBaseHelper = DataBaseHelper.getUserDataHelper(context);
            friedCircleDao = dataBaseHelper.getDao(FriendCircle.class);
        } catch (SQLException e) {
            LocalLog.e(TAG, "FriendCircleDao() failed!");
            e.printStackTrace();
        } finally {

        }
    }

    public void add_friendCircle(FriendCircle friendCircle) {
        try {
            friedCircleDao.create(friendCircle);
        } catch (SQLException e) {
            LocalLog.e(TAG, "add_friendCircle() failed!");
            e.printStackTrace();
        }
    }

    /*
    *@function getFriendCircleAppendUser
    *@param
    *@return 附加User属性的查询
    */
    public FriendCircle getFriendCircleAppendUser(int id) {
        FriendCircle friendCircle = null;
        try {
            friendCircle = friedCircleDao.queryForId(id);
            dataBaseHelper.getDao(User.class).refresh(friendCircle.getUser());
        } catch (SQLException e) {
            LocalLog.e(TAG, "getFriendCircleAppendUser() enter");
        } finally {

        }
        return friendCircle;
    }

    public FriendCircle getFriendCircle(int id) {
        FriendCircle friendCircle = null;
        try {
            friendCircle = friedCircleDao.queryForId(id);
        } catch (SQLException e) {
            LocalLog.e(TAG, "getFriendCircle() failed!");
        } finally {

        }
        return friendCircle;
    }

    public List<FriendCircle> getFriendCircleByUserId(int userId) {
        try {
            return friedCircleDao.queryBuilder().where().eq("user_id", userId).query();
        } catch (SQLException e) {
            LocalLog.e(TAG, "getFriendCircleByUserId() failed!");
        } finally {

        }
        return null;
    }
}
