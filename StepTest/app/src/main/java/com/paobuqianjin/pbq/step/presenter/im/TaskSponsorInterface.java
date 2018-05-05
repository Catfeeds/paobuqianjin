package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskSponsorRespone;

/**
 * Created by Administrator on 2018/4/23.
 */

public interface TaskSponsorInterface extends CallBackInterface {
    void response(TaskSponsorRespone taskSponsorRespone);

    void responseLocation(String city, double latitude, double longitude);

}
