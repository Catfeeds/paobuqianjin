package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.PassSetFirstFragment;
import com.paobuqianjin.pbq.step.view.fragment.owner.PassSetSecondFragment;

/**
 * Created by pbq on 2018/6/8.
 */

public class PayPassWordActivity extends BaseActivity {
    private PassSetFirstFragment passSetFirstFragment = new PassSetFirstFragment();
    private PassSetSecondFragment passSetSecondFragment = new PassSetSecondFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_psw_activity_layout);
    }

    public void showFirstSetPassWord() {
        if (!passSetFirstFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.pass_word_container, passSetFirstFragment)
                    .show(passSetFirstFragment)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .hide(passSetSecondFragment)
                    .show(passSetFirstFragment)
                    .commit();
        }
        passSetFirstFragment.cleanPassWord();

    }

    public void showSenCondPassWord(String pwd) {
        if (!passSetSecondFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .hide(passSetFirstFragment)
                    .add(R.id.pass_word_container, passSetSecondFragment)
                    .show(passSetSecondFragment)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .hide(passSetFirstFragment)
                    .show(passSetSecondFragment)
                    .commit();
        }
        passSetSecondFragment.setPws(pwd);
    }

    @Override
    protected void initView() {
        showFirstSetPassWord();
    }

}
