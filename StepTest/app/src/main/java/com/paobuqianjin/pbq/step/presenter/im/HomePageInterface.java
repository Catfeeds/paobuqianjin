package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.PostUserStepResponse;

import java.util.Map;

/**
 * Created by pbq on 2017/12/5.
 */

public interface HomePageInterface extends CallBackInterface {
    public void responseStepToday(int stepToday);

    public void responseTarget(int personTarget);

    public void responseWeather();

    public void responseTodayIncome();

    public void responseMonthIncome();

    public void responseLocation(String city, double latitude, double longitude);

    public void response(PostUserStepResponse postUserStepResponse);
}
