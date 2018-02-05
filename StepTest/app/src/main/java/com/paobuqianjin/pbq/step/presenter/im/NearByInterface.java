package com.paobuqianjin.pbq.step.presenter.im;


import com.paobuqianjin.pbq.step.data.bean.gson.response.NearByResponse;

/**
 * Created by pbq on 2018/2/5.
 */

public interface NearByInterface extends CallBackInterface {
    public void response(NearByResponse nearByResponse);
}
