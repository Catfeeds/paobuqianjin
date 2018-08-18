package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NearBySponsorResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.ReleaseRecordAdapter;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.base.view.Rotate3dAnimation;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/6/26.
 */

public class SponsorRedDetailActivity extends BaseBarActivity {
    private final static String TAG = SponsorRedDetailActivity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.red_pkg_recycler)
    SwipeMenuRecyclerView redPkgRecycler;
    @Bind(R.id.red_pkg_refresh)
    SwipeRefreshLayout redPkgRefresh;
    LinearLayoutManager layoutManager;
    ReleaseRecordAdapter adapter;
    @Bind(R.id.no_red_data)
    ImageView noRedData;
    @Bind(R.id.sponsor_red)
    RelativeLayout sponsorRed;
    private List<?> data;
    ImageView openRedPkgView;
    private View popRedPkgView;
    TextView totalRedPkg;
    TextView redRevTv;
    TextView errorTextView;
    TextView desPkgTextView;
    TextView sponsorRedName;
    TextView sponsorRelName;
    TextView sponsorAddress;
    TextView sponsorTel;
    LinearLayout sponsorLinear;
    private PopupWindow popupRedPkgWindow;
    private TranslateAnimation animationCircleType;
    private NormalDialog normalDialog;
    private final static String USER_FIT_ACTION_SETTING = "com.paobuqianjin.pbq.USER_FIT_ACTION_USER_SETTING";
    Banner banner;
    private Rationale mRationale;
    private PermissionSetting mSetting;
    private ArrayList<AdObject> adList ;

    @Override
    protected String title() {
        return "商家红包";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_red_detail_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        redPkgRefresh = (SwipeRefreshLayout) findViewById(R.id.red_pkg_refresh);
        redPkgRefresh.setOnRefreshListener(mRefreshListener);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(this);
        redPkgRecycler = (SwipeMenuRecyclerView) findViewById(R.id.red_pkg_recycler);
        redPkgRecycler.setLayoutManager(layoutManager);
        noRedData = (ImageView) findViewById(R.id.no_red_data);
        requestPermission(Permission.Group.LOCATION);
        banner = (Banner) findViewById(R.id.banner);
        loadBanner();
    }

        /*权限适配*/

    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        loadData();
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(SponsorRedDetailActivity.this, permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    private void showInfo() {
        Presenter.getInstance(SponsorRedDetailActivity.this).startService(null, LocalBaiduService.class);
        UserInfoResponse.DataBean userInfo = Presenter.getInstance(SponsorRedDetailActivity.this).getCurrentUser();
        if (userInfo != null) {
            if (userInfo.getIs_perfect() == 0) {
                showUseInfSettingDialog(userInfo);
            }
        } else {
            getUserInfo();
        }
    }

    private void loadBanner() {
        String bannerUrl = NetApi.urlAd + "?position=redpack_list";
        LocalLog.d(TAG, "bannerUrl  = " + bannerUrl);
        Presenter.getInstance(this).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    Adresponse adresponse = new Gson().fromJson(s, Adresponse.class);
                     adList = new ArrayList<>();
                    if (adresponse.getData() != null && adresponse.getData().size() > 0) {
                        int size = adresponse.getData().size();
                        for (int i = 0; i < size; i++) {
                            if (adresponse.getData().get(i).getImgs() != null
                                    && adresponse.getData().get(i).getImgs().size() > 0) {
                                int imgSize = adresponse.getData().get(i).getImgs().size();
                                for (int j = 0; j < imgSize; j++) {
                                    AdObject adObject = new AdObject();
                                    adObject.setImg_url(adresponse.getData().get(i).getImgs().get(j).getImg_url());
                                    adObject.setTarget_url(adresponse.getData().get(i).getTarget_url());
                                    adList.add(adObject);
                                }
                            }
                        }
                    }
                    banner.setImageLoader(new BannerImageLoader())
                            .setImages(adList)
                            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                            .setBannerAnimation(Transformer.Default)
                            .isAutoPlay(true)
                            .setDelayTime(2000)
                            .setIndicatorGravity(BannerConfig.CENTER)
                            .setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    String targetUrl = adList.get(position).getTarget_url();
                                    if(!TextUtils.isEmpty(targetUrl)) startActivity(new Intent(SponsorRedDetailActivity.this, SingleWebViewActivity.class).putExtra("url",targetUrl));
                                }
                            })
                            .start();
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                LocalLog.d(TAG, "获取失败!");
            }
        });
    }

    private boolean showFitInfoEnable() {
        return FlagPreference.getFitShowEd(this);
    }

    private void setShowEd() {
        FlagPreference.setFitShowEd(this, true);
    }

    private void getUserInfo() {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlUser + FlagPreference.getUid(this), null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                    if (userInfoResponse.getData() != null && userInfoResponse.getData().getIs_perfect() == 0
                            && !showFitInfoEnable()) {
                        showUseInfSettingDialog(userInfoResponse.getData());
                        setShowEd();
                    } else {

                    }
                } catch (Exception j) {
                    j.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean == null) {

                }
            }
        });
    }

    private void showUseInfSettingDialog(final UserInfoResponse.DataBean userInfo) {
        if (normalDialog == null) {
            normalDialog = new NormalDialog(SponsorRedDetailActivity.this);
            normalDialog.setTitle(getString(R.string.useinfo_setting_title));
            normalDialog.setMessage(getString(R.string.useinfo_setting));
            normalDialog.setNoOnclickListener(getString(R.string.cancel), new NormalDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    normalDialog.dismiss();
                }
            });

            normalDialog.setYesOnclickListener(getString(R.string.fit_info), new NormalDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    normalDialog.dismiss();
                    Intent intent = new Intent();
                    intent.setClass(SponsorRedDetailActivity.this, UserFitActivity.class);
                    intent.setAction(USER_FIT_ACTION_SETTING);
                    intent.putExtra("userinfo", userInfo);
                    startActivity(intent);

                }
            });
            if (!normalDialog.isShowing()) {
                normalDialog.show();
            }
        } else {
            if (!normalDialog.isShowing()) {
                normalDialog.show();
            }
        }
    }

    public void popRedPkg(final NearBySponsorResponse.DataBean.NearedpacketBean nearedpacketBean) {
        if (nearedpacketBean.getStatus() != 0) {
            return;
        }
        if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
            LocalLog.d(TAG, "红包在显示");
            return;
        }
        /*popRedPkgView = null;*/
        popRedPkgView = View.inflate(this, R.layout.red_pkg_pop_window, null);
        totalRedPkg = (TextView) popRedPkgView.findViewById(R.id.total_red_pkg);
        redRevTv = (TextView) popRedPkgView.findViewById(R.id.concla_id);
        openRedPkgView = (ImageView) popRedPkgView.findViewById(R.id.open_red_pkg);
        errorTextView = (TextView) popRedPkgView.findViewById(R.id.error_text);
        desPkgTextView = (TextView) popRedPkgView.findViewById(R.id.des_pkg);
        sponsorRedName = (TextView) popRedPkgView.findViewById(R.id.sponsor_red_name);
        sponsorRedName.setText("任务名称:" + nearedpacketBean.getRed_name());
        sponsorRelName = (TextView) popRedPkgView.findViewById(R.id.sponsor_rel_name);
        sponsorRelName.setText("会员名称:" + nearedpacketBean.getName());
        sponsorAddress = (TextView) popRedPkgView.findViewById(R.id.address_detail);
        String address = nearedpacketBean.getAddra();
        sponsorLinear = (LinearLayout) popRedPkgView.findViewById(R.id.sponsor_linear);
        sponsorLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nearedpacketBean.getBusinessid() != -1) {
                    Intent intent = new Intent();
                    intent.putExtra(getPackageName() + "businessid", nearedpacketBean.getBusinessid());
                    intent.setClass(SponsorRedDetailActivity.this, SponsorDetailActivity.class);
                    startActivity(intent);
                }
            }
        });
        if (!TextUtils.isEmpty(address)) {
            address = address.replace("/", "");
        }
        sponsorAddress.setText(address + nearedpacketBean.getAddress());
        sponsorTel = (TextView) popRedPkgView.findViewById(R.id.sponsor_tel);
        sponsorTel.setText("商家电话:" + nearedpacketBean.getTel());

        totalRedPkg.setVisibility(View.GONE);
        openRedPkgView.setVisibility(View.VISIBLE);
        errorTextView.setVisibility(View.INVISIBLE);
        desPkgTextView.setVisibility(View.VISIBLE);
        redRevTv.setVisibility(View.INVISIBLE);
        LocalLog.d(TAG, "errorTextView = " + errorTextView);
        RelativeLayout relativeLayout = (RelativeLayout) popRedPkgView.findViewById(R.id.cancel_red_span);
        TextView tv_go2be_vip = (TextView) popRedPkgView.findViewById(R.id.tv_go2be_vip);
        tv_go2be_vip.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_go2be_vip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (Presenter.getInstance(SponsorRedDetailActivity.this).getCurrentUser() != null)
                    intent.putExtra("vip", Presenter.getInstance(SponsorRedDetailActivity.this).getCurrentUser().getVip());
                intent.setClass(SponsorRedDetailActivity.this, VipActivity.class);
                startActivity(intent);
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupRedPkgWindow != null) {
                    popupRedPkgWindow.dismiss();
                }
            }
        });
        popupRedPkgWindow = new PopupWindow(popRedPkgView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupRedPkgWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupRedPkgWindow = null;
            }
        });
        popupRedPkgWindow.setFocusable(true);
        popupRedPkgWindow.setOutsideTouchable(true);
        popupRedPkgWindow.setBackgroundDrawable(new BitmapDrawable());
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);
        openRedPkgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "领取商户红包");
                final Rotate3dAnimation animation = new Rotate3dAnimation(0, 359, view.getWidth() / 2f, view.getHeight() / 2f, 30, true);
                animation.setDuration(500);
                animation.setRepeatCount(Animation.INFINITE);
                animation.setFillAfter(true);
                view.setAnimation(animation);
                view.startAnimation(animation);
                if (openRedPkgView == null) {
                    return;
                }
                openRedPkgView.setEnabled(false);
                LocalLog.d(TAG, "领红包按钮!");
                String[] location = FlagPreference.getLocation(getApplicationContext());
                if (location != null && location.length >= 2) {
                    if (TextUtils.isEmpty(location[0]) || TextUtils.isEmpty(location[1])) {
                        PaoToastUtils.showLongToast(getApplicationContext(), "开启位置获取红包!");
                        openRedPkgView.clearAnimation();
                        return;
                    }
                }
                openRedPkgView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (openRedPkgView != null && openRedPkgView.getVisibility() == View.VISIBLE
                                && popupRedPkgWindow != null
                                && popupRedPkgWindow.isShowing()) {
                            openRedPkgView.clearAnimation();
                            openRedPkgView.setVisibility(View.INVISIBLE);
                            errorTextView.setText(getString(R.string.error_red));
                            errorTextView.setVisibility(View.VISIBLE);
                            openRedPkgView.setEnabled(true);
                        }
                    }
                }, 2 * 60 * 1000);
                Map<String, String> params = new HashMap<>();
                params.put("redid", String.valueOf(nearedpacketBean.getRed_id()));
                params.put("latitude", location[0]);
                params.put("longitude", location[1]);
                Presenter.getInstance(getApplicationContext()).postPaoBuSimple(NetApi.urlNearByPkgRev, params, new PaoCallBack() {
                    @Override
                    protected void onSuc(String s) {
                        openRedPkgView.clearAnimation();
                        String result = "";
                        try {
                            JSONObject jsonObj = new JSONObject(s);
                            jsonObj = jsonObj.getJSONObject("data");
                            String allmoney = jsonObj.getString("allmoney");
                            LocalLog.d(TAG, "allmoney = " + allmoney);
                            float redMoney = Float.parseFloat(allmoney);
                            if (redMoney > 0.0f) {
                                openRedPkgView.setVisibility(View.INVISIBLE);
                                totalRedPkg.setVisibility(View.VISIBLE);
                                totalRedPkg.setText(String.valueOf(redMoney));
                                redRevTv.setText(getString(R.string.get_red));
                                redRevTv.setVisibility(View.VISIBLE);
                                result = String.valueOf(redMoney);
                            } else {
                                openRedPkgView.setVisibility(View.INVISIBLE);
                                errorTextView.setText(getString(R.string.slow_text));
                                errorTextView.setVisibility(View.VISIBLE);
                                result = getString(R.string.slow_text);
                            }
                        } catch (JSONException j) {
                            openRedPkgView.setVisibility(View.INVISIBLE);
                            errorTextView.setText(getString(R.string.error_red));
                            errorTextView.setVisibility(View.VISIBLE);
                            j.printStackTrace();
                            result = getString(R.string.error_red);
                        }

                        if (nearedpacketBean.getBusinessid() != -1) {
                            Intent intent = new Intent();
                            intent.putExtra(getPackageName() + "businessid", nearedpacketBean.getBusinessid());
                            intent.putExtra(getPackageName() + "red_result", result);
                            intent.setClass(SponsorRedDetailActivity.this, SponsorDetailActivity.class);
                            startActivity(intent);
                        }
                        popupRedPkgWindow.dismiss();
                        loadData();
                    }

                    @Override
                    protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                        LocalLog.d(TAG, "领取失败!");
                        openRedPkgView.clearAnimation();
                        openRedPkgView.setVisibility(View.INVISIBLE);
                        errorTextView.setVisibility(View.VISIBLE);
                        String result = "";
                        if (errorBean == null) {
                            errorTextView.setText(getString(R.string.error_red));
                            result = getString(R.string.error_red);
                        } else {
                            errorTextView.setText(getString(R.string.slow_text));
                            result = getString(R.string.slow_text);
                        }
                        if (nearedpacketBean.getBusinessid() != -1) {
                            Intent intent = new Intent();
                            intent.putExtra(getPackageName() + "businessid", nearedpacketBean.getBusinessid());
                            intent.putExtra(getPackageName() + "red_result", result);
                            intent.setClass(SponsorRedDetailActivity.this, SponsorDetailActivity.class);
                            startActivity(intent);
                        }
                        popupRedPkgWindow.dismiss();
                        loadData();
                    }
                });

            }
        });
        popupRedPkgWindow.showAtLocation(findViewById(R.id.sponsor_red), Gravity.CENTER, 0, 0);

        popRedPkgView.startAnimation(animationCircleType);
    }


    private void loadData() {
        LocalLog.d(TAG, "获取红包!");
        String[] location = FlagPreference.getLocation(this);
        if (location != null && location.length >= 2) {
            if (TextUtils.isEmpty(location[0]) || TextUtils.isEmpty(location[1])) {
                return;
            }
        }
        String redPkgUrl = NetApi.urlNearBySponsorRed + "latitude=" + location[0] + "&longitude=" +
                location[1];
        Presenter.getInstance(this).getPaoBuSimple(redPkgUrl, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                redPkgRefresh.setRefreshing(false);
                noRedData.setVisibility(View.GONE);
                redPkgRecycler.setVisibility(View.VISIBLE);
                try {
                    NearBySponsorResponse nearBySponsorResponse = new Gson().fromJson(s, NearBySponsorResponse.class);
                    if (nearBySponsorResponse.getMessage().contains("附近没有可领取的红包")) {
                        noRedData.setVisibility(View.VISIBLE);
                        redPkgRecycler.setVisibility(View.GONE);
                        showInfo();
                        return;
                    }
                    if (nearBySponsorResponse.getData() == null) {
                        noRedData.setVisibility(View.VISIBLE);
                        redPkgRecycler.setVisibility(View.GONE);
                        showInfo();
                        return;
                    }

                    if (nearBySponsorResponse.getData().getLedredpacket() == null
                            && nearBySponsorResponse.getData().getNearedpacket() == null) {
                        noRedData.setVisibility(View.VISIBLE);
                        redPkgRecycler.setVisibility(View.GONE);
                        showInfo();
                        return;
                    }
                    if (nearBySponsorResponse.getData().getUserstatus() == 0) {
                        if (nearBySponsorResponse.getData() != null && nearBySponsorResponse.getData().getNearedpacket() != null
                                && nearBySponsorResponse.getData().getNearedpacket().size() > 0) {
                            data = nearBySponsorResponse.getData().getNearedpacket();
                            adapter = new ReleaseRecordAdapter(SponsorRedDetailActivity.this, data);
                            adapter.setStep(nearBySponsorResponse.getData().getUserstep());
                            redPkgRecycler.setAdapter(adapter);
                        }
                    } else {
                        if (nearBySponsorResponse.getData() != null && nearBySponsorResponse.getData().getLedredpacket() != null
                                && nearBySponsorResponse.getData().getLedredpacket().size() > 0) {
                            data = nearBySponsorResponse.getData().getLedredpacket();
                            adapter = new ReleaseRecordAdapter(SponsorRedDetailActivity.this, data);
                            adapter.setStep(nearBySponsorResponse.getData().getUserstep());
                            redPkgRecycler.setAdapter(adapter);
                        }
                    }

                } catch (JsonSyntaxException j) {
                    LocalLog.e(TAG, "红包数据格式错误！");
                    PaoToastUtils.showLongToast(SponsorRedDetailActivity.this, "开小差了，请稍后再试");
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                redPkgRefresh.setRefreshing(false);
                LocalLog.d(TAG, "errorBean = " + errorBean.toString());
                if (errorBean != null) {
                    if (errorBean.getError() == 1) {
                        noRedData.setVisibility(View.VISIBLE);
                        redPkgRecycler.setVisibility(View.GONE);
                        showInfo();
                    }
                }
            }
        });
    }

    /**
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            redPkgRefresh.post(new Runnable() {
                @Override
                public void run() {
                    loadData();
                }
            }); // 延时模拟请求服务器。
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
