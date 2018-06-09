package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.PassSetFirstFragment;
import com.paobuqianjin.pbq.step.view.fragment.owner.PassSetSecondFragment;
import com.paobuqianjin.pbq.step.view.fragment.owner.PassSetZeroFragment;

/**
 * Created by pbq on 2018/6/8.
 */

public class PayPassWordActivity extends BaseActivity {
    private final static String ACTION_FIRST_CARD = "com.paobuqianjin.pbq.step.ACTION_FIRST_CARD";
    private final static String ACTION_RESET = "com.paobuqianjin.pbq.step.ACTION_RESET";
    private final static String ACTION_RESET_BANK = "com.paobuqianjin.pbq.step.ACTION_RESET_BANK";
    private PassSetFirstFragment passSetFirstFragment = new PassSetFirstFragment();
    private PassSetSecondFragment passSetSecondFragment = new PassSetSecondFragment();
    private PassSetZeroFragment passSetZeroFragment = new PassSetZeroFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_psw_activity_layout);
    }

    public void showFirstSetPassWord(String psw) {

        if (!passSetFirstFragment.isAdded()) {
            if (passSetZeroFragment.isAdded()) {
                getSupportFragmentManager().beginTransaction()
                        .remove(passSetZeroFragment)
                        .add(R.id.pass_word_container, passSetFirstFragment)
                        .show(passSetFirstFragment)
                        .commit();
                passSetZeroFragment = null;
                passSetFirstFragment.setPsw(psw);
            } else {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.pass_word_container, passSetFirstFragment)
                        .show(passSetFirstFragment)
                        .commit();
            }

        } else {
            getSupportFragmentManager().beginTransaction()
                    .hide(passSetSecondFragment)
                    .show(passSetFirstFragment)
                    .commit();
        }
        passSetFirstFragment.cleanPassWord();

    }

    public void showSenCondPassWord(String pwd, String pws) {
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
        passSetSecondFragment.setPws(pwd, pws);
    }


    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            if (ACTION_FIRST_CARD.equals(intent.getAction()) || ACTION_RESET_BANK.equals(intent.getAction())) {
                showFirstSetPassWord("");
            } else if (ACTION_RESET.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.pass_word_container, passSetZeroFragment)
                        .show(passSetZeroFragment)
                        .commit();
            }

        }

    }

}
