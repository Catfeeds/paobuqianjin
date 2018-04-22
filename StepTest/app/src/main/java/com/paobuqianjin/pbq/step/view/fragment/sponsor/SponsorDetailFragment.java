package com.paobuqianjin.pbq.step.view.fragment.sponsor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorRedPkgResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.SponsorGoodsPicLookActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/23.
 */

public class SponsorDetailFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = SponsorDetailFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.sponsor_images)
    ViewPager sponsorImages;
    @Bind(R.id.pic_sample)
    ImageView picSample;
    @Bind(R.id.current_pic)
    TextView currentPic;
    @Bind(R.id.sponsor_face_span)
    RelativeLayout sponsorFaceSpan;
    @Bind(R.id.line)
    ImageView line;
    @Bind(R.id.sponsor_tel_icon)
    ImageView sponsorTelIcon;
    @Bind(R.id.sponsor_tel_num)
    TextView sponsorTelNum;
    @Bind(R.id.sponsor_tel_span)
    RelativeLayout sponsorTelSpan;
    @Bind(R.id.sponsor_time_icon)
    ImageView sponsorTimeIcon;
    @Bind(R.id.sponsor_time_num)
    TextView sponsorTimeNum;
    @Bind(R.id.sponsor_time_span)
    RelativeLayout sponsorTimeSpan;
    @Bind(R.id.sponsor_location_icon)
    ImageView sponsorLocationIcon;
    @Bind(R.id.sponsor_location_num)
    TextView sponsorLocationNum;
    @Bind(R.id.sponsor_location_span)
    RelativeLayout sponsorLocationSpan;
    @Bind(R.id.line2)
    ImageView line2;
    @Bind(R.id.sponsor_have)
    TextView sponsorHave;
    @Bind(R.id.go_to)
    ImageView goTo;
    @Bind(R.id.goto_sponsor)
    RelativeLayout gotoSponsor;
    @Bind(R.id.center_pic)
    ImageView centerPic;
    SponsorRedPkgResponse.DataBeanX.DataBean dataBean;
    @Bind(R.id.sponsor_tel_num_str)
    TextView sponsorTelNumStr;
    @Bind(R.id.sponsor_time_num_str)
    TextView sponsorTimeNumStr;
    @Bind(R.id.location_str)
    TextView locationStr;
    @Bind(R.id.more_str)
    TextView moreStr;
    @Bind(R.id.goods_a)
    ImageView goodsA;
    @Bind(R.id.goods_b)
    ImageView goodsB;
    List<View> Mview = new ArrayList<>();
    private final static String SHOW_SPONSOR_PICS_ACTION = "com.paobuqianjin.pbq.step.SHOW_PIC_ACTION";

    @Override
    protected String title() {
        return "商家详情";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.sponsor_detail_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        if (dataBean != null) {
            setTitle(dataBean.getName());
        }
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        sponsorTelNumStr = (TextView) viewRoot.findViewById(R.id.sponsor_tel_num_str);
        sponsorTimeNumStr = (TextView) viewRoot.findViewById(R.id.sponsor_time_num_str);
        locationStr = (TextView) viewRoot.findViewById(R.id.location_str);
        currentPic = (TextView) viewRoot.findViewById(R.id.current_pic);
        centerPic = (ImageView) viewRoot.findViewById(R.id.center_pic);
        goodsA = (ImageView) viewRoot.findViewById(R.id.goods_a);
        goodsB = (ImageView) viewRoot.findViewById(R.id.goods_b);
        gotoSponsor = (RelativeLayout) viewRoot.findViewById(R.id.goto_sponsor);
        sponsorImages = (ViewPager) viewRoot.findViewById(R.id.sponsor_images);

        gotoSponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "查看更多");
                Intent intent = new Intent();
                intent.setAction(SHOW_SPONSOR_PICS_ACTION);
                intent.setClass(getContext(), SponsorGoodsPicLookActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getActivity().getIntent();
        Mview.clear();
        if (intent != null) {
            dataBean = (SponsorRedPkgResponse.DataBeanX.DataBean) intent.getSerializableExtra(getContext().getPackageName());
            if (dataBean != null) {
                LocalLog.d(TAG, "dataBean  =  " + dataBean.toString());
                sponsorTelNumStr.setText(dataBean.getTel());
                sponsorTimeNumStr.setText(dataBean.getHours());
                locationStr.setText(dataBean.getAddress());

                int sizeEnv = 0;


                if (dataBean.getEnvironment_images() != null) {
                    sizeEnv = dataBean.getEnvironment_images().size();
                    for (int i = 0; i < sizeEnv; i++) {
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.sponsor_image_view, null);
                        ImageView imageView = (ImageView) view.findViewById(R.id.sponsor_env_img);
                        Presenter.getInstance(getContext()).getImage(imageView, dataBean.getEnvironment_images().get(i));
                        Mview.add(view);
                    }
                }
                if (Mview.size() > 0) {

                    sponsorImages.setAdapter(new ImageViewPagerAdapter(getContext(), Mview));
                    sponsorImages.addOnPageChangeListener(onPageChangeListener);
                }
                int size = 0;
                if (dataBean.getGoods_images() != null) {
                    size = dataBean.getGoods_images().size();
                    if (size == 1) {
                        goodsA.setVisibility(View.VISIBLE);
                        Presenter.getInstance(getContext()).getImage(goodsA, dataBean.getGoods_images().get(0));
                    } else if (size == 2) {
                        goodsA.setVisibility(View.VISIBLE);
                        centerPic.setVisibility(View.VISIBLE);
                        Presenter.getInstance(getContext()).getImage(goodsA, dataBean.getGoods_images().get(0));
                        Presenter.getInstance(getContext()).getImage(centerPic, dataBean.getGoods_images().get(1));
                    } else if (size >= 3) {

                        goodsA.setVisibility(View.VISIBLE);
                        centerPic.setVisibility(View.VISIBLE);
                        goodsB.setVisibility(View.VISIBLE);
                        Presenter.getInstance(getContext()).getImage(goodsA, dataBean.getGoods_images().get(0));
                        Presenter.getInstance(getContext()).getImage(centerPic, dataBean.getGoods_images().get(1));
                        Presenter.getInstance(getContext()).getImage(goodsB, dataBean.getGoods_images().get(2));
                    }
                }
            }
        }

    }


    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            LocalLog.d(TAG, "position = " + position);
            currentPic.setText(String.valueOf(position + 1) + "/" + Mview.size());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
