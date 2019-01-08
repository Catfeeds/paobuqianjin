package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImageDataModel;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
import com.lwkandroid.imagepicker.utils.ImagePickerComUtils;
import com.lwkandroid.imagepicker.widget.photoview.PhotoView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.adapter.GridAddPic2Adapter;
import com.paobuqianjin.pbq.step.customview.ChooseOneItemWheelPopWindow;
import com.paobuqianjin.pbq.step.data.alioss.AliOss;
import com.paobuqianjin.pbq.step.data.alioss.OssService;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExPublistResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExStyleResponse;
import com.paobuqianjin.pbq.step.data.bean.table.SelectPicBean;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.umeng.socialize.utils.SocializeUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.model.RongGridView;

/**
 * Created by pbq on 2018/12/25.
 */

public class TwoHandReleaseActivity extends BaseBarActivity {
    private final static String TAG = TwoHandReleaseActivity.class.getSimpleName();
    private final static String EDIT_ACTION = "com.paobuqianjin.pbq.step.EDIT_ACTION";
    private final static String RELEASE_ACTION = "com.paobuqianjin.pbq.step.RELEASE_ACTION";
    String hisImage = "";
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
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.two_title)
    EditText twoTitle;
    @Bind(R.id.length_num)
    TextView lengthNum;
    @Bind(R.id.et_information)
    EditText etInformation;
    @Bind(R.id.grid_view)
    RongGridView gridView;
    @Bind(R.id.good_title)
    EditText goodTitle;
    @Bind(R.id.title_span)
    LinearLayout titleSpan;
    @Bind(R.id.full_price)
    EditText fullPrice;
    @Bind(R.id.price_span)
    LinearLayout priceSpan;
    @Bind(R.id.need_step_dollar)
    EditText needStepDollar;
    @Bind(R.id.step_span)
    LinearLayout stepSpan;
    @Bind(R.id.need_triff_dollar)
    EditText needTriffDollar;
    @Bind(R.id.yuan)
    TextView yuan;
    @Bind(R.id.select_icon)
    ImageView selectIcon;
    @Bind(R.id.triff_des)
    TextView triffDes;
    @Bind(R.id.tri_span)
    LinearLayout triSpan;
    @Bind(R.id.type_span)
    LinearLayout typeSpan;
    @Bind(R.id.pay_span)
    LinearLayout paySpan;
    @Bind(R.id.btn_confirm)
    Button btnConfirm;
    @Bind(R.id.style_tv)
    TextView styleTv;
    @Bind(R.id.linear_rel)
    LinearLayout linearRel;
    private GridAddPic2Adapter adapter;
    public static final int MAX_SIZE = 9;
    private View popupCircleTypeView;
    private PopupWindow popupCircleTypeWindow;
    private TranslateAnimation animationCircleType;
    private final int REQUEST_CODE = 111;
    private String cachePath;
    private ProgressDialog dialog;
    ArrayList<ImageBean> resultList = new ArrayList<>();
    //邮寄状态: 0.待议 1.不包邮 2.包邮
    private int express_status = 0;
    private boolean isFreeTrf;//是否包邮
    private ExPublistResponse.DataBeanX.DataBean dataBean;
    private List<String> styleList = new ArrayList<>();
    private Map<String, String> styleCode = new HashMap<>();
    private ChooseOneItemWheelPopWindow wheelPopWindow;

    @Override

    protected String title() {
        return "发布";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_two_hand_activity_layout);
        ButterKnife.bind(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("上传中");
        dialog.setCancelable(false);
        cachePath = Utils.getDiskCacheDir(this).getAbsolutePath();

        Intent intent = getIntent();
        if (intent != null) {
            dataBean = (ExPublistResponse.DataBeanX.DataBean) intent.getSerializableExtra("data");
            initAdapter();
            setFreeTrf(false);
        }
        getStyle();
    }


    private void setFreeTrf(boolean isFreeTrf) {
        if (isFreeTrf) {
            LocalLog.d(TAG, "包邮");
            selectIcon.setImageResource(R.drawable.selected_icon);
            needStepDollar.setEnabled(false);
            needStepDollar.setFocusable(false);
        } else {
            LocalLog.d(TAG, "不包邮");
            selectIcon.setImageDrawable(null);
            needStepDollar.setEnabled(true);
            needStepDollar.setFocusable(true);
        }
        this.isFreeTrf = isFreeTrf;
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
        if (dataBean != null) {
            express_status = dataBean.getExpress_status();
            if (!TextUtils.isEmpty(dataBean.getName()))
                goodTitle.setText(dataBean.getName());
            if (!TextUtils.isEmpty(dataBean.getContent()))
                etInformation.setText(dataBean.getContent());
            if (dataBean.getCredit() > 0)
                needStepDollar.setText(String.valueOf(dataBean.getCredit()));
            if (!TextUtils.isEmpty(dataBean.getExpress_price()))
                needTriffDollar.setText(dataBean.getExpress_price());
            if (!TextUtils.isEmpty(dataBean.getOld_price()))
                fullPrice.setText(dataBean.getOld_price());
            if (dataBean.getStatus() == 1) {
                setFreeTrf(false);
            } else {
                setFreeTrf(true);
            }
            btnConfirm.setText("确定");
        }
        if (dataBean != null && dataBean.getImg_arr() != null && dataBean.getImg_arr().size() > 0) {
            List<SelectPicBean> list = new ArrayList<>();
            for (int i = 0; i < dataBean.getImg_arr().size(); i++) {
                SelectPicBean bean = new SelectPicBean();
                bean.setImageUrl(dataBean.getImg_arr().get(i));
                list.add(bean);
                if (!TextUtils.isEmpty(hisImage)) {
                    hisImage += ",";
                }
                hisImage += dataBean.getImg_arr().get(i);
            }
            adapter.setDatas(list);
        }

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
                        .start(TwoHandReleaseActivity.this, REQUEST_CODE);
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
                picker.start(TwoHandReleaseActivity.this, REQUEST_CODE);
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


    private void editTwoHand() {
        Map<String, String> param = new HashMap<>();
        if (!TextUtils.isEmpty(goodTitle.getText().toString().trim()) && !goodTitle.getText().toString().trim().equals(dataBean.getName())) {
            param.put("name", goodTitle.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(etInformation.getText().toString().trim()) &&
                !etInformation.getText().toString().trim().equals(dataBean.getContent())) {
            param.put("content", etInformation.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(needStepDollar.getText().toString().trim())
                && !needStepDollar.getText().toString().trim().equals(String.valueOf(dataBean.getCredit()))) {
            param.put("credit", needStepDollar.getText().toString().trim());
        }
        if (!TextUtils.isEmpty(fullPrice.getText().toString()) && !fullPrice.getText().toString().trim().equals(dataBean.getOld_price())) {
            try {
                if (Float.parseFloat(fullPrice.getText().toString()) > 0.0f) {
                    param.put("old_price", fullPrice.getText().toString().trim());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (!isFreeTrf) {
            LocalLog.d(TAG, "不包邮邮费必填");
            if (!TextUtils.isEmpty(needTriffDollar.getText().toString().trim())) {
                try {
                    if (Float.parseFloat(needTriffDollar.getText().toString()) > 0.0f
                            && !needTriffDollar.getText().toString().trim().equals(dataBean.getExpress_price())) {
                        param.put("express_price", needTriffDollar.getText().toString().trim());
                        express_status = 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                PaoToastUtils.showLongToast(this, "邮费必填");
                express_status = 0;
            }
        } else {
            express_status = 2;
        }
        if (express_status != dataBean.getExpress_status()) {
            param.put("express_status", String.valueOf(express_status));
        }


        String images = "";
        for (SelectPicBean bean : adapter.getData()) {
            if (!TextUtils.isEmpty(images)) {
                images += ",";
            }
            images += bean.getImageUrl();
        }
        if (!TextUtils.isEmpty(images) && !images.equals(hisImage)) {
            param.put("images", images);
        }

        if (param.size() < 1) {
            PaoToastUtils.showLongToast(this, "没做任何修改!");
            return;
        }

        Presenter.getInstance(this).putPaoBuSimple(NetApi.urlAddExChange + "/" + String.valueOf(dataBean.getId()), param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                PaoToastUtils.showLongToast(TwoHandReleaseActivity.this, "编辑成功");
                finish();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                PaoToastUtils.showLongToast(TwoHandReleaseActivity.this, "编辑失败");
            }
        });
    }

    private void releaseTwoHand() {
        Map<String, String> param = new HashMap<>();
        if (!TextUtils.isEmpty(goodTitle.getText().toString().trim())) {
            param.put("name", goodTitle.getText().toString().trim());
        } else {
            PaoToastUtils.showLongToast(this, "宝贝标题必填");
            return;
        }

        if (!TextUtils.isEmpty(etInformation.getText().toString().trim())) {
            param.put("content", etInformation.getText().toString().trim());
        } else {
            PaoToastUtils.showLongToast(this, "宝贝描述必填");
            return;
        }

        if (!TextUtils.isEmpty(needStepDollar.getText().toString().trim())) {
            param.put("credit", needStepDollar.getText().toString().trim());
        } else {
            PaoToastUtils.showLongToast(this, "步币个数");
            return;
        }

        if (!TextUtils.isEmpty(fullPrice.getText().toString())) {
            try {
                if (Float.parseFloat(fullPrice.getText().toString()) > 0.0f) {
                    param.put("old_price", fullPrice.getText().toString().trim());
                } else {
                    PaoToastUtils.showLongToast(this, "原价输入错误");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (!isFreeTrf) {
            LocalLog.d(TAG, "不包邮邮费必填");
            if (!TextUtils.isEmpty(needTriffDollar.getText().toString().trim())) {
                try {
                    if (Float.parseFloat(needTriffDollar.getText().toString()) > 0.0f) {
                        param.put("express_price", needTriffDollar.getText().toString().trim());
                        express_status = 1;
                    } else {
                        PaoToastUtils.showLongToast(this, "邮费输入错误");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                PaoToastUtils.showLongToast(this, "邮费必填");
                express_status = 0;
            }
        } else {
            express_status = 2;
        }
        param.put("express_status", String.valueOf(express_status));


        String images = "";
        for (SelectPicBean bean : adapter.getData()) {
            if (!TextUtils.isEmpty(images)) {
                images += ",";
            }
            images += bean.getImageUrl();
        }
        if (!TextUtils.isEmpty(images)) {
            param.put("images", images);
        } else {
            PaoToastUtils.showLongToast(this, "至少选择一张图片");
            return;
        }

        if (!TextUtils.isEmpty(styleTv.getText().toString().trim())) {
            if (!TextUtils.isEmpty(styleCode.get(styleTv.getText().toString().trim()))) {
                param.put("cate_id", styleCode.get(styleTv.getText().toString().trim()));
            }
        }

        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlAddExChange, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                LocalLog.d(TAG, "发布成功！");
                PaoToastUtils.showLongToast(TwoHandReleaseActivity.this, "发布成功");
                finish();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                PaoToastUtils.showLongToast(TwoHandReleaseActivity.this, "发布失败");
            }
        });
    }


    private void getStyle() {
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlExStyles, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    ExStyleResponse exStyleResponse = new Gson().fromJson(s, ExStyleResponse.class);
                    if (exStyleResponse.getData().size() > 0) {
                        for (int i = 0; i < exStyleResponse.getData().size(); i++) {
                            styleList.add(exStyleResponse.getData().get(i).getCategroy_name());
                            if (!TextUtils.isEmpty(exStyleResponse.getData().get(i).getCategroy_name()))
                                styleCode.put(exStyleResponse.getData().get(i).getCategroy_name(), String.valueOf(exStyleResponse.getData().get(i).getId()));
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

    @OnClick({R.id.triff_des, R.id.btn_confirm, R.id.select_span, R.id.pay_span})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_span:
                setFreeTrf(!isFreeTrf);
                break;
            case R.id.triff_des:
                LocalLog.d(TAG, "查看邮费提示");
                startActivity(TrifficSuggestActivity.class, null);
                break;
            case R.id.btn_confirm:
                if (dataBean != null) {
                    editTwoHand();
                } else {
                    releaseTwoHand();
                }

                LocalLog.d(TAG, "发布");
                break;
            case R.id.pay_span:
                if (wheelPopWindow == null && styleList.size() > 0) {
                    wheelPopWindow = new ChooseOneItemWheelPopWindow(this, styleList);
                    wheelPopWindow.setItemConfirmListener(new ChooseOneItemWheelPopWindow.OnWheelItemConfirmListener() {
                        @Override
                        public void onItemSelectLis(String result) {
                            styleTv.setText(result);
                        }
                    });
                }
                if (wheelPopWindow != null && wheelPopWindow.isShowing()) {
                    wheelPopWindow.cancel();
                    return;
                }
                if (wheelPopWindow != null) {
                    wheelPopWindow.setCurrentSelectValue(styleTv.getText().toString());
                    wheelPopWindow.show();
                }
                break;
        }
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

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            LocalLog.d(TAG, "onPostExecute() enter" + s);
            super.onPostExecute(s);
            SocializeUtils.safeCloseDialog(dialog);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

        }
    }
}
