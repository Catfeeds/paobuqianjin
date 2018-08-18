package com.paobuqianjin.pbq.step;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleType2Response;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.CircleTagAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CircleTypeActivity extends BaseBarActivity {

    @Bind(R.id.rv_type)
    SwipeMenuRecyclerView rvType;
    private CircleTagAdapter adapter;
    private List<CircleType2Response.DataBean> list = new ArrayList<>();
    private int selectPosition = -1;

    @Override
    protected String title() {
        return getString(R.string.circle_type);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_type);
        ButterKnife.bind(this);

        adapter = new CircleTagAdapter(this, list);
        rvType.setLayoutManager(new GridLayoutManager(this, 3));
        rvType.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                for (CircleType2Response.DataBean bean :
                        list) {
                    bean.setSelect(false);
                }
                list.get(position).setSelect(true);
                adapter.notifyDataSetChanged();
                selectPosition = position;
            }
        });
        rvType.setAdapter(adapter);

        Presenter.getInstance(this).getPaoBuSimple(NetApi.getCircleTags, null, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                CircleType2Response response = new Gson().fromJson(s, CircleType2Response.class);
                list.clear();
                list.addAll(response.getData());
                adapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.btn_confirm)
    public void onClick() {
        if (selectPosition == -1) {
            PaoToastUtils.showShortToast(this, getString(R.string.tips_select_circle_type));
            return;
        }
        CircleType2Response.DataBean bean = list.get(selectPosition);
        Intent intent = new Intent();
        intent.putExtra("typeName", bean.getName());
        intent.putExtra("typeId", bean.getTagid());
        setResult(RESULT_OK,intent);
        finish();
    }
}
