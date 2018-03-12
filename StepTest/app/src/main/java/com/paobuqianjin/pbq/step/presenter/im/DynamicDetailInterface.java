package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.param.PostDynamicContentParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentIdDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicIdDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicLikeListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostDynamicContentResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PutVoteResponse;

/**
 * Created by pbq on 2018/1/13.
 */

public interface DynamicDetailInterface extends CallBackInterface {
    public void response(DynamicCommentListResponse dynamicCommentListResponse);

    public void response(DynamicIdDetailResponse dynamicIdDetailResponse);

    public void response(ErrorCode errorCode);

    public void response(DynamicLikeListResponse dynamicLikeListResponse);

    public void response(PostDynamicContentResponse postDynamicContentResponse);

    public void response(PutVoteResponse putVoteResponse);

    public void postDynamicAction(PostDynamicContentParam postDynamicContentParam,String dearName,ReflashInterface reflashInterface);
}
