package com.paobuqianjin.pbq.step.data.netcallback;


import com.google.gson.Gson;
import com.l.okhttppaobu.okhttp.callback.Callback;
import com.paobuqianjin.pbq.step.data.bean.gson.CircleType;

import java.util.List;

import okhttp3.Response;

/**
 * Created by pbq on 2017/12/19.
 */
/*@desc 获取支持圈子类型的回掉接口函数
*@function
*@param
*@return 
*/
public abstract class ListCircleCallBack extends Callback<List<CircleType.DataBean>> {
    @Override
    public List<CircleType.DataBean> parseNetworkResponse(Response response, int i) throws Exception {
        String string = response.body().toString();
        List<CircleType.DataBean> dataBeans = new Gson().fromJson(string, CircleType.class).getData();
        return dataBeans;
    }
}
