package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutUserInfoParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoSetResponse;
import com.paobuqianjin.pbq.step.data.bean.table.User;
import com.paobuqianjin.pbq.step.data.tencent.yun.ObjectSample.PutObjectSample;
import com.paobuqianjin.pbq.step.data.tencent.yun.activity.ResultHelper;
import com.paobuqianjin.pbq.step.data.tencent.yun.common.QServiceCfg;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UserInfoLoginSetInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.SelectSettingAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.paobuqianjin.pbq.step.view.base.view.wheelpicker.WheelPicker;
import com.paobuqianjin.pbq.step.view.base.view.wheelpicker.widgets.WheelDatePicker;
import com.paobuqianjin.pbq.step.view.fragment.login.PersonInfoSettingFragment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2018/1/5.
 */

public class UserInfoSettingFragment extends BaseBarStyleTextViewFragment implements UserInfoLoginSetInterface {
    private final static String TAG = UserInfoSettingFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.head_ico)
    CircleImageView headIco;
    @Bind(R.id.go_pic)
    ImageView goPic;
    @Bind(R.id.user_head_icon_change)
    RelativeLayout userHeadIconChange;
    @Bind(R.id.line_change_ico)
    ImageView lineChangeIco;
    @Bind(R.id.go_pic1)
    ImageView goPic1;
    @Bind(R.id.user_name_change)
    RelativeLayout userNameChange;
    @Bind(R.id.line_change_male)
    ImageView lineChangeMale;
    @Bind(R.id.male_text)
    TextView maleText;
    @Bind(R.id.go_pic2)
    ImageView goPic2;
    @Bind(R.id.change_male)
    RelativeLayout changeMale;
    @Bind(R.id.birth_day)
    TextView birthDayTV;
    @Bind(R.id.go_pic3)
    ImageView goPic3;
    @Bind(R.id.change_birth)
    RelativeLayout changeBirth;
    @Bind(R.id.line_birth)
    ImageView lineBirth;
    @Bind(R.id.high_num)
    TextView highNum;
    @Bind(R.id.go_pic5)
    ImageView goPic5;
    @Bind(R.id.change_high)
    RelativeLayout changeHigh;
    @Bind(R.id.line_weight)
    ImageView lineWeight;
    @Bind(R.id.weight_num)
    TextView weightNum;
    @Bind(R.id.go_pic6)
    ImageView goPic6;
    @Bind(R.id.change_weight)
    RelativeLayout changeWeight;
    @Bind(R.id.line_change_birth)
    ImageView lineChangeBirth;
    @Bind(R.id.city_names)
    TextView cityNames;
    @Bind(R.id.go_pic4)
    ImageView goPic4;
    @Bind(R.id.change_city)
    RelativeLayout changeCity;
    @Bind(R.id.user_info_setting_fg)
    RelativeLayout userInfoSettingFg;
    @Bind(R.id.dear_name_setting)
    EditText dearNameSetting;
    PutUserInfoParam putUserInfoParam = new PutUserInfoParam();
    @Bind(R.id.confirm_setting)
    Button confirmSetting;
    private String localAvatar;

    private View popBirthSelectView;
    private View popWeighSelectView;
    private View popHighSelectView;
    private PopupWindow popupSelectWindow;
    private String birthYear = null;
    private String birthMonth = null;
    private String birthDay = null;
    private String high;
    private String weight;
    private View popupCircleTypeView;
    private PopupWindow popupCircleTypeWindow;
    private TranslateAnimation animationCircleType;
    ArrayList<String> weightList = new ArrayList<>();
    //50-250cm
    ArrayList<String> heightList = new ArrayList<>();

    QServiceCfg qServiceCfg;
    private final int REQUEST_CODE = 111;
    private String cachePath;
    private UserInfoResponse.DataBean userInfo;
    private boolean citySetFlag = false;

    @Override
    protected String title() {
        return "修改资料";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.user_info_setting_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        userHeadIconChange = (RelativeLayout) viewRoot.findViewById(R.id.user_head_icon_change);
        userNameChange = (RelativeLayout) viewRoot.findViewById(R.id.user_name_change);
        changeMale = (RelativeLayout) viewRoot.findViewById(R.id.change_male);
        changeBirth = (RelativeLayout) viewRoot.findViewById(R.id.change_birth);
        changeCity = (RelativeLayout) viewRoot.findViewById(R.id.change_city);
        headIco = (CircleImageView) viewRoot.findViewById(R.id.head_ico);
        changeHigh = (RelativeLayout) viewRoot.findViewById(R.id.change_high);
        changeWeight = (RelativeLayout) viewRoot.findViewById(R.id.change_weight);
        maleText = (TextView) viewRoot.findViewById(R.id.male_text);
        highNum = (TextView) viewRoot.findViewById(R.id.high_num);
        dearNameSetting = (EditText) viewRoot.findViewById(R.id.dear_name_setting);
        weightNum = (TextView) viewRoot.findViewById(R.id.weight_num);
        cityNames = (TextView) viewRoot.findViewById(R.id.city_names);


        qServiceCfg = QServiceCfg.instance(getContext());
        cachePath = getContext().getExternalCacheDir().getAbsolutePath();
        birthDayTV = (TextView) viewRoot.findViewById(R.id.birth_day);
        confirmSetting = (Button) viewRoot.findViewById(R.id.confirm_setting);

        LocalLog.d(TAG, "cachePath = " + cachePath);

        Intent intent = getActivity().getIntent();
        if (intent != null) {
            userInfo = (UserInfoResponse.DataBean) intent.getSerializableExtra("userinfo");
            if (userInfo != null) {
                Presenter.getInstance(getContext()).getImage(headIco, userInfo.getAvatar());
                dearNameSetting.setText(userInfo.getNickname());
                if (userInfo.getSex() == 0) {
                    maleText.setText("男");
                } else if (userInfo.getSex() == 1) {
                    maleText.setText("女");
                }
                birthDayTV.setText(userInfo.getBirthyear() + "年" + userInfo.getBirthmonth() + "月" + userInfo.getBirthday() + "日");
                highNum.setText(String.valueOf(userInfo.getHeight()) + "cm");
                weightNum.setText(String.valueOf(userInfo.getWeight()) + "kg");
                cityNames.setText(String.valueOf(userInfo.getCity()));
                LocalLog.d(TAG, "ID = " + userInfo.getId());
            }
        }
        setOnClickListener();
        initData();
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    private void initData() {
        int high = 50;
        while (high < 250) {
            heightList.add(String.valueOf(high));
            high += 5;
        }

        float weight = 10.0f;
        while (weight < 250.0f) {
            weightList.add(String.valueOf(weight));
            weight += 0.5;
        }
    }

    private void setOnClickListener() {
        userHeadIconChange.setOnClickListener(onClickListener);
        userNameChange.setOnClickListener(onClickListener);
        changeMale.setOnClickListener(onClickListener);
        changeBirth.setOnClickListener(onClickListener);
        changeCity.setOnClickListener(onClickListener);
        changeHigh.setOnClickListener(onClickListener);
        changeWeight.setOnClickListener(onClickListener);
        confirmSetting.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LocalLog.d(TAG, "onClick() enter");
            switch (view.getId()) {
                case R.id.user_head_icon_change:
                    LocalLog.d(TAG, "设置头像");
                    selectPicture();
                    break;
                case R.id.change_male:
                    LocalLog.d(TAG, "设置性别");
                    ArrayList<String> male = new ArrayList<>();
                    male.add("男");
                    male.add("女");
                    selectType(male);
                    break;
                case R.id.change_birth:
                    LocalLog.d(TAG, "设置生日");
                    setBirthDay();
                    break;
                case R.id.change_city:
                    LocalLog.d(TAG, "设置城市");
                    CityListLoader.getInstance().loadProData(getContext());
                    CityPickerView.getInstance().setConfig(new CityConfig.Builder(getContext()).build());
                    CityPickerView.getInstance().setOnCityItemClickListener(new OnCityItemClickListener() {
                        @Override
                        public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

                            //省份
                            if (province != null) {

                            }

                            //城市
                            if (city != null) {

                            }

                            //地区
                            if (district != null) {

                            }
                            LocalLog.d(TAG, province.getName() + "· " + city.getName() + "·" + district.getName());
                            putUserInfoParam.setCity(province.getName() + "· " + city.getName() + "·" + district.getName());
                            cityNames.setText(province.getName() + "· " + city.getName() + "·" + district.getName());

                        }

                        @Override
                        public void onCancel() {
                            ToastUtils.showLongToast(getContext(), "已取消");
                        }
                    });

                    //显示
                    CityPickerView.getInstance().showCityPicker(getContext());
                    break;
                case R.id.change_high:
                    LocalLog.d(TAG, "设置身高");
                    setHigh();
                    break;
                case R.id.change_weight:
                    LocalLog.d(TAG, "设置体重");
                    setWeight();
                    break;
                case R.id.confirm_setting:
                    LocalLog.d(TAG, "确认修改");
                    if (localAvatar != null) {
                        LogoUpTask logoUpTask = new LogoUpTask();
                        logoUpTask.execute(localAvatar);
                    } else {
                        if (dearNameSetting.getText() == null && "".equals(dearNameSetting.getText().toString())) {
                            Toast.makeText(getContext(), "昵称不能为空", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!userInfo.getNickname().equals(dearNameSetting.getText().toString())) {
                            putUserInfoParam.setNickname(dearNameSetting.getText().toString());
                        }
                        Presenter.getInstance(getContext()).putUserInfo(userInfo.getId(), putUserInfoParam);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocalLog.d(TAG, "onActivityResult() enter");
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<ImageBean> resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            String content = "";
            for (ImageBean imageBean : resultList) {
                content = content + imageBean.toString() + "\n";
            }
            LocalLog.d(TAG, "content = " + content);
            if (resultList != null && resultList.size() == 1) {
                Presenter.getInstance(getContext()).getImage(resultList.get(0).getImagePath(), headIco, resultList.get(0).getWidth() / 4
                        , resultList.get(0).getHeight() / 4);
                localAvatar = resultList.get(0).getImagePath();
            } else {
                LocalLog.d(TAG, "未知操作");
            }
            return;
        }

    }
   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE) {
            LocalLog.d(TAG, "PICTURE OK");
            if (data != null) {

                //TODO 线程中上传保存*//*
                Uri selectedImage = data.getData();

                final String pathResult = getPath(selectedImage);
                LocalLog.d(TAG, "pathResult = " + pathResult);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                Bitmap docodeFile = BitmapFactory.decodeFile(pathResult, options);


                // 获取到这个图片的原始宽度和高度
                int picWidth = options.outWidth;
                int picHeight = options.outHeight;
                LocalLog.d(TAG, "options.outWidth = " + options.outWidth + "options.outHeight = " + options.outHeight);

                // 获取屏的宽度和高度
                WindowManager windowManager = getActivity().getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                int screenWidth = display.getWidth();
                int screenHeight = display.getHeight();

                LocalLog.d(TAG, "screenWidth =  " + screenWidth + ",screenHeight = " + screenHeight);
                // isSampleSize是表示对图片的缩放程度，比如值为2图片的宽度和高度都变为以前的1/2
                options.inSampleSize = 1;
                // 根据屏的大小和图片大小计算出缩放比例
                if (picWidth > picHeight) {
                    if (picWidth > screenWidth)
                        options.inSampleSize = picWidth / screenWidth;
                } else {
                    if (picHeight > screenHeight)
                        options.inSampleSize = picHeight / screenHeight;
                }

                // 这次再真正地生成一个有像素的，经过缩放了的bitmap
                options.inJustDecodeBounds = false;
                //docodeFile = BitmapFactory.decodeFile(pathResult, options);
                Presenter.getInstance(getContext()).getImage(pathResult, headIco);
                //logoCirclePic.setImageBitmap(docodeFile);
                LogoUpTask logoUpTask = new LogoUpTask();
                logoUpTask.execute(pathResult);
            }
*//*        if (requestCode == REQUEST_CODE_TAG) {
                if (data != null) {
                    ArrayList<String> tags = data.getStringArrayListExtra("tag");
                    if (tags != null) {
                        if (tags.size() == 2) {
                            flagA1.setText(tags.get(0));
                            flagA0.setText(tags.get(1));
                            flagA1.setVisibility(View.VISIBLE);
                            flagA0.setVisibility(View.VISIBLE);
                        } else if (tags.size() == 1) {
                            flagA1.setText(tags.get(0));
                            flagA1.setVisibility(View.VISIBLE);
                        }
                    }
                    ArrayList<String> ids = data.getStringArrayListExtra("id");
                    if (ids != null) {
                        if (ids.size() == 2) {
                            LocalLog.d(TAG, ids.get(0));
                            LocalLog.d(TAG, ids.get(1));
                            createCircleBodyParam.setTags(ids.get(0) + "," + ids.get(1));
                        } else if (ids.size() == 1) {
                            LocalLog.d(TAG, ids.get(0));
                            createCircleBodyParam.setTags(ids.get(0));
                        }
                    }

                }
        }*//*
        }
    }*/


    public class LogoUpTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = null;
            for (String path : strings) {
                LocalLog.d(TAG, "path = " + path);
                ResultHelper result = null;
                PutObjectSample putObjectSample = new PutObjectSample(qServiceCfg);
                result = putObjectSample.start(path);
                //LocalLog.d(TAG, "result = " + result.cosXmlResult.printError());
                url = result.cosXmlResult.accessUrl;
                LocalLog.d(TAG, "url = " + url);

            }
            return url;
        }

        @Override
        protected void onPostExecute(String s) {
            LocalLog.d(TAG, "onPostExecute() enter");
            super.onPostExecute(s);
            //SocializeUtils.safeCloseDialog(dialog);
            putUserInfoParam.setAvatar(s);
            Presenter.getInstance(getContext()).putUserInfo(userInfo.getId(), putUserInfoParam);
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
                return getDataColumn(getContext(), contentUri, selection, selectionArgs);
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
            return getDataColumn(getContext(), uri, null, null);
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

    private void saveImage(Bitmap bitmap, String sourcePath) throws FileNotFoundException {
        String path = getContext().getExternalCacheDir() + "/head_logo.png";
        LocalLog.d(TAG, "path = " + path);
        FileOutputStream fos = new FileOutputStream(path);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

    }

    private void selectPicture() {
        popupCircleTypeView = View.inflate(getContext(), R.layout.select_camera_pic, null);
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
                        .start(UserInfoSettingFragment.this, REQUEST_CODE);
                popupCircleTypeWindow.dismiss();
            }
        });
        ((RelativeLayout) popupCircleTypeView.findViewById(R.id.xiangche_camera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "相册");
                new ImagePicker()
                        .pickType(ImagePickType.MULTI)//设置选取类型(拍照、单选、多选)
                        .maxNum(1)//设置最大选择数量(拍照和单选都是1，修改后也无效)
                        .needCamera(true)//是否需要在界面中显示相机入口(类似微信)
                        .cachePath(cachePath)//自定义缓存路径
                        .displayer(new GlideImagePickerDisplayer())//自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                        .start(UserInfoSettingFragment.this, REQUEST_CODE);
                popupCircleTypeWindow.dismiss();
            }
        });
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupCircleTypeWindow.showAtLocation(getActivity().findViewById(R.id.user_info_setting_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);
    }


    private void setBirthDay() {
        LocalLog.d(TAG, "setBirthDay() 生日选择框");
        popBirthSelectView = View.inflate(getContext(), R.layout.wheel_select_layout, null);
        popupSelectWindow = new PopupWindow(popBirthSelectView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popRedPkgButton dismiss() ");
                popupSelectWindow = null;
            }
        });
        Button confirmBt = (Button) popBirthSelectView.findViewById(R.id.confirm);
        Button cancelBt = (Button) popBirthSelectView.findViewById(R.id.cancel);
        final WheelDatePicker wheelDatePicker = (WheelDatePicker) popBirthSelectView.findViewById(R.id.date_picker);
        wheelDatePicker.setOnDateSelectedListener(new WheelDatePicker.OnDateSelectedListener() {
            @Override
            public void onDateSelected(WheelDatePicker picker, Date date) {
                birthYear = DateTimeUtil.formatDateTime(date, "yyyy");
                birthMonth = DateTimeUtil.formatDateTime(date, "MM");
                birthDay = DateTimeUtil.formatDateTime(date, "dd");
            }
        });
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "确认");
                birthDayTV.setText(birthYear + "年" + birthMonth + "月" + birthDay + "日");

                putUserInfoParam.setBirthyear(birthYear).setBirthmonth(birthMonth).setBirthday(birthDay);
                popupSelectWindow.dismiss();
            }
        });
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "取消");
                birthYear = null;
                birthMonth = null;
                birthDay = null;
                //TODO  使用默认的生日
                popupSelectWindow.dismiss();
            }
        });
        popupSelectWindow.setFocusable(true);
        popupSelectWindow.setOutsideTouchable(true);

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.user_info_setting_fg), Gravity.CENTER_HORIZONTAL, 0, 0);
        popBirthSelectView.startAnimation(animationCircleType);
    }

    public void setWeight() {
        LocalLog.d(TAG, "setWeight() 选择框");
        popWeighSelectView = View.inflate(getContext(), R.layout.wheel_weight_select_layout, null);
        popupSelectWindow = new PopupWindow(popWeighSelectView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popupSelectWindow dismiss() ");
                popupSelectWindow = null;
            }
        });

        Button confirmBt = (Button) popWeighSelectView.findViewById(R.id.confirm);
        Button cancelBt = (Button) popWeighSelectView.findViewById(R.id.cancel);
        final WheelPicker wheelWeigthPicker = (WheelPicker) popWeighSelectView.findViewById(R.id.weigth_picker);
        wheelWeigthPicker.setData(weightList);
        wheelWeigthPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                weight = (String) data;
            }
        });
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "确认");
                weightNum.setText(weight + "公斤");
                popupSelectWindow.dismiss();
            }
        });
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "取消");
                popupSelectWindow.dismiss();
            }
        });


        popupSelectWindow.setFocusable(true);
        popupSelectWindow.setOutsideTouchable(true);

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.user_info_setting_fg), Gravity.CENTER_HORIZONTAL, 0, 0);
        popWeighSelectView.startAnimation(animationCircleType);
    }

    public void setHigh() {
        LocalLog.d(TAG, "setWeight() 选择框");
        popHighSelectView = View.inflate(getContext(), R.layout.wheel_high_select_layout, null);
        popupSelectWindow = new PopupWindow(popHighSelectView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popupSelectWindow dismiss() ");
                popupSelectWindow = null;
            }
        });

        Button confirmBt = (Button) popHighSelectView.findViewById(R.id.confirm);
        Button cancelBt = (Button) popHighSelectView.findViewById(R.id.cancel);
        final WheelPicker wheelHighPicker = (WheelPicker) popHighSelectView.findViewById(R.id.high_picker);
        wheelHighPicker.setData(heightList);
        wheelHighPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                LocalLog.d(TAG, (String) data);
                high = (String) data;

            }
        });
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "确认");
                highNum.setText(high + "cm");
                putUserInfoParam.setHeight(high);
                popupSelectWindow.dismiss();
            }
        });
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "取消");
                popupSelectWindow.dismiss();
            }
        });

        popupSelectWindow.setFocusable(true);
        popupSelectWindow.setOutsideTouchable(true);

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.user_info_setting_fg), Gravity.CENTER_HORIZONTAL, 0, 0);
        popHighSelectView.startAnimation(animationCircleType);
    }


    //单选项
    public void selectType(ArrayList<String> strings) {
        final SelectSettingAdapter selectSettingAdapter = new SelectSettingAdapter(getContext(), strings);

        popupCircleTypeView = View.inflate(getContext(), R.layout.select_dialog_layout, null);
        popupCircleTypeWindow = new PopupWindow(popupCircleTypeView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupCircleTypeWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popupCircleTypeWindow onDismiss() enter");
                popupCircleTypeWindow = null;
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        popupCircleTypeWindow.setFocusable(true);

        popupCircleTypeWindow.setOutsideTouchable(true);

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);
        popupCircleTypeView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                LocalLog.d(TAG, "onClick() 取消");
                if (popupCircleTypeWindow.isShowing()) {
                    popupCircleTypeWindow.dismiss();
                    popupCircleTypeWindow = null;
                }
            }
        });

        popupCircleTypeView.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "onClick() 确定");
                String selectString = selectSettingAdapter.getSelectContent();
                LocalLog.d(TAG, "选择结果: " + selectString);
                if ("男".equals(selectString)) {
                    putUserInfoParam.setSex(0);
                } else {
                    putUserInfoParam.setSex(1);
                }
                maleText.setText(selectString);
                if (popupCircleTypeWindow.isShowing()) {
                    popupCircleTypeWindow.dismiss();
                    popupCircleTypeWindow = null;
                }

            }
        });
        final RecyclerView recyclerView = (RecyclerView) popupCircleTypeView.findViewById(R.id.select_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(selectSettingAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                LocalLog.d(TAG, "OnItemClick() enter " + position);
                int lastSelectPosition = selectSettingAdapter.getSelectPosition();
                selectSettingAdapter.setSelectPosition(position);
                recyclerView.getAdapter().notifyItemChanged(position);
                recyclerView.getAdapter().notifyItemChanged(lastSelectPosition);
            }

            @Override
            public void OnItemLongClick(View view, int position) {
                LocalLog.d(TAG, "OnItemLongClick() enter " + position);
            }
        }));

        popupCircleTypeWindow.showAtLocation(getActivity().findViewById(R.id.user_info_setting_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);
    }

    @Override
    public void response(UserInfoSetResponse userInfoSetResponse) {
        if (userInfoSetResponse.getError() == 0) {
            Toast.makeText(getContext(), userInfoSetResponse.getMessage(), Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }else if(userInfoSetResponse.getError() == -100){
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }
}
