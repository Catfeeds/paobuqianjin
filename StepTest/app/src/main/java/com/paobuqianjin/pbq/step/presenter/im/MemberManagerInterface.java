package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.AddDeleteAdminResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AdminDeleteResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DearNameResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MemberDeleteResponse;

/**
 * Created by pbq on 2018/3/21.
 */
/*
@className :MemberManagerInterface
*@date 2018/3/21
*@author
*@description 成员管理接口
*/
public interface MemberManagerInterface extends CallBackInterface {
    public void response(AddDeleteAdminResponse addAdminResponse);

    public void response(AdminDeleteResponse adminDeleteResponse);

    public void response(MemberDeleteResponse memberDeleteResponse);

    public void response(DearNameResponse dearNameResponse);
}
