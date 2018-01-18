package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.AddFriendAddressActivity;
import com.paobuqianjin.pbq.step.view.activity.FriendAddressActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/17.
 */

public class MyFriendFragment extends BaseBarImageViewFragment {
    private final static String TAG = MyFriendFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    ImageView barTvRight;
    @Bind(R.id.search_icon)
    ImageView searchIcon;
    @Bind(R.id.search_cancel)
    ImageView searchCancel;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.friend_recycler)
    RecyclerView friendRecycler;

    @Override
    protected int getLayoutResId() {
        return R.layout.my_friend_fg;
    }

    @Override
    protected String title() {
        return "我的好友";
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
        setToolBarListener(toolBarListener);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private BaseBarImageViewFragment.ToolBarListener toolBarListener = new ToolBarListener() {
        @Override
        public void clickLeft() {

        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "邀请好友");
            Intent friendAddressIntent = new Intent();
            friendAddressIntent.setClass(getContext(), AddFriendAddressActivity.class);
            startActivity(friendAddressIntent);
        }
    };

    @Override
    public Object right() {
        return getDrawableResource(R.drawable.add_friend);
    }
}
