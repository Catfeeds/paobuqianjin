package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTagResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.TagFragInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2017/12/20.
 */

public class CircleTagFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = CircleTagFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.hot_sport_flag)
    TextView hotSportFlag;
    @Bind(R.id.flag_a0)
    TextView flagA0;
    @Bind(R.id.flag_a1)
    TextView flagA1;
    @Bind(R.id.flag_a2)
    TextView flagA2;
    @Bind(R.id.flag_first_line)
    RelativeLayout flagFirstLine;
    @Bind(R.id.flag_b0)
    TextView flagB0;
    @Bind(R.id.flag_b1)
    TextView flagB1;
    @Bind(R.id.flag_b2)
    TextView flagB2;
    @Bind(R.id.flag_b3)
    TextView flagB3;
    @Bind(R.id.flag_second_line)
    RelativeLayout flagSecondLine;
    @Bind(R.id.hot_flags)
    RelativeLayout hotFlags;
    @Bind(R.id.sep_line)
    ImageView sepLine;
    @Bind(R.id.all_flags_text)
    TextView allFlagsText;
    @Bind(R.id.flag_all_a0)
    TextView flagAllA0;
    @Bind(R.id.flag_all_a1)
    TextView flagAllA1;
    @Bind(R.id.flag_all_a2)
    TextView flagAllA2;
    @Bind(R.id.flag_all_a3)
    TextView flagAllA3;
    @Bind(R.id.flag_first_all_line)
    RelativeLayout flagFirstAllLine;
    @Bind(R.id.flag_all_b0)
    TextView flagAllB0;
    @Bind(R.id.flag_all_b1)
    TextView flagAllB1;
    @Bind(R.id.flag_all_b2)
    TextView flagAllB2;
    @Bind(R.id.flag_all_b3)
    TextView flagAllB3;
    @Bind(R.id.flag_second_all_line)
    RelativeLayout flagSecondAllLine;
    @Bind(R.id.flag_all_c0)
    TextView flagAllC0;
    @Bind(R.id.flag_all_c1)
    TextView flagAllC1;
    @Bind(R.id.flag_all_c2)
    TextView flagAllC2;
    @Bind(R.id.flag_third_all_line)
    RelativeLayout flagThirdAllLine;
    @Bind(R.id.all_flags)
    RelativeLayout allFlags;
    private ArrayList<TextView> textViewsHot = new ArrayList<>();
    private ArrayList<TextView> textViewAll = new ArrayList<>();
    private int selectTag = 0;
    private boolean[] selectFlag = new boolean[18];
    private HashMap<String, String> tableTag = new HashMap<>();

    public CircleTagFragment() {
        super();
    }


    @Override
    protected String title() {
        return "添加标签";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_flag_fragment;
    }

    @Override
    public Object right() {
        return "完成";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        Presenter.getInstance(getContext()).attachUiInterface(tagFragInterface);
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        flagA0 = (TextView) viewRoot.findViewById(R.id.flag_a0);
        flagA1 = (TextView) viewRoot.findViewById(R.id.flag_a1);
        flagA2 = (TextView) viewRoot.findViewById(R.id.flag_a2);

        flagB0 = (TextView) viewRoot.findViewById(R.id.flag_b0);
        flagB1 = (TextView) viewRoot.findViewById(R.id.flag_b1);
        flagB2 = (TextView) viewRoot.findViewById(R.id.flag_b2);
        flagB3 = (TextView) viewRoot.findViewById(R.id.flag_b3);

        textViewsHot.add(flagA0);
        textViewsHot.add(flagA1);
        textViewsHot.add(flagA2);
        textViewsHot.add(flagB0);
        textViewsHot.add(flagB1);
        textViewsHot.add(flagB2);
        textViewsHot.add(flagB3);

        flagAllA0 = (TextView) viewRoot.findViewById(R.id.flag_all_a0);
        flagAllA1 = (TextView) viewRoot.findViewById(R.id.flag_all_a1);
        flagAllA2 = (TextView) viewRoot.findViewById(R.id.flag_all_a2);
        flagAllA3 = (TextView) viewRoot.findViewById(R.id.flag_all_a3);
        flagAllB0 = (TextView) viewRoot.findViewById(R.id.flag_all_b0);
        flagAllB1 = (TextView) viewRoot.findViewById(R.id.flag_all_b1);
        flagAllB2 = (TextView) viewRoot.findViewById(R.id.flag_all_b2);
        flagAllB3 = (TextView) viewRoot.findViewById(R.id.flag_all_b3);


        flagAllC0 = (TextView) viewRoot.findViewById(R.id.flag_all_c0);
        flagAllC1 = (TextView) viewRoot.findViewById(R.id.flag_all_c1);
        flagAllC2 = (TextView) viewRoot.findViewById(R.id.flag_all_c2);

        textViewAll.add(flagAllA0);
        textViewAll.add(flagAllA1);
        textViewAll.add(flagAllA2);
        textViewAll.add(flagAllA3);
        textViewAll.add(flagAllB0);
        textViewAll.add(flagAllB1);
        textViewAll.add(flagAllB2);
        textViewAll.add(flagAllB3);
        textViewAll.add(flagAllC0);
        textViewAll.add(flagAllC1);
        textViewAll.add(flagAllC2);
        Presenter.getInstance(getContext()).getCircleTag();
        setToolBarListener(toolBarListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(tagFragInterface);
    }

    private TagFragInterface tagFragInterface = new TagFragInterface() {
        @Override
        public void response(CircleTagResponse circleTagResponse) {
            LocalLog.d(TAG, "CircleTagResponse() enter");
            int booleanSize = circleTagResponse.getData().getAllCircleTags().size() + circleTagResponse.getData().getHotCircleTags().size();
            for (int i = 0; i < circleTagResponse.getData().getHotCircleTags().size(); i++) {
                if (textViewsHot.size() <= circleTagResponse.getData().getHotCircleTags().size()) {
                    for (int j = 0; j < textViewsHot.size(); j++) {
                        textViewsHot.get(j).setVisibility(View.VISIBLE);
                        textViewsHot.get(j).setText(circleTagResponse.getData().getHotCircleTags().get(j).getName());
                    }
                } else {
                    for (int j = 0; j < circleTagResponse.getData().getHotCircleTags().size(); j++) {
                        textViewsHot.get(j).setText(circleTagResponse.getData().getHotCircleTags().get(j).getName());
                    }
                }

                tableTag.put(circleTagResponse.getData().getHotCircleTags().get(i).getName(),
                        String.valueOf(circleTagResponse.getData().getHotCircleTags().get(i).getId()));
            }

            for (int i = 0; i < circleTagResponse.getData().getAllCircleTags().size(); i++) {
                if (textViewAll.size() <= circleTagResponse.getData().getAllCircleTags().size()) {
                    for (int j = 0; j < textViewAll.size(); j++) {
                        textViewAll.get(j).setVisibility(View.VISIBLE);
                        textViewAll.get(j).setText(circleTagResponse.getData().getAllCircleTags().get(j).getName());
                    }
                } else {
                    for (int j = 0; j < circleTagResponse.getData().getAllCircleTags().size(); j++) {
                        textViewAll.get(j).setText(circleTagResponse.getData().getAllCircleTags().get(j).getName());
                    }
                }

                tableTag.put(circleTagResponse.getData().getAllCircleTags().get(i).getName(),
                        String.valueOf(circleTagResponse.getData().getAllCircleTags().get(i).getId()));
            }


        }

        @Override
        public void response(ErrorCode errorCode) {
            if (errorCode.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                Presenter.getInstance(getContext()).setId(-1);
                Presenter.getInstance(getContext()).steLogFlg(false);
                Presenter.getInstance(getContext()).setToken(getContext(), "");
                getActivity().finish();
                System.exit(0);
            }
        }
    };

    public void toast() {
        Toast.makeText(getContext(), "最多选择2个标签", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.flag_a0, R.id.flag_a1, R.id.flag_a2, R.id.flag_b0, R.id.flag_b1, R.id.flag_b2, R.id.flag_b3, R.id.flag_all_a0, R.id.flag_all_a1, R.id.flag_all_a2, R.id.flag_all_a3, R.id.flag_all_b0, R.id.flag_all_b1, R.id.flag_all_b2, R.id.flag_all_b3, R.id.flag_all_c0, R.id.flag_all_c1, R.id.flag_all_c2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.flag_a0:
                if (selectTag < 2) {
                    if (!selectFlag[0]) {
                        LocalLog.d(TAG, "选种1");
                        selectTag++;
                        flagA0.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        flagA0.setTypeface(Typeface.DEFAULT_BOLD);
                        selectFlag[0] = true;
                        flagA0.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagA0.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        flagA0.setTypeface(Typeface.DEFAULT);
                        selectFlag[0] = false;
                        flagA0.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[0]) {
                        selectTag--;
                        flagA0.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[0] = false;
                        flagA0.setTypeface(Typeface.DEFAULT);
                        flagA0.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_a1:
                if (selectTag < 2) {
                    if (!selectFlag[1]) {
                        LocalLog.d(TAG, "选种2");
                        selectTag++;
                        flagA1.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[1] = true;
                        flagA1.setTypeface(Typeface.DEFAULT_BOLD);
                        flagA1.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagA1.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[1] = false;
                        flagA1.setTypeface(Typeface.DEFAULT);
                        flagA1.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[1]) {
                        selectTag--;
                        flagA1.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[1] = false;
                        flagA1.setTypeface(Typeface.DEFAULT);
                        flagA1.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_a2:
                if (selectTag < 2) {
                    if (!selectFlag[2]) {
                        LocalLog.d(TAG, "选种3");
                        selectTag++;
                        flagA2.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[2] = true;
                        flagA2.setTypeface(Typeface.DEFAULT_BOLD);
                        flagA2.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagA2.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[2] = false;
                        flagA2.setTypeface(Typeface.DEFAULT);
                        flagA2.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[2]) {
                        selectTag--;
                        flagA2.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[2] = false;
                        flagA2.setTypeface(Typeface.DEFAULT);
                        flagA2.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_b0:
                if (selectTag < 2) {
                    if (!selectFlag[3]) {
                        LocalLog.d(TAG, "选种4");
                        selectTag++;
                        flagB0.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[3] = true;
                        flagB0.setTypeface(Typeface.DEFAULT_BOLD);
                        flagB0.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagB0.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[3] = false;
                        flagB0.setTextColor(getResources().getColor(R.color.color_6c71c4));
                        flagB0.setTypeface(Typeface.DEFAULT);
                    }
                } else {
                    if (selectFlag[3]) {
                        selectTag--;
                        flagB0.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[3] = false;
                        flagB0.setTextColor(getResources().getColor(R.color.color_6c71c4));
                        flagB0.setTypeface(Typeface.DEFAULT);
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_b1:
                if (selectTag < 2) {
                    if (!selectFlag[4]) {
                        LocalLog.d(TAG, "选种5");
                        selectTag++;
                        flagB1.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[4] = true;
                        flagB1.setTypeface(Typeface.DEFAULT_BOLD);
                        flagB1.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagB1.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[4] = false;
                        flagB1.setTypeface(Typeface.DEFAULT);
                        flagB1.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[4]) {
                        selectTag--;
                        flagB1.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[4] = false;
                        flagB1.setTypeface(Typeface.DEFAULT);
                        flagB1.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                    toast();
                }
                break;
            case R.id.flag_b2:
                if (selectTag < 2) {
                    if (!selectFlag[5]) {
                        LocalLog.d(TAG, "选种6");
                        selectTag++;
                        flagB2.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[5] = true;
                        flagB2.setTypeface(Typeface.DEFAULT_BOLD);
                        flagB2.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagB2.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[5] = false;
                        flagB2.setTypeface(Typeface.DEFAULT);
                        flagB2.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[5]) {
                        selectTag--;
                        flagB2.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[5] = false;
                        flagB2.setTypeface(Typeface.DEFAULT);
                        flagB2.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_b3:
                if (selectTag < 2) {
                    if (!selectFlag[6]) {
                        LocalLog.d(TAG, "选种2");
                        selectTag++;
                        flagB3.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[6] = true;
                        flagB3.setTypeface(Typeface.DEFAULT_BOLD);
                        flagB3.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagB3.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[6] = false;
                        flagB3.setTypeface(Typeface.DEFAULT);
                        flagB3.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[6]) {
                        selectTag--;
                        flagB3.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[6] = false;
                        flagB3.setTypeface(Typeface.DEFAULT);
                        flagB3.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }

                }
                break;
            case R.id.flag_all_a0:
                if (selectTag < 2) {
                    if (!selectFlag[7]) {
                        LocalLog.d(TAG, "选种2");
                        selectTag++;
                        flagAllA0.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[7] = true;
                        flagAllA0.setTypeface(Typeface.DEFAULT_BOLD);
                        flagAllA0.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagAllA0.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[7] = false;
                        flagAllA0.setTypeface(Typeface.DEFAULT);
                        flagAllA0.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[7]) {
                        selectTag--;
                        flagAllA0.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[7] = false;
                        flagAllA0.setTypeface(Typeface.DEFAULT);
                        flagAllA0.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_all_a1:
                if (selectTag < 2) {
                    if (!selectFlag[8]) {
                        LocalLog.d(TAG, "选种2");
                        selectTag++;
                        flagAllA1.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[8] = true;
                        flagAllA1.setTypeface(Typeface.DEFAULT_BOLD);
                        flagAllA1.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagAllA1.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[8] = false;
                        flagAllA1.setTypeface(Typeface.DEFAULT);
                        flagAllA1.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[8]) {
                        selectTag--;
                        flagAllA1.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[8] = false;
                        flagAllA1.setTypeface(Typeface.DEFAULT);
                        flagAllA1.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }

                }
                break;
            case R.id.flag_all_a2:
                if (selectTag < 2) {
                    if (!selectFlag[9]) {
                        LocalLog.d(TAG, "选种2");
                        selectTag++;
                        flagAllA2.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[9] = true;
                        flagAllA2.setTypeface(Typeface.DEFAULT_BOLD);
                        flagAllA2.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagAllA2.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[9] = false;
                        flagAllA2.setTypeface(Typeface.DEFAULT);
                        flagAllA2.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[9]) {
                        selectTag--;
                        flagAllA2.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[9] = false;
                        flagAllA2.setTypeface(Typeface.DEFAULT);
                        flagAllA2.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_all_a3:
                if (selectTag < 2) {
                    if (!selectFlag[10]) {
                        LocalLog.d(TAG, "选种2");
                        selectTag++;
                        flagAllA3.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[10] = true;
                        flagAllA3.setTypeface(Typeface.DEFAULT_BOLD);
                        flagAllA3.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagAllA3.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[10] = false;
                        flagAllA3.setTypeface(Typeface.DEFAULT);
                        flagAllA3.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[10]) {
                        selectTag--;
                        flagAllA3.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[10] = false;
                        flagAllA3.setTypeface(Typeface.DEFAULT);
                        flagAllA3.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_all_b0:
                if (selectTag < 2) {
                    if (!selectFlag[11]) {
                        LocalLog.d(TAG, "选种2");
                        selectTag++;
                        flagAllB0.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[11] = true;
                        flagAllB0.setTypeface(Typeface.DEFAULT_BOLD);
                        flagAllB0.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagAllB0.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[11] = false;
                        flagAllB0.setTypeface(Typeface.DEFAULT);
                        flagAllB0.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[11]) {
                        selectTag--;
                        flagAllB0.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[11] = false;
                        flagAllB0.setTypeface(Typeface.DEFAULT);
                        flagAllB0.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_all_b1:
                if (selectTag < 2) {
                    if (!selectFlag[12]) {
                        LocalLog.d(TAG, "选种2");
                        selectTag++;
                        flagAllB1.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[12] = true;
                        flagAllB1.setTypeface(Typeface.DEFAULT_BOLD);
                        flagAllB1.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagAllB1.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[12] = false;
                        flagAllB1.setTypeface(Typeface.DEFAULT);
                        flagAllB1.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[12]) {
                        selectTag--;
                        flagAllB1.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[12] = false;
                        flagAllB1.setTypeface(Typeface.DEFAULT);
                        flagAllB1.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_all_b2:
                if (selectTag < 2) {
                    if (!selectFlag[13]) {
                        LocalLog.d(TAG, "选种2");
                        selectTag++;
                        flagAllB2.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[13] = true;
                        flagAllB2.setTypeface(Typeface.DEFAULT);
                        flagAllB2.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagAllB2.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[13] = false;
                        flagAllB2.setTypeface(Typeface.DEFAULT);
                        flagAllB2.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[13]) {
                        selectTag--;
                        flagAllB2.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[13] = false;
                        flagAllB2.setTypeface(Typeface.DEFAULT);
                        flagAllB2.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_all_b3:
                if (selectTag < 2) {
                    if (!selectFlag[14]) {
                        LocalLog.d(TAG, "选种2");
                        selectTag++;
                        flagAllB3.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[14] = true;
                        flagAllB3.setTypeface(Typeface.DEFAULT_BOLD);
                        flagAllB3.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagAllB3.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[14] = false;
                        flagAllB3.setTypeface(Typeface.DEFAULT);
                        flagAllB3.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[14]) {
                        selectTag--;
                        flagAllB3.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[14] = false;
                        flagAllB3.setTypeface(Typeface.DEFAULT);
                        flagAllB3.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_all_c0:
                if (selectTag < 2) {
                    if (!selectFlag[15]) {
                        LocalLog.d(TAG, "选种2");
                        selectTag++;
                        flagAllC0.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[15] = true;
                        flagAllC0.setTypeface(Typeface.DEFAULT_BOLD);
                        flagAllC0.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagAllC0.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[15] = false;
                        flagAllC0.setTypeface(Typeface.DEFAULT);
                        flagAllC0.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[15]) {
                        selectTag--;
                        flagAllC0.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[5] = false;
                        flagAllC0.setTypeface(Typeface.DEFAULT);
                        flagAllC0.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_all_c1:
                if (selectTag < 2) {
                    if (!selectFlag[16]) {
                        LocalLog.d(TAG, "选种2");
                        selectTag++;
                        flagAllC1.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[16] = true;
                        flagAllC1.setTypeface(Typeface.DEFAULT_BOLD);
                        flagAllC1.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagAllC1.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[16] = false;
                        flagAllC1.setTypeface(Typeface.DEFAULT);
                        flagAllC1.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[16]) {
                        selectTag--;
                        flagAllC1.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[16] = false;
                        flagAllC1.setTypeface(Typeface.DEFAULT);
                        flagAllC1.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            case R.id.flag_all_c2:
                if (selectTag < 2) {
                    if (!selectFlag[17]) {
                        LocalLog.d(TAG, "选种2");
                        selectTag++;
                        flagAllC2.setBackground(getDrawableResource(R.drawable.tag_back_ground_select));
                        selectFlag[17] = true;
                        flagAllC2.setTypeface(Typeface.DEFAULT_BOLD);
                        flagAllC2.setTextColor(getResources().getColor(R.color.color_f8));
                    } else {
                        selectTag--;
                        flagAllC2.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[17] = false;
                        flagAllC2.setTypeface(Typeface.DEFAULT);
                        flagAllC2.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    }
                } else {
                    if (selectFlag[17]) {
                        selectTag--;
                        flagAllC2.setBackground(getDrawableResource(R.drawable.tag_back_ground_unselect));
                        selectFlag[17] = false;
                        flagAllC2.setTypeface(Typeface.DEFAULT);
                        flagAllC2.setTextColor(getResources().getColor(R.color.color_6c71c4));
                    } else {
                        toast();
                    }
                }
                break;
            default:
                break;
        }
    }

    public ArrayList<String> getSelect() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < selectFlag.length; i++) {
            if (selectFlag[i]) {
                if (i < textViewsHot.size()) {
                    result.add(textViewsHot.get(i).getText().toString());
                } else {
                    result.add(textViewAll.get(i - textViewsHot.size()).getText().toString());
                }
            }
        }
        return result;
    }

    public ArrayList<String> getSelectId() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < selectFlag.length; i++) {
            if (selectFlag[i]) {
                if (i < textViewsHot.size()) {
                    result.add(tableTag.get(textViewsHot.get(i).getText().toString()));
                } else {
                    result.add(tableTag.get(textViewAll.get(i - textViewsHot.size()).getText().toString()));
                }
            }
        }
        return result;
    }

    private BaseBarImageViewFragment.ToolBarListener toolBarListener = new BaseBarImageViewFragment.ToolBarListener() {
        @Override
        public void clickLeft() {

        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "clickRight() enter ");

            Intent intent = new Intent();
            intent.putStringArrayListExtra("tag", getSelect());
            intent.putStringArrayListExtra("id", getSelectId());
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
        }
    };
}
