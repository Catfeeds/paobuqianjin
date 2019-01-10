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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExInOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExOutOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.OrderStatusResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.RongYunChatUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.rong.imlib.model.Conversation;

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
    @Bind(R.id.view_line1)
    View viewLine1;
    @Bind(R.id.wu_liu_pan)
    LinearLayout wuLiuPan;
    private ExOutOrderResponse.DataBeanX.DataBean dataBean;
    private ExInOrderResponse.DataBeanX.DataBean inBean;
    private final int RELEASE_TR = 4;
    private final int REQUEST_PAY = 6;
    String order_no;

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
            inBean = (ExInOrderResponse.DataBeanX.DataBean) intent.getSerializableExtra("comm_order_no_in");
            if (dataBean != null && !TextUtils.isEmpty(dataBean.getComm_no())) {
                getOrderTail(dataBean.getComm_no());
                order_no = dataBean.getComm_no();
                leftTv.setOnClickListener(onClickListener);
                rightTv.setOnClickListener(onClickListener);
            } else if (inBean != null && !TextUtils.isEmpty(inBean.getComm_no())) {
                getOrderTail(inBean.getComm_no());
                order_no = inBean.getComm_no();
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


    private void cancelOrderBy(String comm_id) {
        Map<String, String> param = new HashMap<>();
        param.put("comm_order_id", comm_id);
        param.put("type", "buy");
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlExCancel, param, new PaoCallBack() {
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


    private void quitOrderBuy(String comm_id) {
        Map<String, String> param = new HashMap<>();
        param.put("comm_order_id", comm_id);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlExQuit, param, new PaoCallBack() {
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

    //卖家确认退款
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
            if (v instanceof TextView && (dataBean != null || inBean != null)) {
                switch (((TextView) v).getText().toString().trim()) {
                    case "取消订单":
                        if (dataBean != null) {
                            cancelOrder(String.valueOf(dataBean.getId()));
                        } else if (inBean != null) {
                            cancelOrderBy(String.valueOf(inBean.getId()));
                        }
                        break;
                    case "确认退款":
                        if (dataBean != null) {
                            quitOrder(String.valueOf(dataBean.getId()));
                        }
                        break;
                    case "退款":
                        if (dataBean != null) {
                            quitOrder(String.valueOf(dataBean.getId()));
                        } else if (inBean != null) {
                            quitOrderBuy(String.valueOf(inBean.getId()));
                        }
                        break;
                    case "立即发货":
                        if (dataBean != null) {
                            Intent intentRe = new Intent();
                            intentRe.setClass(OrderActivity.this, AddExTriffActivity.class);
                            intentRe.putExtra("comm_order_id", String.valueOf(dataBean.getId()));
                            startActivityForResult(intentRe, RELEASE_TR);
                        }
                        break;
                    case "查看物流":
                        if (dataBean != null) {
                            Intent intentTr = new Intent();
                            intentTr.setClass(OrderActivity.this, ExTriffActivity.class);
                            intentTr.putExtra("comm_order_no", dataBean);
                            startActivity(intentTr);
                        } else if (inBean != null) {
                            Intent intentTr = new Intent();
                            intentTr.setClass(OrderActivity.this, ExTriffActivity.class);
                            intentTr.putExtra("comm_order_no", inBean);
                            startActivity(intentTr);
                        }
                        break;
                    case "立即支付":
                        if (inBean != null) {
                            Intent intentRe = new Intent();
                            intentRe.setClass(OrderActivity.this, ExpayActivity.class);
                            intentRe.putExtra("ex_in_pay", inBean);
                            startActivityForResult(intentRe, REQUEST_PAY);
                        }
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
                    final OrderStatusResponse orderStatusResponse = new Gson().fromJson(s, OrderStatusResponse.class);
                    statusStr.setText(orderStatusResponse.getData().getOrder_status_text());
                    orderNo.setText("订单编号 " + orderStatusResponse.getData().getComm_no());
                    String time = DateTimeUtil.formatDateTime(orderStatusResponse.getData().getCreate_time() * 1000, DateTimeUtil.DF_YYYY_MM_DD_HH_MM_SS);
                    timeStr.setText("下单时间：" + time);
                    if (orderStatusResponse.getData().getPayment_type() == 1) {
                        payWay.setText("支付方式: 钱包支付");
                    } else if (orderStatusResponse.getData().getPayment_type() == 0) {
                        payWay.setText("支付方式: 微信支付");
                    }
                    if (!TextUtils.isEmpty(orderStatusResponse.getData().getExpress_no())) {
                        wuliu.setText("共" + orderStatusResponse.getData().getNumber() + "个包裹，" + orderStatusResponse.getData().getCompany_name() + " " +
                                "运单号:" + orderStatusResponse.getData().getExpress_no());
                    } else {
                        wuLiuPan.setVisibility(View.GONE);
                        viewLine1.setVisibility(View.GONE);
                    }
                    phoneName.setText(orderStatusResponse.getData().getBuyer_consigner());
                    phone.setText(orderStatusResponse.getData().getBuyer_mobile());
                    address.setText(orderStatusResponse.getData().getBuyer_addr() + orderStatusResponse.getData().getBuyer_address());


                    Presenter.getInstance(OrderActivity.this).getPlaceErrorImage(saleHeadIco, orderStatusResponse.getData().getUser_avatar()
                            , R.drawable.default_head_ico, R.drawable.default_head_ico);
                    saleHeadIco.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!TextUtils.isEmpty(orderStatusResponse.getData().getUser_nickname())) {
                                RongYunChatUtils.getInstance().chatTo(OrderActivity.this
                                        , Conversation.ConversationType.PRIVATE
                                        , orderStatusResponse.getData().getUserid() + ""
                                        , orderStatusResponse.getData().getUser_nickname());

                            }
                        }
                    });

                    saleName.setText(orderStatusResponse.getData().getUser_nickname());
                    String showMoney = "";
                    if (Float.parseFloat(orderStatusResponse.getData().getPrice_total()) > 0.0f) {
                        showMoney = "￥" + orderStatusResponse.getData().getPrice_total();
                    }
                    if (Integer.parseInt(orderStatusResponse.getData().getCredit_total()) > 0) {
                        if (TextUtils.isEmpty(showMoney)) {
                            showMoney = String.valueOf(orderStatusResponse.getData().getCredit_total()) + "步币";
                        } else {
                            showMoney += "+" + String.valueOf(orderStatusResponse.getData().getCredit_total()) + "步币";
                        }
                    }
                    if (Integer.parseInt(orderStatusResponse.getData().getCredit_total()) > 0) {
                        SpannableString stepDollarSpan = new SpannableString(showMoney);
                        stepDollarSpan.setSpan(new AbsoluteSizeSpan(12, true), showMoney.length() - 2,
                                showMoney.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                        stepDollars.setText(stepDollarSpan);
                        stepDollar.setText(String.valueOf(orderStatusResponse.getData().getCredit_total()) + "步币");
                    } else {
                        stepDollars.setText(showMoney);
                    }
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
                            case -1:
                                rightTv.setText("确认退款");
                                rightTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_red));
                                break;
                            case 0:
                                rightTv.setText("取消订单");
                                rightTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_red));
                    /*exOutViewHolder.rightTv.setBackground();*/
                                break;
                            case 1:
                                leftTv.setText("退款");
                                rightTv.setText("立即发货");
                                leftTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_gray));
                                rightTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_red));
                                break;
                            case 2:
                                rightTv.setText("查看物流");
                                rightTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_red));
                                break;
                            case 3:
                                rightTv.setText("查看物流");
                                rightTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_red));
                                break;
                            case 4:
                                rightTv.setText("查看物流");
                                rightTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_red));
                                break;
                        }
                    } else if (inBean != null) {
                        switch (inBean.getOrder_status()) {
                            case 0:
                                leftTv.setText("取消订单");
                                rightTv.setText("立即支付");
                                leftTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_gray));
                                rightTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_red));
                    /*exOutViewHolder.rightTv.setBackground();*/
                                break;
                            case 1:
                                rightTv.setText("退款");
                                rightTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_red));
                                break;
                            case 2:
                                leftTv.setText("查看物流");
                                rightTv.setText("确认收货");
                                leftTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_gray));
                                rightTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_red));
                                break;
                            case 3:
                                leftTv.setText("查看物流");
                                rightTv.setText("待评价");
                                leftTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_gray));
                                rightTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_red));
                                break;
                            case 4:
                                rightTv.setText("交易成功");
                                rightTv.setBackground(ContextCompat.getDrawable(OrderActivity.this, R.drawable.ex_order_button_bg_red));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PAY && resultCode == Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK);
            finish();
        } else if (requestCode == RELEASE_TR && resultCode == Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK);
            finish();
        }
    }
}
