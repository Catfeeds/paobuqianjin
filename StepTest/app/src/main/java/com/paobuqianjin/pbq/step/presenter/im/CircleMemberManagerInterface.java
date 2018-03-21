package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.AddAdminResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AdminDeleteResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DearNameModifyResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MemberDeleteResponse;

/**
 * Created by pbq on 2018/2/2.
 */

public interface CircleMemberManagerInterface extends CallBackInterface {
    public void response(CircleMemberResponse circleMemberResponse);

    public void response(AddAdminResponse addAdminResponse);

    public void response(AdminDeleteResponse adminDeleteResponse);

    public void response(MemberDeleteResponse memberDeleteResponse);

    public void response(DearNameModifyResponse dearNameModifyResponse);
}
