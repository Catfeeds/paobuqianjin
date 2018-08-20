package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RoundRedInfoResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/8/20.
 */

public class RoundRedRelActivity extends BaseBarActivity {
    private final static String TAG = RoundRedRelActivity.class.getSimpleName();
    @Bind(R.id.user_head)
    CircleImageView userHead;
    @Bind(R.id.pkg_money)
    TextView pkgMoney;
    @Bind(R.id.op_des)
    TextView opDes;
    @Bind(R.id.rec_recycler)
    SwipeMenuRecyclerView recRecycler;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    String red_id = "";
    private final static int PAGE_SIZE = 10;
    private int pageIndex = 1, pageCount = 0;
    TextView redDes;

    @Override
    protected String title() {
        return "红包信息";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.red_info_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        userHead = (CircleImageView) findViewById(R.id.user_head);
        pkgMoney = (TextView) findViewById(R.id.pkg_money);
        opDes = (TextView) findViewById(R.id.op_des);
        redDes = (TextView) findViewById(R.id.des_red);
        Intent intent = getIntent();
        if (intent != null) {
            red_id = intent.getStringExtra(getPackageName() + "red_id");
            LocalLog.d(TAG, "red_id =" + red_id);
            if (!TextUtils.isEmpty(red_id)) {
                redInfo(red_id);
            }
        }
    }

    private void redInfo(final String redId) {
        Map<String, String> param = new HashMap<>();
        param.put("red_id", redId);
        param.put("page", String.valueOf(pageIndex));
        param.put("pagesize", String.valueOf(PAGE_SIZE));
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlRedInfo, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    RoundRedInfoResponse redInfoResponse = new Gson().fromJson(s, RoundRedInfoResponse.class);
                    pageCount = redInfoResponse.getData().getReceive_list().getPagenation().getTotalPage();
                    Presenter.getInstance(RoundRedRelActivity.this).getPlaceErrorImage(userHead,
                            redInfoResponse.getData().getAvatar(), R.drawable.default_head_ico,
                            R.drawable.default_head_ico);
                    if (redInfoResponse.getData().getIs_me() == 0) {
                        LocalLog.d(TAG, "领");
                        pkgMoney.setText(redInfoResponse.getData().getIncome_money());
                    } else if (redInfoResponse.getData().getIs_me() == 1) {
                        LocalLog.d(TAG, "发");
                        opDes.setVisibility(View.INVISIBLE);
                        pkgMoney.setText("自己发的红包无法领取");
                    }
                    redDes.setText("已领取" + redInfoResponse.getData().getReceive_num() + "个" + "，共"
                            + redInfoResponse.getData().getReceive_total_money() + "/" + redInfoResponse.getData().getMoney());


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }
}
