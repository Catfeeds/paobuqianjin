package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.app.Activity;
import android.content.Intent;
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
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExAddResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.exchange.ExAddrAdapter;
import com.paobuqianjin.pbq.step.view.fragment.exchange.SaleManFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/12/28.
 */

public class SelectAddressActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {
    private final static String TAG = SelectAddressActivity.class.getSimpleName();
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
    @Bind(R.id.address_detail)
    SwipeMenuRecyclerView addressDetail;
    private final static int ADD_ADDRESS = 1;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    private int currentPage = 0;
    private List<ExAddResponse.DataBeanX.DataBean> listData = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private ExAddrAdapter exAddrAdapter;

    @Override
    protected String title() {
        return "选择地址";
    }

    @Override
    public Object right() {
        return "添加";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_selcet_address_activity_layout);
        ButterKnife.bind(this);
        setToolBarListener(this);
        linearLayoutManager = new LinearLayoutManager(this);
        exAddrAdapter = new ExAddrAdapter(this, listData);
        addressDetail.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                LocalLog.d(TAG, "选择为默认地址");
                if (position < listData.size() && listData.get(position).getIs_default() != 1) {
                    setDefaultAddress(position);
                }
            }
        });
        addressDetail.setLayoutManager(linearLayoutManager);
        addressDetail.setAdapter(exAddrAdapter);
        addressDetail.addItemDecoration(new SpaceItemDecoration(1));
        getAddRList(1);
    }

    private void setDefaultAddress(final int position) {
        Map<String, String> param = new HashMap<>();
        param.put("addr_id", String.valueOf(listData.get(position).getId()));
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlExSetDefaultAddress, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                setResult(Activity.RESULT_OK);
                finish();
                PaoToastUtils.showLongToast(SelectAddressActivity.this,"默认地址设置成功！");
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {

                }
            }
        });
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    private void getAddRList(final int page) {
        Map<String, String> param = new HashMap<>();
        param.put("page", String.valueOf(page));
        param.put("pagesize", String.valueOf(Constants.PAGE_SIZE));
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlExAddressList, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    ExAddResponse exAddResponse = new Gson().fromJson(s, ExAddResponse.class);
                    if (page == 1) {
                        listData.clear();
                    }
                    if (exAddResponse.getData().getData().size() > 0) {
                        listData.addAll(exAddResponse.getData().getData());
                        exAddrAdapter.notifyDataSetChanged();
                    }
                    notFoundData.setVisibility(View.GONE);
                    addressDetail.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                notFoundData.setVisibility(View.VISIBLE);
                addressDetail.setVisibility(View.GONE);
            }
        });
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

    @Override
    public void clickRight() {
        LocalLog.d(TAG, "");
        Intent intent = new Intent();
        intent.setClass(this, AddExAddrActivity.class);
        startActivityForResult(intent, ADD_ADDRESS);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ADDRESS && resultCode == Activity.RESULT_OK) {
            getAddRList(1);
        }
    }
}
