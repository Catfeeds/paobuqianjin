package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/7/10.
 */

public class AccountChargeFragment extends BaseFragment {
    @Bind(R.id.help_title)
    TextView helpTitle;
    @Bind(R.id.help_des_a)
    TextView helpDesA;
    @Bind(R.id.help_des_b)
    TextView helpDesB;
    private final static String ACTION_RECHARGE_HELP = "com.paobuqianjin.pbq.step.RECHARGE_HELP";
    private final static String ACTION_ACCOUNT_HELP = "com.paobuqianjin.pbq.step.ACCOUNT_HELP";

    @Override
    protected int getLayoutResId() {
        return R.layout.qq_or_recharge_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initView(View viewRoot) {
        helpTitle = (TextView) viewRoot.findViewById(R.id.help_title);
        helpDesA = (TextView) viewRoot.findViewById(R.id.help_des_a);
        helpDesB = (TextView) viewRoot.findViewById(R.id.help_des_b);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (ACTION_RECHARGE_HELP.equals(intent.getAction())) {
                helpTitle.setText(R.string.recharge_help);
                helpDesA.setText(R.string.recharge_help_des);
            } else if (ACTION_ACCOUNT_HELP.equals(intent.getAction())) {
                helpTitle.setText(R.string.accout_help);
                helpDesA.setText(R.string.accout_help_desc);
                helpDesB.setText(R.string.accout_help_detail);
            }
        }
    }
}
