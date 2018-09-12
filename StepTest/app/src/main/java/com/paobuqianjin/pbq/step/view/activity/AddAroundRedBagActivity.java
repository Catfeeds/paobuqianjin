package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
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
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorInfoActivity;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorManagerActivity;
import com.paobuqianjin.pbq.step.adapter.GridAddPic2Adapter;
import com.paobuqianjin.pbq.step.data.alioss.AliOss;
import com.paobuqianjin.pbq.step.data.alioss.OssService;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetUserBusinessResponse;
import com.paobuqianjin.pbq.step.data.bean.table.SelectPicBean;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.task.ReleaseTaskSponsorFragment;
import com.umeng.socialize.utils.SocializeUtils;

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
    private GridAddPic2Adapter adapter;
    private View popupCircleTypeView;
    private PopupWindow popupCircleTypeWindow;
    private String cachePath;
    private static final String TAG = AddAroundRedBagActivity.class.getSimpleName();
    private final int REQUEST_CODE = 111;
    private TranslateAnimation animationCircleType;
    private Intent intent;
    private ProgressDialog dialog;
    private boolean hasBusiness;
    private int businessId = -1;
    ArrayList<ImageBean> resultList = new ArrayList<>();
    private final static String PAY_FOR_STYLE = "pay_for_style";
    private final static String CIRCLE_ID = "id";
    private final static String PAY_ACTION = "android.intent.action.PAY";
    private final static int MAP_RED_PAY = 200;
    private final static String CIRCLE_RECHARGE = "pay";

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
        getDefaultBusiness();
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
                if (businessId != -1) {
                    sponorMsgDesDetail.setText(data.getStringExtra("name"));
                }
            } else {
                getDefaultBusiness();
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
        } else if (requestCode == MAP_RED_PAY) {
            if (resultCode == Activity.RESULT_OK) {
                LocalLog.d(TAG, "支付成功");
                finish();
            }
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

    @OnClick({R.id.linear_shop, R.id.btn_confirm, R.id.attion})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.attion:
                startActivity(GoldenSponsoractivity.class, null);
                break;
            case R.id.linear_shop:
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
            case R.id.btn_confirm:
                if (fillter()) {
                    String images = "";
                    for (SelectPicBean bean : adapter.getData()) {
                        if (!TextUtils.isEmpty(images)) {
                            images += ",";
                        }
                        images += bean.getImageUrl();
                    }

                    Map<String, String> params = new HashMap<>();
                    params.put("number", etRedBagNum.getText().toString());
                    params.put("money", etRedBagTotalMoney.getText().toString());
                    params.put("businessid", businessId + "");
                    params.put("content", etInformation.getText().toString());
                    params.put("images", images);
                    params.put("target_url", tvLink.getText().toString());
                    Presenter.getInstance(this).postPaoBuSimple(NetApi.urlRedpacketMap, params, new PaoTipsCallBack() {
                        @Override
                        protected void onSuc(String s) {
                            try {
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
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;
        }
    }

    private boolean fillter() {
        if (TextUtils.isEmpty(etInformation.getText().toString().trim())) {
            PaoToastUtils.showShortToast(this, "请输入推广信息");
            return false;
        }
        /*if (resultList.size() < 1) {
            PaoToastUtils.showShortToast(this, "请选择图片");
            return false;
        }*/

        if (TextUtils.isEmpty(etRedBagNum.getText().toString().trim())) {
            PaoToastUtils.showShortToast(this, "请输入红包个数");
            return false;
        }

        if (TextUtils.isEmpty(etRedBagTotalMoney.getText().toString().trim())) {
            PaoToastUtils.showShortToast(this, "请输入红包总金额");
            return false;
        }
        if (businessId < 1) {
            PaoToastUtils.showShortToast(this, "请选择您的商铺");
            return false;
        }
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

    public void getDefaultBusiness() {
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
                                if (sponorMsgDesDetail != null) {
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
}