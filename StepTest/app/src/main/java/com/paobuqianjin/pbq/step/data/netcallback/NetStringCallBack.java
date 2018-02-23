package com.paobuqianjin.pbq.step.data.netcallback;

import com.google.gson.Gson;
import com.l.okhttppaobu.okhttp.callback.StringCallback;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AllIncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.BindAccountResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.BindCardListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CheckSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTagResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTargetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTypeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicAllIndexResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyJoinCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NearByResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostUserStepResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskReleaseResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ThirdPartyLoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendSearchResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WeatherResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayResultResponse;
import com.paobuqianjin.pbq.step.model.Engine;
import com.paobuqianjin.pbq.step.presenter.im.CallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.CircleDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.CircleMemberManagerInterface;
import com.paobuqianjin.pbq.step.presenter.im.CrashInterface;
import com.paobuqianjin.pbq.step.presenter.im.DynamicCommentUiInterface;
import com.paobuqianjin.pbq.step.presenter.im.DynamicIndexUiInterface;
import com.paobuqianjin.pbq.step.presenter.im.HomePageInterface;
import com.paobuqianjin.pbq.step.presenter.im.LoginSignCallbackInterface;
import com.paobuqianjin.pbq.step.presenter.im.MyCreatCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.MyJoinCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.NearByInterface;
import com.paobuqianjin.pbq.step.presenter.im.OwnerUiInterface;
import com.paobuqianjin.pbq.step.presenter.im.PayInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReflashMyCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.SelectUserFriendInterface;
import com.paobuqianjin.pbq.step.presenter.im.SignCodeCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.SignCodeInterface;
import com.paobuqianjin.pbq.step.presenter.im.TagFragInterface;
import com.paobuqianjin.pbq.step.presenter.im.TaskReleaseInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiCreateCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiHotCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiStepAndLoveRankInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserIncomInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserInfoInterface;
import com.paobuqianjin.pbq.step.presenter.im.WxPayResultQueryInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import okhttp3.Call;

/**
 * Created by pbq on 2017/12/20.
 */

public class NetStringCallBack extends StringCallback {
    private final static String TAG = NetStringCallBack.class.getSimpleName();
    private CallBackInterface callBackInterface;
    private int command = -1;

    public NetStringCallBack(CallBackInterface callBackInterface) {
        this.callBackInterface = callBackInterface;
    }

    public NetStringCallBack(CallBackInterface callBackInterface, int command) {
        this.callBackInterface = callBackInterface;
        this.command = command;
    }

    @Override
    public void onError(Call call, Exception e, int i, Object response) {
        e.printStackTrace();
        if (response != null) {
            LocalLog.d(TAG, "onError() enter" + response.toString());
            if (callBackInterface != null && callBackInterface instanceof LoginSignCallbackInterface) {

                if (command == Engine.COMMAND_LOGIN_IN_BY_PHONE) {

                }
                if (command == Engine.COMMAND_LOGIN_BY_THIRD) {

                }
                if (command == Engine.COMMAND_REG_BY_PHONE) {

                }
            } else if (callBackInterface != null && callBackInterface instanceof UserInfoInterface) {

            } else if (callBackInterface != null && callBackInterface instanceof SignCodeCallBackInterface) {

            } else if (callBackInterface != null && callBackInterface instanceof LoginSignCallbackInterface
                    && command == Engine.COMMAND_REFRESH_PASSWORD) {

            } else if (callBackInterface != null && callBackInterface instanceof MyCreateCircleResponse
                    && command == Engine.COMMAND_GET_MY_CREATE_CIRCLE) {

            } else if (callBackInterface != null && callBackInterface instanceof UiHotCircleInterface
                    && command == Engine.COMMAND_GET_CHOICE_CIRCLE) {

            } else if (callBackInterface != null
                    && callBackInterface instanceof MyJoinCircleInterface
                    && command == Engine.COMMAND_GET_MY_JOIN_CIRCLE) {

            } else if (callBackInterface != null
                    && callBackInterface instanceof UiCreateCircleInterface
                    && command == Engine.COMMAND_CIRCLE_TYPE) {

            } else if (callBackInterface != null
                    && callBackInterface instanceof UiStepAndLoveRankInterface
                    && command == Engine.COMMAND_RECHARGE_RANK) {

            } else if (callBackInterface != null
                    && callBackInterface instanceof UiStepAndLoveRankInterface
                    && command == Engine.COMMAND_STEP_RANK) {

            } else if (callBackInterface != null
                    && callBackInterface instanceof CircleDetailInterface
                    && command == Engine.COMMAND_GET_CIRCLE_DETAIL) {

            } else if (callBackInterface != null
                    && callBackInterface instanceof TagFragInterface
                    && command == Engine.COMMAND_GET_TAG) {

            } else if (callBackInterface != null
                    && callBackInterface instanceof UiCreateCircleInterface
                    && command == Engine.COMMAND_CREATE_CIRCLE) {
                ((UiCreateCircleInterface) callBackInterface).response(response);
            } else if (callBackInterface != null
                    && callBackInterface instanceof UiCreateCircleInterface
                    && command == Engine.COMMAND_GET_CIRCLE_TARGET) {

            } else if (callBackInterface != null
                    && callBackInterface instanceof ReflashMyCircleInterface
                    && command == Engine.COMMAND_REFLASH_CIRCLE) {

            } else if (callBackInterface != null &&
                    callBackInterface instanceof DynamicIndexUiInterface
                    && command == Engine.COMMAND_GET_DYNAMIC_INDEX) {

            } else if (callBackInterface != null
                    && callBackInterface instanceof DynamicCommentUiInterface
                    && command == Engine.COMMAND_DYNAMIC_CONTENTS) {

            } else if (callBackInterface != null
                    && callBackInterface instanceof OwnerUiInterface
                    && command == Engine.COMMAND_OWNER_USER_INFO) {

            } else if (callBackInterface != null
                    && callBackInterface instanceof PayInterface
                    && command == Engine.COMMAND_CIRCLE_ORDER_POST) {

            } else if (callBackInterface != null
                    && callBackInterface instanceof WxPayResultQueryInterface
                    && command == Engine.COMMAND_PAY_RESULT_QUERY_WX) {
            } else {
                LocalLog.e(TAG, " dispatch not match");
            }
        }
    }

    @Override
    public void onResponse(String s, int i) {
        LocalLog.d(TAG, "onResponse() enter  " + s);
        disPatchResponse(s, i);
    }

    private void disPatchResponse(String s, int i) {
        if (callBackInterface != null && callBackInterface instanceof LoginSignCallbackInterface) {
            LocalLog.d(TAG, "disPatchResponse() enter body " + s);
            if (command == Engine.COMMAND_LOGIN_IN_BY_PHONE) {
                LoginResponse loginResponse = new Gson().fromJson(s, LoginResponse.class);
                ((LoginSignCallbackInterface) callBackInterface).requestPhoneLoginCallback(loginResponse);
            }
            if (command == Engine.COMMAND_LOGIN_BY_THIRD) {
                LocalLog.d(TAG, "三方登录成功");
                ThirdPartyLoginResponse thirdPartyLoginResponse = new Gson().fromJson(s, ThirdPartyLoginResponse.class);
                ((LoginSignCallbackInterface) callBackInterface).requestThirdLoginCallBack(thirdPartyLoginResponse);
            }
            if (command == Engine.COMMAND_REG_BY_PHONE) {
                SignUserResponse signUserResponse = new Gson().fromJson(s, SignUserResponse.class);
                ((LoginSignCallbackInterface) callBackInterface).registerByPhoneCallBack(signUserResponse);
            }

        } else if (callBackInterface != null && callBackInterface instanceof UserInfoInterface) {
            UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
            ((UserInfoInterface) callBackInterface).update(userInfoResponse);
        } else if (callBackInterface != null && callBackInterface instanceof SignCodeCallBackInterface) {
            SignCodeResponse signCodeResponse = new Gson().fromJson(s, SignCodeResponse.class);
            ((SignCodeCallBackInterface) callBackInterface).signCodeCallBack(signCodeResponse);
        } else if (callBackInterface != null && callBackInterface instanceof LoginSignCallbackInterface
                && command == Engine.COMMAND_REFRESH_PASSWORD) {
            LocalLog.d(TAG, s);
        } else if (callBackInterface != null && callBackInterface instanceof MyCreatCircleInterface
                && command == Engine.COMMAND_GET_MY_CREATE_CIRCLE) {
            LocalLog.d(TAG, "获取我创建的圈子");
            MyCreateCircleResponse myCreateCircleResponse = new Gson().fromJson(s, MyCreateCircleResponse.class);
            ((MyCreatCircleInterface) callBackInterface).response(myCreateCircleResponse);
        } else if (callBackInterface != null && callBackInterface instanceof UiHotCircleInterface
                && command == Engine.COMMAND_GET_CHOICE_CIRCLE) {
            ChoiceCircleResponse choiceCircleResponse = new Gson().fromJson(s, ChoiceCircleResponse.class);
            ((UiHotCircleInterface) callBackInterface).response(choiceCircleResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof MyJoinCircleInterface
                && command == Engine.COMMAND_GET_MY_JOIN_CIRCLE) {
            LocalLog.d(TAG, "我加入的圈子");
            MyJoinCircleResponse myJoinCircleResponse = new Gson().fromJson(s, MyJoinCircleResponse.class);
            ((MyJoinCircleInterface) callBackInterface).response(myJoinCircleResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof UiHotCircleInterface
                && command == Engine.COMMAND_GET_MY_HOT) {
            MyHotCircleResponse myHotCircleResponse = new Gson().fromJson(s, MyHotCircleResponse.class);
            ((UiHotCircleInterface) callBackInterface).response(myHotCircleResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof UiCreateCircleInterface
                && command == Engine.COMMAND_CIRCLE_TYPE) {
            LocalLog.d(TAG, "圈子类型");
            CircleTypeResponse circleTypeResponse = new Gson().fromJson(s, CircleTypeResponse.class);
            ((UiCreateCircleInterface) callBackInterface).response(circleTypeResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof UiStepAndLoveRankInterface
                && command == Engine.COMMAND_RECHARGE_RANK) {
            LocalLog.d(TAG, "充值排行");
            ReChargeRankResponse reChargeRankResponse = new Gson().fromJson(s, ReChargeRankResponse.class);
            ((UiStepAndLoveRankInterface) callBackInterface).response(reChargeRankResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof UiStepAndLoveRankInterface
                && command == Engine.COMMAND_STEP_RANK) {
            LocalLog.d(TAG, "步数排行");
            StepRankResponse stepRankResponse = new Gson().fromJson(s, StepRankResponse.class);
            ((UiStepAndLoveRankInterface) callBackInterface).response(stepRankResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof CircleDetailInterface
                && command == Engine.COMMAND_GET_CIRCLE_DETAIL) {
            LocalLog.d(TAG, "圈子详情");
            CircleDetailResponse circleDetailResponse = new Gson().fromJson(s, CircleDetailResponse.class);
            ((CircleDetailInterface) callBackInterface).response(circleDetailResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof TagFragInterface
                && command == Engine.COMMAND_GET_TAG) {
            LocalLog.d(TAG, "获取标签!");
            CircleTagResponse circleTagResponse = new Gson().fromJson(s, CircleTagResponse.class);
            ((TagFragInterface) callBackInterface).response(circleTagResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof UiCreateCircleInterface
                && command == Engine.COMMAND_CREATE_CIRCLE) {
            LocalLog.d(TAG, "创建圈子");
            CreateCircleResponse createCircleResponse = new Gson().fromJson(s, CreateCircleResponse.class);
            ((UiCreateCircleInterface) callBackInterface).response(createCircleResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof UiCreateCircleInterface
                && command == Engine.COMMAND_GET_CIRCLE_TARGET) {
            LocalLog.d(TAG, "获取圈子目标");
            CircleTargetResponse circleTargetResponse = new Gson().fromJson(s, CircleTargetResponse.class);
            ((UiCreateCircleInterface) callBackInterface).response(circleTargetResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof ReflashMyCircleInterface
                && command == Engine.COMMAND_REFLASH_CIRCLE) {
            LocalLog.d(TAG, "获取圈子目标");
            MyCreateCircleResponse myCreateCircleResponse = new Gson().fromJson(s, MyCreateCircleResponse.class);
            ((ReflashMyCircleInterface) callBackInterface).response(myCreateCircleResponse);
        } else if (callBackInterface != null &&
                callBackInterface instanceof DynamicIndexUiInterface
                && command == Engine.COMMAND_GET_DYNAMIC_INDEX) {
            LocalLog.d(TAG, "动态列表");
            DynamicAllIndexResponse dynamicAllIndexResponse = new Gson().fromJson(s, DynamicAllIndexResponse.class);
            ((DynamicIndexUiInterface) callBackInterface).response(dynamicAllIndexResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof DynamicCommentUiInterface
                && command == Engine.COMMAND_DYNAMIC_CONTENTS) {
            LocalLog.d(TAG, "评论列表");
            DynamicCommentResponse dynamicCommentResponse = new Gson().fromJson(s, DynamicCommentResponse.class);
            ((DynamicCommentUiInterface) callBackInterface).response(dynamicCommentResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof OwnerUiInterface
                && command == Engine.COMMAND_OWNER_USER_INFO) {
            LocalLog.d(TAG, "用户信息");
            UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
            ((OwnerUiInterface) callBackInterface).response(userInfoResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof PayInterface
                && command == Engine.COMMAND_CIRCLE_ORDER_POST) {
            LocalLog.d(TAG, "订单信息");
            WxPayOrderResponse wxPayOrderResponse = new Gson().fromJson(s, WxPayOrderResponse.class);
            ((PayInterface) callBackInterface).response(wxPayOrderResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof WxPayResultQueryInterface
                && command == Engine.COMMAND_PAY_RESULT_QUERY_WX) {
            LocalLog.d(TAG, "订单信息");
            WxPayResultResponse wxPayResultResponse = new Gson().fromJson(s, WxPayResultResponse.class);
            ((WxPayResultQueryInterface) callBackInterface).response(wxPayResultResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof CircleMemberManagerInterface
                && command == Engine.COMMAND_GET_MEMBER) {
            LocalLog.d(TAG, "获取圈子成员");
            CircleMemberResponse circleMemberResponse = new Gson().fromJson(s, CircleMemberResponse.class);
            ((CircleMemberManagerInterface) callBackInterface).response(circleMemberResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof TaskReleaseInterface
                && command == Engine.COMMAND_TASK_RELEASE) {
            TaskReleaseResponse taskReleaseResponse = new Gson().fromJson(s, TaskReleaseResponse.class);
            ((TaskReleaseInterface) callBackInterface).response(taskReleaseResponse);
        } else if (callBackInterface != null
                && callBackInterface instanceof HomePageInterface) {
            if (command == Engine.COMMAND_POST_USER_STEP) {
                PostUserStepResponse postUserStepResponse = new Gson().fromJson(s, PostUserStepResponse.class);
                ((HomePageInterface) callBackInterface).response(postUserStepResponse);
            } else if (command == Engine.COMMAND_WEATHER) {
                WeatherResponse weatherResponse = new Gson().fromJson(s, WeatherResponse.class);
                ((HomePageInterface) callBackInterface).responseWeather(weatherResponse);
            }else if (command == Engine.COMMAND_INCOME_TODAY) {
                IncomeResponse incomeResponse = new Gson().fromJson(s, IncomeResponse.class);
                ((HomePageInterface) callBackInterface).responseTodayIncome(incomeResponse);
            } else if (command == Engine.COMMAND_INCOME_MONTH) {
                IncomeResponse incomeResponse = new Gson().fromJson(s, IncomeResponse.class);
                ((HomePageInterface) callBackInterface).responseMonthIncome(incomeResponse);
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof NearByInterface
                && command == Engine.COMMAND_NEARBY_PEOPLE) {
            LocalLog.d(TAG, "附近的人");
            NearByResponse nearByResponse = new Gson().fromJson(s, NearByResponse.class);
            ((NearByInterface) callBackInterface).response(nearByResponse);
        } else if (callBackInterface != null && callBackInterface instanceof UserIncomInterface) {
            if (command == Engine.COMMAND_INCOME_YESTERDAY) {
                IncomeResponse incomeResponse = new Gson().fromJson(s, IncomeResponse.class);
                ((UserIncomInterface) callBackInterface).responseYesterday(incomeResponse);
            } else if (command == Engine.COMMAND_INCOME_TODAY) {
                IncomeResponse incomeResponse = new Gson().fromJson(s, IncomeResponse.class);
                ((UserIncomInterface) callBackInterface).responseToday(incomeResponse);
            } else if (command == Engine.COMMAND_INCOME_MONTH) {
                IncomeResponse incomeResponse = new Gson().fromJson(s, IncomeResponse.class);
                ((UserIncomInterface) callBackInterface).responseMonth(incomeResponse);
            } else if (command == Engine.COMMAND_INCOME_ALL) {
                AllIncomeResponse allIncomeResponse = new Gson().fromJson(s, AllIncomeResponse.class);
                ((UserIncomInterface) callBackInterface).responseAll(allIncomeResponse);
            }
        } else if (callBackInterface != null && callBackInterface instanceof CrashInterface) {
            if (command == Engine.COMMAND_CRASH_BANK_CARD_LIST) {
                LocalLog.d(TAG, "获取绑定列表");
                BindCardListResponse bindCardListResponse = new Gson().fromJson(s, BindCardListResponse.class);
                ((CrashInterface) callBackInterface).response(bindCardListResponse);
            } else if (command == Engine.COMMAND_CRASH_TO) {

            }
        } else if (callBackInterface != null
                && callBackInterface instanceof SignCodeInterface) {
            if (command == Engine.COMMAND_GET_SIGN_CODE) {
                GetSignCodeResponse getSignCodeResponse = new Gson().fromJson(s, GetSignCodeResponse.class);
                ((SignCodeInterface) callBackInterface).response(getSignCodeResponse);
            } else if (command == Engine.COMMAND_CHECK_SIGN_CODE) {
                CheckSignCodeResponse checkSignCodeResponse = new Gson().fromJson(s, CheckSignCodeResponse.class);
                ((SignCodeInterface) callBackInterface).response(checkSignCodeResponse);
            } else if (command == Engine.COMMAND_BIND_CRASH_ACCOUNT) {
                LocalLog.d(TAG, "绑定结果...");
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof SelectUserFriendInterface) {
            if (command == Engine.COMMAND_USER_FRIEND) {
                UserFriendResponse userFriendResponse = new Gson().fromJson(s, UserFriendResponse.class);
                ((SelectUserFriendInterface) callBackInterface).response(userFriendResponse);
            }
        } else {
            LocalLog.d(TAG, " dispatch not match");
        }
    }
}
