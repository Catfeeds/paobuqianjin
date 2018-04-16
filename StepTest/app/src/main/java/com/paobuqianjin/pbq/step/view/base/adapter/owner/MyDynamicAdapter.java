package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.LoginOutParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutVoteParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DeleteDynamicResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicAllIndexResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicPersonResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PutVoteResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.DynamicActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.fragment.owner.MyDynamicFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.paobuqianjin.pbq.step.view.emoji.EmotionViewPagerAdapter.numToHex8;
import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * Created by pbq on 2018/1/17.
 */

public class MyDynamicAdapter extends RecyclerView.Adapter<MyDynamicAdapter.MyDynamicViewHolder> {
    private final static String TAG = MyDynamicAdapter.class.getSimpleName();
    private final static int defaultCount = 3;
    private Activity mContext;
    private List<?> mData;
    MyDynamicFragment.PopBigImageInterface popBigImageInterface;
    private View popCircleOpBar;
    private PopupWindow popupOpWindow;
    private TranslateAnimation animationCircleType;
    TextView cancelText;
    TextView confirmText;

    public MyDynamicAdapter(Activity context, List<DynamicPersonResponse.DataBeanX.DataBean> data, MyDynamicFragment.PopBigImageInterface popBigImageInterface) {
        mContext = context;
        mData = data;
        this.popBigImageInterface = popBigImageInterface;
    }

    public void notifyDataSetChanged(List<DynamicPersonResponse.DataBeanX.DataBean> data) {
        this.mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public MyDynamicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyDynamicViewHolder(LayoutInflater.from(mContext).inflate(R.layout.my_dynamic_one_pic_style, parent, false));
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
    public void onBindViewHolder(MyDynamicViewHolder holder, int position) {
        upDataListItem(holder, position);
    }

    private void upDataListItem(MyDynamicViewHolder holder, int position) {
        if (mData.get(position) instanceof DynamicPersonResponse.DataBeanX.DataBean) {
            holder.dots = new ArrayList<>();
            holder.Mview = new ArrayList<>();
            LocalLog.d(TAG, "upDataListItem() enter  data " + ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).toString());
            int[] emj = mContext.getResources().getIntArray(R.array.emjio_list);
            Presenter.getInstance(mContext).getImage(holder.dynamicUserIcon, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getAvatar());
            holder.dynamicUserName.setText(((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            long create_time = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getCreate_time();
            //服务器保存到秒级别，本地处理为毫秒级别
            LocalLog.d(TAG, "create_time = " + DateTimeUtil.formatDateTime(create_time * 1000));
            String create_timeStr = DateTimeUtil.formatFriendly(new Date(create_time * 1000));
            holder.createTime.setText(create_timeStr);
            int imageSize = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().size();
            holder.dynamicId = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getId();
            holder.userid = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
            holder.is_vote = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getIs_vote();
            holder.vote = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getVote();
            holder.position = position;
            LocalLog.d(TAG, "imageSize = " + imageSize);
            if (((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getDynamic().equals("")) {
                LocalLog.d(TAG, "无内容");
                holder.dynamicContentText.setVisibility(View.GONE);
            } else {
                String content = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getDynamic();
                LocalLog.d(TAG, "content = " + content);
                if (content != null) {
                    for (int i = 0; i < emj.length; i++) {
                        content = content.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                    }
                }

                holder.dynamicContentText.setText(content);
            }
            int likes = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getVote();
            int content = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getComment();
            LocalLog.d(TAG, "点赞数 = " + likes + ",评论数 = " + content);
            if (likes > 0) {
                holder.likeNumIcon.setImageResource(R.drawable.fabulous_s);
                holder.contentSupports.setText(String.valueOf(likes));
            } else {
                holder.contentSupports.setText(String.valueOf(0));
            }

            if (content > 0) {
                holder.firstContent.setVisibility(View.VISIBLE);
                holder.contentNumbers.setText(String.valueOf(content));
                String firstContentDes = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getOne_comment().getNickname() + ":";
                String firstContentText = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getOne_comment().getContent();
                if (firstContentText != null) {
                    for (int i = 0; i < emj.length; i++) {
                        firstContentText = firstContentText.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                    }
                }

                LocalLog.d(TAG, "firstContentText = " + firstContentText);
                holder.dynamicContentText.setText(firstContentText);
                SpannableStringBuilder style = new SpannableStringBuilder(firstContentDes + firstContentText);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), 0, firstContentDes.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff161727")), firstContentDes.length(), (firstContentDes + firstContentText).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.firstContent.setText(style);
            } else {
                LocalLog.d(TAG, "没有任何评论");
                holder.contentNumbers.setText(String.valueOf(0));
                holder.scanMore.setVisibility(View.GONE);
            }

            if (imageSize <= 0) {
                LocalLog.d(TAG, "动态没有图片");
                holder.picViewpager.setVisibility(View.GONE);
            } else if (imageSize == 1) {
                if (((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0).equals("")) {
                    LocalLog.d(TAG, "动态没有图片");
                    holder.picViewpager.setVisibility(View.GONE);
                } else {
                    holder.imageView0 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);

                    holder.dots.add(holder.dot1);
                    holder.Mview.add(holder.imageView0);
                    ImageView imageView0 = (ImageView) holder.imageView0.findViewById(R.id.dynamic_pic);

                    Presenter.getInstance(mContext).getImage(imageView0, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                    showBigImage(imageView0, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                }
            } else if (imageSize == 2) {

                holder.imageView0 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);
                holder.imageView1 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);

                holder.dots.add(holder.dot1);
                holder.dots.add(holder.dot2);
                holder.dot1.setVisibility(View.VISIBLE);
                holder.dot2.setVisibility(View.VISIBLE);

                holder.Mview.add(holder.imageView0);
                holder.Mview.add(holder.imageView1);
                ImageView imageView0 = (ImageView) holder.imageView0.findViewById(R.id.dynamic_pic);
                ImageView imageView1 = (ImageView) holder.imageView1.findViewById(R.id.dynamic_pic);
                holder.dots.get(0).setBackgroundResource(R.drawable.image_viewpager_dot_selected);
                Presenter.getInstance(mContext).getImage(imageView0, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                Presenter.getInstance(mContext).getImage(imageView1, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(1));

                showBigImage(imageView0, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                showBigImage(imageView1, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(1));
            } else if (imageSize == 3) {

                holder.imageView0 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);
                holder.imageView1 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);
                holder.imageView2 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);

                holder.dots.add(holder.dot1);
                holder.dots.add(holder.dot2);
                holder.dots.add(holder.dot3);

                holder.dot1.setVisibility(View.VISIBLE);
                holder.dot2.setVisibility(View.VISIBLE);
                holder.dot3.setVisibility(View.VISIBLE);

                holder.Mview.add(holder.imageView0);
                holder.Mview.add(holder.imageView1);
                holder.Mview.add(holder.imageView2);

                ImageView imageView0 = (ImageView) holder.imageView0.findViewById(R.id.dynamic_pic);
                ImageView imageView1 = (ImageView) holder.imageView1.findViewById(R.id.dynamic_pic);
                ImageView imageView2 = (ImageView) holder.imageView2.findViewById(R.id.dynamic_pic);
                holder.dots.get(0).setBackgroundResource(R.drawable.image_viewpager_dot_selected);
                Presenter.getInstance(mContext).getImage(imageView0, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                Presenter.getInstance(mContext).getImage(imageView1, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(1));
                Presenter.getInstance(mContext).getImage(imageView2, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(2));

                showBigImage(imageView0, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                showBigImage(imageView1, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(1));
                showBigImage(imageView2, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(2));


            } else if (imageSize >= 4) {

                holder.imageView0 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);
                holder.imageView1 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);
                holder.imageView2 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);
                holder.imageView3 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);

                holder.dots.add(holder.dot1);
                holder.dots.add(holder.dot2);
                holder.dots.add(holder.dot3);
                holder.dots.add(holder.dot4);

                holder.dot1.setVisibility(View.VISIBLE);
                holder.dot2.setVisibility(View.VISIBLE);
                holder.dot3.setVisibility(View.VISIBLE);
                holder.dot4.setVisibility(View.VISIBLE);

                ImageView imageView0 = (ImageView) holder.imageView0.findViewById(R.id.dynamic_pic);
                ImageView imageView1 = (ImageView) holder.imageView1.findViewById(R.id.dynamic_pic);
                ImageView imageView2 = (ImageView) holder.imageView2.findViewById(R.id.dynamic_pic);
                ImageView imageView3 = (ImageView) holder.imageView3.findViewById(R.id.dynamic_pic);

                holder.Mview.add(holder.imageView0);
                holder.Mview.add(holder.imageView1);
                holder.Mview.add(holder.imageView2);
                holder.Mview.add(holder.imageView3);
                holder.dots.get(0).setBackgroundResource(R.drawable.image_viewpager_dot_selected);

                Presenter.getInstance(mContext).getImage(imageView0, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                Presenter.getInstance(mContext).getImage(imageView1, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(1));
                Presenter.getInstance(mContext).getImage(imageView2, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(2));
                Presenter.getInstance(mContext).getImage(imageView3, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(3));

                showBigImage(imageView0, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                showBigImage(imageView1, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(1));
                showBigImage(imageView2, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(2));
                showBigImage(imageView3, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(3));
            } else {
                LocalLog.e(TAG, "图片数量超过5");
            }
            holder.adapter = new ImageViewPagerAdapter(mContext, holder.Mview);
            holder.imageViewpager.setAdapter(holder.adapter);
            if (Presenter.getInstance(mContext).getId() == ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getUserid()) {

            }
        }
    }

    private void showBigImage(ImageView imageView, final String url) {
        /*LocalLog.d(TAG, "URL = " + url);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popBigImageInterface != null) {
                    LocalLog.d(TAG, "url = " + url);
                    popBigImageInterface.popImageView(url);
                }

            }
        });*/
    }

    @Override
    public void onBindViewHolder(MyDynamicViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }


    private void popQuitConfirm(final int dynamicId, final InnerCallBack innerCallBack) {
        LocalLog.d(TAG, "popQuitConfirm() enter 退圈确认");
        popCircleOpBar = View.inflate(mContext, R.layout.quit_circle_confirm, null);
        TextView viewTitle = (TextView) popCircleOpBar.findViewById(R.id.quit_title);
        viewTitle.setText("是否删除该动态");
        popupOpWindow = new PopupWindow(popCircleOpBar, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupOpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupOpWindow = null;
            }
        });

        cancelText = (TextView) popCircleOpBar.findViewById(R.id.cancel_quit_text);
        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "取消退圈动作");
                popupOpWindow.dismiss();
            }
        });
        confirmText = (TextView) popCircleOpBar.findViewById(R.id.confirm_quit_text);
        confirmText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Presenter.getInstance(mContext).deleteDynamic(dynamicId, innerCallBack);

            }
        });


        popupOpWindow.setFocusable(true);
        popupOpWindow.setOutsideTouchable(true);
        popupOpWindow.setBackgroundDrawable(new BitmapDrawable());

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new

                AccelerateInterpolator());
        animationCircleType.setDuration(200);

        popupOpWindow.showAtLocation(mContext.findViewById(R.id.my_dynamic_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popCircleOpBar.startAnimation(animationCircleType);
    }

    public class MyDynamicViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dynamic_user_icon)
        CircleImageView dynamicUserIcon;
        @Bind(R.id.dynamic_user_name)
        TextView dynamicUserName;
        @Bind(R.id.dynamic_owner_rel)
        RelativeLayout dynamicOwnerRel;
        @Bind(R.id.dynamic_content_text)
        TextView dynamicContentText;
        @Bind(R.id.pic_content_rel)
        RelativeLayout picContentRel;
        @Bind(R.id.image_viewpager)
        ViewPager imageViewpager;
        @Bind(R.id.dot_1)
        View dot1;
        @Bind(R.id.dot_2)
        View dot2;
        @Bind(R.id.dot_3)
        View dot3;
        @Bind(R.id.dot_4)
        View dot4;
        @Bind(R.id.dot_line)
        LinearLayout dotLine;
        @Bind(R.id.pic_viewpager)
        RelativeLayout picViewpager;
        @Bind(R.id.content_numbers)
        TextView contentNumbers;
        @Bind(R.id.content_number_icon)
        ImageView contentNumberIcon;
        @Bind(R.id.content_supports)
        TextView contentSupports;
        @Bind(R.id.like_num_icon)
        ImageView likeNumIcon;
        @Bind(R.id.location_support_rel)
        RelativeLayout locationSupportRel;
        @Bind(R.id.line_content)
        ImageView lineContent;
        @Bind(R.id.first_content)
        TextView firstContent;
        @Bind(R.id.scan_more)
        TextView scanMore;
        @Bind(R.id.content_des)
        RelativeLayout contentDes;
        @Bind(R.id.line_content_list)
        ImageView lineContentList;
        @Bind(R.id.delete_dynamic)
        ImageView deleteDynamic;
        @Bind(R.id.create_time)
        TextView createTime;

        List<View> Mview;
        List<View> dots;
        int oldPosition;
        int currentItem;
        View imageView0, imageView1, imageView2, imageView3;
        ImageViewPagerAdapter adapter;
        int dynamicId = -1;
        int userid = -1;
        int is_vote = 0;
        int position = 0;
        int vote = 0;

        public MyDynamicViewHolder(View view) {
            super(view);
            initView(view);
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

                        ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).setIs_vote(is_vote);
                        contentSupports.setText(String.valueOf(vote));
                    }
                } else if (object instanceof DeleteDynamicResponse) {
                    if (((DeleteDynamicResponse) object).getError() == 0) {
                        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                        mData.remove(MyDynamicViewHolder.this.getAdapterPosition());
                        notifyItemRemoved(MyDynamicViewHolder.this.getAdapterPosition());
                    }
                    popupOpWindow.dismiss();
                }
            }
        };


        private void initView(View viewRoot) {

            dynamicUserIcon = (CircleImageView) viewRoot.findViewById(R.id.dynamic_user_icon);
            dynamicUserName = (TextView) viewRoot.findViewById(R.id.dynamic_user_name);
            dynamicContentText = (TextView) viewRoot.findViewById(R.id.dynamic_content_text);
            contentNumbers = (TextView) viewRoot.findViewById(R.id.content_numbers);
            contentNumberIcon = (ImageView) viewRoot.findViewById(R.id.content_number_icon);
            contentSupports = (TextView) viewRoot.findViewById(R.id.content_supports);
            likeNumIcon = (ImageView) viewRoot.findViewById(R.id.like_num_icon);
            firstContent = (TextView) viewRoot.findViewById(R.id.first_content);
            likeNumIcon.setOnClickListener(onClickListener);
            createTime = (TextView) viewRoot.findViewById(R.id.create_time);
            deleteDynamic = (ImageView) viewRoot.findViewById(R.id.delete_dynamic);

            picViewpager = (RelativeLayout) viewRoot.findViewById(R.id.pic_viewpager);

            dot1 = viewRoot.findViewById(R.id.dot_1);
            dot2 = viewRoot.findViewById(R.id.dot_2);
            dot3 = viewRoot.findViewById(R.id.dot_3);
            dot4 = viewRoot.findViewById(R.id.dot_4);
/*            dots.add(dot1);
            dots.add(dot2);
            dots.add(dot3);
            dots.add(dot4);*/

/*            Mview.add(imageView0);
            Mview.add(imageView1);
            Mview.add(imageView2);
            Mview.add(imageView3);*/
            imageViewpager = (ViewPager) viewRoot.findViewById(R.id.image_viewpager);
            imageViewpager.addOnPageChangeListener(onPageChangeListener);
            //dots.get(0).setBackgroundResource(R.drawable.image_viewpager_dot_selected);

            scanMore = (TextView) viewRoot.findViewById(R.id.scan_more);
            scanMore.setOnClickListener(onClickListener);
            deleteDynamic = (ImageView) viewRoot.findViewById(R.id.delete_dynamic);
            deleteDynamic.setOnClickListener(onClickListener);


            imageViewpager.setOnClickListener(onClickListener);
            dynamicContentText.setOnClickListener(onClickListener);

        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.scan_more:
                        LocalLog.d(TAG, "点击查看更多评价");
                        LocalLog.d(TAG, "dynamicId = " + dynamicId + ",userId = " + userid);
                        Intent intent = new Intent();
                        intent.putExtra(mContext.getPackageName() + "dynamicId", dynamicId);
                        intent.putExtra(mContext.getPackageName() + "userId", userid);
                        intent.putExtra(mContext.getPackageName() + "is_vote", is_vote);
                        intent.setClass(mContext, DynamicActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case R.id.delete_dynamic:
                        LocalLog.d(TAG, "删除动态");
                        //
                        popQuitConfirm(dynamicId, innerCallBack);
                        break;
                    case R.id.like_num_icon:
                        LocalLog.d(TAG, "点赞");
                        PutVoteParam putVoteParam = new PutVoteParam();
                        putVoteParam.setDynamicid(dynamicId).setUserid(Presenter.getInstance(mContext).getId());
                        Presenter.getInstance(mContext).putVote(putVoteParam, innerCallBack);
                        break;
                    default:
                        break;
                }
            }
        };

        private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LocalLog.d(TAG, " ");
                dots.get(oldPosition).setBackgroundResource(R.drawable.image_viewpager_dot_unselected);
                dots.get(position).setBackgroundResource(R.drawable.image_viewpager_dot_selected);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }
}
