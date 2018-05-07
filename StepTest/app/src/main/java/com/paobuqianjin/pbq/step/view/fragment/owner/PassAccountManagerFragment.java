package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostBindUnBindWqParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostBindResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostBindStateResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.BindThirdAccoutInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.OlderPassChangeActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/3/31.
 */

public class PassAccountManagerFragment extends BaseBarStyleTextViewFragment implements BindThirdAccoutInterface {
    private final static String TAG = PassAccountManagerFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.address_mail)
    ImageView addressMail;
    @Bind(R.id.phone_address)
    TextView phoneAddress;
    @Bind(R.id.pass_word_layer)
    RelativeLayout passWordLayer;
    @Bind(R.id.address_weichat)
    ImageView addressWeichat;
    @Bind(R.id.weichat_address)
    TextView weichatAddress;
    @Bind(R.id.address_weichat_layer)
    RelativeLayout addressWeichatLayer;
    @Bind(R.id.address_qq)
    ImageView addressQq;
    @Bind(R.id.qq_address)
    TextView qqAddress;
    @Bind(R.id.qq_address_layer)
    RelativeLayout qqAddressLayer;
    @Bind(R.id.we_chat_des)
    TextView weChatDes;
    @Bind(R.id.go_to_we_chat)
    ImageView goToWeChat;
    @Bind(R.id.qq_chat_des)
    TextView qqChatDes;
    @Bind(R.id.go_to_qq)
    ImageView goToQq;
    @Bind(R.id.account_phone)
    ImageView accountPhone;
    @Bind(R.id.phone_account)
    TextView phoneAccount;
    @Bind(R.id.phone_chat_des)
    TextView phoneChatDes;
    @Bind(R.id.go_to_phone)
    ImageView goToPhone;
    @Bind(R.id.address_phone_layer)
    RelativeLayout addressPhoneLayer;
    @Bind(R.id.bind_account)
    RelativeLayout bindAccount;
    private UserInfoResponse.DataBean userInfo;
    private View popBirthSelectView;
    private PopupWindow popupSelectWindow;
    private TranslateAnimation animationCircleType;
    private String action;
    private ProgressDialog dialog;

    @Override
    protected int getLayoutResId() {
        return R.layout.bind_account_fg;
    }

    @Override
    protected String title() {
        return "账号";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        UMShareAPI.get(getContext()).release();
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        weChatDes = (TextView) viewRoot.findViewById(R.id.we_chat_des);
        qqChatDes = (TextView) viewRoot.findViewById(R.id.qq_chat_des);
        phoneChatDes = (TextView) viewRoot.findViewById(R.id.phone_chat_des);
        dialog = new ProgressDialog(getContext());
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            userInfo = (UserInfoResponse.DataBean) intent.getSerializableExtra("userinfo");
            LocalLog.d(TAG, "userInfo = " + userInfo.toString());
            /*if (userInfo != null) {
                if (userInfo.getWx_openid() != null && !"".equals(userInfo.getWx_openid())) {
                    weChatDes.setText("已绑定账号");
                }
                if (userInfo.getQq_openid() != null && !"".equals(userInfo.getQq_openid())) {
                    qqChatDes.setText("已绑定账号");
                }
            }*/
        }
        Presenter.getInstance(getContext()).getBindStates();
    }


    private void bindUnbindConfirm(final String actionWxQ) {
        LocalLog.d(TAG, "actionWxQ =" + actionWxQ);
        action = actionWxQ;
        popBirthSelectView = View.inflate(getContext(), R.layout.bind_unbind_select, null);
        TextView bindOpTextView = (TextView) popBirthSelectView.findViewById(R.id.bind_op);
        if (actionWxQ.equals("wx")) {
            switch (weChatDes.getText().toString()) {
                case "已绑定账号":
                    bindOpTextView.setText("是否解绑微信");
                    break;
                case "尚未绑定":
                    bindOpTextView.setText("是否绑定微信");
                    break;
                default:
                    break;
            }
        } else if (actionWxQ.equals("qq")) {
            switch (qqChatDes.getText().toString()) {
                case "已绑定账号":
                    bindOpTextView.setText("是否解绑QQ");
                    break;
                case "尚未绑定":
                    bindOpTextView.setText("是否绑定QQ");
                    break;
                default:
                    break;
            }
        }
        popupSelectWindow = new PopupWindow(popBirthSelectView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "bindUnbindConfirm dismiss() ");
                popupSelectWindow = null;
            }
        });
        Button confirmBt = (Button) popBirthSelectView.findViewById(R.id.btn_confirm);
        Button cancelBt = (Button) popBirthSelectView.findViewById(R.id.cancel);
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "取消绑定或绑定");
                if (actionWxQ.equals("wx")) {
                    PostBindUnBindWqParam postBindUnBindWqParam = new PostBindUnBindWqParam();
                    switch (weChatDes.getText().toString()) {
                        case "已绑定账号":
                            LocalLog.d(TAG, "解绑WX");
                            postBindUnBindWqParam.setAction("wx").setOpenid(userInfo.getWx_openid());
                            Presenter.getInstance(getContext()).postBindWq(postBindUnBindWqParam);
                            break;
                        case "尚未绑定":
                            LocalLog.d(TAG, "绑定WX");
                            UMShareAPI.get(getContext()).getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
                            break;
                        default:
                            break;
                    }

                } else if (actionWxQ.equals("qq")) {
                    switch (qqChatDes.getText().toString()) {
                        case "已绑定账号":
                            LocalLog.d(TAG, "解绑QQ");
                            break;
                        case "尚未绑定":
                            LocalLog.d(TAG, "绑定QQ");
                            UMShareAPI.get(getContext()).getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, authListener);
                            break;
                        default:
                            break;
                    }

                }

                popupSelectWindow.dismiss();
            }
        });
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupSelectWindow.dismiss();
            }
        });
        popupSelectWindow.setFocusable(true);
        popupSelectWindow.setOutsideTouchable(true);
        popupSelectWindow.setBackgroundDrawable(new BitmapDrawable());

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.bind_account), Gravity.CENTER_HORIZONTAL, 0, 0);
        popBirthSelectView.startAnimation(animationCircleType);
    }


    @OnClick({R.id.pass_word_layer, R.id.we_chat_des, R.id.qq_chat_des, R.id.phone_chat_des})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pass_word_layer:
                LocalLog.d(TAG, "修改密码");
                startActivity(OlderPassChangeActivity.class, null);
                break;
            case R.id.we_chat_des:
                LocalLog.d(TAG, "绑定或者解绑");
                bindUnbindConfirm("wx");
                break;
            case R.id.qq_chat_des:
                LocalLog.d(TAG, "绑定或解绑");
                bindUnbindConfirm("qq");
                break;
            case R.id.phone_chat_des:
                LocalLog.d(TAG, "手机号码绑定");
                break;
            default:
                break;
        }
    }


    private UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LocalLog.d(TAG, "授权开始callback:" + share_media.toString());
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            LocalLog.d(TAG, "授权成功callback:" + share_media.toString());
            Toast.makeText(getContext(), "成功：", Toast.LENGTH_LONG).show();
            String temp = "";
            if (share_media.ordinal() == SHARE_MEDIA.WEIXIN.ordinal()) {

                for (String key : map.keySet()) {
                    temp = temp + key + ":" + map.get(key) + "\n";
                    switch (key) {
                        case "openid":
                            break;
                        case "screen_name":

                            continue;
                        case "iconurl":

                            continue;
                        case "province":

                            continue;
                        case "city":

                            continue;
                        case "gender":

                            continue;
                        case "unionid":
                            PostBindUnBindWqParam postBindUnBindWqParam = new PostBindUnBindWqParam();
                            postBindUnBindWqParam.setAction("wx").setOpenid(map.get(key));
                            Presenter.getInstance(getContext()).postBindWq(postBindUnBindWqParam);
                            LocalLog.d(TAG, "绑定当前手机微信");
                            break;
                        default:
                            continue;
                    }
                }

            } else if (share_media.ordinal() == SHARE_MEDIA.QQ.ordinal()) {
                for (String key : map.keySet()) {
                    temp = temp + key + ":" + map.get(key) + "\n";
                    switch (key) {
                        case "openid":
                            break;
                        case "screen_name":

                            continue;
                        case "iconurl":

                            continue;
                        case "province":

                            continue;
                        case "city":

                            continue;
                        case "gender":

                            continue;
                        case "unionid":
                            PostBindUnBindWqParam postBindUnBindWqParam = new PostBindUnBindWqParam();
                            postBindUnBindWqParam.setAction("qq").setOpenid(map.get(key));
                            Presenter.getInstance(getContext()).postBindWq(postBindUnBindWqParam);
                            LocalLog.d(TAG, "绑定当前手机QQ");
                            break;
                        default:
                            continue;
                    }

                    LocalLog.d(TAG, temp);
                }
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(getContext(), "失败：" + throwable.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(getContext(), "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(getContext()).onSaveInstanceState(outState);
    }

    @Override
    public void response(ErrorCode errorCode) {

    }

    @Override
    public void response(PostBindStateResponse postBindStateResponse) {
        if (postBindStateResponse.getError() == 0) {
            if (weChatDes == null) {
                return;
            }
            if (postBindStateResponse.getData().getWx() == 1) {
                weChatDes.setText("已绑定账号");
            } else {
                weChatDes.setText("尚未绑定");
            }
            if (postBindStateResponse.getData().getQq() == 1) {
                qqChatDes.setText("已绑定账号");
            } else {
                qqChatDes.setText("尚未绑定");
            }
            if (postBindStateResponse.getData().getMobile() == 1) {
                phoneChatDes.setText("已绑定账号");
            } else {
                phoneChatDes.setText("尚未绑定");
            }
        }
    }

    @Override
    public void response(PostBindResponse postBindResponse) {

        if (postBindResponse.getError() == 0) {
            Toast.makeText(getContext(), postBindResponse.getMessage(), Toast.LENGTH_SHORT).show();
            switch (postBindResponse.getMessage()) {
                case "解绑成功":
                    if (action != null) {
                        if ("wx".equals(action)) {
                            weChatDes.setText("尚未绑定");
                        } else if ("qq".equals(action)) {
                            qqChatDes.setText("尚未绑定");
                        }
                    }
                    break;
                case "加绑成功":
                    if (action != null) {
                        if ("wx".equals(action)) {
                            weChatDes.setText("已绑定账号");
                        } else if ("qq".equals(action)) {
                            qqChatDes.setText("已绑定账号");
                        }
                    }
                    break;
            }
        } else if (postBindResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
        SocializeUtils.safeCloseDialog(dialog);
    }
}
