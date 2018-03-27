package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageAtResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageContentResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageLikeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageSystemResponse;

/**
 * Created by pbq on 2018/3/27.
 */

public interface MessageInterface extends CallBackInterface {
    public void response(MessageSystemResponse messageSystemResponse);

    public void response(MessageLikeResponse messageLikeResponse);

    public void response(MessageContentResponse messageContentResponse);

    public void response(MessageAtResponse messageAtResponse);
}
