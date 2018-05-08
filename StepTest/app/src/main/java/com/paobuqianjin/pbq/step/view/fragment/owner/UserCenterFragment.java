package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.QueryFollowStateParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddDeleteFollowResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicPersonResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.QueryFollowStateResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.AddDeleteFollowInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserHomeInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.UserDynamicRecordAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    LinearLayoutManager layoutManager;
    QueryFollowStateParam queryFollowStateParam;
    @Bind(R.id.vip_flg)
    ImageView vipFlg;
    @Bind(R.id.scrollView_center)
    BounceScrollView scrollViewCenter;
    RelativeLayout barNull;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGESIZE = 50;

    @Override
    protected String title() {
        return "个人主页";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.user_center_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
        Presenter.getInstance(getContext()).attachUiInterface(addDeleteFollowInterface);
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
                queryFollowStateParam = new QueryFollowStateParam();
                if (userid != Presenter.getInstance(getContext()).getId()) {
                    queryFollowStateParam.setFollowid(userid);
                    Presenter.getInstance(getContext()).postQueryFollowState(queryFollowStateParam);
                }
                Presenter.getInstance(getContext()).getUserInfo(String.valueOf(userid));
                Presenter.getInstance(getContext()).getUserDynamic(String.valueOf(userid), pageIndex, PAGESIZE);
            }
        }
        userHeadIco = (CircleImageView) viewRoot.findViewById(R.id.user_head_ico);
        bntLike = (Button) viewRoot.findViewById(R.id.bnt_like);
        userName = (TextView) viewRoot.findViewById(R.id.user_name);
        sexIcon = (ImageView) viewRoot.findViewById(R.id.sex_icon);
        targerNum = (TextView) viewRoot.findViewById(R.id.targer_num);
        locationCity = (TextView) viewRoot.findViewById(R.id.location_city);

        layoutManager = new LinearLayoutManager(getContext());
        dynamicRecordRecycler = (RecyclerView) viewRoot.findViewById(R.id.dynamic_record_recycler);
        dynamicRecordRecycler.setLayoutManager(layoutManager);
        vipFlg = (ImageView) viewRoot.findViewById(R.id.vip_flg);
        scrollViewCenter = (BounceScrollView) viewRoot.findViewById(R.id.scrollView_center);
        barNull = (RelativeLayout) (viewRoot.findViewById(R.id.bar_return_null_bg));
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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(addDeleteFollowInterface);
    }

    private boolean specialCity(String province) {
        if ("澳门".equals(province) || "台湾省".equals(province)
                || "香港".equals(province) || "重庆市".equals(province) || "上海市".equals(province)
                || "天津市".equals(province) || "北京市".equals(province)) {
            return true;
        }
        return false;
    }


    @Override
    public void response(UserInfoResponse userInfoResponse) {
        LocalLog.d(TAG, "UserInfoResponse() enter" + userInfoResponse.toString());
        if (userInfoResponse.getError() == 0) {
            if (sexIcon == null) {
                return;
            }
            if (userInfoResponse.getData().getSex() == 1) {
                sexIcon.setImageResource(R.drawable.man);
            } else if (userInfoResponse.getData().getSex() == 2) {
                sexIcon.setImageResource(R.drawable.woman_flag);
            }
            userName.setText(userInfoResponse.getData().getNickname());
            String stepFormat = getContext().getResources().getString(R.string.user_target);
            String stepStr = String.format(stepFormat, userInfoResponse.getData().getTarget_step());
            targerNum.setText(stepStr);
            Presenter.getInstance(getContext()).getImage(userHeadIco, userInfoResponse.getData().getAvatar());
            if (userInfoResponse.getData().getCity().equals(userInfoResponse.getData().getProvince())) {
                locationCity.setText(userInfoResponse.getData().getCity());
            } else {
                if (!TextUtils.isEmpty(userInfoResponse.getData().getProvince())) {

                    if (specialCity(userInfoResponse.getData().getProvince())) {
                        locationCity.setText(userInfoResponse.getData().getCity());
                    } else {
                        locationCity.setText(userInfoResponse.getData().getProvince() + "· " + userInfoResponse.getData().getCity());
                    }
                } else {
                    locationCity.setText(userInfoResponse.getData().getCity());
                }

            }
            if (userInfoResponse.getData().getVip() == 1) {
                vipFlg.setVisibility(View.VISIBLE);
            }
        } else if (userInfoResponse.getError() == 0) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    @Override
    public void response(DynamicPersonResponse dynamicPersonResponse) {
        if (dynamicPersonResponse.getError() == 0) {
            LocalLog.d(TAG, "DynamicPersonResponse() enter " + dynamicPersonResponse.toString());
            pageCount = dynamicPersonResponse.getData().getPagenation().getTotalPage();
            List<List> map = checkDaysDynamic(dynamicPersonResponse);
            dynamicRecordRecycler.addItemDecoration(new UserDynamicRecordAdapter.SpaceItemDecoration(45));
            dynamicRecordRecycler.setAdapter(new UserDynamicRecordAdapter(getContext(), map));
        } else if (dynamicPersonResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    private List<List> checkDaysDynamic(DynamicPersonResponse dynamicPersonResponse) {
        LocalLog.d(TAG, "当前记录条数 " + dynamicPersonResponse.getData().getData().size());
        String tempDays = "";
        List<List> map = new ArrayList<>();
        List<DynamicPersonResponse.DataBeanX.DataBean> list = new ArrayList<>();
        for (int i = 0; i < dynamicPersonResponse.getData().getData().size(); i++) {
            long create_time = dynamicPersonResponse.getData().getData().get(i).getCreate_time();
            String create_timeStr = DateTimeUtil.formatFriendly(new Date(create_time * 1000));
            LocalLog.d(TAG, "create_timeStr = " + create_timeStr + ", tempdays =" + tempDays);
            if (i == 0) {
                LocalLog.d(TAG, "记录第一条时间");
                tempDays = create_timeStr;
                list.add(dynamicPersonResponse.getData().getData().get(0));
                if (dynamicPersonResponse.getData().getData().size() == 1) {
                    map.add(list);
                    break;
                }
            } else {
                if (create_timeStr.equals(tempDays)) {
                    list.add(dynamicPersonResponse.getData().getData().get(i));
                } else {
                    LocalLog.d(TAG, tempDays + " 记录条数 " + list.size());
                    map.add(list);
                    list = new ArrayList<>();
                    list.add(dynamicPersonResponse.getData().getData().get(i));
                    tempDays = create_timeStr;
                }
                if (i == dynamicPersonResponse.getData().getData().size() - 1) {
                    LocalLog.d(TAG, tempDays + " 记录条数 " + list.size());
                    map.add(list);
                }
            }
        }
        return map;
    }

    @Override
    public void response(QueryFollowStateResponse queryFollowStateResponse) {
        LocalLog.d(TAG, "QueryFollowStateResponse() enter " + queryFollowStateResponse.toString());
        if (queryFollowStateResponse.getError() == 0) {
            if (queryFollowStateResponse.getData().getIs_follow() == 0) {
                LocalLog.d(TAG, "关注");
                bntLike.setText("关注");
            } else if (queryFollowStateResponse.getData().getIs_follow() == 1) {
                LocalLog.d(TAG, "已关注");
                bntLike.setText("已关注");
            }
            bntLike.setVisibility(View.VISIBLE);
            bntLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.bnt_like:
                            switch (bntLike.getText().toString()) {
                                case "已关注":
                                    LocalLog.d(TAG, "取消关注");
                                    Presenter.getInstance(getContext()).postAddUserFollow(queryFollowStateParam);
                                    break;
                                case "关注":
                                    LocalLog.d(TAG, "去关注");
                                    Presenter.getInstance(getContext()).postAddUserFollow(queryFollowStateParam);
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                }
            });
        } else if (queryFollowStateResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    private AddDeleteFollowInterface addDeleteFollowInterface = new AddDeleteFollowInterface() {
        @Override
        public void response(AddDeleteFollowResponse addDeleteFollowResponse) {
            if (addDeleteFollowResponse.getError() == 0) {
                if (addDeleteFollowResponse.getMessage().equals("取消关注成功")) {
                    bntLike.setText("关注");
                } else {
                    bntLike.setText("已关注");
                }
            } else if (addDeleteFollowResponse.getError() == -100) {
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
            if (errorCode.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                Presenter.getInstance(getContext()).setId(-1);
                Presenter.getInstance(getContext()).steLogFlg(false);
                Presenter.getInstance(getContext()).setToken(getContext(), "");
                getActivity().finish();
                System.exit(0);
            }
        }
    };

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
