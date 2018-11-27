package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyReleaseTaskDetailResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.MyReleaseTaskDetailInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.MyTaskDetailStateAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/25.
 */

public class MyReleaseDetailFragment extends BaseBarStyleTextViewFragment implements MyReleaseTaskDetailInterface {
    private final static String TAG = MyReleaseDetailFragment.class.getSimpleName();
    LinearLayoutManager layoutManager;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.task_name)
    TextView targetName;
    @Bind(R.id.target_money)
    TextView targetMoney;
    @Bind(R.id.title_span)
    LinearLayout titleSpan;
    @Bind(R.id.line_center)
    ImageView lineCenter;
    @Bind(R.id.friend_ico)
    CircleImageView friendIco;
    @Bind(R.id.friend_name)
    TextView friendName;
    @Bind(R.id.pao_bu_no)
    TextView paoBuNo;
    @Bind(R.id.release_live_bg)
    RelativeLayout releaseLiveBg;
    @Bind(R.id.button_1)
    Button button1;
    @Bind(R.id.current_step)
    TextView currentStep;
    @Bind(R.id.man_run_ico)
    ImageView manRunIco;
    @Bind(R.id.step_des_run)
    TextView stepDesRun;
    @Bind(R.id.process_run)
    ProgressBar processRun;
    @Bind(R.id.button_2)
    Button button2;
    @Bind(R.id.task_tells)
    TextView taskTells;
    @Bind(R.id.task_all_days)
    TextView taskAllDays;
    @Bind(R.id.target_step_des)
    TextView targetStepDes;
    @Bind(R.id.gift_money)
    TextView giftMoney;
    @Bind(R.id.strat_time)
    TextView stratTime;
    @Bind(R.id.end_time)
    TextView endTime;
    @Bind(R.id.task_desc)
    TextView taskDesc;
    @Bind(R.id.task_ruls_detail)
    TextView taskRulsDetail;
    @Bind(R.id.line1)
    ImageView line1;
    @Bind(R.id.task_record)
    TextView taskRecord;
    @Bind(R.id.task_detail_recycler)
    RecyclerView taskDetailRecycler;
    int toUserId = -1;
    private String toName = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.release_detail_fg;
    }

    @Override
    protected String title() {
        return "任务详情";
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
        targetName = (TextView) viewRoot.findViewById(R.id.task_name);
        targetMoney = (TextView) viewRoot.findViewById(R.id.target_money);
        friendIco = (CircleImageView) viewRoot.findViewById(R.id.friend_ico);
        friendName = (TextView) viewRoot.findViewById(R.id.friend_name);
        paoBuNo = (TextView) viewRoot.findViewById(R.id.pao_bu_no);
        manRunIco = (ImageView) viewRoot.findViewById(R.id.man_run_ico);
        stepDesRun = (TextView) viewRoot.findViewById(R.id.step_des_run);
        processRun = (ProgressBar) viewRoot.findViewById(R.id.process_run);
        taskAllDays = (TextView) viewRoot.findViewById(R.id.task_all_days);
        targetStepDes = (TextView) viewRoot.findViewById(R.id.target_step_des);
        giftMoney = (TextView) viewRoot.findViewById(R.id.target_step_des);
        stratTime = (TextView) viewRoot.findViewById(R.id.strat_time);
        endTime = (TextView) viewRoot.findViewById(R.id.end_time);
        taskRulsDetail = (TextView) viewRoot.findViewById(R.id.task_ruls_detail);

        taskDetailRecycler = (RecyclerView) viewRoot.findViewById(R.id.task_detail_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        taskDetailRecycler.setLayoutManager(layoutManager);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            int taskId = intent.getIntExtra("taskid", -1);
            toUserId = intent.getIntExtra("toid", -1);
            toName = intent.getStringExtra("toname");
            if (taskId != -1) {
                LocalLog.d(TAG, "获取 " + taskId + "任务详情");
                Presenter.getInstance(getContext()).getTaskDetail(taskId);
            }
        }
    }

    private void loadFriendData(String toid, final int target) {
        String url = NetApi.ulrNoStep + "?toid=" + toid;
        Presenter.getInstance(getContext()).getPaoBuSimple(url, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded()) {
                    return;
                }
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    String avatar = jsonObj.getString("avatar");
                    int steps = jsonObj.getInt("step");
                    Presenter.getInstance(getContext()).getPlaceErrorImage(friendIco, avatar, R.drawable.default_head_ico, R.drawable.default_head_ico);
                    String uerNo = jsonObj.getString("userno");
                    friendName.setText(toName);
                    paoBuNo.setText("跑步钱进号:" + uerNo);
                    if (steps > target) {
                        steps = target;
                    }
                    processRun.setProgress((int) (steps * 100.0f / target));
                    stepDesRun.setText(String.valueOf(steps) + "/" + String.valueOf(target));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                LocalLog.d(TAG, "获取步数失败");
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
    public void response(ErrorCode errorCode) {
        LocalLog.d(TAG, "ErrorCode() enter");
    }

    @Override
    public void response(MyReleaseTaskDetailResponse myReleaseTaskDetailResponse) {
        if (!isAdded()) {
            return;
        }
        if (myReleaseTaskDetailResponse.getError() == 0) {
            LocalLog.d(TAG, "MyReleaseTaskDetailResponse() enter " + myReleaseTaskDetailResponse.toString());
            targetName.setText(myReleaseTaskDetailResponse.getData().getTask().getTask_name());
            if (myReleaseTaskDetailResponse.getData().getTask().getTrade_way() == 1) {
                String targetMoneyStrFormat = getString(R.string.target_money);
                String targetMoneyStr = String.format(targetMoneyStrFormat, myReleaseTaskDetailResponse.getData().getTask().getReward_amount());
                targetMoney.setText(targetMoneyStr);
                giftMoney.setText("奖励总金额:" + myReleaseTaskDetailResponse.getData().getTask().getAvgmoney() + "元");
            } else {
                String targetMoneyStrFormat = getString(R.string.target_step_dollor);
                String targetMoneyStr = String.format(targetMoneyStrFormat, myReleaseTaskDetailResponse.getData().getTask().getCredit());
                targetMoney.setText(targetMoneyStr);
                giftMoney.setText("奖励总步币:" + myReleaseTaskDetailResponse.getData().getTask().getCredit());
            }
            String targetDayStrFormat = getString(R.string.task_days);
            String targetDayStr = String.format(targetDayStrFormat, myReleaseTaskDetailResponse.getData().getTask().getTask_days());
            taskAllDays.setText(targetDayStr);
            String releaseTargetFormat = getString(R.string.task_step_release);
            String releaseTargetStr = String.format(releaseTargetFormat, myReleaseTaskDetailResponse.getData().getTask().getTarget_step());
            targetStepDes.setText(releaseTargetStr);

            long startT = myReleaseTaskDetailResponse.getData().getTask().getCreate_time();
            String start_timeStr = DateTimeUtil.formatDateTime(startT * 1000);

            long endT = myReleaseTaskDetailResponse.getData().getTask().getEnd_time();
            String end_timeStr = DateTimeUtil.formatDateTime(endT * 1000);
            String dateStartStr = start_timeStr.replace("-", "/");
            String dateEndStr = end_timeStr.replace("-", "/");
            stratTime.setText(dateStartStr);
            endTime.setText(dateEndStr);
            taskRulsDetail.setText(Html.fromHtml(myReleaseTaskDetailResponse.getData().getTask().getTask_desc()));
            if (toUserId != -1) {
                loadFriendData(String.valueOf(toUserId),
                        myReleaseTaskDetailResponse.getData().getTask().getTarget_step());
            }
            taskDetailRecycler.setAdapter(new MyTaskDetailStateAdapter(getContext(), myReleaseTaskDetailResponse.getData().getTask_record()));
        } else if (myReleaseTaskDetailResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }
}
