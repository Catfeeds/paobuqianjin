package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.AllIncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;

/**
 * Created by pbq on 2018/2/6.
 */

public interface UserIncomInterface extends CallBackInterface {

    public void responseYesterday(IncomeResponse yesterdayIncomeResponse);

    public void responseToday(IncomeResponse incomeResponse);

    public void responseMonth(IncomeResponse incomeResponse);

    public void responseAll(AllIncomeResponse allIncomeResponse);

    public void response(UserInfoResponse userInfoResponse);
}
