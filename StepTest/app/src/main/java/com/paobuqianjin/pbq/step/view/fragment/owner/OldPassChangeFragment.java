package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
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

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostPassByOldParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.OldPassChangeResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.OlderPassInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/4/2.
 */

public class OldPassChangeFragment extends BaseBarStyleTextViewFragment implements OlderPassInterface {
    private final static String TAG = OldPassChangeFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.source_pass)
    EditText sourcePass;
    @Bind(R.id.older_pass)
    RelativeLayout olderPass;
    @Bind(R.id.new_pass)
    EditText newPass;
    @Bind(R.id.new_pass_pan)
    RelativeLayout newPassPan;
    @Bind(R.id.pass_new)
    EditText passNew;
    @Bind(R.id.new_pass_confirm)
    RelativeLayout newPassConfirm;
    @Bind(R.id.confirm_new_pass)
    Button confirmNewPass;

    PostPassByOldParam postPassByOldParam = new PostPassByOldParam();

    @Override
    protected int getLayoutResId() {
        return R.layout.older_pass_change_fg;
    }

    @Override
    protected String title() {
        return "修改密码";
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @OnClick(R.id.confirm_new_pass)
    public void onClick() {
        LocalLog.d(TAG, "确定修改");
        if ("".equals(sourcePass.getText().toString()) || "".equals(newPass.getText().toString()) || "".equals(passNew.getText().toString())) {
            Toast.makeText(getContext(), "旧密码或者新密码为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!newPass.getText().toString().equals(passNew.getText().toString())) {
            LocalLog.d(TAG, "两次输入的密码不一致");
            Toast.makeText(getContext(), "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        postPassByOldParam.setOld_password(sourcePass.getText().toString()).setNew_password(newPass.getText().toString());
        Presenter.getInstance(getContext()).postPassByOlder(postPassByOldParam);
    }

    @Override
    public void response(ErrorCode errorCode) {
        ToastUtils.showShortToast(getContext(), errorCode.getMessage());
    }

    @Override
    public void response(OldPassChangeResponse oldPassChangeResponse) {
        if (oldPassChangeResponse.getError() == 0) {
            LocalLog.d(TAG, "密码修改成功");
            ToastUtils.showLongToast(getContext(), oldPassChangeResponse.getMessage());
            getActivity().onBackPressed();
        } else if (oldPassChangeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            ToastUtils.showLongToast(getContext(), oldPassChangeResponse.getMessage());
        }
    }
}