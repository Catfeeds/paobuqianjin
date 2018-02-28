package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReleaseRecordResponse;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/2/28.
 */

public class ReleaseRecordAdapter extends RecyclerView.Adapter<ReleaseRecordAdapter.ReleaseRecordViewHolder> {
    private final static String TAG = ReleaseRecordAdapter.class.getSimpleName();
    Context context;
    List<?> mData;

    public ReleaseRecordAdapter(Context context, List<ReleaseRecordResponse.DataBeanX.DataBean> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(ReleaseRecordViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(ReleaseRecordViewHolder holder, int position) {
        if (mData.get(position) instanceof ReleaseRecordResponse.DataBeanX.DataBean) {
            holder.releaseName.setText(((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getTask_name());
            holder.releasePerson.setText("领取人: " + ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            int releaseDays = ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getTask_days();
            String releaseDaysStrFormat = context.getString(R.string.task_days);
            String releaseDayStr = String.format(releaseDaysStrFormat, releaseDays);
            holder.releaseDays.setText(releaseDayStr);
            int attachDays = ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getFinished().getDays();
            String attachDayStrFormat = context.getString(R.string.task_finish_days);
            String attachDayStr = String.format(attachDayStrFormat, attachDays);
            holder.attachDays.setText(attachDayStr);
            String moneyStrFormat = "￥%.2f元";
            float replyMoney = ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getUnfinished().getAmount();
            String replyMoneyStr = String.format(moneyStrFormat, replyMoney);
            String replyDes = "退款余额:";
            SpannableStringBuilder replyMoneyStyle = new SpannableStringBuilder(replyDes + replyMoneyStr);
            replyMoneyStyle.setSpan(new ForegroundColorSpan(Color.parseColor("#ff666666")), 0, replyDes.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            replyMoneyStyle.setSpan(new ForegroundColorSpan(Color.parseColor("#ffe4393c")), replyDes.length(), (replyDes + replyMoneyStr).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.replyMoney.setText(replyMoneyStyle);

            float recvMoney = ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getFinished().getAmount();
            String recvDes = "达标金额:";
            String recvStr = String.format(moneyStrFormat, recvMoney);
            SpannableStringBuilder recvMoneyStyle = new SpannableStringBuilder(recvDes + recvStr);
            recvMoneyStyle.setSpan(new ForegroundColorSpan(Color.parseColor("#ff666666")), 0, recvDes.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            recvMoneyStyle.setSpan(new ForegroundColorSpan(Color.parseColor("#ffe4393c")), recvDes.length(), (recvDes + recvStr).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.recvMoney.setText(recvMoneyStyle);
            long time = ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getCreate_time();
            String date = DateTimeUtil.formatDateTime(time * 1000, DateTimeUtil.DF_YYYY_MM_DD);
            String dateStr = date.replace("-", "/");
            holder.time.setText(dateStr);
        }
    }

    @Override
    public ReleaseRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReleaseRecordViewHolder(LayoutInflater.from(context).inflate(R.layout.release_record_list, parent, false));
    }

    public class ReleaseRecordViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.release_name)
        TextView releaseName;
        @Bind(R.id.release_person)
        TextView releasePerson;
        @Bind(R.id.release_days)
        TextView releaseDays;
        @Bind(R.id.attach_days)
        TextView attachDays;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.reply_money)
        TextView replyMoney;
        @Bind(R.id.recv_money)
        TextView recvMoney;

        public ReleaseRecordViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            releaseName = (TextView) view.findViewById(R.id.release_name);
            releasePerson = (TextView) view.findViewById(R.id.release_person);
            releaseDays = (TextView) view.findViewById(R.id.release_days);
            attachDays = (TextView) view.findViewById(R.id.attach_days);
            time = (TextView) view.findViewById(R.id.time);
            replyMoney = (TextView) view.findViewById(R.id.reply_money);
            recvMoney = (TextView) view.findViewById(R.id.recv_money);
        }
    }
}
