package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
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
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.param.AddressPerson;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.LocalContactAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
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
import butterknife.OnClick;

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
    @Bind(R.id.search_icon)
    RelativeLayout searchIcon;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.cancel_icon)
    RelativeLayout cancelIcon;
    @Bind(R.id.cancel_text)
    TextView cancelText;
    @Bind(R.id.un_reg_app_recycler)
    RecyclerView unRegAppRecycler;
    @Bind(R.id.friend_scroll)
    BounceScrollView friendScroll;
    private ContentResolver cr;
    private List<Map<String, String>> mp = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private Rationale mRationale;
    private PermissionSetting mSetting;
    private List<AddressPerson> listShow;
    private LocalContactAdapter allContactAdapter;

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
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(getContext());
        layoutManager = new LinearLayoutManager(getContext());
        unRegAppRecycler = (RecyclerView) viewRoot.findViewById(R.id.un_reg_app_recycler);
        unRegAppRecycler.setLayoutManager(layoutManager);
        unRegAppRecycler.setHasFixedSize(true);
        unRegAppRecycler.setNestedScrollingEnabled(false);
        requestPermission(Permission.Group.CONTACTS);
        searchCircleText = (EditText) viewRoot.findViewById(R.id.search_circle_text);
        searchCircleText.setHint("搜索联系人电话、姓名");
        searchCircleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    Utils.hideInput(getActivity());
                    String keyWord = searchCircleText.getText().toString().trim();
                    if (!TextUtils.isEmpty(keyWord))
                        searchKeyWord(keyWord);
                }
                return false;
            }
        });

    }

    private String phoneKeyWord(String keyWord) {
        if (keyWord.length() <= 3) {
            return keyWord;
        }
        StringBuilder sb = new StringBuilder(keyWord);
        if (3 < sb.length() && sb.length() <= 7) {
            return sb.insert(3, " ").toString();
        }
        if (7 < keyWord.length() && keyWord.length() <= 11) {
            return sb.insert(7, " ").insert(3, " ").toString();
        }
        sb = new StringBuilder(keyWord.substring(0, 10));
        return sb.insert(7, " ").insert(3, " ").toString();
    }

    private void searchKeyWord(String keyWord) {
        List<Map<String, String>> searchMap = null;
        if (PhoneNumberUtils.isGlobalPhoneNumber(keyWord)) {
            String search = phoneKeyWord(keyWord);
            LocalLog.d(TAG, "手机号码搜索" + search);
            searchMap = searchContacts(ContactsContract.CommonDataKinds.Phone.NUMBER + " like " + "'%" + search + "%'", null, null);
        } else {
            LocalLog.d(TAG, "名字搜索");
            searchMap = searchContacts(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " like " + "'%" + keyWord + "%'", null, null);
        }

        if (searchMap != null && searchMap.size() > 0) {
            updateSearchBook(searchMap);
        } else {
            PaoToastUtils.showLongToast(getContext(), "没有相关联系人");
        }
    }

    /*权限适配*/
    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        List<Map<String, String>> maps = getContacts(null, null, null);
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
        if (maps != null && maps.size() > 0) {
            int size = maps.size();
            LocalLog.d(TAG, "size = " + size);
            listShow = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Map<String, String> stringMap = maps.get(i);
                AddressPerson addressPerson = new AddressPerson();
                for (String key : stringMap.keySet()) {
                    if (key.equals("name")) {
                        addressPerson.setName(stringMap.get(key));
                    } else if (key.equals("phone")) {
                        addressPerson.setPhone(stringMap.get(key));
                    }

                }
                listShow.add(addressPerson);
            }

            allContactAdapter = new LocalContactAdapter(getActivity(), listShow);
            unRegAppRecycler.setAdapter(allContactAdapter);
            if (maps != null) {
                maps.clear();
                //
                maps = null;
                System.gc();
            }
        }
    }

    private void updateSearchBook(List<Map<String, String>> maps) {
        if (maps != null && maps.size() > 0) {
            int size = maps.size();
            LocalLog.d(TAG, "size = " + size);
            List<AddressPerson> listSearch = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Map<String, String> stringMap = maps.get(i);
                AddressPerson addressPerson = new AddressPerson();
                for (String key : stringMap.keySet()) {
                    if (key.equals("name")) {
                        addressPerson.setName(stringMap.get(key));
                    } else if (key.equals("phone")) {
                        addressPerson.setPhone(stringMap.get(key));
                    }

                }
                listSearch.add(addressPerson);
            }
            allContactAdapter = new LocalContactAdapter(getActivity(), listSearch);
            unRegAppRecycler.setAdapter(allContactAdapter);
            if (maps != null) {
                maps.clear();
                //
                maps = null;
                System.gc();
            }
        }
    }


    private List<Map<String, String>> searchContacts(String selection, String[] selectionArgs, String sortOrder) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
        };
        cr = getContext().getContentResolver();
        Cursor cus = cr.query(uri, projection, selection, selectionArgs, sortOrder);
        if (cus == null) {
            return null;
        }
        List<Map<String, String>> mpSearch = null;
        while (cus.moveToNext()) {
            if (mpSearch == null) {
                mpSearch = new ArrayList<>();
            }
            String name = cus.getString(0);
            String number = cus.getString(1);


            number = number.replace(" ", "");
            if (number.length() > 11) {
                LocalLog.d(TAG, "非手机号码不处理");
                continue;
            }
            Map<String, String> maps = new HashMap<>();
            maps.put("name", name);
            maps.put("phone", number);
            mpSearch.add(maps);
        }
        cus.close();
        return mpSearch;
    }

    public List<Map<String, String>> getContacts(String selection, String[] selectionArgs, String sortOrder) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
        };
        cr = getContext().getContentResolver();
        Cursor cus = cr.query(uri, projection, selection, selectionArgs, sortOrder);
        if (cus == null) {
            return null;
        }
        while (cus.moveToNext()) {

            String name = cus.getString(0);
            String number = cus.getString(1);

            number = number.replace(" ", "");
            if (!PhoneNumberUtils.isGlobalPhoneNumber(number)) {
                LocalLog.d(TAG, "非手机号码不处理");
                continue;
            }
            Map<String, String> maps = new HashMap<>();
            maps.put("name", name);
            maps.put("phone", number);
            mp.add(maps);
        }
        cus.close();
        return mp;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.cancel_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_text:
                searchCircleText.setHint("搜索联系人电话、姓名");
                searchCircleText.setText(null);
                if (listShow != null && listShow.size() > 0) {
                    allContactAdapter = new LocalContactAdapter(getActivity(), listShow);
                    unRegAppRecycler.setAdapter(allContactAdapter);
                }
                break;
        }
    }
}
