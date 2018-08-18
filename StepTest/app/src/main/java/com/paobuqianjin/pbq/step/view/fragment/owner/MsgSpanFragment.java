package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.MessageContentBundleData;
import com.paobuqianjin.pbq.step.data.bean.bundle.MessageLikeBundleData;
import com.paobuqianjin.pbq.step.data.bean.bundle.MessageSystemBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageAtResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageContentResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageLikeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageSystemResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.MessageInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.SystemMsgActivity;
import com.paobuqianjin.pbq.step.view.activity.UserCenterContentActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/17.
 */

public class MsgSpanFragment extends BaseBarStyleTextViewFragment implements MessageInterface {
    private final static String TAG = MsgSpanFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.content_icon)
    ImageView contentIcon;
    @Bind(R.id.content)
    TextView content;
    @Bind(R.id.content_des_msg)
    TextView contentDesMsg;
    @Bind(R.id.message_span)
    RelativeLayout messageSpan;
    @Bind(R.id.like_icon)
    ImageView likeIcon;
    @Bind(R.id.like)
    TextView like;
    @Bind(R.id.like_des_msg)
    TextView likeDesMsg;
    @Bind(R.id.like_span)
    RelativeLayout likeSpan;
    @Bind(R.id.system_msg_icon)
    ImageView systemMsgIcon;
    @Bind(R.id.system_msg_text)
    TextView systemMsgText;
    @Bind(R.id.system_msg_des_msg)
    TextView systemMsgDesMsg;
    @Bind(R.id.system_span)
    RelativeLayout systemSpan;
    MessageContentResponse messageContentResponse;
    MessageLikeResponse messageLikeResponse;
    MessageSystemResponse messageSystemResponse;
    private final static String LIKE_MESSAGE_ACTION = "com.paobuqianjin.pbq.step.LIKE_MESSAGE_ACTION";
    private final static String CONTENT_MESSAGE_ACTION = "com.paobuqianjin.pbq.step.CONTENT_MESSAGE_ACTION";
    private final static String SYS_MESSAGE_ACTION = "com.paobuqianjin.pbq.step.SYS_MESSAGE_ACTION";

    @Override

    protected String title() {
        return "消息";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.message_kan_ban_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        Presenter.getInstance(getContext()).getMessage(2, 1, 10);
        Presenter.getInstance(getContext()).getMessage(3, 1, 10);
        Presenter.getInstance(getContext()).getMessage(4, 1, 10);
        contentDesMsg = (TextView) viewRoot.findViewById(R.id.content_des_msg);
        likeDesMsg = (TextView) viewRoot.findViewById(R.id.like_des_msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @OnClick({R.id.message_span, R.id.like_span, R.id.system_span})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.message_span:
                LocalLog.d(TAG, "评论");
                //TODO null判断
                MessageContentBundleData messageContentBundleData;
                if (messageLikeResponse == null || messageLikeResponse.getData() == null) {
                    messageContentBundleData = null;
                } else {
                    if (messageContentResponse == null) return;
                    messageContentBundleData = new MessageContentBundleData((ArrayList<MessageContentResponse.DataBeanX.DataBean>) messageContentResponse.getData().getData());
                }

                intent.putExtra(getActivity().getPackageName(), messageContentBundleData);
                intent.setAction(CONTENT_MESSAGE_ACTION);
                intent.setClass(getContext(), UserCenterContentActivity.class);
                startActivity(intent);
                break;
            case R.id.like_span:
                LocalLog.d(TAG, "点赞");
                MessageLikeBundleData messageLikeBundleData;
                if (messageLikeResponse == null || messageLikeResponse.getData() == null) {
                    messageLikeBundleData = null;
                } else {
                    messageLikeBundleData = new MessageLikeBundleData((ArrayList<MessageLikeResponse.DataBeanX.DataBean>) messageLikeResponse.getData().getData());
                }

                intent.putExtra(getActivity().getPackageName(), messageLikeBundleData);
                intent.setAction(LIKE_MESSAGE_ACTION);
                intent.setClass(getContext(), UserCenterContentActivity.class);
                startActivity(intent);
                break;
            case R.id.system_span:
                LocalLog.d(TAG, "系统消息");
                MessageSystemBundleData messageSystemBundleData;
                if (messageSystemResponse == null || messageSystemResponse.getData() == null) {
                    messageSystemBundleData = null;
                } else {
                    messageSystemBundleData = new MessageSystemBundleData((ArrayList<MessageSystemResponse.DataBeanX.DataBean>) messageSystemResponse.getData().getData());
                }

                intent.putExtra(getActivity().getPackageName(), messageSystemBundleData);
                intent.setAction(SYS_MESSAGE_ACTION);
                intent.setClass(getContext(), UserCenterContentActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void response(MessageAtResponse messageAtResponse) {
        if (messageAtResponse.getError() == 0) {

        }
    }

    @Override
    public void response(MessageLikeResponse messageLikeResponse) {
        if (!isAdded()) {
            return;
        }
        if (messageLikeResponse.getError() == 0) {
            this.messageLikeResponse = messageLikeResponse;
            if (likeDesMsg == null) {
                return;
            }
            if (messageLikeResponse.getData() != null && messageLikeResponse.getData().getPagenation() != null) {
                likeDesMsg.setText(String.valueOf(messageLikeResponse.getData().getPagenation().getTotalCount()) + "个赞");
            }else{
                likeDesMsg.setText("0个赞");
            }
        } else if (messageLikeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(MessageSystemResponse messageSystemResponse) {
        if (!isAdded()) {
            return;
        }
        if (messageSystemResponse.getError() == 0) {
            this.messageSystemResponse = messageSystemResponse;
            if (systemMsgDesMsg == null) {
                return;
            }
            if (messageSystemResponse.getData() != null && messageSystemResponse.getData().getPagenation() != null) {
                systemMsgDesMsg.setText(String.valueOf(messageSystemResponse.getData().getPagenation().getTotalCount()) + "条消息");
            } else {
                systemMsgDesMsg.setText("0条消息");
            }
        } else if (messageSystemResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(MessageContentResponse messageContentResponse) {
        if (!isAdded()) {
            return;
        }
        if (messageContentResponse.getError() == 0) {
            this.messageContentResponse = messageContentResponse;
            if (contentDesMsg == null) {
                return;
            }
            if ((messageContentResponse.getData() != null && messageContentResponse.getData().getPagenation() != null)) {
                contentDesMsg.setText(String.valueOf(messageContentResponse.getData().getPagenation().getTotalCount()) + "条消息");
            }else{
                contentDesMsg.setText("0条消息");
            }
        } else if (messageContentResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (!isAdded()) {
            return;
        }
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }
}
