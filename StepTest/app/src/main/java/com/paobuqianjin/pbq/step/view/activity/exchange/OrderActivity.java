package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExOutOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.OrderStatusResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UpItemInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
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

public class OrderActivity extends BaseBarActivity {
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
    @Bind(R.id.status_str)
    TextView statusStr;
    @Bind(R.id.order_no)
    TextView orderNo;
    @Bind(R.id.time_str)
    TextView timeStr;
    @Bind(R.id.copy)
    TextView copy;
    @Bind(R.id.wuliu)
    TextView wuliu;
    @Bind(R.id.time_stmap)
    TextView timeStmap;
    @Bind(R.id.phone_name)
    TextView phoneName;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.sale_head_ico)
    CircleImageView saleHeadIco;
    @Bind(R.id.sale_name)
    TextView saleName;
    @Bind(R.id.good_picture)
    CircularImageView goodPicture;
    @Bind(R.id.good_name)
    TextView goodName;
    @Bind(R.id.step_dollar)
    TextView stepDollar;
    @Bind(R.id.triff_money)
    TextView triffMoney;
    @Bind(R.id.pay_way)
    TextView payWay;
    @Bind(R.id.step_dollars)
    TextView stepDollars;
    @Bind(R.id.src_price)
    TextView srcPrice;
    @Bind(R.id.left_tv)
    TextView leftTv;
    @Bind(R.id.right_tv)
    TextView rightTv;
    private ExOutOrderResponse.DataBeanX.DataBean dataBean;
    private final int RELEASE_TR = 4;

    @Override
    protected String title() {
        return "订单详情";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_order_detail_activity_layout);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            dataBean = (ExOutOrderResponse.DataBeanX.DataBean) intent.getSerializableExtra("comm_order_no");
            if (dataBean != null && !TextUtils.isEmpty(dataBean.getComm_no())) {
                getOrderTail(dataBean.getComm_no());
                leftTv.setOnClickListener(onClickListener);
                rightTv.setOnClickListener(onClickListener);
            }
        }
    }


    private void cancelOrder(String comm_id) {
        Map<String, String> param = new HashMap<>();
        param.put("comm_order_id", comm_id);
        param.put("type", "sale");
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlExCancel, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                setResult(Activity.RESULT_OK);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(OrderActivity.this, errorBean.getMessage());
                }
            }
        });
    }

    private void quitOrder(String comm_id) {
        Map<String, String> param = new HashMap<>();
        param.put("comm_order_id", comm_id);
        Presenter.getInstance(OrderActivity.this).postPaoBuSimple(NetApi.urlExQuitOrder, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                setResult(Activity.RESULT_OK);
                finish();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(OrderActivity.this, errorBean.getMessage());
                }
            }
        });
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof TextView && dataBean != null) {
                switch (((TextView) v).getText().toString().trim()) {
                    case "取消订单":
                        cancelOrder(String.valueOf(dataBean.getId()));
                        break;
                    case "退款":
                        quitOrder(String.valueOf(dataBean.getId()));
                        break;
                    case "立即发货":
                        Intent intentRe = new Intent();
                        intentRe.setClass(OrderActivity.this, AddExTriffActivity.class);
                        intentRe.putExtra("comm_order_id", dataBean.getId());
                        startActivityForResult(intentRe, RELEASE_TR);
                        break;
                    case "查看物流":
                        Intent intentTr = new Intent();
                        intentTr.setClass(OrderActivity.this, ExTriffActivity.class);
                        intentTr.putExtra("comm_order_no", dataBean);
                        startActivity(intentTr);
                        break;
                }
            }
        }
    };

    private void getOrderTail(String comm_no) {
        final Map<String, String> param = new HashMap<>();
        param.put("comm_order_no", comm_no);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlExOrderDetail, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    OrderStatusResponse orderStatusResponse = new Gson().fromJson(s, OrderStatusResponse.class);
                    statusStr.setText(orderStatusResponse.getData().getOrder_status_text());
                    orderNo.setText("订单编号 " + orderStatusResponse.getData().getComm_no());
                    String time = DateTimeUtil.formatDateTime(orderStatusResponse.getData().getCreate_time() * 1000, DateTimeUtil.DF_YYYY_MM_DD_HH_MM_SS);
                    timeStr.setText("下单时间：" + time);
                    if (orderStatusResponse.getData().getPayment_type() == 1) {
                        payWay.setText("支付方式: 钱包支付");
                    } else if (orderStatusResponse.getData().getPayment_type() == 0) {
                        payWay.setText("支付方式: 微信支付");
                    }
                    wuliu.setText("共" + orderStatusResponse.getData().getNumber() + "个包裹，" + orderStatusResponse.getData().getCompany_name() + " " +
                            "运单号:" + orderStatusResponse.getData().getExpress_no());
                    phoneName.setText(orderStatusResponse.getData().getBuyer_consigner());
                    phone.setText(orderStatusResponse.getData().getBuyer_mobile());
                    address.setText(orderStatusResponse.getData().getBuyer_addr() + orderStatusResponse.getData().getBuyer_address());


                    Presenter.getInstance(OrderActivity.this).getPlaceErrorImage(saleHeadIco, orderStatusResponse.getData().getUser_avatar()
                            , R.drawable.default_head_ico, R.drawable.default_head_ico);
                    saleName.setText(orderStatusResponse.getData().getUser_nickname());
                    SpannableString stepDollarSpan = new SpannableString(String.valueOf(orderStatusResponse.getData().getCredit()) + "步币");
                    stepDollarSpan.setSpan(new AbsoluteSizeSpan(12, true), orderStatusResponse.getData().getCredit().length(),
                            (orderStatusResponse.getData().getCredit() + "步币").length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
                    ;
                    stepDollars.setText(stepDollarSpan);
                    stepDollar.setText(stepDollarSpan);
                    Presenter.getInstance(OrderActivity.this).getPlaceErrorImage(goodPicture, orderStatusResponse.getData().getImg_url()
                            , R.drawable.bitmap_null, R.drawable.bitmap_null);
                    if (Float.parseFloat(orderStatusResponse.getData().getOld_price()) > 0.0f) {
                        srcPrice.setText("原价" + orderStatusResponse.getData().getOld_price() + "元");
                        srcPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                    }

                    if (Float.parseFloat(orderStatusResponse.getData().getExpress_price()) > 0.0f) {
                        triffMoney.setText("快递: ￥" + orderStatusResponse.getData().getExpress_price());
                    }
                    goodName.setText(orderStatusResponse.getData().getName());
                    if (dataBean != null) {
                        switch (dataBean.getOrder_status()) {
                            case 0:
                                rightTv.setText("取消订单");
                    /*exOutViewHolder.rightTv.setBackground();*/
                                break;
                            case 1:
                                leftTv.setText("退款");
                                rightTv.setText("立即发货");
                                break;
                            case 2:
                                rightTv.setText("查看物流");
                                break;
                            case 3:
                                rightTv.setText("查看物流");
                                break;
                            case 4:
                                rightTv.setText("查看物流");
                                break;
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

}
