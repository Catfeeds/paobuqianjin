package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DanListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserDanResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.DanInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/17.
 */

public class DanFragment extends BaseBarStyleTextViewFragment implements DanInterface {
    private final static String TAG = DanFragment.class.getSimpleName();
    @Bind(R.id.user_icon)
    CircleImageView userIcon;
    @Bind(R.id.decorate)
    ImageView decorate;
    @Bind(R.id.dan_step_des)
    TextView danStepDes;
    @Bind(R.id.dan_percent)
    TextView danPercent;
    @Bind(R.id.dan_text_des)
    RelativeLayout danTextDes;
    @Bind(R.id.dan_text_layer)
    RelativeLayout danTextLayer;
    @Bind(R.id.flag_icon)
    ImageView flagIcon;
    @Bind(R.id.next_dan)
    TextView nextDan;
    @Bind(R.id.next_rel)
    RelativeLayout nextRel;
    @Bind(R.id.distance_process)
    ImageView distanceProcess;
    @Bind(R.id.process_dan)
    TextView processDan;
    @Bind(R.id.dan_main_rel)
    RelativeLayout danMainRel;
    @Bind(R.id.process_bar)
    RelativeLayout processBar;
    @Bind(R.id.percent)
    TextView percent;
    @Bind(R.id.process_bar_rel)
    RelativeLayout processBarRel;
    @Bind(R.id.process_des)
    TextView processDes;
    @Bind(R.id.main_dan_layer)
    RelativeLayout mainDanLayer;
    @Bind(R.id.next_dan_progress)
    RelativeLayout nextDanProgress;
    @Bind(R.id.dan_step_flag_icon)
    ImageView danStepFlagIcon;
    @Bind(R.id.step_dan)
    TextView stepDan;
    @Bind(R.id.dan_step_rel)
    RelativeLayout danStepRel;
    @Bind(R.id.dan_recycler)
    RecyclerView danRecycler;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;

    @Override
    protected String title() {
        return "我的段位";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dan_fg;
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
        Presenter.getInstance(getContext()).getDanList();
        Presenter.getInstance(getContext()).getUserDan();
        return rootView;
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
    }

    @Override
    public void response(DanListResponse danListResponse) {
        LocalLog.d(TAG, "DanListResponse() enter " + danListResponse.toString());
    }

    @Override
    public void response(UserDanResponse userDanResponse) {
        LocalLog.d(TAG, "UserDanResponse() enter " + userDanResponse.toString());
    }
}
