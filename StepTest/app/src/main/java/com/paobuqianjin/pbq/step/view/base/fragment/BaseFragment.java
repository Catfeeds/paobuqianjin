package com.paobuqianjin.pbq.step.view.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.activity.LoginActivity;

/**
 * Created by pbq on 2017/12/1.
 */

public abstract class BaseFragment extends Fragment {
    private static NormalDialog exitDialog;
    private final static String RE_LOGIN_ACTION = "com.paobuqianjin.pbq.step.RE_LOGIN";

    protected void runOnMainUi(Runnable runnable) {
        getActivity().runOnUiThread(runnable);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        initBarView(view);
        initView(view);
        return view;
    }

    protected void initBarView(View view) {

    }

    protected abstract int getLayoutResId();

    /*
        *@function startActivity
        *@param
        *@return
        */
    public void startActivity(Class<? extends Activity> target, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), target);
        if (bundle != null)
            intent.putExtra(getActivity().getPackageName(), bundle);
        getActivity().startActivity(intent);
    }

    public void startActivity(Class<? extends Activity> target, Parcelable parcelable) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), target);
        if (parcelable != null)
            intent.putExtra(getActivity().getPackageName(), parcelable);
        getActivity().startActivity(intent);
    }

    public void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void startActivity(Class<? extends Activity> target, Bundle bundle, boolean finish, String action) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), target);
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtra(getActivity().getPackageName(), bundle);
        }
        startActivity(intent);
        if (finish)
            getActivity().finish();
    }

    protected void initView(View viewRoot) {

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void exitTokenUnfect() {
        if (exitDialog == null) {
            if (getActivity() == null) {
                return;
            }
            exitDialog = new NormalDialog(getActivity());
            exitDialog.setMessage("登录过期，点击确定重新登录");
            exitDialog.setYesOnclickListener(getContext().getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    exitDialog.dismiss();
                    Presenter.getInstance(getContext()).setId(-1);
                    Presenter.getInstance(getContext()).steLogFlg(false);
                    Presenter.getInstance(getContext()).setToken(getContext(), "");
                    startActivity(LoginActivity.class, null, true, RE_LOGIN_ACTION);
                    exitDialog = null;
                }
            });
            exitDialog.setSingleBtn(true);
            if (getActivity() == null) {
                return;
            }
            if (!getActivity().isFinishing()) {
                exitDialog.show();
            }
        } else {
            if (!exitDialog.isShowing() && !getActivity().isFinishing()) {
                exitDialog.show();
            }
        }
    }
}
