package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DanListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserDanResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.DanInterface;
import com.paobuqianjin.pbq.step.utils.LoadBitmap;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.DanAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
import com.paobuqianjin.pbq.step.view.base.view.ProcessDanDrawable;

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
    ImageView processBar;
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
    @Bind(R.id.vip_flg)
    ImageView vipFlg;
    @Bind(R.id.scrollView_center)
    BounceScrollView scrollViewCenter;
    private long userStep;
    LinearLayoutManager layoutManager;
    RelativeLayout relativeLayout;
    RelativeLayout barNull;

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
    protected void initView(View viewRoot) {
        super.initView(viewRoot);

        userIcon = (CircleImageView) viewRoot.findViewById(R.id.user_icon);
        danStepDes = (TextView) viewRoot.findViewById(R.id.dan_step_des);
        danPercent = (TextView) viewRoot.findViewById(R.id.dan_percent);
        processDan = (TextView) viewRoot.findViewById(R.id.process_dan);
        percent = (TextView) viewRoot.findViewById(R.id.percent);
        processDes = (TextView) viewRoot.findViewById(R.id.process_des);

        processBar = (ImageView) viewRoot.findViewById(R.id.process_bar);
        layoutManager = new LinearLayoutManager(getContext());
        danRecycler = (RecyclerView) viewRoot.findViewById(R.id.dan_recycler);
        danRecycler.setLayoutManager(layoutManager);
        vipFlg = (ImageView) viewRoot.findViewById(R.id.vip_flg);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            String usrIcon = intent.getStringExtra("usericon");
            if (usrIcon != null && !usrIcon.equals("")) {
                /*Presenter.getInstance(getContext()).getPlaceErrorImage(userIcon, usrIcon, R.drawable.default_head_ico, R.drawable.default_head_ico);*/
                LoadBitmap.glideLoad(getActivity(), userIcon, usrIcon, R.drawable.default_head_ico, R.drawable.default_head_ico);
            }
        }
        barNull = (RelativeLayout) (viewRoot.findViewById(R.id.dan_bar));
        scrollViewCenter = (BounceScrollView) viewRoot.findViewById(R.id.scrollView_center);
        scrollViewCenter.setScrollListener(new BounceScrollView.ScrollListener() {
            @Override
            public void scrollOritention(int l, int t, int oldl, int oldt) {
                LocalLog.d(TAG, "l =  " + l + ",t = " + t + ",oldl= " + oldl + "," + oldt);
                if (Utils.px2dip(getContext(), (float) t) > 64) {
                    barNull.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_232433));
                } else {
                    barNull.setBackground(null);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
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
        if (!isAdded()) {
            return;
        }
        Presenter.getInstance(getContext()).getDanList();
    }

    @Override
    public void response(DanListResponse danListResponse) {
        LocalLog.d(TAG, "DanListResponse() enter " + danListResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (danListResponse.getError() == 0) {
            for (int i = 0; i < danListResponse.getData().size(); i++) {
                if (userStep > danListResponse.getData().get(i).getTarget()) {
                    danListResponse.getData().get(i).setFinished(true);
                }
            }
            if (danRecycler != null) {
                danRecycler.setAdapter(new DanAdapter(getContext(), danListResponse.getData()));
            }

        } else if (danListResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            Toast.makeText(getContext(), danListResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void response(UserDanResponse userDanResponse) {
        LocalLog.d(TAG, "UserDanResponse() enter " + userDanResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (userDanResponse.getError() == 0) {
            if (!isAdded() || danStepDes == null) {
                return;
            }
            userStep = userDanResponse.getData().getTotal_step_number();
            LocalLog.d(TAG, "用户当前步数");
            String stepStrFormat = getString(R.string.total_step_dan);
            String stepStr = String.format(stepStrFormat, userStep);
            danStepDes.setText(stepStr);
            String beatStrFormat = getString(R.string.beat_nums);
            String beatStr = String.format(beatStrFormat, userDanResponse.getData().getBeat());
            danPercent.setText(beatStr);
            String processDanFormat = getString(R.string.steps_should_add_to);
            String processDanStr = String.format(processDanFormat, userDanResponse.getData().getTarget());
            processDan.setText(processDanStr);
            final float percents = (float) userDanResponse.getData().getTotal_step_number() / userDanResponse.getData().getTarget();
            processBar.post(new Runnable() {
                @Override
                public void run() {
                    if (processBar == null) {
                        return;
                    }
                    int width = processBar.getWidth();
                    int height = processBar.getHeight();
                    LocalLog.d(TAG, "width = " + width + ",height = " + height);
                    if (width <= width * percents) {

                    }
                    processBar.setImageDrawable(new ProcessDanDrawable().setMaxLength(width).setLength(width * percents, height));
                }
            });
            String percentFormat = getString(R.string.percent);
            String percentStr = String.format(percentFormat, percents * 100);
            percent.setText(percentStr);
            String peopleNumFormat = getString(R.string.people_finished);
            String peopleNumStr = String.format(peopleNumFormat, userDanResponse.getData().getNumber());
            processDes.setText(peopleNumStr);
            Presenter.getInstance(getContext()).getDanList();
        } else if (userDanResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            Toast.makeText(getContext(), userDanResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
