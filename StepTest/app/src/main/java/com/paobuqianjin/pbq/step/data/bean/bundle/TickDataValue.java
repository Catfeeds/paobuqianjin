package com.paobuqianjin.pbq.step.data.bean.bundle;

import java.io.Serializable;

/**
 * Created by pbq on 2018/10/15.
 */

public class TickDataValue implements Serializable {
    private String voucher_name;

    public String getVoucher_name() {
        return voucher_name;
    }

    public void setVoucher_name(String voucher_name) {
        this.voucher_name = voucher_name;
    }

    public String getSpend_money() {
        return spend_money;
    }

    public void setSpend_money(String spend_money) {
        this.spend_money = spend_money;
    }

    public String getDeduction_money() {
        return deduction_money;
    }

    public void setDeduction_money(String deduction_money) {
        this.deduction_money = deduction_money;
    }

    public String getValid_day() {
        return valid_day;
    }

    public void setValid_day(String valid_day) {
        this.valid_day = valid_day;
    }

    public String getVoucher_number() {
        return voucher_number;
    }

    public void setVoucher_number(String voucher_number) {
        this.voucher_number = voucher_number;
    }

    private String spend_money;
    private String deduction_money;
    private String valid_day;
    private String voucher_number;
}
