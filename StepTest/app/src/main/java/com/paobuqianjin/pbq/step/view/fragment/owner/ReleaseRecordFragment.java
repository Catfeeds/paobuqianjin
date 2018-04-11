package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReleaseRecordResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.ReleaseRecordInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.ReleaseRecordAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/25.
 */

public class ReleaseRecordFragment extends BaseBarStyleTextViewFragment implements ReleaseRecordInterface {
    private final static String TAG = ReleaseRecordFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.release_record)
    RecyclerView releaseRecord;
    LinearLayoutManager layoutManager;
    @Bind(R.id.empty_record_span)
    RelativeLayout emptyRecordSpan;
    @Bind(R.id.release_refresh)
    SwipeRefreshLayout releaseRefresh;
    @Bind(R.id.empty_record)
    ImageView emptyRecord;

    @Override
    protected int getLayoutResId() {
        return R.layout.release_record_fg;
    }

    @Override
    protected String title() {
        return "发布记录";
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
        Presenter.getInstance(getContext()).getReleaseRecord(1, 10);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        releaseRefresh = (SwipeRefreshLayout) viewRoot.findViewById(R.id.release_refresh);
        layoutManager = new LinearLayoutManager(getContext());
        releaseRecord = (RecyclerView) viewRoot.findViewById(R.id.release_record);
        releaseRecord.setLayoutManager(layoutManager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(ErrorCode errorCode) {
        LocalLog.d(TAG, "ErrorCode() enter " + errorCode.toString());
        if (errorCode.getError() == 1) {
            LocalLog.d(TAG, "没有记录");
        }
    }

    @Override
    public void response(ReleaseRecordResponse releaseRecordResponse) {
        LocalLog.d(TAG, "ReleaseRecordResponse() enter " + releaseRecordResponse.toString());
        if (releaseRecordResponse.getError() == 0) {
            if (releaseRefresh.getVisibility() == View.GONE) {
                releaseRefresh.setVisibility(View.VISIBLE);
            }
            releaseRecord.setAdapter(new ReleaseRecordAdapter(getContext(), releaseRecordResponse.getData().getData()));
        } else if (releaseRecordResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }
}
