package com.paobuqianjin.pbq.step.view.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.adapter.CommonGoodAdapter;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SearchGoodResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SearchResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.ShopWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.exchange.ExchangeGoodDeatilActivity;
import com.paobuqianjin.pbq.step.view.activity.exchange.TwoHandReleaseActivity;
import com.paobuqianjin.pbq.step.view.activity.shop.TianMaoActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.model.RongGridView;

/**
 * Created by pbq on 2019/1/14.
 */

public class ShopFragment extends BaseFragment {
    private final static String TAG = ShopFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.time_wait)
    TextView timeWait;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
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
    @Bind(R.id.search_ico)
    LinearLayout searchIco;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.search_shop)
    LinearLayout searchShop;
    @Bind(R.id.good_grid)
    RongGridView goodGrid;
    @Bind(R.id.shop_scroll)
    BounceScrollView shopScroll;
    @Bind(R.id.release_goods)
    ImageView releaseGoods;
    @Bind(R.id.shop_recycler_fg)
    RelativeLayout shopRecyclerFg;
    private final static int EX_GOOD_DETAIL = 2019;
    private final static int EX_GOOD_RELEASE = 2020;
    private int currentPage, pageCount;
    private boolean isLoadingData;
    private CommonGoodAdapter gridGoodAdpter;
    private final static String RELEASE_ACTION = "com.paobuqianjin.pbq.step.RELEASE_ACTION";
    private String keyWord;

    @Override
    protected int getLayoutResId() {
        return R.layout.shop_recycler_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        barTitle = (TextView) viewRoot.findViewById(R.id.bar).findViewById(R.id.bar_title);
        barTitle.setText("步币商城");
        shopScroll = (BounceScrollView) viewRoot.findViewById(R.id.shop_scroll);
        gridGoodAdpter = new CommonGoodAdapter(getContext(), 12);
        goodGrid = (RongGridView) viewRoot.findViewById(R.id.good_grid);
        goodGrid.setAdapter(gridGoodAdpter);
        searchCircleText = (EditText) viewRoot.findViewById(R.id.search_circle_text);
        searchCircleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    searchKeyWord(searchCircleText.getText().toString());
                    Utils.hideInput(getContext());
                    searchCircleText.setFocusable(true);
                    searchCircleText.setFocusableInTouchMode(true);
                }
                return false;
            }
        });
        searchCircleText.addTextChangedListener(textWatcher);
        goodGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (gridGoodAdpter.getExData().size() > 0 && position < gridGoodAdpter.getExData().size()) {
                    Intent intent = new Intent();
                    intent.putExtra("ex_id", String.valueOf(gridGoodAdpter.getExData().get(position).getId()));
                    intent.setClass(getContext(), ExchangeGoodDeatilActivity.class);
                    startActivityForResult(intent, EX_GOOD_DETAIL);
                }
            }
        });

        shopScroll.setTopBottomListener(new BounceScrollView.TopBottomListener() {
            @Override
            public void topBottom(int topOrBottom) {
                if (topOrBottom == 0) {

                } else if (topOrBottom == 1) {
                    if (currentPage < pageCount && !isLoadingData && TextUtils.isEmpty(keyWord)) {
                        /*initGridGood(currentPage + 1);*/
                        getExGood(currentPage + 1);
                    } else {
                        LocalLog.d(TAG, "正在加载或没有更多数据了");
                    }
                }
            }
        });
        getExGood(1);
    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String temp = s.toString();
            LocalLog.d(TAG, "隐藏搜索界面");
            if (TextUtils.isEmpty(temp)) {
                keyWord = null;
                getExGood(1);
            }
        }
    };


    private void searchKeyWord(final String keyword) {
        this.keyWord = keyword;
        search(keyword);
    }

    //分页搜，不支持
    private void search(final int page) {

    }

    //不分页搜
    private void search(final String search) {
        Map<String, String> param = new HashMap<>();
        param.put("search", search);
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlExSearch, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded())
                    return;
                try {
                    SearchGoodResponse searchResponse = new Gson().fromJson(s,SearchGoodResponse.class);
                    if (searchResponse.getData().size() > 0) {
                        gridGoodAdpter.cleanData();
                    }
                    gridGoodAdpter.setData(searchResponse.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isLoadingData = false;
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                isLoadingData = false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.pingduoduo, R.id.tianmao, R.id.jingdong, R.id.weipinghui, R.id.mogu, R.id.release_goods})
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
            case R.id.release_goods:
                Intent intent = new Intent();
                intent.setAction(RELEASE_ACTION);
                intent.setClass(getContext(), TwoHandReleaseActivity.class);
                startActivityForResult(intent, EX_GOOD_RELEASE);
                break;
        }
    }


    private void getExGood(final int page) {
        LocalLog.d(TAG, "getExGood() enter");
        currentPage = page;
        Map<String, String> param = new HashMap<>();
        param.put("page", String.valueOf(page));
        param.put("pagesize", String.valueOf(12));
        isLoadingData = true;
        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlExchangeList, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded()) {
                    return;
                }
                try {
                    ExListResponse exListResponse = new Gson().fromJson(s, ExListResponse.class);
                    pageCount = exListResponse.getData().getPagenation().getTotalPage();
                    if (page == 1) {
                        gridGoodAdpter.cleanData();
                    }
                    gridGoodAdpter.setData(exListResponse.getData().getData());
                    isLoadingData = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                isLoadingData = false;
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EX_GOOD_DETAIL && resultCode == Activity.RESULT_OK) {
            LocalLog.d(TAG, "购买了产品！");
            getExGood(1);
        } else if (requestCode == EX_GOOD_RELEASE && resultCode == Activity.RESULT_OK) {
            LocalLog.d(TAG, "发布了交换品!");
            getExGood(1);
        }
    }
}
