package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.content.Intent;
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
import com.paobuqianjin.pbq.step.data.bean.bundle.RechargeRankBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UiStepAndLoveRankInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.LoveRankActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.RankAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.RechargeRankSimpleAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by pbq on 2017/12/29.
 */

public class CircleDetailNoAdminMainFragment extends BaseBarImageViewFragment {
    private final static String TAG = CircleDetailNoAdminMainFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    ImageView barTvRight;
    @Bind(R.id.circle_obj_des)
    TextView circleObjDes;
    @Bind(R.id.circle_message)
    RelativeLayout circleMessage;
    @Bind(R.id.love_money_list)
    TextView loveMoneyList;
    @Bind(R.id.image_button)
    RelativeLayout imageButton;
    @Bind(R.id.love_money_list_rel)
    RelativeLayout loveMoneyListRel;
    @Bind(R.id.rank_recycler)
    RecyclerView rankRecycler;
    @Bind(R.id.rank_money)
    RelativeLayout rankMoney;
    @Bind(R.id.line_rank_step)
    ImageView lineRankStep;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.member_num_des)
    TextView memberNumDes;
    @Bind(R.id.step_rank)
    RelativeLayout stepRank;
    @Bind(R.id.step_recycler)
    RecyclerView stepRecycler;
    private Context mContext;
    private int circleId;
    private int circleNum;
    private LinearLayoutManager reChargeLayoutManager;
    private LinearLayoutManager stepLayoutManager;
    private RechargeRankBundleData rechargeRankBundleData;
    private boolean isMainAdmin;
    private String target;
    private CircleDetailResponse circleDetailResponse = null;
    private final static String CIRCLE_ID = "id";
    private final static String CIRCLE_NAME = "name";
    private final static String CIRCLE_LOGO = "logo";
    private final static String QRCODE_ACTION = "android.intent.action.QRCODE";


    public int getCircleId() {
        return circleId;
    }

/*    public void setCircleId(Context context, int circleId, int circleNum, String targetStr, boolean isMainAdmin) {
        this.circleId = circleId;
        this.circleNum = circleNum;
        this.isMainAdmin = isMainAdmin;
        target = targetStr;

    }*/

    public void setCircleDetail(CircleDetailResponse circleDetailResponse, int circleNum) {
        this.circleDetailResponse = circleDetailResponse;
        if (circleDetailResponse != null) {
            circleId = circleDetailResponse.getData().getId();
            this.circleNum = circleNum;
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        Presenter.getInstance(getContext()).attachUiInterface(uiStepAndLoveRankInterface);
        Presenter.getInstance(getContext()).getCircleRechargeRand(circleId);
        Presenter.getInstance(getContext()).getCircleStepRank(circleId);
    }


    @Override
    protected String title() {
        return "圈子";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_detail_fg_admin_main;
    }

    @Override
    public Object right() {
        return getDrawableResource(R.drawable.exit);

    }

    private ToolBarListener toolBarListener = new ToolBarListener() {
        @Override
        public void clickLeft() {

        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "普通用户点击");
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        circleObjDes = (TextView) viewRoot.findViewById(R.id.circle_obj_des);
        rankRecycler = (RecyclerView) viewRoot.findViewById(R.id.rank_recycler);
        stepRecycler = (RecyclerView) viewRoot.findViewById(R.id.step_recycler);
        reChargeLayoutManager = new LinearLayoutManager(getContext());
        reChargeLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        stepLayoutManager = new LinearLayoutManager(getContext());
        rankRecycler.setLayoutManager(reChargeLayoutManager);
        rankRecycler.addItemDecoration(new RechargeRankSimpleAdapter.SpaceItemDecoration(30));
        stepRecycler.setLayoutManager(stepLayoutManager);

        memberNumDes = (TextView) viewRoot.findViewById(R.id.member_num_des);
        String sAgeFormat = mContext.getResources().getString(R.string.member_total);
        String sFinalMember = String.format(sAgeFormat, circleNum);
        memberNumDes.setText(sFinalMember);
        imageButton = (RelativeLayout) viewRoot.findViewById(R.id.image_button);
        imageButton.setOnClickListener(onClickListener);
        setToolBarListener(toolBarListener);

        if (circleDetailResponse != null) {
            String targetFormat = getResources().getString(R.string.target_step);
            target = String.format(targetFormat, circleDetailResponse.getData().getTarget());
            circleObjDes.setText(target);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(uiStepAndLoveRankInterface);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.image_button:
                    LocalLog.d(TAG, "进入爱心榜单行情");
                    Intent intent = new Intent();
                    intent.putExtra(getActivity().getPackageName() + "circle_detail", rechargeRankBundleData);
                    intent.setClass(getContext(), LoveRankActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    private UiStepAndLoveRankInterface uiStepAndLoveRankInterface = new UiStepAndLoveRankInterface() {
 /*       @Override
        public void response(CircleDetailResponse circleDetailResponse) {
            LocalLog.d(TAG, "CircleDetailResponse() ");
            String sAgeFormat = mContext.getResources().getString(R.string.target_step);
            String sFinalMember = String.format(sAgeFormat, circleDetailResponse.getData().getTarget());
            circleObjDes.setText(sFinalMember);
            userIdCircleAdminMain = circleDetailResponse.getData().getUserid();
            if (Presenter.getInstance(getContext()).getId() == userIdCircleAdminMain) {
                LocalLog.d(TAG, "当前用户为圈创建者");

            } else {
                LocalLog.d(TAG, "当前用户普通");
            }
        }*/

        @Override
        public void response(ReChargeRankResponse reChargeRankResponse) {
            LocalLog.d(TAG, "ReChargeRankResponse() ");
            rankRecycler.setAdapter(new RechargeRankSimpleAdapter(getContext(), reChargeRankResponse.getData().getData()));
            rechargeRankBundleData = new RechargeRankBundleData(
                    (ArrayList<ReChargeRankResponse.DataBeanX.DataBean>)
                            reChargeRankResponse.getData().getData());
        }

        @Override
        public void response(StepRankResponse stepRankResponse) {
            LocalLog.d(TAG, "StepRankResponse() ");
            stepRecycler.setAdapter(new RankAdapter(getContext(), stepRankResponse.getData().getData()));
        }
    };
}
