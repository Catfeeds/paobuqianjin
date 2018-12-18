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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.FriendCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.MessageActivity;

import io.rong.imkit.fragment.ConversationListFragment;

public class MyConversationListFragment extends ConversationListFragment implements View.OnClickListener {

    private static final String TAG = MyConversationListFragment.class.getSimpleName();
    private ImageView bar_right_drawable;
    private View popCircleOpBar;
    private PopupWindow popupOpWindowTop;
    private TranslateAnimation animationCircleType;
    private final static String LIKE_MESSAGE_ACTION = "com.paobuqianjin.pbq.step.LIKE_MESSAGE_ACTION";
    private final static String CONTENT_MESSAGE_ACTION = "com.paobuqianjin.pbq.step.CONTENT_MESSAGE_ACTION";
    private final static String SYS_MESSAGE_ACTION = "com.paobuqianjin.pbq.step.SYS_MESSAGE_ACTION";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout layoutTop = view.findViewById(R.id.frame_top_bar);
        layoutTop.setVisibility(View.VISIBLE);
        View topBarView = LayoutInflater.from(getActivity()).inflate(R.layout.bar_no_return, null);
        ((TextView) topBarView.findViewById(R.id.bar_title)).setText("会话");
        bar_right_drawable = topBarView.findViewById(R.id.bar_right_drawable);
        bar_right_drawable.setVisibility(View.VISIBLE);
        bar_right_drawable.setImageResource(R.drawable.conversion_more);
        bar_right_drawable.setOnClickListener(this);
//        ViewGroup.LayoutParams params = topBarView.getLayoutParams();
//        params.height = Utils.px2dip(getActivity(), 64f);
        layoutTop.addView(topBarView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        return view;
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.circle_text_span:
                    LocalLog.d(TAG, "社群");
                    startActivity(new Intent(getActivity(), FriendCircleActivity.class));
                    if (popupOpWindowTop != null) {
                        popupOpWindowTop.dismiss();
                    }
                    break;
                case R.id.content_text_span:
                    LocalLog.d(TAG, "评论");
                    startActivity(new Intent().setAction(CONTENT_MESSAGE_ACTION).setClass(getContext(), MessageActivity.class));
                    if (popupOpWindowTop != null) {
                        popupOpWindowTop.dismiss();
                    }
                    break;
                case R.id.zan_text_span:
                    LocalLog.d(TAG, "点赞");
                    startActivity(new Intent().setAction(LIKE_MESSAGE_ACTION).setClass(getContext(), MessageActivity.class));
                    if (popupOpWindowTop != null) {
                        popupOpWindowTop.dismiss();
                    }
                    break;
                case R.id.mananger_text_span:
                    LocalLog.d(TAG, "系统消息");
                    startActivity(new Intent().setAction(SYS_MESSAGE_ACTION).setClass(getContext(), MessageActivity.class));
                    if (popupOpWindowTop != null) {
                        popupOpWindowTop.dismiss();
                    }
                    break;
            }
        }
    };

    private void popActionSelect() {
        popCircleOpBar = View.inflate(getContext(), R.layout.top_conversion_linear, null);
        popupOpWindowTop = new PopupWindow(popCircleOpBar,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popCircleOpBar.findViewById(R.id.circle_text_span).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.content_text_span).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.zan_text_span).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.mananger_text_span).setOnClickListener(onClickListener);

        popupOpWindowTop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupOpWindowTop = null;
            }
        });

        popupOpWindowTop.setFocusable(true);
        popupOpWindowTop.setOutsideTouchable(true);
        popupOpWindowTop.setBackgroundDrawable(new BitmapDrawable());

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupOpWindowTop.showAsDropDown(bar_right_drawable, 20, -20);
        popCircleOpBar.startAnimation(animationCircleType);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (popupOpWindowTop != null) {
            popupOpWindowTop.dismiss();
            popupOpWindowTop = null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_right_drawable:
                LocalLog.d(TAG, "消息");
                popActionSelect();
                break;
        }
    }
}
