package com.paobuqianjin.pbq.step.view.fragment.honor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendStepRankDayResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.FriendHonorInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.FriendStepDanActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.dan.HonorAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by pbq on 2018/1/9.
 */

public class PersonHonorFragment extends BaseFragment implements FriendHonorInterface {
    private final static String TAG = PersonHonorFragment.class.getSimpleName();

    FriendStepRankDayResponse friendStepRankDayResponse;
    LinearLayoutManager layoutManager;
    @Bind(R.id.bg)
    FrameLayout bg;
    @Bind(R.id.time_month_day)
    TextView timeMonthDay;
    @Bind(R.id.rank_honor)
    TextView rankHonor;
    @Bind(R.id.rank_des)
    TextView rankDes;
    @Bind(R.id.step_num)
    TextView stepNum;
    @Bind(R.id.step_des)
    TextView stepDes;
    @Bind(R.id.rank_data_span)
    RelativeLayout rankDataSpan;
    @Bind(R.id.rank_recycler_view)
    RecyclerView rankRecyclerView;
    @Bind(R.id.rank_detail_des)
    TextView rankDetailDes;
    @Bind(R.id.rank_master_span)
    RelativeLayout rankMasterSpan;
    private final static String ACTION_FRIEND_HONOR = "com.paobuqianjin.pbq.ACTION_FRIEND_HONOR";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(context).attachUiInterface(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.person_honor_fg;
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
        rankHonor = (TextView) viewRoot.findViewById(R.id.rank_honor);
        rankDes = (TextView) viewRoot.findViewById(R.id.rank_des);
        stepNum = (TextView) viewRoot.findViewById(R.id.step_num);
        stepDes = (TextView) viewRoot.findViewById(R.id.step_des);
        rankRecyclerView = (RecyclerView) viewRoot.findViewById(R.id.rank_recycler_view);
        timeMonthDay = (TextView) viewRoot.findViewById(R.id.time_month_day);
        String dateTime = DateTimeUtil.getLocalTime();
        LocalLog.d(TAG, "dateTime = " + dateTime);
        timeMonthDay.setText(dateTime);
        layoutManager = new LinearLayoutManager(getContext());
        rankRecyclerView.setLayoutManager(layoutManager);
        Presenter.getInstance(getContext()).getFriendHonor(1, 10);
        rankMasterSpan = (RelativeLayout) viewRoot.findViewById(R.id.rank_master_span);

        rankMasterSpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(ACTION_FRIEND_HONOR);
                intent.setClass(getContext(), FriendStepDanActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(FriendStepRankDayResponse friendStepRankDayResponse) {
        LocalLog.d(TAG, "FriendStepRankDayResponse() enter  " + friendStepRankDayResponse.toString());
        if (friendStepRankDayResponse.getError() == 0) {
            this.friendStepRankDayResponse = friendStepRankDayResponse;
            if (rankHonor != null && stepNum != null && rankRecyclerView != null) {
                rankHonor.setText(String.valueOf(friendStepRankDayResponse.getData().getData().getMydata().getRank()));
                stepNum.setText(String.valueOf(friendStepRankDayResponse.getData().getData().getMydata().getStep_number()));
                rankRecyclerView.setAdapter(new HonorAdapter(getContext(), friendStepRankDayResponse.getData().getData().getMember()));
            }
        } else if (friendStepRankDayResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }
}
