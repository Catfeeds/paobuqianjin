package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostDynamicContentParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentListResponse;
import com.paobuqianjin.pbq.step.presenter.im.DynamicDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReflashInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;

import java.util.List;

import butterknife.Bind;

import static com.paobuqianjin.pbq.step.view.emoji.EmotionViewPagerAdapter.numToHex8;

/**
 * Created by pbq on 2017/12/31.
 */

public class MiddleContentAdapter extends RecyclerView.Adapter<MiddleContentAdapter.MiddleContentViewHolder> {
    private final static String TAG = MiddleContentAdapter.class.getSimpleName();
    private final static int defaultCount = 7;

    private Context context;
    List<?> mData;
    DynamicDetailInterface dynamicDetailInterface;

    public MiddleContentAdapter(Context context, List<?> data, DynamicDetailInterface dynamicDetailInterface) {
        super();
        this.context = context;
        mData = data;
        this.dynamicDetailInterface = dynamicDetailInterface;
    }

    @Override
    public MiddleContentAdapter.MiddleContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MiddleContentViewHolder(LayoutInflater.from(context).inflate(
                R.layout.content_item, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(MiddleContentAdapter.MiddleContentViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(MiddleContentAdapter.MiddleContentViewHolder holder, int position) {
        if (mData.get(position) instanceof DynamicCommentListResponse.DataBeanX.DataBean.ChildBean) {
            LocalLog.d(TAG, ((DynamicCommentListResponse.DataBeanX.DataBean.ChildBean) mData.get(position)).toString());
            holder.dearName = ((DynamicCommentListResponse.DataBeanX.DataBean.ChildBean) mData.get(position)).getNickname();
            String nameA = holder.dearName;
            String nameB = ((DynamicCommentListResponse.DataBeanX.DataBean.ChildBean) mData.get(position)).getReply_nickname();
            String reply = "回复";
            String content = ((DynamicCommentListResponse.DataBeanX.DataBean.ChildBean) mData.get(position)).getContent();
            int[] emj = context.getResources().getIntArray(R.array.emjio_list);
            for (int i = 0; i < emj.length; i++) {
                content = content.replace("[0x" + numToHex8(emj[i]) + "]", Utils.getEmojiStringByUnicode(emj[i]));
            }
            SpannableStringBuilder style = new SpannableStringBuilder(nameA + reply + nameB + ":" + content);
            style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), 0, nameA.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff161727")), nameA.length(), (nameA + reply).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), (nameA + reply).length(), (nameA + reply + nameB).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff161727")), (nameA + reply + nameB).length(), (nameA + reply + nameB + ":" + content).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.itemContent.setText(style);

            holder.parent_id = ((DynamicCommentListResponse.DataBeanX.DataBean.ChildBean) mData.get(position)).getId();
            holder.reply_userid = ((DynamicCommentListResponse.DataBeanX.DataBean.ChildBean) mData.get(position)).getUserid();
            holder.dynamicid = ((DynamicCommentListResponse.DataBeanX.DataBean.ChildBean) mData.get(position)).getDynamicid();
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

    public class MiddleContentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_content)
        TextView itemContent;
        int parent_id = -1;
        int reply_userid = -1;
        int userid = -1;
        int dynamicid = -1;
        String dearName;
        ReflashInterface reflashInterface = new ReflashInterface() {
            @Override
            public void notifyReflash() {
                LocalLog.d(TAG, "刷新二级评论");
            }
        };

        public MiddleContentViewHolder(View view) {
            super(view);
            initView(view);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.item_content:
                        LocalLog.d(TAG, "回复第二层 :parent_id = " + parent_id
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

        private void initView(View view) {
            itemContent = (TextView) view.findViewById(R.id.item_content);
            itemContent.setOnClickListener(onClickListener);
        }
    }
}
