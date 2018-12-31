package com.paobuqianjin.pbq.step.view.fragment.exchange;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.adapter.GoodDetailAdapter;
import com.paobuqianjin.pbq.step.view.activity.shop.TianMaoDetailActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/12/28.
 */
/*
@className :ExgoodDetailFragment
*@date 2018/12/28
*@author
*@description 交换物品详情
*/
public class ExgoodDetailFragment extends BaseFragment {

    @Bind(R.id.ex_good_des)
    TextView exGoodDes;
    @Bind(R.id.good_img)
    RecyclerView goodImg;
    private String goodDesc;
    private List<String> imgList;

    public void setContent(String desc, List<String> img) {
        this.goodDesc = desc;
        this.imgList = img;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.exchange_good_detail_fg;
    }

    @Override
    protected void initView(View viewRoot) {
        exGoodDes = (TextView) viewRoot.findViewById(R.id.ex_good_des);
        goodImg = (RecyclerView) viewRoot.findViewById(R.id.good_img);
        goodImg.setNestedScrollingEnabled(false);
        goodImg.setFocusable(false);
        if (!TextUtils.isEmpty(goodDesc)) {
            exGoodDes.setText(goodDesc);
        }
        if (imgList != null && imgList.size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            goodImg.setLayoutManager(linearLayoutManager);
            goodImg.setAdapter(new GoodDetailAdapter(getContext(), imgList));
            goodImg.setNestedScrollingEnabled(false);
        }
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
