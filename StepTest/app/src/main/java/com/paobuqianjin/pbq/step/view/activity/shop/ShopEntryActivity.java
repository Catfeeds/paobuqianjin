package com.paobuqianjin.pbq.step.view.activity.shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorSelectPicActivity;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorTMapActivity;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopEntryRePayResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.AddAroundRedBagActivity;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2019/1/15.
 */

public class ShopEntryActivity extends BaseBarActivity {
    private final static String TAG = ShopEntryActivity.class.getSimpleName();
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
    @Bind(R.id.edit_sponsor_name)
    EditText editSponsorName;
    @Bind(R.id.sponsor_name_pan)
    RelativeLayout sponsorNamePan;
    @Bind(R.id.edit_sponsor_out_pics)
    CircleImageView editSponsorOutPics;
    @Bind(R.id.sponsor_out_pics_pan)
    RelativeLayout sponsorOutPicsPan;
    @Bind(R.id.edit_sponsor_phone)
    EditText editSponsorPhone;
    @Bind(R.id.sponsor_phone_pan)
    RelativeLayout sponsorPhonePan;
    @Bind(R.id.edit_sponsor_time)
    TextView editSponsorTime;
    @Bind(R.id.edit_sponsor_day)
    TextView editSponsorDay;
    @Bind(R.id.edit_sponsor_hour)
    TextView editSponsorHour;
    @Bind(R.id.sponsor_time_pan)
    RelativeLayout sponsorTimePan;
    @Bind(R.id.edit_sponsor_location_pan)
    TextView editSponsorLocationPan;
    @Bind(R.id.go_to_location)
    ImageView goToLocation;
    @Bind(R.id.sponsor_location_pan)
    RelativeLayout sponsorLocationPan;
    @Bind(R.id.tv_adress_title)
    TextView tvAdressTitle;
    @Bind(R.id.edit_sponsor_location_detail_pan)
    EditText editSponsorLocationDetailPan;
    @Bind(R.id.sponsor_location_detail_pan)
    RelativeLayout sponsorLocationDetailPan;
    @Bind(R.id.edit_sponsor_inner_pics)
    TextView editSponsorInnerPics;
    @Bind(R.id.sponsor_inner_pics_pan)
    RelativeLayout sponsorInnerPicsPan;
    @Bind(R.id.edit_sponsor_invite_code)
    EditText editSponsorInviteCode;
    @Bind(R.id.sponsor_invite_code_pan)
    RelativeLayout sponsorInviteCodePan;
    @Bind(R.id.text_content)
    TextView textContent;
    @Bind(R.id.sponsors)
    EditText sponsors;
    @Bind(R.id.btn_confirm)
    Button btnConfirm;

    public final static int REQ_PIC = 3;
    public final static int REQ_PIC_IN = 4;
    public final static String ACTION_INNER_PIC = "com.paobuqianjin.pbq.step.INNER_ACTION";
    public final static String ACTION_OUT_PIC = "com.paobuqianjin.pbq.step.OUT_ACTION";
    private final static int REQ_POSITION = 101;
    private String images;
    private String imagesIn;
    //详细地址
    private String longitude;
    private String latitude;
    private PermissionSetting mSetting;
    private String city;
    private String inviteCode;
    private final static int SHOP_ENTRY = 1001;
    private final static String PAY_ACTION = "android.intent.action.PAY";
    private final static String PAY_FOR_STYLE = "pay_for_style";
    private final static String CIRCLE_RECHARGE = "pay";
    private final static String CIRCLE_ID = "id";

    @Override
    protected String title() {
        return "商家入驻";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_entry_activity_layout);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlShopEntryCode, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    inviteCode = new JSONObject(s).getJSONObject("data").getString("inviter_no");
                    if (!TextUtils.isEmpty(inviteCode) && !"0".equals(inviteCode)) {
                        editSponsorInviteCode.setText(inviteCode);
                        editSponsorInviteCode.setEnabled(false);
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

    @OnClick({R.id.sponsor_location_pan, R.id.sponsor_out_pics_pan,
            R.id.sponsor_inner_pics_pan, R.id.edit_sponsor_location_pan, R.id.btn_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sponsor_out_pics_pan:
                LocalLog.d(TAG, "logo");
                Intent intent = new Intent();
                intent.setAction(ACTION_OUT_PIC);
                intent.setClass(this, SponsorSelectPicActivity.class);
                intent.putExtra("images", images);
                startActivityForResult(intent, REQ_PIC);
                break;
            case R.id.sponsor_inner_pics_pan:
                LocalLog.d(TAG, "店内环境照片");
                Intent intentOut = new Intent();
                intentOut.setAction(ACTION_INNER_PIC);
                intentOut.setClass(this, SponsorSelectPicActivity.class);
                intentOut.putExtra("images", imagesIn);
                startActivityForResult(intentOut, REQ_PIC_IN);
                break;
            case R.id.edit_sponsor_location_pan:
                LocalLog.d(TAG, "位置选择");
                Intent intentLocation = new Intent();
                intentLocation.putExtra("lat", latitude);
                intentLocation.putExtra("lng", longitude);
                intentLocation.setClass(this, SponsorTMapActivity.class);
                startActivityForResult(intentLocation, REQ_POSITION);
                break;
            case R.id.btn_confirm:
                commit();
                break;
        }
    }

    private void commit() {
        if (TextUtils.isEmpty(editSponsorName.getText().toString().trim())) {
            PaoToastUtils.showLongToast(this, "店铺名称必需");
            return;
        }
        Map<String, String> param = new HashMap<>();
        param.put("name", editSponsorName.getText().toString().trim());
        if (TextUtils.isEmpty(images)) {
            PaoToastUtils.showLongToast(this, "店铺logo必需");
            return;
        }
        param.put("logo", images);
        if (!TextUtils.isEmpty(imagesIn)) {
            param.put("goods_images", imagesIn);
        }
        if (TextUtils.isEmpty(editSponsorLocationPan.getText().toString().trim())) {
            PaoToastUtils.showLongToast(this, "位置位置必选");
            return;
        }
        param.put("addra", editSponsorLocationPan.getText().toString().trim());
        if (TextUtils.isEmpty(longitude) || TextUtils.isEmpty(latitude)) {
            PaoToastUtils.showLongToast(this, "位置位置必选");
            return;
        }
        param.put("latitude", latitude);
        param.put("longitude", longitude);

        if (editSponsorInviteCode.isEnabled() && !TextUtils.isEmpty(editSponsorInviteCode.getText().toString().trim())) {
            param.put("inviter_no", editSponsorInviteCode.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(editSponsorLocationDetailPan.getText().toString().trim())) {
            param.put("address", editSponsorLocationDetailPan.getText().toString().trim());
        }

        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlShopEntryInfo, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    ShopEntryRePayResponse shopEntryRePayResponse = new Gson().fromJson(s, ShopEntryRePayResponse.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(CIRCLE_ID, shopEntryRePayResponse.getData().getGuide_id());
                    bundle.putString(PAY_FOR_STYLE, "apply_guide");
                    bundle.putString(CIRCLE_RECHARGE, shopEntryRePayResponse.getData().getAmount());
                    Intent intent = new Intent();
                    intent.setClass(ShopEntryActivity.this, PaoBuPayActivity.class);
                    intent.putExtra(ShopEntryActivity.this.getPackageName(), bundle);
                    intent.setAction(PAY_ACTION);
                    startActivityForResult(intent, SHOP_ENTRY);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_PIC && resultCode > 0) {
            images = data.getStringExtra("images");
            int size = data.getIntExtra("size", 0);
            LocalLog.d(TAG, "images =  " + images);
            Presenter.getInstance(this).getPlaceErrorImage(editSponsorOutPics, images, R.drawable.default_head_ico, R.drawable.default_head_ico);
            if (size == 1) {

            } else {

            }
        } else if (requestCode == REQ_PIC_IN && resultCode > 0) {
            imagesIn = data.getStringExtra("images");
            int size = data.getIntExtra("size", 0);
            if (size == 0) {
                editSponsorInnerPics.setText("请上传照片");
            } else {
                editSponsorInnerPics.setText("已上传" + size + "张");
            }
        } else if (requestCode == REQ_POSITION && data != null) {
            city = data.getStringExtra("city");
            latitude = String.valueOf(data.getDoubleExtra("latitude", 0));
            longitude = String.valueOf(data.getDoubleExtra("longitude", 0));
            if (TextUtils.isEmpty(data.getStringExtra("address"))) {
                editSponsorLocationPan.setText(city);
            } else {
                city = data.getStringExtra("address");
                editSponsorLocationPan.setText(city);
            }
        } else if (requestCode == SHOP_ENTRY && resultCode == Activity.RESULT_OK) {
            PaoToastUtils.showLongToast(this, "恭喜您入驻成功");
            finish();
        }
    }

}
