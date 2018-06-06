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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.WalletPassDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.BankListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Base64Util;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferCardActivity extends BaseBarActivity {
    private final static String TAG = TransferCardActivity.class.getSimpleName();
    private static final int REQ_ADD_CARD = 1;
    @Bind(R.id.tv_transfer_all)
    TextView tvTransferAll;
    @Bind(R.id.rv_bank)
    RecyclerView rvBank;
    @Bind(R.id.btn_transfer)
    Button btnTransfer;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;
    @Bind(R.id.et_can_transfer)
    EditText etCanTransfer;
    private List<BankListResponse.CardBean> listData = new ArrayList<>();
    private BankSelectAdapter adapter;
    private float canCrashNum;
    private BankListResponse.CardBean selectBean;
    private WalletPassDialog walletPassDialog;

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
            canCrashNum = intent.getFloatExtra("total", 0.0f);
            String canCrashStrFormat = getString(R.string.can_crash);
            String canCrashStr = String.format(canCrashStrFormat, canCrashNum);
            etCanTransfer.setHint(canCrashStr);
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
        initData();
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

    @OnClick({R.id.btn_transfer, R.id.tv_protocol, R.id.linear_item_more, R.id.tv_transfer_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_transfer:
                String transferMoneyStr = etCanTransfer.getText().toString();
                if (TextUtils.isEmpty(transferMoneyStr)) {
                    PaoToastUtils.showShortToast(this, "请输入需要提现的金额");
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
                crash();
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
            case R.id.tv_transfer_all:
                etCanTransfer.setText(canCrashNum + "");
                break;
        }
    }

    private void crash() {
        String transferMoneyStr = etCanTransfer.getText().toString();
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
                    String base64Pass = Base64Util.getUidFromBase64(pass);
                    Map<String, String> params = new HashMap<>();
                    params.put("paypw", base64Pass);
                    Presenter.getInstance(TransferCardActivity.this).postPaoBuSimple(NetApi.urlPayPass, params, new PaoCallBack() {
                        @Override
                        protected void onSuc(String s) {

                        }

                        @Override
                        protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                        }
                    });
                }
            });

            walletPassDialog.setForgetPassOnclickListener(new WalletPassDialog.ForgetPassOnclickListener() {
                @Override
                public void onForgetPassClick() {
                    LocalLog.d(TAG, "忘记支付密码");
                }
            });
        }
        if (!walletPassDialog.isShowing() && !isFinishing()) {
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
