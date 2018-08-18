package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/7/10.
 */

public class StepOrFriendFragment extends BaseFragment {
    @Bind(R.id.step_zero)
    TextView stepZero;
    @Bind(R.id.help_pic_one)
    ImageView helpPicOne;
    private final static String ACTION_STEP_HELP = "com.paobuqianjin.pbq.step.STEP_HELP";
    private final static String ACTION_FRIEND_HELP = "com.paobuqianjin.pbq.step.FRIEND_HELP";
    @Bind(R.id.add_friend_des)
    TextView addFriendDes;
    @Bind(R.id.step_zero_des)
    TextView stepZeroDes;

    @Override
    protected int getLayoutResId() {
        return R.layout.question_step_layout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initView(View viewRoot) {
        stepZero = (TextView) viewRoot.findViewById(R.id.step_zero);
        addFriendDes = (TextView) viewRoot.findViewById(R.id.add_friend_des);
        helpPicOne = (ImageView) viewRoot.findViewById(R.id.help_pic_one);
        stepZeroDes = (TextView) viewRoot.findViewById(R.id.step_zero_des);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (ACTION_STEP_HELP.equals(intent.getAction())) {
                stepZero.setText(R.string.no_step);
                Presenter.getInstance(getContext()).getPlaceErrorImage(helpPicOne, null, R.drawable.step_help, R.drawable.step_help);
                stepZeroDes.setText(R.string.android_step);
                stepZeroDes.setVisibility(View.VISIBLE);
                addFriendDes.setText(R.string.step_help_des);
                addFriendDes.setVisibility(View.VISIBLE);
            } else if (ACTION_FRIEND_HELP.equals(intent.getAction())) {
                stepZero.setText(R.string.friend_help);
                Presenter.getInstance(getContext()).getPlaceErrorImage(helpPicOne, null, R.drawable.friend_help, R.drawable.friend_help);
                addFriendDes.setText(R.string.friend_help_des);
                addFriendDes.setVisibility(View.VISIBLE);
            }
        }

    }
}
