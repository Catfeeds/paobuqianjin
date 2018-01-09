package com.paobuqianjin.pbq.step.model;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.ImageView;
import android.widget.Toast;

import com.l.okhttppaobu.okhttp.OkHttpUtils;
import com.l.okhttppaobu.okhttp.callback.BitmapCallback;
import com.paobuqianjin.pbq.step.data.bean.gson.param.AddFollowParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.AuthenticationParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CreateCircleBodyParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.DynamicContentParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.DynamicParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.FeedBackParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostIncomeParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostMessageParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostWithDrawParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.param.UserRecordParam;
import com.paobuqianjin.pbq.step.data.netcallback.NetStringCallBack;
import com.paobuqianjin.pbq.step.presenter.im.CallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.LoginSignCallbackInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReflashMyCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.SignCodeCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.LoginCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.TagFragInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiCreateCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiHotCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiStepAndLoveRankInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserInfoInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

import static com.paobuqianjin.pbq.step.utils.NetApi.urlFindPassWord;
import static com.paobuqianjin.pbq.step.utils.NetApi.urlNearByPeople;
import static com.paobuqianjin.pbq.step.utils.NetApi.urlRegisterPhone;

/**
 * Created by pbq on 2017/11/29.
 */

public final class Engine {
    private final static String TAG = Engine.class.getSimpleName();
    private static Engine engine;
    private static Context mContext;
    private StepServerConnection connection = new StepServerConnection();
    private Messenger messenger;
    private Messenger serviceMessenger = new Messenger(new ServiceHandler());
    private final static int MSG_SEND_DATA_TO_SETP_SERVICE = 0;
    private final static int MSG_SEND_DATA_TO_ENGINE = 1;
    private LoginCallBackInterface loginCallBackInterface;
    private UiCreateCircleInterface uiCreateCircleInterface;
    private TagFragInterface tagFragInterface;
    private UiHotCircleInterface uiHotCircleInterface;
    private ReflashMyCircleInterface reflashMyCircleInterface;
    private UiStepAndLoveRankInterface uiStepAndLoveRankInterface;
    public final static int COMMAND_REQUEST_SIGN = 0;
    public final static int COMMAND_REG_BY_PHONE = 1;
    public final static int COMMAND_LOGIN_IN = 2;
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

    private Engine() {

    }

    public static Engine getEngine(Context context) {
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

    public void bindService(String action, Class<? extends Service> clazz) {
        LocalLog.d(TAG, "bindService() enter");
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setClass(mContext, clazz);
        mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void unbindStepService() {
        LocalLog.d(TAG, "unbindStepService() enter");
        mContext.unbindService(connection);
    }

    private class StepServerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LocalLog.d(TAG, "onServiceConnected() = " + componentName);
            if (iBinder != null) {
                messenger = new Messenger(iBinder);
                sendToService(new Bundle(), MSG_SEND_DATA_TO_SETP_SERVICE);
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LocalLog.d(TAG, "onServiceDisconnected() 断开连接！");
        }
    }

    public void sendToService(Bundle bundle, int what) {
        LocalLog.d(TAG, "sendBundleToService() enter");
        if (messenger != null) {
            Message msg = Message.obtain();
            msg.what = what;
            bundle.putString("Engine", "Engine -> StepService");
            msg.setData(bundle);
            msg.replyTo = serviceMessenger;
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                LocalLog.d(TAG, "sendToService() failed");
                e.printStackTrace();
            } finally {

            }
        }

    }


    public boolean getLogFlag(Context context) {
        return FlagPreference.getLoginFlag(context);
    }

    public void setLogFlag(Context context, boolean isLogin) {
        FlagPreference.setLoginFlag(context, isLogin);
    }

    public int getId(Context context) {
        return FlagPreference.getUid(context);
    }

    public void setId(Context context, int id) {
        FlagPreference.setUid(context, id);
    }

    public String getStartSportTime(Context context) {
        return FlagPreference.getEffectStartSportTime(context);
    }

    public void setStartServiceTime(Context context, String startServiceTime) {
        FlagPreference.setStartServiceTime(context, startServiceTime);
    }

    public void getUserInfo(final int userId) {
        LocalLog.d(TAG, "getUserInfo() enter");
        String url = NetApi.urlUser + String.valueOf(userId);
        LocalLog.d(TAG, "getUserInfo() url = " + url);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(new UserInfoInterface() {
                    @Override
                    public void update(UserInfoResponse userInfoResponse) {
                        LocalLog.d(TAG, " 获取用户信息回调成功!" + userInfoResponse.toString());
                    }
                }));
    }

    //重置密码
    public void refreshPassWorld() {
        LocalLog.d(TAG, "findPassWorld() enter");
        String url = urlFindPassWord + "13424156029";
        OkHttpUtils
                .put()
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

    //手机号码注册
    public void registerByPhoneNumber(String[] userInfo) {
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
        OkHttpUtils
                .post()
                .url(urlRegisterPhone)
                .addParams("mobile", userInfo[0])
                .addParams("password", userInfo[2])
                .addParams("code", userInfo[1])
                .build()
                .execute(new NetStringCallBack(loginCallBackInterface, COMMAND_REG_BY_PHONE));
    }

    //获取附近的人
    public void getNearByPeople() {
        LocalLog.d(TAG, "urlNearByPeople() enter");
        OkHttpUtils
                .get()
                .url(urlNearByPeople)
                .build()
                .execute(new NetStringCallBack(loginCallBackInterface, COMMAND_NEARBY_PEOPLE));
    }

    //获取验证码
    public void getMsg(String phone) {
        LocalLog.d(TAG, "getMsg() enter phone =" + phone);
        if (!isPhone(phone)) {
            Toast.makeText(mContext, "请输入一个手机号码:", Toast.LENGTH_SHORT).show();
            return;

        }
        String url = NetApi.urlSendMsg + phone;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(new SignCodeCallBackInterface() {
                    @Override
                    public void signCodeCallBack(SignCodeResponse response) {
                        Toast.makeText(mContext, "验证码已发送，请查看短信！" + response.getMessage() + " data : " + response.getData(), Toast.LENGTH_SHORT).show();
                        if (loginCallBackInterface != null && loginCallBackInterface instanceof LoginSignCallbackInterface) {
                            // 设置验码
                            ((LoginSignCallbackInterface) loginCallBackInterface).requestPhoneSignCodeCallBack(response.getData());
                        }
                        return;
                    }
                }));
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

    public void userLoginByPhoneNumber(String[] userInfo) {
        LocalLog.d(TAG, "userLoginByPhoneNumber() enter");
        if (!isPhone(userInfo[0])) {
            Toast.makeText(mContext, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userInfo[1] == null) {
            Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils
                .post()
                .url(NetApi.urlUserLogin)
                .addParams("mobile", userInfo[0])
                .addParams("password", userInfo[1])
                .build()
                .execute(new NetStringCallBack(loginCallBackInterface, COMMAND_LOGIN_IN));
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
                .url(NetApi.urlCircleCover)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //TODO 获取圈子的封面
    public void getCircleCoverByCircleId(int circleId) {

    }

    //动态接口
    public void getDynamic(int page, int pagesize) {
        LocalLog.d(TAG, "getDynamic() enter");
        String url = NetApi.urlDynamic + "?page=" + page + "&pagesize=" + pagesize;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //发布动态
    public void postDynamic(DynamicParam dynamicParam) {
        LocalLog.d(TAG, "postDynamic() enter");
        OkHttpUtils
                .post()
                .url(NetApi.urlDynamic)
                .params(dynamicParam.getParam())
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //获取动他详情by    动态id
    public void getDynamicDetail(int id) {
        LocalLog.d(TAG, "getDynamicDetail() enter");
        String url = NetApi.urlDynamic + "/" + String.valueOf(id);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //获取评论列表 http://119.29.10.64/v1/DynamicComment?dynamicid=2
    public void getDynamicCommentList(int dynamicid, int page, int pagesize) {
        LocalLog.d(TAG, "getDynamicCommentList() enter");
        String url = NetApi.urlDynamicComment + "?dynamicid=" + dynamicid + "&page=" + page + " &pagesize=" + pagesize;

        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //获取单条评论
    public void getDynamicById(int id) {
        LocalLog.d(TAG, "getDynamicById() enter");
        String url = NetApi.urlDynamicComment + "/" + String.valueOf(id);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //Post 发送评论
    public void postDynamicComment(DynamicContentParam dynamicContentParam) {
        LocalLog.d(TAG, "postDynamicComment() enter");
        OkHttpUtils
                .post()
                .url(NetApi.urlDynamicComment)
                .params(dynamicContentParam.getParam())
                .build()
                .execute(new NetStringCallBack(null, -1));
    }


    //获取点赞用户列表
    public void getDynamicVoteList(int dynamicid, int page, int pagesize) {
        LocalLog.d(TAG, "getDynamicVoteList() enter");
        String url = NetApi.urlDynamicVote + "?dynamicid=" + dynamicid + "&page=" + page + "&pagesie=" + pagesize;
        OkHttpUtils
                .get()
                .url(url)
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
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //添加用户反馈
    public void postFeedBack(FeedBackParam feedBackParam) {
        LocalLog.d(TAG, "postFeedBack() enter");
        OkHttpUtils
                .post()
                .url(NetApi.urlFeedBack)
                .params(feedBackParam.getParam())
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //获取所有圈子成员
    public void getCircleMemberAll(int circleid, int page, int pagesize) {
        LocalLog.d(TAG, "getCircleMemberAll() enter");
        String url = NetApi.urlCircleMember + "/" + String.valueOf(circleid)
                + "&page=" + page + "&pagesize=" + pagesize;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //关于我们类型 http://119.29.10.64/v1/abouttype
    public void getAboutType() {
        LocalLog.d(TAG, "getAboutType() enter");
        OkHttpUtils
                .get()
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
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, 0));
    }

    //获取消息列表请求方式：get，地址：http://119.29.10.64/v1/messages/?userid=5&typeid=1，
    // 参数：userid用户id，typeid消息类型id，如果是系统消息，请不要传值用户userid和类型typeid,-------------分页：
    // page默认当前页为第一页，pagesize默认10条数据

    public void getMessage(int userid, int typeid) {
        String url = NetApi.urlMessage + "/?useid=" + String.valueOf(userid)
                + "&typeid=" + String.valueOf(typeid);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //获取消息详情，请求方式：get，地址：http://119.29.10.64/v1/messages/detail/?id=1，参数：消息id
    public void getMessageDetail(int id) {
        LocalLog.d(TAG, "getMessageDetail() enter");
        String url = NetApi.urlMessage + "/detail/?id=" + String.valueOf(id);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //Post message
    public void postMessage(PostMessageParam postMessageParam) {
        LocalLog.d(TAG, "postMessage() enter");
        OkHttpUtils
                .post()
                .url(NetApi.urlMessage)
                .params(postMessageParam.getParam())
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //修改备注名称和设为管理员
    public void markAdminReset() {
        LocalLog.d(TAG, "markAdminReset() enter");
        String url = NetApi.urlCircleMember;
    }


    //加入圈子
    public void joinCircle(int circleid) {
        LocalLog.d(TAG, "joinCircle() enter");
        OkHttpUtils
                .post()
                .url(NetApi.urlCircleMember)
                .addParams("userid", String.valueOf(getId(mContext)))
                .addParams("circleid", String.valueOf(circleid))
                .build()
                .execute(new NetStringCallBack(null, COMMAND_JOIN_CIRCLE));
    }

    public void joinCircle(int circleid, String password) {
        LocalLog.d(TAG, "joinCircle() password");
        OkHttpUtils
                .post()
                .url(NetApi.urlCircleMember)
                .addParams("userid", String.valueOf(getId(mContext)))
                .addParams("circleid", String.valueOf(circleid))
                .addParams("password", password)
                .build()
                .execute(new NetStringCallBack(null, COMMAND_JOIN_CIRCLE));
    }

    //TODO 获取圈子目标列表
    public void getCircleTarget() {
        LocalLog.d(TAG, "getCircleTarget() enter");
        OkHttpUtils
                .get()
                .url(NetApi.urlTarget)
                .build()
                .execute(new NetStringCallBack(uiCreateCircleInterface, COMMAND_GET_CIRCLE_TARGET));
    }

    /*TODO 圈子类型接口*/
    public void getCircleType() {
        LocalLog.d(TAG, "圈子类型列表：getCircleType() enter");
        OkHttpUtils
                .get()
                .url(NetApi.urlCircleType)
                .build()
                .execute(new NetStringCallBack(uiCreateCircleInterface, COMMAND_CIRCLE_TYPE) {
                });
    }


    /* TODO 圈子接口*/
    public void getCircleStepRank(int circleId, int page, int pagesize) {
        LocalLog.d(TAG, "圈子步数排行：getCircleStepRank() enter");
        String url = NetApi.urlCircleRank + "/?circleid=" + String.valueOf(circleId)
                + "&action=step";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(uiStepAndLoveRankInterface, COMMAND_STEP_RANK));
    }


    public void getCircleRechargeRank(int circleId, int page, int pagesize) {
        LocalLog.d(TAG, "充值排行：getCircleRechargeRank() enter");
        String url = NetApi.urlCircleRank + "/?circleid=" + String.valueOf(circleId)
                + "&action=recharge";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(uiStepAndLoveRankInterface, COMMAND_RECHARGE_RANK));
    }

    public void getMyJoinCircle(int page, int pagesize) {
        LocalLog.d(TAG, "我加入的圈子：getMyJoin() enter");
        String url = NetApi.urlCircle + "?action=join" + "&userid=" + String.valueOf(getId(mContext))
                + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(uiHotCircleInterface, COMMAND_GET_MY_JOIN_CIRCLE));
    }


    public void reflashMyCreateCircle(int page, int pagesize) {
        LocalLog.d(TAG, " 我创建的圈子：getMyCreateCirlce() enter");
        String url = NetApi.urlCircle + "?action=create" + "&userid=" + String.valueOf(engine.getId(mContext))
                + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(reflashMyCircleInterface, COMMAND_REFLASH_CIRCLE));
    }

    public void getMyCreateCirlce(int page, int pagesize) {
        LocalLog.d(TAG, " 我创建的圈子：getMyCreateCirlce() enter");
        String url = NetApi.urlCircle + "?action=create" + "&userid=" + String.valueOf(engine.getId(mContext))
                + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(uiHotCircleInterface, COMMAND_GET_MY_CREATE_CIRCLE));
    }


    public void getCircleChoice(int userid, int page, int pagesize) {
        LocalLog.d(TAG, "精选圈子：getCircleChoice() enter");
        String url = NetApi.urlCircle + "?action=choice" + "&userid=" + String.valueOf(userid)
                + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(pagesize);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(uiHotCircleInterface, COMMAND_GET_CHOICE_CIRCLE));
    }


    public void createCircle(CreateCircleBodyParam createCircleBodyParam) {
        LocalLog.d(TAG, "  创建圈子createCircle() enter" + createCircleBodyParam.toString());
        OkHttpUtils
                .post()
                .url(NetApi.urlCircle)
                .params(createCircleBodyParam.getParams())
                .build()
                .execute(new NetStringCallBack(uiCreateCircleInterface, COMMAND_CREATE_CIRCLE));
    }

    public void getCircleDetail(int circleId) {
        LocalLog.d(TAG, " 获取圈子详情 getCircleDetail() ");
        String url = NetApi.urlCircle + "/" + String.valueOf(circleId);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(uiStepAndLoveRankInterface, COMMAND_GET_CIRCLE_DETAIL));
    }

    public void putCircle(CreateCircleBodyParam createCircleBodyParam, int circleId) {
        LocalLog.d(TAG, "编辑圈子 putCircle()");
        String url = NetApi.urlCircle + "/" + circleId;
        OkHttpUtils
                .put()
                .url(url)
                .params(createCircleBodyParam.getParams())
                .build()
                .execute(new NetStringCallBack(null, COMMAND_EDIT_CIRCLE));
    }


    public void deleteCircle(int circleId) {
        LocalLog.d(TAG, "删除圈子：deleteCircle() enter");
        String url = NetApi.urlCircle + "/" + circleId;
        OkHttpUtils
                .delete()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, COMMAND_DELETE_CIRCLE));
    }

    //获取用户登录记录，暂时无需实现
    public void getUserRecord(int userId) {
        LocalLog.d(TAG, "getUserRecord() enter");
        String url = NetApi.urlUserRecord + String.valueOf(userId);
        OkHttpUtils
                .get()
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
                .url(NetApi.urlUserRecordPost)
                .params(userRecordParam.getParam())
                .build()
                .execute(new NetStringCallBack(null, -1));

    }

    //请求方式get，地址：http://pb.com/v1/userstep/1 参数：用户id
    public void getUserStep(int id) {
        LocalLog.d(TAG, "getUserStep() enter");
        String url = NetApi.urlUserStep + String.valueOf(id);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //请求方式：put，地址：http://pb.com/v1/userstep/1，参数：用户id，用户最新行走步数：step_number
    public void putUserStep(int id, int todayStep) {
        LocalLog.d(TAG, "putUserStep() enter");
        String url = NetApi.urlUserStep + String.valueOf(id);
        OkHttpUtils
                .put()
                /*.requestBody("application/x-www-form-urlencoded")*/
                .param("step_number", String.valueOf(todayStep))
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //获取昨日收益，当月收益，总收益，请求方式：get，地址：http://pb.com/v1/income/?id=1&action=yesterday，参数：用户id、action=all（总收益）、action=month（当月收益）、action=yesterday（昨日收益）
    public void getIncome(int id, String yesterday) {
        String url;
        OkHttpUtils
                .get()
                .url(NetApi.urlIncome)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //请求方式post，地址：http://pb.com/v1/income，参数：userid（用户id）、typeid（收益类型）、circleid（圈子id）、amount（收益金额）
    public void putIncome(PostIncomeParam param) {
        String url;
        OkHttpUtils
                .post()
                .url(NetApi.urlIncomePost)
                .params(param.getParam())
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //用户认证，请求方式：post，地址：http://api.runmoneyin.com/v1/userauthentication，参数：id（用户id）、idcard（身份证号）、realname（真实名字）
    public void postAuthentication(AuthenticationParam authenticationParam) {
        LocalLog.d(TAG, "postAuthentication() enter");
        OkHttpUtils
                .post()
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
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //获取用户步币详细信息，请求方式：get，地址：http://api.runmoneyin.com/v1/usercredit/?id=5
    public void getUserCredit(int id) {
        LocalLog.d(TAG, "getUserCredit() enter");
        String url = NetApi.urlCredit + String.valueOf(id);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, 0));
    }

    //获取用户提现信息记录，请求方式：get，地址：http://api.runmoneyin.com/v1/withdraw/2，参数：用户id
    public void getWithDraw(int id) {
        LocalLog.d(TAG, "getWithDraw() enter");
        String url = NetApi.urlWithDraw + String.valueOf(id);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //添加用户提现记录，请求方式：post，地址：http://api.runmoneyin.com/v1/withdraw，参数：type提现类型、amount提现金额、userid用户id
    public void postWithDraw(PostWithDrawParam postWithDrawParam) {
        LocalLog.d(TAG, "postWithDraw() enter");
        OkHttpUtils
                .post()
                .url(NetApi.urlWithDraw)
                .params(postWithDrawParam.getParam())
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //邀请排行榜，请求方式：get，地址：http://api.runmoneyin.com/v1/userinviter
    public void getUserInviter() {
        LocalLog.d(TAG, "getUserInviter() enter");
        OkHttpUtils
                .get()
                .url(NetApi.urlUserInviter)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //获取我邀请的人数，请求方式：get，地址：http://api.runmoneyin.com/v1/userinviter/1
    public void getUserInter(int id) {
        LocalLog.d(TAG, "getUserInter(id) enter");
        String url = NetApi.urlUserInviter + String.valueOf(id);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //我的关注
    public void getUserMeFollow(int id) {
        LocalLog.d(TAG, "getUserMeFollow( id ) enter");
        String url = NetApi.urlUserFollow + "?userid=" + String.valueOf(id);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //添加关注
    public void postAddUserFollow(AddFollowParam addFollowParam) {
        LocalLog.d(TAG, "postAddUserFollow()");
        OkHttpUtils
                .get()
                .url(NetApi.urlUserFollow)
                .params(addFollowParam.getParam())
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //关注我的： http://api.runmoneyin.com/v1/UserFollow/5
    public void getUserFollowMe(int id) {
        LocalLog.d(TAG, "getUserFollowMe() enter");
        String url = NetApi.urlUserFollow + "/" + String.valueOf(id);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(null, -1));
    }

    //取消关注 TODO
    public void deleteMeFollow(int id) {
        LocalLog.d(TAG, "deleteMeFollow() enter");

    }

    //网络图片获取接口
    public void getImage(final ImageView imageView, String urlImage) {
        LocalLog.d(TAG, "getImage() enter");
        Picasso.with(mContext).load(urlImage).into(imageView);
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

    //

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
        }

    }
}
