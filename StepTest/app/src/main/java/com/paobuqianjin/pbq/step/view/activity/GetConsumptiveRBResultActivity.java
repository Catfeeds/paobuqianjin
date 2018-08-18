package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.VoucherDetailResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetConsumptiveRBResultActivity extends BaseBarActivity {

    @Bind(R.id.iv_title_img)
    ImageView ivTitleImg;
    @Bind(R.id.linear_top_result)
    LinearLayout linearTopResult;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_money_limit)
    TextView tvMoneyLimit;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_enable_date)
    TextView tvEnableDate;
    @Bind(R.id.iv_status)
    ImageView ivStatus;
    @Bind(R.id.tv_status)
    TextView tvStatus;
    @Bind(R.id.tv_rb_name)
    TextView tvRbName;
    @Bind(R.id.tv_rb_vip_name)
    TextView tvRbVipName;
    @Bind(R.id.tv_rb_mobile)
    TextView tvRbMobile;
    @Bind(R.id.tv_rb_shop_address)
    TextView tvRbShopAddress;
    @Bind(R.id.tv_rb_target_step)
    TextView tvRbTargetStep;
    @Bind(R.id.iv_banner)
    ImageView ivBanner;

    private String idStr;
    private String myVoucherId;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    private double[] nowLocation;

    @Override
    protected String title() {
        return getString(R.string.consumptive_red_bag_info);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_consumptive_rbresult);
        ButterKnife.bind(this);

        idStr = getIntent().getStringExtra("idStr");
        int status = getIntent().getIntExtra("status", 1);
        nowLocation = getIntent().getDoubleArrayExtra("nowLocation");
        if(status == 0) {
            pullDownConsumptiveRedBag();
        }else{
            ivTitleImg.setImageResource(R.mipmap.ic_get_rb_fal);
            tvStatus.setVisibility(View.GONE);
            ivStatus.setVisibility(View.VISIBLE);
            ivStatus.setImageResource(R.mipmap.ic_fail2reach_the_standard);
        }

        tvName.setTextSize(14);
        tvEnableDate.setTextSize(11);
        tvMoney.setTextSize(27);
        tvMoneyLimit.setTextSize(11);
        initData();
    }

    private void initData() {

        Presenter.getInstance(this).getPaoBuSimple(NetApi.getMySendVoucher + "/" + idStr, null, new PaoTipsCallBack() {
            // 数据完更多数据，一定要调用这个方法。
            // 第一个参数：表示此次数据是否为空。
            // 第二个参数：表示是否还有更多数据。

            @Override
            protected void onSuc(String s) {
                VoucherDetailResponse response = new Gson().fromJson(s, VoucherDetailResponse.class);
                VoucherDetailResponse.VoucherDetailBean bean = response.getData();

                //优惠券
                tvName.setText(getString(R.string.vip_name_x, bean.getBusinessname()));
                tvEnableDate.setText(getString(R.string.end_time_x, dateFormat.format(new Date(bean.getE_time()))));
                tvMoney.setText(bean.getMoney());
                tvMoneyLimit.setText(getString(R.string.use_by_x, bean.getCondition()));

                //下面
                tvRbName.setText(bean.getVname());
                tvRbVipName.setText(getString(R.string.vip_name_x, bean.getBusinessname()));
                tvRbMobile.setText(getString(R.string.phone_x, bean.getPhone()));
                tvRbShopAddress.setText(getString(R.string.shop_address_x, bean.getAddra().replace("/", "")) + bean.getAddress());
                tvRbTargetStep.setText(getString(R.string.target_step_x, bean.getStep()));
                Presenter.getInstance(GetConsumptiveRBResultActivity.this).getImage(ivBanner,bean.getLogo());

            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);

            }
        });
    }

    /**
     * 领取消费红包
     */
    private void pullDownConsumptiveRedBag() {
        Map<String, String> params = new HashMap<>();
        params.put("voucherid", idStr);
        params.put("latitude", nowLocation[0]+"");
        params.put("longitude", nowLocation[1]+"");
        Presenter.getInstance(this).postPaoBuSimple(NetApi.receiveVoucher, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    myVoucherId = jsonObject.getJSONObject("data").getString("recordid");
                    ivTitleImg.setImageResource(R.mipmap.ic_get_rb_suc);
                    ivStatus.setVisibility(View.GONE);
                    tvStatus.setVisibility(View.VISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorStr.contains("距离")) {
                    ivTitleImg.setImageResource(R.mipmap.ic_get_rb_fal_distance);
                    tvStatus.setVisibility(View.GONE);
                    ivStatus.setVisibility(View.GONE);
                    ivStatus.setImageResource(R.mipmap.ic_fail2reach_the_standard);
                }else{
                    ivTitleImg.setImageResource(R.mipmap.ic_get_rb_fal);
                    tvStatus.setVisibility(View.GONE);
                    ivStatus.setVisibility(View.VISIBLE);
                    ivStatus.setImageResource(R.mipmap.ic_fail2reach_the_standard);
                }

            }
        });
    }

    /**
     * 使用消费红包
     *
     * @param recordid
     */
    private void useConsumptiveRedBag(String recordid) {
        Map<String, String> params = new HashMap<>();
        params.put("recordid", recordid);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.useVoucher, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    ivStatus.setVisibility(View.VISIBLE);
                    tvStatus.setVisibility(View.GONE);
                    ivStatus.setImageResource(R.mipmap.ic_already_used);

                    JSONObject jsonObject = new JSONObject(s);
                    jsonObject = jsonObject.getJSONObject("data");
                    String vNo = jsonObject.getString("vno");
                    final NormalDialog codeDialog = new NormalDialog(GetConsumptiveRBResultActivity.this);
                    codeDialog.setMessage("消费红包码：" + vNo);
                    codeDialog.setSingleBtn(true);
                    codeDialog.setYesOnclickListener(getString(R.string.confirm), new NormalDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            codeDialog.dismiss();
                        }
                    });
                    codeDialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @OnClick(R.id.tv_status)
    public void onClick() {
        final NormalDialog dialog = new NormalDialog(this);
        dialog.setMessage("确定要使用消费红包吗?\n" +
                "使用后则无法再次使用。");
        dialog.setYesOnclickListener(getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                dialog.dismiss();
                useConsumptiveRedBag(myVoucherId);
            }
        });
        dialog.setNoOnclickListener(getString(R.string.cancel_no), new NormalDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
