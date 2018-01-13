package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentResponse;

/**
 * Created by pbq on 2018/1/13.
 */

public interface DynamicCommentUiInterface extends CallBackInterface {
    public void response(DynamicCommentResponse dynamicCommentResponse);
}
