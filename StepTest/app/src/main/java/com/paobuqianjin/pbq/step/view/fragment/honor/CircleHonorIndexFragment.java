package com.paobuqianjin.pbq.step.view.fragment.honor;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleStepRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.DanCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.dan.HonorAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/10.
 */

public class CircleHonorIndexFragment extends BaseFragment implements DanCircleInterface {
    private final static String TAG = CircleHonorFragment.class.getSimpleName();
    @Bind(R.id.index_pager)
    ViewPager indexPager;
    List<View> mView = new ArrayList<>();

    private ViewGroup container;
    private int pageIndex = 1, pageCount = 0;
    private int totalCircle = 0;
    private PagerAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_honor_index_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        this.container = container;
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        indexPager = (ViewPager) viewRoot.findViewById(R.id.index_pager);
        Presenter.getInstance(getContext()).getAllMyCircle(pageIndex, 10);
        adapter = new ImageViewPagerAdapter(getContext(), mView);
        indexPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(MyHotCircleResponse myHotCircleResponse) {
        if (myHotCircleResponse.getError() == 0) {
            pageCount = myHotCircleResponse.getData().getPagenation().getPageSize();
            totalCircle = myHotCircleResponse.getData().getPagenation().getTotalCount();
            for (int i = 0; i < myHotCircleResponse.getData().getData().size(); i++) {
                Presenter.getInstance(getContext()).getUserCircleRank(myHotCircleResponse.getData().getData().get(i).getId());
            }
            if (pageIndex <= pageCount) {
                //获取下一页数据
                pageIndex++;
                Presenter.getInstance(getContext()).getAllMyCircle(pageIndex, 10);

            }

        }
    }

    @Override
    public void response(CircleStepRankResponse circleStepRankResponse) {
        if (circleStepRankResponse.getError() == 0) {
            load(circleStepRankResponse);
        }
    }

    private void load(CircleStepRankResponse circleStepRankResponse) {
        final View circle = LayoutInflater.from(getContext()).inflate(R.layout.circle_honor_fg, container, false);
        TextView rankHonor = (TextView) circle.findViewById(R.id.rank_honor);
        rankHonor.setText(String.valueOf(circleStepRankResponse.getData().getRank()));
        TextView stepNum = (TextView) circle.findViewById(R.id.step_num);
        stepNum.setText(String.valueOf(circleStepRankResponse.getData().getStep_number()));
        CircleImageView circleLogo = (CircleImageView) circle.findViewById(R.id.circle_logo);
        Presenter.getInstance(getContext()).getImage(circleLogo, circleStepRankResponse.getData().getCircle().get(0).getAvatar());
        RecyclerView rankRecyclerView = (RecyclerView) circle.findViewById(R.id.rank_recycler_view);
        TextView no1 = (TextView) circle.findViewById(R.id.no_1_name);
        no1.setText(circleStepRankResponse.getData().getCircle().get(0).getNickname());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rankRecyclerView.setLayoutManager(layoutManager);
        rankRecyclerView.setAdapter(new HonorAdapter(getContext(), circleStepRankResponse.getData().getCircle()));
        RelativeLayout relativeLayout = (RelativeLayout) circle.findViewById(R.id.more_span);
        TextView circleIndex = (TextView) circle.findViewById(R.id.circle_index);
        relativeLayout.setTag(circleStepRankResponse.getData().getCircleid());
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int circleId = (int) view.getTag();
                LocalLog.d(TAG, "排行榜详情 circleId = " + circleId);

            }
        });
        mView.add(circle);
        circleIndex.setText(String.valueOf(mView.size()) + "/" + totalCircle);
        LocalLog.d(TAG, "initView() enter");
        adapter.notifyDataSetChanged();
    }
}
