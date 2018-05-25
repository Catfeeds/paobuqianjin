package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageContentResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageLikeResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Base64Util;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.DynamicActivity;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.paobuqianjin.pbq.step.view.emoji.EmotionViewPagerAdapter.numToHex8;

/**
 * Created by pbq on 2018/3/27.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = MessageAdapter.class.getSimpleName();
    private Context context;
    private List<?> mData;

    public MessageAdapter(Context context, List<?> data) {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            LocalLog.d(TAG, "MessageContentResponse ");
            return new MessageContentViewHolder(LayoutInflater.from(context).inflate(R.layout.content_list, parent, false));
        } else if (viewType == 1) {
            LocalLog.d(TAG, "MessageLikeResponse");
            return new MessageLikeViewHolder(LayoutInflater.from(context).inflate(R.layout.like_list, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mData != null) {
            int[] emj = context.getResources().getIntArray(R.array.emjio_list);
            if (mData.get(position) instanceof MessageContentResponse.DataBeanX.DataBean) {
                if (holder instanceof MessageContentViewHolder) {
                    Presenter.getInstance(context).getPlaceErrorImage(((MessageContentViewHolder) holder).rectIcon,
                            ((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getFrom_avatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
                    ((MessageContentViewHolder) holder).dearName.setText(((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getFrom_nickanme());
                    ((MessageContentViewHolder) holder).contentText.setText(((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getTitle());
                    String content = ((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getContent();
                    LocalLog.d(TAG, "content = " + content);
                    if (content != null) {
                        for (int i = 0; i < emj.length; i++) {
                            content = content.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                        }
                    }
                    ((MessageContentViewHolder) holder).contentTextDes.setText(content);
                    //服务器保存到秒级别，本地处理为毫秒级别
                    long create_time = ((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getCreate_time();
                    LocalLog.d(TAG, "create_time = " + DateTimeUtil.formatDateTime(create_time * 1000));
                    String create_timeStr = DateTimeUtil.formatFriendly(new Date(create_time * 1000));
                    ((MessageContentViewHolder) holder).timeStmap.setText(create_timeStr);
                    int size = ((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getImages().size();
                    if (size == 1) {
                        ((MessageContentViewHolder) holder).imageA.setVisibility(View.VISIBLE);
                        Presenter.getInstance(context).getImage(((MessageContentViewHolder) holder).imageA, ((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                    } else if (size == 2) {
                        ((MessageContentViewHolder) holder).imageA.setVisibility(View.VISIBLE);
                        Presenter.getInstance(context).getImage(((MessageContentViewHolder) holder).imageA, ((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                        ((MessageContentViewHolder) holder).imageB.setVisibility(View.VISIBLE);
                        Presenter.getInstance(context).getImage(((MessageContentViewHolder) holder).imageB, ((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(1));

                    } else if (size >= 3) {
                        ((MessageContentViewHolder) holder).imageA.setVisibility(View.VISIBLE);
                        Presenter.getInstance(context).getImage(((MessageContentViewHolder) holder).imageA, ((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                        ((MessageContentViewHolder) holder).imageB.setVisibility(View.VISIBLE);
                        Presenter.getInstance(context).getImage(((MessageContentViewHolder) holder).imageB, ((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(1));
                        ((MessageContentViewHolder) holder).imageC.setVisibility(View.VISIBLE);
                        Presenter.getInstance(context).getImage(((MessageContentViewHolder) holder).imageC, ((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(2));
                    }
                    ((MessageContentViewHolder) holder).dynamicId = ((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getDynamicid();
                    ((MessageContentViewHolder) holder).is_vote = ((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getDynamicdata().getIs_vote();
                }
                /*if (((MessageContentResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                    ((MessageContentViewHolder) holder).vipFlg.setVisibility(View.VISIBLE);
                }*/
            } else if (mData.get(position) instanceof MessageLikeResponse.DataBeanX.DataBean) {
                if (holder instanceof MessageLikeViewHolder) {
                    Presenter.getInstance(context).getPlaceErrorImage(((MessageLikeViewHolder) holder).rectIcon,
                            ((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getFrom_avatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
                    ((MessageLikeViewHolder) holder).dearName.setText(((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getFrom_nickanme());
                    ((MessageLikeViewHolder) holder).contentText.setText(((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getTitle());
                    String content = Base64Util.getUidFromBase64(((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getContent());
                    LocalLog.d(TAG, "content = " + content);
                    if (content != null) {
                        for (int i = 0; i < emj.length; i++) {
                            content = content.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                        }
                    }
                    ((MessageLikeViewHolder) holder).contentTextDes.setText(content);
                    //服务器保存到秒级别，本地处理为毫秒级别
                    long create_time = ((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getCreate_time();
                    LocalLog.d(TAG, "create_time = " + DateTimeUtil.formatDateTime(create_time * 1000));
                    String create_timeStr = DateTimeUtil.formatFriendly(new Date(create_time * 1000));
                    ((MessageLikeViewHolder) holder).timeStmap.setText(create_timeStr);
                    int size = ((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getImages().size();
                    if (size == 1) {
                        ((MessageLikeViewHolder) holder).imageA.setVisibility(View.VISIBLE);
                        Presenter.getInstance(context).getImage(((MessageLikeViewHolder) holder).imageA, ((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                    } else if (size == 2) {
                        ((MessageLikeViewHolder) holder).imageA.setVisibility(View.VISIBLE);
                        Presenter.getInstance(context).getImage(((MessageLikeViewHolder) holder).imageA, ((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                        ((MessageLikeViewHolder) holder).imageB.setVisibility(View.VISIBLE);
                        Presenter.getInstance(context).getImage(((MessageLikeViewHolder) holder).imageB, ((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(1));

                    } else if (size >= 3) {
                        ((MessageLikeViewHolder) holder).imageA.setVisibility(View.VISIBLE);
                        Presenter.getInstance(context).getImage(((MessageLikeViewHolder) holder).imageA, ((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                        ((MessageLikeViewHolder) holder).imageB.setVisibility(View.VISIBLE);
                        Presenter.getInstance(context).getImage(((MessageLikeViewHolder) holder).imageB, ((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(1));
                        ((MessageLikeViewHolder) holder).imageC.setVisibility(View.VISIBLE);
                        Presenter.getInstance(context).getImage(((MessageLikeViewHolder) holder).imageC, ((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(2));
                    }
                    ((MessageLikeViewHolder) holder).dynamicId = ((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getDynamicid();
                    ((MessageLikeViewHolder) holder).is_vote = ((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getDynamicdata().getIs_vote();
                }
                /*if (((MessageLikeResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                    ((MessageLikeViewHolder) holder).vipFlg.setVisibility(View.VISIBLE);
                }*/
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mData != null) {
            if (mData.get(position) instanceof MessageContentResponse.DataBeanX.DataBean) {
                return 0;
            } else if (mData.get(position) instanceof MessageLikeResponse.DataBeanX.DataBean) {
                return 1;
            }
        }
        return -1;
    }

    public class MessageContentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.rect_icon)
        CircleImageView rectIcon;
        @Bind(R.id.dear_name)
        TextView dearName;
        @Bind(R.id.button_reply)
        TextView buttonReply;
        @Bind(R.id.content_title)
        RelativeLayout contentTitle;
        @Bind(R.id.content_text)
        TextView contentText;
        @Bind(R.id.content_text_des)
        TextView contentTextDes;
        @Bind(R.id.image_a)
        ImageView imageA;
        @Bind(R.id.image_b)
        ImageView imageB;
        @Bind(R.id.image_c)
        ImageView imageC;
        @Bind(R.id.image_d)
        ImageView imageD;
        @Bind(R.id.content_details)
        RelativeLayout contentDetails;
        @Bind(R.id.content_span)
        RelativeLayout contentSpan;
        @Bind(R.id.time_stmap)
        TextView timeStmap;
        @Bind(R.id.vip_flg)
        ImageView vipFlg;

        int dynamicId = -1;
        int is_vote = 0;


        public MessageContentViewHolder(View view) {
            super(view);
            initView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra(context.getPackageName() + "dynamicId", dynamicId);
                    intent.putExtra(context.getPackageName() + "userId", Presenter.getInstance(context).getId());
                    intent.putExtra(context.getPackageName() + "is_vote", is_vote);
                    intent.setClass(context, DynamicActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        private void initView(View view) {
            rectIcon = (CircleImageView) view.findViewById(R.id.rect_icon);
            dearName = (TextView) view.findViewById(R.id.dear_name);
            contentText = (TextView) view.findViewById(R.id.content_text);
            contentTextDes = (TextView) view.findViewById(R.id.content_text_des);
            imageA = (ImageView) view.findViewById(R.id.image_a);
            imageB = (ImageView) view.findViewById(R.id.image_b);
            imageC = (ImageView) view.findViewById(R.id.image_c);
            timeStmap = (TextView) view.findViewById(R.id.time_stmap);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
        }
    }

    public class MessageLikeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.rect_icon)
        CircleImageView rectIcon;
        @Bind(R.id.dear_name)
        TextView dearName;
        @Bind(R.id.content_title)
        RelativeLayout contentTitle;
        @Bind(R.id.content_text)
        TextView contentText;
        @Bind(R.id.content_text_des)
        TextView contentTextDes;
        @Bind(R.id.image_a)
        ImageView imageA;
        @Bind(R.id.image_b)
        ImageView imageB;
        @Bind(R.id.image_c)
        ImageView imageC;
        @Bind(R.id.image_d)
        ImageView imageD;
        @Bind(R.id.content_details)
        RelativeLayout contentDetails;
        @Bind(R.id.content_span)
        RelativeLayout contentSpan;
        @Bind(R.id.time_stmap)
        TextView timeStmap;
        @Bind(R.id.vip_flg)
        ImageView vipFlg;
        int dynamicId = -1;
        int is_vote = 0;

        public MessageLikeViewHolder(View view) {
            super(view);
            initView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra(context.getPackageName() + "dynamicId", dynamicId);
                    intent.putExtra(context.getPackageName() + "userId", Presenter.getInstance(context).getId());
                    intent.putExtra(context.getPackageName() + "is_vote", is_vote);
                    intent.setClass(context, DynamicActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        private void initView(View view) {
            rectIcon = (CircleImageView) view.findViewById(R.id.rect_icon);
            dearName = (TextView) view.findViewById(R.id.dear_name);
            contentText = (TextView) view.findViewById(R.id.content_text);
            contentTextDes = (TextView) view.findViewById(R.id.content_text_des);
            imageA = (ImageView) view.findViewById(R.id.image_a);
            imageB = (ImageView) view.findViewById(R.id.image_b);
            imageC = (ImageView) view.findViewById(R.id.image_c);
            timeStmap = (TextView) view.findViewById(R.id.time_stmap);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
        }
    }
}
