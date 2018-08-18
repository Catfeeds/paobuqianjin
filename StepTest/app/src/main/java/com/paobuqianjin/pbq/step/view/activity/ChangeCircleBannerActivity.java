package com.paobuqianjin.pbq.step.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.alioss.AliOss;
import com.paobuqianjin.pbq.step.data.alioss.OssService;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.tencent.yun.ObjectSample.PutObjectSample;
import com.paobuqianjin.pbq.step.data.tencent.yun.activity.ResultHelper;
import com.paobuqianjin.pbq.step.data.tencent.yun.common.QServiceCfg;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.PaoBuApplication;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeCircleBannerActivity extends BaseBarActivity {

    private static final String TAG = ChangeCircleBannerActivity.class.getSimpleName();
    private static final int REQUEST_CODE = 2;
    @Bind(R.id.iv_banner)
    ImageView ivBanner;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    private PopupWindow popupCircleTypeWindow;
    private String cachePath;
    private String localImage;
    private int screenWidth;
    private int imageHeight;
    private QServiceCfg qServiceCfg;
    private String urlImage;
    private String groupId;
    private ProgressDialog progressDialog;

    @Override
    protected String title() {
        return getString(R.string.change_circle_banner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_circle_banner);
        ButterKnife.bind(this);

    }

    @Override
    protected void initView() {
        super.initView();
        ivBanner = findViewById(R.id.iv_banner);
        barTvRight = findViewById(R.id.bar_tv_right);
        cachePath = getExternalCacheDir().getAbsolutePath();
        qServiceCfg = QServiceCfg.instance(this);

        urlImage = getIntent().getStringExtra("intentImgUrl");
        groupId = getIntent().getStringExtra("intentGroupId");
        if (!TextUtils.isEmpty(urlImage)) {
            Presenter.getInstance(this).getImage(ivBanner, urlImage);
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("图片正在上传，请耐心等待");
    }

    @OnClick({R.id.bar_tv_right, R.id.linear_change_bg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_tv_right:
                LogoUpTask logoUpTask = new LogoUpTask();
                logoUpTask.execute(localImage);
                break;
            case R.id.linear_change_bg:
                selectPicture();
                break;
        }
    }

    private void selectPicture() {
        screenWidth = Utils.getScreenWidthHight(ChangeCircleBannerActivity.this)[0];
        imageHeight = ivBanner.getHeight();

        LocalLog.d(TAG, "screenWidth,imageHeight, -- " + screenWidth + "," + imageHeight);

        View popupCircleTypeView = View.inflate(this, R.layout.select_camera_pic, null);
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
                        .doCrop(screenWidth, imageHeight, screenWidth, imageHeight)//裁剪功能需要调用这个方法，多选模式下无效
                        .displayer(new GlideImagePickerDisplayer())//自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                        .start(ChangeCircleBannerActivity.this, REQUEST_CODE);
                popupCircleTypeWindow.dismiss();
            }
        });
        ((RelativeLayout) popupCircleTypeView.findViewById(R.id.xiangche_camera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "相册");
                new ImagePicker()
                        .pickType(ImagePickType.SINGLE)//设置选取类型(拍照、单选、多选)
                        .maxNum(1)//设置最大选择数量(拍照和单选都是1，修改后也无效)
                        .needCamera(true)//是否需要在界面中显示相机入口(类似微信)
                        .cachePath(cachePath)//自定义缓存路径
                        .doCrop(screenWidth, imageHeight, screenWidth, imageHeight)
                        .displayer(new GlideImagePickerDisplayer())//自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                        .start(ChangeCircleBannerActivity.this, REQUEST_CODE);
                popupCircleTypeWindow.dismiss();
            }
        });
        TranslateAnimation animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupCircleTypeWindow.showAtLocation(ChangeCircleBannerActivity.this.getWindow().getDecorView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);
    }

    class LogoUpTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //SocializeUtils.safeShowDialog(dialog);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = null;
            AliOss aliOss = new AliOss();
            aliOss.initRegion(getApplicationContext());
            OssService ossService = aliOss.initOSS(getApplicationContext());
            for (String path : strings) {
                LocalLog.d(TAG, "path = " + path);
                if (ChangeCircleBannerActivity.this == null) {
                    LocalLog.d(TAG, "取消上传");
                    return null;
                }
                url = ossService.asyncPutImageLocal(path);
                LocalLog.d(TAG, "url = " + url);

            }
            return url;
        }

        @Override
        protected void onPostExecute(String s) {
            LocalLog.d(TAG, "onPostExecute() enter " + s);
            super.onPostExecute(s);
            //SocializeUtils.safeCloseDialog(dialog);

            urlImage = s;//新的图片网络路径
            updateServerBanner(urlImage);
        }
    }

    private void updateServerBanner(final String urlImage) {
        Map<String, String> params = new HashMap<>();
        params.put("site", "banner");
        params.put("groupid", groupId);
        params.put("images", urlImage);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlGetGroupBanner, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                progressDialog.dismiss();
                ToastUtils.showShortToast(ChangeCircleBannerActivity.this,"保存成功");
                Intent intent = getIntent();
                intent.putExtra("intentImgUrl", urlImage);
                setResult(RESULT_OK,intent);
                finish();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            List<ImageBean> resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            String content = "";
            for (ImageBean imageBean : resultList) {
                content = content + imageBean.toString() + "\n";
            }
            LocalLog.d(TAG, "content = " + content);
            if (resultList != null && resultList.size() == 1) {
                localImage = resultList.get(0).getImagePath();
                Presenter.getInstance(this).getImage(resultList.get(0).getImagePath(), ivBanner);
                barTvRight.setText(R.string.save);
            } else {
                LocalLog.d(TAG, "未知操作");
            }
        }
    }
}
