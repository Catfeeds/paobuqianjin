package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.sponsor.ConsumRedDetailFragment;
import butterknife.ButterKnife;

public class GetConsumptiveRBResultActivity extends BaseActivity {
    private final static String TAG = GetConsumptiveRBResultActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_consumptive_rbresult);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        ConsumRedDetailFragment consumRedDetailFragment = new ConsumRedDetailFragment();
        if (!consumRedDetailFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.consum_red_container, consumRedDetailFragment)
                    .show(consumRedDetailFragment)
                    .commit();
        }
    }

}
