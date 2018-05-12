package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostDynamicContentParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostDynamicContentResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.DynamicDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReflashInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.paobuqianjin.pbq.step.view.emoji.EmotionViewPagerAdapter.numToHex8;

/**
 * Created by pbq on 2017/12/31.
 */

public class TopLevelContentAdapter extends RecyclerView.Adapter<TopLevelContentAdapter.TopLevelViewHolder> {
    private final static String TAG = TopLevelContentAdapter.class.getSimpleName();
    private final static int defaultCount = 7;
    List<?> mData;
    private Context context;
    DynamicDetailInterface dynamicDetailInterface;


    public TopLevelContentAdapter(Context context, List<?> data, DynamicDetailInterface dynamicDetailInterface) {
        super();
        this.context = context;
        mData = data;
        this.dynamicDetailInterface = dynamicDetailInterface;
    }

    public void notifyDataSetChanged(List<?> data) {
        this.mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public TopLevelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopLevelViewHolder(LayoutInflater.from(context).inflate(
                R.layout.content_first_support, parent, false));
    }

    @Override
    public void onBindViewHolder(TopLevelViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(TopLevelViewHolder holder, int position) {
        if (mData.get(position) instanceof DynamicCommentListResponse.DataBeanX.DataBean) {
            LocalLog.d(TAG, ((DynamicCommentListResponse.DataBeanX.DataBean) mData.get(position)).toString());
            Presenter.getInstance(context).getImage(holder.contentUserIcon, ((DynamicCommentListResponse.DataBeanX.DataBean) mData.get(position)).getAvatar());
            holder.dearName = ((DynamicCommentListResponse.DataBeanX.DataBean) mData.get(position)).getNickname();
            holder.userContentName.setText(((DynamicCommentListResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            long create_time = ((DynamicCommentListResponse.DataBeanX.DataBean) mData.get(position)).getCreate_time();
            String time_day_str = DateTimeUtil.formatDateTime(create_time * 1000, DateTimeUtil.DF_MM_DD_MM);
            String time_min_str = DateTimeUtil.formatDateTime(create_time * 1000, DateTimeUtil.DF_HH_MM);
            holder.timeContentA.setText(time_day_str);
            holder.timeContentB.setText(time_min_str);

            String content = ((DynamicCommentListResponse.DataBeanX.DataBean) mData.get(position)).getContent();
            LocalLog.d(TAG, "content = " + content);
            int[] emj = context.getResources().getIntArray(R.array.emjio_list);
            if (content != null) {
                for (int i = 0; i < emj.length; i++) {
                    content = content.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
                }
            }

            holder.userContentRanka.setText(content);

            if (((DynamicCommentListResponse.DataBeanX.DataBean) mData.get(position)).getChild() != null) {
                if (holder.middleContentAdapter == null) {
                    holder.childBeans.addAll(((DynamicCommentListResponse.DataBeanX.DataBean) mData.get(position)).getChild());
                    holder.middleContentAdapter = new MiddleContentAdapter(context, holder.childBeans, dynamicDetailInterface, holder.reflashInterface);

                }
                LocalLog.d(TAG, "有子评论" + ((DynamicCommentListResponse.DataBeanX.DataBean) mData.get(position)).getChild().size());
                holder.contendAllRecycler.setAdapter(holder.middleContentAdapter);
            }

            holder.parent_id = ((DynamicCommentListResponse.DataBeanX.DataBean) mData.get(position)).getId();
            holder.reply_userid = ((DynamicCommentListResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
            holder.dynamicid = ((DynamicCommentListResponse.DataBeanX.DataBean) mData.get(position)).getDynamicid();
            if (((DynamicCommentListResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }
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

    public class TopLevelViewHolder extends RecyclerView.ViewHolder {
        private LinearLayoutManager layoutManager;
        @Bind(R.id.content_user_icon)
        CircleImageView contentUserIcon;
        @Bind(R.id.user_content_name)
        TextView userContentName;
        @Bind(R.id.user_content_ranka)
        TextView userContentRanka;
        @Bind(R.id.time_content_a)
        TextView timeContentA;
        @Bind(R.id.time_content_b)
        TextView timeContentB;
        @Bind(R.id.contend_all_recycler)
        RecyclerView contendAllRecycler;
        @Bind(R.id.vip_flg)
        ImageView vipFlg;
        MiddleContentAdapter middleContentAdapter;
        ArrayList<DynamicCommentListResponse.DataBeanX.DataBean.ChildBean> childBeans = new ArrayList<>();
        ReflashInterface reflashInterface = new ReflashInterface() {
            @Override
            public void notifyReflash(Object object) {

                if (object instanceof PostDynamicContentResponse) {
                    LocalLog.d(TAG, "刷新一级评论");
                    PostDynamicContentResponse postDynamicContentResponse = (PostDynamicContentResponse) object;
                    LocalLog.d(TAG, postDynamicContentResponse.toString());
                    DynamicCommentListResponse.DataBeanX.DataBean.ChildBean dataBean = new DynamicCommentListResponse.DataBeanX.DataBean.ChildBean();
                    dataBean.setAvatar(postDynamicContentResponse.getData().getAvatar());
                    dataBean.setContent(postDynamicContentResponse.getData().getContent());
                    dataBean.setCreate_time(postDynamicContentResponse.getData().getCreate_time());
                    dataBean.setDynamicid(Integer.parseInt(postDynamicContentResponse.getData().getDynamicid()));
                    dataBean.setId(Integer.parseInt(postDynamicContentResponse.getData().getUserid()));
                    dataBean.setParent_id(Integer.parseInt(postDynamicContentResponse.getData().getParent_id()));
                    dataBean.setReply_userid(Integer.parseInt(postDynamicContentResponse.getData().getReply_userid()));
                    dataBean.setId(Integer.parseInt(postDynamicContentResponse.getData().getId()));
                    dataBean.setReply_nickname(postDynamicContentResponse.getData().getReply_nickname());
                    dataBean.setNickname(postDynamicContentResponse.getData().getNickname());
                    dataBean.setUserid(Integer.parseInt(postDynamicContentResponse.getData().getUserid()));
                    int size = childBeans.size();
                    childBeans.add(size, dataBean);
                    middleContentAdapter.notifyItemChanged(childBeans.size() - 1);
                } else if (object instanceof DynamicCommentListResponse.DataBeanX.DataBean.ChildBean) {
                    LocalLog.d(TAG, "刷新二级评论");
                    int size = childBeans.size();
                    childBeans.add(size, (DynamicCommentListResponse.DataBeanX.DataBean.ChildBean) object);
                    middleContentAdapter.notifyItemChanged(childBeans.size() - 1);
                }

            }
        };

        int parent_id = -1;
        int reply_userid = -1;
        int userid = -1;
        int dynamicid = -1;
        String dearName;

        public TopLevelViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            LocalLog.d(TAG, "");
            contentUserIcon = (CircleImageView) view.findViewById(R.id.content_user_icon);
            userContentName = (TextView) view.findViewById(R.id.user_content_name);
            userContentRanka = (TextView) view.findViewById(R.id.user_content_ranka);
            contendAllRecycler = (RecyclerView) view.findViewById(R.id.contend_all_recycler);
            timeContentA = (TextView) view.findViewById(R.id.time_content_a);
            timeContentB = (TextView) view.findViewById(R.id.time_content_b);
            layoutManager = new LinearLayoutManager(context);
            contendAllRecycler.setLayoutManager(layoutManager);
            userContentRanka.setOnClickListener(onClickListener);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.user_content_ranka:
                        LocalLog.d(TAG, "回复第一层 :parent_id = " + parent_id
                                + ",reply_userid = " + reply_userid + ",userid= " + userid + ", dynamicid = " + dynamicid);
                        if (dynamicDetailInterface != null) {
                            PostDynamicContentParam postDynamicContentParam = new PostDynamicContentParam()
                                    .setParent_id(parent_id)
                                    .setReply_userid(reply_userid).setDynamicid(dynamicid);
                            dynamicDetailInterface.postDynamicAction(postDynamicContentParam, dearName, reflashInterface);
                        }
                        break;
                }
            }
        };
    }
}
