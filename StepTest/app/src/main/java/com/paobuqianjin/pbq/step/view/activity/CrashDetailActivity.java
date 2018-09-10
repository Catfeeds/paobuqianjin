package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddDeleteFollowResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CrashInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowStatusResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserCenterResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/9/3.
 */

public class CrashDetailActivity extends BaseBarActivity {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.crash_to)
    TextView crashTo;
    @Bind(R.id.crash_money)
    TextView crashMoney;
    @Bind(R.id.top_pop)
    LinearLayout topPop;
    @Bind(R.id.crash_total)
    TextView crashTotal;
    @Bind(R.id.crash_diss)
    TextView crashDiss;
    @Bind(R.id.time_crash)
    TextView timeCrash;
    @Bind(R.id.bank_to)
    TextView bankTo;
    @Bind(R.id.text_content)
    TextView textContent;
    @Bind(R.id.crash_status_str)
    TextView crashStatusStr;
    @Bind(R.id.attion)
    Button attion;
    @Bind(R.id.time_arrv)
    TextView timeArrv;
    @Bind(R.id.tel)
    TextView tel;

    @Override
    protected String title() {
        return "提现明细";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crash_detail_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        crashStatusStr = (TextView) findViewById(R.id.crash_status_str);
        crashTotal = (TextView) findViewById(R.id.crash_total);
        crashDiss = (TextView) findViewById(R.id.crash_diss);
        timeCrash = (TextView) findViewById(R.id.time_crash);
        bankTo = (TextView) findViewById(R.id.bank_to);
        attion = (Button) findViewById(R.id.attion);
        crashTo = (TextView) findViewById(R.id.crash_to);
        crashMoney = (TextView) findViewById(R.id.crash_money);
        timeArrv = (TextView) findViewById(R.id.time_arrv);
        tel = (TextView) findViewById(R.id.tel);
        if (intent != null) {
            String withdrawId = intent.getStringExtra("withdrawid");
            if (!TextUtils.isEmpty(withdrawId)) {
                crashDetail(withdrawId);
            }
        }

    }

    private void checkFollow(final String followid) {
        Map<String, String> param = new HashMap<>();
        param.put("userid", String.valueOf(Presenter.getInstance(this).getId()));
        param.put("followid", followid);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlFollowStatus, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    FollowStatusResponse followStatusResponse = new Gson().fromJson(s, FollowStatusResponse.class);
                    if (attion != null) {
                        if (followStatusResponse.getData().getIs_follow() == 0) {
                            attion.setText("关注");
                            attion.setEnabled(true);
                            setOnClick(followid);

                        } else if (followStatusResponse.getData().getIs_follow() == 1) {
                            attion.setText("已关注");
                            attion.setEnabled(true);
                            setOnClick(followid);
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

    private void setOnClick(final String followid) {
        attion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> param = new HashMap<>();
                param.put("userid", String.valueOf(Presenter.getInstance(CrashDetailActivity.this)));
                param.put("followid", followid);
                Presenter.getInstance(CrashDetailActivity.this).postPaoBuSimple(NetApi.urlUserFollow, param, new PaoCallBack() {
                    @Override
                    protected void onSuc(String s) {
                        try {
                            AddDeleteFollowResponse addDeleteFollowResponse = new AddDeleteFollowResponse();
                            if (addDeleteFollowResponse.getMessage().equals("取消关注成功")) {
                                attion.setText("关注");
                                attion.setTextColor(ContextCompat.getColor(CrashDetailActivity.this, R.color.color_6c71c4));
                            } else {
                                attion.setText("已关注");
                                attion.setTextColor(ContextCompat.getColor(CrashDetailActivity.this, R.color.color_646464));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                        if (errorBean != null) {
                            PaoToastUtils.showLongToast(CrashDetailActivity.this, errorBean.getMessage());
                        }
                    }
                });
            }
        });
    }

    private void crashDetail(String withdrawId) {
        Map<String, String> param = new HashMap<>();
        param.put("withdraw_id", withdrawId);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlCrashDetail, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    final CrashInfoResponse crashInfoResponse = new Gson().fromJson(s, CrashInfoResponse.class);
                    crashTo.setText("零钱提现-到" + crashInfoResponse.getData().getBankname() + "(" + crashInfoResponse.getData().getBank_card() + ")");
                    crashMoney.setText(crashInfoResponse.getData().getAmount());
                    if (crashInfoResponse.getData().getWithdraw_status() == 0) {
                        crashStatusStr.setText("申请中");
                    } else if (crashInfoResponse.getData().getWithdraw_status() == 1) {
                        crashStatusStr.setText("成功");
                    } else if (crashInfoResponse.getData().getWithdraw_status() == 2) {
                        crashStatusStr.setText("申请失败");
                    } else if (crashInfoResponse.getData().getWithdraw_status() == 3) {
                        crashStatusStr.setText("失败");
                    }
                    crashTotal.setText(crashInfoResponse.getData().getAmount());
                    crashDiss.setText(crashInfoResponse.getData().getFeemoney());
                    long time = crashInfoResponse.getData().getCreate_time();
                    String timeStr = DateTimeUtil.formatDateTime(time * 1000, DateTimeUtil.DF_YYYY_MM_DD_HH_MM_SS);
                    timeCrash.setText(timeStr);
                    timeArrv.setText(crashInfoResponse.getData().getUpdate_time());
                    bankTo.setText(crashInfoResponse.getData().getBankname());
                    tel.setText("有问题请联系公司客服电话：" + String.valueOf(crashInfoResponse.getData().getServicer_phone()));
                    tel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent1 = new Intent(Intent.ACTION_DIAL);
                            intent1.setData(Uri.parse("tel:" + crashInfoResponse.getData().getServicer_phone()));
                            startActivity(intent1);
                        }
                    });
                    checkFollow(crashInfoResponse.getData().getServicer_id());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(CrashDetailActivity.this, errorBean.getMessage());
                }
            }
        });
    }
}
