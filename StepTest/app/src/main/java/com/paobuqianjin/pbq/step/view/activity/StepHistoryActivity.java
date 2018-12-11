package com.paobuqianjin.pbq.step.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.LineChartManager;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepHisResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/12/10.
 */

public class StepHistoryActivity extends BaseBarActivity {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.time_wait)
    TextView timeWait;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.switch_doll)
    ImageView switchDoll;
    @Bind(R.id.week_step)
    TextView weekStep;
    @Bind(R.id.month_step)
    TextView monthStep;
    @Bind(R.id.per_step)
    TextView perStep;
    @Bind(R.id.rank_icon)
    ImageView rankIcon;
    @Bind(R.id.your_dan)
    TextView yourDan;
    @Bind(R.id.head_icon_user)
    CircleImageView headIconUser;
    @Bind(R.id.user_name_rank)
    TextView userNameRank;
    @Bind(R.id.step_num_my)
    TextView stepNumMy;
    @Bind(R.id.your_dan_layer)
    RelativeLayout yourDanLayer;
    @Bind(R.id.line_dan)
    ImageView lineDan;
    @Bind(R.id.dan_detail_recycler)
    SwipeMenuRecyclerView danDetailRecycler;
    List<StepHisResponse.DataBean.StepListBean> weekList = new ArrayList<>();
    List<StepHisResponse.DataBean.StepListBean> monthList = new ArrayList<>();
    @Bind(R.id.line_chart)
    LineChart lineChart;
    private LineChartManager lineChartManager1;
    private boolean isShowMonth = false;

    @Override
    protected String title() {
        return "历史记录";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_history_activity_layout);
        ButterKnife.bind(this);
        lineChartManager1 = new LineChartManager(lineChart, this);
        //设置曲线填充色 以及 MarkerView
        Drawable drawable = getResources().getDrawable(R.drawable.fade_blue);
        lineChartManager1.setChartFillDrawable(drawable);
        lineChartManager1.setMarkerView(StepHistoryActivity.this);
        getWeekMonthStep("1");
        getWeekMonthStep("2");
    }

    /*@desc
    *@function
    *@param 1周，2月
    *@return
    */
    private void getWeekMonthStep(final String circle) {
        Map<String, String> param = new HashMap<>();
        param.put("circle", circle);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlStepLineChart, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    StepHisResponse stepHisResponse = new Gson().fromJson(s, StepHisResponse.class);
                    if (stepHisResponse.getData() != null && stepHisResponse.getData().getStep_list() != null
                            && stepHisResponse.getData().getStep_list().size() > 0) {
                        if ("1".equals(circle)) {
                            weekList.addAll(stepHisResponse.getData().getStep_list());
                            lineChartManager1.showLineChart(weekList, "步数", getResources().getColor(R.color.blue));
                            lineChart.invalidate();
                        } else if ("2".equals(circle)) {
                            monthList.addAll(stepHisResponse.getData().getStep_list());
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    @OnClick(R.id.switch_doll)
    public void onClick() {
        if (!isShowMonth) {
            isShowMonth = true;
            //显示月数据
            if (monthList.size() > 0) {
                switchDoll.setImageResource(R.drawable.month_switch);
                weekStep.setVisibility(View.INVISIBLE);
                monthStep.setVisibility(View.VISIBLE);
                lineChartManager1.showLineChart(monthList, "步数", getResources().getColor(R.color.blue));
                lineChart.invalidate();
            }
        } else {
            isShowMonth = false;
            //显示周数据
            if (weekList.size() > 0) {
                switchDoll.setImageResource(R.drawable.week_switch);
                weekStep.setVisibility(View.VISIBLE);
                monthStep.setVisibility(View.INVISIBLE);
                lineChartManager1.showLineChart(weekList, "步数", getResources().getColor(R.color.blue));
                lineChart.invalidate();
            }
        }
    }
}
