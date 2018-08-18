package com.paobuqianjin.pbq.step.data.bean.table;

import android.net.Uri;
import android.text.TextUtils;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2018/6/28.
 */
@DatabaseTable(tableName = "chat_user_info")
public class ChatUserInfo {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String nickname;
    @DatabaseField
    private String remark_name;
    @DatabaseField
    private String avatar;

    public ChatUserInfo() {
    }

    public ChatUserInfo(String id, String nickname, String remark_name, String avatar) {
        this.id = id;
        this.nickname = nickname;
        this.remark_name = remark_name;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }
    public String getDisplayName() {
        return TextUtils.isEmpty(remark_name) ? nickname : remark_name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRemark_name() {
        return remark_name;
    }

    public void setRemark_name(String remark_name) {
        this.remark_name = remark_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Uri getAvatarUri() {
        return avatar == null ? null : Uri.parse(avatar);
    }
}
