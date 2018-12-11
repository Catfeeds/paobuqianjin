package com.paobuqianjin.pbq.step.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.customview.WalletPassDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.BankListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DrawMoneyListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Base64Util;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.GridMoneyAdapter;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.model.RongGridView;

public class TransferCardActivity extends BaseBarActivity {
    private final static String TAG = TransferCardActivity.class.getSimpleName();
    private static final int REQ_ADD_CARD = 1;
    @Bind(R.id.rv_bank)
    RecyclerView rvBank;
    @Bind(R.id.btn_transfer)
    Button btnTransfer;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;
    @Bind(R.id.transfer_limit)
    TextView transferLimit;
    @Bind(R.id.grid_view)
    RongGridView gridView;
    @Bind(R.id.wallet_money)
    TextView walletMoney;
    private List<BankListResponse.CardBean> listData = new ArrayList<>();
    private BankSelectAdapter adapter;
    private float canCrashNum;
    private BankListResponse.CardBean selectBean;
    private WalletPassDialog walletPassDialog;
    private NormalDialog passWordSetDialog;
    List<DrawMoneyListResponse.DataBean> crashData = new ArrayList<>();
    private String crashMoney;//选择提现的金额
    GridMoneyAdapter gridMoneyAdapter;
    List<String> rules = new ArrayList<>();

    @Override
    protected String title() {
        return "转出到银行卡";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_card);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            canCrashNum = Float.parseFloat(intent.getStringExtra("total"));
/*            String canCrashStrFormat = getString(R.string.can_crash);
            String canCrashStr = String.format(canCrashStrFormat, canCrashNum);
            walletMoney.setText(canCrashStr);*/
/*            String canCrashStrFormat = getActivity().getString(R.string.can_crash);
            String canCrashStr = String.format(canCrashStrFormat, canCrashNum);*/
            walletMoney.setText("钱包余额: " + String.valueOf(canCrashNum));
        }

        rvBank.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BankSelectAdapter(this, listData);
        adapter.setItemClickListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

                for (BankListResponse.CardBean bean :
                        listData) {
                    bean.setStatus(0);
                }
                listData.get(position).setStatus(1);
                selectBean = listData.get(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });
        rvBank.setAdapter(adapter);
        //设置Item增加、移除动画
        rvBank.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        rvBank.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        cbAgree.setChecked(Presenter.getInstance(this).getReadCrashProtocol(this));

        initGrid();
        initData();
    }

    private void initGrid() {
        Map<String, String> param = new HashMap<>();
        param.put("userid", String.valueOf(Presenter.getInstance(this).getId()));
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlWithDrawList, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    DrawMoneyListResponse drawMoneyListResponse = new Gson().fromJson(s, DrawMoneyListResponse.class);
                    if (drawMoneyListResponse.getData() != null && drawMoneyListResponse.getData().size() > 0) {
                        for (int i = 0; i < drawMoneyListResponse.getData().size(); i++) {
                            if (i == 0) {
                                rules.addAll(drawMoneyListResponse.getData().get(0).getWithdraw_tips());
                            }
                            if (drawMoneyListResponse.getData().get(i).getIs_disable() == 0) {
                                crashMoney = drawMoneyListResponse.getData().get(i).getMoney();
                                drawMoneyListResponse.getData().get(i).setIs_select(true);
                                break;
                            }
                        }
                        crashData.addAll(drawMoneyListResponse.getData());
                        gridMoneyAdapter = new GridMoneyAdapter(TransferCardActivity.this, crashData);
                        gridView.setAdapter(gridMoneyAdapter);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if (position >= crashData.size()) {
                                    return;
                                }
                                if (crashData.get(position).getIs_disable() == 1) {
                                    LocalLog.d(TAG, "不可选中");
                                    return;
                                }
                                if (crashData.get(position).isIs_select()) {
                                    crashData.get(position).setIs_select(false);
                                    crashMoney = null;
                                    gridMoneyAdapter.notifyDataSetChanged();
                                } else {
                                    crashData.get(position).setIs_select(true);
                                    crashMoney = crashData.get(position).getMoney();
                                    for (int j = 0; j < crashData.size(); j++) {
                                        if (j != position && crashData.get(j).isIs_select()) {
                                            crashData.get(j).setIs_select(false);
                                        }
                                    }
                                    gridMoneyAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                    if (rules.size() > 0) {
                        String ruleStr = "";
                        for (int line = 0; line < rules.size(); line++) {
                            ruleStr += String.valueOf(line + 1) + ". " + rules.get(line) + "\n";
                        }
                        transferLimit.setText(ruleStr);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(TransferCardActivity.this, errorBean.getMessage());
                }
            }
        });
    }

    private void initData() {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.GET_BANK_LIST, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                BankListResponse bankGson = new Gson().fromJson(s, BankListResponse.class);
                listData.clear();
                listData.addAll(bankGson.getData());
                adapter.notifyDataSetChanged();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    if (errorBean.getError() != 1) {
                        PaoToastUtils.showShortToast(TransferCardActivity.this, errorBean.getMessage());
                    }
                }
            }
        });
    }

    @OnClick({R.id.btn_transfer, R.id.tv_protocol, R.id.linear_item_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_transfer:
                String transferMoneyStr = crashMoney;
                if (TextUtils.isEmpty(transferMoneyStr)) {
                    PaoToastUtils.showShortToast(this, "请选择需要提现的金额");
                    return;
                }
                if (selectBean == null) {
                    PaoToastUtils.showShortToast(this, "请选择银行卡");
                    return;
                }
                if (!cbAgree.isChecked()) {
                    PaoToastUtils.showShortToast(this, "请认真阅读" + getString(R.string.transfer_protcol) + "并确认勾选");
                    return;
                }
                if (Float.parseFloat(transferMoneyStr) > canCrashNum) {
                    LocalLog.d(TAG, "提现金额大于可提现金额!");
                    PaoToastUtils.showShortToast(this, "提现金额大于可提现金额");
                    return;
                }
                //TODO 判断是否设置过密码
                Presenter.getInstance(this).getPaoBuSimple(NetApi.urlPassCheck, null, new PaoCallBack() {
                    @Override
                    protected void onSuc(String s) {
                        try {
                            JSONObject jsonObj = new JSONObject(s);
                            jsonObj = jsonObj.getJSONObject("data");
                            String status = jsonObj.getString("setpw");
                            if (status.equals("1")) {
                                popPayPass();
                            } else if (status.equals("0")) {
                                if (passWordSetDialog == null) {
                                    passWordSetDialog = new NormalDialog(TransferCardActivity.this);
                                }
                                passWordSetDialog.setMessage("您还未设置支付密码，去上设置支付密码?");
                                passWordSetDialog.setYesOnclickListener("去设置", new NormalDialog.onYesOnclickListener() {
                                    @Override
                                    public void onYesClick() {
                                        startActivity(IdentifedSetPassActivity.class, null);
                                        if (passWordSetDialog != null)
                                            passWordSetDialog.dismiss();
                                    }
                                });
                                passWordSetDialog.setNoOnclickListener("不设置", new NormalDialog.onNoOnclickListener() {
                                    @Override
                                    public void onNoClick() {
                                        if (passWordSetDialog != null)
                                            passWordSetDialog.dismiss();
                                    }
                                });
                                if (!passWordSetDialog.isShowing())
                                    passWordSetDialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                    }
                });
                break;
            case R.id.tv_protocol:
                startActivity(AgreementActivity.class, null, false, UserServiceProtcolFragment.USER_CRASH_ACTION);
                break;
            case R.id.linear_item_more:
                Intent intent = new Intent(this, BindCardActivity.class);
                if (listData.size() >= 1) {
                    intent.putExtra("name", listData.get(0).getAccount_name());
                }
                startActivityForResult(intent, REQ_ADD_CARD);
                break;
        }
    }

    private void crash() {
        String transferMoneyStr = crashMoney;
        Map<String, String> params = new HashMap<>();
        params.put("cardid", selectBean.getCardid());
        params.put("amount", transferMoneyStr);
        params.put("typeid", "3");
        params.put("limit_version_name", Constants.LIMITE_VERSION);
//                params.put("wx_openid", transferMoneyStr);//app对应的openid
        Presenter.getInstance(this).postPaoBuSimple(NetApi.TRANSFER_BY_HELIBAO, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                Intent intent = new Intent(TransferCardActivity.this, TransferResultActivity.class);
                intent.putExtra("is_success", true);
                startActivity(intent);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null)
                    PaoToastUtils.showShortToast(TransferCardActivity.this, errorBean.getMessage());
                Intent intent = new Intent(TransferCardActivity.this, TransferResultActivity.class);
                intent.putExtra("is_success", false);
                startActivity(intent);
            }
        });
    }

    private void popPayPass() {
        if (walletPassDialog == null) {
            walletPassDialog = new WalletPassDialog(this);
            walletPassDialog.setPassEditListener(new WalletPassDialog.PassEditListener() {
                @Override
                public void onPassWord(String pass) {
                    LocalLog.d(TAG, "pass =" + pass);
                    walletPassDialog.dismiss();
                    String base64Pass = Base64Util.makeUidToBase64(pass);
                    Map<String, String> params = new HashMap<>();
                    params.put("paypw", base64Pass);
                    Presenter.getInstance(TransferCardActivity.this).postPaoBuSimple(NetApi.urlPayPass, params, new PaoCallBack() {
                        @Override
                        protected void onSuc(String s) {
                            try {
                                ErrorCode errorCode = new Gson().fromJson(s, ErrorCode.class);
                                if ("密码正确".equals(errorCode.getMessage())) {
                                    crash();
                                } else {

                                }
                            } catch (JsonSyntaxException j) {
                                LocalLog.d(TAG, "error data format!");
                            }
                        }

                        @Override
                        protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                            if (errorBean.getError() != 100) {
                                PaoToastUtils.showShortToast(getApplicationContext(), errorBean.getMessage());
                            }
                        }
                    });
                }
            });

            walletPassDialog.setForgetPassOnclickListener(new WalletPassDialog.ForgetPassOnclickListener() {
                @Override
                public void onForgetPassClick() {
                    LocalLog.d(TAG, "忘记支付密码");
                    startActivity(ForgetPayWordActivity.class, null);
                }
            });
        }
        if (!walletPassDialog.isShowing() && !isFinishing()) {
            walletPassDialog.clearPassword();
            walletPassDialog.show();
        }
    }

    private class BankSelectAdapter extends RecyclerView.Adapter<BankItemHolder> {

        private Context context;
        private List<BankListResponse.CardBean> listData;
        private RecyclerItemClickListener.OnItemClickListener itemClickListener;
        private LayoutInflater inflater;

        public BankSelectAdapter(Context context, List<BankListResponse.CardBean> listData) {
            this.context = context;
            this.listData = listData;
            inflater = LayoutInflater.from(context);
        }

        public void setItemClickListener(RecyclerItemClickListener.OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public BankItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BankItemHolder(inflater.inflate(R.layout.item_select_card, parent, false));
        }

        @Override
        public void onBindViewHolder(final BankItemHolder holder, final int position) {
            BankListResponse.CardBean itemBean = listData.get(position);

            holder.cb_select.setChecked(itemBean.getStatus() == 1);
            holder.tv_card_num.setText("**** " + itemBean.getBank_card().substring(itemBean.getBank_card().length() - 4));
            holder.tv_title.setText(itemBean.getBank_name());
            Presenter.getInstance(context).getImage(holder.iv_icon, itemBean.getImg_url());

            if (itemClickListener != null) {
                holder.itemRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.OnItemClick(holder.itemRootView, position);
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            return listData.size();
        }
    }


    private class BankItemHolder extends RecyclerView.ViewHolder {

        CheckBox cb_select;
        ImageView iv_icon;
        View itemRootView;
        TextView tv_card_num;
        TextView tv_title;

        public BankItemHolder(View itemView) {
            super(itemView);
            itemRootView = itemView;
            cb_select = (CheckBox) itemView.findViewById(R.id.cb_select);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_card_num = (TextView) itemView.findViewById(R.id.tv_card_num);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ADD_CARD) {
            initData();
        }
    }
}
