package com.paobuqianjin.pbq.step.view.fragment.honor;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.im.DanCircleInterface;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/9.
 */

public class CircleHonorFragment extends BaseFragment{
    @Bind(R.id.circle_bg)
    ImageView circleBg;
    @Bind(R.id.bg)
    FrameLayout bg;
    @Bind(R.id.rank_circle)
    TextView rankCircle;
    @Bind(R.id.left_img)
    ImageView leftImg;
    @Bind(R.id.rank_honor)
    TextView rankHonor;
    @Bind(R.id.rank_des)
    TextView rankDes;
    @Bind(R.id.step_num)
    TextView stepNum;
    @Bind(R.id.step_des)
    TextView stepDes;
    @Bind(R.id.circle_logo)
    CircleImageView circleLogo;
    @Bind(R.id.rank_data_span)
    RelativeLayout rankDataSpan;
    @Bind(R.id.rank_recycler_view)
    RecyclerView rankRecyclerView;
    @Bind(R.id.rank_master_span)
    RelativeLayout rankMasterSpan;
    @Bind(R.id.rank_detail_des)
    TextView rankDetailDes;

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_honor_fg;
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
