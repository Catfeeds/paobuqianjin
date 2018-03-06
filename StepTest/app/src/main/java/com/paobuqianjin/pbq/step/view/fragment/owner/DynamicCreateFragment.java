package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReleaseDynamicResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.ReleaseDynamicInterface;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/9.
 */

public class DynamicCreateFragment extends BaseBarStyleTextViewFragment implements ReleaseDynamicInterface {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.dynamic_content)
    EditText dynamicContent;
    @Bind(R.id.pic_a)
    ImageView picA;
    @Bind(R.id.pic_b)
    ImageView picB;
    @Bind(R.id.pic_c)
    ImageView picC;
    @Bind(R.id.pic_d)
    ImageView picD;
    @Bind(R.id.pic_span)
    LinearLayout picSpan;
    @Bind(R.id.dynamic_span)
    RelativeLayout dynamicSpan;
    @Bind(R.id.line)
    ImageView line;
    @Bind(R.id.location_ico)
    ImageView locationIco;
    @Bind(R.id.location_span)
    RelativeLayout locationSpan;
    @Bind(R.id.line_width)
    ImageView lineWidth;
    @Bind(R.id.at)
    ImageView at;

    @Override
    protected String title() {
        return "编辑动态";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dynamic_create_fg;
    }

    @Override
    public Object right() {
        return "发布";
    }

    @Override
    public void response(ReleaseDynamicResponse releaseDynamicResponse) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        dynamicContent = (EditText) viewRoot.findViewById(R.id.dynamic_content);
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
}
