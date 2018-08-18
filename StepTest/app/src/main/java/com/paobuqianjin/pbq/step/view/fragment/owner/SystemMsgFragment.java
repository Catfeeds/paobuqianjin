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
import com.paobuqianjin.pbq.step.data.bean.bundle.MessageSystemBundleData;
import com.paobuqianjin.pbq.step.view.base.adapter.MessageAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/23.
 */

public class SystemMsgFragment extends BaseBarStyleTextViewFragment {
    MessageSystemBundleData messageSystemBundleData;
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
    @Bind(R.id.content_scroll)
    BounceScrollView contentScroll;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    LinearLayoutManager layoutManager;
    @Override
    protected int getLayoutResId() {
        return R.layout.system_msg_fg;
    }

    @Override
    protected String title() {
        return "系统消息";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void setData(MessageSystemBundleData messageSystemBundleData) {
        this.messageSystemBundleData = messageSystemBundleData;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initView(View viewRoot) {
        layoutManager = new LinearLayoutManager(getContext());
        contentRecycler = (RecyclerView) viewRoot.findViewById(R.id.content_recycler);
        contentRecycler.setLayoutManager(layoutManager);
        notFoundData = (TextView) viewRoot.findViewById(R.id.not_found_data);
        if (messageSystemBundleData == null) {
            notFoundData.setVisibility(View.VISIBLE);
            return;
        }
        if (messageSystemBundleData != null) {
            boolean isNoData = messageSystemBundleData.getMessageSystemData().size() < 1;
            if (isNoData) {
                notFoundData.setText("您暂时还没有收到评论！");
                notFoundData.setVisibility(View.VISIBLE);
            }else{
                contentRecycler.setAdapter(new MessageAdapter(getContext(), messageSystemBundleData.getMessageSystemData()));
            }
        }

    }
}
