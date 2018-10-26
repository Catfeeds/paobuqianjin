package com.paobuqianjin.pbq.step.view.activity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.j256.ormlite.stmt.query.In;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
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
import com.paobuqianjin.pbq.step.adapter.GridAddPic2Adapter;
import com.paobuqianjin.pbq.step.customview.ChooseOneItemWheelPopWindow;
import com.paobuqianjin.pbq.step.customview.LimitLengthFilter;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.customview.SponsorDialog;
import com.paobuqianjin.pbq.step.data.alioss.AliOss;
import com.paobuqianjin.pbq.step.data.alioss.OssService;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTargetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ConSumRedStyleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ConSumSendHisResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetUserBusinessResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.table.SelectPicBean;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.task.ReleaseTaskSponsorFragment;
import com.umeng.socialize.utils.SocializeUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.model.RongGridView;

public class AddConsumptiveRedBagActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {


    private static final String TAG = AddConsumptiveRedBagActivity.class.getSimpleName();
    private static final int REQUEST_CHANGE = 1;
    private static final int REQUEST_ADD = 2;
    @Bind(R.id.et_red_bag_name)
    EditText etRedBagName;
    @Bind(R.id.et_money)
    EditText etMoney;
    @Bind(R.id.et_limite_money)
    EditText etLimiteMoney;
    @Bind(R.id.et_day_num)
    EditText etDayNum;
    @Bind(R.id.et_num)
    EditText etNum;
    @Bind(R.id.switch_stand)
    ImageView switchStand;
    @Bind(R.id.tv_step)
    TextView tvStep;
    @Bind(R.id.stand_circle_pan)
    RelativeLayout standCirclePan;
    @Bind(R.id.sponor_msg_des_detail)
    TextView sponorMsgDesDetail;
    @Bind(R.id.sponor_msg_span)
    RelativeLayout sponorMsgSpan;

    private final int DEVALUE_STEP = 10000;//默认步数
    @Bind(R.id.linear_open_vip)
    LinearLayout linearOpenVip;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.select_historty)
    LinearLayout selectHistorty;
    @Bind(R.id.et_information)
    EditText etInformation;
    @Bind(R.id.grid_view)
    RongGridView gridView;
    @Bind(R.id.tv_link)
    EditText tvLink;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;
    @Bind(R.id.red_rule)
    LinearLayout redRule;
    @Bind(R.id.switch_style_stand)
    ImageView switchStyleStand;
    @Bind(R.id.tv_style)
    TextView tvStyle;
    @Bind(R.id.sponsor_style_span)
    RelativeLayout sponsorStyleSpan;
    @Bind(R.id.message_sponsor)
    TextView messageSponsor;
    @Bind(R.id.btn_prescan)
    Button btnPrescan;
    @Bind(R.id.btn_confirm)
    Button btnConfirm;

    private ArrayList<String> targetStepArr = new ArrayList<>();
    private ArrayList<String> styleArr = new ArrayList<>();
    private ChooseOneItemWheelPopWindow wheelPopWindow, wheelPopStyle;
    private boolean hasBusiness;
    //    private GetUserBusinessResponse.DataBeanX.DataBean shopBean;
    private int businessId = -1;
    /*    private boolean isVip;*/
    private LimitLengthFilter limitLengthFilter;
    SponsorDialog sponsorApplyDialog, sponsorDialogSuccess;
    private ArrayList<AdObject> adList;
    private GridAddPic2Adapter adapter;
    public static final int MAX_SIZE = 9;
    private TranslateAnimation animationCircleType;
    private View popupCircleTypeView;
    private PopupWindow popupCircleTypeWindow;
    private String cachePath;
    private final int REQUEST_CODE = 111;
    private ProgressDialog dialog;
    private final static int VIP_REQUEST = 11;
    ArrayList<ImageBean> resultList = new ArrayList<>();
    private double[] location;
    private Map<String, String> styleS = new HashMap<>();
    ConSumSendHisResponse.DataBeanX.SendListBean.DataBean dataBean;
    String hisImage = "";

    @Override
    protected String title() {
        return getString(R.string.add_consumptive_red_bag);
    }

    @Override
    public void clickRight() {

    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }


    private void commit() {
/*        if (!isVip) {
            PaoToastUtils.showLongToast(this, "只有金牌会员才能发消费红包");
            return;
        }*/
        if (fillter()) {
            Map<String, String> params = new HashMap<>();
            params.put("busid", businessId + "");
            params.put("vname", etRedBagName.getText().toString());
            params.put("amount", etNum.getText().toString());
            params.put("condition", etLimiteMoney.getText().toString());
            params.put("money", etMoney.getText().toString());
            params.put("day", etDayNum.getText().toString());
            params.put("step", tvStep.getText().toString());
            //params.put("distance", tvStep.getText().toString());
            if (!TextUtils.isEmpty(tvLink.getText().toString().trim())) {
                params.put("target_url", tvLink.getText().toString().trim());
            }

            if (!TextUtils.isEmpty(etInformation.getText().toString().trim())) {
                params.put("content", etInformation.getText().toString().trim());
            }

            String images = "";
            for (SelectPicBean bean : adapter.getData()) {
                if (!TextUtils.isEmpty(images)) {
                    images += ",";
                }
                images += bean.getImageUrl();
            }
            if (!TextUtils.isEmpty(images))
                params.put("images", images);
            location = Presenter.getInstance(this).getLocation();
            if (businessId < 0) {
                if (location[0] > 0d && location[1] > 0d) {
                    params.put("latitude", String.valueOf(location[0]));
                    params.put("latitude", String.valueOf(location[1]));
                }
            }

            if (!TextUtils.isEmpty(tvStyle.getText().toString().trim())) {
                params.put("cateid", styleS.get(tvStyle.getText().toString().trim()));
            }
            Presenter.getInstance(this).postPaoBuSimple(NetApi.getMySendVoucher, params, new PaoTipsCallBack() {
                @Override
                protected void onSuc(String s) {
                    ToastUtils.showShortToast(AddConsumptiveRedBagActivity.this, "添加成功");
                    setResult(4);
                    finish();
                }
            });
        }
    }


    private void preScan() {
       /* if (!isVip) {
            PaoToastUtils.showLongToast(this, "只有金牌会员才能发消费红包");
            return;
        }*/
        if (fillter()) {
            Map<String, String> params = new HashMap<>();
            params.put("busid", businessId + "");
            params.put("vname", etRedBagName.getText().toString());
            params.put("amount", etNum.getText().toString());
            params.put("condition", etLimiteMoney.getText().toString());
            params.put("money", etMoney.getText().toString());
            params.put("day", etDayNum.getText().toString());
            params.put("step", tvStep.getText().toString());
            //params.put("distance", tvStep.getText().toString());
            if (!TextUtils.isEmpty(tvLink.getText().toString().trim())) {
                params.put("target_url", tvLink.getText().toString().trim());
            }

            if (!TextUtils.isEmpty(etInformation.getText().toString().trim())) {
                params.put("content", etInformation.getText().toString().trim());
            }

            String images = "";
            for (SelectPicBean bean : adapter.getData()) {
                if (!TextUtils.isEmpty(images)) {
                    images += ",";
                }
                images += bean.getImageUrl();
            }
            if (!TextUtils.isEmpty(images))
                params.put("images", images);
            location = Presenter.getInstance(this).getLocation();
            if (businessId < 0) {
                if (location[0] > 0d && location[1] > 0d) {
                    params.put("latitude", String.valueOf(location[0]));
                    params.put("latitude", String.valueOf(location[1]));
                }
            }

            if (!TextUtils.isEmpty(tvStyle.getText().toString().trim())) {
                params.put("cateid", styleS.get(tvStyle.getText().toString().trim()));
            }
            if (params.keySet().size() <= 0) {
                PaoToastUtils.showLongToast(this, "请填写消费红包信息");
                return;
            }
            params.put("preview", "1");
            Presenter.getInstance(this).postPaoBuSimple(NetApi.getMySendVoucher, params, new PaoTipsCallBack() {
                @Override
                protected void onSuc(String s) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        int vid = jsonObject.getJSONObject("data").getInt("voucherid");
                        Intent intent = new Intent(AddConsumptiveRedBagActivity.this, GetConsumptiveRBResultActivity.class);
                        intent.putExtra(getPackageName() + "red_id", vid);
                        if (businessId > 0) {
                            intent.putExtra(getPackageName() + "businessid", businessId);
                        }
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consumptive_red_bag);
        ButterKnife.bind(this);

        limitLengthFilter = new LimitLengthFilter();
        etRedBagName.setFilters(new InputFilter[]{limitLengthFilter});
        tvStep.setText(DEVALUE_STEP + "");
        dialog = new ProgressDialog(this);
        dialog.setMessage("上传中");
        dialog.setCancelable(false);
        cachePath = Utils.getDiskCacheDir(this).getAbsolutePath();
        initData();
        setToolBarListener(this);
        loadBanner();
        initAdapter();
        //获取金牌会员状态
        //getVipStatus();
        getConSumRedStyle();
        Intent intent = getIntent();
        if (intent != null) {
            dataBean = (ConSumSendHisResponse.DataBeanX.SendListBean.DataBean) intent.getSerializableExtra("tick_info");
            if (dataBean != null) {
                etInformation.setText(dataBean.getVcontent());
                etRedBagName.setText(dataBean.getVname());
                etMoney.setText(dataBean.getMoney());
                etLimiteMoney.setText(dataBean.getCondition());
                etDayNum.setText(String.valueOf(dataBean.getDay()));
                etNum.setText(String.valueOf(dataBean.getAmount()));
                tvStep.setText(String.valueOf(dataBean.getStep()));
                tvLink.setText(dataBean.getTarget_url());

                if (dataBean.getVimg_arr() != null) {
                    int size = dataBean.getVimg_arr().size();
                    List<SelectPicBean> selectPicBeans = new ArrayList<>();

                    for (int i = 0; i < size; i++) {
                        SelectPicBean selectPicBean = new SelectPicBean();
                        selectPicBean.setImageUrl(dataBean.getVimg_arr().get(i));
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
                if (dataBean.getBusinessid() > 0) {
                    ivDelete.setVisibility(View.VISIBLE);
                    sponorMsgDesDetail.setText(dataBean.getBusinessname());
                    businessId = dataBean.getBusinessid();
                    getDefaultBusiness(false);
                } else {
                    getDefaultBusiness(true);
                }

            } else {
                getDefaultBusiness(true);
            }
        }
    }


    private void getConSumRedStyle() {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlConSumStyle, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    ConSumRedStyleResponse conSumRedStyleResponse = new Gson().fromJson(s, ConSumRedStyleResponse.class);
                    int size = conSumRedStyleResponse.getData().size();
                    for (int i = 0; i < size; i++) {
                        styleS.put(conSumRedStyleResponse.getData().get(i).getName(), String.valueOf(conSumRedStyleResponse.getData().get(i).getId()));
                        styleArr.add(conSumRedStyleResponse.getData().get(i).getName());
                        if (dataBean != null && !TextUtils.isEmpty(dataBean.getCate_name()) && dataBean.getCate_name().equals(conSumRedStyleResponse.getData().get(i).getName())) {
                            tvStyle.setText(dataBean.getCate_name());
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
                        .start(AddConsumptiveRedBagActivity.this, REQUEST_CODE);
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
                picker.start(AddConsumptiveRedBagActivity.this, REQUEST_CODE);
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


    private void loadBanner() {
        String bannerUrl = NetApi.urlAd + "?position=voucher_create" + Presenter.getInstance(this).getLocationStrFormat();
        LocalLog.d(TAG, "bannerUrl  = " + bannerUrl);
        Presenter.getInstance(AddConsumptiveRedBagActivity.this).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
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
                                        ClipboardManager cmb = (ClipboardManager) AddConsumptiveRedBagActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData textClipData = ClipData.newPlainText("Label", getString(R.string.wx_code));
                                        cmb.setPrimaryClip(textClipData);
                                        LocalLog.d(TAG, "  msg = " + cmb.getText());
                                        PaoToastUtils.showLongToast(AddConsumptiveRedBagActivity.this, "微信号复制成功");
                                    } else {
                                        String targetUrl = adList.get(position).getTarget_url();
                                        if (!TextUtils.isEmpty(targetUrl))
                                            startActivity(new Intent(AddConsumptiveRedBagActivity.this, SingleWebViewActivity.class).putExtra("url", targetUrl));
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

    private void initData() {
        //目标步数
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlTarget, null, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    CircleTargetResponse circleTargetResponse = new Gson().fromJson(s, CircleTargetResponse.class);
                    if (circleTargetResponse.getData() != null && circleTargetResponse.getData().size() > 0) {
                        for (int i = 0; i < circleTargetResponse.getData().size(); i++) {
                            targetStepArr.add(String.valueOf(circleTargetResponse.getData().get(i).getTarget()));
                        }
                    }
                } catch (JsonSyntaxException j) {
                    j.printStackTrace();
                }
            }
        });

        //获取默认商家

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

/*    private void getVipStatus() {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlUser + FlagPreference.getUid(this), null, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                    isVip = userInfoResponse.getData().getGvip() == 1;
                    linearOpenVip.setVisibility(isVip ? View.GONE : View.VISIBLE);
                } catch (Exception j) {
                    j.printStackTrace();
                }
            }

        });
    }*/

 /*   private void applyGolden() {
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlTryGvip, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                isVip = true;
                if (sponsorDialogSuccess == null) {
                    sponsorDialogSuccess = new SponsorDialog(AddConsumptiveRedBagActivity.this);
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
    }*/

/*
    private void goldenFreeApply() {
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
    }
*/

    @OnClick({R.id.stand_circle_pan, R.id.sponor_msg_span, R.id.linear_open_vip, R.id.select_historty, R.id.sponsor_style_span, R.id.btn_confirm
            , R.id.btn_prescan, R.id.iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_delete:
                sponorMsgDesDetail.setText(null);
                businessId = -1;
                ivDelete.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_prescan:
                LocalLog.d(TAG, "预览");
                preScan();
                break;
            case R.id.btn_confirm:
                commit();
                break;
            case R.id.sponsor_style_span:
                LocalLog.d(TAG, "选择消费券类型");
                if (wheelPopStyle == null && styleArr.size() > 0) {
                    wheelPopStyle = new ChooseOneItemWheelPopWindow(this, styleArr);
                    wheelPopStyle.setItemConfirmListener(new ChooseOneItemWheelPopWindow.OnWheelItemConfirmListener() {
                        @Override
                        public void onItemSelectLis(String result) {
                            tvStyle.setText(result);
                        }
                    });
                }
                if (wheelPopStyle.isShowing()) {
                    wheelPopStyle.cancel();
                    return;
                }
                wheelPopStyle.setCurrentSelectValue(tvStyle.getText().toString());
                wheelPopStyle.show();
                break;
            case R.id.select_historty:
                LocalLog.d(TAG, "选择历史记录");
                //TODO
                Intent hisIntent = new Intent(this, ConsumHsRedActivity.class);
                hisIntent.putExtra("select", "tick");
                startActivity(hisIntent);
                break;
            case R.id.stand_circle_pan:
                if (wheelPopWindow == null && targetStepArr.size() > 0) {
                    wheelPopWindow = new ChooseOneItemWheelPopWindow(this, targetStepArr);
                    wheelPopWindow.setItemConfirmListener(new ChooseOneItemWheelPopWindow.OnWheelItemConfirmListener() {
                        @Override
                        public void onItemSelectLis(String result) {
                            tvStep.setText(result);
                        }
                    });

                }
                if (wheelPopWindow.isShowing()) {
                    wheelPopWindow.cancel();
                    return;
                }
                wheelPopWindow.setCurrentSelectValue(tvStep.getText().toString());
                wheelPopWindow.show();
                break;
            case R.id.sponor_msg_span:
                Intent intent = new Intent();
                if (hasBusiness) {
                    LocalLog.d(TAG, "商铺信息");
                    intent.setClass(this, SponsorManagerActivity.class);
                    intent.putExtra("businessId", businessId);
//                    intent.setAction(SPONSOR_INFO_ACTION);
                    startActivityForResult(intent, REQUEST_CHANGE);
                } else {
                    LocalLog.d(TAG, "添加商铺");
                    intent.setAction("com.paobuqianjin.pbq.step.SPONSOR_INFO_ACTION");
                    intent.setClass(this, SponsorInfoActivity.class);
                    startActivityForResult(intent, REQUEST_ADD);
                }
                break;
            case R.id.linear_open_vip:
                startActivityForResult(new Intent().setClass(AddConsumptiveRedBagActivity.this, GoldenSponsoractivity.class), VIP_REQUEST);
                break;
        }
    }

    private boolean fillter() {
        String redBagNmae = etRedBagName.getText().toString();

        if (TextUtils.isEmpty(redBagNmae.trim())) {
            PaoToastUtils.showShortToast(this, "请输入消费红包名称");
            return false;
        }
        if (limitLengthFilter.calculateLength(redBagNmae) > 32) {
            PaoToastUtils.showShortToast(this, "消费红包名称不能大于32个字符");
            return false;
        }
        if (TextUtils.isEmpty(etMoney.getText().toString())) {
            PaoToastUtils.showShortToast(this, "请输入消费红包金额");
            return false;
        }
        if (TextUtils.isEmpty(etLimiteMoney.getText().toString())) {
            PaoToastUtils.showShortToast(this, "请输入使用消费红包的最低金额");
            return false;
        }
        try {
            if (Integer.parseInt(etLimiteMoney.getText().toString()) <= Integer.parseInt(etMoney.getText().toString())) {
                PaoToastUtils.showShortToast(this, "优惠金额不能超过消费金额");
                return false;
            }
        } catch (Exception e) {
            PaoToastUtils.showShortToast(this, "参数错误");
            return false;
        }
        if (TextUtils.isEmpty(etDayNum.getText().toString())) {
            PaoToastUtils.showShortToast(this, "请输入有效天数");
            return false;
        }
        if (TextUtils.isEmpty(etNum.getText().toString())) {
            PaoToastUtils.showShortToast(this, "请输入使用消费红包的数量");
            return false;
        }
        if (businessId < 1 && TextUtils.isEmpty(tvLink.getText().toString().trim())) {
            PaoToastUtils.showShortToast(this, "请选择您的商铺或者商铺地址链接");
            return false;
        }
/*        if (!isVip) {
            final NormalDialog dialog = new NormalDialog(this);
            dialog.setMessage("创建消费红包失败,账户还未开通金牌商家会员,是否要开通商家金牌会员?");
            dialog.setYesOnclickListener("去申请", new NormalDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    startActivity(GoldenSponsoractivity.class, null);
                    dialog.dismiss();
                }
            });
            dialog.setNoOnclickListener("取消申请", new NormalDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    dialog.dismiss();
                }
            });
            dialog.show();
            return false;
        }*/
        return true;
    }

    public void getDefaultBusiness(final boolean show) {
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
                            if (shopBean.getDefaultX() == 1 && show) {
                                if (sponorMsgDesDetail != null) {
                                    ivDelete.setVisibility(View.VISIBLE);
                                    businessId = shopBean.getBusinessid();
                                    sponorMsgDesDetail.setText(shopBean.getName());
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHANGE) {
            LocalLog.d(TAG, "resultCode == " + resultCode);
            if (resultCode == ReleaseTaskSponsorFragment.RESULT_SPONSOR_MSG) {
                businessId = data.getIntExtra("businessId", -1);
                if (businessId != -1) {
                    sponorMsgDesDetail.setText(data.getStringExtra("name"));
                }
            } else {
                getDefaultBusiness(true);
            }
        } else if (requestCode == REQUEST_ADD) {
            if (resultCode > 0) {
                int businessId = data.getIntExtra("businessId", -1);
                if (businessId != -1) {
                    hasBusiness = true;
                    this.businessId = businessId;
                    sponorMsgDesDetail.setText(data.getStringExtra("name"));
                }
            }
        } else if (requestCode == VIP_REQUEST) {
            /*getVipStatus();*/
        } else if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
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
        }
    }
}
