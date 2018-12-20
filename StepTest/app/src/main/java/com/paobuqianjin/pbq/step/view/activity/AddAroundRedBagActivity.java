package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImageDataModel;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
import com.lwkandroid.imagepicker.utils.ImagePickerComUtils;
import com.lwkandroid.imagepicker.widget.photoview.PhotoView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorInfoActivity;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorManagerActivity;
import com.paobuqianjin.pbq.step.activity.sponsor.TargetPeopleActivity;
import com.paobuqianjin.pbq.step.adapter.GridAddPic2Adapter;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.alioss.AliOss;
import com.paobuqianjin.pbq.step.data.alioss.OssService;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.bundle.TickDataValue;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetUserBusinessResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RedSendHisResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.table.SelectPicBean;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.ShopToolUtil;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.task.ReleaseTaskSponsorFragment;
import com.umeng.socialize.utils.SocializeUtils;
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

public class AddAroundRedBagActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {

    public static final int MAX_SIZE = 9;
    private static final int REQUEST_CHANGE = 1;
    private static final int REQUEST_ADD = 2;
    private final static int REQUEST_CONSUM_RED = 10;
    private final static int VIP_REQUEST = 11;
    @Bind(R.id.grid_view)
    GridView gridView;
    String title;
    @Bind(R.id.sponor_msg_des_detail)
    TextView sponorMsgDesDetail;
    @Bind(R.id.et_red_bag_num)
    EditText etRedBagNum;
    @Bind(R.id.et_red_bag_total_money)
    EditText etRedBagTotalMoney;
    @Bind(R.id.tv_link)
    EditText tvLink;
    @Bind(R.id.et_information)
    EditText etInformation;
    @Bind(R.id.attion)
    RelativeLayout attion;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.sponor_circle_detail)
    TextView sponorCircleDetail;
    @Bind(R.id.select_circle)
    LinearLayout selectCircle;
    @Bind(R.id.circle_pass)
    EditText circlePass;
    @Bind(R.id.password_circle)
    LinearLayout passwordCircle;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;
    @Bind(R.id.btn_prescan)
    Button btnPrescan;
    @Bind(R.id.circle_delete)
    ImageView circleDelete;
    @Bind(R.id.btn_confirm)
    Button btnConfirm;
    @Bind(R.id.select_historty)
    LinearLayout selectHistorty;
    @Bind(R.id.red_rule)
    LinearLayout redRule;
    @Bind(R.id.consum_red_des)
    TextView consumRedDes;
    @Bind(R.id.iv_delete_consum)
    ImageView ivDeleteConsum;
    @Bind(R.id.linear_consum_red)
    LinearLayout linearConsumRed;
    @Bind(R.id.linear_shop)
    LinearLayout linearShop;
    @Bind(R.id.switch_doll)
    ImageView switchDoll;
    @Bind(R.id.crash_money)
    TextView crashMoney;
    @Bind(R.id.step_dolls)
    TextView stepDolls;
    @Bind(R.id.crash_span)
    LinearLayout crashSpan;
    @Bind(R.id.step_dollor)
    EditText stepDollor;
    @Bind(R.id.step_span)
    LinearLayout stepSpan;
    @Bind(R.id.people_target_span)
    LinearLayout peopleTargetSpan;
    @Bind(R.id.target_people_detail)
    TextView targetPeopleDetail;
    private GridAddPic2Adapter adapter;
    private View popupCircleTypeView;
    private PopupWindow popupCircleTypeWindow;
    private String cachePath;
    private static final String TAG = AddAroundRedBagActivity.class.getSimpleName();
    private final int REQUEST_CODE = 111;
    private TranslateAnimation animationCircleType;
    private ProgressDialog dialog;
    private boolean hasBusiness;
    private int businessId = -1;
    private int lastBusinessId = -1;
    ArrayList<ImageBean> resultList = new ArrayList<>();
    private final static String PAY_FOR_STYLE = "pay_for_style";
    private final static String CIRCLE_ID = "id";
    private final static String PAY_ACTION = "android.intent.action.PAY";
    private final static int MAP_RED_PAY = 200;
    private final static String CIRCLE_RECHARGE = "pay";
    private boolean isVip;
    private final static String SELECT_CIRCLE_ACTION = "com.paobuqianjin.pbq.SELECT_ACTION";
    private final static String ROUND_ACTION = "com.paobuqianjin.pbq.ROUND_PKG.ACTION";
    private final static int SELECT_CIRCLE = 77;
    private String circleId;
    private static String ROUND_RED = "com.paobuqianjin.pbq.ROUND_RED";
    private final static String SEND_ACTION = "com.paobuqianin.pbq.step.SEND";//发红包
    private final static String EDIT_ACTION = "com.paobuqianjin.pbq.step.EDIT";
    private final static String NEAR_RED_RULE = "com.paobuqianjin.pbq.step.NEAR_RED_RULE";
    RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean dataBean;
    String hisImage = "";
    NormalDialog normalDialog, lowDialog;
    TickDataValue tickDataValue;
    private int is_lower = 0;//消费红包是否下架
    private String currentAction;
    //是否选择步币
    private boolean boolStepDoll = false;
    //新增加目标人群
    private String sexStr;
    private String ageMinStr;
    private String ageMaxStr;
    private double longitudeStr;
    private double latitudeStr;
    private String distanceStr;
    private String city;
    private String cityCode;
    private String address;
    private static String TARGET_PEOPLE_ACTION = "com.paobuqianjin.pbq.step.TARGET_ACTION";
    private final static int REQUEST_TARGET_PEOPLE = 0;

    @Override
    protected String title() {
        return getString(R.string.add_around_red_bag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_around_red_bag);
        ButterKnife.bind(this);
        setToolBarListener(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("上传中");
        dialog.setCancelable(false);
        cachePath = Utils.getDiskCacheDir(this).getAbsolutePath();
        initAdapter();
        loadBanner();
        Intent intent = getIntent();
        linearConsumRed = (LinearLayout) findViewById(R.id.linear_consum_red);
        if (intent != null) {
            if (SEND_ACTION.equals(intent.getAction())) {
                dataBean = (RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean)
                        intent.getSerializableExtra("around");
                currentAction = SEND_ACTION;
                if (dataBean != null) {
                    if (dataBean.getLower_id() == 0 && dataBean.getVoucherid() > 0) {
                        tickDataValue = new TickDataValue();
                        tickDataValue.setVoucher_name(dataBean.getVname());
                        tickDataValue.setVoucher_number(dataBean.getNumber());
                        tickDataValue.setValid_day(String.valueOf(dataBean.getVday()));
                        tickDataValue.setSpend_money(dataBean.getVcondition());
                        tickDataValue.setDeduction_money(dataBean.getVmoney());
                        consumRedDes.setText(tickDataValue.getVoucher_name());
                        ivDeleteConsum.setVisibility(View.VISIBLE);
                    } else {

                    }
                    initEdit(dataBean, true);
                    selectHistorty.setVisibility(View.GONE);
                } else {
                    getDefaultBusiness(true);
                }
            } else if (EDIT_ACTION.equals(intent.getAction())) {
                dataBean = (RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean)
                        intent.getSerializableExtra("around");
                currentAction = EDIT_ACTION;
                btnPrescan.setVisibility(View.GONE);
                btnConfirm.setText("确定");
                selectHistorty.setVisibility(View.GONE);
                if (dataBean != null) {
                    if (dataBean.getVoucherid() > 0) {
                        if (dataBean.getLower_id() == 0) {
                            checkConsumRedBack(false);
                        } else {
                            checkConsumRedBack(true);
                        }
                    } else {
                        linearConsumRed.setEnabled(false);
                    }
                    initEdit(dataBean, false);
                } else {
                    getDefaultBusiness(true);
                }
            }
        }
        getVipStatus();
    }

    private void initEdit(final RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean dataBean, boolean canEditable) {
        LocalLog.d(TAG, "dataBean =" + dataBean.toString());
        if (!TextUtils.isEmpty(dataBean.getMap_content())) {
            etInformation.setText(dataBean.getMap_content());
        }
        if (dataBean.getMap_img_arr() != null) {
            int size = dataBean.getMap_img_arr().size();
            List<SelectPicBean> selectPicBeans = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                SelectPicBean selectPicBean = new SelectPicBean();
                selectPicBean.setImageUrl(dataBean.getMap_img_arr().get(i));
                selectPicBeans.add(selectPicBean);
            }
            adapter.setDatas(selectPicBeans);
            for (SelectPicBean bean : adapter.getData()) {
                if (!TextUtils.isEmpty(hisImage)) {
                    hisImage += ",";
                }
                hisImage += bean.getImageUrl();
            }
        }
        LocalLog.d(TAG, "hisImage = " + hisImage);
        if (!canEditable) {
            etRedBagNum.setEnabled(false);
        }
        if (!TextUtils.isEmpty(dataBean.getNumber())) {
            etRedBagNum.setText(dataBean.getNumber());
        }

        if (!canEditable) {
            etRedBagTotalMoney.setEnabled(false);
            stepDollor.setEnabled(false);
            switchDoll.setEnabled(false);
        }
        if (!TextUtils.isEmpty(dataBean.getMoney()) && dataBean.getType() == 1) {
            etRedBagTotalMoney.setText(dataBean.getMoney());
        }
        if (!TextUtils.isEmpty(dataBean.getTarget_url())) {
            tvLink.setText(dataBean.getTarget_url());
        }
        if (dataBean.getType() == 1) {
            boolStepDoll = false;
            init_switch(dataBean.getMoney());
        } else if (dataBean.getType() == 2) {
            boolStepDoll = true;
            init_switch(String.valueOf(dataBean.getPay_credit()));
        }
        if (!TextUtils.isEmpty(dataBean.getCircleid())) {
            try {
                int circleid = Integer.parseInt(dataBean.getCircleid());
                if (circleid > 0) {
                    sponorCircleDetail.setText(dataBean.getCircle_name());
                    circleDelete.setVisibility(View.VISIBLE);
                    circleId = String.valueOf(dataBean.getCircleid());
                    if (!TextUtils.isEmpty(dataBean.getCircle_pwd())) {
                        passwordCircle.setVisibility(View.GONE);
                        circlePass.setText(dataBean.getCircle_pwd());
                    } else {
                        passwordCircle.setVisibility(View.GONE);
                    }
                }

            } catch (Exception e) {
                PaoToastUtils.showLongToast(this, "社群信息有误");
            }

        }

        if (Integer.parseInt(dataBean.getBusinessid()) > 0) {
            hasBusiness = true;
            businessId = Integer.parseInt(dataBean.getBusinessid());
            sponorMsgDesDetail.setText(dataBean.getBusiness_name());
            ivDelete.setVisibility(View.VISIBLE);
        }

        /*消费券*/
        if (dataBean.getVoucherid() > 0) {
            consumRedDes.setText(dataBean.getVname());
            if (EDIT_ACTION.equals(currentAction)) {
                targetPeopleDetail.setText("已筛选");
                linearConsumRed.setEnabled(false);
                consumRedDes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lowDialog == null) {
                            lowDialog = new NormalDialog(AddAroundRedBagActivity.this);
                            lowDialog.setMessage("确定下架该消费券？");
                            lowDialog.setYesOnclickListener("确定", new NormalDialog.onYesOnclickListener() {
                                @Override
                                public void onYesClick() {
                                    cutDonwConsumptiveRedBag(dataBean.getVoucherid());
                                    lowDialog.dismiss();
                                }
                            });

                            lowDialog.setNoOnclickListener("取消", new NormalDialog.onNoOnclickListener() {
                                @Override
                                public void onNoClick() {
                                    lowDialog.dismiss();
                                }
                            });
                        }
                        if (!lowDialog.isShowing())
                            lowDialog.show();
                    }
                });
            } else {

            }
        }
        getDefaultBusiness(false);
    }

    /*消费券下架*/
    private void cutDonwConsumptiveRedBag(final int vouchid) {
        Map<String, String> params = new HashMap<>();
        params.put("voucherid", String.valueOf(vouchid));
        Presenter.getInstance(this).postPaoBuSimple(NetApi.lowerVoucher, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                consumRedDes.setText(null);
                consumRedDes.setEnabled(false);
                is_lower = 1;
                checkConsumRedBack(true);
                PaoToastUtils.showShortToast(AddAroundRedBagActivity.this, "下架成功");
            }
        });
    }

    private void loadBanner() {
        String bannerUrl = NetApi.urlAd + "?position=redmap_create" + Presenter.getInstance(this).getLocationStrFormat();
        LocalLog.d(TAG, "bannerUrl  = " + bannerUrl);
        Presenter.getInstance(AddAroundRedBagActivity.this).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
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
                                        ClipboardManager cmb = (ClipboardManager) AddAroundRedBagActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData textClipData = ClipData.newPlainText("Label", getString(R.string.wx_code));
                                        cmb.setPrimaryClip(textClipData);
                                        LocalLog.d(TAG, "  msg = " + cmb.getText());
                                        PaoToastUtils.showLongToast(AddAroundRedBagActivity.this, "微信号复制成功");
                                    }
                                    String targetUrl = adList.get(position).getTarget_url();
                                    String result = ShopToolUtil.taoBaoString(targetUrl);
                                    if (!TextUtils.isEmpty(result)) {
                                        if (result.startsWith(ShopToolUtil.TaoBaoSchema)
                                                && Utils.checkPackage(getApplicationContext(), ShopToolUtil.TaoBao)) {
                                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result));
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        } else {
                                            startActivity(new Intent(AddAroundRedBagActivity.this, SingleWebViewActivity.class).putExtra("url", targetUrl));
                                        }
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

    private void initAdapter() {
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridAddPic2Adapter(this, MAX_SIZE);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == adapter.getData().size()) {
                    //点击+
                    selectPicture();
                } else {
                    //点击图片查看大图
                    popImageView(adapter.getData().get(position).getImageUrl());
                }
            }
        });
        String image = getIntent().getStringExtra("images");
        if (!TextUtils.isEmpty(image)) {
            String[] images = image.split(",");
            List<SelectPicBean> list = new ArrayList<>();
            for (String s : images) {
                SelectPicBean bean = new SelectPicBean();
                bean.setImageUrl(s);
                list.add(bean);
            }
            adapter.setDatas(list);
        }
    }

    public void popImageView(String url) {
        LocalLog.d(TAG, "查看大图");
        int mScreenWidth = ImagePickerComUtils.getScreenWidth(this);
        int mScreenHeight = ImagePickerComUtils.getScreenHeight(this);
        View popBirthSelectView = View.inflate(this, R.layout.image_big_view, null);
        PhotoView photoView = (PhotoView) popBirthSelectView.findViewById(R.id.photo_view);
        ImageDataModel.getInstance().getDisplayer().display(this, url, photoView, mScreenWidth, mScreenHeight);
        PopupWindow popupSelectWindow = new PopupWindow(popBirthSelectView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
      /*  popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popImageVie dismiss() ");
                popupSelectWindow = null;
            }
        });*/

        popupSelectWindow.setFocusable(true);
        popupSelectWindow.setOutsideTouchable(true);
        popupSelectWindow.setBackgroundDrawable(new BitmapDrawable());

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupSelectWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        popBirthSelectView.startAnimation(animationCircleType);
    }

    private void selectPicture() {
        hideSoftInputView();
        popupCircleTypeView = View.inflate(this, R.layout.select_camera_pic, null);
        popupCircleTypeWindow = new PopupWindow(popupCircleTypeView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupCircleTypeWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popupCircleTypeWindow onDismiss() enter");
                popupCircleTypeWindow = null;
            }
        });
        popupCircleTypeWindow.setFocusable(true);
        popupCircleTypeWindow.setOutsideTouchable(true);
        popupCircleTypeWindow.setBackgroundDrawable(new BitmapDrawable());
        ((RelativeLayout) popupCircleTypeView.findViewById(R.id.select_camera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "相机");
                new ImagePicker()
                        .pickType(ImagePickType.ONLY_CAMERA)//设置选取类型(拍照、单选、多选)
                        .maxNum(1)//设置最大选择数量(拍照和单选都是1，修改后也无效)
                        .needCamera(true)//是否需要在界面中显示相机入口(类似微信)
                        .cachePath(cachePath)//自定义缓存路径
                        .doCrop(1, 1, 0, 0)//裁剪功能需要调用这个方法，多选模式下无效
                        .displayer(new GlideImagePickerDisplayer())//自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                        .start(AddAroundRedBagActivity.this, REQUEST_CODE);
                popupCircleTypeWindow.dismiss();
            }
        });
        ((RelativeLayout) popupCircleTypeView.findViewById(R.id.xiangche_camera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "相册");
                ImagePicker picker = new ImagePicker()
                        .pickType(ImagePickType.MULTI)//设置选取类型(拍照、单选、多选)
                        .needCamera(true)//是否需要在界面中显示相机入口(类似微信)
                        .cachePath(cachePath)//自定义缓存路径
                        .displayer(new GlideImagePickerDisplayer());//自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                //设置最大选择数量(拍照和单选都是1，修改后也无效)
                picker.maxNum(MAX_SIZE - adapter.getData().size());
                picker.start(AddAroundRedBagActivity.this, REQUEST_CODE);
                popupCircleTypeWindow.dismiss();
            }
        });
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupCircleTypeWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);
    }

    private void checkConsumRedBack(boolean is_lowed) {
        if (is_lowed) {
            LocalLog.d(TAG, "已下架 可以进行店铺操作");
            linearShop.setEnabled(true);
            tvLink.setEnabled(true);
        } else {
            LocalLog.d(TAG, "未下架 只能增加店铺或者链接");
            if (!TextUtils.isEmpty(dataBean.getBusiness_name())) {
                linearShop.setEnabled(false);
            }
            if (!TextUtils.isEmpty(dataBean.getTarget_url())) {
                tvLink.setEnabled(false);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void editConfirm(String redid) {
        LocalLog.d(TAG, "修改");
        String images = "";
        for (SelectPicBean bean : adapter.getData()) {
            if (!TextUtils.isEmpty(images)) {
                images += ",";
            }
            images += bean.getImageUrl();
        }

        Map<String, String> params = new HashMap<>();
        if (businessId <= 0) {
            params.put("businessid", 0 + "");
        }
        if (businessId > 0 && !TextUtils.isEmpty(sponorMsgDesDetail.getText().toString().trim())
                && !String.valueOf(businessId).equals(dataBean.getBusinessid())) {
            params.put("businessid", businessId + "");
        }

        if (TextUtils.isEmpty(etInformation.getText().toString()) && !TextUtils.isEmpty(dataBean.getMap_content())) {
            params.put("content", etInformation.getText().toString());
        }

        if (!TextUtils.isEmpty(etInformation.getText().toString()) &&
                !etInformation.getText().toString().trim().equals(dataBean.getMap_content()))
            params.put("content", etInformation.getText().toString());

        if (TextUtils.isEmpty(images) && !TextUtils.isEmpty(hisImage)) {
            params.put("images", "");
        }
        if (!TextUtils.isEmpty(images)) {
            if (!images.equals(hisImage))
                params.put("images", images);
        }

        if (TextUtils.isEmpty(tvLink.getText().toString()) && !TextUtils.isEmpty(dataBean.getTarget_url())) {
            params.put("target_url", "");
        }
        if (!TextUtils.isEmpty(tvLink.getText().toString()) && !tvLink.getText().toString().trim().equals(dataBean.getTarget_url())) {
            params.put("target_url", tvLink.getText().toString());
        }

        if (TextUtils.isEmpty(circleId) && !TextUtils.isEmpty(dataBean.getCircleid()) && Integer.parseInt(dataBean.getCircleid()) > 0) {
            params.put("circleid", "0");
        }

        if (!TextUtils.isEmpty(circleId) && !circleId.equals(dataBean.getCircleid())) {
            params.put("circleid", circleId);
            if (!TextUtils.isEmpty(circlePass.getText().toString()))
                params.put("circle_pwd", circlePass.getText().toString());
        }

        if (dataBean.getVoucherid() > 0 && is_lower == 1) {
            LocalLog.d(TAG, "消费红包已下架!");
            params.put("vid", String.valueOf(dataBean.getVoucherid()));
            params.put("is_lower", String.valueOf(is_lower));
        }

        if (params.keySet().size() <= 0) {
            PaoToastUtils.showLongToast(AddAroundRedBagActivity.this, "没有做任何修改");
            return;
        }
        Presenter.getInstance(this).putPaoBuSimple(NetApi.ulrEditRRED + redid, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                PaoToastUtils.showLongToast(AddAroundRedBagActivity.this, "编辑成功");
                AddAroundRedBagActivity.this.setResult(Activity.RESULT_OK);
                AddAroundRedBagActivity.this.finish();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(AddAroundRedBagActivity.this, errorBean.getMessage());
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocalLog.d(TAG, "onActivityResult() enter");
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            SocializeUtils.safeShowDialog(dialog);
            resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            String content = "";
            for (ImageBean imageBean : resultList) {
                content = content + imageBean.toString() + "\n";
            }
            LocalLog.d(TAG, "content = " + content);
            if (resultList != null && resultList.size() > 0) {
                ImageBean[] beans = new ImageBean[resultList.size()];
                beans = resultList.toArray(beans);
                ImageUpTask imageUpTask = new ImageUpTask();
                imageUpTask.execute(beans);
            } else {
                LocalLog.d(TAG, "未知操作");
            }
            return;
        } else if (requestCode == REQUEST_CHANGE) {
            LocalLog.d(TAG, "resultCode == " + resultCode);
            if (resultCode == ReleaseTaskSponsorFragment.RESULT_SPONSOR_MSG) {
                businessId = data.getIntExtra("businessId", -1);
                if (businessId > 0) {
                    sponorMsgDesDetail.setText(data.getStringExtra("name"));
                    ivDelete.setVisibility(View.VISIBLE);
                }
            } else {
                getDefaultBusiness(true);
            }
        } else if (requestCode == REQUEST_ADD) {
            if (resultCode > 0) {
                int businessId = data.getIntExtra("businessId", -1);
                if (businessId > 0) {
                    hasBusiness = true;
                    this.businessId = businessId;
                    sponorMsgDesDetail.setText(data.getStringExtra("name"));
                    ivDelete.setVisibility(View.VISIBLE);

                }
            }
        } else if (requestCode == MAP_RED_PAY) {
            if (resultCode == Activity.RESULT_OK) {
                LocalLog.d(TAG, "支付成功");
                finish();
            }
        } else if (requestCode == SELECT_CIRCLE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        MyCreateCircleResponse.DataBeanX.DataBean circleData = (MyCreateCircleResponse.DataBeanX.DataBean) data.getSerializableExtra("circle");
                        if (circleData != null && circleData.getId() > 0) {
                            sponorCircleDetail.setText(circleData.getName());
                            circleDelete.setVisibility(View.VISIBLE);
                            circleId = String.valueOf(circleData.getId());
                            if (circleData.getIs_pwd() == 1) {
                                passwordCircle.setVisibility(View.VISIBLE);
                            } else {
                                passwordCircle.setVisibility(View.GONE);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (requestCode == REQUEST_CONSUM_RED) {
            LocalLog.d(TAG, "选择了消费券");
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    tickDataValue = (TickDataValue) data.getSerializableExtra("tick");
                    if (tickDataValue != null) {
                        consumRedDes.setText(tickDataValue.getVoucher_name());
                    }
                }
            }
        } else if (requestCode == VIP_REQUEST) {
            LocalLog.d(TAG, "VIP");
            getVipStatus();
        } else if (requestCode == REQUEST_TARGET_PEOPLE) {
            if (resultCode != 0) {
                sexStr = data.getStringExtra("sexStr");
                ageMinStr = data.getStringExtra("minAgeStr");
                ageMaxStr = data.getStringExtra("maxAgeStr");
                longitudeStr = data.getDoubleExtra("longitudeStr", 0);
                latitudeStr = data.getDoubleExtra("latitudeStr", 0);
                distanceStr = data.getStringExtra("targetSelectStr");
                city = data.getStringExtra("city");
                cityCode = data.getStringExtra("cityCode");
                address = data.getStringExtra("address");

                LocalLog.d(TAG, sexStr + " " + ageMinStr + "\n" + ageMaxStr + "\n" +
                        cityCode + "\n" + address + "\n" + city + "\n"
                        + longitudeStr + "\n" + latitudeStr + "\n" + distanceStr + "\n"
                );
                targetPeopleDetail.setText("已筛选");
            }
        }
    }

    /*预览红包效果*/

    private void preScan() {
        if (fillter()) {
            String images = "";
            for (SelectPicBean bean : adapter.getData()) {
                if (!TextUtils.isEmpty(images)) {
                    images += ",";
                }
                images += bean.getImageUrl();
            }

            Map<String, String> params = new HashMap<>();
            if (!TextUtils.isEmpty(etRedBagNum.getText().toString()))
                params.put("number", etRedBagNum.getText().toString());
            if (!boolStepDoll && !TextUtils.isEmpty(etRedBagTotalMoney.getText().toString())) {
                params.put("money", etRedBagTotalMoney.getText().toString());
                //type =1 表示使用现金
                params.put("type", "1");
            }
            if (boolStepDoll && !TextUtils.isEmpty(stepDollor.getText().toString().trim())) {
                params.put("credit", stepDollor.getText().toString());
                //type =2 表示使用步币
                params.put("type", "2");
            }
            if (businessId > 0 && !TextUtils.isEmpty(sponorMsgDesDetail.getText().toString().trim()))
                params.put("businessid", businessId + "");
            if (!TextUtils.isEmpty(etInformation.getText().toString()))
                params.put("content", etInformation.getText().toString());
            if (!TextUtils.isEmpty(images))
                params.put("images", images);
            if (!TextUtils.isEmpty(tvLink.getText().toString()))
                params.put("target_url", tvLink.getText().toString());
            if (!TextUtils.isEmpty(circleId)) {
                params.put("circleid", circleId);
                if (!TextUtils.isEmpty(circlePass.getText().toString()))
                    params.put("circle_pwd", circlePass.getText().toString());
            }

            if (tickDataValue != null) {
                params.put("voucher_name", tickDataValue.getVoucher_name());
                params.put("spend_money", tickDataValue.getSpend_money());
                params.put("voucher_number", tickDataValue.getVoucher_number());
                params.put("valid_day", tickDataValue.getValid_day());
                params.put("deduction_money", tickDataValue.getDeduction_money());

                /*链接或者店铺必选其一*/
                if (TextUtils.isEmpty(sponorMsgDesDetail.getText().toString().trim())
                        && TextUtils.isEmpty(tvLink.getText().toString().trim())) {
                    PaoToastUtils.showLongToast(this, "有优惠券时店铺或者网店链接必填一项");
                    return;
                }
            }


            //1表示预览
            params.put("is_preview", "1");
            Presenter.getInstance(this).postPaoBuSimple(NetApi.urlRedpacketMap, params, new PaoTipsCallBack() {
                @Override
                protected void onSuc(String s) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        String red_map_id = jsonObject.getJSONObject("data").getString("red_map_id");
                        Intent intent = new Intent();
                        intent.setAction(ROUND_RED);
                        intent.putExtra(AddAroundRedBagActivity.this.getPackageName() + "red_id", red_map_id);
                        intent.setClass(AddAroundRedBagActivity.this, RedInfoActivity.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                    super.onFal(e, errorStr, errorBean);
                    LocalLog.d(TAG, errorStr);
                }
            });
        }
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {
        String images = "";
        for (SelectPicBean bean : adapter.getData()) {
            if (!TextUtils.isEmpty(images)) {
                images += ",";
            }
            images += bean.getImageUrl();
        }
        Intent intent = new Intent();
        intent.putExtra("images", images);
        intent.putExtra("size", adapter.getData().size());
        setResult(2, intent);
        finish();
    }

    private void getVipStatus() {
        LocalLog.d(TAG, "getVipStatus() enter");
        Presenter.getInstance(AddAroundRedBagActivity.this).getPaoBuSimple(NetApi.urlUser + FlagPreference.getUid(AddAroundRedBagActivity.this), null, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                    /*isVip = userInfoResponse.getData().getGvip() == 1;*/
                    isVip = true;
                    attion.setVisibility(isVip ? View.GONE : View.VISIBLE);
                } catch (Exception j) {
                    j.printStackTrace();
                }
            }

        });
    }


    private void init_switch(String data) {
        LocalLog.d(TAG, "init_switch() enter");
        if (boolStepDoll) {
            switchDoll.setImageResource(R.drawable.sdoar_switch_a);
            LocalLog.d(TAG, "选择步币");
            stepDollor.setText(data);
            crashSpan.setVisibility(View.GONE);
            stepSpan.setVisibility(View.VISIBLE);
            crashMoney.setTextColor(ContextCompat.getColor(this, R.color.color_A6A9D9));
            stepDolls.setTextColor(ContextCompat.getColor(this, R.color.color_6c71c4));
            etRedBagNum.setHint(R.string.red_num_des_step);
        } else {
            LocalLog.d(TAG, "选择现金");
            switchDoll.setImageResource(R.drawable.sdoar_switch_b);
            crashMoney.setTextColor(ContextCompat.getColor(this, R.color.color_6c71c4));
            stepDolls.setTextColor(ContextCompat.getColor(this, R.color.color_A6A9D9));
            crashSpan.setVisibility(View.VISIBLE);
            stepSpan.setVisibility(View.GONE);
            etRedBagTotalMoney.setText(data);
            etRedBagNum.setHint(R.string.red_num_des_money);
        }
    }

    private void check_swicth(String data) {
        if (!boolStepDoll) {
            switchDoll.setImageResource(R.drawable.sdoar_switch_a);
            LocalLog.d(TAG, "选择步币");
            etRedBagTotalMoney.setText(data);
            crashSpan.setVisibility(View.GONE);
            stepSpan.setVisibility(View.VISIBLE);
            boolStepDoll = !boolStepDoll;
            crashMoney.setTextColor(ContextCompat.getColor(this, R.color.color_A6A9D9));
            stepDolls.setTextColor(ContextCompat.getColor(this, R.color.color_6c71c4));
            etRedBagNum.setHint(R.string.red_num_des_step);
        } else {
            LocalLog.d(TAG, "选择现金");
            switchDoll.setImageResource(R.drawable.sdoar_switch_b);
            crashMoney.setTextColor(ContextCompat.getColor(this, R.color.color_6c71c4));
            stepDolls.setTextColor(ContextCompat.getColor(this, R.color.color_A6A9D9));
            stepDollor.setText(data);
            crashSpan.setVisibility(View.VISIBLE);
            stepSpan.setVisibility(View.GONE);
            boolStepDoll = !boolStepDoll;
            etRedBagNum.setHint(R.string.red_num_des_money);
        }
    }

    @OnClick({R.id.switch_doll, R.id.linear_shop, R.id.btn_confirm, R.id.attion, R.id.select_circle, R.id.iv_delete, R.id.btn_prescan, R.id.select_historty,
            R.id.circle_delete, R.id.red_rule, R.id.iv_delete_consum, R.id.linear_consum_red, R.id.people_target_span})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.people_target_span:
                if (EDIT_ACTION.equals(currentAction)) {
                    LocalLog.d(TAG, "编辑不可选");
                    return;
                }
                LocalLog.d(TAG, "目标人群筛选");
                intent.setClass(this, TargetPeopleActivity.class);
                intent.putExtra("sexStr", sexStr);
                intent.putExtra("minAgeStr", ageMinStr);
                intent.putExtra("maxAgeStr", ageMaxStr);
                intent.putExtra("longitudeStr", longitudeStr);
                intent.putExtra("latitudeStr", latitudeStr);
                intent.putExtra("targetSelectStr", distanceStr);
                intent.putExtra("city", city);
                intent.putExtra("cityCode", cityCode);
                intent.putExtra("address", address);
                intent.setAction(TARGET_PEOPLE_ACTION);
                startActivityForResult(intent, REQUEST_TARGET_PEOPLE);
                break;
            case R.id.switch_doll:
                etInformation.setFocusable(false);
                check_swicth(null);
                break;
            case R.id.red_rule:
                LocalLog.d(TAG, "查看红包规则");
                startActivity(AgreementActivity.class, null, false, NEAR_RED_RULE);
                break;
            case R.id.iv_delete_consum:
                consumRedDes.setText(null);
                tickDataValue = null;
                break;
            case R.id.circle_delete:
                circleDelete.setVisibility(View.GONE);
                sponorCircleDetail.setText(null);
                circleId = null;
                circlePass.setText(null);
                passwordCircle.setVisibility(View.GONE);
                break;
            case R.id.select_historty:
                LocalLog.d(TAG, "选择历史记录红包再发");
                Intent hisIntent = new Intent(this, RedHsRecordActivity.class);
                hisIntent.putExtra("select", "round");
                hisIntent.setAction(ROUND_ACTION);
                startActivity(hisIntent);
                break;
            case R.id.iv_delete:
                LocalLog.d(TAG, "删除默认店铺");
                sponorMsgDesDetail.setText("");
                lastBusinessId = businessId;
                businessId = -1;
                ivDelete.setVisibility(View.GONE);
                break;
            case R.id.select_circle:
                Intent selectCircleIntent = new Intent();
                selectCircleIntent.setAction(SELECT_CIRCLE_ACTION);
                selectCircleIntent.setClass(this, OwnerCircleActivity.class);
                startActivityForResult(selectCircleIntent, SELECT_CIRCLE);
                break;
            case R.id.attion:
                startActivityForResult(new Intent().setClass(AddAroundRedBagActivity.this, GoldenSponsoractivity.class), VIP_REQUEST);
                break;
            case R.id.linear_shop:
                if (hasBusiness) {
                    LocalLog.d(TAG, "商铺信息");
                    intent.setClass(this, SponsorManagerActivity.class);
                    if (businessId > 0) {
                        intent.putExtra("businessId", businessId);
                    } else {
                        if (lastBusinessId > 0) {
                            intent.putExtra("businessId", lastBusinessId);
                        }
                    }
//                    intent.setAction(SPONSOR_INFO_ACTION);
                    startActivityForResult(intent, REQUEST_CHANGE);
                } else {
                    LocalLog.d(TAG, "添加商铺");
                    intent.setAction("com.paobuqianjin.pbq.step.SPONSOR_INFO_ACTION");
                    intent.setClass(this, SponsorInfoActivity.class);
                    startActivityForResult(intent, REQUEST_ADD);
                }
                break;
            case R.id.btn_prescan:
                preScan();
                break;
            case R.id.btn_confirm:
                if ("确定".equals(btnConfirm.getText().toString().trim())) {
                    LocalLog.d(TAG, "提交修改");
                    if (dataBean != null && !TextUtils.isEmpty(dataBean.getRed_id()))
                        editConfirm(dataBean.getRed_id());
                    return;
                }
                if (fillter()) {
                    String images = "";
                    for (SelectPicBean bean : adapter.getData()) {
                        if (!TextUtils.isEmpty(images)) {
                            images += ",";
                        }
                        images += bean.getImageUrl();
                    }

                    Map<String, String> params = new HashMap<>();
                    if (!TextUtils.isEmpty(etRedBagNum.getText().toString()))
                        params.put("number", etRedBagNum.getText().toString());
                    if (!boolStepDoll && !TextUtils.isEmpty(etRedBagTotalMoney.getText().toString())) {
                        params.put("money", etRedBagTotalMoney.getText().toString());
                        //type =1 表示使用现金
                        params.put("type", "1");
                    }
                    if (boolStepDoll && !TextUtils.isEmpty(stepDollor.getText().toString().trim())) {
                        params.put("credit", stepDollor.getText().toString());
                        //type =2 表示使用步币
                        params.put("type", "2");
                    }
                    if (businessId > 0 && !TextUtils.isEmpty(sponorMsgDesDetail.getText().toString().trim()))
                        params.put("businessid", businessId + "");
                    if (!TextUtils.isEmpty(etInformation.getText().toString()))
                        params.put("content", etInformation.getText().toString());
                    if (!TextUtils.isEmpty(images))
                        params.put("images", images);
                    if (!TextUtils.isEmpty(tvLink.getText().toString()))
                        params.put("target_url", tvLink.getText().toString());
                    if (!TextUtils.isEmpty(circleId)) {
                        params.put("circleid", circleId);
                        if (!TextUtils.isEmpty(circlePass.getText().toString()))
                            params.put("circle_pwd", circlePass.getText().toString());
                    }

                    if (!TextUtils.isEmpty(sexStr)) {
                        params.put("sex", sexStr);
                    }
                    if (latitudeStr > 0.0d) {
                        params.put("latitude", String.valueOf(latitudeStr));
                    }
                    if (longitudeStr > 0.0d) {
                        params.put("longitude", String.valueOf(longitudeStr));
                    }

                    if (!TextUtils.isEmpty(ageMinStr)) {
                        params.put("age_min", ageMinStr);
                    }

                    if (!TextUtils.isEmpty(ageMaxStr)) {
                        params.put("age_max", ageMaxStr);
                    }
                    if (!TextUtils.isEmpty(distanceStr)) {
                        params.put("distance", distanceStr);
                    }

                    if (tickDataValue != null) {
                        params.put("voucher_name", tickDataValue.getVoucher_name());
                        params.put("spend_money", tickDataValue.getSpend_money());
                        params.put("voucher_number", tickDataValue.getVoucher_number());
                        params.put("valid_day", tickDataValue.getValid_day());
                        params.put("deduction_money", tickDataValue.getDeduction_money());
                        /*链接或者店铺必选其一*/
                        if (TextUtils.isEmpty(sponorMsgDesDetail.getText().toString().trim())
                                && TextUtils.isEmpty(tvLink.getText().toString().trim())) {
                            PaoToastUtils.showLongToast(this, "有优惠券时店铺或者网店链接必填一项");
                            return;
                        }
                    }
                    if (params.keySet().size() <= 0) {
                        PaoToastUtils.showLongToast(AddAroundRedBagActivity.this, "参数为空");
                        return;
                    }
                    //0表示添加
                    params.put("is_preview", "0");
                    Presenter.getInstance(this).postPaoBuSimple(NetApi.urlRedpacketMap, params, new PaoTipsCallBack() {
                        @Override
                        protected void onSuc(String s) {
                            try {
                                if (!boolStepDoll) {
                                    JSONObject jsonObject = new JSONObject(s);
                                    String red_map_id = jsonObject.getJSONObject("data").getString("red_map_id");
                                    Bundle bundle = new Bundle();
                                    bundle.putString(CIRCLE_ID, red_map_id);
                                    bundle.putString(PAY_FOR_STYLE, "red_map");
                                    bundle.putString(CIRCLE_RECHARGE, etRedBagTotalMoney.getText().toString());
                                    Intent intent = new Intent();
                                    intent.setClass(AddAroundRedBagActivity.this, PaoBuPayActivity.class);
                                    intent.putExtra(AddAroundRedBagActivity.this.getPackageName(), bundle);
                                    intent.setAction(PAY_ACTION);
                                    startActivityForResult(intent, MAP_RED_PAY);
                                } else {
                                    LocalLog.d(TAG, "发步币！成功");
                                    setResult(Activity.RESULT_OK);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                            super.onFal(e, errorStr, errorBean);
                            LocalLog.d(TAG, errorStr);
                        }
                    });
                }
                break;
            case R.id.linear_consum_red:
                if (isVip) {
                    Intent intentTick = new Intent();
                    if (tickDataValue != null) {
                        intentTick.putExtra("tick", tickDataValue);
                    }
                    startActivityForResult(intentTick.setClass(AddAroundRedBagActivity.this, AddLittleConsumActivity.class), REQUEST_CONSUM_RED);
                    break;
                } else {
                    if (normalDialog == null) {
                        normalDialog = new NormalDialog(this);
                        normalDialog.setMessage("成为金牌会员才能发布消费券哦！");
                        normalDialog.setYesOnclickListener("去开通", new NormalDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                normalDialog.dismiss();
                                startActivityForResult(new Intent().setClass(AddAroundRedBagActivity.this, GoldenSponsoractivity.class), VIP_REQUEST);
                            }
                        });
                        normalDialog.setNoOnclickListener("取消", new NormalDialog.onNoOnclickListener() {
                            @Override
                            public void onNoClick() {
                                normalDialog.dismiss();
                            }
                        });
                    }
                    if (!normalDialog.isShowing()) {
                        normalDialog.show();
                    }
                }
                break;
            default:
                break;
        }
    }

    private boolean fillter() {
/*        if (TextUtils.isEmpty(etInformation.getText().toString().trim())) {
            PaoToastUtils.showShortToast(this, "请输入推广信息");
            return false;
        }*/
        /*if (resultList.size() < 1) {
            PaoToastUtils.showShortToast(this, "请选择图片");
            return false;
        }*/

        if (TextUtils.isEmpty(etRedBagNum.getText().toString().trim())) {
            PaoToastUtils.showShortToast(this, "请输入红包个数");
            return false;
        }

        if (!boolStepDoll && TextUtils.isEmpty(etRedBagTotalMoney.getText().toString().trim())) {
            PaoToastUtils.showShortToast(this, "请输入红包总金额");
            return false;
        }

        if (boolStepDoll && TextUtils.isEmpty(stepDollor.getText().toString().trim())) {
            PaoToastUtils.showShortToast(this, "请输入步币个数");
            return false;
        }

        UserInfoResponse.DataBean userInfoResponse = Presenter.getInstance(this).getCurrentUser();
        if (boolStepDoll) {
            if (Integer.parseInt(stepDollor.getText().toString().trim()) > userInfoResponse.getCredit()) {
                PaoToastUtils.showShortToast(this, "请输入步币不足");
                return false;
            }
        }

/*        if (businessId < 1) {
            PaoToastUtils.showShortToast(this, "请选择您的商铺");
            return false;
        }*/
        return true;
    }

    public class ImageUpTask extends AsyncTask<ImageBean, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        protected String doInBackground(ImageBean... strings) {
            AliOss aliOss = new AliOss();
            aliOss.initRegion(getApplicationContext());
            OssService ossService = aliOss.initOSS(getApplicationContext());
            for (ImageBean path : strings) {
                LocalLog.d(TAG, "path = " + path);
                String url = null;
                url = ossService.asyncPutImageLocal(path.getImagePath());
                final SelectPicBean selectPicBean = new SelectPicBean();
                selectPicBean.setFileUrl(path.getImagePath());
                selectPicBean.setImageUrl(url);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setData(selectPicBean);
                    }
                });
                LocalLog.d(TAG, "url = " + url);
            }
            SocializeUtils.safeCloseDialog(dialog);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            LocalLog.d(TAG, "onPostExecute() enter" + s);
            super.onPostExecute(s);
            //SocializeUtils.safeCloseDialog(dialog);
//            putUserInfoParam.setAvatar(s);
//            Presenter.getInstance(getContext()).putUserInfo(userInfo.getId(), putUserInfoParam);
        }
    }

    public void getDefaultBusiness(final boolean show) {
        LocalLog.d(TAG, "getDefaultBusiness() enter show =" + show);
        //商铺信息
        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("pagesize", "1");
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlGetUserBusiness, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    GetUserBusinessResponse businessResponse = new Gson().fromJson(s, GetUserBusinessResponse.class);
                    if (businessResponse.getError() == 0) {
                        hasBusiness = true;
                        if (businessResponse.getData().getData().size() > 0) {
                            GetUserBusinessResponse.DataBeanX.DataBean shopBean = businessResponse.getData().getData().get(0);
                            if (shopBean.getDefaultX() == 1) {
                                lastBusinessId = shopBean.getBusinessid();
                                if (sponorMsgDesDetail != null && show) {
                                    businessId = shopBean.getBusinessid();
                                    sponorMsgDesDetail.setText(shopBean.getName());
                                    ivDelete.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                } catch (JsonSyntaxException j) {
                    j.printStackTrace();
                }
            }
        });
    }
}