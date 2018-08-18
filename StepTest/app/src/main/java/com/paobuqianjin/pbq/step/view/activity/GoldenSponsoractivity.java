package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.SponsorDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/7/14.
 */

public class GoldenSponsoractivity extends BaseBarActivity {
    private final static String TAG = GoldenSponsoractivity.class.getSimpleName();
    private final static String ACTION_GOLDEN_VIP = "com.paobuqianjin.pbq.step.VIP_GOLDEN_ACTION";
    private final static int PAY_GOLDEN_VIP = 1;
    boolean bGvip = false;
    @Bind(R.id.golden_des)
    TextView goldenDes;
    @Bind(R.id.apy_golden_free)
    Button apyGoldenFree;
    @Bind(R.id.apy_golden)
    Button apyGolden;
    SponsorDialog sponsorApplyDialog, sponsorDialogSuccess;

    @Override
    protected String title() {
        return "金牌会员中心";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.golden_sponor_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        apyGolden = (Button) findViewById(R.id.apy_golden);
        goldenDes = (TextView) findViewById(R.id.golden_des);
        String partDesA = getString(R.string.golden_des_top);
        String partDesB = getString(R.string.golden_des_top_part);
        SpannableString spannableString = new SpannableString(partDesA + partDesB);
        spannableString.setSpan(new AbsoluteSizeSpan(14, true), 0, partDesA.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TypefaceSpan("default"), 0, partDesA.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.color_161727)),
                0, (partDesA + partDesB).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new AbsoluteSizeSpan(17, true), partDesA.length(), (partDesA + partDesB).length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TypefaceSpan("bold"), partDesA.length(), (partDesA + partDesB).length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        goldenDes.setText(spannableString);
        checkGvip();
    }

    private void getUserInfo() {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlUser + FlagPreference.getUid(this), null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                    if (userInfoResponse.getData().getGvip() == 0) {
                        bGvip = false;
                    } else if (userInfoResponse.getData().getGvip() == 1) {
                        bGvip = true;
                    }
                } catch (Exception j) {
                    j.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(GoldenSponsoractivity.this, errorBean.getMessage());
                }
            }
        });
    }

    private void checkGvip() {
        getUserInfo();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAY_GOLDEN_VIP) {
            if (resultCode == Activity.RESULT_OK) {
                LocalLog.d(TAG, "金牌会员开通成功!");
                bGvip = true;
            }
        }
    }

    private void applyGolden() {
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlTryGvip, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                bGvip = true;
                if (sponsorDialogSuccess == null) {
                    sponsorDialogSuccess = new SponsorDialog(GoldenSponsoractivity.this);
                    sponsorDialogSuccess.setTitle("申请成功");
                    sponsorDialogSuccess.setMessage(getString(R.string.golden_dialog_message_b));

                    sponsorDialogSuccess.setNoOnclickListener("取消", new SponsorDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            sponsorDialogSuccess.dismiss();
                        }
                    });

                    sponsorDialogSuccess.setYesOnclickListener("去发消费红包", new SponsorDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            sponsorDialogSuccess.dismiss();
                            LocalLog.d(TAG, "去发消费红包");
                            Intent intent = new Intent(GoldenSponsoractivity.this, AddConsumptiveRedBagActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                if (!isFinishing() && !sponsorApplyDialog.isShowing())
                    sponsorDialogSuccess.show();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    @OnClick({R.id.apy_golden_free, R.id.apy_golden})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.apy_golden_free:
                LocalLog.d(TAG, "申请试用");
                if (bGvip) {
                    PaoToastUtils.showLongToast(GoldenSponsoractivity.this, "您已经是商家金牌会员");
                    return;
                }
                if (sponsorApplyDialog == null) {
                    sponsorApplyDialog = new SponsorDialog(this);
                    sponsorApplyDialog.setTitle("申请免费试用");
                    sponsorApplyDialog.setMessage(getString(R.string.golden_dialog_message_a));
                    sponsorApplyDialog.setNoOnclickListener("取消", new SponsorDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            sponsorApplyDialog.dismiss();
                        }
                    });

                    sponsorApplyDialog.setYesOnclickListener("试用", new SponsorDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            sponsorApplyDialog.dismiss();
                            LocalLog.d(TAG, "申请试用");
                            applyGolden();

                        }
                    });
                }
                if (!sponsorApplyDialog.isShowing())
                    sponsorApplyDialog.show();
                break;
            case R.id.apy_golden:
                LocalLog.d(TAG, "申请商家金牌会员");
                if (!bGvip) {
                    Intent intent = new Intent();
                    intent.setAction(ACTION_GOLDEN_VIP);
                    intent.setClass(GoldenSponsoractivity.this, PaoBuPayActivity.class);
                    startActivityForResult(intent, PAY_GOLDEN_VIP);
                } else {
                    PaoToastUtils.showLongToast(GoldenSponsoractivity.this, "您已经是商家金牌会员");
                }
                break;
        }
    }
}
