package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.LocalContactAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/18.
 */

public class FriendAddressFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = FriendAddressFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.add_friend)
    TextView addFriend;
    @Bind(R.id.add_friend_layer)
    RelativeLayout addFriendLayer;
    @Bind(R.id.add_friend_line_a)
    ImageView addFriendLineA;
    @Bind(R.id.reg_app_recycler)
    RecyclerView regAppRecycler;
    @Bind(R.id.add_friend_line_b)
    ImageView addFriendLineB;
    @Bind(R.id.un_reg_app_recycler)
    RecyclerView unRegAppRecycler;

    private ContentResolver cr;
    private List<Map<String, String>> mp = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private Rationale mRationale;
    private PermissionSetting mSetting;

    @Override
    protected int getLayoutResId() {
        return R.layout.friend_address_fg;
    }

    @Override
    protected String title() {
        return "通讯录好友";
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

        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(getContext());
        layoutManager = new LinearLayoutManager(getContext());
        unRegAppRecycler = (RecyclerView) viewRoot.findViewById(R.id.un_reg_app_recycler);
        unRegAppRecycler.setLayoutManager(layoutManager);
        requestPermission(Permission.Group.CONTACTS);

    }

     /*权限适配*/

    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        toast(R.string.successfully);
                        unRegAppRecycler.setAdapter(new LocalContactAdapter(getContext(), getContacts()));
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                toast(R.string.failure);
                if (AndPermission.hasAlwaysDeniedPermission(getActivity(), permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }


    protected void toast(@StringRes int message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public List<Map<String, String>> getContacts() {
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        cr = getContext().getContentResolver();
        Cursor cs = cr.query(uri, null, null, null, null);
        while (cs.moveToNext()) {
            int id = cs.getInt(cs.getColumnIndex("_id"));
            String name = cs.getString(cs.getColumnIndex("display_name"));
            LocalLog.d(TAG, "name = " + name);

            Uri uri1 = Uri.parse("content://com.android.contacts/raw_contacts/" + String.valueOf(id) + "/data");
            Cursor cs2 = cr.query(uri1, null, null, null, null);
            Map<String, String> maps = new HashMap<>();
            while (cs2.moveToNext()) {
                String data1 = cs2.getString(cs2.getColumnIndex("data1"));
                String type = cs2.getString(cs2.getColumnIndex("mimetype"));
                String str = type.substring(type.indexOf("/") + 1, type.length());

                if ("name".equals(str)) {
                    maps.put("name", data1);
                } else if ("phone_v2".equals(str)) {
                    maps.put("phone", data1);
                }
                LocalLog.d(TAG, "data1 =" + data1 + " ,type =" + type);
            }
            mp.add(maps);
        }
        cs.close();

        return mp;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
