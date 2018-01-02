package com.paobuqianjin.pbq.step.view.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.activity.QrCodeMakeActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

/**
 * Created by pbq on 2017/12/1.
 */

public final class HomePageFragment extends BaseFragment {
    Button button ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.home_page;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        button = (Button) viewRoot.findViewById(R.id.bnt_share);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(QrCodeMakeActivity.class,null);
            }
        });
    }
}
