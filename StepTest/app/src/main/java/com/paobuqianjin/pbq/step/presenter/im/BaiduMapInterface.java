package com.paobuqianjin.pbq.step.presenter.im;

import com.baidu.location.BDLocation;

/**
 * Created by pbq on 2018/4/22.
 */

public interface BaiduMapInterface extends CallBackInterface {
    public void response(String city, double latitude, double longitude);

    void response(BDLocation location);
}
