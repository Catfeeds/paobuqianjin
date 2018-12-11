package com.paobuqianjin.pbq.step.view.activity.shop;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FavRitItemResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.FavorAdapter;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/12/3.
 */

public class FavorIdActivity extends BaseBarActivity implements SwipeMenuRecyclerView.LoadMoreListener {
    private final static String TAG = FavorIdActivity.class.getSimpleName();
    private final static int PAGE_SIZE = 10;
    String favorId = "";
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.grid_view)
    SwipeMenuRecyclerView gridView;
    FavorAdapter favorAdapter;
    List<FavRitItemResponse.DataBean.UatmTbkItemBean> listData = new ArrayList<>();
    RelativeLayout barNull;
    @Bind(R.id.bg_img)
    ImageView bgImg;
    private View viewFirst;
    private GridLayoutManager gridLayoutManager;
    private int currentPage = 1;
    private String title = "";

    @Override
    protected String title() {
        return title;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            title = intent.getStringExtra(getPackageName() + "title");
            setTitle(title);
        }
        setContentView(R.layout.favor_activity_layout);
        ButterKnife.bind(this);
        barNull = (RelativeLayout) findViewById(R.id.bar_null);
        bgImg = (ImageView) barNull.findViewById(R.id.bg_img);
        bgImg.setVisibility(View.VISIBLE);
        barTitle = (TextView) barNull.findViewById(R.id.bar_title);
        barTitle.setTextColor(ContextCompat.getColor(this, R.color.color_f8));
        if (intent != null) {
            favorId = intent.getStringExtra(getPackageName() + "favor_id");
            gridLayoutManager = new GridLayoutManager(this, 2);
            gridView.setLayoutManager(gridLayoutManager);
            favorAdapter = new FavorAdapter(this, listData);
            gridView.setHasFixedSize(true);
            gridView.setNestedScrollingEnabled(false);
            DefineLoadMoreView loadMoreView = new DefineLoadMoreView(this);
            gridView.addFooterView(loadMoreView); // 添加为Footer。
            gridView.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
            gridView.setLoadMoreListener(this);
            gridView.setSwipeItemClickListener(new SwipeItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
                    if (position < listData.size()) {
                        String num_id = listData.get(position).getNum_iid();
                        Intent intent = new Intent();
                        if (!TextUtils.isEmpty(listData.get(position).getCoupon_info())) {
                            intent.putExtra(getPackageName() + "coupon_info", listData.get(position).getCoupon_info());
                        }
                        if (!TextUtils.isEmpty(listData.get(position).getCoupon_click_url())) {
                            intent.putExtra(getPackageName() + "coupon_click_url", listData.get(position).getCoupon_click_url());
                        }
                        if (!TextUtils.isEmpty(listData.get(position).getClick_url())) {
                            intent.putExtra(getPackageName() + "click_url", listData.get(position).getClick_url());
                        }
                        intent.putExtra(getPackageName() + "num_id", num_id);
                        intent.setClass(FavorIdActivity.this, TianMaoDetailActivity.class);
                        startActivity(intent);
                    }
                }
            });
            gridView.setAdapter(favorAdapter);
            getFavorIdList(1);
            gridView.addItemDecoration(new SpaceItemDecoration(14));
            gridView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    LocalLog.d(TAG, "dy =" + dy);
                    try {
                        viewFirst = gridLayoutManager.findViewByPosition(0);
                        if (viewFirst != null) {
                            int[] location = new int[2];
                            LocalLog.d(TAG, "找到view  1");
                            viewFirst.getLocationOnScreen(location);
                            LocalLog.d(TAG, "location[0] =" + location[0] + ",location[1]= " + location[1]);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
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
            if (parent.getChildLayoutPosition(view) > 1) {
                outRect.top = space;
            }
        }

    }

    @Override
    public void onLoadMore() {
        getFavorIdList(currentPage + 1);
    }

    private void getFavorIdList(int page) {
        if (TextUtils.isEmpty(favorId)) {
            finish();
        }
        currentPage = page;
        Map<String, String> param = new HashMap<>();
        param.put("page", String.valueOf(page));
        param.put("pagesize", String.valueOf(PAGE_SIZE));
        param.put("favor_id", favorId);
        param.put("term_id", "1");

        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlFavGoodList, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    FavRitItemResponse favRitItemResponse = new Gson().fromJson(s, FavRitItemResponse.class);
                    if (favRitItemResponse.getData().getUatm_tbk_item() != null && favRitItemResponse.getData().getUatm_tbk_item().size() > 0) {
                        listData.addAll(favRitItemResponse.getData().getUatm_tbk_item());
                        favorAdapter.notifyDataSetChanged();
                        gridView.loadMoreFinish(false, true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {

                } else {
                    PaoToastUtils.showLongToast(FavorIdActivity.this, "开小差了，请稍后再试");
                }
                gridView.loadMoreFinish(false, false);
            }
        });

    }
}
