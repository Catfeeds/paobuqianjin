package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostInviteCodeParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostInviteCodeResponse;
import com.paobuqianjin.pbq.step.model.Engine;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.PostInviteCodeInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/2/27.
 */

public class FillInCodeFragment extends BaseBarStyleTextViewFragment implements PostInviteCodeInterface {
    private final static String TAG = FillInCodeFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.code_des)
    TextView codeDes;
    @Bind(R.id.code)
    EditText code;
    @Bind(R.id.line)
    ImageView line;
    @Bind(R.id.confirm_code)
    Button confirm;
    private PostInviteCodeParam postInviteCodeParam = new PostInviteCodeParam();

    @Override
    protected int getLayoutResId() {
        return R.layout.fill_code_fg;
    }

    @Override
    protected String title() {
        return "填写邀请码";
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
        code = (EditText) viewRoot.findViewById(R.id.code);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @OnClick(R.id.confirm_code)
    public void onClick() {
        LocalLog.d(TAG, "确定");
        if (checkPostInviteParam()) {
            postInviteCodeParam.setUserid(Presenter.getInstance(getContext()).getId()).setIcode(code.getText().toString());
            Presenter.getInstance(getContext()).postInviteCode(postInviteCodeParam);
        }

    }

    private boolean checkPostInviteParam() {
        LocalLog.d(TAG, code.getText().toString());
        if (TextUtils.isEmpty(code.getText().toString())) {
            Toast.makeText(getContext(), "请填写邀请码", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            postInviteCodeParam.setMobile(code.getText().toString());
            return true;
        }
    }

    @Override
    public void response(PostInviteCodeResponse postInviteCodeResponse) {
        LocalLog.d(TAG, "PostInviteCodeResponse() enter " + postInviteCodeResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (postInviteCodeResponse.getError() == 0) {
            ToastUtils.showLongToast(getContext(), postInviteCodeResponse.getMessage());
            getActivity().finish();
        } else {
            ToastUtils.showLongToast(getContext(), postInviteCodeResponse.getMessage());
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
        } else {

        }
    }
}
