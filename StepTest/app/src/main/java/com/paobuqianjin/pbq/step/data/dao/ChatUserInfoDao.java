package com.paobuqianjin.pbq.step.data.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import com.paobuqianjin.pbq.step.data.bean.table.ChatGroupInfo;
import com.paobuqianjin.pbq.step.data.bean.table.ChatUserInfo;
import com.paobuqianjin.pbq.step.data.sql.DataBaseHelper;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.sql.SQLException;

public class ChatUserInfoDao {
    private final static String TAG = ChatUserInfoDao.class.getSimpleName();
    private Context context;
    private Dao<ChatUserInfo, String> userinfoDao;
    private DataBaseHelper helper;

    public ChatUserInfoDao(Context context) {
        this.context = context;
        try {
            helper = DataBaseHelper.getUserDataHelper(context);
            userinfoDao = helper.getDao(ChatUserInfo.class);
        } catch (SQLException e) {
            LocalLog.e(TAG, "UserDao() constructor 失败!");
            e.printStackTrace();
        }
    }

    public int insert(ChatUserInfo chatUserInfo) throws SQLException {
        return userinfoDao.create(chatUserInfo);
    }

    public void updateOrinsert(ChatUserInfo chatUserInfo) throws SQLException {
//        if(chatUserInfo == null) return;
//        if (queryById(chatUserInfo.getId()) != null) {
//
            userinfoDao.createOrUpdate(chatUserInfo);
//        }
    }

    public ChatUserInfo queryById(String id) throws SQLException {
        return userinfoDao.queryBuilder().where().eq("id", id).queryForFirst();
    }

    public void clearTable() throws SQLException {
        TableUtils.clearTable(helper.getConnectionSource(), ChatUserInfo.class);
    }

}
