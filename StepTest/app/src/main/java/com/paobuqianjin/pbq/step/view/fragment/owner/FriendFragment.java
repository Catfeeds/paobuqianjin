package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFollowOtOResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserIdFollowResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.activity.FriendActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.FollowAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2019/1/11.
 */

public class FriendFragment extends BaseFragment implements SwipeMenuRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.friend_recycler)
    SwipeMenuRecyclerView friendRecycler;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;
    private String action = "";
    private List<Object> listData = new ArrayList<>();
    private List<?> searchData = new ArrayList<>();
    private String iAction;
    private int currentPage;
    private String keyWord;
    private FollowAdapter adapter;
    private FriendActivity.UpFollowMeInterface upFollowMeInterface;

    //call before commit only once
    public void setFollowAction(String action) {
        this.action = action;
    }

    public void setUpFollowMeInterface(FriendActivity.UpFollowMeInterface upFollowMeInterface) {
        this.upFollowMeInterface = upFollowMeInterface;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.friend_base_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        getData(1);
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
        getData(1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initView(View viewRoot) {
        refresh = (SwipeRefreshLayout) viewRoot.findViewById(R.id.refresh);
        friendRecycler = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.friend_recycler);
        adapter = new FollowAdapter(getActivity(), listData);
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getActivity());
        friendRecycler.addFooterView(loadMoreView); // 添加为Footer。
        friendRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        friendRecycler.setLoadMoreListener(this);
        friendRecycler.setHasFixedSize(true);
        friendRecycler.setNestedScrollingEnabled(false);
        refresh.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }

            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        };
        friendRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                refresh.setEnabled(topRowVerticalPosition >= 0);
            }
        });
        friendRecycler.setLayoutManager(linearLayoutManager);
        friendRecycler.setAdapter(adapter);
        if (!TextUtils.isEmpty(action)) {
            switch (action) {
                case "好友":
                    iAction = "mutual";
                    break;
                case "已关注":
                    iAction = "my";
                    break;
                case "关注我的":
                    iAction = "me";
                    break;
            }
        }
        getData(1);
    }

    @Override
    public void onRefresh() {
        getData(1);
    }

    @Override
    public void onLoadMore() {
        if (isAdded()) {
            getData(currentPage + 1);
        }
    }

    private void getData(final int page) {
        if (!TextUtils.isEmpty(iAction)) {
            Map<String, String> param = new HashMap<>();
            param.put("action", iAction);
            if (!TextUtils.isEmpty(keyWord)) {
                param.put("keyword", keyWord);
            }
            param.put("userid", String.valueOf(Presenter.getInstance(getContext()).getId()));
            currentPage = page;
            param.put("page", String.valueOf(page));
            param.put("pagesize", String.valueOf(Constants.PAGE_SIZE));

            Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlUserFollow, param, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    if (!isAdded())
                        return;
                    try {
                        refresh.setRefreshing(false);
                        if (iAction.equals("mutual")) {
                            UserFollowOtOResponse userFollowOtOResponse = new Gson().fromJson(s, UserFollowOtOResponse.class);
                            if (page == 1) {
                                listData.clear();
                                adapter.notifyDataSetChanged();
                            }
                            if (userFollowOtOResponse.getData().getData().size() > 0) {
                                listData.addAll(userFollowOtOResponse.getData().getData());
                                adapter.notifyDataSetChanged();
                            }
                        } else if (iAction.equals("my")) {
                            UserIdFollowResponse userIdFollowResponse = new Gson().fromJson(s, UserIdFollowResponse.class);
                            if (page == 1) {
                                listData.clear();
                                adapter.notifyDataSetChanged();
                            }
                            if (userIdFollowResponse.getData().getData().size() > 0) {
                                listData.addAll(userIdFollowResponse.getData().getData());
                                adapter.notifyDataSetChanged();
                            }
                        } else if (iAction.equals("me")) {
                            FollowUserResponse followUserResponse = new Gson().fromJson(s, FollowUserResponse.class);
                            if (upFollowMeInterface != null) {
                                upFollowMeInterface.update(followUserResponse.getData().getPagenation().getTotalCount());
                            }
                            if (page == 1) {
                                listData.clear();
                                adapter.notifyDataSetChanged();
                            }
                            if (followUserResponse.getData().getData().size() > 0) {
                                listData.addAll(followUserResponse.getData().getData());
                                adapter.notifyDataSetChanged();
                            }
                        }
                        friendRecycler.loadMoreFinish(false, true);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                    if (!isAdded())
                        return;
                    refresh.setRefreshing(false);
                    friendRecycler.loadMoreFinish(true, false);
                    if (page == 1 && iAction.equals("me")) {
                        upFollowMeInterface.update(0);
                    }
                }
            });
        }
    }
}
