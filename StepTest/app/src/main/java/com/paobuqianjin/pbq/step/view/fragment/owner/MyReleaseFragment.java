package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskMyReleaseResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.MyReleaseTaskInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.TaskReleaseActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.MyReleaseTaskAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/24.
 */

public class MyReleaseFragment extends BaseBarStyleTextViewFragment implements MyReleaseTaskInterface {
    private final static String TAG = MyReleaseFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.my_release_recycler)
    RecyclerView myReleaseRecycler;
    LinearLayoutManager layoutManager;
    @Bind(R.id.go_to_release)
    TextView goToRelease;
    @Bind(R.id.no_release_record)
    RelativeLayout noReleaseRecord;
    @Bind(R.id.my_release_scroll)
    BounceScrollView myReleaseScroll;


    @Override
    protected int getLayoutResId() {
        return R.layout.my_release_fg;
    }

    @Override
    protected String title() {
        return "我的发布";
    }

    @Override
    public Object right() {
        return "记录";
    }

    private BaseBarImageViewFragment.ToolBarListener toolBarListener = new BaseBarImageViewFragment.ToolBarListener() {
        @Override
        public void clickLeft() {

        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "点击查看记录");
            startActivity(ReleaseRecordActivity.class, null);
        }
    };

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
        Presenter.getInstance(getContext()).taskMyRelease(1, 10);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        myReleaseScroll = (BounceScrollView) viewRoot.findViewById(R.id.my_release_scroll);
        setToolBarListener(toolBarListener);
        layoutManager = new LinearLayoutManager(getContext());
        myReleaseRecycler = (RecyclerView) viewRoot.findViewById(R.id.my_release_recycler);
        myReleaseRecycler.setLayoutManager(layoutManager);

        noReleaseRecord = (RelativeLayout) viewRoot.findViewById(R.id.no_release_record);
        goToRelease = (TextView) viewRoot.findViewById(R.id.go_to_release);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(TaskMyReleaseResponse taskMyReleaseResponse) {
        LocalLog.d(TAG, "TaskMyReleaseResponse() enter");
        if (taskMyReleaseResponse.getError() == 1) {

        } else if (taskMyReleaseResponse.getError() == -1) {

        } else if (taskMyReleaseResponse.getError() == 0) {
            if (myReleaseScroll.getVisibility() != View.VISIBLE) {
                myReleaseScroll.setVisibility(View.VISIBLE);
            }
            myReleaseRecycler.setAdapter(new MyReleaseTaskAdapter(getContext(), taskMyReleaseResponse.getData().getData()));
        } else if (taskMyReleaseResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }

    }

    @Override
    public void response(ErrorCode errorCode) {
        LocalLog.d(TAG, "ErrorCode() enter " + errorCode.getMessage());
        if (errorCode.getError() == 1) {
            LocalLog.d(TAG, "没有数据");
            if (myReleaseScroll.getVisibility() == View.VISIBLE) {
                myReleaseScroll.setVisibility(View.GONE);
                goToRelease.setOnClickListener(onClickListener);
            }
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.go_to_release:
                    startActivity(TaskReleaseActivity.class, null);
                    break;
            }
        }
    };
}
