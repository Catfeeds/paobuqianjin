package com.paobuqianjin.pbq.step.view.fragment.owner;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/1/15.
 */

public class MyWalletFragment extends BaseBarStyleTextViewFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.my_wallet_fg;
    }

    @Override
    protected String title() {
        return "我的钱包";
    }
}
