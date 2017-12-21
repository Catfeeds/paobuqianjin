package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

/**
 * Created by pbq on 2017/12/1.
 */

public final class OwnerFragment extends BaseFragment {
    private TextView logoutTV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.owner_page;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        logoutTV = (TextView) viewRoot.findViewById(R.id.login_out);
        logoutTV.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view != null) {
                switch (view.getId()) {
                    case R.id.login_out:
                        Presenter.getInstance(getContext()).steLogFlg(false);
                        getActivity().finish();
                        break;
                }
            }

        }
    };
}
