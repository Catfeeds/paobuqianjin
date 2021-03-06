package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.j256.ormlite.stmt.query.In;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExAddResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExGoodDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExPublistResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.MainActivity;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/12/28.
 */

public class ConfirmOrderExActivity extends BaseBarActivity {
    private final static String TAG = ConfirmOrderExActivity.class.getSimpleName();
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
    @Bind(R.id.phone_name)
    TextView phoneName;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.address_to)
    TextView addressTo;
    @Bind(R.id.message_phone_address)
    LinearLayout messagePhoneAddress;
    @Bind(R.id.sale_head_ico)
    CircleImageView saleHeadIco;
    @Bind(R.id.sale_name)
    TextView saleName;
    @Bind(R.id.good_picture)
    CircularImageView goodPicture;
    @Bind(R.id.good_name)
    TextView goodName;
    @Bind(R.id.step_dollars)
    TextView stepDollars;
    @Bind(R.id.src_price)
    TextView srcPrice;
    @Bind(R.id.pay_wallet)
    TextView payWallet;
    @Bind(R.id.pay_wex)
    TextView payWex;
    @Bind(R.id.trf_free)
    TextView trfFree;
    @Bind(R.id.step_dollar)
    TextView stepDollar;
    @Bind(R.id.triff_money)
    TextView triffMoney;
    @Bind(R.id.need_pay)
    TextView needPay;
    @Bind(R.id.confirm_order)
    Button confirmOrder;
    private final static int REQUEST_SELECT_ADDR = 4;
    private ExAddResponse.DataBeanX.DataBean dataBean;
    private ExGoodDetailResponse.DataBean goodBean;
    private final int REQUEST_PAY = 5;

    @Override
    protected String title() {
        return "确认订单";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exchange_order_confirm_activity_layout);
        ButterKnife.bind(this);
        getAddDefault(1);
        Intent intent = getIntent();
        if (intent != null) {
            goodBean = (ExGoodDetailResponse.DataBean) intent.getSerializableExtra("good_detail");
            if (goodBean != null) {
                Presenter.getInstance(this).getPlaceErrorImage(saleHeadIco, goodBean.getUser_info().getAvatar()
                        , R.drawable.default_head_ico, R.drawable.default_head_ico);
                saleName.setText(goodBean.getUser_info().getNickname());
                String money_credit = "";
                if (Float.parseFloat(goodBean.getExpress_price()) > 0.0f ||
                        Float.parseFloat(goodBean.getPrice()) > 0.0f) {
                    money_credit = "￥" + String.valueOf(Float.parseFloat(goodBean.getPrice()));
                }
                if (goodBean.getCredit() > 0) {
                    if (TextUtils.isEmpty(money_credit)) {
                        money_credit += goodBean.getCredit() + "步币";
                    } else {
                        money_credit += "+" + goodBean.getCredit() + "步币";
                    }
                }
                if (goodBean.getCredit() > 0) {
                    SpannableString stepDollarSpan = new SpannableString(money_credit);
                    stepDollarSpan.setSpan(new AbsoluteSizeSpan(12, true), money_credit.length() - 2,
                            money_credit.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                    stepDollars.setText(stepDollarSpan);
                    stepDollar.setText(String.valueOf(goodBean.getCredit()) + "步币");
                } else {
                    stepDollars.setText(money_credit);
                }
                Presenter.getInstance(this).getPlaceErrorImage(goodPicture, goodBean.getImgs_arr().get(0)
                        , R.drawable.bitmap_null, R.drawable.bitmap_null);
                if (Float.parseFloat(goodBean.getOld_price()) > 0.0f) {
                    srcPrice.setText("原价" + goodBean.getOld_price() + "元");
                    srcPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                }

                if (Float.parseFloat(goodBean.getExpress_price()) > 0.0f) {
                    triffMoney.setText("快递: ￥" + goodBean.getExpress_price());
                    trfFree.setText("快递: ￥" + goodBean.getExpress_price());
                }
                needPay.setText("合计: ￥" + String.valueOf(Float.parseFloat(goodBean.getExpress_price()) + Float.parseFloat(goodBean.getPrice())));
                goodName.setText(goodBean.getName());

            }
        }
    }

    private void getAddDefault(final int page) {
        Map<String, String> param = new HashMap<>();
        param.put("page", String.valueOf(page));
        param.put("pagesize", String.valueOf(Constants.PAGE_SIZE));
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlExAddressList, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    ExAddResponse exAddResponse = new Gson().fromJson(s, ExAddResponse.class);
                    if (exAddResponse.getData().getData().size() > 0) {
                        int size = exAddResponse.getData().getData().size();
                        for (int i = 0; i < size; i++) {
                            if (exAddResponse.getData().getData().get(i).getIs_default() == 1) {
                                dataBean = exAddResponse.getData().getData().get(i);
                                phoneName.setText(dataBean.getConsigner());
                                phone.setText(dataBean.getMobile());
                                addressTo.setText(dataBean.getAddr() +
                                        dataBean.getAddress());
                            }
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

    @OnClick({R.id.message_phone_address, R.id.confirm_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.message_phone_address:
                Intent addIntent = new Intent();
                addIntent.setClass(this, SelectAddressActivity.class);
                startActivityForResult(addIntent, REQUEST_SELECT_ADDR);
                break;
            case R.id.confirm_order:
                UserInfoResponse.DataBean userInfo = Presenter.getInstance(this).getCurrentUser();
                if (dataBean == null) {
                    PaoToastUtils.showLongToast(this, "请先选择地址！");
                    return;
                }
                if (userInfo != null && goodBean != null && dataBean != null) {
                    if (userInfo.getCredit() < goodBean.getCredit()) {
                        PaoToastUtils.showLongToast(this, "步币数量不足！");
                        return;
                    }
                    Intent intentPay = new Intent();
                    intentPay.putExtra("good_detail", goodBean);
                    intentPay.putExtra("addr_id", String.valueOf(dataBean.getId()));
                    intentPay.setClass(this, ExpayActivity.class);
                    startActivityForResult(intentPay, REQUEST_PAY);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_ADDR) {
            getAddDefault(1);
        } else if (requestCode == REQUEST_PAY && resultCode == Activity.RESULT_OK) {
            LocalLog.d(TAG, "购买成功");
            setResult(Activity.RESULT_OK);
            finish();
        }
    }
}
