package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.BankListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.BankCardAdapter;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyBankCardActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {

    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.rv_cards)
    RecyclerView rvCards;
    LinearLayoutManager layoutManager;
    @Bind(R.id.bank_scroll)
    BounceScrollView bankScroll;
    @Bind(R.id.empty_bank)
    ImageView emptyBank;
    @Bind(R.id.no_crard)
    TextView noCrard;
    @Bind(R.id.empty_bank_pan)
    RelativeLayout emptyBankPan;
    private List<BankListResponse.CardBean> listData = new ArrayList<>();
    private BankCardAdapter bankCardAdapter;
    private final static int BIND_CARD_RESULT = 100;

    @Override
    protected String title() {
        return "银行卡";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bank_card);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        layoutManager = new LinearLayoutManager(this);
        rvCards = (RecyclerView) findViewById(R.id.rv_cards);
        rvCards.setLayoutManager(layoutManager);
        bankScroll = (BounceScrollView) findViewById(R.id.bank_scroll);
        emptyBankPan = (RelativeLayout) findViewById(R.id.empty_bank_pan);
        bankCardAdapter = new BankCardAdapter(this, listData);
        rvCards.setAdapter(bankCardAdapter);
        setToolBarListener(this);
        getBankList();
    }

    private void getBankList() {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.GET_BANK_LIST, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                bankScroll.setVisibility(View.VISIBLE);
                emptyBankPan.setVisibility(View.GONE);
                BankListResponse bankGson = new Gson().fromJson(s, BankListResponse.class);
                listData.clear();
                listData.addAll(bankGson.getData());
//                for (int i = 0; i < 4; i++) {
//                    BankListResponse.CardBean bean = new BankListResponse.CardBean();
//                    listData.add(bean);
//                }
                bankCardAdapter.notifyDataSetChanged();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    if (errorBean.getError() == 1) {
                        emptyBankPan.setVisibility(View.VISIBLE);
                        bankScroll.setVisibility(View.GONE);
                    } else {
                        PaoToastUtils.showShortToast(MyBankCardActivity.this, errorBean.getMessage());
                    }
                }
            }
        });
    }

    @Override
    protected void setRightValue(int id, Object obj) {
        super.setRightValue(id, obj);
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {
        Intent intent = new Intent();
        intent.setClass(this, BindCardActivity.class);
        if (listData.size() > 0) {
            intent.putExtra("name", listData.get(0).getAccount_name());
        }
        startActivityForResult(intent, BIND_CARD_RESULT);
    }

    @Override
    public Object right() {
        return R.mipmap.ic_add_white;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BIND_CARD_RESULT) {
            getBankList();
        }
    }
}
