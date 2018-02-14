package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.Map;

/**
 * Created by pbq on 2018/2/2.
 */
/*
@className :TaskReleaseParam
*@date 2018/2/2
*@author
*@description 发布任务参数
*/
public class TaskReleaseParam {
    /*
    userid	用户ID	true	int
to_userid	领取人ID 多个用户逗	true	int
target_step	目标步数	true	int
reward_amount	奖励金额	true	float
task_days	任务天数	true	int
    * */
    private int userid;
    private int to_userid;
    private int target_step;
    private float reward_amount;
    private int task_days;

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    protected Map<String, String> params;


    public int getUserid() {
        return userid;
    }

    public TaskReleaseParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public int getTo_userid() {
        return to_userid;
    }

    public TaskReleaseParam setTo_userid(int to_userid) {
        this.to_userid = to_userid;
        params.put("to_userid", String.valueOf(to_userid) + ',');
        return this;
    }

    public int getTarget_step() {
        return target_step;
    }

    public TaskReleaseParam setTarget_step(int target_step) {
        this.target_step = target_step;
        params.put("target_step", String.valueOf(target_step));
        return this;
    }

    public float getReward_amount() {
        return reward_amount;
    }

    public TaskReleaseParam setReward_amount(float reward_amount) {
        this.reward_amount = reward_amount;
        params.put("reward_amount", String.valueOf(reward_amount));
        return this;
    }

    public int getTask_days() {
        return task_days;
    }

    public TaskReleaseParam setTask_days(int task_days) {
        this.task_days = task_days;
        params.put("task_days", String.valueOf(task_days));
        return this;

    }

    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
    }
}