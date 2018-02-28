package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReleaseRecordResponse;

/**
 * Created by pbq on 2018/2/28.
 */
/*
@className :ReleaseRecordInterface
*@date 2018/2/28
*@author
*@description 发布记录接口
*/
public interface ReleaseRecordInterface extends CallBackInterface {
    public void response(ReleaseRecordResponse releaseRecordResponse);

    public void response(ErrorCode errorCode);
}
