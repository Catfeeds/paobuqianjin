package com.paobuqianjin.pbq.step.view.fragment.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.MessageActivity;

import io.rong.imkit.fragment.ConversationListFragment;

public class MyConversationListFragment extends ConversationListFragment implements View.OnClickListener{

    private static final String TAG = MyConversationListFragment.class.getSimpleName();
    private ImageView bar_right_drawable;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout layoutTop = view.findViewById(R.id.frame_top_bar);
        layoutTop.setVisibility(View.VISIBLE);
        View topBarView = LayoutInflater.from(getActivity()).inflate(R.layout.bar_no_return, null);
        ((TextView)topBarView.findViewById(R.id.bar_title)).setText("会话");
        bar_right_drawable = topBarView.findViewById(R.id.bar_right_drawable);
        bar_right_drawable.setVisibility(View.VISIBLE);
        bar_right_drawable.setImageResource(R.drawable.message_left);
        bar_right_drawable.setOnClickListener(this);
//        ViewGroup.LayoutParams params = topBarView.getLayoutParams();
//        params.height = Utils.px2dip(getActivity(), 64f);
        layoutTop.addView(topBarView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= super.onCreateView(inflater, container, savedInstanceState);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_right_drawable:
                LocalLog.d(TAG, "消息");
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
        }
    }
}
