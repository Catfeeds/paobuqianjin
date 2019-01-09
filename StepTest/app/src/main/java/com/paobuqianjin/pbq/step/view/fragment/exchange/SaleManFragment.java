package com.paobuqianjin.pbq.step.view.fragment.exchange;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExGoodDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.view.activity.exchange.ExchangeGoodDeatilActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.exchange.ExMoreAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.umeng.commonsdk.debug.E;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/12/28.
 */

public class SaleManFragment extends BaseFragment {
    @Bind(R.id.head_ico)
    CircleImageView headIco;
    @Bind(R.id.dear_name)
    TextView dearName;
    @Bind(R.id.arrive_time)
    TextView arriveTime;
    @Bind(R.id.sale_in)
    TextView saleIn;
    @Bind(R.id.sale_out)
    TextView saleOut;
    @Bind(R.id.grid_view)
    SwipeMenuRecyclerView gridView;
    private List<ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean> listData = new ArrayList<>();
    private ExMoreAdapter exMoreAdapter;
    private ExGoodDetailResponse.DataBean.UserInfoBean userInfoBean;

    public void setUserInfo(ExGoodDetailResponse.DataBean.UserInfoBean userInfo) {
        this.userInfoBean = userInfo;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.exchange_sale_man_fg;
    }

    @Override
    protected void initView(View viewRoot) {
        headIco = (CircleImageView) viewRoot.findViewById(R.id.head_ico);
        dearName = (TextView) viewRoot.findViewById(R.id.dear_name);
        saleIn = (TextView) viewRoot.findViewById(R.id.sale_in);
        saleOut = (TextView) viewRoot.findViewById(R.id.sale_out);
        gridView = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.grid_view);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2);
        try {
            Presenter.getInstance(getContext()).getPlaceErrorImage(headIco, userInfoBean.getAvatar(),
                    R.drawable.default_head_ico, R.drawable.default_head_ico);

            dearName.setText(userInfoBean.getNickname());
            saleOut.setText(String.valueOf(userInfoBean.getTrade_count()));
            saleIn.setText(String.valueOf(userInfoBean.getSale_count()));
            listData = userInfoBean.getOther_commnuity();
            exMoreAdapter = new ExMoreAdapter(getContext(), userInfoBean.getOther_commnuity());
            gridView.setLayoutManager(linearLayoutManager);
            gridView.setHasFixedSize(true);
            gridView.setNestedScrollingEnabled(false);
            gridView.setSwipeItemClickListener(new SwipeItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
                    Intent intent = new Intent();
                    intent.putExtra("ex_id", String.valueOf(listData.get(position).getId()));
                    intent.setClass(getContext(), ExchangeGoodDeatilActivity.class);
                    startActivity(intent);
                }
            });
            gridView.setAdapter(exMoreAdapter);
            gridView.addItemDecoration(new SpaceItemDecoration(7));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
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
