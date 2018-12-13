package com.paobuqianjin.pbq.step.view.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.adapter.GridGoodAdpter;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.HomeGoodResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.ShopWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.shop.TianMaoActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.model.RongGridView;

/**
 * Created by pbq on 2018/12/12.
 */

public class PaoBuShopFragment extends BaseFragment {
    private final static String TAG = PaoBuShopFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_right_drawable)
    ImageView barRightDrawable;
    @Bind(R.id.linear_shop)
    LinearLayout linearShop;
    @Bind(R.id.step_dollar_shop)
    LinearLayout stepDollarShop;
    @Bind(R.id.pingduoduo)
    ImageView pingduoduo;
    @Bind(R.id.tianmao)
    ImageView tianmao;
    @Bind(R.id.jingdong)
    ImageView jingdong;
    @Bind(R.id.weipinghui)
    ImageView weipinghui;
    @Bind(R.id.mogu)
    ImageView mogu;
    @Bind(R.id.banner_tv)
    ImageView bannerTv;
    @Bind(R.id.good_grid)
    RongGridView goodGrid;
    @Bind(R.id.go_shoping_tv)
    TextView goShopingTv;
    @Bind(R.id.release_goods)
    ImageView shopPing;
    private String shopUrl = null;
    private GridGoodAdpter gridGoodAdpter;


    @Override
    protected int getLayoutResId() {
        return R.layout.pao_bu_shop_fg;
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

    @Override
    protected void initView(View viewRoot) {
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        barTitle.setText("商城");
        goodGrid = (RongGridView) viewRoot.findViewById(R.id.good_grid);
        gridGoodAdpter = new GridGoodAdpter(getContext(), 12);
        goodGrid.setAdapter(gridGoodAdpter);
        goodGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < gridGoodAdpter.getData().size()) {
                    if (!TextUtils.isEmpty(gridGoodAdpter.getData().get(position).getTarget_url())) {
                        String goodUrl = gridGoodAdpter.getData().get(position).getTarget_url() + "&" + Presenter.getInstance(getContext()).getShopEnd();
                        startActivity(new Intent(getActivity(), ShopWebViewActivity.class).putExtra("url",
                                goodUrl));
                    }
                }
            }
        });

        initGridGood();
        goShopingTv = (TextView) viewRoot.findViewById(R.id.go_shoping_tv);
        SpannableString spannableString = new SpannableString("去商城逛逛 查看更多");
        spannableString.setSpan(new TypefaceSpan("default-bold"), 1, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(0xFFFFA202), 1, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        goShopingTv.setText(spannableString);
    }

    private void initGridGood() {
        LocalLog.d(TAG, "init() enter");
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlShopHome, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded())
                    return;
                try {
                    HomeGoodResponse homeGoodResponse = new Gson().fromJson(s, HomeGoodResponse.class);
                    if (!TextUtils.isEmpty(homeGoodResponse.getData().getShop_url())) {
                        shopUrl = homeGoodResponse.getData().getShop_url() + "?" + Presenter.getInstance(getContext()).getShopEnd();
                    }
                    gridGoodAdpter.setDatas(homeGoodResponse.getData().getGoods_list());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (!isAdded()) {
                    return;
                }
                if (errorBean != null) {

                } else {

                }
            }
        });
    }

    @OnClick({R.id.pingduoduo, R.id.tianmao, R.id.jingdong, R.id.weipinghui, R.id.mogu, R.id.go_shoping_tv, R.id.release_goods})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pingduoduo:
                LocalLog.d(TAG, "拼多多");
                startActivity(new Intent(getContext(), SingleWebViewActivity.class).putExtra("url", NetApi.tickXiXiUrl));
                break;
            case R.id.tianmao:
                startActivity(TianMaoActivity.class, null);
                break;
            case R.id.jingdong:
                startActivity(new Intent(getContext(), SingleWebViewActivity.class).putExtra("url", NetApi.urlJd));
                break;
            case R.id.weipinghui:
                PaoToastUtils.showLongToast(getContext(), "敬请期待");
                break;
            case R.id.mogu:
                startActivity(new Intent(getContext(), SingleWebViewActivity.class).putExtra("url", NetApi.urlMoguUrl));
                break;
            case R.id.go_shoping_tv:
                if (!TextUtils.isEmpty(shopUrl))
                    startActivity(new Intent(getActivity(), ShopWebViewActivity.class).putExtra("url", shopUrl));
                break;
            case R.id.release_goods:
                if (!TextUtils.isEmpty(shopUrl))
                    startActivity(new Intent(getActivity(), ShopWebViewActivity.class).putExtra("url", shopUrl));
                break;
        }
    }
}
