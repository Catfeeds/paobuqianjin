package com.paobuqianjin.pbq.step.activity.sponsor;

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
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImageDataModel;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
import com.lwkandroid.imagepicker.utils.ImagePickerComUtils;
import com.lwkandroid.imagepicker.widget.photoview.PhotoView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.adapter.GridAddPicAdapter;
import com.paobuqianjin.pbq.step.data.alioss.AliOss;
import com.paobuqianjin.pbq.step.data.alioss.OssService;
import com.paobuqianjin.pbq.step.data.bean.table.SelectPicBean;
import com.paobuqianjin.pbq.step.data.tencent.yun.ObjectSample.PutObjectSample;
import com.paobuqianjin.pbq.step.data.tencent.yun.activity.ResultHelper;
import com.paobuqianjin.pbq.step.data.tencent.yun.common.QServiceCfg;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SponsorSelectPicActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {

    public static final int MAX_SIZE = 30;
    @Bind(R.id.desc)
    TextView desc;
    @Bind(R.id.pic_grid_view)
    RelativeLayout picGridView;
    @Bind(R.id.grid_view)
    GridView gridView;
    String title;
    private GridAddPicAdapter adapter;
    private View popupCircleTypeView;
    private PopupWindow popupCircleTypeWindow;
    private String cachePath;
    private static final String TAG = SponsorSelectPicActivity.class.getSimpleName();
    private final int REQUEST_CODE = 111;
    private TranslateAnimation animationCircleType;
    private Intent intent;
    private ProgressDialog dialog;

    @Override
    protected String title() {
        intent = getIntent();
        if (SponsorInfoActivity.ACTION_OUT_PIC.equals(intent.getAction())) {
            title = "门店照片";
            return title;
        } else if (SponsorInfoActivity.ACTION_INNER_PIC.equals(intent.getAction())) {
            title = "店内环境照片";
            return title;
        } else {
            return null;
        }
    }

    @Override
    public Object right() {
        return "确定";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor_select_pic);
        ButterKnife.bind(this);
        setToolBarListener(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("上传中");
        dialog.setCancelable(false);
        cachePath = Utils.getDiskCacheDir(this).getAbsolutePath();
        initAdapter();
    }


    private void initAdapter() {
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        if (SponsorInfoActivity.ACTION_OUT_PIC.equals(intent.getAction())) {
            adapter = new GridAddPicAdapter(this, 1);
        } else {
            adapter = new GridAddPicAdapter(this, MAX_SIZE);
        }
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


        popupSelectWindow.showAtLocation(findViewById(R.id.layout_select_pic), Gravity.CENTER, 0, 0);
        popBirthSelectView.startAnimation(animationCircleType);
    }

    private void selectPicture() {
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
                        .start(SponsorSelectPicActivity.this, REQUEST_CODE);
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
                if (SponsorInfoActivity.ACTION_OUT_PIC.equals(intent.getAction())) {
                    picker.maxNum(1);
                } else {
                    if ((MAX_SIZE - adapter.getData().size()) / 12 == 0) {
                        picker.maxNum((MAX_SIZE - adapter.getData().size()) % 12);
                    }else{
                        picker.maxNum(12);
                    }
                }
                picker.start(SponsorSelectPicActivity.this, REQUEST_CODE);
                popupCircleTypeWindow.dismiss();
            }
        });
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupCircleTypeWindow.showAtLocation(findViewById(R.id.layout_select_pic), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocalLog.d(TAG, "onActivityResult() enter");
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            SocializeUtils.safeShowDialog(dialog);
            List<ImageBean> resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
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
        Intent intent = getIntent();
        intent.putExtra("images", images);
        intent.putExtra("size", adapter.getData().size());
        setResult(2, intent);
        finish();
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
}
