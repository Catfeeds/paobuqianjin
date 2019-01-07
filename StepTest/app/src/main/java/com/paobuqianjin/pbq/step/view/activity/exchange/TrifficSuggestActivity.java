package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TrSugResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.exchange.ExtrSuggestAdapter;
import com.paobuqianjin.pbq.step.view.fragment.exchange.ExOutFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/12/26.
 */

public class TrifficSuggestActivity extends BaseBarActivity {
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
    @Bind(R.id.recycler_tr)
    SwipeMenuRecyclerView recyclerTr;

    @Override
    protected String title() {
        return "运费提示";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_tri_suggest_layout);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerTr.setLayoutManager(linearLayoutManager);
        recyclerTr.addItemDecoration(new SpaceItemDecoration(20));
        getTrSu();
    }


    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
            if (parent.getChildAdapterPosition(view) > 0) {
                outRect.top = space;
            }
        }

    }

    private void getTrSu() {
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlExTriSu, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    TrSugResponse trSugResponse = new Gson().fromJson(s, TrSugResponse.class);
                    ExtrSuggestAdapter extrSuggestAdapter = new ExtrSuggestAdapter(TrifficSuggestActivity.this, trSugResponse.getData());
                    recyclerTr.setAdapter(extrSuggestAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }
}
