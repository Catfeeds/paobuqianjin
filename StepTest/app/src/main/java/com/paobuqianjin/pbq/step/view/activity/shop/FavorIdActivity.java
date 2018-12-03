package com.paobuqianjin.pbq.step.view.activity.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FavRitItemResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.rong.imkit.model.RongGridView;

/**
 * Created by pbq on 2018/12/3.
 */

public class FavorIdActivity extends BaseBarActivity {
    private final static int PAGE_SIZE = 10;
    String favorId = "";
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.grid_view)
    RongGridView gridView;

    @Override
    protected String title() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favor_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        favorId = intent.getStringExtra(getPackageName() + "favor_id");
        getFavorIdList(1);
    }

    private void getFavorIdList(int page) {
        if (TextUtils.isEmpty(favorId)) {
            finish();
        }
        Map<String, String> param = new HashMap<>();
        param.put("page", String.valueOf(page));
        param.put("pagesize", String.valueOf(PAGE_SIZE));
        param.put("favor_id", favorId);
        param.put("term_id", "1");

        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlFavGoodList, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    FavRitItemResponse favRitItemResponse = new Gson().fromJson(s, FavRitItemResponse.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(FavorIdActivity.this, errorBean.getMessage());
                } else {
                    PaoToastUtils.showLongToast(FavorIdActivity.this, "开小差了，请稍后再试");
                }
            }
        });

    }
}
