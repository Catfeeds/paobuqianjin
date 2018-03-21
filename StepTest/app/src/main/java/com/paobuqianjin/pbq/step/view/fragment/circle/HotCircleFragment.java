package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.ChoiceBundleData;
import com.paobuqianjin.pbq.step.data.bean.bundle.MyCreateCircleBundleData;
import com.paobuqianjin.pbq.step.data.bean.bundle.MyJoinCreateCircleBudleData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTypeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.JoinCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyJoinCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.JoinCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.QueryRedPkgInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiHotCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.CirCleDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.CreateCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.OwnerCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.SearchCircleActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.adapter.CircleChooseGoodAdapter;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/11.
 */

public class HotCircleFragment extends BaseFragment {
    private final static String TAG = HotCircleFragment.class.getSimpleName();
    private LinearLayoutManager layoutManagerChoose;
    private RecyclerView choiceRecyclerView;
    private RelativeLayout myHotLa, myHotLb;
    private ImageView createCircleView;
    private CircleImageView myHotCircleIV, secondHotCircleIV;
    private TextView myHotCircleTV, secondHotCircleTV;
    private TextView moreMyCircleTV, moreChoiceTV;
    private ImageView readPackAIV, readPackBIV;
    private Context mContext;
    private ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> choiceCircleData;
    private ArrayList<MyCreateCircleResponse.DataBeanX.DataBean> myCreateCircle;
    private ArrayList<MyJoinCircleResponse.DataBeanX.DataBean> myJoinCirCle;
    private int[] pageCounts = new int[3];
    private int circleIdA = -1, circleIdB = -1;
    private int circleNumA = -1, circleNumB = -1;
    private ArrayList<String> circleTypeList;
    CircleChooseGoodAdapter adapter;
    private final static String ACTION_ENTER_ICON = "coma.paobuqian.pbq.step.ICON_ACTION";
    private int PAGE_INDEX_DEFAULT = 1;
    private int PAGE_DEFAULT_SIZE = 10;

    @Override

    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LocalLog.d(TAG, "onCreateView() enter");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        LocalLog.d(TAG, "getLayoutResId() layout R " + R.layout.hot_circle_fragment);
        return R.layout.hot_circle_fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Presenter.getInstance(mContext).dispatchUiInterface(uiHotCircleInterface);
        Presenter.getInstance(mContext).dispatchUiInterface(queryRedPkgInterface);
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        LocalLog.d(TAG, "initView() enter");
        //TODO 圈子活动
        //TODO 精选圈子
        circleTypeList = new ArrayList<>();
        choiceRecyclerView = (RecyclerView) rootView
                .findViewById(R.id.live_choose_good_module)
                .findViewById(R.id.live_choose_good_module_recycler);
        layoutManagerChoose = new LinearLayoutManager(getContext());
        layoutManagerChoose.setOrientation(LinearLayoutManager.VERTICAL);
        choiceRecyclerView.setLayoutManager(layoutManagerChoose);
        choiceRecyclerView.setItemAnimator(new DefaultItemAnimator());
        choiceRecyclerView.addItemDecoration(new CircleChooseGoodAdapter.SpaceItemDecoration(5));


        //
        createCircleView = (ImageView) rootView.findViewById(R.id.circle_create);
        createCircleView.setOnClickListener(onClickListener);

        myHotLa = (RelativeLayout) rootView.findViewById(R.id.circle_hot_a_span);
        myHotLb = (RelativeLayout) rootView.findViewById(R.id.hot_circle_b);
        myHotCircleIV = (CircleImageView) rootView.findViewById(R.id.circle_hot_a_img);
        myHotCircleIV.setOnClickListener(onClickListener);
        secondHotCircleIV = (CircleImageView) rootView.findViewById(R.id.circle_hot_b_img);
        secondHotCircleIV.setOnClickListener(onClickListener);
        myHotCircleTV = (TextView) rootView.findViewById(R.id.circle_hot_a_name);
        secondHotCircleTV = (TextView) rootView.findViewById(R.id.circle_hot_b_name);
        readPackAIV = (ImageView) rootView.findViewById(R.id.red_pack_a);
        readPackBIV = (ImageView) rootView.findViewById(R.id.red_pack_b);

        moreMyCircleTV = (TextView) rootView.findViewById(R.id.find_more_my_circle);
        moreMyCircleTV.setOnClickListener(onClickListener);
        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.live_choose_good_module);
        moreChoiceTV = (TextView) relativeLayout.findViewById(R.id.find_more_choice);
        moreChoiceTV.setOnClickListener(onClickListener);
        Presenter.getInstance(mContext).attachUiInterface(uiHotCircleInterface);
        Presenter.getInstance(mContext).attachUiInterface(queryRedPkgInterface);
        loadingData();
    }


    private void loadingData() {
        Presenter.getInstance(mContext).getCircleChoice(PAGE_INDEX_DEFAULT, PAGE_DEFAULT_SIZE);
        Presenter.getInstance(mContext).getMyHotCircle(PAGE_INDEX_DEFAULT, 2);
    }

    /*@desc  返回Fragment标签
    *@function getTabLabel
    *@param
    *@return
    */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LocalLog.d(TAG, "onClick() enter");
            switch (view.getId()) {
                case R.id.circle_create:
                    LocalLog.d(TAG, "onClick()创建圈子");
                    Intent intent = new Intent();
                    intent.setClass(HotCircleFragment.this.getContext(), CreateCircleActivity.class);
                    //intent.setClass(HotCircleFragment.this.getContext(),SearchCircleActivity.class);
                    //intent.setClass(HotCircleFragment.this.getContext(), MemberManagerActivity.class);
                    intent.putStringArrayListExtra(getContext().getPackageName() + "circle_type", circleTypeList);
                    HotCircleFragment.this.getActivity().startActivity(intent);
                    break;
                case R.id.circle_hot_a_img:
                    LocalLog.d(TAG, "onClick() 我的第一个圈子被点击");
                    Intent intentA = new Intent();
                    intentA.setClass(getContext(), CirCleDetailActivity.class);
                    intentA.putExtra(mContext.getPackageName() + "circleid", circleIdA);
                    intentA.setAction(ACTION_ENTER_ICON);
                    startActivity(intentA);
                    break;
                case R.id.circle_hot_b_img:
                    LocalLog.d(TAG, "onClick() 我的第二个圈子被点击");
                    Intent intentB = new Intent();
                    intentB.setClass(getContext(), CirCleDetailActivity.class);
                    intentB.putExtra(mContext.getPackageName() + "circleid", circleIdB);
                    intentB.setAction(ACTION_ENTER_ICON);
                    startActivity(intentB);
                    break;
                case R.id.find_more_my_circle:
                    LocalLog.d(TAG, "查看更多我的圈子");
                    MyCreateCircleBundleData myCreateCircleBundleData = new MyCreateCircleBundleData(myCreateCircle);
                    MyJoinCreateCircleBudleData myJoinCreateCircleBudleData = new MyJoinCreateCircleBudleData(myJoinCirCle);
                    Intent intentCircle = new Intent();
                    intentCircle.setClass(getActivity(), OwnerCircleActivity.class);
/*                    intentCircle.putExtra(getActivity().getPackageName() + "my_create", myCreateCircleBundleData);
                    intentCircle.putExtra(getActivity().getPackageName() + "my_join", myJoinCreateCircleBudleData);
                    intentCircle.putExtra(getActivity().getPackageName() + "my_create_total_page", pageCounts[0]);
                    intentCircle.putExtra(getActivity().getPackageName() + "my_join_total_page", pageCounts[1]);*/
                    getActivity().startActivity(intentCircle);
                    break;
                case R.id.live_choose_good_module:

                    break;
                case R.id.find_more_choice:
                    LocalLog.d(TAG, "查看更多圈子！");
                    ChoiceBundleData choiceBundleData = new ChoiceBundleData(choiceCircleData);
                    startActivity(SearchCircleActivity.class, choiceBundleData);
                    break;
                default:
                    break;
            }
        }
    };

    public String getTabLabel() {
        return "热门";
    }

    public void setMyHotLa(String name, String urlImage) {
        myHotCircleTV.setText(name);

        Presenter.getInstance(mContext).getImage(myHotCircleIV, urlImage);
        myHotLa.setVisibility(View.VISIBLE);

    }

    public void setMyHotLb(String name, String urlImage) {
        secondHotCircleTV.setText(name);
        Presenter.getInstance(mContext).getImage(secondHotCircleIV, urlImage);
        myHotLb.setVisibility(View.VISIBLE);
    }

    private UiHotCircleInterface uiHotCircleInterface = new UiHotCircleInterface() {
        @Override
        public void response(MyHotCircleResponse myHotCircleResponse) {
            if (myHotCircleResponse.getError() == 0) {
                LocalLog.d(TAG, "myHotCircleResponse ");
                int size = myHotCircleResponse.getData().getData().size();
                LocalLog.d(TAG, "myHotCircleResponse() size =" + size);
                if (size == 1) {

                    setMyHotLa(myHotCircleResponse.getData().getData().get(0).getName(),
                            myHotCircleResponse.getData().getData().get(0).getLogo());
                    circleIdA = myHotCircleResponse.getData().getData().get(0).getId();
                    circleNumA = myHotCircleResponse.getData().getData().get(0).getMember_number();
                    //Presenter.getInstance(getContext()).getCircleDetail(myHotCircleResponse.getData().getData().get(0).getId());
                    if (myHotCircleResponse.getData().getData().get(0).getIs_red_packet() == 1) {
                        readPackAIV.setVisibility(View.VISIBLE);
                    }
                } else if (size >= 2) {
                    setMyHotLa(myHotCircleResponse.getData().getData().get(0).getName(),
                            myHotCircleResponse.getData().getData().get(0).getLogo());
                    setMyHotLb(myHotCircleResponse.getData().getData().get(1).getName(),
                            myHotCircleResponse.getData().getData().get(1).getLogo());

                    circleIdA = myHotCircleResponse.getData().getData().get(0).getId();
                    circleNumA = myHotCircleResponse.getData().getData().get(0).getMember_number();

                    circleIdB = myHotCircleResponse.getData().getData().get(1).getId();
                    circleNumB = myHotCircleResponse.getData().getData().get(1).getMember_number();
                    //Presenter.getInstance(getContext()).getCircleDetail(myHotCircleResponse.getData().getData().get(0).getId());
                    if (myHotCircleResponse.getData().getData().get(0).getIs_red_packet() == 1) {
                        readPackAIV.setVisibility(View.VISIBLE);
                    }
                    //Presenter.getInstance(getContext()).getCircleDetail(myHotCircleResponse.getData().getData().get(1).getId());
                    if (myHotCircleResponse.getData().getData().get(1).getIs_red_packet() == 1) {
                        readPackBIV.setVisibility(View.VISIBLE);
                    }
                }
                pageCounts[1] = myHotCircleResponse.getData().getPagenation().getTotalPage();
            }
        }

        @Override
        public void response(CircleTypeResponse circleTypeResponse) {
            LocalLog.d(TAG, "CircleTypeResponse() ");
            int size = circleTypeResponse.getData().size();
            for (int i = 0; i < size; i++) {
                circleTypeList.add(circleTypeResponse.getData().get(i).getName());
            }
        }

        @Override
        public void response(MyJoinCircleResponse myJoinCircleResponse) {
            /*LocalLog.d(TAG, "myJoinCircleResponse ");
            myJoinCirCle = (ArrayList<MyJoinCircleResponse.DataBeanX.DataBean>) myJoinCircleResponse.getData().getData();
            int size = myJoinCircleResponse.getData().getData().size();
            LocalLog.d(TAG, "myHotCircleResponse() size =" + size);
            if (size == 1) {

                setMyHotLa(myJoinCircleResponse.getData().getData().get(0).getName(),
                        myJoinCircleResponse.getData().getData().get(0).getLogo(),
                        true);
                circleIdA = myJoinCircleResponse.getData().getData().get(0).getId();
                circleNumA = myJoinCircleResponse.getData().getData().get(0).getMember_number();
            } else if (size >= 2) {
                setMyHotLa(myJoinCircleResponse.getData().getData().get(0).getName(),
                        myJoinCircleResponse.getData().getData().get(0).getLogo(),
                        true);
                setMyHotLb(myJoinCircleResponse.getData().getData().get(1).getName(),
                        myJoinCircleResponse.getData().getData().get(1).getLogo(),
                        true);

                circleIdA = myJoinCircleResponse.getData().getData().get(0).getId();
                circleNumA = myJoinCircleResponse.getData().getData().get(0).getMember_number();

                circleIdB = myJoinCircleResponse.getData().getData().get(1).getId();
                circleNumB = myJoinCircleResponse.getData().getData().get(1).getMember_number();
            }
            pageCounts[1] = myJoinCircleResponse.getData().getPagenation().getTotalPage();*/
        }

        @Override
        public void response(MyCreateCircleResponse myCreateCircleResponse) {
          /*  LocalLog.d(TAG, "myCreateCircleResponse  ");
            myCreateCircle = (ArrayList<MyCreateCircleResponse.DataBeanX.DataBean>) myCreateCircleResponse.getData().getData();
            pageCounts[0] = myCreateCircleResponse.getData().getPagenation().getTotalPage();*/
        }

        @Override
        public void response(ChoiceCircleResponse choiceCircleResponse) {
            if (choiceCircleResponse.getError() == 0) {
                LocalLog.d(TAG, " response() 更新精选圈子 size = " + choiceCircleResponse.getData().getData().size());
                choiceCircleData = (ArrayList<ChoiceCircleResponse.DataBeanX.DataBean>) choiceCircleResponse.getData().getData();
                adapter = new CircleChooseGoodAdapter(getActivity(), getContext(),
                        choiceCircleData);
                choiceRecyclerView.setAdapter(adapter);
                pageCounts[2] = choiceCircleResponse.getData().getPagenation().getTotalPage();
            }
        }
    };


    private QueryRedPkgInterface queryRedPkgInterface = new QueryRedPkgInterface() {
        @Override
        public void response(CircleDetailResponse circleDetailResponse) {
            LocalLog.d(TAG, "获取圈子详情");
            if (circleDetailResponse.getError() == 0) {
                if (circleDetailResponse.getData().getIs_red_packet() == 1) {
                    if (circleIdA == circleDetailResponse.getData().getId()) {
                        readPackAIV.setVisibility(View.VISIBLE);
                    } else if (circleIdA == circleDetailResponse.getData().getId()) {
                        readPackBIV.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    };
}
