package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SearchResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.LocalContactAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/7/9.
 */

public class SearchResultActivity extends BaseBarActivity {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.search_icon)
    RelativeLayout searchIcon;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.cancel_icon)
    RelativeLayout cancelIcon;
    @Bind(R.id.cancel_text)
    TextView cancelText;
    @Bind(R.id.search_result)
    SwipeMenuRecyclerView searchResult;
    LinearLayoutManager layoutManager;
    LocalContactAdapter localContactAdapter;
    @Bind(R.id.not_found_data)
    TextView notFoundData;

    @Override
    protected String title() {
        return "搜索结果";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        searchCircleText = (EditText) findViewById(R.id.search_circle_text);
        searchCircleText.setHint("搜索手机号、跑步钱进号");
        if (intent != null) {
            String keyWord = intent.getStringExtra("keyword");
            if (!TextUtils.isEmpty(keyWord)) {
                searchKeyWord(keyWord);
                searchCircleText.setText(keyWord);
            }
        }
        layoutManager = new LinearLayoutManager(this);
        searchResult = (SwipeMenuRecyclerView) findViewById(R.id.search_result);
        searchResult.setHasFixedSize(true);
        searchResult.setNestedScrollingEnabled(false);
        searchResult.setLayoutManager(layoutManager);
        notFoundData = (TextView) findViewById(R.id.not_found_data);
        searchCircleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    Utils.hideInput(SearchResultActivity.this);
                    String keyWord = searchCircleText.getText().toString().trim();
                    searchKeyWord(keyWord);
                }
                return false;
            }
        });
    }

    private void searchKeyWord(String keyWord) {
        if (TextUtils.isEmpty(keyWord)) {
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("search", keyWord);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlSearchFriend, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    notFoundData.setVisibility(View.GONE);
                    searchResult.setVisibility(View.VISIBLE);
                    SearchResponse searchResponse = new Gson().fromJson(s, SearchResponse.class);
                    localContactAdapter = new LocalContactAdapter(SearchResultActivity.this, searchResponse.getData());
                    searchResult.setAdapter(localContactAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                if (errorBean != null) {
                    if (errorBean.getError() == 1) {
                        if ("Not Found Data".equals(errorBean.getMessage())) {
                            notFoundData.setVisibility(View.VISIBLE);
                            searchResult.setVisibility(View.GONE);
                        }
                    } else {
                        PaoToastUtils.showLongToast(SearchResultActivity.this, errorBean.getMessage());
                    }
                }
            }
        });
    }

    @OnClick({R.id.cancel_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_text:
                searchCircleText.setText(null);
                break;
        }
    }
}
