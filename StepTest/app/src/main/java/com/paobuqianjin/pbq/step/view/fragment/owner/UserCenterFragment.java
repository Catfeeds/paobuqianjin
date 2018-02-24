package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicPersonResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UserHomeInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/6.
 */

public class UserCenterFragment extends BaseBarStyleTextViewFragment implements UserHomeInterface {
    private final static String TAG = UserCenterFragment.class.getSimpleName();
    @Bind(R.id.user_head_ico)
    CircleImageView userHeadIco;
    @Bind(R.id.bnt_like)
    Button bntLike;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.sex_icon)
    ImageView sexIcon;
    @Bind(R.id.mu_biao)
    TextView muBiao;
    @Bind(R.id.user_simple_info)
    RelativeLayout userSimpleInfo;
    @Bind(R.id.location_city)
    TextView locationCity;
    @Bind(R.id.line_center)
    ImageView lineCenter;
    @Bind(R.id.dynamic_record)
    TextView dynamicRecord;
    @Bind(R.id.line_dynamic_line)
    ImageView lineDynamicLine;
    @Bind(R.id.dynamic_record_recycler)
    RecyclerView dynamicRecordRecycler;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.targer_num)
    TextView targerNum;

    @Override
    protected String title() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.user_center_fg;
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
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            int userid = intent.getIntExtra("userid", -1);
            if (userid != -1) {
                LocalLog.d(TAG, "userid = " + userid);
                Presenter.getInstance(getContext()).getUserInfo(String.valueOf(userid));
                Presenter.getInstance(getContext()).getUserDynamic(String.valueOf(userid));
            }
        }
        userHeadIco = (CircleImageView) viewRoot.findViewById(R.id.user_head_ico);
        bntLike = (Button) viewRoot.findViewById(R.id.bnt_like);
        userName = (TextView) viewRoot.findViewById(R.id.user_name);
        sexIcon = (ImageView) viewRoot.findViewById(R.id.sex_icon);
        targerNum = (TextView) viewRoot.findViewById(R.id.targer_num);
        locationCity = (TextView) viewRoot.findViewById(R.id.location_city);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }


    @Override
    public void response(UserInfoResponse userInfoResponse) {
        LocalLog.d(TAG, "UserInfoResponse() enter" + userInfoResponse.toString());
        if (userInfoResponse.getData().getSex() == 0) {
            sexIcon.setImageResource(R.drawable.man_flag);
        } else if (userInfoResponse.getData().getSex() == 1) {
            sexIcon.setImageResource(R.drawable.woman_flag);
        }
        userName.setText(userInfoResponse.getData().getNickname());
        String stepFormat = getContext().getResources().getString(R.string.user_target);
        String stepStr = String.format(stepFormat, userInfoResponse.getData().getTarget_step());
        targerNum.setText(stepStr);
        Presenter.getInstance(getContext()).getImage(userHeadIco,userInfoResponse.getData().getAvatar());
        if (userInfoResponse.getData().getCity().equals(userInfoResponse.getData().getProvince())) {
            locationCity.setText(userInfoResponse.getData().getCity());
        } else {
            locationCity.setText(userInfoResponse.getData().getProvince() + "," + userInfoResponse.getData().getCity());
        }
    }

    @Override
    public void response(DynamicPersonResponse dynamicPersonResponse) {
        LocalLog.d(TAG, "DynamicPersonResponse() enter " + dynamicPersonResponse.toString());

    }
}
