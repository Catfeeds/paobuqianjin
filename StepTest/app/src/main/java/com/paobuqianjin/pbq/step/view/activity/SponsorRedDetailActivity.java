package com.paobuqianjin.pbq.step.view.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorManagerActivity;
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
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.TencentPoi;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;
import com.tencent.tencentmap.mapsdk.map.UiSettings;
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
import butterknife.OnClick;

/**
 * Created by pbq on 2018/6/26.
 */

public class SponsorRedDetailActivity extends BaseBarActivity implements TencentMap.OnMapClickListener, TencentMap.OnMarkerClickListener, TencentLocationListener, BaseBarActivity.ToolBarListener {
    private final static String TAG = SponsorRedDetailActivity.class.getSimpleName();
    private final static String NEAR_ACTION = "com.paobuqianjin.pbq.NEAR_PKG.ACTION";
    private final static String NEAR_RED_RULE = "com.paobuqianjin.pbq.step.NEAR_RED_RULE";
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
    @Bind(R.id.sponsor_red)
    RelativeLayout sponsorRed;
    @Bind(R.id.mapview)
    MapView mapview;
    @Bind(R.id.iv_history)
    ImageView ivHistory;
    @Bind(R.id.linear_history)
    LinearLayout linearHistory;
    @Bind(R.id.iv_send_red_bag)
    TextView ivSendRedBag;
    @Bind(R.id.iv_sponsor)
    ImageView ivSponsor;
    @Bind(R.id.red_rule)
    LinearLayout redRule;
    private List<?> data = new ArrayList<>();
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
    private ArrayList<AdObject> adList;


    private int currentPage = 1;
    private double[] location;
    private TencentLocationManager locationManager;
    private boolean isFirstLocation = true;
    private double[] nowLocation = {0, 0};
    private String req;//定位类型
    private String currentAddrName;
    List<Marker> listMark = new ArrayList<>();
    private PopupWindow popOpWindowRedButtonHori;
    private final static String SPOSNOR_ACTION = "com.paobuqianjin.person.SPONSOR_ACTION";

    @Override
    protected String title() {
        return "精准红包";
    }

    @Override
    public Object right() {
        return "";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_red_detail_activity_layout);
        ButterKnife.bind(this);
        //initMapView(savedInstanceState);
        setToolBarListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {

    }

    @Override
    protected void onDestroy() {
        if (popOpWindowRedButtonHori != null) {
            popOpWindowRedButtonHori.dismiss();
            popOpWindowRedButtonHori = null;
        }
        super.onDestroy();
    }

    @Override
    protected void initView() {
        redPkgRefresh = (SwipeRefreshLayout) findViewById(R.id.red_pkg_refresh);
        redPkgRefresh.setOnRefreshListener(mRefreshListener);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(this);
        redPkgRecycler = (SwipeMenuRecyclerView) findViewById(R.id.red_pkg_recycler);
        redPkgRecycler.setLayoutManager(layoutManager);
        redPkgRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                redPkgRefresh.setEnabled(topRowVerticalPosition >= 0);
            }
        });
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
                        if (loadData()) {
                            showLoadingBar();
                        }
                        startLocation();
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

    public void popRedPkgButton() {
        LocalLog.d(TAG, "popRedPkgButton() 弹出红包");
        if (popOpWindowRedButtonHori != null && popOpWindowRedButtonHori.isShowing()) {
            return;
        }
        if (popOpWindowRedButtonHori == null) {
            View popCircleOpBarHori = View.inflate(SponsorRedDetailActivity.this, R.layout.near_by_red_pop_window, null);
            popOpWindowRedButtonHori = new PopupWindow(popCircleOpBarHori,
                    WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            final Button button = (Button) popCircleOpBarHori.findViewById(R.id.red_pkg_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(SPOSNOR_ACTION);
                    intent.setClass(SponsorRedDetailActivity.this, TaskReleaseActivity.class);
                    startActivity(intent);
                }
            });
            popOpWindowRedButtonHori.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    LocalLog.d(TAG, "popRedPkgButton dismiss() ");
                }
            });

            popOpWindowRedButtonHori.setBackgroundDrawable(new BitmapDrawable());
            TranslateAnimation animationCircleTypeHori = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                    0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                    1, Animation.RELATIVE_TO_PARENT, 0);
            animationCircleTypeHori.setInterpolator(new AccelerateInterpolator());
            animationCircleTypeHori.setDuration(200);


            popOpWindowRedButtonHori.showAtLocation(findViewById(R.id.sponsor_red), Gravity.BOTTOM | Gravity.RIGHT, 0, 400);
            popCircleOpBarHori.startAnimation(animationCircleTypeHori);
        }
    }

    private void loadBanner() {
        String bannerUrl = NetApi.urlAd + "?position=red_near";
        LocalLog.d(TAG, "bannerUrl  = " + bannerUrl);
        Presenter.getInstance(SponsorRedDetailActivity.this).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    Adresponse adresponse = new Gson().fromJson(s, Adresponse.class);
                    final ArrayList<AdObject> adList = new ArrayList<>();
                    if (adresponse.getData() != null && adresponse.getData().size() > 0) {
                        int size = adresponse.getData().size();
                        for (int i = 0; i < size; i++) {
                            if (adresponse.getData().get(i).getImgs() != null
                                    && adresponse.getData().get(i).getImgs().size() > 0) {
                                int imgSize = adresponse.getData().get(i).getImgs().size();
                                for (int j = 0; j < imgSize; j++) {
                                    AdObject adObject = new AdObject();
                                    adObject.setRid(Integer.parseInt(adresponse.getData().get(i).getRid()));
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
                                    if (adList.get(position).getRid() == 0) {
                                        LocalLog.d(TAG, "复制微信号");
                                        ClipboardManager cmb = (ClipboardManager) SponsorRedDetailActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData textClipData = ClipData.newPlainText("Label", getString(R.string.wx_code));
                                        cmb.setPrimaryClip(textClipData);
                                        LocalLog.d(TAG, "  msg = " + cmb.getText());
                                        PaoToastUtils.showLongToast(SponsorRedDetailActivity.this, "微信号复制成功");
                                    } else {
                                        String targetUrl = adList.get(position).getTarget_url();
                                        if (!TextUtils.isEmpty(targetUrl))
                                            startActivity(new Intent(SponsorRedDetailActivity.this, SingleWebViewActivity.class).putExtra("url", targetUrl));
                                    }

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

                        if (nearedpacketBean.getBusinessid() != 0 && nearedpacketBean.getRed_id() != 0) {
                            Intent intent = new Intent();
                            intent.putExtra(getPackageName() + "businessid", nearedpacketBean.getBusinessid());
                            intent.putExtra(getPackageName() + "red_id", nearedpacketBean.getRed_id());
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
                            intent.putExtra(getPackageName() + "red_id", nearedpacketBean.getRed_id());
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


    private boolean loadData() {
        LocalLog.d(TAG, "获取红包!");
        String[] location = FlagPreference.getLocation(this);
        if (location != null && location.length >= 2) {
            if (TextUtils.isEmpty(location[0]) || TextUtils.isEmpty(location[1])) {
                return false;
            }
        }
        String redPkgUrl = NetApi.urlNearBySponsorRed + "latitude=" + location[0] + "&longitude=" +
                location[1];
        if (nowLocation[0] != 0 && nowLocation[1] != 0) {

        }
        Presenter.getInstance(this).getPaoBuSimple(redPkgUrl, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                hideLoadingBar();
                redPkgRefresh.setRefreshing(false);
                try {
                    NearBySponsorResponse nearBySponsorResponse = new Gson().fromJson(s, NearBySponsorResponse.class);
                    if (nearBySponsorResponse.getMessage().contains("附近没有可领取的红包")
                            && nearBySponsorResponse.getData().getLedredpacket().size() == 0) {
                        List<NearBySponsorResponse.DataBean.NearedpacketBean> tempData = new ArrayList<>();
                        int tempSize = tempData.size();
                        for (int i = tempSize; i < 4; i++) {
                            NearBySponsorResponse.DataBean.NearedpacketBean adLabel = new NearBySponsorResponse.DataBean.NearedpacketBean();
                            tempData.add(adLabel);
                        }
                        data = tempData;
                        adapter = new ReleaseRecordAdapter(SponsorRedDetailActivity.this, data);
                        redPkgRecycler.setAdapter(adapter);
                        showInfo();
                        return;
                    }
                    data.clear();
                    if (nearBySponsorResponse.getData() == null) {
                        showInfo();
                        return;
                    }

                    if (nearBySponsorResponse.getData().getLedredpacket() == null
                            && nearBySponsorResponse.getData().getNearedpacket() == null) {
                        showInfo();
                        return;
                    }
                    if (nearBySponsorResponse.getData() != null && nearBySponsorResponse.getData().getNearedpacket() != null
                            && nearBySponsorResponse.getData().getNearedpacket().size() > 0) {
                        List<NearBySponsorResponse.DataBean.NearedpacketBean> tempData = nearBySponsorResponse.getData().getNearedpacket();
                        int tempSize = tempData.size();
                        if (tempSize > 0 && tempSize < 4) {
                            for (int i = tempSize; i < 4; i++) {
                                NearBySponsorResponse.DataBean.NearedpacketBean adLabel = new NearBySponsorResponse.DataBean.NearedpacketBean();
                                tempData.add(adLabel);
                            }
                        }
                        data.addAll((List) tempData);
                    }
                    if (nearBySponsorResponse.getData() != null && nearBySponsorResponse.getData().getLedredpacket() != null
                            && nearBySponsorResponse.getData().getLedredpacket().size() > 0) {
                        List<NearBySponsorResponse.DataBean.Ledredpacket> tempData = nearBySponsorResponse.getData().getLedredpacket();
                        int tempSize = tempData.size();
                        if (tempSize > 0 && tempSize < 4) {
                            for (int i = tempSize; i < 4; i++) {
                                NearBySponsorResponse.DataBean.Ledredpacket adLabel = new NearBySponsorResponse.DataBean.Ledredpacket();
                                tempData.add(adLabel);
                            }
                        }
                        data.addAll((List) tempData);
                    }
                    if (data.size() > 0) {
                        adapter = new ReleaseRecordAdapter(SponsorRedDetailActivity.this, data);
                        adapter.setStep(nearBySponsorResponse.getData().getUserstep());
                        redPkgRecycler.setAdapter(adapter);
                    }

                } catch (JsonSyntaxException j) {
                    LocalLog.e(TAG, "红包数据格式错误！");
                    PaoToastUtils.showLongToast(SponsorRedDetailActivity.this, "开小差了，请稍后再试");
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                redPkgRefresh.setRefreshing(false);
                hideLoadingBar();
                if (errorBean != null) {
                    List<NearBySponsorResponse.DataBean.NearedpacketBean> tempData = new ArrayList<>();
                    int tempSize = tempData.size();
                    for (int i = tempSize; i < 4; i++) {
                        NearBySponsorResponse.DataBean.NearedpacketBean adLabel = new NearBySponsorResponse.DataBean.NearedpacketBean();
                        tempData.add(adLabel);
                    }
                    data = tempData;
                    adapter = new ReleaseRecordAdapter(SponsorRedDetailActivity.this, data);
                    redPkgRecycler.setAdapter(adapter);
                    if (errorBean.getError() == 1) {
                        showInfo();
                    }
                }
            }
        });
        return true;
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

    private void initMapView(Bundle savedInstanceState) {
        mapview.onCreate(savedInstanceState);
        //获取TencentMap实例
        TencentMap tencentMap = mapview.getMap();
//设置卫星底图
//        tencentMap.setSatelliteEnabled(true);
//设置实时路况开启
//        tencentMap.setTrafficEnabled(true);
//设置地图中心点
        tencentMap.setCenter(new LatLng(Presenter.getInstance(this).getLocation()[1], Presenter.getInstance(this).getLocation()[0]));
//设置缩放级别
        tencentMap.setZoom(11);
//        tencentMap.zoomToSpan(Presenter.getInstance(this).getLocation()[1], Presenter.getInstance(this).getLocation()[0]);
        //获取UiSettings实例
        UiSettings uiSettings = mapview.getUiSettings();
//设置logo到屏幕底部中心
        //uiSettings.setLogoPosition(UiSettings.LOGO_POSITION_CENTER_BOTTOM);
//设置比例尺到屏幕右下角
        uiSettings.setScaleViewPosition(UiSettings.SCALEVIEW_POSITION_RIGHT_BOTTOM);
//启用缩放手势(更多的手势控制请参考开发手册)
        uiSettings.setZoomGesturesEnabled(true);

//        addRedBagView();
        tencentMap.setOnMapClickListener(this);
        tencentMap.setOnMarkerClickListener(this);
        if (Build.VERSION.SDK_INT >= 23) {

        } else {
            startLocation();
        }
        setDefaultLocation();
    }

    private void startLocation() {
        TencentLocationRequest request = TencentLocationRequest.create();
        request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_POI);
        request.setAllowGPS(true);
        req = request.toString();
        locationManager = TencentLocationManager.getInstance(this);
        int error = locationManager.requestLocationUpdates(request, this);
        LocalLog.d(TAG, "错误---------" + error);
    }

    private void logMsg(TencentLocation location) {
        StringBuilder sb = new StringBuilder(256);
        sb.append("time : ");
        sb.append(location.getTime());
        sb.append("\n定位参数 : ");// 定位类型
        sb.append(req);
        sb.append("\nlatitude : ");// 纬度
        sb.append(location.getLatitude());
        sb.append("\nlontitude : ");// 经度
        sb.append(location.getLongitude());
        sb.append("\n精度 : ");
        sb.append(location.getAccuracy());
        sb.append("\n来源 : ");// 国家码
        sb.append(location.getProvider());
        sb.append("\n名称 : ");//
        sb.append(location.getName());
        sb.append("\nCountry : ");// 国家名称
        sb.append(location.getCity());
        sb.append("\nprovince:");//省份
        sb.append(location.getProvince());
        sb.append("\ncitycode : ");// 城市编码
        sb.append(location.getCityCode());
        sb.append("\ncity : ");// 城市
        sb.append(location.getCity());
        sb.append("\nDistrict : ");// 区
        sb.append(location.getDistrict());
        sb.append("\nStreet : ");// 街道
        sb.append(location.getStreet());
        sb.append("\naddr : ");// 地址信息
        sb.append(location.getAddress());
        sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
        sb.append(location.getIndoorLocationType());
        sb.append("\nDirection(not all devices have value): ");
        sb.append(location.getDirection());// 方向
        sb.append("\nPoi: ");// POI信息
        if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
            for (int i = 0; i < location.getPoiList().size(); i++) {
                TencentPoi poi = location.getPoiList().get(i);
                sb.append(poi.getName()).append(";");
            }
        }
        if (TencentLocation.GPS_PROVIDER.equals(location.getProvider())) {// GPS定位结果
            sb.append("\nspeed : ");
            sb.append(location.getSpeed());// 速度 单位：km/h
            sb.append("\nheight : ");
            sb.append(location.getAltitude());// 海拔高度 单位：米
            sb.append("\ngps status : ");
            sb.append(location.getGPSRssi());// *****gps质量判断*****
            sb.append("\ndescribe : ");
            sb.append("gps定位成功");
        } else if (TencentLocation.NETWORK_PROVIDER.equals(location.getProvider())) {// 网络定位结果
            // 运营商信息
            sb.append("\ndescribe : ");
            sb.append("网络定位成功");
        }
        LocalLog.d(TAG, sb.toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onLocationChanged(TencentLocation location, int error, String reason) {
        if (error == TencentLocation.ERROR_OK && isFirstLocation) {
            isFirstLocation = false;
            stopLocation();
            logMsg(location);
            String city = location.getCity();
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            this.nowLocation[0] = latitude;
            this.nowLocation[1] = longitude;
            loadData();
/*            if (location.getPoiList() != null && location.getPoiList().size() > 0) {
                this.currentAddrName = location.getPoiList().get(0).getName();
            }
            TencentMap tencentMap = mapview.getMap();
//            tencentMap.setOnMapCameraChangeListener(onMapCameraChangeListener);
//            setPosition(latitude, longitude, true, true);
            tencentMap.zoomToSpan(new LatLng(nowLocation[0] - 0.01, nowLocation[1] + 0.008), new LatLng(nowLocation[0] + 0.01, nowLocation[1] - 0.008));
            tencentMap.setCenter(new LatLng(nowLocation[0], nowLocation[1]));
            addMark();
            addMyLocation();*/
        } else {
//            PaoToastUtils.showShortToast(this, reason);
        }
    }


    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }

    @Override
    public void onMapClick(LatLng latLng) {
        startActivity(new Intent(this, ConsumptiveRedBag2Activity.class).putExtra("isNoConsumptive", true));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        startActivity(new Intent(this, ConsumptiveRedBag2Activity.class).putExtra("isNoConsumptive", true));
        return true;
    }

    private void stopLocation() {
        locationManager.removeUpdates(this);
    }

    private void addMyLocation() {
        Marker marker = mapview.getMap().addMarker(new MarkerOptions()
                .position(new LatLng(nowLocation[0], nowLocation[1]))
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory
                        .defaultMarker())
                .draggable(false));
        if (!TextUtils.isEmpty(currentAddrName)) {
            marker.setTitle("我在 " + currentAddrName + " 附近>");
            marker.showInfoWindow();// 设置默认显示一个infoWindow
        }
    }

    private void addMark() {
        for (int i = 0; i < listMark.size(); i++) {
            listMark.get(i).remove();
        }
        listMark.clear();
        for (int i = 0; i < 5; i++) {
            double offSetY = getRandomOffSet(0.01);
            double offSetX = getRandomOffSet(0.008);
            Marker marker1 = mapview.getMap().addMarker(new MarkerOptions()
                    .position(new LatLng(nowLocation[0] + offSetY, nowLocation[1] + offSetX))
                    .anchor(0.5f, 0.5f)
                    .snippet(i + "")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_around_red_bag))
                    .draggable(false));
            listMark.add(marker1);
        }
    }

    private double getRandomOffSet(double right) {
        return Math.random() * right * (Math.random() > 0.5 ? 1 : -1);
    }

    private void setDefaultLocation() {
        LocalLog.d(TAG, "default location");
        String city = "深圳市";
        double latitude = 22.548826;
        double longitude = 113.930819;
        this.nowLocation[0] = latitude;
        this.nowLocation[1] = longitude;
        mapview.getMap().setCenter(new LatLng(nowLocation[0], nowLocation[1]));
        addMyLocation();
        addMark();
    }

    @OnClick({R.id.iv_history, R.id.iv_send_red_bag, R.id.iv_sponsor, R.id.red_rule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.red_rule:
                LocalLog.d(TAG, "查看红包规则");
                startActivity(AgreementActivity.class, null, false, NEAR_RED_RULE);
                break;
            case R.id.iv_history:
                LocalLog.d(TAG, "查看红包历史记录");
                Intent hisIntent = new Intent(this, RedHsRecordActivity.class);
                hisIntent.setAction(NEAR_ACTION);
                startActivity(hisIntent);
                break;
            case R.id.iv_send_red_bag:
                LocalLog.d(TAG, "发附近红包");
                Intent intent = new Intent();
                intent.setAction(SPOSNOR_ACTION);
                intent.setClass(SponsorRedDetailActivity.this, TaskReleaseActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_sponsor:
                LocalLog.d(TAG, "我的店铺");
                Intent intentBuss = new Intent();
                intentBuss.setClass(this, SponsorManagerActivity.class);
                startActivity(intentBuss);
                break;
        }
    }
}
