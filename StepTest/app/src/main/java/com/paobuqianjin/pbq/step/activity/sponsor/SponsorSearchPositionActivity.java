package com.paobuqianjin.pbq.step.activity.sponsor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.adapter.SearchPositionAdapter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.tencent.lbssearch.TencentSearch;
import com.tencent.lbssearch.httpresponse.BaseObject;
import com.tencent.lbssearch.httpresponse.HttpResponseListener;
import com.tencent.lbssearch.object.param.SearchParam;
import com.tencent.lbssearch.object.param.SuggestionParam;
import com.tencent.lbssearch.object.result.SearchResultObject;
import com.tencent.lbssearch.object.result.SuggestionResultObject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SponsorSearchPositionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.search_icon)
    RelativeLayout searchIcon;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.cancel_icon)
    RelativeLayout cancelIcon;
    @Bind(R.id.search_location)
    RelativeLayout searchLocation;
    @Bind(R.id.lv_position)
    ListView lvPosition;

    private TencentSearch tencentSearch;
    private SearchPositionAdapter adapter;
    private boolean isBottom;
    private int pageNum = 1;
    private boolean isLoad;
    private Intent intent;
    private String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor_search_position);
        ButterKnife.bind(this);
        init();
        initListener();
    }

    private void initListener() {
        lvPosition.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == SCROLL_STATE_IDLE && isBottom && !isLoad) {
                    isLoad = true;
                    SearchParam.Region r = new SearchParam.Region().poi(city);
                    SearchParam param = new SearchParam().keyword(searchCircleText.getText().toString()).boundary(r)
                            .page_index(pageNum);
                    tencentSearch.search(param, listener);
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                isBottom = i + i1 == i2;
            }
        });
        searchCircleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                LocalLog.d("SearchDemo", editable.toString());
                pageNum = 1;
                SuggestionParam suggestionParam = new SuggestionParam().keyword(editable.toString());
                tencentSearch.suggestion(suggestionParam, listener);
            }
        });
    }

    HttpResponseListener listener = new HttpResponseListener() {
        @Override
        public void onSuccess(int i, BaseObject object) {
            LocalLog.d("SearchDemo", "title:");
            if (object instanceof SuggestionResultObject) {
                SuggestionResultObject oj = (SuggestionResultObject) object;
//            String result = "关键字输入提示，区域北京,关键字为：" + s1 + "\n\n";
                if (oj.data != null) {
                    for (SuggestionResultObject.SuggestionData data : oj.data) {
                        LocalLog.d("SearchDemo", "title:" + data.title);
                        LocalLog.d("SearchDemo", "add:" + data.address);
//                    result += data.title + "\n";
                    }
                    adapter.setData(oj.data);
                }
            } else if (object instanceof SearchResultObject) {
                pageNum++;
                SearchResultObject oj = (SearchResultObject) object;
//            String result = "关键字输入提示，区域北京,关键字为：" + s1 + "\n\n";
                if (oj.data != null) {
                    for (SearchResultObject.SearchResultData data : oj.data) {
                        LocalLog.d("SearchDemo", "title:" + data.title);
                        LocalLog.d("SearchDemo", "add:" + data.address);
//                    result += data.title + "\n";
                    }
                    adapter.setMoreData(oj.data);
                }
            }
            isLoad = false;
        }

        @Override
        public void onFailure(int i, String s, Throwable throwable) {
            PaoToastUtils.showShortToast(SponsorSearchPositionActivity.this, s);
            isLoad = false;
        }
    };

    private void init() {
        intent = getIntent();
        city = intent.getStringExtra("city");
        ViewCompat.setTransitionName(searchLocation, "image");
        tencentSearch = new TencentSearch(this);
        adapter = new SearchPositionAdapter(this);
        lvPosition.setAdapter(adapter);
        lvPosition.setOnItemClickListener(this);
    }

    @OnClick({R.id.button_return_bar})
    public void onClick(View view) {
        switch ((view.getId())) {
            case R.id.button_return_bar:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Object object = adapter.getData().get(i);
        if (object instanceof SearchResultObject.SearchResultData) {
            intent.putExtra("lat", ((SearchResultObject.SearchResultData) object).location.lat);
            intent.putExtra("lng", ((SearchResultObject.SearchResultData) object).location.lng);
        } else if (object instanceof SuggestionResultObject.SuggestionData) {
            intent.putExtra("lat", ((SuggestionResultObject.SuggestionData) object).location.lat);
            intent.putExtra("lng", ((SuggestionResultObject.SuggestionData) object).location.lng);
        }
        setResult(2, intent);
        finish();
    }
}
