package com.paobuqianjin.pbq.step.view.fragment.honor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleStepRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.DanCircleInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.FriendStepDanActivity;
import com.paobuqianjin.pbq.step.view.activity.SearchCircleActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.dan.HonorAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2018/1/10.
 */

public class CircleHonorIndexFragment extends BaseFragment implements DanCircleInterface {
    private final static String TAG = CircleHonorFragment.class.getSimpleName();
    @Bind(R.id.index_pager)
    ViewPager indexPager;
    List<View> mView = new ArrayList<>();
    @Bind(R.id.no_add_icon)
    ImageView noAddIcon;
    @Bind(R.id.add_circle_attention)
    TextView addCircleAttention;
    @Bind(R.id.empty_circle)
    RelativeLayout emptyCircle;
    @Bind(R.id.add_circle)
    Button addCircle;
    private final static int ADD_CIRCLE = 207;
    private ViewGroup container;
    private int pageIndex = 1, pageCount = 0;
    private int totalCircle = 0;
    private PagerAdapter adapter;
    private final static String ACTION_CIRCLE_HONOR = "com.paobuqianjin.pbq.ACTION_CIRCLE_HONOR";
    private final static int PAGESIZE = 200;

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
        Presenter.getInstance(getContext()).getAllMyCircle(pageIndex, PAGESIZE);
        adapter = new ImageViewPagerAdapter(getContext(), mView);
        indexPager.setAdapter(adapter);
        emptyCircle = (RelativeLayout) viewRoot.findViewById(R.id.empty_circle);
        addCircle = (Button) viewRoot.findViewById(R.id.add_circle);
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
            pageCount = myHotCircleResponse.getData().getPagenation().getTotalPage();
            totalCircle = myHotCircleResponse.getData().getPagenation().getTotalCount();
            if (getContext() == null) {
                return;
            }
            if (emptyCircle.getVisibility() == View.VISIBLE) {
                emptyCircle.setVisibility(View.GONE);
            }
            for (int i = 0; i < myHotCircleResponse.getData().getData().size(); i++) {
                Presenter.getInstance(getContext()).getUserCircleRank(myHotCircleResponse.getData().getData().get(i).getId());
            }
            if (pageIndex < pageCount) {
                //获取下一页数据
                pageIndex++;
                Presenter.getInstance(getContext()).getAllMyCircle(pageIndex, PAGESIZE);
            }

        } else if (myHotCircleResponse.getError() == 1) {
            if (pageIndex == 1) {
                if (emptyCircle == null) {
                    return;
                }
                emptyCircle.setVisibility(View.VISIBLE);
                addCircle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentSearch = new Intent();
                        intentSearch.setClass(getActivity(), SearchCircleActivity.class);
                        CircleHonorIndexFragment.this.startActivityForResult(intentSearch, ADD_CIRCLE);
                    }
                });

            }

        } else if (myHotCircleResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(CircleStepRankResponse circleStepRankResponse) {
        if (circleStepRankResponse.getError() == 0) {
            load(circleStepRankResponse);
        } else if (circleStepRankResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    private void upDateCircleDetail(TextView myName, ImageView imageView, TextView stepNum, int circleId) {
        LocalLog.d(TAG, "upDateCircleDetail() enter" + circleId);
        Presenter.getInstance(getContext()).getCircleDetailView(myName, imageView, stepNum, circleId);
    }

    private void load(CircleStepRankResponse circleStepRankResponse) {
        //TODO 所有异步更新都要判断界面UI是否存在
        if (getContext() == null) {
            return;
        }
        final View circle = LayoutInflater.from(getContext()).inflate(R.layout.circle_honor_fg, container, false);
        TextView rankHonor = (TextView) circle.findViewById(R.id.rank_honor);
        rankHonor.setText(String.valueOf(circleStepRankResponse.getData().getData().getRank()));
        TextView stepNum = (TextView) circle.findViewById(R.id.step_num);
        //stepNum.setText(String.valueOf(circleStepRankResponse.getData().getStep_number()));
        CircleImageView circleLogo = (CircleImageView) circle.findViewById(R.id.circle_logo);
        //Presenter.getInstance(getContext()).getImage(circleLogo, circleStepRankResponse.getData().getAvatar());
        RecyclerView rankRecyclerView = (RecyclerView) circle.findViewById(R.id.rank_recycler_view);
        TextView myName = (TextView) circle.findViewById(R.id.my_name);
        //myName.setText(circleStepRankResponse.getData().getNickname());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rankRecyclerView.setLayoutManager(layoutManager);
        rankRecyclerView.setAdapter(new HonorAdapter(getContext(), circleStepRankResponse.getData().getData().getCircle()));
        RelativeLayout relativeLayout = (RelativeLayout) circle.findViewById(R.id.more_span);
        TextView circleIndex = (TextView) circle.findViewById(R.id.circle_index);
        TextView timeToday = (TextView) circle.findViewById(R.id.times_today);
        timeToday.setText(DateTimeUtil.getLocalTime());
        relativeLayout.setTag(circleStepRankResponse.getData().getData().getCircleid());
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int circleId = (int) view.getTag();
                LocalLog.d(TAG, "排行榜详情 circleId = " + circleId);
                Intent intent = new Intent();
                intent.setAction(ACTION_CIRCLE_HONOR);
                intent.putExtra("circleid", circleId);
                intent.setClass(getContext(), FriendStepDanActivity.class);
                startActivity(intent);

            }
        });
        upDateCircleDetail(myName, circleLogo, stepNum, circleStepRankResponse.getData().getData().getCircleid());
        mView.add(circle);
        circleIndex.setText(String.valueOf(mView.size()) + "/" + totalCircle);
        if (mView.size() < totalCircle) {

        }
        LocalLog.d(TAG, "initView() enter");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == ADD_CIRCLE) {
                    LocalLog.d(TAG, "ADD_CIRCLE ");
                    if (data != null) {
                        int[] circleIds = data.getIntArrayExtra(getContext().getPackageName() + "circleids");
                        if (circleIds != null && circleIds.length > 0) {
                            LocalLog.d(TAG, "新加入的圈子");
                            Presenter.getInstance(getContext()).getAllMyCircle(pageIndex, PAGESIZE);
                        }
                    }
                }
                break;
        }
    }

}
