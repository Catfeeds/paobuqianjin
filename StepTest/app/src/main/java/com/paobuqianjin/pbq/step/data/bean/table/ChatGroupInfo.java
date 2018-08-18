package com.paobuqianjin.pbq.step.data.bean.table;

import android.net.Uri;
import android.text.TextUtils;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2018/6/28.
 */
@DatabaseTable(tableName = "chat_group_info")
public class ChatGroupInfo {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String remark_name;
    @DatabaseField
    private String logo;

    public ChatGroupInfo() {
    }

    public ChatGroupInfo(String id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }
    public Uri getLogoUri() {
        return TextUtils.isEmpty(logo) ? null : Uri.parse(logo);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRemark_name() {
        return remark_name;
    }

    public void setRemark_name(String remark_name) {
        this.remark_name = remark_name;
    }

    public String getDisplayName() {
        return TextUtils.isEmpty(remark_name) ? name : remark_name;
    }

}
