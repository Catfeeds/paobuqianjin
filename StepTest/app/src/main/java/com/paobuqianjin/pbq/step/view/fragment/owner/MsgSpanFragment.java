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
        Presenter.getInstance(getContext()).getMessage(2);
        Presenter.getInstance(getContext()).getMessage(3);
        Presenter.getInstance(getContext()).getMessage(4);
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
                intent.setClass(getContext(), UserCenterContentActivity.class);
                startActivity(intent);
                break;
            case R.id.like_span:
                LocalLog.d(TAG, "点赞");
                intent.setClass(getContext(), UserCenterContentActivity.class);
                startActivity(intent);
                break;
            case R.id.system_span:
                LocalLog.d(TAG, "系统消息");
                intent.setClass(getContext(), SystemMsgActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void response(MessageAtResponse messageAtResponse) {

    }

    @Override
    public void response(MessageLikeResponse messageLikeResponse) {

    }

    @Override
    public void response(MessageSystemResponse messageSystemResponse) {

    }

    @Override
    public void response(MessageContentResponse messageContentResponse) {

    }

}
