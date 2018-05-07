package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteCodeResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.view.activity.AgreementActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/2/27.
 */

public class MyInviteFragment extends BaseFragment {
    @Bind(R.id.invite_pkg)
    ImageView invitePkg;
    @Bind(R.id.invite_result)
    TextView inviteResult;
    @Bind(R.id.desc)
    TextView desc;
    @Bind(R.id.invite_rule)
    TextView inviteRule;
    @Bind(R.id.go_rule_span)
    RelativeLayout goRuleSpan;
    @Bind(R.id.invite_code_rel)
    RelativeLayout inviteCodeRel;
    @Bind(R.id.invite_code)
    TextView inviteCode;
    private final static String USER_INVITE_AGREEMENT_ACTION = "com.paobuqianjin.step.pbq.INVITE_ACTION";

    @Override
    protected int getLayoutResId() {
        return R.layout.my_invite_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void setMsg(int inviteNum, int stepDollar) {
        if (isDetached()) {
            return;
        } else {
            String inviteStrformat = getString(R.string.invite_msg);
            String inviteStr = String.format(inviteStrformat, inviteNum, stepDollar);
            inviteResult.setText(inviteStr);
            /*inviteCode.setText("我的邀请码:" + code);*/
        }
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        inviteResult = (TextView) viewRoot.findViewById(R.id.invite_result);
        inviteCode = (TextView) viewRoot.findViewById(R.id.invite_code);
        Presenter.getInstance(getContext()).getMyCode(innerCallBack);
    }

    private InnerCallBack innerCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (object instanceof ErrorCode) {

            } else if (object instanceof InviteCodeResponse) {
                if (inviteCode != null) {
                    if (((InviteCodeResponse) object).getError() == 0) {
                        if (((InviteCodeResponse) object).getData() != null) {
                            int size = ((InviteCodeResponse) object).getData().size();
                            if (size >= 1) {
                                inviteCode.setText("我的邀请码:" + ((InviteCodeResponse) object).getData().get(1));
                            }
                        }
                    }
                }

            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.go_rule_span)
    public void onClick() {
        startActivity(AgreementActivity.class, null, false, USER_INVITE_AGREEMENT_ACTION);
    }
}
