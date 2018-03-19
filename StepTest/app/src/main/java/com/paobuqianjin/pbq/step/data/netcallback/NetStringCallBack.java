package com.paobuqianjin.pbq.step.data.netcallback;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.l.okhttppaobu.okhttp.callback.StringCallback;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostDynamicContentParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddDeleteFollowResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddFollowResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AllIncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.BindCardListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CheckSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleStepRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTagResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTargetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTypeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CrashListDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DanListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicAllIndexResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicIdDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicLikeListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicPersonResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendStepRankDayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteDanResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.JoinCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginOutResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyInviteResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyJoinCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyRecTaskRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyReleaseTaskDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NearByResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostDynamicContentResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostInviteCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostUserStepResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PutVoteResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.QueryFollowStateResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecPayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReceiveTaskResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReleaseDynamicResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReleaseRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepDollarDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskMyReleaseResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskRecDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskReleaseResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ThirdPartyLoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserDanResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFollowOtOResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserIdFollowResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WeatherResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayResultResponse;
import com.paobuqianjin.pbq.step.model.Engine;
import com.paobuqianjin.pbq.step.presenter.im.AddDeleteFollowInterface;
import com.paobuqianjin.pbq.step.presenter.im.CallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.CircleDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.CircleMemberManagerInterface;
import com.paobuqianjin.pbq.step.presenter.im.CrashInterface;
import com.paobuqianjin.pbq.step.presenter.im.CrashRecordInterface;
import com.paobuqianjin.pbq.step.presenter.im.DanCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.DanInterface;
import com.paobuqianjin.pbq.step.presenter.im.DynamicDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.DynamicIndexUiInterface;
import com.paobuqianjin.pbq.step.presenter.im.FriendHonorDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.FriendHonorInterface;
import com.paobuqianjin.pbq.step.presenter.im.HomePageInterface;
import com.paobuqianjin.pbq.step.presenter.im.InviteInterface;
import com.paobuqianjin.pbq.step.presenter.im.JoinCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.LoginSignCallbackInterface;
import com.paobuqianjin.pbq.step.presenter.im.MyCreatCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.MyDynamicInterface;
import com.paobuqianjin.pbq.step.presenter.im.MyJoinCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.MyReleaseTaskDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.MyReleaseTaskInterface;
import com.paobuqianjin.pbq.step.presenter.im.NearByInterface;
import com.paobuqianjin.pbq.step.presenter.im.OwnerUiInterface;
import com.paobuqianjin.pbq.step.presenter.im.PayInterface;
import com.paobuqianjin.pbq.step.presenter.im.PostInviteCodeInterface;
import com.paobuqianjin.pbq.step.presenter.im.QueryRedPkgInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReceiveTaskInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReflashMyCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReleaseDynamicInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReleaseRecordInterface;
import com.paobuqianjin.pbq.step.presenter.im.SearchCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.SelectUserFriendInterface;
import com.paobuqianjin.pbq.step.presenter.im.SignCodeCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.SignCodeInterface;
import com.paobuqianjin.pbq.step.presenter.im.StepDollarDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.TagFragInterface;
import com.paobuqianjin.pbq.step.presenter.im.TaskDetailRecInterface;
import com.paobuqianjin.pbq.step.presenter.im.TaskMyRecInterface;
import com.paobuqianjin.pbq.step.presenter.im.TaskReleaseInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiCreateCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiHotCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiStepAndLoveRankInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserFollowInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserHomeInterface;
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
            try {
                ErrorCode errorCode = new Gson().fromJson(response.toString(), ErrorCode.class);
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
                        && callBackInterface instanceof DynamicDetailInterface
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
                } else if (callBackInterface != null
                        && callBackInterface instanceof PostInviteCodeInterface) {
                    if (command == Engine.COMMAND_POST_INVITE_CODE) {
                        ((PostInviteCodeInterface) callBackInterface).responseError(errorCode);
                    }
                } else if (callBackInterface != null
                        && callBackInterface instanceof MyReleaseTaskInterface) {
                    if (command == Engine.COMMAND_GET_MY_RELEASE_TASK) {
                        ((MyReleaseTaskInterface) callBackInterface).response(errorCode);
                    }
                } else if (callBackInterface != null
                        && callBackInterface instanceof MyReleaseTaskDetailInterface) {
                    if (command == Engine.COMMAND_GET_MY_RELEASE_TASK_DETAIL) {
                        ((MyReleaseTaskDetailInterface) callBackInterface).response(errorCode);
                    }
                } else if (callBackInterface != null
                        && callBackInterface instanceof ReleaseRecordInterface) {
                    if (command == Engine.COMMAND_GET_MY_RELEASE_RECORD) {
                        ((ReleaseRecordInterface) callBackInterface).response(errorCode);
                    }
                } else if (callBackInterface != null
                        && callBackInterface instanceof DanInterface) {
                    if (command == Engine.COMMAND_GET_DAN_LIST) {
                        ((DanInterface) callBackInterface).response(errorCode);
                    } else if (command == Engine.COMMAND_GET_USER_DAN) {
                        ((DanInterface) callBackInterface).response(errorCode);
                    }
                } else if (callBackInterface != null && callBackInterface instanceof JoinCircleInterface) {
                    LocalLog.d(TAG, "加入圈子错误");
                    ((JoinCircleInterface) callBackInterface).response(errorCode);
                } else {
                    LocalLog.e(TAG, " dispatch not match");
                }
            } catch (JsonSyntaxException j) {
                LocalLog.e(TAG, "未知错误");
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
                && callBackInterface instanceof CircleDetailInterface) {
            if (command == Engine.COMMAND_QUIT_CIRCLE) {
                LoginOutResponse loginOutResponse = new Gson().fromJson(s, LoginOutResponse.class);
                ((CircleDetailInterface) callBackInterface).response(loginOutResponse);
            } else if (command == Engine.COMMAND_GET_CIRCLE_DETAIL) {
                LocalLog.d(TAG, "圈子详情");
                CircleDetailResponse circleDetailResponse = new Gson().fromJson(s, CircleDetailResponse.class);
                ((CircleDetailInterface) callBackInterface).response(circleDetailResponse);
            }

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
                && callBackInterface instanceof DynamicDetailInterface) {
            if (command == Engine.COMMAND_DYNAMIC_CONTENTS) {
                LocalLog.d(TAG, "评论列表");
                DynamicCommentListResponse dynamicCommentListResponse = new Gson().fromJson(s, DynamicCommentListResponse.class);
                ((DynamicDetailInterface) callBackInterface).response(dynamicCommentListResponse);
            } else if (command == Engine.COMMAND_GET_DYNAMIC_ID_DETAIL) {
                LocalLog.d(TAG, "动态详情");
                DynamicIdDetailResponse dynamicIdDetailResponse = new Gson().fromJson(s, DynamicIdDetailResponse.class);
                ((DynamicDetailInterface) callBackInterface).response(dynamicIdDetailResponse);
            } else if (command == Engine.COMMAND_GET_VOTE_LIST) {
                LocalLog.d(TAG, "点赞列表");
                DynamicLikeListResponse dynamicLikeListResponse = new Gson().fromJson(s, DynamicLikeListResponse.class);
                ((DynamicDetailInterface) callBackInterface).response(dynamicLikeListResponse);
            } else if (command == Engine.COMMAND_PUT_VOTE) {
                PutVoteResponse putVoteResponse = new Gson().fromJson(s, PutVoteResponse.class);
                ((DynamicDetailInterface) callBackInterface).response(putVoteResponse);
            } else if (command == Engine.COMMAND_POST_DYNAMIC_COMMENT) {
                PostDynamicContentResponse postDynamicContentResponse = new Gson().fromJson(s, PostDynamicContentResponse.class);
                ((DynamicDetailInterface) callBackInterface).response(postDynamicContentResponse);
            }

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
            } else if (command == Engine.COMMAND_INCOME_TODAY) {
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
        } else if (callBackInterface != null
                && callBackInterface instanceof UserHomeInterface) {
            if (command == Engine.COMMAND_GET_USER_INFO) {
                UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                ((UserHomeInterface) callBackInterface).response(userInfoResponse);
            } else if (command == Engine.COMMAND_GET_USER_DYNAMIC) {
                DynamicPersonResponse dynamicPersonResponse = new Gson().fromJson(s, DynamicPersonResponse.class);
                ((UserHomeInterface) callBackInterface).response(dynamicPersonResponse);
            } else if (command == Engine.COMMAND_QUERY_FOLLOW_STATE) {
                QueryFollowStateResponse queryFollowStateResponse = new Gson().fromJson(s, QueryFollowStateResponse.class);
                ((UserHomeInterface) callBackInterface).response(queryFollowStateResponse);
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof MyDynamicInterface) {
            if (command == Engine.COMMAND_GET_USER_DYNAMIC) {
                DynamicPersonResponse dynamicPersonResponse = new Gson().fromJson(s, DynamicPersonResponse.class);
                ((MyDynamicInterface) callBackInterface).response(dynamicPersonResponse);
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof StepDollarDetailInterface) {
            if (command == Engine.COMMAND_GET_USER_STEP_DOLLAR_DETAIL) {
                LocalLog.d(TAG, "步币明细");
                StepDollarDetailResponse dollarDetailResponse = new Gson().fromJson(s, StepDollarDetailResponse.class);
                ((StepDollarDetailInterface) callBackInterface).response(dollarDetailResponse);
            }
            if (command == Engine.COMMAND_GET_USER_INFO) {
                UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                ((StepDollarDetailInterface) callBackInterface).response(userInfoResponse);
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof InviteInterface) {
            if (command == Engine.COMMAND_GET_INVITE_DAN) {
                InviteDanResponse inviteDanResponse = new Gson().fromJson(s, InviteDanResponse.class);
                ((InviteInterface) callBackInterface).response(inviteDanResponse);
            } else if (command == Engine.COMMAND_GET_MY_INVITE_MSG) {
                MyInviteResponse myInviteResponse = new Gson().fromJson(s, MyInviteResponse.class);
                ((InviteInterface) callBackInterface).response(myInviteResponse);
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof PostInviteCodeInterface) {
            if (command == Engine.COMMAND_POST_INVITE_CODE) {
                PostInviteCodeResponse postInviteCodeResponse = new Gson().fromJson(s, PostInviteCodeResponse.class);
                ((PostInviteCodeInterface) callBackInterface).response(postInviteCodeResponse);
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof MyReleaseTaskInterface) {
            if (command == Engine.COMMAND_GET_MY_RELEASE_TASK) {
                TaskMyReleaseResponse taskMyReleaseResponse = new Gson().fromJson(s, TaskMyReleaseResponse.class);
                ((MyReleaseTaskInterface) callBackInterface).response(taskMyReleaseResponse);
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof MyReleaseTaskDetailInterface) {
            if (command == Engine.COMMAND_GET_MY_RELEASE_TASK_DETAIL) {
                MyReleaseTaskDetailResponse myReleaseTaskDetailResponse = new Gson().fromJson(s, MyReleaseTaskDetailResponse.class);
                ((MyReleaseTaskDetailInterface) callBackInterface).response(myReleaseTaskDetailResponse);
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof ReleaseRecordInterface) {
            if (command == Engine.COMMAND_GET_MY_RELEASE_RECORD) {
                ReleaseRecordResponse releaseRecordResponse = new Gson().fromJson(s, ReleaseRecordResponse.class);
                ((ReleaseRecordInterface) callBackInterface).response(releaseRecordResponse);
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof DanInterface) {
            if (command == Engine.COMMAND_GET_DAN_LIST) {
                DanListResponse danListResponse = new Gson().fromJson(s, DanListResponse.class);
                ((DanInterface) callBackInterface).response(danListResponse);
            } else if (command == Engine.COMMAND_GET_USER_DAN) {
                UserDanResponse userDanResponse = new Gson().fromJson(s, UserDanResponse.class);
                ((DanInterface) callBackInterface).response(userDanResponse);
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof UserFollowInterface) {
            if (command == Engine.COMMAND_MY_FOLLOW) {
                UserIdFollowResponse userIdFollowResponse = new Gson().fromJson(s, UserIdFollowResponse.class);
                ((UserFollowInterface) callBackInterface).response(userIdFollowResponse);
            } else if (command == Engine.COMMAND_FOLLOW_ME) {
                FollowUserResponse followUserResponse = new Gson().fromJson(s, FollowUserResponse.class);
                ((UserFollowInterface) callBackInterface).response(followUserResponse);
            } else if (command == Engine.COMMAND_FOLLOW_O_TO_O) {
                UserFollowOtOResponse followOtOResponse = new Gson().fromJson(s, UserFollowOtOResponse.class);
                ((UserFollowInterface) callBackInterface).response(followOtOResponse);
            }
        } else if (callBackInterface != null && callBackInterface instanceof ReleaseDynamicInterface) {
            if (command == Engine.COMMAND_POST_DYNAMIC) {
                ReleaseDynamicResponse releaseDynamicResponse = new Gson().fromJson(s, ReleaseDynamicResponse.class);
                ((ReleaseDynamicInterface) callBackInterface).response(releaseDynamicResponse);
            }
        } else if (callBackInterface != null && callBackInterface instanceof TaskMyRecInterface) {
            if (command == Engine.COMMAND_GET_MY_RCV_TASK_RECORD) {
                LocalLog.d(TAG, "我领取的任务");
                MyRecTaskRecordResponse myRecTaskRecordResponse = new Gson().fromJson(s, MyRecTaskRecordResponse.class);
                ((TaskMyRecInterface) callBackInterface).response(myRecTaskRecordResponse);
            }
        } else if (callBackInterface != null && callBackInterface instanceof TaskDetailRecInterface) {
            if (command == Engine.COMMAND_GET_REC_TASK_DETAIL) {
                TaskRecDetailResponse taskRecDetailResponse = new Gson().fromJson(s, TaskRecDetailResponse.class);
                ((TaskDetailRecInterface) callBackInterface).response(taskRecDetailResponse);
            } else if (command == Engine.COMMAND_RECV_TASK_PAY) {
                RecPayResponse recPayResponse = new Gson().fromJson(s, RecPayResponse.class);
                ((TaskDetailRecInterface) callBackInterface).response(recPayResponse);
            }
        } else if (callBackInterface != null && callBackInterface instanceof ReceiveTaskInterface) {
            if (command == Engine.COMMAND_RECV_TASK) {
                ReceiveTaskResponse receiveTaskResponse = new Gson().fromJson(s, ReceiveTaskResponse.class);
                ((ReceiveTaskInterface) callBackInterface).response(receiveTaskResponse);
            }
        } else if (callBackInterface != null && callBackInterface instanceof CrashRecordInterface) {
            CrashListDetailResponse crashListDetailResponse = new Gson().fromJson(s, CrashListDetailResponse.class);
            ((CrashRecordInterface) callBackInterface).response(crashListDetailResponse);
        } else if (callBackInterface != null && callBackInterface instanceof JoinCircleInterface) {
            LocalLog.d(TAG, "加入圈子");
            JoinCircleResponse joinCircleResponse = new Gson().fromJson(s, JoinCircleResponse.class);
            ((JoinCircleInterface) callBackInterface).response(joinCircleResponse);
        } else if (callBackInterface != null && callBackInterface instanceof SearchCircleInterface) {
            if (command == Engine.COMMAND_GET_CHOICE_CIRCLE) {
                ChoiceCircleResponse choiceCircleResponse = new Gson().fromJson(s, ChoiceCircleResponse.class);
                ((SearchCircleInterface) callBackInterface).response(choiceCircleResponse);
            }
        } else if (callBackInterface != null && callBackInterface instanceof AddDeleteFollowInterface
                && command == Engine.COMMAND_ADD_DELETE_FOLLOW) {
            LocalLog.d(TAG, "取消/关注");
            AddDeleteFollowResponse addDeleteFollowResponse = new Gson().fromJson(s, AddDeleteFollowResponse.class);
            ((AddDeleteFollowInterface) callBackInterface).response(addDeleteFollowResponse);
        } else if (callBackInterface != null && callBackInterface instanceof QueryRedPkgInterface) {
            if (command == Engine.COMMAND_GET_CIRCLE_DETAIL) {
                CircleDetailResponse circleDetailResponse = new Gson().fromJson(s, CircleDetailResponse.class);
                ((QueryRedPkgInterface) callBackInterface).response(circleDetailResponse);
            }
        } else if (callBackInterface != null && callBackInterface instanceof FriendHonorInterface) {
            if (command == Engine.COMMAND_FRIEND_HONOR) {
                FriendStepRankDayResponse friendStepRankDayResponse = new Gson().fromJson(s, FriendStepRankDayResponse.class);
                ((FriendHonorInterface) callBackInterface).response(friendStepRankDayResponse);
            }
        } else if (callBackInterface != null && callBackInterface instanceof DanCircleInterface) {
            if (command == Engine.COMMAND_GET_MY_HOT) {
                MyHotCircleResponse myHotCircleResponse = new Gson().fromJson(s, MyHotCircleResponse.class);
                ((DanCircleInterface) callBackInterface).response(myHotCircleResponse);
            } else if (command == Engine.COMMAND_STEP_RANK) {
                CircleStepRankResponse circleStepRankResponse = new Gson().fromJson(s, CircleStepRankResponse.class);
                ((DanCircleInterface) callBackInterface).response(circleStepRankResponse);
            }
        } else if (callBackInterface != null && callBackInterface instanceof FriendHonorDetailInterface) {
            if (command == Engine.COMMAND_FRIEND_HONOR) {
                FriendStepRankDayResponse friendStepRankDayResponse = new Gson().fromJson(s, FriendStepRankDayResponse.class);
                ((FriendHonorDetailInterface) callBackInterface).response(friendStepRankDayResponse);
            }
        } else {
            LocalLog.e(TAG, " dispatch not match");
        }
    }
}
