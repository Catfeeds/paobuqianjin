package com.paobuqianjin.pbq.step.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChatCircleInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChatImagesResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowStatusResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.RongYunChatUtils;
import com.paobuqianjin.pbq.step.utils.SharedPreferencesUtil;
import com.paobuqianjin.pbq.step.view.base.PaoBuApplication;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Administrator on 2018/6/26.
 */
public class ConversationActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {

    private static final String TAG = ConversationActivity.class.getClass().getSimpleName();
    private final static String DELETE_ACTION = "com.paobuqianjin.pbq.step.DELETE_CIRCLE";
    private String targetId;
    private String title;
    private Conversation.ConversationType mConversationType;
    private boolean isOutOfCircle;
    private HandlerThread handlerThread = new HandlerThread("member_list");
    private Handler handler;
    private int currentPage = 0;
    private int pageCount = 0;
    private ArrayList<UserInfo> list = new ArrayList<>();

    @Override
    protected String title() {
        return TextUtils.isEmpty(title) ? "" : title;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getData().getQueryParameter("title");
        targetId = getIntent().getData().getQueryParameter("targetId");
        setContentView(R.layout.activity_conversation);
    }

    @Override
    protected void initView() {
        setToolBarListener(this);
        String chatToken = (String) SharedPreferencesUtil.get(Constants.SP_RONGYUN_CHAT_TOKEN, "");
        mConversationType = Conversation.ConversationType.valueOf(getIntent().getData()
                .getLastPathSegment().toUpperCase(Locale.getDefault()));
        if (mConversationType == Conversation.ConversationType.GROUP) {
            initGroupView();
        } else if (mConversationType == Conversation.ConversationType.PRIVATE) {
            //initFriendView();
        }

        if (TextUtils.isEmpty(chatToken)) {
            RongYunChatUtils.getInstance().getChatTokenAndConnect(new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                    finish();
                }

                @Override
                public void onSuccess(String s) {
                    RongYunChatUtils.getInstance().chatTo(ConversationActivity.this, mConversationType, targetId, title);
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    finish();
                }
            });
        }
    }


    private void initGroupView() {
        findViewById(R.id.circle_message).setVisibility(View.VISIBLE);
        setRightValue(R.id.bar_tv_right, "进入社群");
        final ImageView iv_banner = findViewById(R.id.iv_banner);
        Map<String, String> params = new HashMap<>();
        params.put("site", "banner");
        params.put("groupid", targetId);
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlGetConversationCircleInfo, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (ConversationActivity.this == null) return;
                final ChatCircleInfoResponse chatImagesResponse = new Gson().fromJson(s, ChatCircleInfoResponse.class);
                if (chatImagesResponse != null && chatImagesResponse.getData().getImages().size() > 0) {
                    Presenter.getInstance(ConversationActivity.this).getImage(iv_banner, chatImagesResponse.getData().getImages().get(0).getUrl());
                    isOutOfCircle = chatImagesResponse.getData().getGroup_delete().equals("1");
                }

                if (isOutOfCircle) {
                    final NormalDialog normalDialog = new NormalDialog(ConversationActivity.this);
                    normalDialog.setMessage("该社群已被解散");
                    normalDialog.setSingleBtn(true);
                    normalDialog.setYesOnclickListener("知道了", new NormalDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            RongYunChatUtils.getInstance().removeConvertion(Conversation.ConversationType.GROUP, targetId + "", null);
                            normalDialog.dismiss();
                            finish();
                        }
                    });
                    normalDialog.show();
                } else {
                    //初始化@能力，用户没有拉取完成时将点时间无法使用
                    handlerThread.start();
                    handler = new Handler(handlerThread.getLooper()) {

                        private void getAllMember(final int page) {
                            LocalLog.d(TAG, "获取成员 page =" + page);
                            currentPage = page;
                            final String url = NetApi.urlCircleMember + "/" + String.valueOf(targetId)
                                    + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(2 * Constants.PAGE_SIZE);
                            Presenter.getInstance(getApplicationContext()).getPaoBuSimple(url, null, new PaoCallBack() {
                                @Override
                                protected void onSuc(String s) {
                                    try {
                                        CircleMemberResponse circleMemberResponse = new Gson().fromJson(s, CircleMemberResponse.class);
                                        if (circleMemberResponse != null && circleMemberResponse.getData() != null
                                                && circleMemberResponse.getData().getPagenation() != null) {
                                            pageCount = circleMemberResponse.getData().getPagenation().getTotalPage();
                                            if (circleMemberResponse.getData().getData() != null && circleMemberResponse.getData().getData().size() > 0) {
                                                for (int i = 0; i < circleMemberResponse.getData().getData().size(); i++) {
                                                    UserInfo userInfo = new UserInfo(String.valueOf(circleMemberResponse.getData().getData().get(i).getId()),
                                                            !TextUtils.isEmpty(circleMemberResponse.getData().getData().get(i).getCirclenickname()) ? circleMemberResponse.getData().getData().get(i).getCirclenickname()
                                                                    : circleMemberResponse.getData().getData().get(i).getNickname(),
                                                            TextUtils.isEmpty(circleMemberResponse.getData().getData().get(i).getAvatar()) ? null : Uri.parse(circleMemberResponse.getData().getData().get(i).getAvatar()));
                                                    list.add(userInfo);
                                                }

                                                if (currentPage >= pageCount) {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            RongIM.getInstance().setGroupMembersProvider(new RongIM.IGroupMembersProvider() {
                                                                @Override
                                                                public void getGroupMembers(String s, RongIM.IGroupMemberCallback iGroupMemberCallback) {
                                                                    LocalLog.d(TAG, "设置 @ 成员信息提供者");
                                                                    iGroupMemberCallback.onGetGroupMembersResult(list);
                                                                }
                                                            });
                                                        }
                                                    });
                                                } else {
                                                    android.os.Message msg = android.os.Message.obtain();
                                                    msg.what = currentPage + 1;
                                                    sendMessage(msg);
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                                }
                            });

                        }

                        @Override
                        public void handleMessage(android.os.Message msg) {
                            getAllMember(msg.what);
                        }
                    };
                    android.os.Message msg = android.os.Message.obtain();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    @Override
    public void clickLeft() {
       onBackPressed();
    }

    @Override
    public void clickRight() {
        if(TextUtils.isEmpty(targetId)) return;
        if(isOutOfCircle){
            PaoToastUtils.showShortToast(this, getString(R.string.tips_circle_be_cancel));
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, CirCleDetailActivity.class);
        intent.putExtra(getPackageName() + "circleid", Integer.parseInt(targetId));
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            LocalLog.d(TAG,"onActivityResult");
        }
    }
}
