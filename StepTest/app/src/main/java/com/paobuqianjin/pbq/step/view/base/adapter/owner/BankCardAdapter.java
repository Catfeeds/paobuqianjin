package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.BankListResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/5/25.
 */

public class BankCardAdapter extends RecyclerView.Adapter<BankCardAdapter.BankCardViewHolder> {
    private Context mContext;
    private List<?> mData;

    public BankCardAdapter(Context context, List<?> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public void onBindViewHolder(BankCardViewHolder holder, int position) {
        if (mData.get(position) instanceof BankListResponse.CardBean) {
            Presenter.getInstance(mContext).getImage(holder.bankCardIco, ((BankListResponse.CardBean) mData.get(position)).getImg_url());
            holder.cardNoStr = ((BankListResponse.CardBean) mData.get(position)).getBank_card();
            String cardStr = holder.cardNoStr;
            if (!TextUtils.isEmpty(cardStr)) {
                int length = cardStr.length();
                if (length > 4) {
                    String replaceStr = cardStr.substring(0, cardStr.length() - 4);
                    String card4Str = cardStr.substring(cardStr.length() - 4, cardStr.length());
                    char[] replaceOp = new char[replaceStr.length()];
                    for (int j = 0; j < replaceOp.length; j++) {
                        replaceOp[j] = '*';
                    }
                    replaceStr = replaceStr.replaceAll("^[0-9]*$", new String(replaceOp));
                    holder.cardNo.setText(replaceStr + card4Str);
                }
            }
        }
    }

    @Override
    public BankCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BankCardViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.bank_card_list_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class BankCardViewHolder extends RecyclerView.ViewHolder {
        CircleImageView bankCardIco;
        TextView bankCardFrom;
        TextView cardStyleDes;
        RelativeLayout cardMessage;
        TextView cardNo;
        String cardNoStr = "";

        public BankCardViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            bankCardIco = (CircleImageView) view.findViewById(R.id.bank_card_ico);
            bankCardFrom = (TextView) view.findViewById(R.id.bank_card_from);
            cardStyleDes = (TextView) view.findViewById(R.id.card_style_des);
            cardNo = (TextView) view.findViewById(R.id.card_no);
        }
    }
}
