package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepDollarDetailResponse;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/2/26.
 */

public class StepDollarDetailAdapter extends RecyclerView.Adapter<StepDollarDetailAdapter.StepDollarDetailViewHolder> {
    private final static String TAG = StepDollarDetailAdapter.class.getSimpleName();
    private List<?> mData;
    private Context context;

    public StepDollarDetailAdapter(Context context, List<StepDollarDetailResponse.DataBeanX.DataBean> data) {
        super();
        this.context = context;
        mData = data;
    }

    @Override
    public StepDollarDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StepDollarDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.step_dollar_invite_list, parent, false));
    }

    @Override
    public void onBindViewHolder(StepDollarDetailViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(StepDollarDetailViewHolder holder, int position) {
        LocalLog.d(TAG, "updateListItem() enter");
        if (mData.get(position) instanceof StepDollarDetailResponse.DataBeanX.DataBean) {
            holder.inviteText.setText(((StepDollarDetailResponse.DataBeanX.DataBean) mData.get(position)).getSource());
            //转换为毫秒级
            long createTime = ((StepDollarDetailResponse.DataBeanX.DataBean) mData.get(position)).getCreat_time() * 1000;
            String dateStr = DateTimeUtil.formatDateTime(createTime, DateTimeUtil.DF_YYYY_MM_DD);
            LocalLog.d(TAG, "dateStr = " + dateStr);
            holder.inviteTime.setText(dateStr);
            holder.stepDollarIncome.setText(((StepDollarDetailResponse.DataBeanX.DataBean) mData.get(position)).getCredit());
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public class StepDollarDetailViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.invite_text)
        TextView inviteText;
        @Bind(R.id.invite_time)
        TextView inviteTime;
        @Bind(R.id.invite_span_list_rel)
        RelativeLayout inviteSpanListRel;
        @Bind(R.id.step_dollar_income)
        TextView stepDollarIncome;

        public StepDollarDetailViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            inviteText = (TextView) view.findViewById(R.id.invite_text);
            inviteTime = (TextView) view.findViewById(R.id.invite_time);
            stepDollarIncome = (TextView) view.findViewById(R.id.step_dollar_income);
        }
    }
}
