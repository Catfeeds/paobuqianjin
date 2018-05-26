package com.paobuqianjin.pbq.step.data.netcallback;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.l.okhttppaobu.okhttp.callback.StringCallback;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddDeleteAdminResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddDeleteFollowResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AllIncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.BindCardListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CheckSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleStepRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleStepRankWeekResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTagResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTargetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTypeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CrashListDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CrashResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DanListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DearNameResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DeleteCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicAllIndexResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicIdDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicLikeListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicPersonResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.EditCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendAddResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendStepRankDayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendWeekResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteDanResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteMessageResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.JoinCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LogBindPhoneResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginOutResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MemberDeleteResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageAtResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageContentResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageLikeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageSystemResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyInviteResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyJoinCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyRecTaskRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyReleaseTaskDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NearByResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.OldPassChangeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PassWordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostBindResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostBindStateResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostDynamicContentResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostInviteCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostRevRedPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostUserStepResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ProtocolResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PutVoteResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.QueryFollowStateResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecPayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReceiveTaskResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RechargeDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReleaseDynamicResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReleaseRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorRedPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepDollarDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRandWeekResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SuggestResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskMyReleaseResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskRecDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskReleaseResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskSponsorRespone;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ThirdPartyLoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserDanResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFollowOtOResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserIdFollowResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoSetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WalletPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WeatherResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayResultResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.YsPayOrderResponse;
import com.paobuqianjin.pbq.step.model.Engine;
import com.paobuqianjin.pbq.step.presenter.im.AddDeleteFollowInterface;
import com.paobuqianjin.pbq.step.presenter.im.BindThirdAccoutInterface;
import com.paobuqianjin.pbq.step.presenter.im.CallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.CircleDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.CircleMemberManagerInterface;
import com.paobuqianjin.pbq.step.presenter.im.CircleStepDetailDanInterface;
import com.paobuqianjin.pbq.step.presenter.im.CrashInterface;
import com.paobuqianjin.pbq.step.presenter.im.CrashRecordInterface;
import com.paobuqianjin.pbq.step.presenter.im.DanCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.DanInterface;
import com.paobuqianjin.pbq.step.presenter.im.DearNameModifyInterface;
import com.paobuqianjin.pbq.step.presenter.im.DynamicDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.DynamicIndexUiInterface;
import com.paobuqianjin.pbq.step.presenter.im.EditCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.ForgetPassWordInterface;
import com.paobuqianjin.pbq.step.presenter.im.FriendAddressInterface;
import com.paobuqianjin.pbq.step.presenter.im.FriendHonorDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.FriendHonorInterface;
import com.paobuqianjin.pbq.step.presenter.im.HomePageInterface;
import com.paobuqianjin.pbq.step.presenter.im.InviteInterface;
import com.paobuqianjin.pbq.step.presenter.im.JoinCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.LoginBindPhoneInterface;
import com.paobuqianjin.pbq.step.presenter.im.LoginSignCallbackInterface;
import com.paobuqianjin.pbq.step.presenter.im.MessageInterface;
import com.paobuqianjin.pbq.step.presenter.im.MyCreatCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.MyDynamicInterface;
import com.paobuqianjin.pbq.step.presenter.im.MyJoinCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.MyReleaseTaskDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.MyReleaseTaskInterface;
import com.paobuqianjin.pbq.step.presenter.im.NearByInterface;
import com.paobuqianjin.pbq.step.presenter.im.OlderPassInterface;
import com.paobuqianjin.pbq.step.presenter.im.OwnerUiInterface;
import com.paobuqianjin.pbq.step.presenter.im.PayInterface;
import com.paobuqianjin.pbq.step.presenter.im.PostInviteCodeInterface;
import com.paobuqianjin.pbq.step.presenter.im.ProtocolInterface;
import com.paobuqianjin.pbq.step.presenter.im.QueryRedPkgInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReceiveTaskInterface;
import com.paobuqianjin.pbq.step.presenter.im.RechargeDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReflashMyCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReleaseDynamicInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReleaseRecordInterface;
import com.paobuqianjin.pbq.step.presenter.im.SearchCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.SelectUserFriendInterface;
import com.paobuqianjin.pbq.step.presenter.im.SignCodeCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.SignCodeInterface;
import com.paobuqianjin.pbq.step.presenter.im.StepDollarDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.SuggestInterface;
import com.paobuqianjin.pbq.step.presenter.im.TagFragInterface;
import com.paobuqianjin.pbq.step.presenter.im.TaskDetailRecInterface;
import com.paobuqianjin.pbq.step.presenter.im.TaskMyRecInterface;
import com.paobuqianjin.pbq.step.presenter.im.TaskReleaseInterface;
import com.paobuqianjin.pbq.step.presenter.im.TaskSponsorInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiCreateCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiHotCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiStepAndLoveRankInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserFollowInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserHomeInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserIncomInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserInfoInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserInfoLoginSetInterface;
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
                    ((LoginSignCallbackInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null && callBackInterface instanceof UserInfoInterface) {
                    callBackInterface.response(errorCode);
                } else if (callBackInterface != null && callBackInterface instanceof SignCodeCallBackInterface) {
                    callBackInterface.response(errorCode);
                } else if (callBackInterface != null && callBackInterface instanceof LoginSignCallbackInterface
                        && command == Engine.COMMAND_REFRESH_PASSWORD) {
                    ((LoginSignCallbackInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null && callBackInterface instanceof MyCreateCircleResponse
                        && command == Engine.COMMAND_GET_MY_CREATE_CIRCLE) {
                    callBackInterface.response(errorCode);
                } else if (callBackInterface != null && callBackInterface instanceof UiHotCircleInterface
                        && command == Engine.COMMAND_GET_CHOICE_CIRCLE) {
                    ((UiHotCircleInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null
                        && callBackInterface instanceof MyJoinCircleInterface
                        && command == Engine.COMMAND_GET_MY_JOIN_CIRCLE) {
                    ((MyJoinCircleInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null
                        && callBackInterface instanceof UiCreateCircleInterface
                        && command == Engine.COMMAND_CIRCLE_TYPE) {
                    ((UiCreateCircleInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null
                        && callBackInterface instanceof UiStepAndLoveRankInterface
                        && command == Engine.COMMAND_RECHARGE_RANK) {
                    ((UiStepAndLoveRankInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null
                        && callBackInterface instanceof UiStepAndLoveRankInterface
                        && command == Engine.COMMAND_STEP_RANK) {
                    ((UiStepAndLoveRankInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null
                        && callBackInterface instanceof CircleDetailInterface) {
                    callBackInterface.response(errorCode);
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
                    ((UiCreateCircleInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null
                        && callBackInterface instanceof ReflashMyCircleInterface
                        && command == Engine.COMMAND_REFLASH_CIRCLE) {

                } else if (callBackInterface != null &&
                        callBackInterface instanceof DynamicIndexUiInterface
                        && command == Engine.COMMAND_GET_DYNAMIC_INDEX) {
                    callBackInterface.response(errorCode);
                } else if (callBackInterface != null
                        && callBackInterface instanceof DynamicDetailInterface
                        && command == Engine.COMMAND_DYNAMIC_CONTENTS) {
                    ((DynamicDetailInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null
                        && callBackInterface instanceof OwnerUiInterface
                        && command == Engine.COMMAND_OWNER_USER_INFO) {
                    callBackInterface.response(errorCode);
                } else if (callBackInterface != null
                        && callBackInterface instanceof PayInterface
                        && command == Engine.COMMAND_CIRCLE_ORDER_POST_WX) {
                    callBackInterface.response(errorCode);
                } else if (callBackInterface != null
                        && callBackInterface instanceof WxPayResultQueryInterface
                        && command == Engine.COMMAND_PAY_RESULT_QUERY_WX) {
                    ((WxPayResultQueryInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null
                        && callBackInterface instanceof PostInviteCodeInterface) {
                    if (command == Engine.COMMAND_POST_INVITE_CODE) {
                        ((PostInviteCodeInterface) callBackInterface).response(errorCode);
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
                } else if (callBackInterface != null
                        && callBackInterface instanceof PayInterface) {
                    callBackInterface.response(errorCode);
                } else if (callBackInterface != null
                        && callBackInterface instanceof TaskSponsorInterface) {
                    callBackInterface.response(errorCode);
                } else if (callBackInterface != null && callBackInterface instanceof ForgetPassWordInterface) {
                    ((ForgetPassWordInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null && callBackInterface instanceof BindThirdAccoutInterface) {
                    ((BindThirdAccoutInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null && callBackInterface instanceof LoginBindPhoneInterface) {
                    callBackInterface.response(errorCode);
                } else if (callBackInterface != null && callBackInterface instanceof OlderPassInterface) {
                    ((OlderPassInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null && callBackInterface instanceof SuggestInterface) {
                    ((SuggestInterface) callBackInterface).response(errorCode);
                } else if (callBackInterface != null && callBackInterface instanceof UserInfoLoginSetInterface) {
                    ((UserInfoLoginSetInterface) callBackInterface).response(errorCode);
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

            if (command == Engine.COMMAND_LOGIN_IN_BY_PHONE) {
                try {
                    LoginResponse loginResponse = new Gson().fromJson(s, LoginResponse.class);
                    ((LoginSignCallbackInterface) callBackInterface).response(loginResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_LOGIN_BY_THIRD) {
                LocalLog.d(TAG, "三方登录成功");
                try {
                    ThirdPartyLoginResponse thirdPartyLoginResponse = new Gson().fromJson(s, ThirdPartyLoginResponse.class);
                    ((LoginSignCallbackInterface) callBackInterface).response(thirdPartyLoginResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_REG_BY_PHONE) {
                try {
                    SignUserResponse signUserResponse = new Gson().fromJson(s, SignUserResponse.class);
                    ((LoginSignCallbackInterface) callBackInterface).response(signUserResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_GET_SIGN_CODE) {
                try {

                    GetSignCodeResponse getSignCodeResponse = new Gson().fromJson(s, GetSignCodeResponse.class);
                    ((LoginSignCallbackInterface) callBackInterface).response(getSignCodeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_PHONE_LOGIN) {
                try {
                    LoginRecordResponse loginRecordResponse = new Gson().fromJson(s, LoginRecordResponse.class);
                    ((LoginSignCallbackInterface) callBackInterface).response(loginRecordResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

        } else if (callBackInterface != null && callBackInterface instanceof UserInfoInterface) {
            try {
                UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                ((UserInfoInterface) callBackInterface).update(userInfoResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null && callBackInterface instanceof SignCodeCallBackInterface) {
            try {
                SignCodeResponse signCodeResponse = new Gson().fromJson(s, SignCodeResponse.class);
                ((SignCodeCallBackInterface) callBackInterface).signCodeCallBack(signCodeResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null && callBackInterface instanceof LoginSignCallbackInterface
                && command == Engine.COMMAND_REFRESH_PASSWORD) {
            LocalLog.d(TAG, s);
        } else if (callBackInterface != null && callBackInterface instanceof MyCreatCircleInterface
                && command == Engine.COMMAND_GET_MY_CREATE_CIRCLE) {
            LocalLog.d(TAG, "获取我创建的圈子");
            try {
                MyCreateCircleResponse myCreateCircleResponse = new Gson().fromJson(s, MyCreateCircleResponse.class);
                ((MyCreatCircleInterface) callBackInterface).response(myCreateCircleResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null && callBackInterface instanceof UiHotCircleInterface
                && command == Engine.COMMAND_GET_CHOICE_CIRCLE) {
            try {
                ChoiceCircleResponse choiceCircleResponse = new Gson().fromJson(s, ChoiceCircleResponse.class);
                ((UiHotCircleInterface) callBackInterface).response(choiceCircleResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof MyJoinCircleInterface
                && command == Engine.COMMAND_GET_MY_JOIN_CIRCLE) {
            LocalLog.d(TAG, "我加入的圈子");
            try {
                MyJoinCircleResponse myJoinCircleResponse = new Gson().fromJson(s, MyJoinCircleResponse.class);
                ((MyJoinCircleInterface) callBackInterface).response(myJoinCircleResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof UiHotCircleInterface
                && command == Engine.COMMAND_GET_MY_HOT) {
            try {
                MyHotCircleResponse myHotCircleResponse = new Gson().fromJson(s, MyHotCircleResponse.class);
                ((UiHotCircleInterface) callBackInterface).response(myHotCircleResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof UiCreateCircleInterface
                && command == Engine.COMMAND_CIRCLE_TYPE) {
            LocalLog.d(TAG, "圈子类型");
            try {
                CircleTypeResponse circleTypeResponse = new Gson().fromJson(s, CircleTypeResponse.class);
                ((UiCreateCircleInterface) callBackInterface).response(circleTypeResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof UiStepAndLoveRankInterface
                && command == Engine.COMMAND_RECHARGE_RANK) {
            LocalLog.d(TAG, "充值排行");
            try {
                ReChargeRankResponse reChargeRankResponse = new Gson().fromJson(s, ReChargeRankResponse.class);
                ((UiStepAndLoveRankInterface) callBackInterface).response(reChargeRankResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof UiStepAndLoveRankInterface
                && command == Engine.COMMAND_STEP_RANK) {
            LocalLog.d(TAG, "步数排行");
            try {
                StepRankResponse stepRankResponse = new Gson().fromJson(s, StepRankResponse.class);
                ((UiStepAndLoveRankInterface) callBackInterface).response(stepRankResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof CircleDetailInterface) {
            if (command == Engine.COMMAND_QUIT_CIRCLE) {
                try {
                    LoginOutResponse loginOutResponse = new Gson().fromJson(s, LoginOutResponse.class);
                    ((CircleDetailInterface) callBackInterface).response(loginOutResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_GET_CIRCLE_DETAIL) {
                LocalLog.d(TAG, "圈子详情");
                try {
                    CircleDetailResponse circleDetailResponse = new Gson().fromJson(s, CircleDetailResponse.class);
                    ((CircleDetailInterface) callBackInterface).response(circleDetailResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_POST_REV_RED_PKG) {
                try {
                    PostRevRedPkgResponse postRevRedPkgResponse = new Gson().fromJson(s, PostRevRedPkgResponse.class);
                    ((CircleDetailInterface) callBackInterface).response(postRevRedPkgResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_DELETE_CIRCLE) {
                try {
                    DeleteCircleResponse deleteCircleResponse = new Gson().fromJson(s, DeleteCircleResponse.class);
                    ((CircleDetailInterface) callBackInterface).response(deleteCircleResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

        } else if (callBackInterface != null
                && callBackInterface instanceof TagFragInterface
                && command == Engine.COMMAND_GET_TAG) {
            LocalLog.d(TAG, "获取标签!");
            try {
                CircleTagResponse circleTagResponse = new Gson().fromJson(s, CircleTagResponse.class);
                ((TagFragInterface) callBackInterface).response(circleTagResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof UiCreateCircleInterface
                && command == Engine.COMMAND_CREATE_CIRCLE) {
            LocalLog.d(TAG, "创建圈子");
            try {
                CreateCircleResponse createCircleResponse = new Gson().fromJson(s, CreateCircleResponse.class);
                ((UiCreateCircleInterface) callBackInterface).response(createCircleResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof UiCreateCircleInterface
                && command == Engine.COMMAND_GET_CIRCLE_TARGET) {
            LocalLog.d(TAG, "获取圈子目标");
            try {
                CircleTargetResponse circleTargetResponse = new Gson().fromJson(s, CircleTargetResponse.class);
                ((UiCreateCircleInterface) callBackInterface).response(circleTargetResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof ReflashMyCircleInterface
                && command == Engine.COMMAND_REFLASH_CIRCLE) {
            LocalLog.d(TAG, "获取圈子目标");
            try {
                MyCreateCircleResponse myCreateCircleResponse = new Gson().fromJson(s, MyCreateCircleResponse.class);
                ((ReflashMyCircleInterface) callBackInterface).response(myCreateCircleResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null &&
                callBackInterface instanceof DynamicIndexUiInterface
                && command == Engine.COMMAND_GET_DYNAMIC_INDEX) {
            LocalLog.d(TAG, "动态列表");
            try {
                DynamicAllIndexResponse dynamicAllIndexResponse = new Gson().fromJson(s, DynamicAllIndexResponse.class);
                ((DynamicIndexUiInterface) callBackInterface).response(dynamicAllIndexResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof DynamicDetailInterface) {
            if (command == Engine.COMMAND_DYNAMIC_CONTENTS) {
                LocalLog.d(TAG, "评论列表");
                try {
                    DynamicCommentListResponse dynamicCommentListResponse = new Gson().fromJson(s, DynamicCommentListResponse.class);
                    ((DynamicDetailInterface) callBackInterface).response(dynamicCommentListResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_GET_DYNAMIC_ID_DETAIL) {
                LocalLog.d(TAG, "动态详情");
                try {
                    DynamicIdDetailResponse dynamicIdDetailResponse = new Gson().fromJson(s, DynamicIdDetailResponse.class);
                    ((DynamicDetailInterface) callBackInterface).response(dynamicIdDetailResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_GET_VOTE_LIST) {
                LocalLog.d(TAG, "点赞列表");
                try {
                    DynamicLikeListResponse dynamicLikeListResponse = new Gson().fromJson(s, DynamicLikeListResponse.class);
                    ((DynamicDetailInterface) callBackInterface).response(dynamicLikeListResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_PUT_VOTE) {
                try {
                    PutVoteResponse putVoteResponse = new Gson().fromJson(s, PutVoteResponse.class);
                    ((DynamicDetailInterface) callBackInterface).response(putVoteResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_POST_DYNAMIC_COMMENT) {
                try {
                    PostDynamicContentResponse postDynamicContentResponse = new Gson().fromJson(s, PostDynamicContentResponse.class);
                    ((DynamicDetailInterface) callBackInterface).response(postDynamicContentResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

        } else if (callBackInterface != null
                && callBackInterface instanceof OwnerUiInterface
                && command == Engine.COMMAND_OWNER_USER_INFO) {
            LocalLog.d(TAG, "用户信息");
            try {
                UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                ((OwnerUiInterface) callBackInterface).response(userInfoResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof PayInterface) {
            if (command == Engine.COMMAND_CIRCLE_ORDER_POST_ALI) {
                LocalLog.d(TAG, "支付宝支付");
            } else if (command == Engine.COMMAND_CIRCLE_ORDER_POST_WX) {
                LocalLog.d(TAG, "微信支付");
                try {
                    WxPayOrderResponse wxPayOrderResponse = new Gson().fromJson(s, WxPayOrderResponse.class);
                    ((PayInterface) callBackInterface).response(wxPayOrderResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_CIRCLE_ORDER_POST_WALLET) {
                LocalLog.d(TAG, "钱包支付");
                try {
                    WalletPayOrderResponse walletPayOrderResponse = new Gson().fromJson(s, WalletPayOrderResponse.class);
                    ((PayInterface) callBackInterface).response(walletPayOrderResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_CIRCLE_ORDER_POST_YSPAY) {
                try {
                    YsPayOrderResponse ysPayOrderResponse = new Gson().fromJson(s, YsPayOrderResponse.class);
                    ((PayInterface) callBackInterface).response(ysPayOrderResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

        } else if (callBackInterface != null
                && callBackInterface instanceof WxPayResultQueryInterface
                && command == Engine.COMMAND_PAY_RESULT_QUERY_WX) {
            LocalLog.d(TAG, "订单信息");
            try {
                WxPayResultResponse wxPayResultResponse = new Gson().fromJson(s, WxPayResultResponse.class);
                ((WxPayResultQueryInterface) callBackInterface).response(wxPayResultResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof CircleMemberManagerInterface) {
            if (command == Engine.COMMAND_GET_MEMBER) {
                LocalLog.d(TAG, "获取圈子成员");
                try {
                    CircleMemberResponse circleMemberResponse = new Gson().fromJson(s, CircleMemberResponse.class);
                    ((CircleMemberManagerInterface) callBackInterface).response(circleMemberResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_DELETE_MEMBER) {
                LocalLog.d(TAG, "删除成员成功");
                try {
                    MemberDeleteResponse memberDeleteResponse = new Gson().fromJson(s, MemberDeleteResponse.class);
                    ((CircleMemberManagerInterface) callBackInterface).response(memberDeleteResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_SET_AS_ADMIN) {
                try {
                    AddDeleteAdminResponse addDeleteAdminResponse = new Gson().fromJson(s, AddDeleteAdminResponse.class);
                    ((CircleMemberManagerInterface) callBackInterface).response(addDeleteAdminResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

        } else if (callBackInterface != null
                && callBackInterface instanceof TaskReleaseInterface
                && command == Engine.COMMAND_TASK_RELEASE) {
            try {
                TaskReleaseResponse taskReleaseResponse = new Gson().fromJson(s, TaskReleaseResponse.class);
                ((TaskReleaseInterface) callBackInterface).response(taskReleaseResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof HomePageInterface) {
            if (command == Engine.COMMAND_POST_USER_STEP) {
                try {
                    PostUserStepResponse postUserStepResponse = new Gson().fromJson(s, PostUserStepResponse.class);
                    ((HomePageInterface) callBackInterface).response(postUserStepResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_WEATHER) {
                try {
                    WeatherResponse weatherResponse = new Gson().fromJson(s, WeatherResponse.class);
                    ((HomePageInterface) callBackInterface).responseWeather(weatherResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_INCOME_TODAY) {
                try {
                    IncomeResponse incomeResponse = new Gson().fromJson(s, IncomeResponse.class);
                    ((HomePageInterface) callBackInterface).responseTodayIncome(incomeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_INCOME_ALL) {
                try {
                    AllIncomeResponse incomeResponse = new Gson().fromJson(s, AllIncomeResponse.class);
                    ((HomePageInterface) callBackInterface).responseAllIncome(incomeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_SPONSOR_PKG) {
                LocalLog.d(TAG, "商圈红包");
                try {
                    SponsorRedPkgResponse sponsorRedPkgResponse = new Gson().fromJson(s, SponsorRedPkgResponse.class);
                    ((HomePageInterface) callBackInterface).response(sponsorRedPkgResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof NearByInterface
                && command == Engine.COMMAND_NEARBY_PEOPLE) {
            LocalLog.d(TAG, "附近的人");
            try {
                NearByResponse nearByResponse = new Gson().fromJson(s, NearByResponse.class);
                ((NearByInterface) callBackInterface).response(nearByResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null && callBackInterface instanceof UserIncomInterface) {
            if (command == Engine.COMMAND_INCOME_YESTERDAY) {
               /* LocalLog.d(TAG, "昨日收益");
                try {
                    IncomeResponse incomeResponse = new Gson().fromJson(s, IncomeResponse.class);
                    ((UserIncomInterface) callBackInterface).responseYesterday(incomeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }*/
            } else if (command == Engine.COMMAND_INCOME_TODAY) {
                LocalLog.d(TAG, "今日收益");
                try {
                    IncomeResponse incomeResponse = new Gson().fromJson(s, IncomeResponse.class);
                    ((UserIncomInterface) callBackInterface).responseToday(incomeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_INCOME_MONTH) {
                LocalLog.d(TAG, "月收益");
                try {
                    IncomeResponse incomeResponse = new Gson().fromJson(s, IncomeResponse.class);
                    ((UserIncomInterface) callBackInterface).responseMonth(incomeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_INCOME_ALL) {
                LocalLog.d(TAG, "总收益");
                try {
                    AllIncomeResponse allIncomeResponse = new Gson().fromJson(s, AllIncomeResponse.class);
                    ((UserIncomInterface) callBackInterface).responseAll(allIncomeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_GET_USER_INFO) {
                try {
                    UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                    ((UserIncomInterface) callBackInterface).response(userInfoResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof CrashInterface) {
            if (command == Engine.COMMAND_CRASH_BANK_CARD_LIST) {
                LocalLog.d(TAG, "获取绑定列表");
                try {
                    BindCardListResponse bindCardListResponse = new Gson().fromJson(s, BindCardListResponse.class);
                    ((CrashInterface) callBackInterface).response(bindCardListResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_CRASH_TO) {
                try {
                    CrashResponse crashResponse = new Gson().fromJson(s, CrashResponse.class);
                    ((CrashInterface) callBackInterface).response(crashResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof SignCodeInterface) {
            if (command == Engine.COMMAND_GET_SIGN_CODE) {
                try {
                    GetSignCodeResponse getSignCodeResponse = new Gson().fromJson(s, GetSignCodeResponse.class);
                    ((SignCodeInterface) callBackInterface).response(getSignCodeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_CHECK_SIGN_CODE) {
                try {
                    CheckSignCodeResponse checkSignCodeResponse = new Gson().fromJson(s, CheckSignCodeResponse.class);
                    ((SignCodeInterface) callBackInterface).response(checkSignCodeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_BIND_CRASH_ACCOUNT) {
                LocalLog.d(TAG, "绑定结果...");
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof SelectUserFriendInterface) {
            if (command == Engine.COMMAND_USER_FRIEND) {
                try {
                    UserFriendResponse userFriendResponse = new Gson().fromJson(s, UserFriendResponse.class);
                    ((SelectUserFriendInterface) callBackInterface).response(userFriendResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof MyDynamicInterface) {
            if (command == Engine.COMMAND_GET_USER_DYNAMIC) {
                try {
                    DynamicPersonResponse dynamicPersonResponse = new Gson().fromJson(s, DynamicPersonResponse.class);
                    ((MyDynamicInterface) callBackInterface).response(dynamicPersonResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof StepDollarDetailInterface) {
            if (command == Engine.COMMAND_GET_USER_STEP_DOLLAR_DETAIL) {
                LocalLog.d(TAG, "步币明细");
                try {
                    StepDollarDetailResponse dollarDetailResponse = new Gson().fromJson(s, StepDollarDetailResponse.class);
                    ((StepDollarDetailInterface) callBackInterface).response(dollarDetailResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof InviteInterface) {
            if (command == Engine.COMMAND_GET_INVITE_DAN) {
                try {
                    InviteDanResponse inviteDanResponse = new Gson().fromJson(s, InviteDanResponse.class);
                    ((InviteInterface) callBackInterface).response(inviteDanResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof PostInviteCodeInterface) {
            if (command == Engine.COMMAND_POST_INVITE_CODE) {
                try {
                    PostInviteCodeResponse postInviteCodeResponse = new Gson().fromJson(s, PostInviteCodeResponse.class);
                    ((PostInviteCodeInterface) callBackInterface).response(postInviteCodeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof MyReleaseTaskInterface) {
            if (command == Engine.COMMAND_GET_MY_RELEASE_TASK) {
                try {
                    TaskMyReleaseResponse taskMyReleaseResponse = new Gson().fromJson(s, TaskMyReleaseResponse.class);
                    ((MyReleaseTaskInterface) callBackInterface).response(taskMyReleaseResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof MyReleaseTaskDetailInterface) {
            if (command == Engine.COMMAND_GET_MY_RELEASE_TASK_DETAIL) {
                try {
                    MyReleaseTaskDetailResponse myReleaseTaskDetailResponse = new Gson().fromJson(s, MyReleaseTaskDetailResponse.class);
                    ((MyReleaseTaskDetailInterface) callBackInterface).response(myReleaseTaskDetailResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof ReleaseRecordInterface) {
            if (command == Engine.COMMAND_GET_MY_RELEASE_RECORD) {
                try {
                    ReleaseRecordResponse releaseRecordResponse = new Gson().fromJson(s, ReleaseRecordResponse.class);
                    ((ReleaseRecordInterface) callBackInterface).response(releaseRecordResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof DanInterface) {
            if (command == Engine.COMMAND_GET_DAN_LIST) {
                try {
                    DanListResponse danListResponse = new Gson().fromJson(s, DanListResponse.class);
                    ((DanInterface) callBackInterface).response(danListResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_GET_USER_DAN) {
                try {
                    UserDanResponse userDanResponse = new Gson().fromJson(s, UserDanResponse.class);
                    ((DanInterface) callBackInterface).response(userDanResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null
                && callBackInterface instanceof UserFollowInterface) {
            if (command == Engine.COMMAND_MY_FOLLOW) {
                try {
                    UserIdFollowResponse userIdFollowResponse = new Gson().fromJson(s, UserIdFollowResponse.class);
                    ((UserFollowInterface) callBackInterface).response(userIdFollowResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_FOLLOW_ME) {
                try {
                    FollowUserResponse followUserResponse = new Gson().fromJson(s, FollowUserResponse.class);
                    ((UserFollowInterface) callBackInterface).response(followUserResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_FOLLOW_O_TO_O) {
                try {
                    UserFollowOtOResponse followOtOResponse = new Gson().fromJson(s, UserFollowOtOResponse.class);
                    ((UserFollowInterface) callBackInterface).response(followOtOResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof ReleaseDynamicInterface) {
            if (command == Engine.COMMAND_POST_DYNAMIC) {
                try {
                    ReleaseDynamicResponse releaseDynamicResponse = new Gson().fromJson(s, ReleaseDynamicResponse.class);
                    ((ReleaseDynamicInterface) callBackInterface).response(releaseDynamicResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof TaskMyRecInterface) {
            if (command == Engine.COMMAND_GET_MY_RCV_TASK_RECORD) {
                LocalLog.d(TAG, "我领取的任务");
                try {
                    MyRecTaskRecordResponse myRecTaskRecordResponse = new Gson().fromJson(s, MyRecTaskRecordResponse.class);
                    ((TaskMyRecInterface) callBackInterface).response(myRecTaskRecordResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof TaskDetailRecInterface) {
            if (command == Engine.COMMAND_GET_REC_TASK_DETAIL) {
                try {
                    TaskRecDetailResponse taskRecDetailResponse = new Gson().fromJson(s, TaskRecDetailResponse.class);
                    ((TaskDetailRecInterface) callBackInterface).response(taskRecDetailResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_RECV_TASK_PAY) {
                try {
                    RecPayResponse recPayResponse = new Gson().fromJson(s, RecPayResponse.class);
                    ((TaskDetailRecInterface) callBackInterface).response(recPayResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof ReceiveTaskInterface) {
            if (command == Engine.COMMAND_RECV_TASK) {
                try {
                    ReceiveTaskResponse receiveTaskResponse = new Gson().fromJson(s, ReceiveTaskResponse.class);
                    ((ReceiveTaskInterface) callBackInterface).response(receiveTaskResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof CrashRecordInterface) {
            try {
                CrashListDetailResponse crashListDetailResponse = new Gson().fromJson(s, CrashListDetailResponse.class);
                ((CrashRecordInterface) callBackInterface).response(crashListDetailResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null && callBackInterface instanceof JoinCircleInterface) {
            LocalLog.d(TAG, "加入圈子");
            try {
                JoinCircleResponse joinCircleResponse = new Gson().fromJson(s, JoinCircleResponse.class);
                ((JoinCircleInterface) callBackInterface).response(joinCircleResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null && callBackInterface instanceof SearchCircleInterface) {
            if (command == Engine.COMMAND_GET_CHOICE_CIRCLE) {
                try {
                    ChoiceCircleResponse choiceCircleResponse = new Gson().fromJson(s, ChoiceCircleResponse.class);
                    ((SearchCircleInterface) callBackInterface).response(choiceCircleResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof AddDeleteFollowInterface
                && command == Engine.COMMAND_ADD_DELETE_FOLLOW) {
            LocalLog.d(TAG, "取消/关注");
            try {
                AddDeleteFollowResponse addDeleteFollowResponse = new Gson().fromJson(s, AddDeleteFollowResponse.class);
                ((AddDeleteFollowInterface) callBackInterface).response(addDeleteFollowResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null && callBackInterface instanceof QueryRedPkgInterface) {
            if (command == Engine.COMMAND_GET_CIRCLE_DETAIL) {
                try {
                    CircleDetailResponse circleDetailResponse = new Gson().fromJson(s, CircleDetailResponse.class);
                    ((QueryRedPkgInterface) callBackInterface).response(circleDetailResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof FriendHonorInterface) {
            if (command == Engine.COMMAND_FRIEND_HONOR) {
                try {
                    FriendStepRankDayResponse friendStepRankDayResponse = new Gson().fromJson(s, FriendStepRankDayResponse.class);
                    ((FriendHonorInterface) callBackInterface).response(friendStepRankDayResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof DanCircleInterface) {
            if (command == Engine.COMMAND_GET_MY_HOT) {
                try {
                    MyHotCircleResponse myHotCircleResponse = new Gson().fromJson(s, MyHotCircleResponse.class);
                    ((DanCircleInterface) callBackInterface).response(myHotCircleResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_STEP_RANK) {
                try {
                    CircleStepRankResponse circleStepRankResponse = new Gson().fromJson(s, CircleStepRankResponse.class);
                    ((DanCircleInterface) callBackInterface).response(circleStepRankResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof FriendHonorDetailInterface) {
            if (command == Engine.COMMAND_FRIEND_HONOR) {
                try {
                    FriendStepRankDayResponse friendStepRankDayResponse = new Gson().fromJson(s, FriendStepRankDayResponse.class);
                    ((FriendHonorDetailInterface) callBackInterface).response(friendStepRankDayResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_FRIEND_HONOR_WEEK) {
                try {
                    FriendWeekResponse friendWeekResponse = new Gson().fromJson(s, FriendWeekResponse.class);
                    ((FriendHonorDetailInterface) callBackInterface).response(friendWeekResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof CircleStepDetailDanInterface) {
            if (command == Engine.COMMAND_STEP_RANK) {
                try {
                    CircleStepRankResponse circleStepRankResponse = new Gson().fromJson(s, CircleStepRankResponse.class);
                    ((CircleStepDetailDanInterface) callBackInterface).response(circleStepRankResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_GET_CIRCLE_DETAIL) {
                try {
                    CircleDetailResponse circleDetailResponse = new Gson().fromJson(s, CircleDetailResponse.class);
                    ((CircleStepDetailDanInterface) callBackInterface).response(circleDetailResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_HONOR_DAN_DAY_STEP) {
                try {
                    StepRankResponse stepRankResponse = new Gson().fromJson(s, StepRankResponse.class);
                    ((CircleStepDetailDanInterface) callBackInterface).response(stepRankResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_HONOR_WEEK_STEP) {
                try {
                    StepRandWeekResponse stepRandWeekResponse = new Gson().fromJson(s, StepRandWeekResponse.class);
                    ((CircleStepDetailDanInterface) callBackInterface).response(stepRandWeekResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_HONOR_WEEK_RANK_NUM) {
                try {
                    CircleStepRankWeekResponse circleStepRankWeekResponse = new Gson().fromJson(s, CircleStepRankWeekResponse.class);
                    ((CircleStepDetailDanInterface) callBackInterface).response(circleStepRankWeekResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof RechargeDetailInterface) {
            try {
                RechargeDetailResponse rechargeDetailResponse = new Gson().fromJson(s, RechargeDetailResponse.class);
                ((RechargeDetailInterface) callBackInterface).response(rechargeDetailResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null && callBackInterface instanceof DearNameModifyInterface) {
            try {
                DearNameResponse dearNameResponse = new Gson().fromJson(s, DearNameResponse.class);
                ((DearNameModifyInterface) callBackInterface).response(dearNameResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null && callBackInterface instanceof ForgetPassWordInterface) {
            if (command == Engine.COMMAND_GET_SIGN_CODE) {
                try {
                    GetSignCodeResponse getSignCodeResponse = new Gson().fromJson(s, GetSignCodeResponse.class);
                    ((ForgetPassWordInterface) callBackInterface).response(getSignCodeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_CHECK_SIGN_CODE) {
                try {
                    CheckSignCodeResponse checkSignCodeResponse = new Gson().fromJson(s, CheckSignCodeResponse.class);
                    ((ForgetPassWordInterface) callBackInterface).response(checkSignCodeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_SET_PASS_WORD) {
                try {
                    PassWordResponse passWordResponse = new Gson().fromJson(s, PassWordResponse.class);
                    ((ForgetPassWordInterface) callBackInterface).response(passWordResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof LoginBindPhoneInterface) {
            if (command == Engine.COMMAND_GET_SIGN_CODE) {
                try {
                    GetSignCodeResponse getSignCodeResponse = new Gson().fromJson(s, GetSignCodeResponse.class);
                    ((LoginBindPhoneInterface) callBackInterface).response(getSignCodeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_CHECK_SIGN_CODE) {
                try {
                    CheckSignCodeResponse checkSignCodeResponse = new Gson().fromJson(s, CheckSignCodeResponse.class);
                    ((LoginBindPhoneInterface) callBackInterface).response(checkSignCodeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_THIRD_BIND_PHONE) {
                try {
                    LogBindPhoneResponse logBindPhoneResponse = new Gson().fromJson(s, LogBindPhoneResponse.class);
                    ((LoginBindPhoneInterface) callBackInterface).response(logBindPhoneResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof UserInfoLoginSetInterface) {
            if (command == Engine.COMMAND_USER_INFO_SET) {
                LocalLog.d(TAG, "设置资料返回");
                try {
                    UserInfoSetResponse userInfoSetResponse = new Gson().fromJson(s, UserInfoSetResponse.class);
                    ((UserInfoLoginSetInterface) callBackInterface).response(userInfoSetResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof MessageInterface) {
            if (command == Engine.COMMAND_AT_MESSAGE) {
                try {
                    MessageAtResponse messageAtResponse = new Gson().fromJson(s, MessageAtResponse.class);
                    ((MessageInterface) callBackInterface).response(messageAtResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_CONTENT_MESSAGE) {
                try {
                    MessageContentResponse messageContentResponse = new Gson().fromJson(s, MessageContentResponse.class);
                    ((MessageInterface) callBackInterface).response(messageContentResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_LIKE_MESSAGE) {
                try {
                    MessageLikeResponse messageLikeResponse = new Gson().fromJson(s, MessageLikeResponse.class);
                    ((MessageInterface) callBackInterface).response(messageLikeResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_SYS_MESSAGE) {
                try {
                    MessageSystemResponse messageSystemResponse = new Gson().fromJson(s, MessageSystemResponse.class);
                    ((MessageInterface) callBackInterface).response(messageSystemResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof FriendAddressInterface) {
            if (command == Engine.COMMAND_IVITE_MSG) {
                try {
                    InviteMessageResponse inviteMessageResponse = new Gson().fromJson(s, InviteMessageResponse.class);
                    ((FriendAddressInterface) callBackInterface).response(inviteMessageResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_UPDATE_ADDRESS_BOOK) {
                try {
                    FriendAddResponse friendAddResponse = new Gson().fromJson(s, FriendAddResponse.class);
                    ((FriendAddressInterface) callBackInterface).response(friendAddResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

        } else if (callBackInterface != null && callBackInterface instanceof EditCircleInterface) {
            if (command == Engine.COMMAND_GET_CIRCLE_TARGET) {
                try {
                    CircleTargetResponse circleTargetResponse = new Gson().fromJson(s, CircleTargetResponse.class);
                    ((EditCircleInterface) callBackInterface).response(circleTargetResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_EDIT_CIRCLE) {
                try {
                    EditCircleResponse editCircleResponse = new Gson().fromJson(s, EditCircleResponse.class);
                    ((EditCircleInterface) callBackInterface).response(editCircleResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof SuggestInterface) {
            try {
                SuggestResponse suggestResponse = new Gson().fromJson(s, SuggestResponse.class);
                ((SuggestInterface) callBackInterface).response(suggestResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null && callBackInterface instanceof OlderPassInterface) {
            try {
                OldPassChangeResponse oldPassChangeResponse = new Gson().fromJson(s, OldPassChangeResponse.class);
                ((OlderPassInterface) callBackInterface).response(oldPassChangeResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null && callBackInterface instanceof ProtocolInterface) {
            try {
                ProtocolResponse protocolResponse = new Gson().fromJson(s, ProtocolResponse.class);
                ((ProtocolInterface) callBackInterface).response(protocolResponse);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (callBackInterface != null && callBackInterface instanceof BindThirdAccoutInterface) {
            if (command == Engine.COMMAND_BIND_THIRD) {
                try {
                    PostBindResponse postBindResponse = new Gson().fromJson(s, PostBindResponse.class);
                    ((BindThirdAccoutInterface) callBackInterface).response(postBindResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            } else if (command == Engine.COMMAND_BIND_THIRD_STATE) {
                try {
                    PostBindStateResponse postBindStateResponse = new Gson().fromJson(s, PostBindStateResponse.class);
                    ((BindThirdAccoutInterface) callBackInterface).response(postBindStateResponse);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else if (callBackInterface != null && callBackInterface instanceof TaskSponsorInterface) {
            try {
                TaskSponsorRespone taskSponsorRespone = new Gson().fromJson(s, TaskSponsorRespone.class);
                ((TaskSponsorInterface) callBackInterface).response(taskSponsorRespone);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else {
            LocalLog.e(TAG, " dispatch not match");
        }
    }
}
