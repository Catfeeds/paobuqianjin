package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentIdDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicIdDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicLikeListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;

/**
 * Created by pbq on 2018/1/13.
 */

public interface DynamicDetailInterface extends CallBackInterface {
    public void response(DynamicCommentListResponse dynamicCommentListResponse);

    public void response(DynamicIdDetailResponse dynamicIdDetailResponse);

    public void response(ErrorCode errorCode);

    public void response(DynamicLikeListResponse dynamicLikeListResponse);

}
