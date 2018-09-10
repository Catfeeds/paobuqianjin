package com.paobuqianjin.pbq.step.view.activity;

import android.Manifest;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorInfoActivity;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.VoucherDetailResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetConsumptiveRBResultActivity extends BaseBarActivity {
    private final static String TAG = GetConsumptiveRBResultActivity.class.getSimpleName();
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
    private PermissionSetting mSetting;
    List<String> mapList = new ArrayList<>();
    private View selectMapView;
    private PopupWindow selectMapWindow;
    private TranslateAnimation animationCircleType;

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
        if (status == 0) {
            pullDownConsumptiveRedBag();
        } else {
            ivTitleImg.setImageResource(R.mipmap.ic_get_rb_fal);
            tvStatus.setVisibility(View.GONE);
            ivStatus.setVisibility(View.VISIBLE);
            ivStatus.setImageResource(R.mipmap.ic_fail2reach_the_standard);
        }

        tvName.setTextSize(14);
        tvEnableDate.setTextSize(11);
        tvMoney.setTextSize(27);
        tvMoneyLimit.setTextSize(11);
        if (Utils.isHaveBaiduMap()) {
            mapList.add("百度");
        }

        if (Utils.isHaveGaodeMap()) {
            mapList.add("高德");
        }

        if (Utils.isHaveTencentMap()) {
            mapList.add("腾讯");
        }
        initData();
        mSetting = new PermissionSetting(this);
    }

    private void initData() {

        Presenter.getInstance(this).getPaoBuSimple(NetApi.getMySendVoucher + "/" + idStr, null, new PaoTipsCallBack() {
            // 数据完更多数据，一定要调用这个方法。
            // 第一个参数：表示此次数据是否为空。
            // 第二个参数：表示是否还有更多数据。

            @Override
            protected void onSuc(String s) {
                VoucherDetailResponse response = new Gson().fromJson(s, VoucherDetailResponse.class);
                final VoucherDetailResponse.VoucherDetailBean bean = response.getData();

                //优惠券
                tvName.setText(getString(R.string.vip_name_x, bean.getBusinessname()));
                tvEnableDate.setText(getString(R.string.end_time_x, dateFormat.format(new Date(bean.getE_time()))));
                tvMoney.setText(bean.getMoney());
                tvMoneyLimit.setText(getString(R.string.use_by_x, bean.getCondition()));

                //下面
                tvRbName.setText(bean.getVname());
                tvRbVipName.setText(getString(R.string.vip_name_x, bean.getBusinessname()));
                tvRbMobile.setText(getString(R.string.phone_x, bean.getPhone()));
                tvRbMobile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestPermission(bean.getPhone(), Permission.Group.PHONE);
                    }
                });
                tvRbShopAddress.setText(getString(R.string.shop_address_x, bean.getAddra().replace("/", "")) + bean.getAddress());
                tvRbShopAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(bean.getAddra())) {
                            if (mapList.size() > 0) {
                                selectMap(mapList, null, bean.getAddra(), bean.getLatitude(), bean.getLongitude());
                            }
                        }
                    }
                });
                tvRbTargetStep.setText(getString(R.string.target_step_x, bean.getStep()));
                Presenter.getInstance(GetConsumptiveRBResultActivity.this).getImage(ivBanner, bean.getLogo());

            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);

            }
        });
    }

    private void selectMap(List<String> strings, final String dqAddress, final String gotoAddress,
                           final String gotoLatitude, final String gotoLongitude) {
        selectMapView = View.inflate(GetConsumptiveRBResultActivity.this, R.layout.map_source_pop_select, null);
        selectMapWindow = new PopupWindow(selectMapView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        selectMapWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "selectMapWindow onDismiss() enter");
                selectMapWindow = null;
            }
        });

        selectMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMapWindow.dismiss();
            }
        });
        if (strings.contains("腾讯")) {
            TextView tencent = (TextView) selectMapView.findViewById(R.id.tencent_maps);
            tencent.setVisibility(View.VISIBLE);
            tencent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectMapWindow.dismiss();
                    Utils.openTencentMap(GetConsumptiveRBResultActivity.this, null, gotoAddress, gotoLatitude, gotoLongitude);
                }
            });
        }
        if (strings.contains("百度")) {
            TextView baidu = (TextView) selectMapView.findViewById(R.id.baidu_map);
            baidu.setVisibility(View.VISIBLE);
            baidu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //定位源来源于百度，目标源来自腾讯，需要转换，
                    selectMapWindow.dismiss();
                    Utils.openBaiduMap(GetConsumptiveRBResultActivity.this, dqAddress, gotoAddress, String.valueOf(Presenter.getInstance(GetConsumptiveRBResultActivity.this).getLocation()[0]),
                            String.valueOf(Presenter.getInstance(GetConsumptiveRBResultActivity.this).getLocation()[1]), gotoLatitude, gotoLongitude);
                }
            });
        }
        if (strings.contains("高德")) {
            TextView gaode = (TextView) selectMapView.findViewById(R.id.gaode_map);
            gaode.setVisibility(View.VISIBLE);
            gaode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectMapWindow.dismiss();
                    Utils.openGaoDeMap(GetConsumptiveRBResultActivity.this, dqAddress, gotoAddress, gotoLatitude, gotoLongitude);
                }
            });
        }
        selectMapWindow.setFocusable(true);
        selectMapWindow.setOutsideTouchable(true);
        selectMapWindow.setBackgroundDrawable(new BitmapDrawable());
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        selectMapWindow.showAtLocation(findViewById(R.id.get_consume), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        selectMapView.startAnimation(animationCircleType);
    }

    private void requestPermission(final String phoneNum, String... permissions) {
        DefaultRationale mRationale = new DefaultRationale();
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Utils.callPhone(GetConsumptiveRBResultActivity.this, phoneNum);
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(GetConsumptiveRBResultActivity.this, permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    /**
     * 领取消费红包
     */
    private void pullDownConsumptiveRedBag() {
        Map<String, String> params = new HashMap<>();
        params.put("voucherid", idStr);
        params.put("latitude", nowLocation[0] + "");
        params.put("longitude", nowLocation[1] + "");
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
                } else {
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
