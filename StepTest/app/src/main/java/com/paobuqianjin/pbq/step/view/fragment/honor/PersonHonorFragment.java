package com.paobuqianjin.pbq.step.view.fragment.honor;

import android.content.Context;
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
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendStepRankDayResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.FriendHonorInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.dan.HonorAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by pbq on 2018/1/9.
 */

public class PersonHonorFragment extends BaseFragment implements FriendHonorInterface {
    @Bind(R.id.bg)
    FrameLayout bg;
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
    @Bind(R.id.rank_master_span)
    RelativeLayout rankMasterSpan;
    @Bind(R.id.rank_detail_des)
    TextView rankDetailDes;
    FriendStepRankDayResponse friendStepRankDayResponse;
    @Bind(R.id.time_month_day)
    TextView timeMonthDay;
    LinearLayoutManager layoutManager;

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
        timeMonthDay.setText(DateTimeUtil.getLocalTime());
        layoutManager = new LinearLayoutManager(getContext());
        rankRecyclerView.setLayoutManager(layoutManager);
        Presenter.getInstance(getContext()).getFriendHonor(1, 10);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(FriendStepRankDayResponse friendStepRankDayResponse) {
        LocalLog.d(TAG, "FriendStepRankDayResponse() enter  " + friendStepRankDayResponse.toString() );
        if (friendStepRankDayResponse.getError() == 0) {
            this.friendStepRankDayResponse = friendStepRankDayResponse;
            rankHonor.setText(String.valueOf(friendStepRankDayResponse.getData().getData().getMydata().getMyranking()));
            stepNum.setText(String.valueOf(friendStepRankDayResponse.getData().getData().getMydata().getStep_number()));
            rankRecyclerView.setAdapter(new HonorAdapter(getContext(), friendStepRankDayResponse.getData().getData().getMember()));
        }
    }
}
