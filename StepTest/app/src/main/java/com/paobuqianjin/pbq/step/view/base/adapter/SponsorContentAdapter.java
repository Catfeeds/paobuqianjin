package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RoundHisResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorCommentResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.FriendDetailActivity;
import com.paobuqianjin.pbq.step.view.emoji.MoonUtils;
import com.tencent.cos.xml.model.tag.CORSConfiguration;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.paobuqianjin.pbq.step.view.emoji.EmotionViewPagerAdapter.numToHex8;

/**
 * Created by pbq on 2018/8/13.
 */

public class SponsorContentAdapter extends RecyclerView.Adapter<SponsorContentAdapter.SponsorContentViewHolder> {
    private final static String TAG = SponsorContentAdapter.class.getSimpleName();
    List<?> mData;
    Context context;

    public SponsorContentAdapter(Context context, List<?> data) {
        mData = data;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public void onBindViewHolder(SponsorContentViewHolder holder, int position) {
        if (mData.get(position) instanceof SponsorCommentResponse.DataBeanX.DataBean.CommentarrayBean) {
            holder.iconUserid = ((SponsorCommentResponse.DataBeanX.DataBean.CommentarrayBean) mData.get(position)).getUserid();
            String sourceName, sourceVachar, toName, content;
            int[] emj = context.getResources().getIntArray(R.array.emjio_list);
            sourceVachar = ((SponsorCommentResponse.DataBeanX.DataBean.CommentarrayBean) mData.get(position)).getAvatar();
            if (!TextUtils.isEmpty(sourceVachar)) {
                Presenter.getInstance(context).getPlaceErrorImage(holder.headIcon, sourceVachar, R.drawable.default_head_ico, R.drawable.default_head_ico);
            }
            sourceName = ((SponsorCommentResponse.DataBeanX.DataBean.CommentarrayBean) mData.get(position)).getNickname();
            toName = ((SponsorCommentResponse.DataBeanX.DataBean.CommentarrayBean) mData.get(position)).getFathername();
            content = ((SponsorCommentResponse.DataBeanX.DataBean.CommentarrayBean) mData.get(position)).getContent();
            if (content != null) {
                for (int i = 0; i < emj.length; i++) {
                    content = content.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                }
            }

            long create_time = ((SponsorCommentResponse.DataBeanX.DataBean.CommentarrayBean) mData.get(position)).getCreate_time();
            String time_day_str = DateTimeUtil.formatFriendly(new Date(create_time * 1000));
            holder.timeTv.setText(time_day_str);
            if (!TextUtils.isEmpty(toName)) {
                String reply = "评论";
                String text = sourceName + reply + toName + ":" + content;
                SpannableString style = new SpannableString(text);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), 0, sourceName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff161727")), sourceName.length(), (sourceName + reply).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), (sourceName + reply).length(), (sourceName + reply + toName).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff161727")), (sourceName + reply + toName).length(), (sourceName + reply + toName + ":" + content).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                MoonUtils.identifyFaceExpression(context, holder.textContent, style, ImageSpan.ALIGN_BOTTOM);
            } else {
                String reply = "\n";
                String text = sourceName + reply + content;
                SpannableString style = new SpannableString(text);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), 0, sourceName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff161727")), sourceName.length(), (sourceName + reply).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), (sourceName + reply).length(), (sourceName + reply).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff161727")), (sourceName + reply).length(), (sourceName + reply + content).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                MoonUtils.identifyFaceExpression(context, holder.textContent, style, ImageSpan.ALIGN_BOTTOM);
            }
        }else if(mData.get(position) instanceof RoundHisResponse.DataBean.CommentListBean){
            holder.iconUserid = ((RoundHisResponse.DataBean.CommentListBean) mData.get(position)).getUserid();
            String sourceName, sourceVachar, toName, content;
            int[] emj = context.getResources().getIntArray(R.array.emjio_list);
            sourceVachar = ((RoundHisResponse.DataBean.CommentListBean) mData.get(position)).getAvatar();
            if (!TextUtils.isEmpty(sourceVachar)) {
                Presenter.getInstance(context).getPlaceErrorImage(holder.headIcon, sourceVachar, R.drawable.default_head_ico, R.drawable.default_head_ico);
            }
            sourceName = ((RoundHisResponse.DataBean.CommentListBean) mData.get(position)).getNickname();
            toName = ((RoundHisResponse.DataBean.CommentListBean) mData.get(position)).getFather_nickname();
            content = ((RoundHisResponse.DataBean.CommentListBean) mData.get(position)).getContent();
            if (content != null) {
                for (int i = 0; i < emj.length; i++) {
                    content = content.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                }
            }

            long create_time = ((RoundHisResponse.DataBean.CommentListBean) mData.get(position)).getCreate_time();
            String time_day_str = DateTimeUtil.formatFriendly(new Date(create_time * 1000));
            holder.timeTv.setText(time_day_str);
            if (!TextUtils.isEmpty(toName)) {
                String reply = "评论";
                String text = sourceName + reply + toName + ":" + content;
                SpannableString style = new SpannableString(text);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), 0, sourceName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff161727")), sourceName.length(), (sourceName + reply).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), (sourceName + reply).length(), (sourceName + reply + toName).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff161727")), (sourceName + reply + toName).length(), (sourceName + reply + toName + ":" + content).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                MoonUtils.identifyFaceExpression(context, holder.textContent, style, ImageSpan.ALIGN_BOTTOM);
            } else {
                String reply = "\n";
                String text = sourceName + reply + content;
                SpannableString style = new SpannableString(text);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), 0, sourceName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff161727")), sourceName.length(), (sourceName + reply).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), (sourceName + reply).length(), (sourceName + reply).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff161727")), (sourceName + reply).length(), (sourceName + reply + content).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                MoonUtils.identifyFaceExpression(context, holder.textContent, style, ImageSpan.ALIGN_BOTTOM);
            }
        }
    }

    @Override
    public SponsorContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SponsorContentViewHolder(LayoutInflater.from(context).inflate(
                R.layout.sponsor_content_item, parent, false
        ));
    }

    public class SponsorContentViewHolder extends RecyclerView.ViewHolder {
        int iconUserid = -1;
        CircleImageView headIcon;
        TextView textContent;
        TextView timeTv;

        public SponsorContentViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            textContent = (TextView) view.findViewById(R.id.text_content);
            headIcon = (CircleImageView) view.findViewById(R.id.head_icon);
            timeTv = (TextView) view.findViewById(R.id.time_text);
            headIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (iconUserid > 0) {
                        Intent intent = new Intent();
                        //TODO ACTION_SCAN_USERID
                        intent.putExtra("userid", iconUserid);
                        intent.setClass(context, FriendDetailActivity.class);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
