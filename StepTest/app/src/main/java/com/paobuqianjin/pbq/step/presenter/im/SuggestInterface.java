package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SuggestResponse;

/**
 * Created by pbq on 2018/3/31.
 */

public interface SuggestInterface extends CallBackInterface {
    public void response(SuggestResponse suggestResponse);

    public void response(ErrorCode errorCode);
}
