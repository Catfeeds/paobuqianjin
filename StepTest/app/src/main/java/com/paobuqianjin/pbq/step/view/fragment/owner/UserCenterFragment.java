package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/6.
 */

public class UserCenterFragment extends BaseBarStyleTextViewFragment {
    @Bind(R.id.user_head_ico)
    CircleImageView userHeadIco;
    @Bind(R.id.bnt_like)
    Button bntLike;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.sex_icon)
    ImageView sexIcon;
    @Bind(R.id.mu_biao)
    TextView muBiao;
    @Bind(R.id.user_simple_info)
    RelativeLayout userSimpleInfo;
    @Bind(R.id.location_city)
    TextView locationCity;
    @Bind(R.id.line_center)
    ImageView lineCenter;
    @Bind(R.id.dynamic_record)
    TextView dynamicRecord;
    @Bind(R.id.line_dynamic_line)
    ImageView lineDynamicLine;
    @Bind(R.id.dynamic_record_recycler)
    RecyclerView dynamicRecordRecycler;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;

    @Override
    protected String title() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.user_center_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
