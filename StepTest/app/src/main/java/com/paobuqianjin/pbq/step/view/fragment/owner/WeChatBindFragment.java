package com.paobuqianjin.pbq.step.view.fragment.owner;

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
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.BindWeChatActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/2/7.
 */

public class WeChatBindFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = WeChatBindFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.account_crash)
    TextView accountCrash;
    @Bind(R.id.accout_list)
    RecyclerView accoutList;
    @Bind(R.id.add_account)
    ImageView addAccount;
    @Bind(R.id.add_wx)
    TextView addWx;
    @Bind(R.id.go_to)
    ImageView goTo;
    @Bind(R.id.add_account_span)
    RelativeLayout addAccountSpan;
    @Bind(R.id.corirm)
    Button corirm;

    @Override
    protected String title() {
        return "选择提现账号";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.we_chat_bind_fg;
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

    @OnClick({R.id.add_account_span, R.id.corirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_account_span:
                LocalLog.d(TAG, "添加绑定账户");
                startActivity(BindWeChatActivity.class,null,false,null);
                break;
            case R.id.corirm:
                break;
        }
    }
}
