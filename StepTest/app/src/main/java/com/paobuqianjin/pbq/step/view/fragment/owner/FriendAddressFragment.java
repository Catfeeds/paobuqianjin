package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.ContentResolver;
import android.content.Context;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostAddressBookParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendAddResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteMessageResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.FriendAddressInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.LocalContactAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/18.
 */

public class FriendAddressFragment extends BaseBarStyleTextViewFragment implements FriendAddressInterface {
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
    @Bind(R.id.friend_scroll)
    BounceScrollView friendScroll;

    private ContentResolver cr;
    private List<Map<String, String>> mp = new ArrayList<>();
    private LinearLayoutManager layoutManager, regLayoutManager;
    private Rationale mRationale;
    private PermissionSetting mSetting;
    //单次最多上传500个联系人
    private final static int UPLOAD_SIZE = 200;
    private int currentIndex = 0;
    private List<PostAddressBookParam.AddressPerson> list;
    private LocalContactAdapter inContactAdapter;
    private LocalContactAdapter ouContactAdapter;
    private ArrayList<?> inContact = new ArrayList<>();
    private ArrayList<?> ouContract = new ArrayList<>();
    private boolean isLoading = false;

    @Override
    protected int getLayoutResId() {
        return R.layout.friend_address_fg;
    }

    @Override
    protected String title() {
        return "通讯录好友";
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

        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(getContext());
        layoutManager = new LinearLayoutManager(getContext());
        regLayoutManager = new LinearLayoutManager(getContext());
        unRegAppRecycler = (RecyclerView) viewRoot.findViewById(R.id.un_reg_app_recycler);
        unRegAppRecycler.setLayoutManager(layoutManager);
        regAppRecycler = (RecyclerView) viewRoot.findViewById(R.id.reg_app_recycler);
        regAppRecycler.setLayoutManager(regLayoutManager);
        regAppRecycler.setHasFixedSize(true);
        regAppRecycler.setNestedScrollingEnabled(false);
        unRegAppRecycler.setHasFixedSize(true);
        unRegAppRecycler.setNestedScrollingEnabled(false);
        requestPermission(Permission.Group.CONTACTS);
        friendScroll = (BounceScrollView) viewRoot.findViewById(R.id.friend_scroll);
        friendScroll.setTopBottomListener(new BounceScrollView.TopBottomListener() {
            @Override
            public void topBottom(int topOrBottom) {
                if (topOrBottom == 0) {

                } else if (topOrBottom == 1) {
                    if (currentIndex < list.size()) {
                        if (!isLoading) {
                            postAddressBook();
                            LocalLog.d(TAG, "加载更多.......");
                        }
                    } else {
                        list.clear();
                        System.gc();
                    }
                }
            }
        });
    }

    /*权限适配*/
    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        List<Map<String, String>> maps = getContacts();
                        updateAddressBook(maps);
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(getActivity(), permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    private void updateAddressBook(List<Map<String, String>> maps) {
        list = new ArrayList<>();
        if (maps != null) {
            int size = maps.size();
            LocalLog.d(TAG, "size = " + size);
            for (int i = 0; i < size; i++) {
                Map<String, String> stringMap = maps.get(i);
                PostAddressBookParam.AddressPerson addressPerson = new PostAddressBookParam.AddressPerson();
                for (String key : stringMap.keySet()) {
                    if (key.equals("name")) {
                        addressPerson.setName(stringMap.get(key));
                    } else if (key.equals("phone")) {
                        addressPerson.setPhone(stringMap.get(key));
                    }

                }
                list.add(addressPerson);
            }
            postAddressBook();
            if (mp != null) {
                mp.clear();
                //
                mp = null;
                System.gc();
            }
        }
    }

    private void postAddressBook() {
        isLoading = true;
        Type type = new TypeToken<List<PostAddressBookParam.AddressPerson>>() {
        }.getType();
        List<PostAddressBookParam.AddressPerson> upLoadList = null;
        if (currentIndex + UPLOAD_SIZE > list.size()) {
            upLoadList = list.subList(currentIndex, list.size() - 1);
            currentIndex = list.size();
        } else {
            upLoadList = list.subList(currentIndex, currentIndex + UPLOAD_SIZE);
            currentIndex += UPLOAD_SIZE;
        }
        if (upLoadList != null) {
            String json = new Gson().toJson(upLoadList, type);
            LocalLog.d(TAG, "json = " + json + ",size = " + upLoadList.toString());
            Presenter.getInstance(getContext()).postAddressBook(json);
        }
    }

    public List<Map<String, String>> getContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
        };
        cr = getContext().getContentResolver();
        Cursor cs = cr.query(uri, projection, null, null, null);
        if (cs == null) {
            return null;
        }
        while (cs.moveToNext()) {

            String name = cs.getString(0);
            String number = cs.getString(1);


            number = number.replace(" ", "");
            if (number.length() > 11) {
                LocalLog.d(TAG, "非手机号码不处理");
                continue;
            }
            Map<String, String> maps = new HashMap<>();
            maps.put("name", name);
            maps.put("phone", number);
            mp.add(maps);
        }
        cs.close();

        return mp;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(FriendAddResponse friendAddResponse) {
        LocalLog.d(TAG, "FriendAddResponse() enter ");
        if (!isAdded()) {
            return;
        }
        if (friendAddResponse.getError() == 0) {
            if (inContactAdapter == null) {
                inContact.clear();
                inContact.addAll((ArrayList) friendAddResponse.getData().getIn_system());
                inContactAdapter = new LocalContactAdapter(getContext(), inContact);
            } else {
                inContact.addAll((ArrayList) friendAddResponse.getData().getIn_system());
                inContactAdapter.notifyItemRangeInserted(inContact.size() - friendAddResponse.getData().getIn_system().size()
                        , friendAddResponse.getData().getIn_system().size());
            }
            regAppRecycler.setAdapter(inContactAdapter);

            if (ouContactAdapter == null) {
                ouContract.clear();
                ouContract.addAll((ArrayList) friendAddResponse.getData().getOut_system());
                ouContactAdapter = new LocalContactAdapter(getContext(), ouContract);
            } else {
                ouContract.addAll((ArrayList) friendAddResponse.getData().getOut_system());
                ouContactAdapter.notifyItemRangeInserted(ouContract.size() - friendAddResponse.getData().getOut_system().size()
                        , friendAddResponse.getData().getOut_system().size());
            }
            unRegAppRecycler.setAdapter(ouContactAdapter);
        } else if (friendAddResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            Toast.makeText(getContext(), friendAddResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
        isLoading = false;

    }

    @Override
    public void response(InviteMessageResponse inviteMessageResponse) {
        if (!isAdded()) {
            return;
        }
        if (inviteMessageResponse.getError() == 0) {

        } else if (inviteMessageResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            Toast.makeText(getContext(), inviteMessageResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (!isAdded()) {
            return;
        }
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }
}
