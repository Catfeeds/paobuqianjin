package com.paobuqianjin.pbq.step.data.bean.gson.param;

import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;

/**
 * Created by pbq on 2018/3/28.
 */
public class AddressPerson {
    /**
     * name : 测试跑步钱进
     * phone : 13828873978
     */
    public AddressPerson() {
    }

    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AddressPerson{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
