package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.paobuqianjin.pbq.step.view.base.adapter.owner.BankPswAdapter;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/6/7.
 */

public class ForgetPayWordActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {
    private final static String ACTION_FORGET_PSW = "com.paobuqianjin.pbq.setp.ACTION_FORGET_PSW";
    private final static int BIND_CARD_RESULT = 100;
    private final static int RESET_PWD = 11;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.des_bank)
    TextView desBank;
    @Bind(R.id.bank_recycler)
    RecyclerView bankRecycler;
    @Bind(R.id.empty_bank)
    ImageView emptyBank;
    @Bind(R.id.no_crard)
    TextView noCrard;
    @Bind(R.id.empty_bank_pan)
    RelativeLayout emptyBankPan;
    @Bind(R.id.bank_bind_list)
    RelativeLayout bankBindList;
    @Bind(R.id.phone_set_pass)
    RelativeLayout phoneSetPass;
    private List<BankListResponse.CardBean> listData = new ArrayList<>();
    private BankPswAdapter bankPswAdapter;
    LinearLayoutManager layoutManager;

    @Override
    protected String title() {
        return "忘记支付密码";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pay_pwd_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    public Object right() {
        return R.mipmap.ic_add_white;
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BIND_CARD_RESULT) {
            getBankList();
        } else if (requestCode == RESET_PWD) {
            if (Activity.RESULT_OK == resultCode) {
                finish();
            }
        }
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
    protected void initView() {
        bankBindList = (RelativeLayout) findViewById(R.id.bank_bind_list);
        emptyBankPan = (RelativeLayout) findViewById(R.id.empty_bank_pan);
        bankRecycler = (RecyclerView) findViewById(R.id.bank_recycler);
        layoutManager = new LinearLayoutManager(this);
        bankRecycler.setLayoutManager(layoutManager);
        bankPswAdapter = new BankPswAdapter(this, listData);
        bankRecycler.setAdapter(bankPswAdapter);
        bankRecycler.addOnItemTouchListener(new RecyclerItemClickListener(this, bankRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                String cardStr = listData.get(position).getBank_card();
                String replaceStr = "";
                if (!TextUtils.isEmpty(cardStr)) {
                    int length = cardStr.length();
                    if (length > 4) {
                        replaceStr = cardStr.substring(cardStr.length() - 4, cardStr.length());
                    }
                }
                Intent intent = new Intent();
                intent.setAction(ACTION_FORGET_PSW);
                intent.setClass(getApplicationContext(), IdentityAuth2Activity.class);
                String cardInfo = listData.get(position).getBank_name() + " " + "储蓄卡" + " " + "(" + replaceStr + ")";
                Bundle bundle = new Bundle();
                bundle.putString("cardinfo", cardInfo);
                bundle.putString("cardid", listData.get(position).getCardid());
                intent.putExtra(getPackageName(), bundle);
                startActivity(intent);
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        }));
        getBankList();
        setToolBarListener(this);
    }

    private void getBankList() {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.GET_BANK_LIST, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (isFinishing()) {
                    return;
                }
                bankBindList.setVisibility(View.VISIBLE);
                emptyBankPan.setVisibility(View.GONE);
                BankListResponse bankGson = new Gson().fromJson(s, BankListResponse.class);
                listData.clear();
                listData.addAll(bankGson.getData());
//                for (int i = 0; i < 4; i++) {
//                    BankListResponse.CardBean bean = new BankListResponse.CardBean();
//                    listData.add(bean);
//                }
                bankPswAdapter.notifyDataSetChanged();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    if (errorBean.getError() == 1 && !isFinishing()) {
                        bankBindList.setVisibility(View.GONE);
                    } else {
                        PaoToastUtils.showShortToast(ForgetPayWordActivity.this, errorBean.getMessage());
                    }
                }
            }
        });
    }

    @OnClick(R.id.phone_set_pass)
    public void onClick() {
        startActivityForResult(new Intent().setClass(this, RePwdPhoneActivity.class), RESET_PWD);
    }
}
