package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.l.okhttppaobu.okhttp.OkHttpUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UiHotCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.CreateCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.MemberManagerActivity;
import com.paobuqianjin.pbq.step.view.activity.SearchCircleActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.adapter.CircleChooseGoodAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.MyHotCircleAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/11.
 */

public class HotCircleFragment extends BaseFragment {
    private final static String TAG = HotCircleFragment.class.getSimpleName();
    private LinearLayoutManager layoutManagerChoose;
    private RecyclerView allHotRecyclerView;
    private RelativeLayout myHotLa, myHotLb;
    private ImageView createCircleView;
    private CircleImageView myHotCircleIV, secondHotCircleIV;
    private TextView myHotCircleTV, secondHotCircleTV;
    private TextView moreMyCircleTV, moreChoiceTV;
    private ImageView readPackAIV, readPackBIV;
    private Context mContext;

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
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        LocalLog.d(TAG, "initView() enter");
        //TODO 圈子活动
        //TODO 精选圈子
        allHotRecyclerView = (RecyclerView) rootView
                .findViewById(R.id.live_choose_good_module)
                .findViewById(R.id.live_choose_good_module_recycler);
        layoutManagerChoose = new LinearLayoutManager(getContext());
        layoutManagerChoose.setOrientation(LinearLayoutManager.VERTICAL);
        allHotRecyclerView.setLayoutManager(layoutManagerChoose);
        allHotRecyclerView.addItemDecoration(new CircleChooseGoodAdapter.SpaceItemDecoration(5));
        allHotRecyclerView.setAdapter(new CircleChooseGoodAdapter(getContext()));


        //
        createCircleView = (ImageView) rootView.findViewById(R.id.circle_create);
        createCircleView.setOnClickListener(onClickListener);

        myHotLa = (RelativeLayout) rootView.findViewById(R.id.circle_hot_a_span);
        myHotLb = (RelativeLayout) rootView.findViewById(R.id.hot_circle_b);
        myHotCircleIV = (CircleImageView) rootView.findViewById(R.id.circle_hot_a_img);
        secondHotCircleIV = (CircleImageView) rootView.findViewById(R.id.circle_hot_b_img);
        myHotCircleTV = (TextView) rootView.findViewById(R.id.circle_hot_a_name);
        secondHotCircleTV = (TextView) rootView.findViewById(R.id.circle_hot_b_name);
        readPackAIV = (ImageView) rootView.findViewById(R.id.red_pack_a);
        readPackBIV = (ImageView) rootView.findViewById(R.id.red_pack_b);
        Presenter.getInstance(mContext).attachUiInterface(uiHotCircleInterface);
        loadingData();
    }


    private void loadingData() {
        Presenter.getInstance(mContext).getCircleMy();
        Presenter.getInstance(mContext).getCircleChoice();

    }

    /*@desc  返回Fragment标签
    *@function getTabLabel
    *@param
    *@return
    */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.circle_create:
                    LocalLog.d(TAG, "创建圈子");
                    Intent intent = new Intent();
                    intent.setClass(HotCircleFragment.this.getContext(), CreateCircleActivity.class);
                    //intent.setClass(HotCircleFragment.this.getContext(),SearchCircleActivity.class);
                    //intent.setClass(HotCircleFragment.this.getContext(), MemberManagerActivity.class);
                    HotCircleFragment.this.getActivity().startActivity(intent);
                    Presenter.getInstance(HotCircleFragment.this.getContext()).getCirCleType();
                    break;
            }
        }
    };

    public String getTabLabel() {
        return "热门";
    }

    public void setMyHotLa(String name, String urlImage, boolean isRedShow) {
        myHotCircleTV.setText(name);
        if (isRedShow) {
            readPackAIV.setVisibility(View.VISIBLE);
        }
        Presenter.getInstance(mContext).getImage(myHotCircleIV, urlImage);
        myHotLa.setVisibility(View.VISIBLE);

    }

    public void setMyHotLb(String name, String urlImage, boolean isRedShow) {
        secondHotCircleTV.setText(name);
        if (isRedShow) {
            readPackBIV.setVisibility(View.VISIBLE);
        }
        Presenter.getInstance(mContext).getImage(secondHotCircleIV, urlImage);
        myHotLb.setVisibility(View.VISIBLE);
    }

    private UiHotCircleInterface uiHotCircleInterface = new UiHotCircleInterface() {
        @Override
        public void response(MyHotCircleResponse myHotCircleResponse) {
            int size = myHotCircleResponse.getData().getData().size();
            LocalLog.d(TAG, "myHotCircleResponse() size =" + size);
            switch (size) {
                case 0:
                    LocalLog.d(TAG, "未加入也未创建任何圈子");
                    break;
                case 1:
                    setMyHotLa(myHotCircleResponse.getData().getData().get(0).getName(),
                            myHotCircleResponse.getData().getData().get(0).getLogo(),
                            true);
                    break;
                case 2:
                    setMyHotLa(myHotCircleResponse.getData().getData().get(0).getName(),
                            myHotCircleResponse.getData().getData().get(0).getLogo(),
                            true);
                    setMyHotLb(myHotCircleResponse.getData().getData().get(1).getName(),
                            myHotCircleResponse.getData().getData().get(1).getLogo(),
                            true);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void response(ChoiceCircleResponse choiceCircleResponse) {

        }
    };
}
