package com.paobuqianjin.pbq.step.view.fragment.honor;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendStepRankDayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendWeekResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.FriendHonorDetailInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.dan.HonorDetailAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/19.
 */

public class FriendStepsDanFragment extends BaseFragment implements FriendHonorDetailInterface {
    private final static String TAG = FriendStepsDanFragment.class.getSimpleName();
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.time_go)
    TextView timeGo;
    @Bind(R.id.bar_tv_right)
    ImageView barTvRight;
    @Bind(R.id.go_down_span)
    RelativeLayout goDownSpan;
    @Bind(R.id.bg_dan)
    ImageView bgDan;
    @Bind(R.id.king_head_icon)
    CircleImageView kingHeadIcon;
    @Bind(R.id.dan_king)
    ImageView danKing;
    @Bind(R.id.king_head_layer)
    RelativeLayout kingHeadLayer;
    @Bind(R.id.king_name)
    TextView kingName;
    @Bind(R.id.your_dan)
    TextView yourDan;
    @Bind(R.id.head_icon_user)
    CircleImageView headIconUser;
    @Bind(R.id.user_name_rank)
    TextView userNameRank;
    @Bind(R.id.your_dan_layer)
    RelativeLayout yourDanLayer;
    @Bind(R.id.line_dan)
    ImageView lineDan;
    @Bind(R.id.dan_detail_recycler)
    RecyclerView danDetailRecycler;
    @Bind(R.id.step_num_my)
    TextView stepNumMy;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.buttone_left_bar)
    RelativeLayout buttoneLeftBar;
    @Bind(R.id.rank_icon)
    ImageView rankIcon;
    @Bind(R.id.vip_flg)
    ImageView vipFlg;

    private int pageIndexDay = 1, pageCountDay = 0, pageIndexWeek = 1, pageCountWeek = 0;
    private final static int PAGE_DEFAULT_SIZE = 10;
    LinearLayoutManager layoutManager;
    FriendStepRankDayResponse friendStepRankDayResponse;
    FriendWeekResponse friendWeekResponse;
    private View popCircleOpBar;
    private PopupWindow popupOpWindowTop;
    private TranslateAnimation animationCircleType;
    HonorDetailAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.friend_step_dan_fg;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
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
        kingHeadIcon = (CircleImageView) viewRoot.findViewById(R.id.king_head_icon);
        kingName = (TextView) viewRoot.findViewById(R.id.king_name);
        yourDan = (TextView) viewRoot.findViewById(R.id.your_dan);
        headIconUser = (CircleImageView) viewRoot.findViewById(R.id.head_icon_user);
        userNameRank = (TextView) viewRoot.findViewById(R.id.user_name_rank);
        stepNumMy = (TextView) viewRoot.findViewById(R.id.step_num_my);
        danDetailRecycler = (RecyclerView) viewRoot.findViewById(R.id.dan_detail_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        danDetailRecycler.setLayoutManager(layoutManager);
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        barTitle.setText("好友步数排行");
        barReturnDrawable = (ImageView) viewRoot.findViewById(R.id.bar_return_drawable);
        barReturnDrawable.setOnClickListener(onClickListener);

        goDownSpan = (RelativeLayout) viewRoot.findViewById(R.id.go_down_span);
        timeGo = (TextView) viewRoot.findViewById(R.id.time_go);
        goDownSpan.setOnClickListener(onClickListener);
        buttoneLeftBar = (RelativeLayout) viewRoot.findViewById(R.id.buttone_left_bar);
        vipFlg = (ImageView) viewRoot.findViewById(R.id.vip_flg);
        buttoneLeftBar.setOnClickListener(onClickListener);

        Presenter.getInstance(getContext()).getFriendHonorDetail(pageIndexDay, PAGE_DEFAULT_SIZE);
        Presenter.getInstance(getContext()).getFriendWeekHonor(pageIndexWeek, PAGE_DEFAULT_SIZE);


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.buttone_left_bar:
                    getActivity().finish();
                    break;
                case R.id.go_down_span:
                    LocalLog.d(TAG, "弹出查看排行...选择");
                    popWeekDaySelect();
                    break;
                case R.id.today_text:
                    LocalLog.d(TAG, "今日");
                    timeGo.setText("今日");
                    updateFriendStepRankDayResponse(friendStepRankDayResponse);
                    break;
                case R.id.week_text:
                    LocalLog.d(TAG, "本周");
                    timeGo.setText("本周");
                    updateFriendWeekResponse(friendWeekResponse);
                    break;
            }
        }
    };

    private void popWeekDaySelect() {
        LocalLog.d(TAG, "popWeekDaySelect() enter");
        popCircleOpBar = View.inflate(getContext(), R.layout.week_day_select_layout, null);
        popupOpWindowTop = new PopupWindow(popCircleOpBar,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popCircleOpBar.findViewById(R.id.today_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.week_text).setOnClickListener(onClickListener);
        popupOpWindowTop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popWindow dismiss() ");
                popupOpWindowTop = null;
            }
        });

        popupOpWindowTop.setFocusable(true);
        popupOpWindowTop.setOutsideTouchable(true);
        popupOpWindowTop.setBackgroundDrawable(new BitmapDrawable());

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupOpWindowTop.showAsDropDown(barTvRight, 20, -10);
        popCircleOpBar.startAnimation(animationCircleType);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    private void loadMoreDay(FriendStepRankDayResponse moreData) {
        if (this.friendStepRankDayResponse == null) {
            this.friendStepRankDayResponse = moreData;
        } else {
            friendStepRankDayResponse.getData().getData().getMember().addAll(moreData.getData().getData().getMember());
            if (adapter == null) {
                return;
            }
            adapter.notifyItemRangeInserted(friendStepRankDayResponse.getData().getData().getMember().size()
                            - moreData.getData().getData().getMember().size()
                    , moreData.getData().getData().getMember().size());
        }

    }


    private void loadMoreWeek(FriendWeekResponse moreData) {
        if (this.friendWeekResponse == null) {
            this.friendWeekResponse = moreData;
        } else {
            friendWeekResponse.getData().getData().getMember().addAll(moreData.getData().getData().getMember());
            if (adapter == null) {
                return;
            }
            adapter.notifyItemRangeInserted(friendWeekResponse.getData().getData().getMember().size()
                            - moreData.getData().getData().getMember().size()
                    , moreData.getData().getData().getMember().size());
        }
    }

    @Override
    public void response(FriendStepRankDayResponse friendStepRankDayResponse) {
        LocalLog.d(TAG, "FriendStepRankDayResponse() enter" + friendStepRankDayResponse.toString());
        if (friendStepRankDayResponse.getError() == 0) {
            if (this.friendStepRankDayResponse == null) {
                this.friendStepRankDayResponse = friendStepRankDayResponse;

                pageCountDay = friendStepRankDayResponse.getData().getPagenation().getTotalPage();
                LocalLog.d(TAG, "pageIndexDay = " + pageIndexDay + "pageIndexDay = " + pageCountDay);
                //adapter.notifyDataSetChanged(friendStepRankDayResponse.getData().getData().getMember());
                if (userNameRank == null) {
                    return;
                }
                userNameRank.setText(String.valueOf(friendStepRankDayResponse.getData().getData().getMydata().getNickname()));
                yourDan.setText(String.valueOf(friendStepRankDayResponse.getData().getData().getMydata().getRank()));
                stepNumMy.setText(String.valueOf(friendStepRankDayResponse.getData().getData().getMydata().getStep_number()));
                Presenter.getInstance(getContext()).getImage(headIconUser, friendStepRankDayResponse.getData().getData().getMydata().getAvatar());
                if (friendStepRankDayResponse.getData().getData().getMydata().getVip() == 1) {
                    vipFlg.setVisibility(View.VISIBLE);
                }
                adapter = new HonorDetailAdapter(getContext(), friendStepRankDayResponse.getData().getData().getMember());
                danDetailRecycler.setAdapter(adapter);
                if (friendStepRankDayResponse.getData().getData().getMember().size() > 0) {
                    Presenter.getInstance(getContext()).getImage(kingHeadIcon, friendStepRankDayResponse.getData().getData().getMember().get(0).getAvatar());
                    kingName.setText(friendStepRankDayResponse.getData().getData().getMember().get(0).getNickname());
                }
                if (pageIndexDay == pageCountDay) {

                } else if (pageIndexDay < pageCountDay) {
                    pageIndexDay++;
                    Presenter.getInstance(getContext()).getFriendHonorDetail(pageIndexDay, PAGE_DEFAULT_SIZE);
                }
            } else {
                LocalLog.d(TAG, "加载到更多数据....");
                loadMoreDay(friendStepRankDayResponse);
                if (pageIndexDay < pageCountDay) {
                    pageIndexDay++;
                    if (getContext() == null) {
                        return;
                    }
                    Presenter.getInstance(getContext()).getFriendHonorDetail(pageIndexDay, PAGE_DEFAULT_SIZE);
                }
            }

        } else if (friendStepRankDayResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    private void updateFriendStepRankDayResponse(FriendStepRankDayResponse friendStepRankDayResponse) {
        if (kingHeadIcon != null && friendStepRankDayResponse != null) {
            userNameRank.setText(String.valueOf(friendStepRankDayResponse.getData().getData().getMydata().getNickname()));
            yourDan.setText(String.valueOf(friendStepRankDayResponse.getData().getData().getMydata().getRank()));
            stepNumMy.setText(String.valueOf(friendStepRankDayResponse.getData().getData().getMydata().getStep_number()));
            Presenter.getInstance(getContext()).getImage(headIconUser, friendStepRankDayResponse.getData().getData().getMydata().getAvatar());
            if (friendStepRankDayResponse.getData().getData().getMember().size() > 0) {
                Presenter.getInstance(getContext()).getImage(kingHeadIcon, friendStepRankDayResponse.getData().getData().getMember().get(0).getAvatar());
                kingName.setText(friendStepRankDayResponse.getData().getData().getMember().get(0).getNickname());
            }
            danDetailRecycler.setAdapter(new HonorDetailAdapter(getContext(), friendStepRankDayResponse.getData().getData().getMember()));
        }
    }

    private void updateFriendWeekResponse(FriendWeekResponse friendWeekResponse) {
        if (kingHeadIcon != null && friendWeekResponse != null) {
            userNameRank.setText(String.valueOf(friendWeekResponse.getData().getData().getMydata().getNickname()));
            yourDan.setText(String.valueOf(friendWeekResponse.getData().getData().getMydata().getRank()));
            stepNumMy.setText(String.valueOf(friendWeekResponse.getData().getData().getMydata().getStep_number()));
            Presenter.getInstance(getContext()).getImage(headIconUser, friendWeekResponse.getData().getData().getMydata().getAvatar());
            if (friendWeekResponse.getData().getData().getMember().size() > 0) {
                Presenter.getInstance(getContext()).getImage(kingHeadIcon, friendWeekResponse.getData().getData().getMember().get(0).getAvatar());
                kingName.setText(friendWeekResponse.getData().getData().getMember().get(0).getNickname());
            }
            danDetailRecycler.setAdapter(new HonorDetailAdapter(getContext(), friendWeekResponse.getData().getData().getMember()));
        }
    }

    @Override
    public void response(FriendWeekResponse friendWeekResponse) {
        LocalLog.d(TAG, "FriendWeekResponse() enter" + friendWeekResponse.toString());
        if (friendWeekResponse.getError() == 0) {
            if (this.friendWeekResponse == null) {
                this.friendWeekResponse = friendWeekResponse;
                pageCountWeek = friendWeekResponse.getData().getPagenation().getTotalPage();
                LocalLog.d(TAG, "pageIndexWeek = " + pageIndexWeek + "pageCountWeek = " + pageCountWeek);
                //adapter.notifyDataSetChanged(friendWeekResponse.getData().getData().getMember());
                adapter = new HonorDetailAdapter(getContext(), friendWeekResponse.getData().getData().getMember());
                danDetailRecycler.setAdapter(adapter);
                if (pageIndexWeek == pageCountWeek) {

                } else if (pageIndexWeek < pageCountWeek) {
                    pageIndexWeek++;
                    Presenter.getInstance(getContext()).getFriendWeekHonor(pageIndexWeek, PAGE_DEFAULT_SIZE);
                }
            } else {
                LocalLog.d(TAG, "加载到更多数据....");
                loadMoreWeek(friendWeekResponse);
                if (pageIndexWeek < pageCountWeek) {
                    pageIndexWeek++;
                    Presenter.getInstance(getContext()).getFriendWeekHonor(pageIndexWeek, PAGE_DEFAULT_SIZE);
                }
            }
        } else if (friendWeekResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    @Override
    public void response(ErrorCode errorCode) {

    }
}
