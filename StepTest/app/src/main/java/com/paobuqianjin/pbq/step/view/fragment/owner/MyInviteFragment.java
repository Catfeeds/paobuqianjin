package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyInviteResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.AgreementActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/2/27.
 */

public class MyInviteFragment extends BaseFragment {
    private final static String TAG = MyInviteFragment.class.getSimpleName();
    private final static String USER_INVITE_AGREEMENT_ACTION = "com.paobuqianjin.step.pbq.INVITE_ACTION";
    ImageView invitePkg;
    TextView invitNum;
    TextView inviteNumDes;
    TextView invitMoney;
    TextView inviteMoneyDes;
    TextView invitStepDollar;
    TextView inviteStepDes;
    RelativeLayout inviteResultPan;
    TextView desc;
    TextView inviteRule;
    TextView inviteCode;
    LinearLayout goRuleSpan;

    private int pageIndex = 1, pageCount = 0;
    private final static int PAGESIZE = 200;

    @Override
    protected int getLayoutResId() {
        return R.layout.my_invite_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }


    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        inviteCode = (TextView) viewRoot.findViewById(R.id.invite_code);
        invitNum = (TextView) viewRoot.findViewById(R.id.invit_num);
        invitMoney = (TextView) viewRoot.findViewById(R.id.invit_money);
        invitStepDollar = (TextView) viewRoot.findViewById(R.id.invit_step_dollar);
        inviteResultPan = (RelativeLayout) viewRoot.findViewById(R.id.go_rule_span);
        inviteResultPan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AgreementActivity.class, null, false, USER_INVITE_AGREEMENT_ACTION);
            }
        });
        update();
    }

    public void update() {
        if (isAdded()) {
            Presenter.getInstance(getContext()).getMyInviteMsg(innerCallBack, pageIndex, PAGESIZE);
            Presenter.getInstance(getContext()).getMyCode(innerCallBack);
        }
    }

    private InnerCallBack innerCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (!isAdded()) {
                return;
            }
            if (object instanceof ErrorCode) {
                PaoToastUtils.showLongToast(getContext(), ((ErrorCode) object).getMessage());
            } else if (object instanceof InviteCodeResponse) {
                if (inviteCode != null) {
                    if (((InviteCodeResponse) object).getError() == 0) {
                        if (((InviteCodeResponse) object).getData() != null) {
                            inviteCode.setText("我的邀请码:" + ((InviteCodeResponse) object).getData().getMycode());

                        }
                    }
                }

            } else if (object instanceof MyInviteResponse) {
                if (((MyInviteResponse) object).getError() == 100) {
                    exitTokenUnfect();
                } else if (((MyInviteResponse) object).getError() == 0) {
                    if (invitNum != null && ((MyInviteResponse) object).getData() != null) {
                        invitNum.setText(String.valueOf(((MyInviteResponse) object).getData().getInumber()));
                        invitMoney.setText(String.valueOf(((MyInviteResponse) object).getData().getImoney()));
                        invitStepDollar.setText(String.valueOf(((MyInviteResponse) object).getData().getIcredit()));
                    }
                } else {
                    PaoToastUtils.showLongToast(getContext(), ((MyInviteResponse) object).getMessage());
                }
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
