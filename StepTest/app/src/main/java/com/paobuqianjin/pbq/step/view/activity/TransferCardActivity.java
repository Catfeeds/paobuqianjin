package com.paobuqianjin.pbq.step.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.BankListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
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

    @Bind(R.id.tv_can_transfer)
    EditText tvCanTransfer;
    @Bind(R.id.tv_transfer_all)
    TextView tvTransferAll;
    @Bind(R.id.rv_bank)
    RecyclerView rvBank;
    @Bind(R.id.btn_transfer)
    Button btnTransfer;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;
    private List<BankListResponse.DataBeanX.DataBean> listData = new ArrayList<>();
    private BankSelectAdapter adapter;

    @Override
    protected String title() {
        return "转出到银行卡";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_card);
        ButterKnife.bind(this);

        rvBank.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BankSelectAdapter(this, listData);
        adapter.setItemClickListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

                for (BankListResponse.DataBeanX.DataBean bean :
                        listData) {
                    bean.setStatus(0);
                }
                listData.get(position).setStatus(1);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });
        rvBank.setAdapter(adapter);
        //设置Item增加、移除动画
//        rvBank.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        rvBank.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        initData();
    }

    private void initData() {
        Map<String, String> params = new HashMap<>();
        Presenter.getInstance(this).postPaoBuSimple(NetApi.GET_BANK_LIST, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                for (int i = 0; i < 4; i++) {
                    BankListResponse.DataBeanX.DataBean bean = new BankListResponse.DataBeanX.DataBean();
                    if (i == 2) {
                        bean.setStatus(1);
                    }
                    listData.add(bean);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showShortToast(TransferCardActivity.this, errorBean.getMessage());
                }
            }
        });
    }

    @OnClick({R.id.btn_transfer, R.id.tv_protocol,R.id.linear_item_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_transfer:

                break;
            case R.id.tv_protocol:
                startActivity(AgreementActivity.class, null, false, UserServiceProtcolFragment.USER_CRASH_ACTION);
                break;
            case R.id.linear_item_more:
                startActivity(BindCardActivity.class, null);
                break;
        }
    }

    private class BankSelectAdapter extends RecyclerView.Adapter<BankItemHolder> {

        private Context context;
        private List<BankListResponse.DataBeanX.DataBean> listData;
        private RecyclerItemClickListener.OnItemClickListener itemClickListener;
        private LayoutInflater inflater;
        private Map<String, Integer> bankMap = null;

        {
             bankMap = new HashMap<>();
             bankMap.put("1", R.mipmap.ic_cheat_add);
             bankMap.put("2", R.mipmap.ic_cheat_add);
             bankMap.put("3", R.mipmap.ic_cheat_add);
             bankMap.put("4", R.mipmap.ic_cheat_add);
        }

        public BankSelectAdapter(Context context, List<BankListResponse.DataBeanX.DataBean> listData) {
            this.context = context;
            this.listData = listData;
            inflater = LayoutInflater.from(context);
        }

        public void setItemClickListener(RecyclerItemClickListener.OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public BankItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BankItemHolder(inflater.inflate(R.layout.item_select_card, parent,false));
        }

        @Override
        public void onBindViewHolder(final BankItemHolder holder, final int position) {
            BankListResponse.DataBeanX.DataBean itemBean = listData.get(position);

            holder.cb_select.setChecked(itemBean.getStatus() == 1);
//            holder.iv_icon.setImageResource(getBankIcon(itemBean.getTypeid()));

            if (itemClickListener != null) {
                holder.itemRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.OnItemClick(holder.itemRootView, position);
                    }
                });
            }

        }

        private int getBankIcon(int typeid) {
            int result = bankMap.get("2");
            if (result == 0) {
                result = R.mipmap.ic_notification_default;
            }
            return result;
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
        public BankItemHolder(View itemView) {
            super(itemView);
            itemRootView = itemView;
            cb_select = (CheckBox) itemView.findViewById(R.id.cb_select);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);

        }
    }
}
