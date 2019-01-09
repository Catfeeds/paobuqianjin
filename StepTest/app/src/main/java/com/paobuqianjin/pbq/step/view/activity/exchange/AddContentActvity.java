package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.paobuqianjin.pbq.step.adapter.GridAddPic2Adapter;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.data.alioss.AliOss;
import com.paobuqianjin.pbq.step.data.alioss.OssService;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExInOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.table.SelectPicBean;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.exchange.ExContentAdapter;
import com.umeng.socialize.utils.SocializeUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.model.RongGridView;

/**
 * Created by pbq on 2019/1/7.
 */

public class AddContentActvity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {
    private final static String TAG = AddContentActvity.class.getSimpleName();
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
    @Bind(R.id.good_pic)
    CircularImageView goodPic;
    @Bind(R.id.good_name)
    TextView goodName;
    @Bind(R.id.grid_view)
    RongGridView gridView;
    @Bind(R.id.review_h)
    ImageView reviewH;
    @Bind(R.id.review_d)
    ImageView reviewD;
    @Bind(R.id.review_l)
    ImageView reviewL;
    @Bind(R.id.des_recycler)
    SwipeMenuRecyclerView desRecycler;
    @Bind(R.id.trf_recycler)
    SwipeMenuRecyclerView trfRecycler;
    @Bind(R.id.taidu_recycler)
    SwipeMenuRecyclerView taiduRecycler;
    @Bind(R.id.content_edit)
    EditText contentEdit;
    private GridAddPic2Adapter adapter;
    private View popupCircleTypeView;
    private PopupWindow popupCircleTypeWindow;
    private String cachePath;
    public static final int MAX_SIZE = 9;
    private final int REQUEST_CODE = 111;
    private TranslateAnimation animationCircleType;
    ArrayList<ImageBean> resultList = new ArrayList<>();
    private ProgressDialog dialog;
    private ExContentAdapter exContentAdapterA, exContentAdapterB, exContentAdapterC;
    private boolean[] booleanA = {true, true, true, true, true};
    private boolean[] booleanB = {true, true, true, true, true};
    private boolean[] booleanC = {true, true, true, true, true};
    private ExInOrderResponse.DataBeanX.DataBean dataBean;
    private boolean[] selectPay = new boolean[3];
    private ImageView[] selectIcon = new ImageView[3];
    private int desA = 5, desB = 5, desC = 5;

    @Override
    protected String title() {
        return "发表评价";
    }

    @Override
    public Object right() {
        return "发表";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_content_activity);
        ButterKnife.bind(this);
        setToolBarListener(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("上传中");
        dialog.setCancelable(false);
        cachePath = Utils.getDiskCacheDir(this).getAbsolutePath();
        Intent intent = getIntent();
        if (intent != null) {
            dataBean = (ExInOrderResponse.DataBeanX.DataBean) intent.getSerializableExtra("comm_order_data");
            if (dataBean != null) {
                try {
                    Presenter.getInstance(this).getPlaceErrorImage(goodPic, dataBean.getImg_arr().get(0), R.drawable.null_bitmap,
                            R.drawable.null_bitmap);
                    goodName.setText(dataBean.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        initContent();
    }

    @OnClick({R.id.review_h, R.id.review_d, R.id.review_l})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.review_h:
                if (selectPay[0]) {
                    selectPay[0] = false;
                    selectIcon[0].setImageDrawable(getDrawableResource(R.drawable.good_review));
                } else {
                    UpdateUnSelect(0);
                    selectIcon[0].setImageDrawable(getDrawableResource(R.drawable.bad_review));
                    selectPay[0] = true;
                }
                break;
            case R.id.review_d:
                if (selectPay[1]) {
                    selectPay[1] = false;
                    selectIcon[1].setImageDrawable(getDrawableResource(R.drawable.good_review));
                } else {
                    UpdateUnSelect(1);
                    selectIcon[1].setImageDrawable(getDrawableResource(R.drawable.bad_review));
                    selectPay[1] = true;
                }
                break;
            case R.id.review_l:
                if (selectPay[2]) {
                    LocalLog.d(TAG, "点击选择钱包");
                    selectPay[2] = false;
                    selectIcon[2].setImageDrawable(getDrawableResource(R.drawable.good_review));
                } else {
                    LocalLog.d(TAG, "选择钱包,设置其他为未选中状态");
                    UpdateUnSelect(2);
                    selectIcon[2].setImageDrawable(getDrawableResource(R.drawable.bad_review));
                    selectPay[2] = true;
                }
                break;
        }
    }


    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
            outRect.left = space;
        }

    }

    private void initContent() {
        initAdapter();
        LinearLayoutManager linearLayoutManagerA = new LinearLayoutManager(this);
        linearLayoutManagerA.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManagerB = new LinearLayoutManager(this);
        linearLayoutManagerB.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManagerC = new LinearLayoutManager(this);
        linearLayoutManagerC.setOrientation(LinearLayoutManager.HORIZONTAL);

        exContentAdapterA = new ExContentAdapter(this, booleanA);
        exContentAdapterB = new ExContentAdapter(this, booleanB);
        exContentAdapterC = new ExContentAdapter(this, booleanC);
        desRecycler.setLayoutManager(linearLayoutManagerA);
        desRecycler.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (position < booleanA.length) {
                    booleanA[position] = !booleanA[position];
                    exContentAdapterA.notifyDataSetChanged();
                }
            }
        });
        desRecycler.setAdapter(exContentAdapterA);
        desRecycler.addItemDecoration(new SpaceItemDecoration(40));
        trfRecycler.setLayoutManager(linearLayoutManagerB);
        trfRecycler.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (position < booleanB.length) {
                    booleanB[position] = !booleanB[position];
                    exContentAdapterB.notifyDataSetChanged();
                }
            }
        });
        trfRecycler.setAdapter(exContentAdapterB);
        trfRecycler.addItemDecoration(new SpaceItemDecoration(40));
        taiduRecycler.setLayoutManager(linearLayoutManagerC);
        taiduRecycler.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (position < booleanC.length) {
                    booleanC[position] = !booleanC[position];
                    exContentAdapterC.notifyDataSetChanged();
                }

            }
        });
        taiduRecycler.addItemDecoration(new SpaceItemDecoration(40));
        taiduRecycler.setAdapter(exContentAdapterC);
        selectIcon[0] = reviewH;
        selectIcon[1] = reviewD;
        selectIcon[2] = reviewL;
        selectIcon[0].setImageDrawable(getDrawableResource(R.drawable.bad_review));
        selectPay[0] = true;

    }

    private void UpdateUnSelect(int i) {
        for (int j = 0; j < selectPay.length; j++) {
            if (j != i) {
                if (selectPay[j]) {
                    selectPay[j] = false;
                    selectIcon[j].setImageDrawable(getDrawableResource(R.drawable.good_review));
                }
            }
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
                        .start(AddContentActvity.this, REQUEST_CODE);
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
                picker.start(AddContentActvity.this, REQUEST_CODE);
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

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {
        LocalLog.d(TAG,"clickRight() ENTER");
        commit();
    }

    private void commit() {
        if (!TextUtils.isEmpty(dataBean.getComm_no())) {
            Map<String, String> param = new HashMap<>();
            param.put("comm_order_no", dataBean.getComm_no());
            if (!TextUtils.isEmpty(contentEdit.getText().toString().trim())) {
                param.put("content", contentEdit.getText().toString().trim());
            }

            String images = "";
            for (SelectPicBean bean : adapter.getData()) {
                if (!TextUtils.isEmpty(images)) {
                    images += ",";
                }
                images += bean.getImageUrl();
            }
            if (!TextUtils.isEmpty(images)) {
                param.put("images", images);
            }
            for (int i = 0; i < selectPay.length; i++) {
                if (selectPay[i]) {
                    param.put("comment_leavl", String.valueOf(i + 1));
                }
            }

            for (int i = 0; i < booleanA.length; i++) {
                if (!booleanA[i]) {
                    desA--;
                }
            }
            for (int i = 0; i < booleanB.length; i++) {
                if (!booleanB[i]) {
                    desB--;
                }
            }
            for (int i = 0; i < booleanC.length; i++) {
                if (!booleanC[i]) {
                    desC--;
                }
            }
            param.put("desc_star", String.valueOf(desA));
            param.put("express_star", String.valueOf(desB));
            param.put("server_star", String.valueOf(desC));

            Presenter.getInstance(this).postPaoBuSimple(NetApi.urlExPuContent, param, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    PaoToastUtils.showLongToast(AddContentActvity.this,"评论成功");
                    setResult(Activity.RESULT_OK);
                    finish();
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                }
            });
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
