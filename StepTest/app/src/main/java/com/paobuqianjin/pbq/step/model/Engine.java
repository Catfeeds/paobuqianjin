package com.paobuqianjin.pbq.step.model;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.l.okhttppaobu.okhttp.OkHttpUtils;
import com.l.okhttppaobu.okhttp.builder.PostFormBuilder;
import com.l.okhttppaobu.okhttp.callback.StringCallback;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.AddBusinessParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.AuthenticationParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.BindCardPostParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CheckSignCodeParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CrashToParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.GetUserBusinessParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.JoinCircleParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.LoginOutParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PayOrderParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostBindUnBindWqParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostCircleRedPkgParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostDynamicContentParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostInviteCodeParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostPassByOldParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostPassWordParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostUserStepParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostWxQqBindPhoneParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutDearNameParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutUserInfoParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutVoteParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.QueryFollowStateParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.RedPkgRecParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.TaskReleaseParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CreateCircleBodyParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.DynamicContentParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostDynamicParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.FeedBackParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostIncomeParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostMessageParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.TaskSponsorParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.ThirdPartyLoginParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.UserHomeParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.UserRecordParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.VipPostParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddBusinessResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddDeleteFollowResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CurrentStepResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CvipNoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DeleteDynamicResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowStatusResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetUserBusinessResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteMessageResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LiveResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyInviteResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NearByResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NormalResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostBindResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PutVoteResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecPayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecRedPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReceiveTaskResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UpdateBusinessResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserCenterResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFollowOtOResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserIdFollowResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.VipNoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.YsPayResultResponse;
import com.paobuqianjin.pbq.step.data.netcallback.NetStringCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.AddDeleteFollowInterface;
import com.paobuqianjin.pbq.step.presenter.im.AllPayOrderInterface;
import com.paobuqianjin.pbq.step.presenter.im.BaiduMapInterface;
import com.paobuqianjin.pbq.step.presenter.im.BindThirdAccoutInterface;
import com.paobuqianjin.pbq.step.presenter.im.CallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.CircleDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.CircleMemberManagerInterface;
import com.paobuqianjin.pbq.step.presenter.im.CircleStepDetailDanInterface;
import com.paobuqianjin.pbq.step.presenter.im.CrashRecordInterface;
import com.paobuqianjin.pbq.step.presenter.im.CrashInterface;
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
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
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
import com.paobuqianjin.pbq.step.presenter.im.OnIdentifyLis;
import com.paobuqianjin.pbq.step.presenter.im.OwnerUiInterface;
import com.paobuqianjin.pbq.step.presenter.im.PayInterface;
import com.paobuqianjin.pbq.step.presenter.im.PhoneSignInterface;
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
import com.paobuqianjin.pbq.step.presenter.im.LoginCallBackInterface;
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
import com.paobuqianjin.pbq.step.presenter.im.UserInfoLoginSetInterface;
import com.paobuqianjin.pbq.step.presenter.im.WxPayResultQueryInterface;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.MD5;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.SharedPreferencesUtil;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;
import com.today.step.lib.ISportStepInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

import static com.paobuqianjin.pbq.step.utils.NetApi.urlFindPassWord;
import static com.paobuqianjin.pbq.step.utils.NetApi.urlMemberSearch;
import static com.paobuqianjin.pbq.step.utils.NetApi.urlNearByPeople;
import static com.paobuqianjin.pbq.step.utils.NetApi.urlProtocol;
import static com.paobuqianjin.pbq.step.utils.NetApi.urlRegisterPhone;
import static com.paobuqianjin.pbq.step.utils.NetApi.urlThirdLogin;
import static com.paobuqianjin.pbq.step.utils.NetApi.urlYsPayResultOrderNo;

/**
 * Created by pbq on 2017/11/29.
 */

public final class Engine {
    private final static String TAG = Engine.class.getSimpleName();
    private static Engine engine;
    private static Context mContext;
    private StepServerConnection connection = new StepServerConnection();
    private Messenger messengerStep;
    private Messenger serviceMessenger = new Messenger(new ServiceHandler());
    private final static int MSG_SEND_DATA_TO_SETP_SERVICE = 0;
    private final static int MSG_SEND_DATA_TO_ENGINE = 1;
    private LoginCallBackInterface loginCallBackInterface;
    private UiCreateCircleInterface uiCreateCircleInterface;
    private TagFragInterface tagFragInterface;
    private UiHotCircleInterface uiHotCircleInterface;
    private MyJoinCircleInterface myJoinCircleInterface;
    private MyCreatCircleInterface myCreatCircleInterface;
    private ReflashMyCircleInterface reflashMyCircleInterface;
    private UiStepAndLoveRankInterface uiStepAndLoveRankInterface;
    private CircleDetailInterface circleDetailInterface;
    private DynamicIndexUiInterface dynamicIndexUiInterface;
    private DynamicDetailInterface dynamicDetailInterface;
    private OwnerUiInterface ownerUiInterface;
    private PayInterface payInterface;
    private WxPayResultQueryInterface payWxResultQueryInterface;
    private CircleMemberManagerInterface circleMemberManagerInterface;
    private TaskReleaseInterface taskReleaseInterface;
    private AllPayOrderInterface allPayOrderInterface;
    private HomePageInterface homePageInterface;
    private NearByInterface nearByInterface;
    private UserIncomInterface userIncomInterface;
    private CrashInterface crashInterface;
    private SignCodeInterface signCodeInterface;
    private SelectUserFriendInterface selectUserFriendInterface;
    private UserHomeInterface userHomeInterface;
    private MyDynamicInterface myDynamicInterface;
    private StepDollarDetailInterface stepDollarDetailInterface;
    private InviteInterface inviteInterface;
    private PostInviteCodeInterface postInviteCodeInterface;
    private MyReleaseTaskInterface myReleaseTaskInterface;
    private MyReleaseTaskDetailInterface myReleaseTaskDetailInterface;
    private ReleaseRecordInterface releaseRecordInterface;
    private DanInterface danInterface;
    private UserFollowInterface userFollowInterface;
    private ReleaseDynamicInterface releaseDynamicInterface;
    private TaskMyRecInterface taskMyRecInterface;
    private TaskDetailRecInterface taskDetailRecInterface;
    private ReceiveTaskInterface receiveTaskInterface;
    private CrashRecordInterface crashRecordInterface;
    private JoinCircleInterface joinCircleInterface;
    private SearchCircleInterface searchCircleInterface;
    private AddDeleteFollowInterface addDeleteFollowInterface;
    private QueryRedPkgInterface queryRedPkgInterface;
    private FriendHonorInterface friendHonorInterface;
    private FriendHonorDetailInterface friendHonorDetailInterface;
    private DanCircleInterface danCircleInterface;
    private CircleStepDetailDanInterface circleStepDetailDanInterface;
    private RechargeDetailInterface rechargeDetailInterface;
    private DearNameModifyInterface dearNameModifyInterface;
    private ForgetPassWordInterface forgetPassWordInterface;
    private LoginBindPhoneInterface loginBindPhoneInterface;
    private UserInfoLoginSetInterface userInfoLoginSetInterface;
    private MessageInterface messageInterface;
    private FriendAddressInterface friendAddressInterface;
    private EditCircleInterface editCircleInterface;
    private SuggestInterface suggestInterface;
    private OlderPassInterface olderPassInterface;
    private ProtocolInterface protocolInterface;
    private BindThirdAccoutInterface bindThirdAccoutInterface;
    private BaiduMapInterface baiduMapInterface;
    private TaskSponsorInterface taskSponsorInterface;
    private PhoneSignInterface phoneSignInterface;
    private final static String STEP_ACTION = "com.paobuqianjian.intent.ACTION_STEP";
    private final static String LOCATION_ACTION = "com.paobuqianjin.intent.ACTION_LOCATION";
    private ISportStepInterface iSportStepInterface;
    private Picasso picasso = null;
    public final static int COMMAND_REQUEST_SIGN = 0;
    public final static int COMMAND_REG_BY_PHONE = 1;
    public final static int COMMAND_LOGIN_IN_BY_PHONE = 2;
    public final static int COMMAND_REFRESH_PASSWORD = 3;
    public final static int COMMAND_NEARBY_PEOPLE = 4;
    //
    public final static int COMMAND_CREATE_CIRCLE = 5;
    //
    public final static int COMMAND_GET_CHOICE_CIRCLE = 7;
    public final static int COMMAND_GET_MY_CREATE_CIRCLE = 8;
    public final static int COMMAND_GET_MY_JOIN_CIRCLE = 9;
    public final static int COMMAND_GET_CIRCLE_DETAIL = 10;
    public final static int COMMAND_EDIT_CIRCLE = 11;
    public final static int COMMAND_DELETE_CIRCLE = 12;
    public final static int COMMAND_STEP_RANK = 13;
    public final static int COMMAND_RECHARGE_RANK = 14;
    public final static int COMMAND_CIRCLE_TYPE = 15;
    public final static int COMMAND_JOIN_CIRCLE = 16;
    public final static int COMMAND_GET_TAG = 17;
    public final static int COMMAND_GET_CIRCLE_TARGET = 18;
    public final static int COMMAND_REFLASH_CIRCLE = 19;
    public final static int COMMAND_GET_DYNAMIC_INDEX = 20;
    public final static int COMMAND_DYNAMIC_CONTENTS = 21;
    public final static int COMMAND_OWNER_USER_INFO = 22;
    public final static int COMMAND_LOGIN_BY_THIRD = 23;
    public final static int COMMAND_CIRCLE_ORDER_POST_WX = 24;
    public final static int COMMAND_PAY_RESULT_QUERY_WX = 25;
    public final static int COMMAND_GET_MY_HOT = 26;
    public final static int COMMAND_GET_MEMBER = 27;
    public final static int COMMAND_TASK_RELEASE = 28;
    public final static int COMMAND_ALL_PAY_ORDER = 29;
    public final static int COMMAND_POST_USER_STEP = 30;
    public final static int COMMAND_INCOME_YESTERDAY = 31;
    public final static int COMMAND_INCOME_TODAY = 32;
    public final static int COMMAND_INCOME_MONTH = 33;
    public final static int COMMAND_INCOME_ALL = 34;
    public final static int COMMAND_CRASH_BANK_CARD_LIST = 35;
    public final static int COMMAND_GET_SIGN_CODE = 36;
    public final static int COMMAND_CHECK_SIGN_CODE = 37;
    public final static int COMMAND_BIND_CRASH_ACCOUNT = 38;
    public final static int COMMAND_CRASH_TO = 39;
    public final static int COMMAND_WEATHER = 40;
    public final static int COMMAND_USER_FRIEND = 41;
    public final static int COMMAND_USER_FRIEND_SEARCH = 42;
    public final static int COMMAND_GET_USER_DYNAMIC = 43;
    public final static int COMMAND_GET_USER_INFO = 44;
    public final static int COMMAND_GET_USER_STEP_DOLLAR_DETAIL = 45;
    public final static int COMMAND_GET_INVITE_DAN = 46;
    public final static int COMMAND_GET_MY_INVITE_MSG = 47;
    public final static int COMMAND_POST_INVITE_CODE = 48;
    public final static int COMMAND_GET_MY_RELEASE_TASK = 49;
    public final static int COMMAND_GET_MY_RELEASE_TASK_DETAIL = 50;
    public final static int COMMAND_GET_MY_RELEASE_RECORD = 51;
    public final static int COMMAND_GET_DAN_LIST = 52;
    public final static int COMMAND_GET_USER_DAN = 53;
    public final static int COMMAND_MY_FOLLOW = 54;
    public final static int COMMAND_FOLLOW_ME = 55;
    public final static int COMMAND_FOLLOW_O_TO_O = 56;
    public final static int COMMAND_GET_DYNAMIC_ID_DETAIL = 56;
    public final static int COMMAND_GET_ID_COMMENT = 57;
    public final static int COMMAND_GET_VOTE_LIST = 58;
    public final static int COMMAND_POST_DYNAMIC_COMMENT = 59;
    public final static int COMMAND_PUT_VOTE = 60;
    public final static int COMMAND_POST_DYNAMIC = 61;
    public final static int COMMAND_GET_MY_RCV_TASK_RECORD = 62;
    public final static int COMMAND_GET_REC_TASK_DETAIL = 63;
    public final static int COMMAND_RECV_TASK = 64;
    public final static int COMMAND_RECV_TASK_PAY = 65;
    public final static int COMMAND_CRASH_RECORD = 66;
    public final static int COMMAND_QUIT_CIRCLE = 67;
    public final static int COMMAND_QUERY_FOLLOW_STATE = 68;
    public final static int COMMAND_ADD_DELETE_FOLLOW = 69;
    public final static int COMMAND_FRIEND_HONOR = 70;
    public final static int COMMAND_POST_REV_RED_PKG = 71;
    public final static int COMMAND_DELETE_MEMBER = 72;
    public final static int COMMAND_RECHARGE_RECORD = 73;
    public final static int COMMAND_SPONSOR_PKG = 74;
    public final static int COMMAND_DEAR_NAME = 75;
    public final static int COMMAND_SET_AS_ADMIN = 76;
    public final static int COMMAND_SET_PASS_WORD = 77;
    public final static int COMMAND_PHONE_LOGIN = 78;
    public final static int COMMAND_THIRD_BIND_PHONE = 79;
    public final static int COMMAND_USER_INFO_SET = 80;
    public final static int COMMAND_AT_MESSAGE = 81;
    public final static int COMMAND_CONTENT_MESSAGE = 82;
    public final static int COMMAND_LIKE_MESSAGE = 83;
    public final static int COMMAND_SYS_MESSAGE = 84;
    public final static int COMMAND_UPDATE_ADDRESS_BOOK = 85;
    public final static int COMMAND_CIRCLE_ORDER_POST_ALI = 86;
    public final static int COMMAND_CIRCLE_ORDER_POST_WALLET = 87;
    public final static int COMMAND_CIRCLE_ORDER_POST_YSPAY = 100;
    public final static int COMMAND_FRIEND_HONOR_WEEK = 88;
    public final static int COMMAND_HONOR_DAN_DAY_STEP = 89;
    public final static int COMMAND_HONOR_WEEK_STEP = 90;
    public final static int COMMAND_HONOR_WEEK_RANK_NUM = 91;
    public final static int COMMAND_FEED_SUGGEST = 92;
    public final static int COMMAND_CHANGE_OLD_PASS = 93;
    public final static int COMMAND_PROTOCOL = 94;
    public final static int COMMAND_BIND_THIRD = 95;
    public final static int COMMAND_BIND_THIRD_STATE = 97;
    public final static int COMMAND_IVITE_MSG = 96;
    private double la = 0;
    private double lb = 0;
    private final static String START_STEP_ACTION = "com.paobuqianjin.step.START_STEP_ACTION";
    BDLocation location;

    public NetworkPolicy getNetworkPolicy() {
        return networkPolicy;
    }

    public void setNetworkPolicy(NetworkPolicy networkPolicy) {
        this.networkPolicy = networkPolicy;
    }

    private NetworkPolicy networkPolicy = NetworkPolicy.NO_STORE;

    public BDLocation getBdLocation() {
        return location;
    }

    private Engine() {
        if (picasso == null) {
            picasso = new Picasso.Builder(mContext)
                    .downloader(new OkHttp3Downloader(OkHttpUtils.getInstance().getOkHttpClient()))
                    .build();
            LocalLog.d(TAG, " 设置Picasso ");
            Picasso.setSingletonInstance(picasso);
        }
    }


    private static class PiccsoTransformation implements Transformation {
        WeakReference<ImageView> weakReference;
        private boolean isSave = false;
        String urlImage = "";

        PiccsoTransformation(final ImageView view, final String url, final boolean save) {
            weakReference = new WeakReference<ImageView>(view);
            isSave = save;
            urlImage = url;
        }

        PiccsoTransformation(final ImageView view) {
            weakReference = new WeakReference<ImageView>(view);
        }

        @Override
        public Bitmap transform(Bitmap source) {
            if (source == null) {
                return null;
            }
            if (isSave && !TextUtils.isEmpty(urlImage)) {
                int index = urlImage.lastIndexOf(".");
                String fileStyle = urlImage.substring(index, urlImage.length());
                LocalLog.d(TAG, "fileStyle =" + fileStyle);
                File cachePath = null;
                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                        || !Environment.isExternalStorageRemovable()) {
                    cachePath = mContext.getExternalCacheDir();
                    LocalLog.d(TAG, "getExternalCachdir() = " + cachePath);
                } else {
                    cachePath = mContext.getCacheDir();
                    LocalLog.d(TAG, "getCacheDir() = " + cachePath);
                }
                String adImgeName = cachePath + "ad" + fileStyle;
                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    source.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    FileOutputStream fos = new FileOutputStream(adImgeName);
                    fos.write(baos.toByteArray());
                    fos.flush();
                    fos.close();
                    FlagPreference.setAdUrl(mContext, urlImage);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }
            ImageView view = weakReference.get();
            if (view == null) {
                LocalLog.d(TAG, "view is null ");
                return source;
            }
            int targetWidth = view.getWidth();
            LocalLog.d(TAG, "targetWidth  =" + targetWidth);
            //返回原图
            if (source.getWidth() == 0 || source.getWidth() < targetWidth || targetWidth == 0) {
                return source;
            }

            //如果图片大小大于等于设置的宽度，则按照设置的宽度比例来缩放
            double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
            int targetHeight = (int) (targetWidth * aspectRatio);
            if (targetHeight == 0 || targetWidth == 0) {
                return source;
            }
            Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
            if (result != source) {
                // Same bitmap is returned if sizes are the same
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "transformation" + " desiredWidth";
        }
    }

    public static synchronized Engine getEngine(Context context) {
        mContext = context;
        if (engine == null) {
            engine = new Engine();
        }
        return engine;
    }

    public void startService(String action, Class<? extends Service> clazz) {
        LocalLog.d(TAG, "startService()  "
                + clazz.getSimpleName() + ",ACTION = " + action);
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setClass(mContext, clazz);
        mContext.startService(intent);
    }

    public void stopService(Class<? extends Service> clazz) {
        Intent intent = new Intent();
        intent.setClass(mContext, clazz);
        mContext.stopService(intent);
    }

    public boolean bindService(String action, Class<? extends Service> clazz) {
        LocalLog.d(TAG, "bindService() enter");
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setClass(mContext, clazz);
        return mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void unbindStepService() {
        LocalLog.d(TAG, "unbindStepService() enter");
        if (connection != null) {
            try {
                mContext.unbindService(connection);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

        }
    }

    public void postTaskSponsorRelease(TaskSponsorParam taskSponsorParam) {
        if (taskSponsorParam == null) {
            return;
        }
        LocalLog.d(TAG, "商家发红包" + taskSponsorParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlSendTaskRedBag)
                .params(taskSponsorParam.getParams())
                .build()
                .execute(new NetStringCallBack(taskSponsorInterface));
    }

    private class LocationServerConnection implements ServiceConnection {
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LocalLog.d(TAG, "LocationServerConnection onServiceDisconnected() enter");

        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LocalLog.d(TAG, "LocationServerConnection onServiceConnected() enter");
            if (iBinder != null) {
                messengerStep = new Messenger(iBinder);

            }

        }
    }

    private class StepServerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LocalLog.d(TAG, "onServiceConnected() = " + componentName);
            //Activity和Service通过aidl进行通信
            iSportStepInterface = ISportStepInterface.Stub.asInterface(iBinder);
            try {
                homePageInterface.responseStepToday(iSportStepInterface.getCurrentTimeSportStep());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LocalLog.d(TAG, "onServiceDisconnected() 断开连接！");
            iSportStepInterface = null;
        }
    }

    public void refreshStep() {
        if (null != iSportStepInterface) {
            try {
                int step = iSportStepInterface.getCurrentTimeSportStep();
                homePageInterface.responseStepToday(step);
                LocalLog.d(TAG, "refreshStep() step = " + step);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

    }

    public void sendToService(Bundle bundle, int what) {
        LocalLog.d(TAG, "sendBundleToService() enter");
        if (messengerStep != null) {
            Message msg = Message.obtain();
            msg.what = what;
            bundle.putString("Engine", "Engine -> StepService");
            msg.setData(bundle);
            msg.replyTo = serviceMessenger;
            try {
                messengerStep.send(msg);
            } catch (RemoteException e) {
                LocalLog.d(TAG, "sendToService() failed");
                e.printStackTrace();
            } finally {

            }
        }

    }

    public SharedPreferences getSharePreferences(Context context) {
        return FlagPreference.getSharedPreferences(context);
    }

    public int getPayResultCode(Context context) {
        return FlagPreference.getPayResultCode(context);
    }

    public void setPayResultCode(Context context, int errorCode) {
        FlagPreference.setPayResultCode(context, errorCode);
    }

    public String getOutTradeNo(Context context) {
        return FlagPreference.getOutTradeNo(context);
    }

    public void setOutTradeNo(Context context, String outTradeNO) {
        FlagPreference.setOutTradeNo(context, outTradeNO);
    }

    public void setTradeStyle(Context context, String tradeStyle) {
        FlagPreference.setTradeStyle(context, tradeStyle);
    }

    public String getTradeStyle(Context context) {
        return FlagPreference.getTradeStyle(context);
    }

    public boolean getLogFlag(Context context) {
        return FlagPreference.getLoginFlag(context);
    }

    public void setLogFlag(Context context, boolean isLogin) {
        FlagPreference.setLoginFlag(context, isLogin);
    }

    public void setToken(Context context, String user_token) {
        FlagPreference.setToken(context, user_token);
    }

    public boolean getReadCrashProtocol(Context context) {
        return FlagPreference.getReadCrashProtocol(context);
    }

    public void setReadCrashProtocol(Context context, boolean readFlg) {
        FlagPreference.setReadCrashProtocol(context, readFlg);
    }

    public String getToken(Context context) {
        return FlagPreference.getToken(context);
    }

    public int getId(Context context) {
        return FlagPreference.getUid(context);
    }

    public void setId(Context context, int id) {
        FlagPreference.setUid(context, id);
    }

    public String getNo(Context context) {
        return FlagPreference.getNo(context);
    }

    public void setNo(Context context, String no) {
        FlagPreference.setNo(context, no);
    }

    public String getAvatar(Context context) {
        return FlagPreference.getAvatar(context);
    }

    public void setAvatar(Context context, String avatar) {
        FlagPreference.setAvatar(context, avatar);
    }

    public void setNickName(Context context, String nickname) {
        FlagPreference.setNickName(context, nickname);
    }

    public String getNickName(Context context) {
        return FlagPreference.getNickName(context);
    }

    public void setTarget(Context context, int target) {
        FlagPreference.setTarget(context, target);
    }

    public int getTarget(Context context) {
        return FlagPreference.getTarget(context);
    }

    public String getMobile(Context context) {
        return FlagPreference.getMobile(context);
    }

    public void setMobile(Context context, String mobile) {
        FlagPreference.setMobile(context, mobile);
    }

    public String getStartSportTime(Context context) {
        return FlagPreference.getEffectStartSportTime(context);
    }

    public void setStartServiceTime(Context context, String startServiceTime) {
        FlagPreference.setStartServiceTime(context, startServiceTime);
    }

    public int getUnEffectStep(Context context) {
        return FlagPreference.getUnEffectStep(context);
    }

    public void setUnEffectStep(Context context, int step) {
        FlagPreference.setUnEffectStep(context, step);
    }

    public void getUserInfo(final int userId) {
        String url = NetApi.urlUser + String.valueOf(userId);
        LocalLog.d(TAG, "getUserInfo() url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(ownerUiInterface, COMMAND_OWNER_USER_INFO));
    }

    public void putUserInfo(int userid, PutUserInfoParam putUserInfoParam) {
        String url = NetApi.urlUser + String.valueOf(userid);
        LocalLog.d(TAG, "putUserInfo() enter url = " + url + "   ,putUserInfoParam = " + putUserInfoParam.paramString());
        if (TextUtils.isEmpty(putUserInfoParam.paramString())) {
            Toast.makeText(mContext, "至少修改一项资料", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils
                .put()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .requestBody(new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return MediaType.parse("application/x-www-form-urlencoded");
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {

                    }
                })
                .params(putUserInfoParam.getParams())
                .build()
                .execute(new NetStringCallBack(userInfoLoginSetInterface, COMMAND_USER_INFO_SET));
    }

    //重置密码
    public void refreshPassWorld() {
        LocalLog.d(TAG, "findPassWorld() enter");
        String url = urlFindPassWord + "13424156029";
        OkHttpUtils
                .put()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .requestBody(new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return MediaType.parse("application/x-www-form-urlencoded");
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {

                    }
                })
                .param("password", "1223434")
                .param("code", "123456")
                .build()
                .execute(new NetStringCallBack(loginCallBackInterface, COMMAND_REFRESH_PASSWORD));
    }

    public void getLoginRecord(String userid) {
        String url = NetApi.urlLoginRecord + userid;
        LocalLog.d(TAG, "getLoginRecord() enter " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(loginCallBackInterface, COMMAND_PHONE_LOGIN));
    }


    //TODO 短信邀请
    public void inviteMsg(String phoneNum, final Button button) {
        LocalLog.d(TAG, "inviteMsg() phoneNum " + phoneNum);
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlinviteMsg)
                .addParams("userid", String.valueOf(getId(mContext)))
                .addParams("phones", "[" + "\"" + phoneNum + "\"" + "]")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        try {
                            ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                            PaoToastUtils.showLongToast(mContext, errorCode.getMessage());
                        } catch (JsonSyntaxException s) {
                            s.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            InviteMessageResponse inviteMessageResponse = new Gson().fromJson(s, InviteMessageResponse.class);
                            if (inviteMessageResponse.getError() == 0) {
                                if (button != null) {
                                    PaoToastUtils.showLongToast(mContext, "发送邀请成功");
                                    /*button.setText("邀请过");*/
                                }
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //手机号码注册
    public void registerByPhoneNumber(String[] userInfo, String pbqj_no) {
        LocalLog.d(TAG, "registerByPhoneNumber() enter");
        if (userInfo[0] == null && !isPhone(userInfo[0])) {
            Toast.makeText(mContext, "请输入一个手机号码:", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userInfo[0] == null) {
            Toast.makeText(mContext, "需要验证码:", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userInfo[2] == null) {
            Toast.makeText(mContext, "注册需要设置密码:", Toast.LENGTH_SHORT).show();
            return;
        }
        LocalLog.d(TAG, userInfo[0] + " ," + userInfo[2] + "," + userInfo[1]);
        PostFormBuilder postFormBuilder = OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(urlRegisterPhone)
                .addParams("mobile", userInfo[0])
                .addParams("password", MD5.md5Slat(userInfo[2]))
                .addParams("code", userInfo[1]);
        if (!TextUtils.isEmpty(pbqj_no)) {
            postFormBuilder.addParams("pbqj_no", pbqj_no);
        }
        postFormBuilder.build()
                .execute(new NetStringCallBack(phoneSignInterface, COMMAND_REG_BY_PHONE));
    }

    //TODO 获取附近的人http://119.29.10.64/v1/user/getNearbyPeople?userid=1&longitude=86.26000&latitude=35.17000
    public void getNearByPeople(double latitude, double longitude, int page, int pagesize, final NearByInterface nearByInterface) {
        String url = urlNearByPeople + "?userid=" + String.valueOf(getId(mContext)) + "&latitude=" + String.valueOf(latitude)
                + "&longitude=" + String.valueOf(longitude) + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        LocalLog.d(TAG, "url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        if (nearByInterface != null) {
                            ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                            nearByInterface.response(errorCode);
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            LocalLog.d(TAG, "S = " + s);
                            if (nearByInterface != null) {
                                NearByResponse nearByResponse = new Gson().fromJson(s, NearByResponse.class);
                                nearByInterface.response(nearByResponse);
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //TODO 获取验证码 http://119.29.10.64/v1/ThirdParty/sendMsg/?mobile=13424156029
    public void getSignCode(String phone) {
        String url = NetApi.urlSendMsg + phone + keyStr(phone);
        LocalLog.d(TAG, "getSignCode() enter url  = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(signCodeInterface, COMMAND_GET_SIGN_CODE));
    }

    public boolean getSignCodeLoginBind(String phone) {
        String url = NetApi.urlSendMsg + phone + keyStr(phone);
        if (!isPhone(phone)) {
            Toast.makeText(mContext, "请输入一个手机号码:", Toast.LENGTH_SHORT).show();
            return false;

        }
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(loginBindPhoneInterface, COMMAND_GET_SIGN_CODE));
        return true;
    }

    public boolean getSignCodePassWord(String phone) {
        if (!Utils.isMobile(phone)) {
            Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return false;

        }
        String url = NetApi.urlSignCode + "/?mobile=" + phone + keyStr(phone);
        LocalLog.d(TAG, "getSignCode() enter url  = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(forgetPassWordInterface, COMMAND_GET_SIGN_CODE));
        return true;
    }

    public void postPassByOlder(PostPassByOldParam postPassByOldParam) {
        postPassByOldParam.setUserid(getId(mContext));
        LocalLog.d(TAG, "postPassByOlder() enter " + postPassByOldParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlByOldPassWord)
                .params(postPassByOldParam.getParams())
                .build()
                .execute(new NetStringCallBack(olderPassInterface, COMMAND_CHANGE_OLD_PASS));
    }

    public void postNewPassWord(PostPassWordParam postPassWordParam) {
        LocalLog.d(TAG, "修改密码" + postPassWordParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlPassWord)
                .params(postPassWordParam.getParams())
                .build()
                .execute(new NetStringCallBack(forgetPassWordInterface, COMMAND_SET_PASS_WORD));
    }

/*    public void checkLoginBindPhone(CheckSignCodeParam checkSignCodeParam) {
        LocalLog.d(TAG, checkSignCodeParam.paramString());
        OkHttpUtils
                .post()
                .url(NetApi.urlSignCodeCheck)
                .params(checkSignCodeParam.getParams())
                .build()
                .execute(new NetStringCallBack(loginBindPhoneInterface, COMMAND_CHECK_SIGN_CODE));
    }*/

    public void bindLoginPhone(PostWxQqBindPhoneParam postWxQqBindPhoneParam) {
        postWxQqBindPhoneParam.setUserid(getId(mContext));
        LocalLog.d(TAG, "bindLoginPhone() enter " + postWxQqBindPhoneParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlBindPhone)
                .params(postWxQqBindPhoneParam.getParams())
                .build()
                .execute(new NetStringCallBack(loginBindPhoneInterface, COMMAND_THIRD_BIND_PHONE));
    }

/*    public void checkSignCodePassWord(CheckSignCodeParam checkSignCodeParam) {
        LocalLog.d(TAG, checkSignCodeParam.paramString());
        OkHttpUtils
                .post()
                .url(NetApi.urlSignCodeCheck)
                .params(checkSignCodeParam.getParams())
                .build()
                .execute(new NetStringCallBack(forgetPassWordInterface, COMMAND_CHECK_SIGN_CODE));
    }*/

    //TODO 校验验证码
    public void checkSignCode(CheckSignCodeParam checkSignCodeParam) {
        LocalLog.d(TAG, checkSignCodeParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlSignCodeCheck)
                .params(checkSignCodeParam.getParams())
                .build()
                .execute(new NetStringCallBack(signCodeInterface, COMMAND_CHECK_SIGN_CODE));
    }

    //TODO 用户提现
    public void postCrashTo(CrashToParam crashToParam) {
        LocalLog.d(TAG, crashToParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlCrashToWx)
                .params(crashToParam.getParams())
                .build()
                .execute(new NetStringCallBack(crashInterface, COMMAND_CRASH_TO));
    }

    public void getCrashRecord(int pageIndex, int pagesize) {
        String url = NetApi.urlCrashTo + "?userid=" + String.valueOf(getId(mContext)) + "&page=" + String.valueOf(pageIndex)
                + "&pagesize=" + pagesize;
        LocalLog.d(TAG, "getCrashRecord() url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(crashRecordInterface, COMMAND_CRASH_RECORD));
    }

    public void getRechargeRecord(int page, int pagesize) {
        String url = NetApi.urlRechargeRecord;
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .addParams("userid", String.valueOf(getId(mContext)))
                .addParams("page", String.valueOf(page))
                .addParams("pagesize", String.valueOf(pagesize))
                .build()
                .execute(new NetStringCallBack(rechargeDetailInterface, COMMAND_RECHARGE_RECORD));
    }


    private String keyStr(String phone) {
        String timeStemp = String.valueOf(System.currentTimeMillis() / 1000);
        return "&term=app&app_sign=" + MD5.md5Slat(Utils.KEY_SIGN + phone + timeStemp) + "&timestamp=" + timeStemp;
    }

    //获取验证码
    public boolean getMsg(String phone) {
        LocalLog.d(TAG, "getMsg() enter phone =" + phone);
        if (!Utils.isMobile(phone)) {
            Toast.makeText(mContext, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return false;

        }
        String url = NetApi.urlSendMsg + phone + keyStr(phone);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(loginCallBackInterface, COMMAND_GET_SIGN_CODE));
        return true;
    }

    /**
     * 判断手机格式是否正确
     *
     * @param str 需要判断的字符串
     * @return 返回true说明字符串匹配成功
     */
    // Pattern类的作用在于编译正则表达式后创建一个匹配模式. Matcher类使用Pattern实例提供的模式信息对正则表达式进行匹配
    private boolean isPhone(String str) {
        // 将给定的正则表达式编译并赋予给Pattern类
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        // 对指定输入的字符串创建一个Matcher对象
        Matcher matcher = pattern.matcher(str);
        // 尝试对整个目标字符展开匹配检测,也就是只有整个目标字符串完全匹配时才返回真值.
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public void userLoginByPhoneOrNo(String[] userInfo) {
        LocalLog.d(TAG, "userLoginByPhoneOrNo() enter ");

        String md5PassWord = MD5.md5Slat(userInfo[1]);
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .addHeader("pushtoken", userInfo[2])
                .url(NetApi.urlUserLogin)
                .addParams("mobile", userInfo[0])
                .addParams("password", md5PassWord)
                .build()
                .execute(new NetStringCallBack(loginCallBackInterface, COMMAND_LOGIN_IN_BY_PHONE));
    }

    //搜索圈子成员 TODO
    public void searchCircleMember(int circleId, String keyword) {
        LocalLog.d(TAG, "searchCircleMember() enter");

    }

    //获取圈子订单类型列表
    public void getOrderType() {
        LocalLog.d(TAG, "getOrderType() enter");
        OkHttpUtils
                .get()
                .url(NetApi.urlCircleOrderType)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //获取圈子标签列表
    public void getCircleTag() {
        String url = NetApi.urlCircleTags;
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(tagFragInterface, COMMAND_GET_TAG));
    }

/*    //GET read 获取单个或者多个标签 http://119.29.10.64/v1/CircleTags/4,5,6,7
    public void getCircleTagByTagId(int a[]) {
        LocalLog.d(TAG, "getCircleTagByTagId() enter");
        String url = NetApi.urlCircleTags + "/";
        for (int i = 0; i < a.length; i++) {
            url += String.valueOf(a[i]) + ",";
        }

        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }*/

    //获取圈子封面列表
    public void getCircleCover() {
        LocalLog.d(TAG, "getCircleCover() enter");
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlCircleCover)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //TODO 获取圈子的封面
    public void getCircleCoverByCircleId(int circleId) {

    }

    //TODO 动态接口 GET index 获取已关注人动态列表
    public void getDynamicIndex(int page, int pagesize) {
        LocalLog.d(TAG, "getDynamicIndex() enter" + "当前页 " + page + "页最大数据目:" + pagesize);
        String url = NetApi.urlDynamic + "/?userid=" + String.valueOf(getId(mContext)) + "&page=" + page + "&pagesize=" + pagesize;
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(dynamicIndexUiInterface, Engine.COMMAND_GET_DYNAMIC_INDEX));
    }

    //TODO 获取个人主页
    public void getUserHome(String userid, String userno, int page, int pagesize, final InnerCallBack userCenterCallBack) {
        final String url = NetApi.urlUserHome;
        UserHomeParam userHomeParam = new UserHomeParam();
        userHomeParam.setPage(page).setPagesize(pagesize);
        if (!TextUtils.isEmpty(userid)) {
            userHomeParam.setUserid(Integer.parseInt(userid));
        } else {
            if (!TextUtils.isEmpty(userno)) {
                userHomeParam.setUserno(userno);
            } else {
                LocalLog.e(TAG, "非法用户");
                return;
            }
        }

        LocalLog.d(TAG, "getUserHome() enter url = " + url + ", UserHomeParam = " + userHomeParam.paramString());
        OkHttpUtils
                .post()
                .params(userHomeParam.getParams())
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        try {
                            ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                            if (userCenterCallBack != null) {
                                userCenterCallBack.innerCallBack(errorCode);
                            }
                        } catch (JsonSyntaxException j) {
                            j.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            UserCenterResponse userCenterResponse = new Gson().fromJson(s, UserCenterResponse.class);
                            if (userCenterCallBack != null) {
                                userCenterCallBack.innerCallBack(userCenterResponse);
                            }
                        } catch (JsonSyntaxException j) {
                            j.printStackTrace();
                        }
                    }
                });

    }

    //TODO 获取当前用户的个人动态
    public void getMyDynamic(int page, int pagesize) {
        String url = NetApi.urlDynamic + "/getUserDynamic?userid=" + String.valueOf(getId(mContext)) + "&page=" +
                String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        LocalLog.d(TAG, "getMyDynamic() enter");
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(myDynamicInterface, COMMAND_GET_USER_DYNAMIC));
    }

    //TODO 获取个人用户信息【非本人】
    public void getUserInfo(final String userid) {
        String url = NetApi.urlUser + userid;
        LocalLog.d(TAG, "getUserInfo() url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(userHomeInterface, COMMAND_GET_USER_INFO));
    }

    //TODO 发表动态
    public void postDynamic(PostDynamicParam postDynamicParam) {
        LocalLog.d(TAG, "postDynamic() enter" + postDynamicParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlDynamic)
                .params(postDynamicParam.getParams())
                .build()
                .execute(new NetStringCallBack(releaseDynamicInterface, COMMAND_POST_DYNAMIC));
    }

    //TODO 动态详情
    public void getDynamicDetail(int id) {
        LocalLog.d(TAG, "getDynamicDetail() enter");
        String url = NetApi.urlDynamic + "/" + String.valueOf(id);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(dynamicDetailInterface, COMMAND_GET_DYNAMIC_ID_DETAIL));
    }

    //TODO 删除动态
    public void deleteDynamic(int dynamicId, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "deleteDynamic() enter");
        String url = NetApi.urlDynamic + "/" + String.valueOf(dynamicId);
        OkHttpUtils
                .delete()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        LocalLog.d(TAG, "S = " + s);
                        if (innerCallBack != null) {
                            DeleteDynamicResponse deleteDynamicResponse = new Gson().fromJson(s, DeleteDynamicResponse.class);
                            innerCallBack.innerCallBack(deleteDynamicResponse);
                        }
                    }
                });

    }

    //TODO 获取评论列表 http://119.29.10.64/v1/DynamicComment/?dynamicid=1
    public void getDynamicCommentList(int dynamicid, int page, int pagesize) {
        String url = NetApi.urlDynamicComment + "/getComment?dynamicid=" + String.valueOf(dynamicid) + "&page=" +
                String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        LocalLog.d(TAG, "getDynamicCommentList() enter url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(dynamicDetailInterface, COMMAND_DYNAMIC_CONTENTS));
    }

    //TODO 获取点赞列表
    public void getDynamicVoteList(int dynamicid, int userid, int page, int pagesize) {
        String url = NetApi.urlDynamicVote + "?dynamicid=" + String.valueOf(dynamicid) + "&userid=" + String.valueOf(userid)
                + "&page=" + String.valueOf(page) + "&pagesie=" + String.valueOf(pagesize);
        LocalLog.d(TAG, "getDynamicVoteList() enter url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(dynamicDetailInterface, COMMAND_GET_VOTE_LIST));
    }

    //TODO 发表评论
    public void postContent(PostDynamicContentParam postDynamicContentParam) {
        LocalLog.d(TAG, "postContent() enter " + postDynamicContentParam.paramString());
        if (String.valueOf(getId(mContext)).equals(String.valueOf(postDynamicContentParam.getReply_userid()))) {
            PaoToastUtils.showLongToast(mContext, "不能评论自己");
            return;
        }
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlDynamicComment)
                .params(postDynamicContentParam.getParams())
                .build()
                .execute(new NetStringCallBack(dynamicDetailInterface, COMMAND_POST_DYNAMIC_COMMENT));
    }

    public void putVote(final PutVoteParam putVoteParam, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "putVote() enter " + putVoteParam.paramString());
        OkHttpUtils
                .put()
                .addHeader("headtoken", getToken(mContext))
                .requestBody(new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return MediaType.parse("application/x-www-form-urlencoded");
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {

                    }
                })
                .url(NetApi.urlDynamicVote + "/" + String.valueOf(putVoteParam.getDynamicid()))
                .param("userid", String.valueOf(putVoteParam.getUserid()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        PutVoteResponse putVoteResponse = new Gson().fromJson(s, PutVoteResponse.class);
                        if (innerCallBack != null) {
                            innerCallBack.innerCallBack(putVoteResponse);
                        }
                    }
                });
    }

    //TODO 点赞
    public void putVote(PutVoteParam putVoteParam) {
        LocalLog.d(TAG, "putVote() enter " + putVoteParam.paramString());
        OkHttpUtils
                .put()
                .addHeader("headtoken", getToken(mContext))
                .requestBody(new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return MediaType.parse("application/x-www-form-urlencoded");
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {

                    }
                })
                .url(NetApi.urlDynamicVote + "/" + String.valueOf(putVoteParam.getDynamicid()))
                .param("userid", String.valueOf(putVoteParam.getUserid()))
                .build()
                .execute(new NetStringCallBack(dynamicDetailInterface, COMMAND_PUT_VOTE));

    }

    //TODO 获取单条评论
    public void getContentById(int id) {
        LocalLog.d(TAG, "getDynamicById() enter");
        String url = NetApi.urlDynamicComment + "/" + String.valueOf(id);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //Post 发送评论
    public void postDynamicComment(DynamicContentParam dynamicContentParam) {
        LocalLog.d(TAG, "postDynamicComment() enter");
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlDynamicComment)
                .params(dynamicContentParam.getParam())
                .build()
                .execute(new NetStringCallBack(null, -1));
    }


    //获取反馈信息列表
    public void getFeedBackList(String name, String content, String mobile, String creattime) {
        LocalLog.d(TAG, "getFeedBackList() enter");
        String url = NetApi.urlFeedBack + "?name=" + name + "&content=" + content
                + "&mobile=" + mobile + "&creattime=" + creattime;

        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //添加用户反馈
    public void postFeedBack(FeedBackParam feedBackParam) {
        LocalLog.d(TAG, "postFeedBack() enter");
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlFeedBack)
                .params(feedBackParam.getParam())
                .build()
                .execute(new NetStringCallBack(suggestInterface, COMMAND_FEED_SUGGEST));
    }

    //TODO 获取所有圈子成员
    public void getCircleMemberAll(int circleid, int page, int pagesize) {
        LocalLog.d(TAG, "getCircleMemberAll() enter");
        String url = NetApi.urlCircleMember + "/" + String.valueOf(circleid)
                + "&page=" + page + "&pagesize=" + pagesize;
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(circleMemberManagerInterface, COMMAND_GET_MEMBER));
    }

    public void searchCircleMember(String circleid, int pageIndex, int pageSize, String keyWord, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "searchCircleMember() enter");
        if (!TextUtils.isEmpty(keyWord)) {
            String ulr = urlMemberSearch + circleid + "&page=" + String.valueOf(pageIndex) + "&pagesize=" + String.valueOf(pageSize) + "&keyword=" + keyWord;
            LocalLog.d(TAG, "searchCircleMember() url = " + ulr);
            OkHttpUtils
                    .get()
                    .addHeader("headtoken", getToken(mContext))
                    .url(ulr)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int i, Object o) {
                            if (e != null) {
                                e.printStackTrace();
                            }
                            if (o != null) {
                                try {
                                    ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                    if (innerCallBack != null) {
                                        innerCallBack.innerCallBack(errorCode);
                                    }
                                } catch (JsonSyntaxException j) {
                                    j.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onResponse(String s, int i) {
                            try {
                                CircleMemberResponse circleMemberResponse = new Gson().fromJson(s, CircleMemberResponse.class);
                                if (innerCallBack != null) {
                                    innerCallBack.innerCallBack(circleMemberResponse);
                                }
                            } catch (JsonSyntaxException j) {
                                j.printStackTrace();
                            }
                        }
                    });
        }
    }

    public void deleteCircleMember(String idStr) {
        String url = NetApi.urlCircleMember + "/" + idStr;
        LocalLog.d(TAG, "deleteCircleMember()" + url);
        OkHttpUtils
                .delete()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(circleMemberManagerInterface, COMMAND_DELETE_MEMBER));
    }

    public void modifyDearName(PutDearNameParam putDearNameParam) {

        String url = NetApi.urlCircleMember + "/" + String.valueOf(putDearNameParam.getId());
        LocalLog.d(TAG, "modifyDearName() enter " + putDearNameParam.paramString() + ", url = " + url);
        switch (putDearNameParam.getAction()) {
            case "remark":
                LocalLog.d(TAG, "修改昵称");
                OkHttpUtils
                        .put()
                        .addHeader("headtoken", getToken(mContext))
                        .requestBody(new RequestBody() {
                            @Override
                            public MediaType contentType() {
                                return MediaType.parse("application/x-www-form-urlencoded");
                            }

                            @Override
                            public void writeTo(BufferedSink sink) throws IOException {

                            }
                        })
                        .url(url)
                        .param("action", "remark")
                        .param("nickname", putDearNameParam.getNickname())
                        .build()
                        .execute(new NetStringCallBack(dearNameModifyInterface, COMMAND_DEAR_NAME));
                break;
            case "admin":
                LocalLog.d(TAG, "设置为管理员");
                OkHttpUtils
                        .put()
                        .addHeader("headtoken", getToken(mContext))
                        .requestBody(new RequestBody() {
                            @Override
                            public MediaType contentType() {
                                return MediaType.parse("application/x-www-form-urlencoded");
                            }

                            @Override
                            public void writeTo(BufferedSink sink) throws IOException {

                            }
                        })
                        .url(url)
                        .param("action", "admin")
                        .build()
                        .execute(new NetStringCallBack(circleMemberManagerInterface, COMMAND_SET_AS_ADMIN));
                break;
        }

    }

    //关于我们类型 http://119.29.10.64/v1/abouttype
    public void getAboutType() {
        LocalLog.d(TAG, "getAboutType() enter");
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlAboutType)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //关于详情http://119.29.10.64/v1/about/2
    public void getAboutTypeId(int id) {
        LocalLog.d(TAG, "getAboutTypeId() enter");
        String url = NetApi.urlAboutType + "/" + String.valueOf(id);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, 0));
    }


    //TODO http://119.29.10.64/v1/usermessages?userid=1&typeid=1
    public void getMessage(int typeid, int page, int pagesize) {
        String url = NetApi.urlMessage + "?userid=" + String.valueOf(getId(mContext))
                + "&typeid=" + String.valueOf(typeid) + "&page=" + page + "&pagesize=" + pagesize;
        LocalLog.d(TAG, "getMessage() enter " + url);
        switch (typeid) {
            case 1:
                OkHttpUtils
                        .get()
                        .addHeader("headtoken", getToken(mContext))
                        .url(url)
                        .build()
                        .execute(new NetStringCallBack(messageInterface, COMMAND_AT_MESSAGE));
                break;
            case 2:
                OkHttpUtils
                        .get()
                        .addHeader("headtoken", getToken(mContext))
                        .url(url)
                        .build()
                        .execute(new NetStringCallBack(messageInterface, COMMAND_CONTENT_MESSAGE));
                break;
            case 3:
                OkHttpUtils
                        .get()
                        .addHeader("headtoken", getToken(mContext))
                        .url(url)
                        .build()
                        .execute(new NetStringCallBack(messageInterface, COMMAND_LIKE_MESSAGE));
                break;
            case 4:
                OkHttpUtils
                        .get()
                        .addHeader("headtoken", getToken(mContext))
                        .url(url)
                        .build()
                        .execute(new NetStringCallBack(messageInterface, COMMAND_SYS_MESSAGE));
                break;
        }

    }


    public void getMessageDetail(int id) {
        LocalLog.d(TAG, "getMessageDetail() enter");
        String url = NetApi.urlMessage + "/detail/?id=" + String.valueOf(id);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //Post message
    public void postMessage(PostMessageParam postMessageParam) {
        LocalLog.d(TAG, "postMessage() enter");
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlMessage)
                .params(postMessageParam.getParam())
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //TODO 好友通讯录
    public void postAddressBook(String addressJson) {
        LocalLog.d(TAG, "postAddressBook() enter");
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlAddressBook)
                .addParams("userid", String.valueOf(getId(mContext)))
                .addParams("mobiles", addressJson)
                .build()
                .execute(new NetStringCallBack(friendAddressInterface, COMMAND_UPDATE_ADDRESS_BOOK));
    }

    //TODO 修改备注名称和设为管理员
    public void markAdminReset() {
        LocalLog.d(TAG, "markAdminReset() enter");
        String url = NetApi.urlCircleMember;
    }

    //
    public void loginOutCircle(LoginOutParam loginOutParam) {
        loginOutParam.setUserid(getId(mContext));
        LocalLog.d(TAG, "loginOutCircle()  " + loginOutParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlCircleMember + "/signOutCirscle")
                .params(loginOutParam.getParams())
                .build()
                .execute(new NetStringCallBack(circleDetailInterface, COMMAND_QUIT_CIRCLE));

    }

    //TODO 加入圈子
    public void joinCircle(JoinCircleParam joinCircleParam) {
        joinCircleParam.setUserid(getId(mContext));
        LocalLog.d(TAG, "joinCircle() enter" + joinCircleParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlCircleMember)
                .params(joinCircleParam.getParams())
                .build()
                .execute(new NetStringCallBack(joinCircleInterface, COMMAND_JOIN_CIRCLE));
    }

    //TODO 退出圈子 和 批量删除成员

    //TODO 加入有密码的圈子
    public void joinCircle(int circleid, String password) {
        LocalLog.d(TAG, "joinCircle() password");
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlCircleMember)
                .addParams("userid", String.valueOf(getId(mContext)))
                .addParams("circleid", String.valueOf(circleid))
                .addParams("password", password)
                .build()
                .execute(new NetStringCallBack(joinCircleInterface, COMMAND_JOIN_CIRCLE));
    }

    //TODO 获取圈子目标列表
    public void getCircleTarget() {
        LocalLog.d(TAG, "getCircleTarget() enter");
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlTarget)
                .build()
                .execute(new NetStringCallBack(uiCreateCircleInterface, COMMAND_GET_CIRCLE_TARGET));
    }

    public void getCircleTargetEdit() {
        LocalLog.d(TAG, "getCircleTargetEdit() enter");
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlTarget)
                .build()
                .execute(new NetStringCallBack(editCircleInterface, COMMAND_GET_CIRCLE_TARGET));
    }

    /*TODO 圈子类型接口*/
    public void getCircleType() {
        LocalLog.d(TAG, "圈子类型列表：getCircleType() enter");
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlCircleType)
                .build()
                .execute(new NetStringCallBack(uiCreateCircleInterface, COMMAND_CIRCLE_TYPE) {
                });
    }


    /* TODO 圈子接口*/
    public void getCircleStepRank(int circleId, int page, int pagesize) {

        String url = NetApi.urlCircleRank + "/?circleid=" + String.valueOf(circleId)
                + "&action=step" + "&page=" + String.valueOf(page) + "&pagesize=" + pagesize;
        LocalLog.d(TAG, "圈子步数排行：getCircleStepRank() enter url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(uiStepAndLoveRankInterface, COMMAND_STEP_RANK));
    }

    public synchronized void getCircleRankMoreDetail(int circleId, int page, int pagesize, final InnerCallBack innerCallBack) {
        String url = NetApi.urlCircleRank + "/?circleid=" + String.valueOf(circleId)
                + "&action=step" + "&page=" + String.valueOf(page) + "&pagesize=" + pagesize;
        LocalLog.d(TAG, "圈子步数排行：getCircleStepRank() enter url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (e != null) {
                            e.printStackTrace();
                            return;
                        }
                        if (o != null) {
                            try {
                                ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                if (innerCallBack != null) {
                                    innerCallBack.innerCallBack(errorCode);
                                }
                            } catch (JsonSyntaxException j) {
                                j.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            if (innerCallBack != null) {
                                StepRankResponse stepRankResponse = new Gson().fromJson(s, StepRankResponse.class);
                                innerCallBack.innerCallBack(stepRankResponse);
                            }
                        } catch (JsonSyntaxException j) {
                            j.printStackTrace();
                        }
                    }
                });
    }

    public void getUserCircleRank(int circleId) {
        String url = NetApi.urlUserCircleRank + String.valueOf(getId(mContext)) + "&circleid=" + String.valueOf(circleId);
        LocalLog.d(TAG, "getUserCircleRank() enter  url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(danCircleInterface, COMMAND_STEP_RANK));
    }

    public void getCircleStepRankDay(int circleId, int page, int pagesize) {
        String url = NetApi.urlCircleRank + "/?circleid=" + String.valueOf(circleId)
                + "&action=step" + "&page=" + String.valueOf(page) + "&pagesize=" + pagesize;
        LocalLog.d(TAG, "圈子步数排行：getCircleStepRankDay() enter url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(circleStepDetailDanInterface, COMMAND_HONOR_DAN_DAY_STEP));
    }

    public void getCircleStepRankWeek(int circleId, int page, int pagesize) {
        String url = NetApi.urlCircleWeekRank + String.valueOf(circleId) + "&page=" + page + "&pagesize=" + pagesize;
        LocalLog.d(TAG, "getCircleStepRankWeek() url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(circleStepDetailDanInterface, COMMAND_HONOR_WEEK_STEP));
    }


    public void getCircleRankNum(int circleId) {
        String url = NetApi.urlStepRankWeekNum + String.valueOf(circleId) + "&userid=" + String.valueOf(getId(mContext));
        LocalLog.d(TAG, "getCircleRankNum() enter  url " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(circleStepDetailDanInterface, COMMAND_HONOR_WEEK_RANK_NUM));
    }

    public void getCircleDetailInCircleDan(int circleId) {
        String url = NetApi.urlCircle + "/" + String.valueOf(circleId) + "?userid=" + String.valueOf(getId(mContext));
        LocalLog.d(TAG, "getUserCircleRankDetail() enter  url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(circleStepDetailDanInterface, COMMAND_GET_CIRCLE_DETAIL));
    }

    public void getUserCircleRankDetail(int circleId) {
        String url = NetApi.urlUserCircleRank + String.valueOf(getId(mContext)) + "&circleid=" + String.valueOf(circleId);
        LocalLog.d(TAG, "getUserCircleRankDetail() enter  url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(circleStepDetailDanInterface, COMMAND_STEP_RANK));
    }

    public void getCircleRechargeRank(int circleId, int page, int pagesize) {
        LocalLog.d(TAG, "充值排行：getCircleRechargeRank() enter");
        String url = NetApi.urlCircleRank + "/?circleid=" + String.valueOf(circleId)
                + "&action=recharge" + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(uiStepAndLoveRankInterface, COMMAND_RECHARGE_RANK));
    }

    public void getMyJoinCircle(int page, int pagesize, String keyWord) {

        String url = NetApi.urlCircle + "?action=join" + "&userid=" + String.valueOf(getId(mContext))
                + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        if (keyWord != null && !"".equals(keyWord)) {
            url += "&keyword=" + keyWord;
        }
        LocalLog.d(TAG, "我加入的圈子：getMyJoin() enter" + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(myJoinCircleInterface, COMMAND_GET_MY_JOIN_CIRCLE));
    }


    public void reflashMyCreateCircle(int page, int pagesize) {
        LocalLog.d(TAG, " 我创建的圈子：reflashMyCreateCircle() enter");
        String url = NetApi.urlCircle + "?action=create" + "&userid=" + String.valueOf(engine.getId(mContext))
                + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(reflashMyCircleInterface, COMMAND_REFLASH_CIRCLE));
    }

    public void getMyCreateCirlce(int page, int pagesize, String keyWord) {

        String url = NetApi.urlCircle + "?action=create" + "&userid=" + String.valueOf(engine.getId(mContext))
                + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        if (keyWord != null && !"".equals(keyWord)) {
            url += "&keyword=" + keyWord;
        }
        LocalLog.d(TAG, "getMyCreateCirlce() url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(myCreatCircleInterface, COMMAND_GET_MY_CREATE_CIRCLE));
    }

    //TODO 获取我的圈子
    public void getMyHotCircle(int page, int pagesize) {
        LocalLog.d(TAG, "获取我的圈子getMyHotCircle()");
        String url = NetApi.urlCircle + "?action=my" + "&userid=" + String.valueOf(engine.getId(mContext))
                + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(uiHotCircleInterface, COMMAND_GET_MY_HOT));
    }


    public void getAllMyCircle(int page, int pagesize) {
        String url = NetApi.urlCircle + "?action=my" + "&userid=" + String.valueOf(engine.getId(mContext))
                + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        LocalLog.d(TAG, "getAllMyCircle() url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(danCircleInterface, COMMAND_GET_MY_HOT));
    }

    public void getLiveList(final InnerCallBack innerCallBack, int page, int pagesize) {
        LocalLog.d(TAG, "getLiveList() enter");
        String url = NetApi.urlLive + "?&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        try {
                            ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                            if (innerCallBack != null) {
                                innerCallBack.innerCallBack(errorCode);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            LocalLog.d(TAG, "s =" + s);
                            LiveResponse liveResponse = new Gson().fromJson(s, LiveResponse.class);
                            if (innerCallBack != null) {
                                innerCallBack.innerCallBack(liveResponse);
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void getMyCode(final InnerCallBack innerCallBack) {
        String url = NetApi.urlMyCode + "?userid=" + String.valueOf(getId(mContext));
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (innerCallBack != null) {
                            if (o == null) {
                                return;
                            }
                            try {
                                ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                innerCallBack.innerCallBack(errorCode);
                            } catch (JsonSyntaxException j) {
                                j.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (innerCallBack != null) {
                            try {
                                InviteCodeResponse inviteCodeResponse = new Gson().fromJson(s, InviteCodeResponse.class);
                                innerCallBack.innerCallBack(inviteCodeResponse);
                            } catch (JsonSyntaxException e) {

                            }
                        }
                    }
                });
    }

    public void getCircleChoice(int page, int pagesize) {

        String url = NetApi.urlCircle + "?action=choice" + "&userid=" + String.valueOf(getId(mContext))
                + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);

        LocalLog.d(TAG, "精选圈子：getCircleChoice() enter" + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(uiHotCircleInterface, COMMAND_GET_CHOICE_CIRCLE));
    }

    public void getMoreCircle(int page, int pagesize, String keyWord) {
        String url = NetApi.urlCircle + "?action=choice" + "&userid=" + String.valueOf(getId(mContext))
                + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        if (keyWord != null && !"".equals(keyWord)) {
            url += "&keyword=" + keyWord;
        }
        LocalLog.d(TAG, "精选圈子：getMoreCircle() enter" + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(searchCircleInterface, COMMAND_GET_CHOICE_CIRCLE));
    }

    public void createCircle(CreateCircleBodyParam createCircleBodyParam) {
        LocalLog.d(TAG, "  创建圈子createCircle() enter " + createCircleBodyParam.paramString());
        if (TextUtils.isEmpty(createCircleBodyParam.paramString())) {
            return;
        }
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlCircle)
                .params(createCircleBodyParam.getParams())
                .build()
                .execute(new NetStringCallBack(uiCreateCircleInterface, COMMAND_CREATE_CIRCLE));
    }

    public void getCircleDetail(int circleId) {
        LocalLog.d(TAG, " 获取圈子详情 getCircleDetail() ");
        String url = NetApi.urlCircle + "/" + String.valueOf(circleId) + "?userid=" + String.valueOf(getId(mContext));
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(circleDetailInterface, COMMAND_GET_CIRCLE_DETAIL));
    }

    public void getCircleDetailView(final TextView myName, final ImageView imageView, final TextView stepNum, final int circleId) {
        LocalLog.d(TAG, "getCircleDetailCircle() cirCleId =  " + circleId);
        String url = NetApi.urlCircle + "/" + String.valueOf(circleId) + "?userid=" + String.valueOf(getId(mContext));
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            CircleDetailResponse circleDetailResponse = new Gson().fromJson(s, CircleDetailResponse.class);
                            if (myName != null) {
                                if (circleDetailResponse.getData() == null) {
                                    return;
                                }
                                myName.setText(circleDetailResponse.getData().getName());
                            }
                            if (imageView != null) {
                                Presenter.getInstance(mContext).getImage(imageView, circleDetailResponse.getData().getLogo());
                            }
                            if (stepNum != null) {
                                stepNum.setText(String.valueOf(circleDetailResponse.getData().getTarget()));
                            }
                        } catch (JsonSyntaxException exception) {
                            exception.printStackTrace();
                        }
                    }
                });

    }

    public void getCircleIsRedPackage(int circleId) {
        LocalLog.d(TAG, " 获取圈子详情 getCircleIsRedPackage() ");
        String url = NetApi.urlCircle + "/" + String.valueOf(circleId) + "?userid=" + String.valueOf(getId(mContext));
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(queryRedPkgInterface, COMMAND_GET_CIRCLE_DETAIL));
    }

    public void putCircle(CreateCircleBodyParam createCircleBodyParam, int circleId) {
        String url = NetApi.urlCircle + "/" + String.valueOf(circleId);
        LocalLog.d(TAG, "编辑圈子 putCircle() url = " + url + " ,createCircleBodyParam = " + createCircleBodyParam.toString());
        OkHttpUtils
                .put()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .requestBody(new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return MediaType.parse("application/x-www-form-urlencoded");
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {

                    }
                })
                .params(createCircleBodyParam.getParams())
                .build()
                .execute(new NetStringCallBack(editCircleInterface, COMMAND_EDIT_CIRCLE));
    }


    public void postCircleRedPkg(PostCircleRedPkgParam postCircleRedPkgParam) {
        String url = NetApi.urlCircle + "/sendRedBag";
        postCircleRedPkgParam.setUserid(getId(mContext));
        LocalLog.d(TAG, "postCircleRedPkg() enter url = " + url + " postCircleRedPkgParam = " + postCircleRedPkgParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .params(postCircleRedPkgParam.getParams())
                .build()
                .execute(new NetStringCallBack(circleDetailInterface, COMMAND_POST_REV_RED_PKG));
    }

    public void deleteCircle(int circleId) {
        String url = NetApi.urlCircle + "/" + String.valueOf(circleId) + "&userid=" + String.valueOf(getId(mContext));
        LocalLog.d(TAG, "删除圈子：deleteCircle() enter");
        OkHttpUtils
                .delete()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(circleDetailInterface, COMMAND_DELETE_CIRCLE));
    }

    //获取用户登录记录，暂时无需实现
    public void getUserRecord(int userId) {
        LocalLog.d(TAG, "getUserRecord() enter");
        String url = NetApi.urlUserRecord + String.valueOf(userId);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //psot用户登陆记录
    public void postUserRecord(int id, double longitude, double latitude) {
        UserRecordParam userRecordParam = new UserRecordParam();
        userRecordParam
                .setId(id)
                .setLongitude(longitude)
                .setLatitude(latitude);

        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlUserRecordPost)
                .params(userRecordParam.getParam())
                .build()
                .execute(new NetStringCallBack(null, -1));

    }

    //TODO 请求方式get，地址：http://pb.com/v1/userstep/1 参数：用户id
    public void getUserStep() {
        String url = NetApi.urlUserStep + "/" + getId(mContext);
        LocalLog.d(TAG, "getUserStep() enter url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    /*TODO 请求方式：POST
    地址：http://119.29.10.64/v1/UserStep/updateStep*/
    public void postUserStep(int step_num) {
        //改变步数
//        int Min = 18000;
//        int Max = 25000;
//        step_num = Min + (int)(Math.random() * ((Max - Min) + 1));
        PostUserStepParam postUserStepParam = new PostUserStepParam();
        postUserStepParam.setUserid(String.valueOf(getId(mContext))).setStep_number(String.valueOf(step_num));
        String url = NetApi.urlUserStep + "/updateStep";
        LocalLog.d(TAG, "putUserStep() enter url =" + url + "\n" + postUserStepParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .params(postUserStepParam.getParams())
                .url(url)
                .build()
                .execute(new NetStringCallBack(homePageInterface, COMMAND_POST_USER_STEP));
    }

    public void getSponsorRedPkg() {
        String[] location = FlagPreference.getLocation(mContext);
        if (location != null && location.length >= 2) {
            if (TextUtils.isEmpty(location[0]) || TextUtils.isEmpty(location[1])) {
                PaoToastUtils.showLongToast(mContext, "开启位置获取红包!");
                return;
            }
        }
        String url = NetApi.urlRedpacket + "?userid=" + String.valueOf(getId(mContext)) +
                "&latitude=" + location[0] + "&longitude=" + location[1];
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(homePageInterface, COMMAND_SPONSOR_PKG));
    }

    //TODO 商户详情
    public void businessDetail(int businessid, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "businessDetail() enter");
        String url = NetApi.urlBusiness + "/" + String.valueOf(businessid);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        if (innerCallBack != null) {
                            ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                            innerCallBack.innerCallBack(errorCode);
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (innerCallBack != null) {
                            LocalLog.d(TAG, "s = " + s);
                            try {
                                SponsorDetailResponse sponsorDetailResponse = new Gson().fromJson(s, SponsorDetailResponse.class);
                                innerCallBack.innerCallBack(sponsorDetailResponse);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    //TODO 领取商户红包
    public void postRedPkgRec(RedPkgRecParam pkgRecParam, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "postRedPkgRec() enter");
        String[] location = FlagPreference.getLocation(mContext);
        if (location != null && location.length >= 2) {
            if (TextUtils.isEmpty(location[0]) || TextUtils.isEmpty(location[1])) {
                LocalLog.d(TAG, "开启位置获取红包!");
                return;
            }
        }
        pkgRecParam.setUserid(getId(mContext)).setLatitude(location[0]).setLongitude(location[1]);
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlRevRedPkg)
                .params(pkgRecParam.getParams())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (innerCallBack != null) {
                            if (o == null) {
                                return;
                            }
                            try {
                                ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                if (innerCallBack != null) {
                                    innerCallBack.innerCallBack(errorCode);
                                }
                            } catch (JsonSyntaxException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (innerCallBack != null) {
                            LocalLog.d(TAG, "s = " + s);
                            try {
                                RecRedPkgResponse recRedPkgResponse = new Gson().fromJson(s, RecRedPkgResponse.class);
                                innerCallBack.innerCallBack(recRedPkgResponse);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    //TODO 创建商户
    public void AddBusiness(AddBusinessParam addBusinessParam, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "AddBusiness() enter" + addBusinessParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlAddBusiness)
                .params(addBusinessParam.getParams())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        if (innerCallBack != null) {
                            ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                            innerCallBack.innerCallBack(errorCode);
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (innerCallBack != null) {
                            LocalLog.d(TAG, "s = " + s);
                            try {
                                AddBusinessResponse response = new Gson().fromJson(s, AddBusinessResponse.class);
                                innerCallBack.innerCallBack(response);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    //TODO  获取用户商户列表
    public void getUserBusiness(GetUserBusinessParam param, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "getUserBusiness() enter");
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlGetUserBusiness)
                .params(param.getParams())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        if (innerCallBack != null) {
                            try {
                                ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                innerCallBack.innerCallBack(errorCode);
                            } catch (JsonSyntaxException j) {
                                j.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (innerCallBack != null) {
                            LocalLog.d(TAG, "s = " + s);
                            try {
                                GetUserBusinessResponse getUserBusinessResponse = new Gson().fromJson(s, GetUserBusinessResponse.class);
                                innerCallBack.innerCallBack(getUserBusinessResponse);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    //TODO  删除商户
    public void deleteBusiness(int businessId, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "deleteBusiness() enter");
        String url = NetApi.urlBusiness + "/" + String.valueOf(businessId);
        OkHttpUtils
                .delete()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        if (innerCallBack != null) {
                            ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                            innerCallBack.innerCallBack(errorCode);
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (innerCallBack != null) {
                            LocalLog.d(TAG, "s = " + s);
                            try {
                                NormalResponse response = new Gson().fromJson(s, NormalResponse.class);
                                innerCallBack.innerCallBack(response);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    //TODO  更新商户信息
    public void updateBusiness(AddBusinessParam addBusinessParam, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "updateBusiness() enter:" + addBusinessParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlUpdateBusiness)
                .params(addBusinessParam.getParams())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        if (innerCallBack != null) {
                            LocalLog.d("---------------", o.toString());
                            ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                            innerCallBack.innerCallBack(errorCode);
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (innerCallBack != null) {
                            LocalLog.d(TAG, "s = " + s);
                            try {
                                UpdateBusinessResponse response = new Gson().fromJson(s, UpdateBusinessResponse.class);
                                innerCallBack.innerCallBack(response);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }


    //TODO   设置默认商户
    public void setDefaultBusiness(int businessId, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "setDefaultBusiness() enter");
        Map<String, String> map = new HashMap<>();
        map.put("businessid", businessId + "");
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlSetDefaultBusiness)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (innerCallBack != null) {
                            if (o == null) {
                                return;
                            }
                            try {
                                ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                innerCallBack.innerCallBack(errorCode);
                            } catch (Exception err) {
                                err.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (innerCallBack != null) {
                            LocalLog.d(TAG, "s = " + s);
                            try {
                                innerCallBack.innerCallBack(s);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    //TODO 获取当前步数
    public boolean getCurrentStep(final InnerCallBack innerCallBack) {
        if (getId(mContext) == -1 || getNetworkPolicy() == NetworkPolicy.OFFLINE) {
            LocalLog.d(TAG, "当前无用户登录");
            return false;
        }
        String url = NetApi.urlCurrentStep + String.valueOf(getId(mContext));
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (innerCallBack != null) {
                            if (o != null) {
                                ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                innerCallBack.innerCallBack(errorCode);
                            }
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (innerCallBack != null) {
                            try {
                                CurrentStepResponse currentStepResponse = new Gson().fromJson(s, CurrentStepResponse.class);
                                innerCallBack.innerCallBack(currentStepResponse);
                            } catch (JsonSyntaxException e) {

                            }
                        }
                    }
                });
        return true;
    }

    //TODO
    public void getHomePageIncome(String action, int page, int pageSize) {
        String url = NetApi.urlIncome + "?userid=" + String.valueOf(getId(mContext)) + "&action=" + action + "&page=" + String.valueOf(page)
                + "&pagesize=" + String.valueOf(pageSize);
        LocalLog.d(TAG, "getIncome() enter url = " + url);
        if ("all".equals(action)) {
            OkHttpUtils
                    .get()
                    .addHeader("headtoken", getToken(mContext))
                    .url(url)
                    .build()
                    .execute(new NetStringCallBack(homePageInterface, COMMAND_INCOME_ALL));
        } else if ("today".equals(action)) {
            OkHttpUtils
                    .get()
                    .addHeader("headtoken", getToken(mContext))
                    .url(url)
                    .build()
                    .execute(new NetStringCallBack(homePageInterface, COMMAND_INCOME_TODAY));
        }
    }

    //TODO http://119.29.10.64/v1/income?userid=1&action=yesterday
    public void getIncome(String action, int page, int pageSize) {
        String url = NetApi.urlIncome + "?userid=" + String.valueOf(getId(mContext)) + "&action=" + action + "&page=" + String.valueOf(page)
                + "&pagesize=" + String.valueOf(pageSize);
        LocalLog.d(TAG, "getIncome() enter url = " + url);
        if ("month".equals(action)) {
            OkHttpUtils
                    .get()
                    .addHeader("headtoken", getToken(mContext))
                    .url(url)
                    .build()
                    .execute(new NetStringCallBack(userIncomInterface, COMMAND_INCOME_MONTH));
        } else if ("yesterday".equals(action)) {
            OkHttpUtils
                    .get()
                    .addHeader("headtoken", getToken(mContext))
                    .url(url)
                    .build()
                    .execute(new NetStringCallBack(userIncomInterface, COMMAND_INCOME_YESTERDAY));
        } else if ("all".equals(action)) {
            OkHttpUtils
                    .get()
                    .addHeader("headtoken", getToken(mContext))
                    .url(url)
                    .build()
                    .execute(new NetStringCallBack(userIncomInterface, COMMAND_INCOME_ALL));
        } else if ("today".equals(action)) {
            OkHttpUtils
                    .get()
                    .addHeader("headtoken", getToken(mContext))
                    .url(url)
                    .build()
                    .execute(new NetStringCallBack(userIncomInterface, COMMAND_INCOME_TODAY));
        }
    }

    //请求方式post，地址：http://pb.com/v1/income，参数：userid（用户id）、typeid（收益类型）、circleid（圈子id）、amount（收益金额）
    public void putIncome(PostIncomeParam param) {
        String url;
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlIncome)
                .params(param.getParam())
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //用户认证，请求方式：post，地址：http://api.runmoneyin.com/v1/userauthentication，参数：id（用户id）、idcard（身份证号）、realname（真实名字）
    public void postAuthentication(AuthenticationParam authenticationParam) {
        LocalLog.d(TAG, "postAuthentication() enter");
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlAuthentication)
                .params(authenticationParam.getParam())
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //获取用户认证状态，请求方式：get，地址：http://api.runmoneyin.com/v1/userauthentication/5（用户id）
    public void getAuthenticationState(int id) {
        LocalLog.d(TAG, "getAuthenticationState() enter");
        String url = NetApi.getUrlAuthenticationState + String.valueOf(id);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, 0));
    }

    //用户收益类型相关接口，地址：http://api.runmoneyin.com/v1/incometype/?id=1
    public void getIncomeType(int id) {
        LocalLog.d(TAG, "getIncomeType() enter");
        String url = NetApi.urlIncomeType + String.valueOf(id);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //TODO 获取用户步币详细信息，请求方式：get，地址：http://119.29.10.64/v1/usercredit?userid=1
    public void getUserCredit(int page, int pagesize) {
        String url = NetApi.urlCredit + "?userid=" + String.valueOf(getId(mContext)) + "&page=" + String.valueOf(page)
                + "&pagesize=" + String.valueOf(pagesize);
        LocalLog.d(TAG, "usercredit() enter url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(stepDollarDetailInterface, COMMAND_GET_USER_STEP_DOLLAR_DETAIL));
    }

    public void getUserDollarStep() {
        String url = NetApi.urlUser + String.valueOf(getId(mContext));
        LocalLog.d(TAG, "getUserDollarStep() url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(stepDollarDetailInterface, COMMAND_GET_USER_INFO));
    }

    public void getUserPackageMoney() {
        String url = NetApi.urlUser + String.valueOf(getId(mContext));
        LocalLog.d(TAG, "getUserDollarStep() url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(userIncomInterface, COMMAND_GET_USER_INFO));
    }

    public void getVerifyIdentify(final Activity activity, final OnIdentifyLis onIdentifyLis) {
        Presenter.getInstance(activity).getPaoBuSimple(NetApi.GET_PERSON_IDENTIFY_STATE, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (activity == null) return;
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    String status = jsonObj.getString("status");
                    if (status.equals("0")) {
                        onIdentifyLis.onUnidentify();
                        return;
                    } else {
                        onIdentifyLis.onIdentifed();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    onIdentifyLis.onGetIdentifyStatusError();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (activity == null) return;
                if (errorBean != null) {
                    PaoToastUtils.showShortToast(activity, errorBean.getMessage());
                }
                onIdentifyLis.onGetIdentifyStatusError();
            }
        });
    }


    //TODO 关注接口
    public void getFollows(String action, int page, int pagesize, String keyWord, final InnerCallBack innerCallBack) {
        String url = NetApi.urlUserFollow + "?action=" + action + "&userid=" + String.valueOf(getId(mContext))
                + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        if (!TextUtils.isEmpty(keyWord)) {
            url += url + "&keyword=" + keyWord;
        }
        LocalLog.d(TAG, "getFollows() enter url = " + url);
        switch (action) {
            case "my":
                LocalLog.d(TAG, "获取我关注的");
                OkHttpUtils
                        .get()
                        .addHeader("headtoken", getToken(mContext))
                        .url(url)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int i, Object o) {
                                if (o == null) {
                                    return;
                                }
                                try {
                                    ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                    if (innerCallBack != null) {
                                        innerCallBack.innerCallBack(errorCode);
                                    }
                                } catch (JsonSyntaxException e1) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onResponse(String s, int i) {
                                try {
                                    UserIdFollowResponse userIdFollowResponse = new Gson().fromJson(s, UserIdFollowResponse.class);
                                    if (innerCallBack != null) {
                                        innerCallBack.innerCallBack(userIdFollowResponse);
                                    }
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
            case "me":
                LocalLog.d(TAG, "获取关注我的");
                OkHttpUtils
                        .get()
                        .addHeader("headtoken", getToken(mContext))
                        .url(url)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int i, Object o) {
                                try {
                                    if (o == null) {
                                        return;
                                    }
                                    ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                    if (innerCallBack != null) {
                                        innerCallBack.innerCallBack(errorCode);
                                    }
                                } catch (JsonSyntaxException e1) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onResponse(String s, int i) {
                                try {
                                    FollowUserResponse followUserResponse = new Gson().fromJson(s, FollowUserResponse.class);
                                    if (innerCallBack != null) {
                                        innerCallBack.innerCallBack(followUserResponse);
                                    }
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
            case "mutual":
                LocalLog.d(TAG, "获取互相关注");
                OkHttpUtils
                        .get()
                        .addHeader("headtoken", getToken(mContext))
                        .url(url)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int i, Object o) {
                                if (o == null) {
                                    return;
                                }
                                try {
                                    ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                    if (innerCallBack != null) {
                                        innerCallBack.innerCallBack(errorCode);
                                    }
                                } catch (JsonSyntaxException e1) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onResponse(String s, int i) {
                                try {
                                    UserFollowOtOResponse followOtOResponse = new Gson().fromJson(s, UserFollowOtOResponse.class);
                                    if (innerCallBack != null) {
                                        innerCallBack.innerCallBack(followOtOResponse);
                                    }
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
        }
    }

    //TODO 添加关注/去关注
    public void postAddUserFollow(QueryFollowStateParam queryFollowStateParam) {
        LocalLog.d(TAG, "postAddUserFollow()");
        queryFollowStateParam.setUserid(getId(mContext));
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlUserFollow)
                .params(queryFollowStateParam.getParams())
                .build()
                .execute(new NetStringCallBack(addDeleteFollowInterface, COMMAND_ADD_DELETE_FOLLOW));
    }

    public void postAddUserFollow(final InnerCallBack innerCallBack, final int followid) {
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlUserFollow)
                .addParams("userid", String.valueOf(getId(mContext)))
                .addParams("followid", String.valueOf(followid))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        AddDeleteFollowResponse addDeleteFollowResponse = new Gson().fromJson(s, AddDeleteFollowResponse.class);
                        if (innerCallBack != null) {
                            innerCallBack.innerCallBack(addDeleteFollowResponse);
                        }
                    }
                });
    }

    public void postUserStatus(final Button button, int followid) {
        LocalLog.d(TAG, "followid =   " + followid);
        if (followid == getId(mContext)) {
            PaoToastUtils.showLongToast(mContext, "不能自己关注自己");
            return;
        }
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlUserFollow)
                .addParams("userid", String.valueOf(getId(mContext)))
                .addParams("followid", String.valueOf(followid))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        AddDeleteFollowResponse addDeleteFollowResponse = new Gson().fromJson(s, AddDeleteFollowResponse.class);
                        if (button != null) {
                            if (addDeleteFollowResponse.getMessage().equals("取消关注成功")) {
                                button.setText("关注");
                                button.setTextColor(ContextCompat.getColor(mContext, R.color.color_6c71c4));
                                button.setBackground(ContextCompat.getDrawable(mContext, R.drawable.has_fllow_nearby));
                            } else {
                                button.setText("已关注");
                                button.setTextColor(ContextCompat.getColor(mContext, R.color.color_646464));
                                button.setBackground(ContextCompat.getDrawable(mContext, R.drawable.has_not_fllow_nearby));
                            }
                        }
                    }
                });
    }

    //TODO 获取关注状态
    public void postQueryFollowState(QueryFollowStateParam queryFollowStateParam) {
        queryFollowStateParam.setUserid(getId(mContext));
        LocalLog.d(TAG, "postQueryFollowState() enter " + queryFollowStateParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlUserFollow + "/followStatus")
                .params(queryFollowStateParam.getParams())
                .build()
                .execute(new NetStringCallBack(userHomeInterface, COMMAND_QUERY_FOLLOW_STATE));
    }

    //TODO 获取个人和好友步数排行当日
    //http://119.29.10.64/v1/UserFriends/?userid=1&action=step
    public void getFriendHonor(int page, int pagesize) {
        String url = NetApi.urlUserFriends + "/?userid=" + String.valueOf(getId(mContext)) +
                "&action=step" + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        LocalLog.d(TAG, "getFriendHonor() enter url" + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(friendHonorInterface, COMMAND_FRIEND_HONOR));
    }

    public void getFriendHonorDetail(int page, int pagesize) {
        String url = NetApi.urlUserFriends + "/?userid=" + String.valueOf(getId(mContext)) +
                "&action=step" + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        LocalLog.d(TAG, "getFriendHonorDetail() enter url" + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(friendHonorDetailInterface, COMMAND_FRIEND_HONOR));
    }

    public void getFriendWeekHonor(int page, int pagesize) {
        String url = NetApi.urlUserFriends + "/getUserSort?userid=" + String.valueOf(getId(mContext)) + "&page=" + String.valueOf(page)
                + "&pagesize=" + String.valueOf(pagesize);
        LocalLog.d(TAG, "getFriendWeekHonor() enter url   " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(friendHonorDetailInterface, COMMAND_FRIEND_HONOR_WEEK));
    }

    public void getImage(String fileUrl, final ImageView imageView) {
        LocalLog.d(TAG, "getImage() local");
        Picasso picasso = Picasso.with(mContext);
        LocalLog.d(TAG, "networkPolicy = " + networkPolicy.name() + " -> " + networkPolicy.toString());
        picasso.load(new File(fileUrl)).config(Bitmap.Config.RGB_565).into(imageView);
    }

    public void getOriginImage(String fileUrl, final ImageView imageView) {
        LocalLog.d(TAG, "getImage() local");
        Picasso picasso = Picasso.with(mContext);
        LocalLog.d(TAG, "networkPolicy = " + networkPolicy.name() + " -> " + networkPolicy.toString());
        picasso.load(new File(fileUrl)).config(Bitmap.Config.RGB_565).into(imageView);
    }

    public void getAdImage(ImageView imageView, String keepUrl) {
        LocalLog.d(TAG, "getAdImage() enter");
        File cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = mContext.getExternalCacheDir();
            LocalLog.d(TAG, "getExternalCachdir() = " + cachePath);
        } else {
            cachePath = mContext.getCacheDir();
            LocalLog.d(TAG, "getCacheDir() = " + cachePath);
        }
        int index = keepUrl.lastIndexOf(".");
        if (index < keepUrl.length()) {
            String fileStyle = keepUrl.substring(index, keepUrl.length());
            LocalLog.d(TAG, "fileStyle =" + fileStyle);
            String adImgeName = cachePath + "ad" + fileStyle;
            File adImage = new File(adImgeName);
            if (adImage.exists()) {
                LocalLog.d(TAG, "使用本地图片");
                picasso.load(adImage).config(Bitmap.Config.RGB_565).into(imageView);
            } else {
                LocalLog.d(TAG, "###重新下载广告图片");
                picasso.load(keepUrl).config(Bitmap.Config.RGB_565).transform(new PiccsoTransformation(imageView, keepUrl, true))
                        .fit().centerCrop().into(imageView);
            }
        } else {
            LocalLog.d(TAG, "路径不合法！");
        }

    }

    public void downLoadAdImage(String urlImage, ImageView view, int placeholderImageId, int errorId) {
        LocalLog.d(TAG, "downLoadAdImage() enter");
        picasso.load(urlImage).config(Bitmap.Config.RGB_565).transform(new PiccsoTransformation(view, urlImage, true))
                .fit().centerCrop().into(view);
    }

    public void getImage(String fileUrl, final ImageView imageView, int targetWidth, int targetHeight) {
        LocalLog.d(TAG, "getImage() local");
        Picasso picasso = Picasso.with(mContext);
        LocalLog.d(TAG, "networkPolicy = " + networkPolicy.name() + " -> " + networkPolicy.toString());
        if (imageView != null)
            picasso.load(new File(fileUrl)).config(Bitmap.Config.RGB_565).resize(targetWidth, targetHeight).into(imageView);
    }

    //网络图片获取接口
    public void getImage(ImageView imageView, String urlImage) {
        LocalLog.d(TAG, "getImage() enter");
        Picasso picasso = Picasso.with(mContext);
        //picasso.setIndicatorsEnabled(true);
        //picasso.setLoggingEnabled(true);
        LocalLog.d(TAG, "networkPolicy = " + networkPolicy.name() + " -> " + networkPolicy.toString());
        picasso.load(urlImage).config(Bitmap.Config.RGB_565).transform(new PiccsoTransformation(imageView)).into(imageView);

        //Picasso.with(mContext).load(urlImage).into(imageView);
/*        OkHttpUtils
                .get()
                .url(urlImage)
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int i) {
                        if(imageView != null){
                        imageView.setImageBitmap(bitmap);}
                    }
                });*/
    }

    public void getPlaceErrorImage(ImageView view, String urlImage, int placeholderImageId, int errorId) {
        if (TextUtils.isEmpty(urlImage)) {
            picasso.load(placeholderImageId).config(Bitmap.Config.RGB_565).transform(new PiccsoTransformation(view)).into(view);
            return;
        }

        picasso.load(urlImage).config(Bitmap.Config.RGB_565).transform(new PiccsoTransformation(view))
                .placeholder(ContextCompat.getDrawable(mContext, placeholderImageId))
                .error(ContextCompat.getDrawable(mContext, errorId)).fit().centerCrop().into(view);
    }

    public void getIcoBitMap(final ImageView imageView, String urlImage, int targetWidth, int targetHeight) {
        Picasso picasso = Picasso.with(mContext);

        picasso.load(urlImage).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                LocalLog.d(TAG, "onBitmapLoaded() enter ");

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                LocalLog.d(TAG, "onBitmapFailed() enter ");

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                LocalLog.d(TAG, "onPrepareLoad() enter " + placeHolderDrawable== null ? "null" :"not null" );
                if(placeHolderDrawable != null && imageView !=null){
                    imageView.setImageDrawable(placeHolderDrawable);
                }
            }
        });
    }

    //网络图片获取接口
    public void getImage(ImageView imageView, String urlImage, int targetWidth, int targetHeight) {
        LocalLog.d(TAG, "getImage() enter");
        Picasso picasso = Picasso.with(mContext);
        //picasso.setIndicatorsEnabled(true);
        //picasso.setLoggingEnabled(true);
        LocalLog.d(TAG, "networkPolicy = " + networkPolicy.name() + " -> " + networkPolicy.toString());
        if (networkPolicy == NetworkPolicy.OFFLINE) {
            picasso.load(urlImage).config(Bitmap.Config.RGB_565).transform(new PiccsoTransformation(imageView)).networkPolicy(networkPolicy).into(imageView);
        } else {
            picasso.load(urlImage).config(Bitmap.Config.RGB_565).transform(new PiccsoTransformation(imageView)).resize(targetWidth, targetHeight).into(imageView);
        }
    }

    //TODO sponsor vip
    public void postVipSponsorNo(VipPostParam vipPostParam, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "vipPostParam " + vipPostParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlSponsorVip)
                .params(vipPostParam.getParams())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        try {
                            ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                            if (innerCallBack != null) {
                                innerCallBack.innerCallBack(errorCode);
                            }
                        } catch (JsonSyntaxException j) {
                            j.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            CvipNoResponse cvipNoResponse = new Gson().fromJson(s, CvipNoResponse.class);
                            if (innerCallBack != null) {
                                innerCallBack.innerCallBack(cvipNoResponse);
                            }
                        } catch (JsonSyntaxException j) {
                            j.printStackTrace();
                        }
                    }
                });
    }

    //TODO VIP  op
    public void postVipNo(VipPostParam vipPostParam, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "vipPostParam " + vipPostParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlVip)
                .params(vipPostParam.getParams())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        try {
                            ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                            if (innerCallBack != null) {
                                innerCallBack.innerCallBack(errorCode);
                            }
                        } catch (JsonSyntaxException j) {
                            j.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            VipNoResponse vipNoResponse = new Gson().fromJson(s, VipNoResponse.class);
                            if (innerCallBack != null) {
                                innerCallBack.innerCallBack(vipNoResponse);
                            }
                        } catch (JsonSyntaxException j) {
                            j.printStackTrace();
                        }
                    }
                });
    }

    //TODO 圈子订单 WX
    public void postWxPayOrder(PayOrderParam wxPayOrderParam) {
        LocalLog.d(TAG, wxPayOrderParam.paramString());
        switch (wxPayOrderParam.getPayment_type()) {
            case "wx":
                LocalLog.d(TAG, "微信支付");
                OkHttpUtils
                        .post()
                        .addHeader("headtoken", getToken(mContext))
                        .url(NetApi.urlPayOrder)
                        .params(wxPayOrderParam.getParams())
                        .build()
                        .execute(new NetStringCallBack(payInterface, COMMAND_CIRCLE_ORDER_POST_WX));
                break;
            case "sevenpay":
                LocalLog.d(TAG, "支付宝");
                OkHttpUtils
                        .post()
                        .addHeader("headtoken", getToken(mContext))
                        .url(NetApi.urlPayOrder)
                        .params(wxPayOrderParam.getParams())
                        .build()
                        .execute(new NetStringCallBack(payInterface, COMMAND_CIRCLE_ORDER_POST_ALI));
                break;
            case "wallet":
                LocalLog.d(TAG, "钱包支付");
                OkHttpUtils
                        .post()
                        .addHeader("headtoken", getToken(mContext))
                        .url(NetApi.urlPayOrder)
                        .params(wxPayOrderParam.getParams())
                        .build()
                        .execute(new NetStringCallBack(payInterface, COMMAND_CIRCLE_ORDER_POST_WALLET));
                break;
            case "yspay":
                LocalLog.d(TAG, "云闪付");
                OkHttpUtils
                        .post()
                        .addHeader("headtoken", getToken(mContext))
                        .url(NetApi.urlPayOrder)
                        .params(wxPayOrderParam.getParams())
                        .build()
                        .execute(new NetStringCallBack(payInterface, COMMAND_CIRCLE_ORDER_POST_YSPAY));
                break;
        }

    }

    //TODO 获取我的订单 http://119.29.10.64/v1/Pay?userid=1
    public void getMyPayOrders() {
        String url = NetApi.urlPay + "?userid=" + String.valueOf(getId(mContext));
        LocalLog.d(TAG, "getMyPayOrders() " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(allPayOrderInterface, COMMAND_ALL_PAY_ORDER));
    }

    //TODO 订单详情 http://119.29.10.64/v1/Pay/orderQuery?order_no=1517535057897&payment_type=wx
    public void getWxPayResultByOrderNo(String orderTradeNo, String payment_type) {
        String url = NetApi.urlPayResultOrderNo + "?order_no=" + orderTradeNo + "&payment_type=" + payment_type;
        LocalLog.d(TAG, "getWxPayResultByOrderNo() url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(payWxResultQueryInterface, COMMAND_PAY_RESULT_QUERY_WX));

    }

    //TODO 云闪付查单
    public void postYsPayResultByOrderNo(String ysOrderNo, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "云闪付单号 +" + ysOrderNo);
        OkHttpUtils
                .post()
                .addParams("order_no", ysOrderNo)
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlYsPayResultOrderNo)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (e != null) {
                            e.printStackTrace();
                            return;
                        }
                        if (o != null) {
                            try {
                                ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                if (innerCallBack != null) {
                                    innerCallBack.innerCallBack(errorCode);
                                }
                            } catch (JsonSyntaxException j) {
                                j.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            YsPayResultResponse ysPayResultResponse = new Gson().fromJson(s, YsPayResultResponse.class);
                            if (innerCallBack != null) {
                                innerCallBack.innerCallBack(ysPayResultResponse);
                            }
                        } catch (JsonSyntaxException j) {
                            j.printStackTrace();
                        }
                    }
                });
    }

    //TODO 三方登录
    public void PostThirdPartyLogin(ThirdPartyLoginParam thirdPartyLoginParam) {
        LocalLog.d(TAG, thirdPartyLoginParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(urlThirdLogin)
                .params(thirdPartyLoginParam.getParam())
                .build()
                .execute(new NetStringCallBack(loginCallBackInterface, COMMAND_LOGIN_BY_THIRD));
    }

    //TODO 任务接口
    public void taskRelease(TaskReleaseParam taskReleaseParam) {
        LocalLog.d(TAG, taskReleaseParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlTask)
                .params(taskReleaseParam.getParams())
                .build()
                .execute(new NetStringCallBack(taskReleaseInterface, COMMAND_TASK_RELEASE));

    }


    public void getTaskDetail(int taskId) {
        String url = NetApi.urlTask + "/" + String.valueOf(taskId);
        LocalLog.d(TAG, "getTaskDetail() url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(myReleaseTaskDetailInterface, COMMAND_GET_MY_RELEASE_TASK_DETAIL));

    }

    //TODO 领取任务和奖励
    public void putTask(String action, int taskId, final InnerCallBack innerCallBack) {
        String url = NetApi.urlTaskRecord + "/" + String.valueOf(taskId);
        LocalLog.d(TAG, "url = " + url);
        switch (action) {
            case "receive_task":
                OkHttpUtils
                        .put()
                        .addHeader("headtoken", getToken(mContext))
                        .url(url)
                        .requestBody(new RequestBody() {
                            @Override
                            public MediaType contentType() {
                                return MediaType.parse("application/x-www-form-urlencoded");
                            }

                            @Override
                            public void writeTo(BufferedSink sink) throws IOException {

                            }
                        })
                        .param("action", action)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        try {
                            if (innerCallBack != null) {
                                ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                innerCallBack.innerCallBack(errorCode);
                            }
                        } catch (JsonSyntaxException j) {
                            j.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (innerCallBack != null) {
                            try {
                                ReceiveTaskResponse receiveTaskResponse = new Gson().fromJson(s, ReceiveTaskResponse.class);
                                innerCallBack.innerCallBack(receiveTaskResponse);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                break;
            case "receive_reward":
                LocalLog.d(TAG, "领取奖励");
                OkHttpUtils
                        .put()
                        .addHeader("headtoken", getToken(mContext))
                        .url(url)
                        .requestBody(new RequestBody() {
                            @Override
                            public MediaType contentType() {
                                return MediaType.parse("application/x-www-form-urlencoded");
                            }

                            @Override
                            public void writeTo(BufferedSink sink) throws IOException {

                            }
                        })
                        .param("action", action)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        if (innerCallBack != null) {
                            try {
                                ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                innerCallBack.innerCallBack(errorCode);
                            } catch (JsonSyntaxException j) {
                                j.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (innerCallBack != null) {
                            try {
                                RecPayResponse recPayResponse = new Gson().fromJson(s, RecPayResponse.class);
                                innerCallBack.innerCallBack(recPayResponse);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                break;
        }

    }

    public void getTaskDetailRec(int taskId) {
        String url = NetApi.urlTaskRecord + "/" + String.valueOf(taskId);
        LocalLog.d(TAG, "getTaskDetailRec() enter url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(taskDetailRecInterface, COMMAND_GET_REC_TASK_DETAIL));
    }

    public void taskMyRelease(int page, int pagesize) {
        String url = NetApi.urlTask + "?action=release&" + "userid=" + String.valueOf(getId(mContext)) + "&page=" + String.valueOf(page) + "&pagesize="
                + String.valueOf(pagesize);
        LocalLog.d(TAG, "taskMyRelease() enter url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(myReleaseTaskInterface, COMMAND_GET_MY_RELEASE_TASK));
    }

    public void getReleaseRecord(int style, int page, int pagesize) {
        String url = NetApi.urlTaskRecord + "?action=send&userid=" + String.valueOf(getId(mContext)) + "&page=" + String.valueOf(page)
                + "&pagesize" + String.valueOf(pagesize) + "&type=" + String.valueOf(style);
        LocalLog.d(TAG, "getReleaseRecord() enter url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(releaseRecordInterface, COMMAND_GET_MY_RELEASE_RECORD));
    }

    //TODO 获取段位列表
    public void getDanList() {
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlUserLevel)
                .build()
                .execute(new NetStringCallBack(danInterface, COMMAND_GET_DAN_LIST));
    }

    public void getUserDan() {
        String url = NetApi.urlUserLevel + "/" + String.valueOf(getId(mContext));
        LocalLog.d(TAG, "getUserDan()  enter");
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(danInterface, COMMAND_GET_USER_DAN));
    }

    public void getTest() {
        LocalLog.d(TAG, "############################测试所有接口#####################");

    }

    private static class ServiceHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SEND_DATA_TO_ENGINE:
                    LocalLog.d(TAG, "handleMessage() enter" + msg.what + " bundle.data ->" + msg.getData().getString("StepService"));
                    break;
            }
            super.handleMessage(msg);
        }

    }

    //TODO 段位
    public void getSportLevel() {
        LocalLog.d(TAG, "getSportLevel() enter");
    }

    //TODO 邀请达人排行榜
    public void getInviteDan(int page, int pagesize) {
        String url = NetApi.urlInvite + "?page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        LocalLog.d(TAG, "getInviteDan() enter url =" + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(inviteInterface, COMMAND_GET_INVITE_DAN));
    }

    public void getMyInviteMsg(final InnerCallBack innerCallBack, int page, int pagesize) {
        String url = NetApi.urlMyInviteData + "?userid=" + String.valueOf(getId(mContext)) + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        LocalLog.d(TAG, "getMyInviteMsg() enter url =  " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o == null) {
                            return;
                        }
                        if (innerCallBack != null) {
                            try {
                                ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                innerCallBack.innerCallBack(errorCode);
                            } catch (JsonSyntaxException j) {
                                j.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (innerCallBack != null) {
                            try {
                                MyInviteResponse myInviteResponse = new Gson().fromJson(s, MyInviteResponse.class);
                                innerCallBack.innerCallBack(myInviteResponse);
                            } catch (JsonSyntaxException e) {

                            }
                        }
                    }
                });
    }

    public void postInviteCode(PostInviteCodeParam postInviteCodeParam) {
        LocalLog.d(TAG, "postInviteCode() enter " + postInviteCodeParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlInvite)
                .params(postInviteCodeParam.getParams())
                .build()
                .execute(new NetStringCallBack(postInviteCodeInterface, COMMAND_POST_INVITE_CODE));
    }

    //TODO 获取任务可选择好友
    public void getUserFriends(int pageIndex, int pagesize, String keyWord) {
        String url = NetApi.urlTaskRecord + "/getFriends?userid=" + getId(mContext) + "&page="
                + String.valueOf(pageIndex) + "&pagesize=" + String.valueOf(pagesize);
        if (!TextUtils.isEmpty(keyWord)) {
            url += "&keyword=" + keyWord;
        }
        LocalLog.d(TAG, "getUserFriends() url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(selectUserFriendInterface, COMMAND_USER_FRIEND));
    }

    //TODO 获取的我领取任务 http://119.29.10.64/v1/TaskRecord?action=all&userid=1
    public void getAllMyRecTask(int style, int pageIndex, int pagesize) {
        String url = NetApi.urlTaskRecord + "?action=all&userid=" + String.valueOf(getId(mContext)) + "&page=" + String.valueOf(pageIndex)
                + "&pagesize" + String.valueOf(pagesize) + "&type=" + String.valueOf(style);
        LocalLog.d(TAG, "url  =  " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(taskMyRecInterface, COMMAND_GET_MY_RCV_TASK_RECORD));

    }

    //TODO HOME Page
    public void getStepToday() {
        LocalLog.d(TAG, "getStepToday() enter");
    }

    public void getTargetStep() {
        LocalLog.d(TAG, "getTargetStep() enter");
    }


    public void getIncomeToday() {
        LocalLog.d(TAG, "getIncomeToday() enter");
    }

    public void getIncomeMonth() {
        LocalLog.d(TAG, "getIncomeMonth() enter");
    }

    //TODO get Weather
    public void getWeather(double latitude, double longitude) {
        //Weather.getWeather(22.548335d, 114.02133d);
        String url = NetApi.urlWeather + "?latitude=" + String.valueOf(latitude) +
                "&longitude=" + String.valueOf(longitude);
        LocalLog.d(TAG, "url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(homePageInterface, COMMAND_WEATHER));
    }

    //TODO 获取账户列表 http://119.29.10.64/v1/UserBankCard?userid=1
    public void getUserBankCard() {
        String url = NetApi.urlUserBankCard + "?userid=" + String.valueOf(getId(mContext));
        LocalLog.d(TAG, "getUserBankCard() enter url = " + url);
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .build()
                .execute(new NetStringCallBack(crashInterface, COMMAND_CRASH_BANK_CARD_LIST));
    }

    //TODO 绑定提现账号
    public void bindCrashAccount(BindCardPostParam bindCardPostParam) {
        bindCardPostParam.setUserid(getId(mContext));
        LocalLog.d(TAG, "bindCrashAccount() enter" + bindCardPostParam.paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlUserBankCard)
                .params(bindCardPostParam.getParams())
                .build()
                .execute(new NetStringCallBack(signCodeInterface, COMMAND_BIND_CRASH_ACCOUNT));
    }

    //TODO 绑定解绑定三方账号
    public void postBindWq(PostBindUnBindWqParam postBindUnBindWqParam) {
        LocalLog.d(TAG, postBindUnBindWqParam.setUserid(getId(mContext)).paramString());
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlBindThird)
                .params(postBindUnBindWqParam.getParams())
                .build()
                .execute(new NetStringCallBack(bindThirdAccoutInterface, COMMAND_BIND_THIRD));
    }

    //TODO 解除绑定
    public void postUnBind(String action, final TextView textView) {
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlUnbindAccount)
                .addParams("action", action)
                .addParams("userid", String.valueOf(getId(mContext)))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        if (o != null) {
                            try {
                                ErrorCode errorCode = new Gson().fromJson(o.toString(), ErrorCode.class);
                                Log.d(TAG, o.toString());
                                if (textView != null) {

                                }
                                PaoToastUtils.showLongToast(mContext, errorCode.getMessage());
                            } catch (JsonSyntaxException j) {
                                j.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        if (textView != null) {
                            Log.d(TAG, s);
                            try {
                                PostBindResponse postBindResponse = new Gson().fromJson(s, PostBindResponse.class);
                                if (postBindResponse.getError() == 0) {
                                    PaoToastUtils.showShortToast(mContext, "解绑成功");
                                    textView.setText("尚未绑定");
                                } else if (postBindResponse.getError() != -100) {
                                    PaoToastUtils.showLongToast(mContext, postBindResponse.getMessage());
                                }
                            } catch (JsonSyntaxException j) {
                                j.printStackTrace();
                            }
                        }
                    }
                });
    }

    //TODO 查询绑定状态
    public void getBindStates() {
        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlGetBindStates)
                .build()
                .execute(new NetStringCallBack(bindThirdAccoutInterface, COMMAND_BIND_THIRD_STATE));
    }

    public void postFollowStatus(final Button button, int followId) {
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(NetApi.urlFollowStatus)
                .addParams("userid", String.valueOf(getId(mContext)))
                .addParams("followid", String.valueOf(followId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                        LocalLog.d(TAG, "查询状态失败");
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            FollowStatusResponse followStatusResponse = new Gson().fromJson(s, FollowStatusResponse.class);
                            if (button != null) {
                                if (followStatusResponse.getData().getIs_follow() == 0) {
                                    button.setText("关注");
                                } else if (followStatusResponse.getData().getIs_follow() == 1) {
                                    button.setText("已关注");
                                }
                            }
                        } catch (JsonSyntaxException j) {
                            j.printStackTrace();
                        }
                    }
                });
    }


    public void postLocation(double la, double lb) {
        OkHttpUtils
                .post()
                .url(NetApi.urlLocation)
                .addHeader("headtoken", getToken(mContext))
                .addParams("userid", String.valueOf(getId(mContext)))
                .addParams("latitude", String.valueOf(la))
                .addParams("longitude", String.valueOf(lb))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        LocalLog.d(TAG, "LOCATION SUCCESS");
                    }
                });
    }

    //TODO 软件协议
    public void protocol(String action) {
        LocalLog.d(TAG, "protocol() enter" + action);
        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .url(urlProtocol)
                .addParams("action", action)
                .build()
                .execute(new NetStringCallBack(protocolInterface, COMMAND_PROTOCOL));
    }

    public double[] getLocation() {
        double[] laction = new double[2];
        laction[0] = la;
        laction[1] = lb;
        return laction;
    }

    public void setLocationAction(double lat, double lbt) {
        la = lat;
        lb = lbt;
    }

    public String getLocationStrFormat() {
        String result = "";
        if (location != null) {
            result = "&province=" + location.getProvince() + "&city=" + location.getCity() + "&district=" + location.getDistrict();
        } else {
            return (String) SharedPreferencesUtil.get("location_bd", "");
        }
        return result;
    }

    public void storgeLoationAd(BDLocation location) {
        if (location != null) {
            String result = "";
            result = "&province=" + location.getProvince() + "&city=" + location.getCity() + "&district=" + location.getDistrict();
            SharedPreferencesUtil.put("location_bd", result);
        }
    }

    //TODO 处理广播信息
    public void handBroadcast(Intent intent) {
        if (intent != null) {
            if (STEP_ACTION.equals(intent.getAction())) {
                LocalLog.d(TAG, "步数信息:");
                if (homePageInterface != null) {
                    int step = intent.getIntExtra("today_step", 0);
                    LocalLog.d(TAG, "today_step = " + step);
                    homePageInterface.responseStepToday(step);
                }
            } else if (LOCATION_ACTION.equals(intent.getAction())) {
                LocalLog.d(TAG, "定位信息");
                String city = intent.getStringExtra("city");
                double la = intent.getDoubleExtra("latitude", 0d);
                double lb = intent.getDoubleExtra("longitude", 0d);
                location = (BDLocation) intent.getParcelableExtra("location");
                storgeLoationAd(location);
                FlagPreference.setLocation(mContext, String.valueOf(la), String.valueOf(lb));
                this.la = la;
                this.lb = lb;
                postLocation(la, lb);
                if (uiCreateCircleInterface != null) {
                    uiCreateCircleInterface.responseLocation(city, la, lb);
                    return;

                }
                if (baiduMapInterface != null) {
                    baiduMapInterface.response(city, la, lb);
                    baiduMapInterface.response(location);
                    return;
                }
                if (releaseDynamicInterface != null) {
                    releaseDynamicInterface.response(city, la, lb);
                    return;
                }
                if (homePageInterface != null) {
                    homePageInterface.responseLocation(city, la, lb);
                }
                if (taskSponsorInterface != null) {
                    taskSponsorInterface.responseLocation(city, la, lb);
                }
            }
        }
    }

    //call onResume
    public void attachUiInterface(CallBackInterface uiCallBackInterface) {
        if (uiCallBackInterface != null && uiCallBackInterface instanceof LoginSignCallbackInterface) {
            this.loginCallBackInterface = (LoginCallBackInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UiHotCircleInterface) {
            this.uiHotCircleInterface = (UiHotCircleInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UiStepAndLoveRankInterface) {
            uiStepAndLoveRankInterface = (UiStepAndLoveRankInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof TagFragInterface) {
            tagFragInterface = (TagFragInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UiCreateCircleInterface) {
            uiCreateCircleInterface = (UiCreateCircleInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof ReflashMyCircleInterface) {
            reflashMyCircleInterface = (ReflashMyCircleInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof DynamicIndexUiInterface) {
            dynamicIndexUiInterface = (DynamicIndexUiInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof DynamicDetailInterface) {
            dynamicDetailInterface = (DynamicDetailInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof OwnerUiInterface) {
            ownerUiInterface = (OwnerUiInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof PayInterface) {
            payInterface = (PayInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof WxPayResultQueryInterface) {
            payWxResultQueryInterface = (WxPayResultQueryInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof MyJoinCircleInterface) {
            myJoinCircleInterface = (MyJoinCircleInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof MyCreatCircleInterface) {
            myCreatCircleInterface = (MyCreatCircleInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof CircleDetailInterface) {
            circleDetailInterface = (CircleDetailInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof CircleMemberManagerInterface) {
            circleMemberManagerInterface = (CircleMemberManagerInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof TaskReleaseInterface) {
            taskReleaseInterface = (TaskReleaseInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof HomePageInterface) {
            homePageInterface = (HomePageInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof NearByInterface) {
            nearByInterface = (NearByInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UserIncomInterface) {
            userIncomInterface = (UserIncomInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof CrashInterface) {
            crashInterface = (CrashInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof SignCodeInterface) {
            signCodeInterface = (SignCodeInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof SelectUserFriendInterface) {
            selectUserFriendInterface = (SelectUserFriendInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UserHomeInterface) {
            userHomeInterface = (UserHomeInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof MyDynamicInterface) {
            myDynamicInterface = (MyDynamicInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof StepDollarDetailInterface) {
            stepDollarDetailInterface = (StepDollarDetailInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof InviteInterface) {
            inviteInterface = (InviteInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof PostInviteCodeInterface) {
            postInviteCodeInterface = (PostInviteCodeInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof MyReleaseTaskInterface) {
            myReleaseTaskInterface = (MyReleaseTaskInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof MyReleaseTaskDetailInterface) {
            myReleaseTaskDetailInterface = (MyReleaseTaskDetailInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof ReleaseRecordInterface) {
            releaseRecordInterface = (ReleaseRecordInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof DanInterface) {
            danInterface = (DanInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UserFollowInterface) {
            userFollowInterface = (UserFollowInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof ReleaseDynamicInterface) {
            releaseDynamicInterface = (ReleaseDynamicInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof TaskMyRecInterface) {
            taskMyRecInterface = (TaskMyRecInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof TaskDetailRecInterface) {
            taskDetailRecInterface = (TaskDetailRecInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof ReceiveTaskInterface) {
            receiveTaskInterface = (ReceiveTaskInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof CrashRecordInterface) {
            crashRecordInterface = (CrashRecordInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof JoinCircleInterface) {
            joinCircleInterface = (JoinCircleInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof SearchCircleInterface) {
            searchCircleInterface = (SearchCircleInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof AddDeleteFollowInterface) {
            addDeleteFollowInterface = (AddDeleteFollowInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof QueryRedPkgInterface) {
            queryRedPkgInterface = (QueryRedPkgInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof FriendHonorInterface) {
            friendHonorInterface = (FriendHonorInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof DanCircleInterface) {
            danCircleInterface = (DanCircleInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof FriendHonorDetailInterface) {
            friendHonorDetailInterface = (FriendHonorDetailInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof CircleStepDetailDanInterface) {
            circleStepDetailDanInterface = (CircleStepDetailDanInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof RechargeDetailInterface) {
            rechargeDetailInterface = (RechargeDetailInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof DearNameModifyInterface) {
            dearNameModifyInterface = (DearNameModifyInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof ForgetPassWordInterface) {
            forgetPassWordInterface = (ForgetPassWordInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof LoginBindPhoneInterface) {
            loginBindPhoneInterface = (LoginBindPhoneInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UserInfoLoginSetInterface) {
            userInfoLoginSetInterface = (UserInfoLoginSetInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof MessageInterface) {
            messageInterface = (MessageInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof FriendAddressInterface) {
            friendAddressInterface = (FriendAddressInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof EditCircleInterface) {
            editCircleInterface = (EditCircleInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof SuggestInterface) {
            suggestInterface = (SuggestInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof OlderPassInterface) {
            olderPassInterface = (OlderPassInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof ProtocolInterface) {
            protocolInterface = (ProtocolInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof BindThirdAccoutInterface) {
            bindThirdAccoutInterface = (BindThirdAccoutInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof BaiduMapInterface) {
            baiduMapInterface = (BaiduMapInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof TaskSponsorInterface) {
            taskSponsorInterface = (TaskSponsorInterface) uiCallBackInterface;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof PhoneSignInterface) {
            phoneSignInterface = (PhoneSignInterface) uiCallBackInterface;
        }
    }

    //call onDestroy
    public void dispatchUiInterface(CallBackInterface uiCallBackInterface) {
        if (uiCallBackInterface != null && uiCallBackInterface instanceof LoginSignCallbackInterface) {
            this.loginCallBackInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UiHotCircleInterface) {
            this.uiHotCircleInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UiStepAndLoveRankInterface) {
            uiStepAndLoveRankInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof TagFragInterface) {
            tagFragInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UiCreateCircleInterface) {
            uiCreateCircleInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof ReflashMyCircleInterface) {
            reflashMyCircleInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof DynamicIndexUiInterface) {
            dynamicIndexUiInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof DynamicDetailInterface) {
            dynamicDetailInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof OwnerUiInterface) {
            ownerUiInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof PayInterface) {
            payInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof WxPayResultQueryInterface) {
            payWxResultQueryInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof MyJoinCircleInterface) {
            myJoinCircleInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof MyCreatCircleInterface) {
            myCreatCircleInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof CircleDetailInterface) {
            circleDetailInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof CircleMemberManagerInterface) {
            circleMemberManagerInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof TaskReleaseInterface) {
            taskReleaseInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof HomePageInterface) {
            homePageInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof NearByInterface) {
            nearByInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UserIncomInterface) {
            userIncomInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof CrashInterface) {
            crashInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof SignCodeInterface) {
            signCodeInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof SelectUserFriendInterface) {
            selectUserFriendInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UserHomeInterface) {
            userHomeInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof MyDynamicInterface) {
            myDynamicInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof StepDollarDetailInterface) {
            stepDollarDetailInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof InviteInterface) {
            inviteInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof PostInviteCodeInterface) {
            postInviteCodeInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof MyReleaseTaskInterface) {
            myReleaseTaskInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof MyReleaseTaskDetailInterface) {
            myReleaseTaskDetailInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof ReleaseRecordInterface) {
            releaseRecordInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof DanInterface) {
            danInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UserFollowInterface) {
            userFollowInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof ReleaseDynamicInterface) {
            releaseDynamicInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof TaskMyRecInterface) {
            taskMyRecInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof TaskDetailRecInterface) {
            taskDetailRecInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof ReceiveTaskInterface) {
            receiveTaskInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof CrashRecordInterface) {
            crashRecordInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof JoinCircleInterface) {
            joinCircleInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof SearchCircleInterface) {
            searchCircleInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof AddDeleteFollowInterface) {
            addDeleteFollowInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof QueryRedPkgInterface) {
            queryRedPkgInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof FriendHonorInterface) {
            friendHonorInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof DanCircleInterface) {
            danCircleInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof DanCircleInterface) {
            friendHonorDetailInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof CircleStepDetailDanInterface) {
            circleStepDetailDanInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof RechargeDetailInterface) {
            rechargeDetailInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof DearNameModifyInterface) {
            dearNameModifyInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof ForgetPassWordInterface) {
            forgetPassWordInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof LoginBindPhoneInterface) {
            loginBindPhoneInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof UserInfoLoginSetInterface) {
            userInfoLoginSetInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof MessageInterface) {
            messageInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof FriendAddressInterface) {
            friendAddressInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof EditCircleInterface) {
            editCircleInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof SuggestInterface) {
            suggestInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof OlderPassInterface) {
            olderPassInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof ProtocolInterface) {
            protocolInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof BindThirdAccoutInterface) {
            bindThirdAccoutInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof BaiduMapInterface) {
            baiduMapInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof TaskSponsorInterface) {
            taskSponsorInterface = null;
        } else if (uiCallBackInterface != null && uiCallBackInterface instanceof PhoneSignInterface) {
            phoneSignInterface = null;
        }
    }

    private Gson gsonPrint = new Gson();

    public void delete(String url, Map<String, String> params, PaoCallBack callBack) {
        if (params == null) params = new HashMap<>();
        LocalLog.d(TAG, gsonPrint.toJson(params));
        callBack.setMyUrl(url);

        OkHttpUtils
                .delete()
                .addHeader("headtoken", getToken(mContext))
                .url(url)
                .params(params)
                .build()
                .execute(callBack);
    }

    public void post(String url, Map<String, String> params, PaoCallBack callBack) {
        if (params == null) params = new HashMap<>();
        LocalLog.d(TAG, gsonPrint.toJson(params));
        callBack.setMyUrl(url);

        OkHttpUtils
                .post()
                .addHeader("headtoken", getToken(mContext))
                .addHeader("limit_version_name", Constants.LIMITE_VERSION)
                .url(url)
                .params(params)
                .build()
                .execute(callBack);
    }


    public void put(String url, Map<String, String> params, PaoCallBack callBack) {
        if (params == null) params = new HashMap<>();
        LocalLog.d(TAG, gsonPrint.toJson(params));
        callBack.setMyUrl(url);
        OkHttpUtils.put()
                .addHeader("headtoken", getToken(mContext))
                .addHeader("limit_version_name", Constants.LIMITE_VERSION)
                .url(url)
                .requestBody(new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return MediaType.parse("application/x-www-form-urlencoded");
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {

                    }
                })
                .params(params)
                .build().execute(callBack);
    }

    public void get(String url, Map<String, String> params, PaoCallBack callBack) {
        if (params == null) params = new HashMap<>();
        LocalLog.d(TAG, "提交数据：" + new Gson().toJson(params));
        callBack.setMyUrl(url);

        OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .addHeader("limit_version_name", Constants.LIMITE_VERSION)
                .url(url)
                .params(params)
                .build()
                .execute(callBack);
    }

    public String getSync(String url, Map<String, String> params) throws IOException {
        if (params == null) params = new HashMap<>();
        LocalLog.d(TAG, "提交数据：" + new Gson().toJson(params));

        Response result = OkHttpUtils
                .get()
                .addHeader("headtoken", getToken(mContext))
                .addHeader("limit_version_name", Constants.LIMITE_VERSION)
                .url(url)
                .params(params)
                .build()
                .execute();
        String reusltStr = result.body().string();
        LocalLog.d(TAG, url + "返回数据：" + reusltStr);
        return reusltStr;
    }


    public String getShopEnd() {
        return "headtoken=" + getToken(mContext) + "&app_sign=" + Utils.appSignShop(getToken(mContext));
    }

    public String shop() {
        if (!TextUtils.isEmpty(getToken(mContext))) {
            return NetApi.urlShop + "headtoken=" + getToken(mContext) + "&app_sign=" + Utils.appSignShop(getToken(mContext));
        } else {
            return null;
        }
    }
}
