package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.GalleryActivity;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorInfoActivity;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorTMapActivity;
import com.paobuqianjin.pbq.step.adapter.DynamicAddPicAdapter;
import com.paobuqianjin.pbq.step.adapter.GridAddPicAdapter;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostDynamicParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReleaseDynamicResponse;
import com.paobuqianjin.pbq.step.data.bean.table.SelectPicBean;
import com.paobuqianjin.pbq.step.data.tencent.yun.ObjectSample.PutObjectSample;
import com.paobuqianjin.pbq.step.data.tencent.yun.activity.ResultHelper;
import com.paobuqianjin.pbq.step.data.tencent.yun.common.QServiceCfg;
import com.paobuqianjin.pbq.step.model.broadcast.StepLocationReciver;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.ReleaseDynamicInterface;
import com.paobuqianjin.pbq.step.utils.LoadBitmap;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.umeng.socialize.utils.SocializeUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2018/1/9.
 */

public class DynamicCreateFragment extends BaseBarStyleTextViewFragment implements ReleaseDynamicInterface {
    private final static String TAG = DynamicCreateFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.dynamic_content)
    EditText dynamicContent;
    @Bind(R.id.pic_a)
    ImageView picA;
    @Bind(R.id.pic_b)
    ImageView picB;
    @Bind(R.id.pic_c)
    ImageView picC;
    @Bind(R.id.pic_d)
    ImageView picD;
    @Bind(R.id.pic_span)
    LinearLayout picSpan;
    @Bind(R.id.dynamic_span)
    RelativeLayout dynamicSpan;
    @Bind(R.id.line)
    ImageView line;
    @Bind(R.id.location_ico)
    ImageView locationIco;
    @Bind(R.id.location_span)
    RelativeLayout locationSpan;
    @Bind(R.id.dynamic_create)
    RelativeLayout dynamicCreate;
    @Bind(R.id.grid_view)
    GridView gridView;
    private View popupCircleTypeView;
    private PopupWindow popupCircleTypeWindow;
    private TranslateAnimation animationCircleType;
    private Rationale mRationale;
    private PermissionSetting mSetting;

    private final static int CAMERA_PIC = 0;
    @Bind(R.id.location_str)
    TextView locationStr;
    private QServiceCfg qServiceCfg;
    List<Bitmap> bitmaps;
    List<String> picPath;
    List<String> netPath;
    private ProgressDialog dialog;
    PostDynamicParam postDynamicParam = new PostDynamicParam();
    private StepLocationReciver stepLocationReciver = new StepLocationReciver();
    private final static String LOCATION_ACTION = "com.paobuqianjin.intent.ACTION_LOCATION";
    private String cachePath;
    private final int REQUEST_CODE = 111;
    ArrayList<ImageBean> mResultList = new ArrayList<>();
    private int picIndex = 0;
    public static final int REQ_POSITION = 3;
    public static final int RES_POSITION = 4;

    private String city;
    private double latitude, longitude;
    private String address;
    private DynamicAddPicAdapter adapter;

    @Override
    protected String title() {
        return "发布动态";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dynamic_create_fg;
    }

    @Override
    public Object right() {
        return "发布";
    }

    @Override
    public void response(ReleaseDynamicResponse releaseDynamicResponse) {
        LocalLog.d(TAG, "ReleaseDynamicResponse() enter" + releaseDynamicResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (releaseDynamicResponse.getError() == 0) {
            Toast.makeText(getContext(), "发布成功", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        } else if (releaseDynamicResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            Toast.makeText(getContext(), releaseDynamicResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LOCATION_ACTION);
        getContext().registerReceiver(stepLocationReciver, intentFilter);
    }

    private BaseBarImageViewFragment.ToolBarListener toolBarListener = new BaseBarImageViewFragment.ToolBarListener() {
        @Override
        public void clickLeft() {
            getActivity().onBackPressed();
        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "发布");
            String content = dynamicContent.getText().toString();
            if (mResultList == null || mResultList.size() == 0) {
                LocalLog.d(TAG, "没有图片");
                if (content.equals("")) {
                    Toast.makeText(getContext(), "没有内容", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (!TextUtils.isEmpty(city) && !TextUtils.isEmpty(locationStr.getText().toString())) {
                        if (city.equals(locationStr.getText().toString())) {
                            postDynamicParam.setCity(city);
                        } else {
                            postDynamicParam.setCity(city).setPosition(locationStr.getText().toString());
                        }
                    } else {
                        postDynamicParam.setCity(locationStr.getText().toString());
                    }
                    postDynamicParam
                            .setDynamic(content)
                            .setUserid(Presenter.getInstance(getContext()).getId());
                    Presenter.getInstance(getContext()).postDynamic(postDynamicParam);
                }
            } else {
                for (int i = 0; i < mResultList.size(); i++) {
                    picPath.add(mResultList.get(i).getImagePath());
                }
                if (picPath.size() > 0) {
                    LogoUpTask logoUpTask = new LogoUpTask();
                    logoUpTask.execute(picPath);
                }

            }
        }
    };

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        ButterKnife.bind(this, viewRoot);
        setToolBarListener(toolBarListener);
        dynamicContent = (EditText) viewRoot.findViewById(R.id.dynamic_content);
        picA = (ImageView) viewRoot.findViewById(R.id.pic_a);
        picB = (ImageView) viewRoot.findViewById(R.id.pic_b);
        picC = (ImageView) viewRoot.findViewById(R.id.pic_c);
        picD = (ImageView) viewRoot.findViewById(R.id.pic_d);
        locationStr = (TextView) viewRoot.findViewById(R.id.location_str);
        bitmaps = new ArrayList<>();
        picPath = new ArrayList<>();
        netPath = new ArrayList<>();
        qServiceCfg = QServiceCfg.instance(getContext());
        dialog = new ProgressDialog(getContext());
        Presenter.getInstance(getContext()).startService(null, LocalBaiduService.class);
        cachePath = getContext().getExternalCacheDir().getAbsolutePath();
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(getContext());
        initAdapter();
    }

    private void initAdapter() {
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new DynamicAddPicAdapter(getContext(), 4, mResultList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == adapter.getData().size()) {
                    //点击+
                    picIndex = mResultList.size();
                    requestPermission(Permission.Group.STORAGE);
                } else {
                    Intent intent = new Intent(getContext(), GalleryActivity.class);
                    intent.putParcelableArrayListExtra("list", mResultList);
                    intent.putExtra("ID", position);
                    getContext().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.activity_photo_enter, -1);

                    //点击图片查看大图
                    //  popImageView(adapter.getData().get(position).getImageUrl());
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
        getContext().unregisterReceiver(stepLocationReciver);
    }

    @OnClick({R.id.pic_a, R.id.pic_b, R.id.pic_c, R.id.pic_d, R.id.location_span})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pic_a:
                picIndex = 0;
                requestPermission(Permission.Group.STORAGE);
                break;
            case R.id.pic_b:
                picIndex = 1;
                requestPermission(Permission.Group.STORAGE);
                break;
            case R.id.pic_c:
                picIndex = 2;
                requestPermission(Permission.Group.STORAGE);
                break;
            case R.id.pic_d:
                picIndex = 3;
                requestPermission(Permission.Group.STORAGE);
                break;
            case R.id.location_span:
                LocalLog.d(TAG, "开启定位");
                Intent intent = new Intent();
                intent.putExtra("lat", latitude);
                intent.putExtra("lng", longitude);
                intent.setClass(getContext(), SponsorTMapActivity.class);
                startActivityForResult(intent, REQ_POSITION);
                break;
        }
    }



    /*权限适配*/

    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        LocalLog.d(TAG, "获取权限成功");
                        selectPicture(picIndex);
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(getActivity(), permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    private void selectPicture(final int index) {
        hideSoftInput(dynamicContent.getWindowToken());
        popupCircleTypeView = View.inflate(getContext(), R.layout.select_camera_pic, null);
        //点击其他地方消失
        popupCircleTypeView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                popupCircleTypeWindow.dismiss();
                return false;
            }
        });
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
                        .start(DynamicCreateFragment.this, REQUEST_CODE);
                popupCircleTypeWindow.dismiss();
            }
        });
        ((RelativeLayout) popupCircleTypeView.findViewById(R.id.xiangche_camera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "相册");
                new ImagePicker()
                        .pickType(ImagePickType.MULTI)//设置选取类型(拍照、单选、多选)
                        .maxNum(4 - index)//设置最大选择数量(拍照和单选都是1，修改后也无效)
                        .needCamera(true)//是否需要在界面中显示相机入口(类似微信)
                        .cachePath(cachePath)//自定义缓存路径
                        .displayer(new GlideImagePickerDisplayer())//自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                        .start(DynamicCreateFragment.this, REQUEST_CODE);
                popupCircleTypeWindow.dismiss();
            }
        });
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupCircleTypeWindow.showAtLocation(getActivity().findViewById(R.id.dynamic_create), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* if (resultCode != RESULT_OK) {
            return;
        }*/

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<ImageBean> resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            String content = "";
            for (ImageBean imageBean : resultList) {
                content = content + imageBean.toString() + "\n";
            }
            LocalLog.d(TAG, "content = " + content);
            if (resultList != null && resultList.size() != 0) {
                int size = resultList.size();
                int resultSize = mResultList.size();
                LocalLog.d(TAG, "size = " + size + ",resultSize = " + resultSize);
                mResultList.addAll(resultList);
                adapter.notifyDataSetChanged();
              /*  if (resultSize == 0) {
                    mResultList = resultList;
                } else {
                    for (int i = 0; i < size; i++) {
                        if (resultSize > picIndex + i) {
                            mResultList.get(picIndex + i).setImagePath(resultList.get(i).getImagePath());
                        } else {
                            mResultList.add(resultList.get(i));
                        }
                    }
                }
                resultSize = mResultList.size();
                if (resultSize == 1) {
                    //TODO a bug
                    showA(mResultList.get(0).getImagePath());
                } else if (resultSize == 2) {
                    showA(mResultList.get(0).getImagePath());
                    showB(mResultList.get(1).getImagePath());
                } else if (resultSize == 3) {
                    showA(mResultList.get(0).getImagePath());
                    showB(mResultList.get(1).getImagePath());
                    showC(mResultList.get(2).getImagePath());
                } else if (resultSize == 4) {
                    showA(mResultList.get(0).getImagePath());
                    showB(mResultList.get(1).getImagePath());
                    showC(mResultList.get(2).getImagePath());
                    showD(mResultList.get(3).getImagePath());
                    picD.setVisibility(View.VISIBLE);
                }*/
            }
            return;
        }

        if (requestCode == REQ_POSITION && resultCode == RES_POSITION) {
            String choiceCity = data.getStringExtra("city");
            if (!TextUtils.isEmpty(choiceCity)) {
                city = choiceCity;
            }
            latitude = data.getDoubleExtra("latitude", 0);
            longitude = data.getDoubleExtra("longitude", 0);
            address = data.getStringExtra("address");
            locationStr.setText(address);

            LocalLog.d(TAG, address);
        }
    }


    private void addResult(int pageIndex, int size) {

    }

    private void showA(String imagePath) {
        /*Presenter.getInstance(getContext()).getImage(imagePath, picA);*/
        LoadBitmap.glideLoad(getActivity(), picA, imagePath);
        picB.setVisibility(View.VISIBLE);
    }

    private void showB(String imagePath) {
        /*Presenter.getInstance(getContext()).getImage(imagePath, picB);*/
        LoadBitmap.glideLoad(getActivity(), picB, imagePath);
        picC.setVisibility(View.VISIBLE);
    }

    private void showC(String imagePath) {
        /*Presenter.getInstance(getContext()).getImage(imagePath, picC);*/
        LoadBitmap.glideLoad(getActivity(), picC, imagePath);
        picD.setVisibility(View.VISIBLE);
    }

    private void showD(String imagePath) {
        /*Presenter.getInstance(getContext()).getImage(imagePath, picD);*/
        LoadBitmap.glideLoad(getActivity(), picD, imagePath);
    }

    public class LogoUpTask extends AsyncTask<List<String>, Integer, List<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        protected List<String> doInBackground(List<String>[] lists) {
            LocalLog.d(TAG, "length  =" + lists.length);
            List<String> url = new ArrayList<>();
            for (int i = 0; i < lists.length; i++) {
                for (int j = 0; j < lists[i].size(); j++) {
                    LocalLog.d(TAG, "path = " + lists[i].get(j));
                    ResultHelper result = null;
                    PutObjectSample putObjectSample = new PutObjectSample(qServiceCfg);
                    if (getContext() != null) {
                        result = putObjectSample.start(lists[i].get(j), getContext().getApplicationContext());
                        if (result != null && result.cosXmlResult != null) {
                            url.add(result.cosXmlResult.accessUrl);
                        }
                    }
                }
            }
            return url;
        }

        @Override
        protected void onPostExecute(List<String> s) {
            LocalLog.d(TAG, "onPostExecute() enter");
            super.onPostExecute(s);
            String images = "";
            for (int i = 0; i < s.size(); i++) {
                LocalLog.d(TAG, "s[" + i + "] =" + s.get(i));
                if (i == 0) {
                    images += s.get(i);
                } else {
                    images += "," + s.get(i);
                }
            }


            netPath = s;
            if (!isAdded() || dynamicContent == null) {
                return;
            }
            String content = dynamicContent.getText().toString();
            if (!TextUtils.isEmpty(city) && !TextUtils.isEmpty(locationStr.getText().toString())) {
                if (city.equals(locationStr.getText().toString())) {
                    postDynamicParam.setCity(city);
                } else {
                    postDynamicParam.setCity(city).setPosition(locationStr.getText().toString());
                }
            } else {
                postDynamicParam.setCity(locationStr.getText().toString());
            }
            postDynamicParam.setDynamic(content)
                    .setUserid(Presenter.getInstance(getContext()).getId())
                    .setImages(images);
            Presenter.getInstance(getContext()).postDynamic(postDynamicParam);
            SocializeUtils.safeCloseDialog(dialog);
        }
    }

    private String getPath(Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= 19) {
            LocalLog.d(TAG, "uri auth:" + uri.getAuthority());

            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(getContext(), contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("img".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("auto".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(getActivity(), contentUri, selection, selectionArgs);
            } else if (isMedia(uri)) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor actualimagecursor = getActivity().managedQuery(uri, proj, null, null, null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();
                return actualimagecursor.getString(actual_image_column_index);
            } else if (isXiaoMi(uri)) {
                LocalLog.d(TAG, "小米手机相册 enter()");
                LocalLog.d(TAG, uri.toString());
                if ("content".equalsIgnoreCase(uri.getScheme())) {
                    return uri.getLastPathSegment();
                }
                return uri.getPath();
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotos(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(getActivity(), uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }


    private String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.downloads.documents".equals(uri.getAuthority());
    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private boolean isMedia(Uri uri) {
        return "media".equals(uri.getAuthority());
    }

    public boolean isGooglePhotos(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public boolean isXiaoMi(Uri uri) {
        LocalLog.d(TAG, uri.getAuthority());
        return "com.miui.gallery.open".equals(uri.getAuthority());
    }

    private void saveImage(Bitmap bitmap) throws FileNotFoundException {
        String path = getActivity().getExternalCacheDir() + "/head_logo.png";
        LocalLog.d(TAG, "path = " + path);
        FileOutputStream fos = new FileOutputStream(path);
        //bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
    }

    @Override
    public void response(String city, double latitude, double longitude) {
        this.city = city;
        locationStr.setText(city);
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (!isAdded()) {
            return;
        }
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }
}
