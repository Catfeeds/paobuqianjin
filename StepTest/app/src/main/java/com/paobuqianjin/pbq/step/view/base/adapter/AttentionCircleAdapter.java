package com.paobuqianjin.pbq.step.view.base.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutVoteParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicAllIndexResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PutVoteResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.Base64Util;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.DynamicActivity;
import com.paobuqianjin.pbq.step.view.activity.UserCenterActivity;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.paobuqianjin.pbq.step.view.emoji.EmotionViewPagerAdapter.numToHex8;

/**
 * Created by pbq on 2017/12/29.
 */

public class AttentionCircleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = AttentionCircleAdapter.class.getSimpleName();


    private List<DynamicAllIndexResponse.DataBeanX.DataBean> data;
    private Context mContext;
    private Fragment fragment;
    private final static int DYNAMIC_DETAIL = 205;

    public enum ITEM_TYPE {
        ITEM_TYPE_NO_IMG,
        ITEM_TYPE_ONE_IMG,
        ITEM_TYPE_TWO_IMG,
        ITEM_TYPE_THREE_IMG
    }

    public AttentionCircleAdapter(Context context, List<DynamicAllIndexResponse.DataBeanX.DataBean> data, Fragment fragment) {
        super();
        mContext = context;
        this.data = data;
        this.fragment = fragment;
    }

    public void notifyDataSetChanged(List<DynamicAllIndexResponse.DataBeanX.DataBean> data) {
        this.data = data;
        super.notifyDataSetChanged();
    }


    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_NO_IMG.ordinal()) {
            return new OneOrZeroViewHodler(
                    LayoutInflater.from(mContext).inflate(R.layout.dynamic_style_one_pic_list, parent, false)
                    , 0);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_ONE_IMG.ordinal()) {
            return new OneOrZeroViewHodler(
                    LayoutInflater.from(mContext).inflate(R.layout.dynamic_style_one_pic_list, parent, false)
                    , 1);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_TWO_IMG.ordinal()) {
            return new TwoPicViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.dynamic_style_two_list, parent, false)
                    , 2);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_THREE_IMG.ordinal()) {
            return new ThreePicViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.dynamic_style_three_list, parent, false)
                    , 3);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        //根据图片数据条目个数判断类型
        if (data.get(position).getImages().size() == 0) {
            //无图片
            return ITEM_TYPE.ITEM_TYPE_NO_IMG.ordinal();
        } else if (data.get(position).getImages().size() == 1) {
            if (!data.get(position).getImages().get(0).equals("")) {
                return ITEM_TYPE.ITEM_TYPE_ONE_IMG.ordinal();
            }
            return ITEM_TYPE.ITEM_TYPE_NO_IMG.ordinal();

        } else if (data.get(position).getImages().size() == 2) {
            return ITEM_TYPE.ITEM_TYPE_TWO_IMG.ordinal();
        } else if (data.get(position).getImages().size() >= 3) {
            return ITEM_TYPE.ITEM_TYPE_THREE_IMG.ordinal();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        updateItem(holder, position);
    }

    @TargetApi(19)
    private void updateItem(RecyclerView.ViewHolder holder, int position) {
        long create_time = data.get(position).getCreate_time();
        //服务器保存到秒级别，本地处理为毫秒级别
        LocalLog.d(TAG, "create_time = " + create_time);
        String create_timeStr = DateTimeUtil.formatFriendly(new Date(create_time * 1000));
        int[] emj = mContext.getResources().getIntArray(R.array.emjio_list);//TODO 优化
        LocalLog.d(TAG, "DATA = " + data.get(position).toString());
        if (holder instanceof OneOrZeroViewHodler) {
            LocalLog.d(TAG, "无图");
            ((OneOrZeroViewHodler) holder).dynamicid = data.get(position).getId();
            ((OneOrZeroViewHodler) holder).userid = data.get(position).getUserid();
            ((OneOrZeroViewHodler) holder).is_vote = data.get(position).getIs_vote();
            if (((OneOrZeroViewHodler) holder).viewType == 1) {
                ((OneOrZeroViewHodler) holder).timeStmp.setText(create_timeStr);
                Presenter.getInstance(mContext).getPlaceErrorImage(((OneOrZeroViewHodler) holder).dynamicPicOne, data.get(position).getImages().get(0), R.drawable.bitmap_null, R.drawable.bitmap_null);
                Presenter.getInstance(mContext).getPlaceErrorImage(((OneOrZeroViewHodler) holder).dynamicUserIcon, data.get(position).getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);


                String content = Base64Util.getUidFromBase64(data.get(position).getDynamic());
                LocalLog.d(TAG, "content = " + content);
                if (content != null) {
                    for (int i = 0; i < emj.length; i++) {
                        content = content.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                    }
                }

                ((OneOrZeroViewHodler) holder).dynamicContentText.setText(content);
                ((OneOrZeroViewHodler) holder).dynamicUserName.setText(data.get(position).getNickname());
                ((OneOrZeroViewHodler) holder).dynamicLocationCity.setText(data.get(position).getShowAddress());
                ((OneOrZeroViewHodler) holder).contentNumbers.setText(String.valueOf(data.get(position).getComment()));
                ((OneOrZeroViewHodler) holder).contentSupports.setText(String.valueOf(data.get(position).getVote()));
                ((OneOrZeroViewHodler) holder).vote = data.get(position).getVote();
                if (data.get(position).getIs_vote() == 1) {
                    ((OneOrZeroViewHodler) holder).likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_s));
                } else {
                    ((OneOrZeroViewHodler) holder).likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_n));
                }
                if (data.get(position).getComment() <= 0) {
                    LocalLog.d(TAG, "updateItem() 无人评论");
                    ((OneOrZeroViewHodler) holder).scanMore.setVisibility(View.GONE);
                    ((OneOrZeroViewHodler) holder).firstContent.setVisibility(View.GONE);
                } else {
                    ((OneOrZeroViewHodler) holder).scanMore.setVisibility(View.VISIBLE);
                    ((OneOrZeroViewHodler) holder).firstContent.setVisibility(View.VISIBLE);
                }

                if (data.get(position).getOne_comment() != null) {
                    if (data.get(position).getOne_comment().getNickname() != null) {
                        String contentL = data.get(position).getOne_comment().getContent();
                        LocalLog.d(TAG, "content = " + contentL);
                        if (contentL != null) {
                            for (int i = 0; i < emj.length; i++) {
                                contentL = contentL.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                            }
                        }
                        LocalLog.d(TAG, "name =  " + data.get(position).getOne_comment().getNickname());
                        String replyStr = data.get(position).getOne_comment().getNickname() + ":" + contentL;
                        Log.d(TAG, "replyStr = " + replyStr);
                        SpannableString spannableString = new SpannableString(replyStr);
                        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.color_6c71c4));
                        spannableString.setSpan(colorSpan, 0, data.get(position).getOne_comment().getNickname().length()
                                , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        ((OneOrZeroViewHodler) holder).firstContent.setText(spannableString);
                    }
                }

            } else if (((OneOrZeroViewHodler) holder).viewType == 0) {
                LocalLog.d(TAG, "1图");
                ((OneOrZeroViewHodler) holder).timeStmp.setText(create_timeStr);
                Presenter.getInstance(mContext).getPlaceErrorImage(((OneOrZeroViewHodler) holder).dynamicUserIcon, data.get(position).getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
                ((OneOrZeroViewHodler) holder).is_vote = data.get(position).getIs_vote();
                String content = Base64Util.getUidFromBase64(data.get(position).getDynamic());
                LocalLog.d(TAG, "content = " + content);
                if (content != null) {
                    for (int i = 0; i < emj.length; i++) {
                        content = content.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                    }
                }
                ((OneOrZeroViewHodler) holder).dynamicContentText.setText(content);
                ((OneOrZeroViewHodler) holder).dynamicUserName.setText(data.get(position).getNickname());
                ((OneOrZeroViewHodler) holder).dynamicLocationCity.setText(data.get(position).getShowAddress());
                ((OneOrZeroViewHodler) holder).contentNumbers.setText(String.valueOf(data.get(position).getComment()));
                ((OneOrZeroViewHodler) holder).contentSupports.setText(String.valueOf(data.get(position).getVote()));
                ((OneOrZeroViewHodler) holder).vote = data.get(position).getVote();
                if (data.get(position).getIs_vote() == 1) {
                    ((OneOrZeroViewHodler) holder).likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_s));
                } else {
                    ((OneOrZeroViewHodler) holder).likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_n));
                }
                if (data.get(position).getComment() <= 0) {
                    LocalLog.d(TAG, "updateItem() 无人评论");
                    ((OneOrZeroViewHodler) holder).scanMore.setVisibility(View.GONE);
                    ((OneOrZeroViewHodler) holder).firstContent.setVisibility(View.GONE);
                } else {
                    ((OneOrZeroViewHodler) holder).scanMore.setVisibility(View.VISIBLE);
                    ((OneOrZeroViewHodler) holder).firstContent.setVisibility(View.VISIBLE);
                }
                if (data.get(position).getOne_comment() != null) {
                    if (data.get(position).getOne_comment().getNickname() != null) {
                        String contentL = data.get(position).getOne_comment().getContent();
                        LocalLog.d(TAG, "content = " + contentL);
                        if (contentL != null) {
                            for (int i = 0; i < emj.length; i++) {
                                contentL = contentL.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                            }
                        }

                        LocalLog.d(TAG, "name =  " + data.get(position).getOne_comment().getNickname());
                        String replyStr = data.get(position).getOne_comment().getNickname() + ":" + contentL;
                        Log.d(TAG, "replyStr = " + replyStr);
                        SpannableString spannableString = new SpannableString(replyStr);
                        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.color_6c71c4));
                        spannableString.setSpan(colorSpan, 0, data.get(position).getOne_comment().getNickname().length()
                                , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        ((OneOrZeroViewHodler) holder).firstContent.setText(spannableString);
                    }
                }
            }
            ((OneOrZeroViewHodler) holder).position = position;
            if (data.get(position).getVip() == 1) {
                ((OneOrZeroViewHodler) holder).vipFlg.setVisibility(View.VISIBLE);
            }
        } else if (holder instanceof TwoPicViewHolder) {
            ((TwoPicViewHolder) holder).timeStmp.setText(create_timeStr);
            ((TwoPicViewHolder) holder).dynamicid = data.get(position).getId();
            ((TwoPicViewHolder) holder).userid = data.get(position).getUserid();
            ((TwoPicViewHolder) holder).is_vote = data.get(position).getIs_vote();
            Presenter.getInstance(mContext).getPlaceErrorImage(((TwoPicViewHolder) holder).dynamicPicOne, data.get(position).getImages().get(0), R.drawable.bitmap_null, R.drawable.bitmap_null);
            Presenter.getInstance(mContext).getPlaceErrorImage(((TwoPicViewHolder) holder).dynamicPicTwo, data.get(position).getImages().get(1), R.drawable.bitmap_null, R.drawable.bitmap_null);
            Presenter.getInstance(mContext).getPlaceErrorImage(((TwoPicViewHolder) holder).dynamicUserIcon, data.get(position).getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
            String content = Base64Util.getUidFromBase64(data.get(position).getDynamic());
            LocalLog.d(TAG, "content = " + content);
            if (content != null) {
                for (int i = 0; i < emj.length; i++) {
                    content = content.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                }
            }

            ((TwoPicViewHolder) holder).dynamicContentText.setText(content);
            ((TwoPicViewHolder) holder).dynamicUserName.setText(data.get(position).getNickname());
            ((TwoPicViewHolder) holder).dynamicLocationCity.setText(data.get(position).getShowAddress());
            ((TwoPicViewHolder) holder).contentNumbers.setText(String.valueOf(data.get(position).getComment()));
            ((TwoPicViewHolder) holder).contentSupports.setText(String.valueOf(data.get(position).getVote()));
            ((TwoPicViewHolder) holder).vote = data.get(position).getVote();
            if (data.get(position).getIs_vote() == 1) {
                ((TwoPicViewHolder) holder).likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_s));
            } else {
                ((TwoPicViewHolder) holder).likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_n));
            }
            if (data.get(position).getComment() <= 0) {
                LocalLog.d(TAG, "updateItem() 无人评论");
                ((TwoPicViewHolder) holder).scanMore.setVisibility(View.GONE);
                ((TwoPicViewHolder) holder).firstContent.setVisibility(View.GONE);
            } else {
                ((TwoPicViewHolder) holder).scanMore.setVisibility(View.VISIBLE);
                ((TwoPicViewHolder) holder).firstContent.setVisibility(View.VISIBLE);
            }
            if (data.get(position).getOne_comment() != null) {
                if (data.get(position).getOne_comment().getNickname() != null) {
                    String contentL = data.get(position).getOne_comment().getContent();
                    LocalLog.d(TAG, "content = " + contentL);
                    if (contentL != null) {
                        for (int i = 0; i < emj.length; i++) {
                            contentL = contentL.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                        }
                    }

                    LocalLog.d(TAG, "name =  " + data.get(position).getOne_comment().getNickname());
                    String replyStr = data.get(position).getOne_comment().getNickname() + ":" + contentL;
                    Log.d(TAG, "replyStr = " + replyStr);
                    SpannableString spannableString = new SpannableString(replyStr);
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.color_6c71c4));
                    spannableString.setSpan(colorSpan, 0, data.get(position).getOne_comment().getNickname().length()
                            , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    ((TwoPicViewHolder) holder).firstContent.setText(spannableString);
                }
            }

            LocalLog.d(TAG, "2图");
            ((TwoPicViewHolder) holder).position = position;
            if (data.get(position).getVip() == 1) {
                ((TwoPicViewHolder) holder).vipFlg.setVisibility(View.VISIBLE);
            }
        } else if (holder instanceof ThreePicViewHolder) {
            ((ThreePicViewHolder) holder).timeStmp.setText(create_timeStr);
            ((ThreePicViewHolder) holder).dynamicid = data.get(position).getId();
            ((ThreePicViewHolder) holder).userid = data.get(position).getUserid();
            ((ThreePicViewHolder) holder).is_vote = data.get(position).getIs_vote();
            ((ThreePicViewHolder) holder).contentNumbers.setText(String.valueOf(data.get(position).getComment()));
            Presenter.getInstance(mContext).getPlaceErrorImage(((ThreePicViewHolder) holder).dynamicPicOne, data.get(position).getImages().get(0), R.drawable.bitmap_null, R.drawable.bitmap_null);
            Presenter.getInstance(mContext).getPlaceErrorImage(((ThreePicViewHolder) holder).dynamicPicTwo, data.get(position).getImages().get(1), R.drawable.bitmap_null, R.drawable.bitmap_null);
            Presenter.getInstance(mContext).getPlaceErrorImage(((ThreePicViewHolder) holder).dynamicPicThree, data.get(position).getImages().get(2), R.drawable.bitmap_null, R.drawable.bitmap_null);
            Presenter.getInstance(mContext).getPlaceErrorImage(((ThreePicViewHolder) holder).dynamicUserIcon, data.get(position).getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
            String content = Base64Util.getUidFromBase64(data.get(position).getDynamic());
            ((ThreePicViewHolder) holder).dynamicContentText.setText(content);
            ((ThreePicViewHolder) holder).dynamicUserName.setText(data.get(position).getNickname());
            ((ThreePicViewHolder) holder).dynamicLocationCity.setText(data.get(position).getShowAddress());
            ((ThreePicViewHolder) holder).contentSupports.setText(String.valueOf(data.get(position).getVote()));
            ((ThreePicViewHolder) holder).vote = data.get(position).getVote();
            if (data.get(position).getIs_vote() == 1) {
                ((ThreePicViewHolder) holder).likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_s));
            } else {
                ((ThreePicViewHolder) holder).likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_n));
            }
            LocalLog.d(TAG, "3图");
            if (data.get(position).getComment() <= 0) {
                LocalLog.d(TAG, "updateItem() 无人评论");
                ((ThreePicViewHolder) holder).scanMore.setVisibility(View.GONE);
                ((ThreePicViewHolder) holder).firstContent.setVisibility(View.GONE);
            } else {
                LocalLog.d(TAG, "updateItem() 有评论");
                ((ThreePicViewHolder) holder).scanMore.setVisibility(View.VISIBLE);
                ((ThreePicViewHolder) holder).firstContent.setVisibility(View.VISIBLE);
            }
            if (data.get(position).getOne_comment() != null) {
                if (data.get(position).getOne_comment().getNickname() != null) {
                    String contentL = data.get(position).getOne_comment().getContent();
                    LocalLog.d(TAG, "content = " + contentL);
                    if (contentL != null) {
                        for (int i = 0; i < emj.length; i++) {
                            contentL = contentL.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                        }
                    }

                    LocalLog.d(TAG, "name =  " + data.get(position).getOne_comment().getNickname());
                    String replyStr = data.get(position).getOne_comment().getNickname() + ":" + contentL;
                    Log.d(TAG, "replyStr = " + replyStr);
                    SpannableString spannableString = new SpannableString(replyStr);
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.color_6c71c4));
                    spannableString.setSpan(colorSpan, 0, data.get(position).getOne_comment().getNickname().length(),
                            Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    ((ThreePicViewHolder) holder).firstContent.setText(spannableString);
                }
            }
            ((ThreePicViewHolder) holder).position = position;
            if (data.get(position).getVip() == 1) {
                ((ThreePicViewHolder) holder).vipFlg.setVisibility(View.VISIBLE);
            }
        } else {
            LocalLog.e(TAG, " unknown data!");
        }
    }

    public class OneOrZeroViewHodler extends
            RecyclerView.ViewHolder {
        int viewType = -1;
        //@Bind(R.id.dynamic_user_name)
        TextView dynamicUserName;
        //@Bind(R.id.dynamic_user_icon)
        CircleImageView dynamicUserIcon;
        //@Bind(R.id.dynamic_owner_rel)
        RelativeLayout dynamicOwnerRel;
        //@Bind(R.id.dynamic_content_text)
        TextView dynamicContentText;
        //@Bind(R.id.dynamic_pic_one)
        ImageView dynamicPicOne;
        //@Bind(R.id.pic_content_rel)
        RelativeLayout picContentRel;
        //@Bind(R.id.dynamic_location_city)
        TextView dynamicLocationCity;
        //@Bind(R.id.content_numbers)
        TextView contentNumbers;
        //@Bind(R.id.content_number_icon)
        ImageView contentNumberIcon;
        //@Bind(R.id.content_supports)
        TextView contentSupports;
        //@Bind(R.id.location_support_rel)
        RelativeLayout locationSupportRel;
        //@Bind(R.id.line_content)
        ImageView lineContent;
        //@Bind(R.id.first_content)
        TextView firstContent;
        //@Bind(R.id.content_des)
        RelativeLayout contentDes;
        //@Bind(R.id.line_content_list)
        ImageView lineContentList;
        //@Bind(R.id.scan_more)
        TextView scanMore;
        //@Bind(R.id.like_num_icon)
        ImageView likeNumIcon;
        @Bind(R.id.time_stmp)
        TextView timeStmp;
        @Bind(R.id.vip_flg)
        ImageView vipFlg;
        int dynamicid = -1;
        int userid = -1;
        int is_vote = 0;
        int vote = 0;
        int position = 0;
        @Bind(R.id.like_num_span)
        RelativeLayout likeNumSpan;
        DynamicAllIndexResponse.DataBeanX.DataBean dataBean;
        private InnerCallBack innerCallBack = new InnerCallBack() {
            @Override
            public void innerCallBack(Object object) {
                if (object instanceof PutVoteResponse) {
                    if (((PutVoteResponse) object).getError() == 0) {
                        if (((PutVoteResponse) object).getMessage().equals("点赞成功")) {
                            likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_s));

                            vote++;
                            is_vote = 1;
                        } else {
                            likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_n));
                            vote--;
                            is_vote = 0;
                        }
                        data.get(position).setIs_vote(is_vote);
                        contentSupports.setText(String.valueOf(vote));
                    }
                }
            }
        };


        public OneOrZeroViewHodler(View view, int viewType) {
            super(view);
            this.viewType = viewType;
            initViewHolder(view, viewType);
        }

        private void initViewHolder(View view, int viewType) {
            LocalLog.d(TAG, "initViewHolder()  enter" + viewType);
            dynamicPicOne = (ImageView) view.findViewById(R.id.dynamic_pic_one);
            switch (viewType) {
                case 0:
                    dynamicPicOne.setVisibility(View.GONE);
                    break;
                case 1:
                    dynamicPicOne.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            dynamicContentText = (TextView) view.findViewById(R.id.dynamic_content_text);
            dynamicUserName = (TextView) view.findViewById(R.id.dynamic_user_name);
            dynamicUserIcon = (CircleImageView) view.findViewById(R.id.dynamic_user_icon);
            scanMore = (TextView) view.findViewById(R.id.scan_more);
            scanMore.setOnClickListener(onClickListener);
            dynamicLocationCity = (TextView) view.findViewById(R.id.dynamic_location_city);
            contentNumbers = (TextView) view.findViewById(R.id.content_numbers);
            contentNumberIcon = (ImageView) view.findViewById(R.id.content_number_icon);
            contentSupports = (TextView) view.findViewById(R.id.content_supports);
            firstContent = (TextView) view.findViewById(R.id.first_content);
            likeNumIcon = (ImageView) view.findViewById(R.id.like_num_icon);
            timeStmp = (TextView) view.findViewById(R.id.time_stmp);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
            likeNumSpan = (RelativeLayout) view.findViewById(R.id.like_num_span);
            likeNumSpan.setOnClickListener(onClickListener);
            dynamicUserIcon.setOnClickListener(onClickListener);
            dynamicUserName.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "onClick() enter");
                switch (view.getId()) {
                    case R.id.like_num_span:
/*                        LocalLog.d(TAG, "点赞");
                        PutVoteParam putVoteParam = new PutVoteParam();
                        putVoteParam.setDynamicid(dynamicid).setUserid(Presenter.getInstance(mContext).getId());
                        Presenter.getInstance(mContext).putVote(putVoteParam, innerCallBack);*/
                        break;
                    case R.id.scan_more:
                        LocalLog.d(TAG, "点击查看更多评价");
                        LocalLog.d(TAG, "dynamicId = " + dynamicid + ",userId = " + userid);
                        Intent intent = new Intent();
                        intent.putExtra(mContext.getPackageName() + "dynamicId", dynamicid);
                        intent.putExtra(mContext.getPackageName() + "userId", userid);
                        intent.putExtra(mContext.getPackageName() + "is_vote", is_vote);
                        intent.setClass(mContext, DynamicActivity.class);
                        fragment.startActivityForResult(intent, DYNAMIC_DETAIL);
                        break;
                    case R.id.dynamic_user_icon:
                    case R.id.dynamic_user_name:
                        LocalLog.d(TAG, "点击头像");
                        LocalLog.d(TAG, "dynamicId = " + dynamicid + ",userId = " + userid);
                        Intent intentPersonDetial = new Intent();
                        intentPersonDetial.putExtra("userid", userid);
                        intentPersonDetial.setClass(mContext, UserCenterActivity.class);
                        fragment.startActivityForResult(intentPersonDetial, DYNAMIC_DETAIL);
                        break;
                }
            }
        };
    }


    public class TwoPicViewHolder extends
            RecyclerView.ViewHolder {
        //@Bind(R.id.dynamic_user_name)
        TextView dynamicUserName;
        //@Bind(R.id.dynamic_user_icon)
        CircleImageView dynamicUserIcon;
        //@Bind(R.id.dynamic_owner_rel)
        RelativeLayout dynamicOwnerRel;
        //@Bind(R.id.dynamic_content_text)
        TextView dynamicContentText;
        //@Bind(R.id.dynamic_pic_one)
        ImageView dynamicPicOne;
        //@Bind(R.id.dynamic_pic_two)
        ImageView dynamicPicTwo;
        //@Bind(R.id.pic_content_rel)
        RelativeLayout picContentRel;
        //@Bind(R.id.dynamic_location_city)
        TextView dynamicLocationCity;
        //@Bind(R.id.content_numbers)
        TextView contentNumbers;
        //@Bind(R.id.content_number_icon)
        ImageView contentNumberIcon;
        //@Bind(R.id.content_supports)
        TextView contentSupports;
        //@Bind(R.id.location_support_rel)
        RelativeLayout locationSupportRel;
        //@Bind(R.id.line_content)
        ImageView lineContent;
        //@Bind(R.id.first_content)
        TextView firstContent;
        //@Bind(R.id.scan_more)
        TextView scanMore;
        //@Bind(R.id.content_des)
        RelativeLayout contentDes;
        //@Bind(R.id.line_content_list)
        ImageView lineContentList;
        int viewType = -1;
        //@Bind(R.id.like_num_icon)
        ImageView likeNumIcon;
        @Bind(R.id.time_stmp)
        TextView timeStmp;
        @Bind(R.id.vip_flg)
        ImageView vipFlg;
        int dynamicid = -1;
        int userid = -1;
        int is_vote = 0;
        int vote = 0;
        int position = 0;
        @Bind(R.id.like_num_span)
        RelativeLayout likeNumSpan;

        public TwoPicViewHolder(View view, int viewType) {
            super(view);
            this.viewType = viewType;
            initViewHolder(view, viewType);
        }

        private InnerCallBack innerCallBack = new InnerCallBack() {
            @Override
            public void innerCallBack(Object object) {
                if (object instanceof PutVoteResponse) {
                    if (((PutVoteResponse) object).getError() == 0) {
                        if (((PutVoteResponse) object).getMessage().equals("点赞成功")) {
                            likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_s));
                            vote++;
                            is_vote = 1;
                        } else {
                            likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_n));
                            vote--;
                            is_vote = 0;
                        }
                        contentSupports.setText(String.valueOf(vote));
                        data.get(position).setIs_vote(is_vote);
                    }
                }
            }
        };

        private void initViewHolder(View view, int viewType) {
            LocalLog.d(TAG, "initViewHolder()  enter" + viewType);
            dynamicUserIcon = (CircleImageView) view.findViewById(R.id.dynamic_user_icon);
            dynamicContentText = (TextView) view.findViewById(R.id.dynamic_content_text);
            dynamicPicOne = (ImageView) view.findViewById(R.id.dynamic_pic_one);
            dynamicPicTwo = (ImageView) view.findViewById(R.id.dynamic_pic_two);
            dynamicLocationCity = (TextView) view.findViewById(R.id.dynamic_location_city);
            contentNumbers = (TextView) view.findViewById(R.id.content_numbers);
            contentNumberIcon = (ImageView) view.findViewById(R.id.content_number_icon);
            contentSupports = (TextView) view.findViewById(R.id.content_supports);
            firstContent = (TextView) view.findViewById(R.id.first_content);
            scanMore = (TextView) view.findViewById(R.id.scan_more);
            dynamicUserName = (TextView) view.findViewById(R.id.dynamic_user_name);
            likeNumIcon = (ImageView) view.findViewById(R.id.like_num_icon);
            timeStmp = (TextView) view.findViewById(R.id.time_stmp);
            scanMore.setOnClickListener(onClickListener);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
            likeNumSpan = (RelativeLayout) view.findViewById(R.id.like_num_span);
            likeNumSpan.setOnClickListener(onClickListener);
            dynamicUserIcon.setOnClickListener(onClickListener);
            dynamicUserName.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "onClick() enter");
                switch (view.getId()) {
                    case R.id.like_num_span:
/*                        LocalLog.d(TAG, "点赞");
                        PutVoteParam putVoteParam = new PutVoteParam();
                        putVoteParam.setDynamicid(dynamicid).setUserid(Presenter.getInstance(mContext).getId());
                        Presenter.getInstance(mContext).putVote(putVoteParam, innerCallBack);*/
                        break;
                    case R.id.scan_more:
                        LocalLog.d(TAG, "点击查看更多评价");
                        LocalLog.d(TAG, "dynamicId = " + dynamicid + ",userId = " + userid);
                        Intent intent = new Intent();
                        intent.putExtra(mContext.getPackageName() + "dynamicId", dynamicid);
                        intent.putExtra(mContext.getPackageName() + "userId", userid);
                        intent.putExtra(mContext.getPackageName() + "is_vote", is_vote);
                        intent.setClass(mContext, DynamicActivity.class);
                        fragment.startActivityForResult(intent, DYNAMIC_DETAIL);
                        break;
                    case R.id.dynamic_user_icon:
                    case R.id.dynamic_user_name:
                        LocalLog.d(TAG, "点击头像");
                        LocalLog.d(TAG, "dynamicId = " + dynamicid + ",userId = " + userid);
                        Intent intentPersonDetial = new Intent();
                        intentPersonDetial.putExtra("userid", userid);
                        intentPersonDetial.setClass(mContext, UserCenterActivity.class);
                        fragment.startActivityForResult(intentPersonDetial, DYNAMIC_DETAIL);
                        break;
                }
            }
        };
    }

    public class ThreePicViewHolder extends RecyclerView.ViewHolder {
        //@Bind(R.id.dynamic_user_name)
        TextView dynamicUserName;
        //@Bind(R.id.dynamic_user_icon)
        CircleImageView dynamicUserIcon;
        //@Bind(R.id.dynamic_owner_rel)
        RelativeLayout dynamicOwnerRel;
        //@Bind(R.id.dynamic_content_text)
        TextView dynamicContentText;
        //@Bind(R.id.dynamic_pic_one)
        ImageView dynamicPicOne;
        //@Bind(R.id.dynamic_pic_two)
        ImageView dynamicPicTwo;
        //@Bind(R.id.dynamic_pic_three)
        ImageView dynamicPicThree;
        //@Bind(R.id.pic_content_rel)
        RelativeLayout picContentRel;
        //@Bind(R.id.dynamic_location_city)
        TextView dynamicLocationCity;
        //@Bind(R.id.content_numbers)
        TextView contentNumbers;
        //@Bind(R.id.content_number_icon)
        ImageView contentNumberIcon;
        //@Bind(R.id.content_supports)
        TextView contentSupports;
        //@Bind(R.id.location_support_rel)
        RelativeLayout locationSupportRel;
        //@Bind(R.id.line_content)
        ImageView lineContent;
        //@Bind(R.id.first_content)
        TextView firstContent;
        //@Bind(R.id.content_des)
        RelativeLayout contentDes;
        //@Bind(R.id.line_content_list)
        ImageView lineContentList;
        //@Bind(R.id.like_num_icon)
        ImageView likeNumIcon;
        @Bind(R.id.time_stmp)
        TextView timeStmp;
        @Bind(R.id.vip_flg)
        ImageView vipFlg;
        int viewType = -1;
        //@Bind(R.id.scan_more)
        TextView scanMore;
        @Bind(R.id.like_num_span)
        RelativeLayout likeNumSpan;
        int dynamicid = -1;
        int userid = -1;
        int is_vote = 0;
        int vote = 0;
        int position = 0;

        public ThreePicViewHolder(View view, int viewType) {
            super(view);
            this.viewType = viewType;
            initViewHolder(view, viewType);
        }

        private void initViewHolder(View view, int viewType) {
            LocalLog.d(TAG, "initViewHolder()  enter" + viewType);
            dynamicUserIcon = (CircleImageView) view.findViewById(R.id.dynamic_user_icon);
            dynamicContentText = (TextView) view.findViewById(R.id.dynamic_content_text);
            dynamicPicOne = (ImageView) view.findViewById(R.id.dynamic_pic_one);
            dynamicPicTwo = (ImageView) view.findViewById(R.id.dynamic_pic_two);
            dynamicPicThree = (ImageView) view.findViewById(R.id.dynamic_pic_three);
            dynamicLocationCity = (TextView) view.findViewById(R.id.dynamic_location_city);
            contentNumbers = (TextView) view.findViewById(R.id.content_numbers);
            contentNumberIcon = (ImageView) view.findViewById(R.id.content_number_icon);
            contentSupports = (TextView) view.findViewById(R.id.content_supports);
            firstContent = (TextView) view.findViewById(R.id.first_content);
            scanMore = (TextView) view.findViewById(R.id.scan_more);
            scanMore.setOnClickListener(onClickListener);
            dynamicUserName = (TextView) view.findViewById(R.id.dynamic_user_name);
            likeNumIcon = (ImageView) view.findViewById(R.id.like_num_icon);
            timeStmp = (TextView) view.findViewById(R.id.time_stmp);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
            likeNumSpan = (RelativeLayout) view.findViewById(R.id.like_num_span);
            likeNumSpan.setOnClickListener(onClickListener);
            dynamicUserIcon.setOnClickListener(onClickListener);
            dynamicUserName.setOnClickListener(onClickListener);
        }

        private InnerCallBack innerCallBack = new InnerCallBack() {
            @Override
            public void innerCallBack(Object object) {
                if (object instanceof PutVoteResponse) {
                    if (((PutVoteResponse) object).getError() == 0) {
                        if (((PutVoteResponse) object).getMessage().equals("点赞成功")) {
                            likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_s));
                            vote++;
                            is_vote = 1;
                        } else {
                            likeNumIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.fabulous_n));
                            vote--;
                            is_vote = 0;
                        }
                        data.get(position).setIs_vote(is_vote);
                        contentSupports.setText(String.valueOf(vote));
                    }
                }
            }
        };
        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "onClick() enter");
                switch (view.getId()) {
                    case R.id.like_num_span:
/*                        LocalLog.d(TAG, "点赞");
                        PutVoteParam putVoteParam = new PutVoteParam();
                        putVoteParam.setDynamicid(dynamicid).setUserid(Presenter.getInstance(mContext).getId());
                        Presenter.getInstance(mContext).putVote(putVoteParam, innerCallBack);*/
                        break;
                    case R.id.scan_more:
                        LocalLog.d(TAG, "点击查看更多评价");
                        LocalLog.d(TAG, "dynamicId = " + dynamicid + ",userId = " + userid);
                        Intent intent = new Intent();
                        intent.putExtra(mContext.getPackageName() + "dynamicId", dynamicid);
                        intent.putExtra(mContext.getPackageName() + "userId", userid);
                        intent.putExtra(mContext.getPackageName() + "is_vote", is_vote);
                        intent.setClass(mContext, DynamicActivity.class);
                        fragment.startActivityForResult(intent, DYNAMIC_DETAIL);
                        break;
                    case R.id.dynamic_user_icon:
                    case R.id.dynamic_user_name:
                        LocalLog.d(TAG, "点击头像");
                        LocalLog.d(TAG, "dynamicId = " + dynamicid + ",userId = " + userid);
                        Intent intentPersonDetial = new Intent();
                        intentPersonDetial.putExtra("userid", userid);
                        intentPersonDetial.setClass(mContext, UserCenterActivity.class);
                        fragment.startActivityForResult(intentPersonDetial, DYNAMIC_DETAIL);
                        break;
                }
            }
        };
    }
}
