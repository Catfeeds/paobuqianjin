package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.MessageContentBundleData;
import com.paobuqianjin.pbq.step.data.bean.bundle.MessageLikeBundleData;
import com.paobuqianjin.pbq.step.view.base.adapter.MessageAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/23.
 */

public class UserContentFragment extends BaseBarStyleTextViewFragment {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.content_recycler)
    RecyclerView contentRecycler;
    MessageLikeBundleData messageLikeBundleData;
    MessageContentBundleData messageContentBundleData;
    LinearLayoutManager layoutManager;
    @Bind(R.id.content_scroll)
    BounceScrollView contentScroll;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    private String title;

    @Override
    protected String title() {
        return "评论";
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.content_fg;
    }


    public void setData(MessageLikeBundleData messageLikeBundleData) {
        this.messageLikeBundleData = messageLikeBundleData;
    }

    public void setData(MessageContentBundleData messageContentBundleData) {
        this.messageContentBundleData = messageContentBundleData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        setTitle(title);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        layoutManager = new LinearLayoutManager(getContext());
        contentRecycler = (RecyclerView) viewRoot.findViewById(R.id.content_recycler);
        contentRecycler.setLayoutManager(layoutManager);
        notFoundData = (TextView) viewRoot.findViewById(R.id.not_found_data);
        if (messageLikeBundleData == null && messageContentBundleData == null) {
            notFoundData.setVisibility(View.VISIBLE);
            return;
        }
        if (messageContentBundleData != null) {
            title = "评论";
            boolean isNoData = messageContentBundleData.getMessageContentBundleData().size() < 1;
            if (isNoData) {
                notFoundData.setText("您暂时还没有收到评论！");
                notFoundData.setVisibility(View.VISIBLE);
            }else{
                contentRecycler.setAdapter(new MessageAdapter(getContext(), messageContentBundleData.getMessageContentBundleData()));
            }
        }
        if (messageLikeBundleData != null) {
            title = "点赞";
            boolean isNoData = messageLikeBundleData.getMessageLikeBundleData().size() < 1;
            if (isNoData) {
                notFoundData.setText("您暂时还没有被点赞！");
                notFoundData.setVisibility(View.VISIBLE);
            }else{
                contentRecycler.setAdapter(new MessageAdapter(getContext(), messageLikeBundleData.getMessageLikeBundleData()));
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
