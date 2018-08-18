package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.FriendAddressActivity;
import com.paobuqianjin.pbq.step.view.activity.SearchResultActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/18.
 */

public class AddFriendFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = AddFriendFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.search_icon)
    RelativeLayout searchIcon;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.cancel_icon)
    RelativeLayout cancelIcon;
    @Bind(R.id.cancel_text)
    TextView cancelText;
    @Bind(R.id.address_mail)
    ImageView addressMail;
    @Bind(R.id.phone_address)
    TextView phoneAddress;
    @Bind(R.id.mail_address_layer)
    RelativeLayout mailAddressLayer;

    @Override
    protected String title() {
        return "添加关注";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.friend_add_from_wx_phone_qq;
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
        mailAddressLayer = (RelativeLayout) viewRoot.findViewById(R.id.mail_address_layer);
        mailAddressLayer.setOnClickListener(onClickListener);
        searchCircleText = (EditText) viewRoot.findViewById(R.id.search_circle_text);
        searchCircleText.setHint("搜索手机号、跑步钱进号");
        cancelText = (TextView) viewRoot.findViewById(R.id.cancel_text);
        cancelText.setOnClickListener(onClickListener);
        searchCircleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    Utils.hideInput(getContext());
                    String keyWord = searchCircleText.getText().toString().trim();
                    if (!TextUtils.isEmpty(keyWord)) {
                        Intent intent = new Intent(getContext(), SearchResultActivity.class);
                        intent.putExtra("keyword", keyWord);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.mail_address_layer:
                    LocalLog.d(TAG, " 通讯录好友");
                    Intent friendAddressIntent = new Intent();
                    friendAddressIntent.setClass(getContext(), FriendAddressActivity.class);
                    startActivity(friendAddressIntent);
                    break;
                case R.id.cancel_text:
                    LocalLog.d(TAG, "取消搜索");
                    searchCircleText.setText(null);
                    break;
                default:
                    break;
            }
        }
    };

}
