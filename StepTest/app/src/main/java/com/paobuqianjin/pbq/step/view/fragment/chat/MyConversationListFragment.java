package com.paobuqianjin.pbq.step.view.fragment.chat;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.FriendCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.MessageActivity;
import com.paobuqianjin.pbq.step.view.base.view.DragPointView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.rong.imkit.fragment.ConversationListFragment;

public class MyConversationListFragment extends ConversationListFragment {

    private static final String TAG = MyConversationListFragment.class.getSimpleName();
    ImageView systemMsg;
    DragPointView tvSystemUnread;
    ImageView contentMsg;
    DragPointView tvContentUnread;
    ImageView likeMsg;
    DragPointView tvLikeUnread;
    ImageView cirlceMsg;
    DragPointView tvCircleUnread;

    private final static String LIKE_MESSAGE_ACTION = "com.paobuqianjin.pbq.step.LIKE_MESSAGE_ACTION";
    private final static String CONTENT_MESSAGE_ACTION = "com.paobuqianjin.pbq.step.CONTENT_MESSAGE_ACTION";
    private final static String SYS_MESSAGE_ACTION = "com.paobuqianjin.pbq.step.SYS_MESSAGE_ACTION";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout layoutTop = view.findViewById(R.id.frame_top_bar);
        layoutTop.setVisibility(View.VISIBLE);
        View topBarView = LayoutInflater.from(getActivity()).inflate(R.layout.conversation_top_bar, null);
        ((TextView) topBarView.findViewById(R.id.bar_title)).setText("会话");
        systemMsg = (ImageView) topBarView.findViewById(R.id.system_msg);
        systemMsg.setOnClickListener(onClickListener);
        tvSystemUnread = (DragPointView) topBarView.findViewById(R.id.tv_system_unread);
        contentMsg = (ImageView) topBarView.findViewById(R.id.content_msg);
        contentMsg.setOnClickListener(onClickListener);
        tvContentUnread = (DragPointView) topBarView.findViewById(R.id.tv_content_unread);
        likeMsg = (ImageView) topBarView.findViewById(R.id.like_msg);
        likeMsg.setOnClickListener(onClickListener);
        tvLikeUnread = (DragPointView) topBarView.findViewById(R.id.tv_like_unread);
        cirlceMsg = (ImageView) topBarView.findViewById(R.id.cirlce_msg);
        cirlceMsg.setOnClickListener(onClickListener);
        tvCircleUnread = (DragPointView) topBarView.findViewById(R.id.tv_circle_unread);
//        ViewGroup.LayoutParams params = topBarView.getLayoutParams();
//        params.height = Utils.px2dip(getActivity(), 64f);
        layoutTop.addView(topBarView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.bind(this, view);
        return view;
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cirlce_msg:
                    LocalLog.d(TAG, "社群");
                    startActivity(new Intent(getActivity(), FriendCircleActivity.class));
                    break;
                case R.id.content_msg:
                    LocalLog.d(TAG, "评论");
                    startActivity(new Intent().setAction(CONTENT_MESSAGE_ACTION).setClass(getContext(), MessageActivity.class));
                    break;
                case R.id.like_msg:
                    LocalLog.d(TAG, "点赞");
                    startActivity(new Intent().setAction(LIKE_MESSAGE_ACTION).setClass(getContext(), MessageActivity.class));
                    break;
                case R.id.system_msg:
                    LocalLog.d(TAG, "系统消息");
                    startActivity(new Intent().setAction(SYS_MESSAGE_ACTION).setClass(getContext(), MessageActivity.class));
                    break;
            }
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
