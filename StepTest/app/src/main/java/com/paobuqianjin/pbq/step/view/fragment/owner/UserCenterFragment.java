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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.QueryFollowStateParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddDeleteFollowResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserCenterResponse;
import com.paobuqianjin.pbq.step.data.bean.table.ChatUserInfo;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.AddDeleteFollowInterface;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.RongYunChatUtils;
import com.paobuqianjin.pbq.step.utils.RongYunUserInfoManager;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.UserDynamicRecordAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.rong.imlib.model.Conversation;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2018/1/6.
 */

public class UserCenterFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = UserCenterFragment.class.getSimpleName();
    @Bind(R.id.user_head_ico)
    CircleImageView userHeadIco;
    @Bind(R.id.bnt_like)
    Button bntLike;
    @Bind(R.id.user_name)
    TextView userName;
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
    QueryFollowStateParam queryFollowStateParam = new QueryFollowStateParam();
    @Bind(R.id.scrollView_center)
    BounceScrollView scrollViewCenter;
    RelativeLayout barNull;
    @Bind(R.id.bnt_chat)
    Button bntChat;
    ImageView getVipFlg;
    @Bind(R.id.user_icon_frm)
    FrameLayout userIconFrm;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGESIZE = 50;
    private final static int REQUEST_DETAIL = 401;
    UserDynamicRecordAdapter adapter;
    private String userNo = "";
    private int userid = -1;
    private UserCenterResponse.DataBeanX.UserDataBean userDataBean;

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
            userid = intent.getIntExtra("userid", -1);
            userNo = intent.getStringExtra("userno");
            if (userid != -1) {
                LocalLog.d(TAG, "userid =  " + userid);
                Presenter.getInstance(getContext()).getUserHome(String.valueOf(userid), "", pageIndex, PAGESIZE, userCenterCallBack);
            }
            if (!TextUtils.isEmpty(userNo)) {
                LocalLog.d(TAG, "userno =  " + userNo);
                Presenter.getInstance(getContext()).getUserHome("", userNo, pageIndex, PAGESIZE, userCenterCallBack);
            }
        }
        userHeadIco = (CircleImageView) viewRoot.findViewById(R.id.user_head_ico);
        bntLike = (Button) viewRoot.findViewById(R.id.bnt_like);
        userName = (TextView) viewRoot.findViewById(R.id.user_name);
        targerNum = (TextView) viewRoot.findViewById(R.id.targer_num);
        locationCity = (TextView) viewRoot.findViewById(R.id.location_city);

        layoutManager = new LinearLayoutManager(getContext());
        dynamicRecordRecycler = (RecyclerView) viewRoot.findViewById(R.id.dynamic_record_recycler);
        dynamicRecordRecycler.setLayoutManager(layoutManager);
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
        getVipFlg = (ImageView) viewRoot.findViewById(R.id.gvip);
        userIconFrm = (FrameLayout) viewRoot.findViewById(R.id.user_icon_frm);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(addDeleteFollowInterface);
    }

    private InnerCallBack userCenterCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (object instanceof ErrorCode) {

            } else if (object instanceof UserCenterResponse) {
                if (((UserCenterResponse) object).getError() == 0) {
                    if (!isAdded()) {
                        return;
                    }
                    //用户信息
                    userDataBean = ((UserCenterResponse) object).getData().getUser_data();
                    userName.setText(((UserCenterResponse) object).getData().getUser_data().getNickname());
                    String stepFormat = getContext().getResources().getString(R.string.user_target);
                    String stepStr = String.format(stepFormat, ((UserCenterResponse) object).getData().getUser_data().getTarget_step());
                    targerNum.setText(stepStr);
                    Presenter.getInstance(getContext()).getPlaceErrorImage(userHeadIco, ((UserCenterResponse) object).getData().getUser_data().getAvatar(),
                            R.drawable.default_head_ico, R.drawable.default_head_ico);
                    if (((UserCenterResponse) object).getData().getUser_data().getCity().equals(((UserCenterResponse) object).getData().getUser_data().getProvince())) {
                        locationCity.setText(((UserCenterResponse) object).getData().getUser_data().getCity());
                    } else {
                        if (!TextUtils.isEmpty(((UserCenterResponse) object).getData().getUser_data().getProvince())) {

                            if (specialCity(((UserCenterResponse) object).getData().getUser_data().getProvince())) {
                                locationCity.setText(((UserCenterResponse) object).getData().getUser_data().getCity());
                            } else {
                                locationCity.setText(((UserCenterResponse) object).getData().getUser_data().getProvince() + "· "
                                        + ((UserCenterResponse) object).getData().getUser_data().getCity());
                            }
                        } else {
                            locationCity.setText(((UserCenterResponse) object).getData().getUser_data().getCity());
                        }

                    }
                    if (((UserCenterResponse) object).getData().getUser_data().getVip() == 1
                            || ((UserCenterResponse) object).getData().getUser_data().getCusvip() == 1
                            || ((UserCenterResponse) object).getData().getUser_data().getGvip() == 1) {
                        if (((UserCenterResponse) object).getData().getUser_data().getGvip() == 0) {
                            if (((UserCenterResponse) object).getData().getUser_data().getCusvip() == 0) {
                                getVipFlg.setImageResource(R.drawable.vip_flg);
                            } else {
                                getVipFlg.setImageResource(R.drawable.vip_sponsor);
                            }
                        } else {
                            getVipFlg.setImageResource(R.drawable.golden_little);
                        }
                        userIconFrm.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.golden_back));
                        getVipFlg.setVisibility(View.VISIBLE);

                    } else {
                        getVipFlg.setVisibility(View.GONE);
                        userIconFrm.setBackground(null);
                    }
                    if (TextUtils.isEmpty(userNo) && userid != -1 && Presenter.getInstance(getContext()).getId() != userid) {
                        queryFollowStateParam.setFollowid(((UserCenterResponse) object).getData().getUser_data().getId());
                        if (((UserCenterResponse) object).getData().getIs_follow() == 0) {
                            LocalLog.d(TAG, "关注");
                            bntLike.setText("关注");
                        } else if (((UserCenterResponse) object).getData().getIs_follow() == 1) {
                            LocalLog.d(TAG, "已关注");
                            bntLike.setText("已关注");
//                            bntChat.setVisibility(View.VISIBLE);
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
                    }
                    try {
                        RongYunUserInfoManager.getInstance().refreshUserInfoDBAndCache(new ChatUserInfo(userDataBean.getId() + ""
                                , userDataBean.getNickname()
                                , userDataBean.getRemark_name()
                                , userDataBean.getAvatar()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    //关注状态
                    if (!TextUtils.isEmpty(userNo) && !userNo.equals(Presenter.getInstance(getContext()).getNo())) {
                        queryFollowStateParam.setFollowid(((UserCenterResponse) object).getData().getUser_data().getId());
                        if (((UserCenterResponse) object).getData().getIs_follow() == 0) {
                            LocalLog.d(TAG, "关注");
                            bntLike.setText("关注");
                        } else if (((UserCenterResponse) object).getData().getIs_follow() == 1) {
                            LocalLog.d(TAG, "已关注");
                            bntLike.setText("已关注");
//                            bntChat.setVisibility(View.VISIBLE);
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
                    }


                    //个人动态列表
                    if (((UserCenterResponse) object).getData().getDynamic_data() != null) {
                        if (((UserCenterResponse) object).getData().getDynamic_data().getData() != null &&
                                ((UserCenterResponse) object).getData().getDynamic_data().getData().size() > 0) {
                            pageCount = ((UserCenterResponse) object).getData().getDynamic_data().getPagenation().getTotalPage();
                            if (((UserCenterResponse) object).getData().getDynamic_data().getData() != null) {
                                List<List> map = checkDaysDynamic(((UserCenterResponse) object).getData().getDynamic_data());
                                if (dynamicRecordRecycler == null) {
                                    return;
                                }
                                dynamicRecordRecycler.addItemDecoration(new UserDynamicRecordAdapter.SpaceItemDecoration(45));
                                if (adapter == null) {
                                    adapter = new UserDynamicRecordAdapter(getContext(), map, UserCenterFragment.this);
                                    dynamicRecordRecycler.setAdapter(adapter);
                                }
                            }
                        }
                    }
                } else if (((UserCenterResponse) object).getError() == 100) {
                    exitTokenUnfect();
                } else {

                }
            }
        }
    };

    private boolean specialCity(String province) {
        if ("澳门".equals(province) || "台湾省".equals(province)
                || "香港".equals(province) || "重庆市".equals(province) || "上海市".equals(province)
                || "天津市".equals(province) || "北京市".equals(province)) {
            return true;
        }
        return false;
    }

/*

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
            Presenter.getInstance(getContext()).getPlaceErrorImage(userHeadIco, userInfoResponse.getData().getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
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

        }
    }
*/

 /*   @Override
    public void response(DynamicPersonResponse dynamicPersonResponse) {
        if (dynamicPersonResponse.getError() == 0) {
            LocalLog.d(TAG, "DynamicPersonResponse() enter " + dynamicPersonResponse.toString());
            pageCount = dynamicPersonResponse.getData().getPagenation().getTotalPage();
            if (dynamicPersonResponse.getData() != null) {
                List<List> map = checkDaysDynamic(dynamicPersonResponse);
                if (dynamicRecordRecycler == null) {
                    return;
                }
                dynamicRecordRecycler.addItemDecoration(new UserDynamicRecordAdapter.SpaceItemDecoration(45));
                if (adapter == null) {
                    adapter = new UserDynamicRecordAdapter(getContext(), map, this);
                    dynamicRecordRecycler.setAdapter(adapter);
                }
            }

        } else if (dynamicPersonResponse.getError() == -1) {
            if (pageIndex == 1) {
                LocalLog.d(TAG, "还没有动态!");
            }
        } else if (dynamicPersonResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }*/

    private List<List> checkDaysDynamic(UserCenterResponse.DataBeanX.DynamicDataBean dynamicDataBean) {
        LocalLog.d(TAG, "当前记录条数 " + dynamicDataBean.getData().size());
        String tempDays = "";
        List<List> map = new ArrayList<>();
        List<UserCenterResponse.DataBeanX.DynamicDataBean.DataBean> list = new ArrayList<>();
        for (int i = 0; i < dynamicDataBean.getData().size(); i++) {
            long create_time = dynamicDataBean.getData().get(i).getCreate_time();
            String create_timeStr = DateTimeUtil.formatFriendly(new Date(create_time * 1000));
            LocalLog.d(TAG, "create_timeStr = " + create_timeStr + ", tempdays =" + tempDays);
            if (i == 0) {
                LocalLog.d(TAG, "记录第一条时间");
                tempDays = create_timeStr;
                list.add(dynamicDataBean.getData().get(0));
                if (dynamicDataBean.getData().size() == 1) {
                    map.add(list);
                    break;
                }
            } else {
                if (create_timeStr.equals(tempDays)) {
                    list.add(dynamicDataBean.getData().get(i));
                } else {
                    LocalLog.d(TAG, tempDays + " 记录条数 " + list.size());
                    map.add(list);
                    list = new ArrayList<>();
                    list.add(dynamicDataBean.getData().get(i));
                    tempDays = create_timeStr;
                }
                if (i == dynamicDataBean.getData().size() - 1) {
                    LocalLog.d(TAG, tempDays + " 记录条数 " + list.size());
                    map.add(list);
                }
            }
        }
        return map;
    }

   /* @Override
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
            exitTokenUnfect();
        }
    }*/

    private AddDeleteFollowInterface addDeleteFollowInterface = new AddDeleteFollowInterface() {
        @Override
        public void response(AddDeleteFollowResponse addDeleteFollowResponse) {
            if (!isAdded()) {
                return;
            }
            if (addDeleteFollowResponse.getError() == 0) {
                if (addDeleteFollowResponse.getMessage().equals("取消关注成功")) {
                    bntLike.setText("关注");
                } else {
                    bntLike.setText("已关注");
//                    bntChat.setVisibility(View.VISIBLE);
                }
            } else if (addDeleteFollowResponse.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }

        @Override
        public void response(ErrorCode errorCode) {
            if (!isAdded()) {
                return;
            }
            if (errorCode.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_DETAIL) {

                if (data != null) {
                    final int topPosition = data.getIntExtra(getContext().getPackageName() + "topPosition", -1);
                    if (topPosition <= -1) {
                        return;
                    }
                    final int position = data.getIntExtra(getContext().getPackageName() + "position", -1);
                    if (position != -1) {
                        final int is_vote = data.getIntExtra(getContext().getPackageName() + "is_vote", -1);
                        if (is_vote != -1) {

                        }
                        final int likeNum = data.getIntExtra(getContext().getPackageName() + "likeNum", -1);
                        if (likeNum != -1) {

                        }
                        final int contentNum = data.getIntExtra(getContext().getPackageName() + "contentNum", -1);
                        if (contentNum != -1) {

                        }

                        LocalLog.d(TAG, "详情操作 is_vote = " + is_vote + ",likeNum = " + likeNum + ",contentNum = " + contentNum + "position = " + position);
                        if (is_vote != -1 || likeNum != -1 || contentNum != -1) {
                            if (adapter != null) {
                                adapter.notifyItemChange(topPosition, position, is_vote, likeNum, contentNum);
                            }
                        }
                    }
                }
            }
        }
    }

    @OnClick(R.id.bnt_chat)
    public void onClick() {
        RongYunChatUtils.getInstance().chatTo(getActivity(), Conversation.ConversationType.PRIVATE, userDataBean.getId() + "", userDataBean.getNickname());
    }
}
