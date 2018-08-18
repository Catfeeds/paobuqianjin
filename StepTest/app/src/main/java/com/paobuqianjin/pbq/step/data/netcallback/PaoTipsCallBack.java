package com.paobuqianjin.pbq.step.data.netcallback;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.PaoBuApplication;

/**
 * Created by Administrator on 2018/7/17.
 */

public abstract class PaoTipsCallBack extends PaoCallBack{
    @Override
    protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
        if (errorBean != null && !errorBean.getMessage().contains("Not Found Data")) {
            PaoToastUtils.showShortToastNoMore(PaoBuApplication.getApplication().getCurrentActivity(), errorBean.getMessage());
            return;
        }else{
            //onFalWithoutNotData(e, errorBean);
        }
        if (e != null) {
            PaoToastUtils.showShortToast(PaoBuApplication.getApplication().getCurrentActivity(),"不好意思，服务器走神了");
            return;
        }
    }
    //abstract void onFalWithoutNotData(Exception e,ErrorCode errorBean);
}
