package com.paobuqianjin.pbq.step.view.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.customview.LimitLengthFilter;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/10/10.
 */

public class AddLittleConsumActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {
    private final static String TAG = AddLittleConsumActivity.class.getSimpleName();
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
    @Bind(R.id.banner)
    Banner banner;
    private int businessId = -1;
    private LimitLengthFilter limitLengthFilter;

    @Override
    protected String title() {
        return getString(R.string.add_consumptive_red_bag);
    }

    @Override
    public Object right() {
        return "确定";
    }

    @Override
    public void clickRight() {
        if (fillter()) {
            Map<String, String> params = new HashMap<>();
            params.put("busid", businessId + "");
            params.put("vname", etRedBagName.getText().toString());
            params.put("amount", etNum.getText().toString());
            params.put("condition", etLimiteMoney.getText().toString());
            params.put("money", etMoney.getText().toString());
            params.put("day", etDayNum.getText().toString());
        }
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_little_consum_red_bag);
        ButterKnife.bind(this);

        limitLengthFilter = new LimitLengthFilter();
        etRedBagName.setFilters(new InputFilter[]{limitLengthFilter});
        setToolBarListener(this);
        //loadBanner();
    }

    private void loadBanner() {
        String bannerUrl = NetApi.urlAd + "?position=voucher_create";
        LocalLog.d(TAG, "bannerUrl  = " + bannerUrl);
        Presenter.getInstance(AddLittleConsumActivity.this).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
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
                                        ClipboardManager cmb = (ClipboardManager) AddLittleConsumActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData textClipData = ClipData.newPlainText("Label", getString(R.string.wx_code));
                                        cmb.setPrimaryClip(textClipData);
                                        LocalLog.d(TAG, "  msg = " + cmb.getText());
                                        PaoToastUtils.showLongToast(AddLittleConsumActivity.this, "微信号复制成功");
                                    } else {
                                        String targetUrl = adList.get(position).getTarget_url();
                                        if (!TextUtils.isEmpty(targetUrl))
                                            startActivity(new Intent(AddLittleConsumActivity.this, SingleWebViewActivity.class).putExtra("url", targetUrl));
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

    @Override
    protected void onResume() {
        super.onResume();
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
        if (TextUtils.isEmpty(etDayNum.getText().toString())) {
            PaoToastUtils.showShortToast(this, "请输入有效天数");
            return false;
        }
        if (TextUtils.isEmpty(etNum.getText().toString())) {
            PaoToastUtils.showShortToast(this, "请输入使用消费红包的数量");
            return false;
        }
        if (businessId < 1) {
            PaoToastUtils.showShortToast(this, "请选择您的商铺");
            return false;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
