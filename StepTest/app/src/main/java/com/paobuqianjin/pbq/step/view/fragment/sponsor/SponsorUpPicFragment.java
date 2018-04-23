package com.paobuqianjin.pbq.step.view.fragment.sponsor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.task.GridAdpter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/4/21.
 */

public class SponsorUpPicFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = SponsorUpPicFragment.class.getSimpleName();
    private final static String ACTION_INNER_PIC = "com.paobuqianjin.pbq.step.INNER_ACTION";
    private final static String ACTION_OUT_PIC = "com.paobuqianjin.pbq.step.OUT_ACTION";
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.desc)
    TextView desc;
    @Bind(R.id.pic_grid_view)
    RelativeLayout picGridView;
    @Bind(R.id.grid_view)
    GridView gridView;
    String title;

    @Override
    protected int getLayoutResId() {
        return R.layout.sponsor_upload_fg;
    }

    @Override
    protected String title() {
        Intent intent = getActivity().getIntent();
        if (ACTION_INNER_PIC.equals(intent.getAction())) {
            title = "门店照片";
            return title;
        } else if (ACTION_OUT_PIC.equals(intent.getAction())) {
            title = "店内环境";
            return title;
        } else {
            return null;
        }
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
        gridView = (GridView) viewRoot.findViewById(R.id.grid_view);
        gridView.setAdapter(new GridAdpter(getContext(), null));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LocalLog.d(TAG, "position =  " + position);

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
