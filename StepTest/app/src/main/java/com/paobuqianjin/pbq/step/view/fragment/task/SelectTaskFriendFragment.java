package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.FriendBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendSearchResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.SelectUserFriendInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.adapter.task.SelectTaskFriendAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/26.
 */

public class SelectTaskFriendFragment extends BaseFragment implements SelectUserFriendInterface {
    private final static String TAG = SelectTaskFriendFragment.class.getSimpleName();
    SelectTaskFriendAdapter selectTaskFriendAdapter = null;

    @Bind(R.id.bar_return_left)
    TextView barReturnLeft;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.search_icon)
    RelativeLayout searchIcon;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.friend_recycler)
    RecyclerView friendRecycler;
    private LinearLayoutManager layoutManager;
    private static final int SELECT_FRIENDS = 0;
    FriendBundleData friendBundleData = null;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGE_SIZE = 10;
    private String keyWord = "";
    private ArrayList<UserFriendResponse.DataBeanX.DataBean> friendAll = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.select_task_friend_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        Presenter.getInstance(getContext()).getUserFiends(pageIndex, PAGE_SIZE, keyWord);
        return rootView;
    }

    public void searchKeyWord(String keyWord) {
        this.keyWord = keyWord;
        pageIndex = 1;
        Presenter.getInstance(getContext()).getUserFiends(pageIndex, PAGE_SIZE, keyWord);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        barReturnLeft = (TextView) viewRoot.findViewById(R.id.bar_return_left);
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        barTvRight = (TextView) viewRoot.findViewById(R.id.bar_tv_right);
        barReturnLeft.setText("取消");
        barTitle.setText("选择好友");
        barTvRight.setText("确认");
        searchCircleText = (EditText) viewRoot.findViewById(R.id.search_circle_text);
        searchCircleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    searchKeyWord(searchCircleText.getText().toString());
                    Utils.hideInput(getContext());
                }
                return false;
            }
        });
        barReturnLeft.setOnClickListener(onClickListener);
        barTvRight.setOnClickListener(onClickListener);
        friendRecycler = (RecyclerView) viewRoot.findViewById(R.id.friend_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        friendRecycler.setLayoutManager(layoutManager);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            friendBundleData = (FriendBundleData) intent.getParcelableExtra(getActivity().getPackageName());
            if (friendBundleData != null) {
                selectTaskFriendAdapter = new SelectTaskFriendAdapter(getContext(), null, friendBundleData.getFriendData());
            } else {
                selectTaskFriendAdapter = new SelectTaskFriendAdapter(getContext(), null, null);
            }
            loadData(friendAll);
            friendRecycler.setAdapter(selectTaskFriendAdapter);
        }


    }

    /**
     * 第一次加载数据。
     */
    private void loadData(ArrayList<UserFriendResponse.DataBeanX.DataBean> dataBeans) {
        selectTaskFriendAdapter.notifyDataSetChanged(dataBeans);
    }

    private void loadMore(ArrayList<UserFriendResponse.DataBeanX.DataBean> newData) {
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        friendAll.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        selectTaskFriendAdapter.notifyItemRangeInserted(friendAll.size() - newData.size(), newData.size());
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bar_return_left:
                    LocalLog.d(TAG, "取消");
                    getActivity().finish();
                    break;
                case R.id.bar_tv_right:
                    LocalLog.d(TAG, "确定");
                    if (selectTaskFriendAdapter != null) {
                        //反馈选中结果到上一个Activity
                        FriendBundleData friendBundleData = new FriendBundleData((ArrayList<UserFriendResponse.DataBeanX.DataBean>) selectTaskFriendAdapter.getResultData());
                        LocalLog.d(TAG, selectTaskFriendAdapter.getResultData().toString());
                        Intent intent = new Intent();
                        intent.putExtra(getActivity().getPackageName(), friendBundleData);
                        getActivity().setResult(SELECT_FRIENDS, intent);
                        getActivity().finish();
                    }
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(UserFriendResponse userFriendResponse) {
        LocalLog.d(TAG, "UserFriendResponse() enter " + userFriendResponse.toString());
        if (userFriendResponse.getError() == 0) {
            pageCount = userFriendResponse.getData().getPagenation().getTotalPage();
            LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);
            loadMore((ArrayList<UserFriendResponse.DataBeanX.DataBean>) userFriendResponse.getData().getData());
            pageIndex++;
            if (pageIndex <= pageCount) {
                Presenter.getInstance(getContext()).getUserFiends(pageIndex, PAGE_SIZE, keyWord);
            }
        } else if (userFriendResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        } else {
            Toast.makeText(getContext(), userFriendResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void response(UserFriendSearchResponse userFriendSearchResponse) {
        LocalLog.d(TAG, "UserFriendSearchResponse() enter");

    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }
}
