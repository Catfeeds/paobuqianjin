package com.paobuqianjin.pbq.step.data.bean.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by pbq on 2017/12/4.
 */
@DatabaseTable(tableName = "friend_circle")
public class FriendCircle {
    /*条目数据所属用户的id*/
    @DatabaseField(generatedId = true)
    private int id;
    /*圈子ID*/
    @DatabaseField
    private int friend_circle_id;
    /*圈中排名*/
    @DatabaseField
    private int rank_num;
    /* 设置外键*/
    @DatabaseField(canBeNull = true, foreign = true, columnName = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFriend_circle_id() {
        return friend_circle_id;
    }

    public void setFriend_circle_id(int friend_circle_id) {
        this.friend_circle_id = friend_circle_id;
    }

    public int getRank_num() {
        return rank_num;
    }

    public void setRank_num() {
        this.rank_num = rank_num;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
