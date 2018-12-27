package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFollowOtOResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.FollowAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/12/19.
 */

public class FollowOtNewFragment extends BaseFragment {
    @Bind(R.id.invite_dan_recycler)
    SwipeMenuRecyclerView inviteDanRecycler;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    LinearLayoutManager layoutManager;
    private FollowAdapter adapter;
    private int currentPage;
    private String action;
    private String keyWord = "";
    ArrayList<UserFollowOtOResponse.DataBeanX.DataBean> followOtoData = new ArrayList<>();
    ArrayList<UserFollowOtOResponse.DataBeanX.DataBean> searchData = new ArrayList<>();
    private boolean isSearch = false;
    private int pageCount, pageCountSearch;

    /*界面显示之前调用*/
    public void setAction(final String action) {
        this.action = action;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.friend_fg;
    }

    @Override
    protected void initView(View viewRoot) {

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

    private void getOto(final String action, final int page, final String keyWord) {
        currentPage = page;
        Map<String, String> param = new HashMap<>();
        param.put("action", action);
        param.put("userid", String.valueOf(Presenter.getInstance(getContext()).getId()));
        param.put("page", String.valueOf(page));
        param.put("pagesize", String.valueOf(Constants.PAGE_SIZE));

        if (!TextUtils.isEmpty(keyWord)) {
            param.put("keyword", keyWord);
        }
        String url = NetApi.urlUserFollow;
        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlUserFollow, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded()) {
                    return;
                }
                try {
                    UserFollowOtOResponse followOtOResponse = new Gson().fromJson(s, UserFollowOtOResponse.class);
                    if (!isSearch) {
                        if (currentPage == 1) {
                            followOtoData.clear();
                        }
                        pageCount = followOtOResponse.getData().getPagenation().getTotalPage();
                        followOtoData.addAll(followOtOResponse.getData().getData());
                        adapter.notifyDataSetChanged();
                    } else {
                        if (currentPage == 1) {
                            adapter.notifyDataSetChanged();
                        }
                        pageCountSearch = followOtOResponse.getData().getPagenation().getTotalPage();
                        searchData.addAll(followOtOResponse.getData().getData());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    @OnClick({R.id.invite_dan_recycler, R.id.not_found_data})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.invite_dan_recycler:
                break;
            case R.id.not_found_data:
                break;
        }
    }
}
