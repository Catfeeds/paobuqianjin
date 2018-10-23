package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.a.v;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NearBySponsorResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReleaseRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorPkgRecordResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.MyReleaseDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorRedDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.TaskReleaseActivity;

import java.util.List;
import java.util.logging.Handler;

/**
 * Created by pbq on 2018/2/28.
 */

public class ReleaseRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String PERSON_TASK_ACTION = "com.paobuqianjin.pbq.step.PERSON_ACTION";
    private final static String SPONSOR_TASK_ACTION = "com.paobuqianjin.pbq.step.SPONSOR_ACTION";
    private final static String SPOSNOR_ACTION = "com.paobuqianjin.person.SPONSOR_ACTION";
    private final static String TAG = ReleaseRecordAdapter.class.getSimpleName();
    private final static int DEFAULT_VIEW_TYPE = 0;
    private final static int RED_PKG_VIEW_TYPE = 1;
    private final static int RED_AD_VIEW_TYPE = 2;
    Activity context;
    List<?> mData;
    private int step = -1;

    public ReleaseRecordAdapter(Activity context, List<?> data) {
        this.context = context;
        mData = data;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public int getItemCount() {
        if (mData != null && mData.size() > 0) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public void notifyDataSetChanged(List<?> data) {
        this.mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mData != null) {
            if (mData.get(position) instanceof NearBySponsorResponse.DataBean.NearedpacketBean
                    || mData.get(position) instanceof NearBySponsorResponse.DataBean.Ledredpacket) {
                if (mData.get(position) instanceof NearBySponsorResponse.DataBean.NearedpacketBean
                        && ((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(position)).getRed_id() == 0) {
                    return RED_AD_VIEW_TYPE;
                }
                if (mData.get(position) instanceof NearBySponsorResponse.DataBean.Ledredpacket
                        && ((NearBySponsorResponse.DataBean.Ledredpacket) mData.get(position)).getRed_id() == 0) {
                    return RED_AD_VIEW_TYPE;
                }
                return RED_PKG_VIEW_TYPE;
            } else {
                return DEFAULT_VIEW_TYPE;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(RecyclerView.ViewHolder holder, int position) {
        if (mData.get(position) instanceof ReleaseRecordResponse.DataBeanX.DataBean) {
            ((ReleaseRecordViewHolder) holder).moneyTv.setText("￥" + ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getReward_amount() + "元");
            ((ReleaseRecordViewHolder) holder).releaseName.setText(((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getTask_name());
            ((ReleaseRecordViewHolder) holder).releaseFriend.setText("领取人: " + ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            String daysFormat = context.getString(R.string.task_days);
            String daysStr = String.format(daysFormat, ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getTask_days());
            ((ReleaseRecordViewHolder) holder).releaseDays.setText(daysStr);
            ((ReleaseRecordViewHolder) holder).taskId = ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getId();
            ((ReleaseRecordViewHolder) holder).sponsorName.setVisibility(View.GONE);
            String localStr = "";
            if (((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getStatus() == 1) {
                localStr = "进行中";
            } else if (((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(position)).getStatus() == 2) {
                localStr = "已结束";
            } else {

            }
            ((ReleaseRecordViewHolder) holder).statusStr = localStr;
            ((ReleaseRecordViewHolder) holder).releaseDetails.setText(localStr);
        } else if (mData.get(position) instanceof SponsorPkgRecordResponse.DataBeanX.DataBean) {
            ((ReleaseRecordViewHolder) holder).sponsorName.setVisibility(View.VISIBLE);
            ((ReleaseRecordViewHolder) holder).releaseName.setText(((SponsorPkgRecordResponse.DataBeanX.DataBean) mData.get(position)).getRed_name());
            ((ReleaseRecordViewHolder) holder).releaseFriend.setText("目标步数:" + ((SponsorPkgRecordResponse.DataBeanX.DataBean) mData.get(position)).getStep());
            String daysFormat = context.getString(R.string.task_days);
            String daysStr = String.format(daysFormat, ((SponsorPkgRecordResponse.DataBeanX.DataBean) mData.get(position)).getDay());
            ((ReleaseRecordViewHolder) holder).releaseDays.setText(daysStr);
            ((ReleaseRecordViewHolder) holder).sponsorName.setText("会员名称:" + ((SponsorPkgRecordResponse.DataBeanX.DataBean) mData.get(position)).getName());
            String red_status = "";
            if (((SponsorPkgRecordResponse.DataBeanX.DataBean) mData.get(position)).getRed_status() == 0) {

            } else if (((SponsorPkgRecordResponse.DataBeanX.DataBean) mData.get(position)).getRed_status() == 1) {
                red_status = "进行中";
            } else if (((SponsorPkgRecordResponse.DataBeanX.DataBean) mData.get(position)).getRed_status() == 2) {
                red_status = "已完成";
            }
            ((ReleaseRecordViewHolder) holder).statusStr = red_status;
            ((ReleaseRecordViewHolder) holder).releaseDetails.setText(red_status);
            ((ReleaseRecordViewHolder) holder).moneyTv.setText("￥" + ((SponsorPkgRecordResponse.DataBeanX.DataBean) mData.get(position)).getMoney() + "元");
        } else if (mData.get(position) instanceof NearBySponsorResponse.DataBean.NearedpacketBean) {
            if (((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(position)).getRed_id() == 0) {

            } else {
                if (!TextUtils.isEmpty(((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(position)).getLogo())) {
                    Presenter.getInstance(context).getPlaceErrorImage(((SponsorRedViewHolder) holder).logoSponsor,
                            ((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(position)).getLogo(), R.mipmap.red_logo, R.mipmap.red_logo);
                }
                ((SponsorRedViewHolder) holder).sponsorName.setVisibility(View.VISIBLE);
                ((SponsorRedViewHolder) holder).process.setVisibility(View.GONE);
                ((SponsorRedViewHolder) holder).releaseName.setText(((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(position)).getRed_name());
                ((SponsorRedViewHolder) holder).releaseFriend.setText("目标步数:" + ((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(position)).getStep());
                String daysFormat = context.getString(R.string.task_days);
                String daysStr = String.format(daysFormat, ((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(position)).getDay());
                ((SponsorRedViewHolder) holder).releaseDays.setText(daysStr);
                ((SponsorRedViewHolder) holder).sponsorName.setText("会员名称:" + ((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(position)).getName());
                int status = ((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(position)).getStatus();
                String localStatus = "";
                if (status == 0) {
                    ((SponsorRedViewHolder) holder).canRec.setVisibility(View.VISIBLE);
                    ((SponsorRedViewHolder) holder).canNoRec.setVisibility(View.GONE);
                    ((SponsorRedViewHolder) holder).process.setVisibility(View.GONE);
                    localStatus = "领取红包";
                    ((SponsorRedViewHolder) holder).canRec.setText(localStatus);
                    ((SponsorRedViewHolder) holder).canRec.setVisibility(View.VISIBLE);
                    ((SponsorRedViewHolder) holder).canNoRec.setVisibility(View.GONE);
                    ((SponsorRedViewHolder) holder).process.setVisibility(View.GONE);
                } else if (status == 1) {
                    if (((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(position)).getStastr() != null &&
                            ((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(position)).getStastr().contains("步数")) {
                        localStatus = "进行中";
                        ((SponsorRedViewHolder) holder).canRec.setVisibility(View.VISIBLE);
                        ((SponsorRedViewHolder) holder).canNoRec.setVisibility(View.GONE);
                        ((SponsorRedViewHolder) holder).canRec.setText(localStatus);
                        if (step != -1) {
                            String stepProcess = String.valueOf(step) + "/" + ((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(position)).getStep();
                            ((SponsorRedViewHolder) holder).process.setText(stepProcess);
                            ((SponsorRedViewHolder) holder).process.setVisibility(View.VISIBLE);
                        }
                    } else {
                        localStatus = "已领完";
                        ((SponsorRedViewHolder) holder).canRec.setVisibility(View.GONE);
                        ((SponsorRedViewHolder) holder).canNoRec.setVisibility(View.VISIBLE);
                        ((SponsorRedViewHolder) holder).process.setVisibility(View.GONE);
                        ((SponsorRedViewHolder) holder).canNoRec.setImageResource(R.drawable.rec_finish);
                    }
                } else if (status == 2) {
                    localStatus = "已领完";
                    ((SponsorRedViewHolder) holder).canRec.setVisibility(View.GONE);
                    ((SponsorRedViewHolder) holder).canNoRec.setVisibility(View.VISIBLE);
                    ((SponsorRedViewHolder) holder).process.setVisibility(View.GONE);
                    ((SponsorRedViewHolder) holder).canNoRec.setImageResource(R.drawable.rec_finish);
                } else if(status == 3){
                    localStatus ="已领取";
                    ((SponsorRedViewHolder) holder).canRec.setVisibility(View.GONE);
                    ((SponsorRedViewHolder) holder).canNoRec.setVisibility(View.VISIBLE);
                    ((SponsorRedViewHolder) holder).process.setVisibility(View.GONE);
                    ((SponsorRedViewHolder) holder).canNoRec.setImageResource(R.drawable.have_rec);
                }else if(status == 5){
                    //红包开放时间未到
                }
                ((SponsorRedViewHolder) holder).statusStr = localStatus;
            }
        } else if (mData.get(position) instanceof NearBySponsorResponse.DataBean.Ledredpacket) {
            if (((NearBySponsorResponse.DataBean.Ledredpacket) mData.get(position)).getRed_id() == 0) {

            } else {
                if (!TextUtils.isEmpty(((NearBySponsorResponse.DataBean.Ledredpacket) mData.get(position)).getLogo())) {
                    Presenter.getInstance(context).getPlaceErrorImage(((SponsorRedViewHolder) holder).logoSponsor,
                            ((NearBySponsorResponse.DataBean.Ledredpacket) mData.get(position)).getLogo(), R.mipmap.red_logo, R.mipmap.red_logo);
                }
                ((SponsorRedViewHolder) holder).canRec.setVisibility(View.GONE);
                ((SponsorRedViewHolder) holder).canNoRec.setVisibility(View.VISIBLE);
                ((SponsorRedViewHolder) holder).process.setVisibility(View.GONE);
                ((SponsorRedViewHolder) holder).sponsorName.setVisibility(View.VISIBLE);
                ((SponsorRedViewHolder) holder).releaseName.setText(((NearBySponsorResponse.DataBean.Ledredpacket) mData.get(position)).getRed_name());
                ((SponsorRedViewHolder) holder).releaseFriend.setText("目标步数:" + ((NearBySponsorResponse.DataBean.Ledredpacket) mData.get(position)).getStep());
                String daysFormat = context.getString(R.string.task_days);
                String daysStr = String.format(daysFormat, ((NearBySponsorResponse.DataBean.Ledredpacket) mData.get(position)).getDay());
                ((SponsorRedViewHolder) holder).releaseDays.setText(daysStr);
                ((SponsorRedViewHolder) holder).sponsorName.setText("会员名称:" + ((NearBySponsorResponse.DataBean.Ledredpacket) mData.get(position)).getName());
                ((SponsorRedViewHolder) holder).canNoRec.setImageResource(R.drawable.have_rec);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == DEFAULT_VIEW_TYPE) {
            holder = new ReleaseRecordViewHolder(LayoutInflater.from(context).inflate(R.layout.my_release_list, parent, false));
        } else if (viewType == RED_PKG_VIEW_TYPE) {
            holder = new SponsorRedViewHolder(LayoutInflater.from(context).inflate(R.layout.red_item_ad, parent, false));
        } else if (viewType == RED_AD_VIEW_TYPE) {
            holder = new SponsorAdViewHolder(LayoutInflater.from(context).inflate(R.layout.red_item_ad_index, parent, false));
        }
        return holder;
    }


    public class SponsorAdViewHolder extends RecyclerView.ViewHolder {
        Button can;
        TextView content;
        LinearLayout linearLayout;

        public SponsorAdViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            can = (Button) view.findViewById(R.id.can_rec);
            content = (TextView) view.findViewById(R.id.content);
            linearLayout = (LinearLayout) view.findViewById(R.id.item);
            linearLayout.setOnClickListener(onClickListener);

            SpannableString spannableString = new SpannableString("附近红包的功能是商家向1-50km内的客户推送红包，增加你的客流量，扩展你的产品知名度。点击发红包 >");
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.color_ffbe24)),
                    "附近红包的功能是商家向1-50km内的客户推送红包，增加你的客流量，扩展你的产品知名度。".length(),
                    "附近红包的功能是商家向1-50km内的客户推送红包，增加你的客流量，扩展你的产品知名度。点击发红包 >".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            content.setText(spannableString);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.item:
                        LocalLog.d(TAG, "发红包");
                        Intent intent = new Intent();
                        intent.setAction(SPOSNOR_ACTION);
                        intent.setClass(context, TaskReleaseActivity.class);
                        context.startActivity(intent);
                        break;
                }
            }
        };
    }

    public class SponsorRedViewHolder extends RecyclerView.ViewHolder {
        TextView releaseName;
        TextView releaseFriend;
        TextView releaseDays;
        Button canRec;
        TextView sponsorName;
        TextView process;
        ImageView canNoRec;
        String statusStr = "";
        ImageView logoSponsor;

        public SponsorRedViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            logoSponsor = (ImageView) view.findViewById(R.id.logo_sponsor);
            canNoRec = (ImageView) view.findViewById(R.id.can_not_rec);
            releaseName = (TextView) view.findViewById(R.id.release_name);
            releaseFriend = (TextView) view.findViewById(R.id.release_friend);
            releaseDays = (TextView) view.findViewById(R.id.release_days);
            canRec = (Button) view.findViewById(R.id.can_rec);
            sponsorName = (TextView) view.findViewById(R.id.release_sponse_name);
            canRec.setOnClickListener(onClickListener);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mData.get(getAdapterPosition()) instanceof NearBySponsorResponse.DataBean.NearedpacketBean) {
                        int businessid = -1;
                        businessid = ((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(getAdapterPosition())).getBusinessid();
                        if (businessid != -1) {
                            LocalLog.d(TAG, "bunessid = " + businessid);
                            Intent intent = new Intent();
                            intent.putExtra(context.getPackageName() + "businessid", businessid);
                            intent.setClass(context, SponsorDetailActivity.class);
                            context.startActivity(intent);
                        }
                    } else if (mData.get(getAdapterPosition()) instanceof NearBySponsorResponse.DataBean.Ledredpacket) {
                        int businessid = -1;
                        businessid = ((NearBySponsorResponse.DataBean.Ledredpacket) mData.get(getAdapterPosition())).getBusinessid();
                        if (businessid != -1) {
                            LocalLog.d(TAG, "bunessid = " + businessid);
                            Intent intent = new Intent();
                            intent.putExtra(context.getPackageName() + "businessid", businessid);
                            intent.setClass(context, SponsorDetailActivity.class);
                            context.startActivity(intent);
                        }
                    }
                }
            });
            process = (TextView) view.findViewById(R.id.process);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.can_rec:
                        if (mData.get(getAdapterPosition()) instanceof NearBySponsorResponse.DataBean.NearedpacketBean) {
                            if (((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(getAdapterPosition())).getStatus() == 0) {
                                try {
                                    ((SponsorRedDetailActivity) context).popRedPkg((NearBySponsorResponse.DataBean.NearedpacketBean) mData.get(getAdapterPosition()));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {

                            }
                        }
                        break;
                }
            }
        };
    }

    public class ReleaseRecordViewHolder extends RecyclerView.ViewHolder {
        TextView releaseName;
        TextView releaseFriend;
        TextView releaseDays;
        Button releaseDetails;
        TextView sponsorName;
        TextView moneyTv;
        TextView process;
        int taskId = -1;
        String statusStr = "";

        public ReleaseRecordViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            releaseName = (TextView) view.findViewById(R.id.release_name);
            releaseFriend = (TextView) view.findViewById(R.id.release_friend);
            releaseDays = (TextView) view.findViewById(R.id.release_days);
            releaseDetails = (Button) view.findViewById(R.id.release_details);
            sponsorName = (TextView) view.findViewById(R.id.release_sponse_name);
            moneyTv = (TextView) view.findViewById(R.id.money_red);
            releaseDetails.setOnClickListener(onClickListener);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mData.get(getAdapterPosition()) instanceof SponsorPkgRecordResponse.DataBeanX.DataBean) {
                        int redid = ((SponsorPkgRecordResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getRed_id();
                        if (redid != -1) {
                            Intent intent = new Intent();
                            intent.putExtra(context.getPackageName() + "redid", redid);
                            LocalLog.d(TAG, "statusStr =  " + statusStr);
                            if (!TextUtils.isEmpty(statusStr)) {
                                intent.putExtra(context.getPackageName() + "statusStr", statusStr);
                            }
                            intent.setAction(SPONSOR_TASK_ACTION);
                            intent.setClass(context, MyReleaseDetailActivity.class);
                            context.startActivity(intent);
                        }
                    } else if (mData.get(getAdapterPosition()) instanceof ReleaseRecordResponse.DataBeanX.DataBean) {
                        if (((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getId() != -1) {
                            Intent intent = new Intent();
                            intent.setAction(PERSON_TASK_ACTION);
                            intent.putExtra("taskid", ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getId());
                            intent.putExtra("toid", ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getTo_userid());
                            intent.putExtra("toname", ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getNickname());
                            intent.setClass(context, MyReleaseDetailActivity.class);
                            context.startActivity(intent);
                        }
                    }
                }
            });
            process = (TextView) view.findViewById(R.id.process);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.release_details:
                        if (mData.get(getAdapterPosition()) instanceof SponsorPkgRecordResponse.DataBeanX.DataBean) {
                            if (taskId != -1) {
                                Intent intent = new Intent();
                                intent.putExtra("taskid", taskId);
                                intent.putExtra("toid", ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getTo_userid());
                                intent.putExtra("toname", ((ReleaseRecordResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getNickname());
                                intent.setAction(PERSON_TASK_ACTION);
                                intent.setClass(context, MyReleaseDetailActivity.class);
                                context.startActivity(intent);
                            }
                        } else if (mData.get(getAdapterPosition()) instanceof ReleaseRecordResponse.DataBeanX.DataBean) {

                        }
                        break;
                }
            }
        };
    }
}
