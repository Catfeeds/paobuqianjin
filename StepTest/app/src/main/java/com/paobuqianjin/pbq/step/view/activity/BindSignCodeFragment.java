package com.paobuqianjin.pbq.step.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.BindCardPostParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CheckSignCodeParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.BindAccountResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CheckSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.SignCodeInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/2/7.
 */

public class BindSignCodeFragment extends BaseBarStyleTextViewFragment implements SignCodeInterface {
    private final static String TAG = BindSignCodeFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.phone_text)
    TextView phoneText;
    @Bind(R.id.line1)
    ImageView line1;
    @Bind(R.id.sign_code_text)
    TextView signCodeText;
    @Bind(R.id.sign_code)
    EditText signCode;
    @Bind(R.id.sign_code_get)
    TextView signCodeGet;
    @Bind(R.id.line2)
    ImageView line2;
    @Bind(R.id.confirm)
    Button confirm;
    @Bind(R.id.phone_nums)
    TextView phoneNums;
    private String openid;
    private String screen_name;

    @Override
    protected int getLayoutResId() {
        return R.layout.bind_wx_phone_sign_code_fg;
    }

    @Override
    protected String title() {
        return "绑定微信";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(context).attachUiInterface(this);
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
        LocalLog.d(TAG, "initView() enter ");
        phoneNums = (TextView) viewRoot.findViewById(R.id.phone_nums);
        phoneNums.setText(Presenter.getInstance(getContext()).getMobile(getContext()));
        signCode = (EditText) viewRoot.findViewById(R.id.sign_code);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(getContext().getPackageName());
            if (bundle != null) {
                openid = bundle.getString("openid");
                screen_name = bundle.getString("screen_name");
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(GetSignCodeResponse getSignCodeResponse) {
        LocalLog.d(TAG, "GetSignCodeResponse() enter");
    }

    @Override
    public void response(CheckSignCodeResponse checkSignCodeResponse) {
        LocalLog.d(TAG, "CheckSignCodeResponse() enter 短信校验成功");
        BindCardPostParam bindCardPostParam = new BindCardPostParam();
        bindCardPostParam.setTypeid(1)
                .setBank_card(openid)
                .setRealname(screen_name);

        Presenter.getInstance(getContext()).bindCrashAccount(bindCardPostParam);
    }

    @Override
    public void response(BindAccountResponse bindAccoutResponse) {
        LocalLog.d(TAG, "BindAccountResponse() enter");
    }

    @OnClick({R.id.sign_code_get, R.id.confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_code_get:
                LocalLog.d(TAG, "获取验证码");
                Presenter.getInstance(getContext()).getSignCode(Presenter.getInstance(getContext()).getMobile(getContext()));
                break;
            case R.id.confirm:
                LocalLog.d(TAG, "下一步");
                if (signCode.getText() == null || signCode.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                CheckSignCodeParam checkSignCodeParam = new CheckSignCodeParam();
                checkSignCodeParam.setCode(signCode.getText().toString());
                checkSignCodeParam.setUserid(Presenter.getInstance(getContext()).getId());
                Presenter.getInstance(getContext()).checkSignCode(checkSignCodeParam);
                break;
        }
    }
}
