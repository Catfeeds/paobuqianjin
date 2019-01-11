package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChatUserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFollowOtOResponse;
import com.paobuqianjin.pbq.step.data.bean.table.ChatUserInfo;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.RongYunChatUtils;
import com.paobuqianjin.pbq.step.utils.RongYunUserInfoManager;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.rong.imlib.model.Conversation;

public class FriendDetailActivity extends BaseBarActivity {
    private final static String TAG = FriendDetailActivity.class.getSimpleName();
    private static final int REQ_CHANGE_REMARK = 1;
    @Bind(R.id.rl_bar)
    RelativeLayout rlBar;
    @Bind(R.id.iv_icon)
    CircleImageView ivIcon;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_number)
    TextView tvNumber;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.tv_attendtion)
    TextView tvAttendtion;
    @Bind(R.id.linear_mark)
    LinearLayout linearMark;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.iv_img1)
    ImageView ivImg1;
    @Bind(R.id.iv_img3)
    ImageView ivImg3;
    @Bind(R.id.iv_img2)
    ImageView ivImg2;
    @Bind(R.id.linear_dynamic)
    LinearLayout linearDynamic;
    @Bind(R.id.btn_confirm)
    Button btnConfirm;
    private int userId;
    private FriendDetailResponse.DataBean detailBean;
    private String userNo;

    @Override
    protected String title() {
        return "个人资料";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
        ButterKnife.bind(this);


        userId = getIntent().getIntExtra("userid", -1);
        userNo = getIntent().getStringExtra("userno");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (userId < 0 && TextUtils.isEmpty(userNo)) {
            finish();
        } else {
            initData();
        }
    }

    private void initData() {
        Map<String, String> params = new HashMap<>();
        if (userId > -1) {
            params.put("otherid", userId + "");
        } else {
            params.put("otherno", userNo + "");
        }
        Presenter.getInstance(this).getPaoBuSimple(NetApi.getFriendDetail, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {

                FriendDetailResponse friendDetailResponse = new Gson().fromJson(s, FriendDetailResponse.class);
                detailBean = friendDetailResponse.getData();
                userId = Integer.parseInt(detailBean.getUserid());
                Presenter.getInstance(FriendDetailActivity.this).getPlaceErrorImage(ivIcon, detailBean.getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
                tvName.setText(detailBean.getNickname());
                tvNumber.setText(getString(R.string.user_no_x, detailBean.getUserno()));
                tvAddress.setText(detailBean.getProvince() + detailBean.getCity());
                List<FriendDetailResponse.DynamicOnlyImg> list = detailBean.getDynamic();
                for (FriendDetailResponse.DynamicOnlyImg dImg :
                        list) {
                    if (!TextUtils.isEmpty(dImg.getOneimg())) {
                        if (ivImg1.getTag() == null) {
                            ivImg1.setTag("1");
                            ivImg1.setVisibility(View.VISIBLE);
                            Presenter.getInstance(FriendDetailActivity.this).getImage(ivImg1, dImg.getOneimg());
                        } else if (ivImg2.getTag() == null && list.size() > 1) {
                            ivImg2.setTag("1");
                            ivImg2.setVisibility(View.VISIBLE);
                            Presenter.getInstance(FriendDetailActivity.this).getImage(ivImg2, dImg.getOneimg());
                        } else if (list.size() > 2) {
                            ivImg3.setTag("1");
                            ivImg3.setVisibility(View.VISIBLE);
                            Presenter.getInstance(FriendDetailActivity.this).getImage(ivImg3, dImg.getOneimg());
                        }
                    }

                }
                switch (detailBean.getIs_follow()) {
                    case 0:
                        btnConfirm.setBackgroundResource(R.drawable.shape_bg_round5_purple);
                        tvNickname.setVisibility(View.GONE);
                        linearMark.setVisibility(View.GONE);
                        tvAttendtion.setText(R.string.attention);
                        tvAttendtion.setBackgroundResource(R.drawable.shape_14_dp_purple);
                        tvAttendtion.setTextColor(getResources().getColor(R.color.color_232433));
                        break;
                    case 1:
                        btnConfirm.setBackgroundResource(R.drawable.shape_bg_round5_purple);
                        tvNickname.setVisibility(View.GONE);
                        linearMark.setVisibility(View.GONE);
                        tvAttendtion.setText(R.string.attentioned);
                        tvAttendtion.setBackgroundResource(R.drawable.shape_14_dp_gray);
                        tvAttendtion.setTextColor(getResources().getColor(R.color.color_9e9e9e));
                        break;
                    case 2:
                        btnConfirm.setBackgroundResource(R.drawable.shape_bg_round5_purple);
                        linearMark.setVisibility(View.VISIBLE);
                        tvAttendtion.setText(R.string.attentioned);
                        tvAttendtion.setBackgroundResource(R.drawable.shape_14_dp_gray);
                        tvAttendtion.setTextColor(getResources().getColor(R.color.color_9e9e9e));

                        if (!TextUtils.isEmpty(detailBean.getRemark_name())) {
                            tvNickname.setVisibility(View.VISIBLE);
                            tvNickname.setText(getString(R.string.nickname_x, detailBean.getNickname()));
                            tvName.setText(detailBean.getRemark_name());
                        } else {
                            tvNickname.setVisibility(View.GONE);
                        }
                        break;
                }
                RongYunUserInfoManager.getInstance().getServerUserInfoById(userId + "");
            }
        });
    }


    @OnClick({R.id.linear_mark, R.id.linear_dynamic, R.id.btn_confirm, R.id.tv_attendtion})
    public void onClick(View view) {
        if (detailBean == null) return;
        switch (view.getId()) {
            case R.id.linear_mark:
                startActivityForResult(new Intent(this, SetRemarkNameActivity.class).putExtra("userid", userId + ""), REQ_CHANGE_REMARK);
                break;
            case R.id.linear_dynamic:
                Intent intent = getIntent();
                intent.putExtra("userid", userId);
                intent.setClass(this, UserCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_confirm:
                switch (detailBean.getIs_follow()) {
                    case 0:
/*                        PaoToastUtils.showShortToast(this,getString(R.string.tips_must_attend));
                        break;*/
                    case 1:
/*                        PaoToastUtils.showShortToast(this,getString(R.string.tips_must_be_attended));
                        break;*/
                    case 2:
                        String title = TextUtils.isEmpty(detailBean.getRemark_name()) ? detailBean.getNickname() : detailBean.getRemark_name();
                        RongYunChatUtils.getInstance().chatTo(FriendDetailActivity.this
                                , Conversation.ConversationType.PRIVATE
                                , userId + ""
                                , title);
                        break;
                }
                break;
            case R.id.tv_attendtion:
                changeAttendtion();
                break;
        }
    }

    private void changeAttendtion() {
        showLoadingBar();
        Map<String, String> param = new HashMap<>();
        param.put("userid", String.valueOf(Presenter.getInstance(this).getId()));
        param.put("followid", userId + "");
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlUserFollow, param, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                hideLoadingBar();
                initData();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);
                hideLoadingBar();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CHANGE_REMARK && resultCode == RESULT_OK) {
            data.getStringExtra("intent_remark");
            initData();
        } else {
            setResult(resultCode);
            finish();
        }
    }
}