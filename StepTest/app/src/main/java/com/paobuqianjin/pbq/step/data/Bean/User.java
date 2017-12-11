package com.paobuqianjin.pbq.step.data.Bean;

/**
 * Created by pbq on 2017/12/4.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User {
    /*本地用户ID*/
    @DatabaseField(generatedId = true)
    private int id;
    /*当天有效步数,在有效统计时间内的步数*/
    @DatabaseField(columnName = "step_useful")
    private int step_useful;
    /*当天总步数*/
    @DatabaseField(columnName = "step_today")
    private int step_today;
    /*自开机以来的总步数*/
    @DatabaseField(columnName = "total_step")
    private int total_step;
    /*记录时间戳*/
    @DatabaseField(columnName = "Date")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStep_useful() {
        return step_useful;
    }

    public void setStepUseful(int step_useful) {
        this.step_useful = step_useful;
    }

    public int getTotal_step() {
        return total_step;
    }

    public void setTotal_step(int total_step) {
        this.total_step = total_step;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
