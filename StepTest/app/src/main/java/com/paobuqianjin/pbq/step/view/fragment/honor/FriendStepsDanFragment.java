package com.paobuqianjin.pbq.step.view.fragment.honor;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendStepRankDayResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.FriendHonorDetailInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.dan.HonorAdapter;
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

    private int pageIndex = 1, pageCount = 0;
    private final static int PAGE_DEFAULT_SIZE = 10;
    LinearLayoutManager layoutManager;

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
        Presenter.getInstance(getContext()).getFriendHonorDetail(pageIndex, PAGE_DEFAULT_SIZE);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bar_return_drawable:
                    getActivity().finish();
                    break;
                case R.id.go_down_span:
                    LocalLog.d(TAG,"弹出查看排行...选择");
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(FriendStepRankDayResponse friendStepRankDayResponse) {
        LocalLog.d(TAG, "FriendStepRankDayResponse() enter" + friendStepRankDayResponse.toString());
        if (friendStepRankDayResponse.getError() == 0) {
            if (kingHeadIcon != null && kingName != null && yourDan != null && headIconUser != null && userNameRank != null && danDetailRecycler != null) {
                userNameRank.setText(String.valueOf(friendStepRankDayResponse.getData().getData().getMydata().getNickname()));
                yourDan.setText(String.valueOf(friendStepRankDayResponse.getData().getData().getMydata().getRanking()));
                stepNumMy.setText(String.valueOf(friendStepRankDayResponse.getData().getData().getMydata().getStep_number()));
                Presenter.getInstance(getContext()).getImage(headIconUser, friendStepRankDayResponse.getData().getData().getMydata().getAvatar());
                if (friendStepRankDayResponse.getData().getData().getMember().size() > 0) {
                    Presenter.getInstance(getContext()).getImage(kingHeadIcon, friendStepRankDayResponse.getData().getData().getMember().get(0).getAvatar());
                    kingName.setText(friendStepRankDayResponse.getData().getData().getMember().get(0).getNickname());
                }
                danDetailRecycler.setAdapter(new HonorAdapter(getContext(), friendStepRankDayResponse.getData().getData().getMember()));
            }
        }
    }

}
