package com.paobuqianjin.pbq.step.data.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import com.paobuqianjin.pbq.step.data.bean.table.ChatGroupInfo;
import com.paobuqianjin.pbq.step.data.bean.table.User;
import com.paobuqianjin.pbq.step.data.sql.DataBaseHelper;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.sql.SQLException;

public class ChatGroupInfoDao {
    private final static String TAG = ChatGroupInfoDao.class.getSimpleName();
    private Context context;
    private Dao<ChatGroupInfo, String> groupDao;
    private DataBaseHelper helper;

    public ChatGroupInfoDao(Context context) {
        this.context = context;
        try {
            helper = DataBaseHelper.getUserDataHelper(context);
            groupDao = helper.getDao(ChatGroupInfo.class);
        } catch (SQLException e) {
            LocalLog.e(TAG, "UserDao() constructor 失败!");
            e.printStackTrace();
        } finally {

        }
    }

    public int inserGroup(ChatGroupInfo chatGroupInfo) throws SQLException {
        return groupDao.create(chatGroupInfo);
    }

    public void updateGroup(ChatGroupInfo chatGroupInfo) throws SQLException {
        groupDao.createOrUpdate(chatGroupInfo);
    }

    public ChatGroupInfo queryGroup(String id) throws SQLException {
        return groupDao.queryBuilder().where().eq("id", id).queryForFirst();
    }

    public void clearTable() throws SQLException {
        TableUtils.clearTable(helper.getConnectionSource(), ChatGroupInfo.class);
    }

}
