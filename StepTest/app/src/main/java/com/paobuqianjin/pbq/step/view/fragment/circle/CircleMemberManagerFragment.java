package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.l.okhttppaobu.okhttp.OkHttpUtils;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutDearNameParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddDeleteAdminResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MemberDeleteResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.CircleMemberManagerInterface;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.DearNameModifyActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.CircleMemberBarAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.MemberManagerAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2017/12/18.
 */

public class CircleMemberManagerFragment extends BaseBarImageViewFragment implements CircleMemberManagerInterface {
    private final static String TAG = CircleMemberManagerFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    ImageView barTvRight;
    @Bind(R.id.search_icon)
    RelativeLayout searchIcon;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.admin_title_line)
    TextView adminTitleLine;
    @Bind(R.id.circle_admin_span)
    RecyclerView circleAdminSpan;
    @Bind(R.id.admin_line)
    ImageView adminLine;
    @Bind(R.id.all_member_span)
    RecyclerView allMemberSpan;
    @Bind(R.id.member_span)
    BounceScrollView memberSpan;
    @Bind(R.id.delete_member_confim)
    Button deleteMemberConfim;
    @Bind(R.id.cancel_icon)
    RelativeLayout cancelIcon;
    @Bind(R.id.circle_admin_span_rel)
    RelativeLayout circleAdminSpanRel;

    private RecyclerView adminRecyclerView, normalRecyclerView;
    private LinearLayoutManager adminManager, normalManager;
    private final static String MEMBER_MANANGER_ACTION = "android.intent.action.MAMBER_MANAGER_ACTION";
    private String id;
    private final static String CIRCLE_ID = "id";
    ArrayList<CircleMemberBarAdapter.AdapterCallInterface> adapterCallInterface;
    ArrayList<String> deleteArrList;
    private static final int DEAR_NAME_MODIFY = 0;
    private int pageIndex = 1, pageCount = 0, pageIndexSearch = 1, pageCountSearch = 0;
    private final static int PAGESIZE = 15;
    private boolean hasDelete = false;
    private int currentMember = 0;
    private int position = -1;
    private boolean isLoadingData = false;
    private MemberManagerAdapter adminAdapter, normalAdapter, searchAdapter;
    private ArrayList<CircleMemberResponse.DataBeanX.DataBean> mainAdminList = new ArrayList<>(),
            searchList = new ArrayList<>(), normalMemberList = new ArrayList<>();

    private String keyWord = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_member_manager_fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(context).attachUiInterface(this);
    }

    @Override
    public Object right() {
        return getDrawableResource(R.drawable.delete_icon);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View viewRoot) {
        setToolBarListener(toolBarListener);
        adminRecyclerView = (RecyclerView) viewRoot.findViewById(R.id.circle_admin_span);
        normalRecyclerView = (RecyclerView) viewRoot.findViewById(R.id.all_member_span);
        adminManager = new LinearLayoutManager(getContext());
        normalManager = new LinearLayoutManager(getContext());
        adminRecyclerView.setLayoutManager(adminManager);
        normalRecyclerView.setLayoutManager(normalManager);
        adminRecyclerView.setHasFixedSize(true);
        adminRecyclerView.setNestedScrollingEnabled(false);
        normalRecyclerView.setHasFixedSize(true);
        normalRecyclerView.setNestedScrollingEnabled(false);
        barTvRight = (ImageView) viewRoot.findViewById(R.id.bar_tv_right);
        deleteMemberConfim = (Button) viewRoot.findViewById(R.id.delete_member_confim);
        deleteMemberConfim.setOnClickListener(onClickListener);
        adapterCallInterface = new ArrayList<>();
        deleteArrList = new ArrayList<>();
        memberSpan = (BounceScrollView) viewRoot.findViewById(R.id.member_span);
        memberSpan.setTopBottomListener(new BounceScrollView.TopBottomListener() {
            @Override
            public void topBottom(int topOrBottom) {
                if (topOrBottom == 0) {

                } else if (topOrBottom == 1) {
                    if (!TextUtils.isEmpty(keyWord)) {
                        if (pageIndexSearch <= pageCountSearch && !isLoadingData)
                            Presenter.getInstance(getContext()).searchCircleMember(id, pageIndexSearch, PAGESIZE, keyWord, searchCallBack);
                    } else {
                        if (pageIndex <= pageCount && !isLoadingData) {
                            isLoadingData = true;
                            Presenter.getInstance(getActivity()).getCircleMemberAll(Integer.parseInt(id), pageIndex, PAGESIZE);
                        } else {
                            LocalLog.d(TAG, "正在加载或没有更多数据了");
                        }
                    }
                }
            }
        });
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (MEMBER_MANANGER_ACTION.equals(intent.getAction())) {
                Bundle bundle = intent.getBundleExtra(getContext().getPackageName());
                id = bundle.getString(CIRCLE_ID, "");
                LocalLog.d(TAG, "成员管理 circleid = " + id);
                pageIndex = 1;
                Presenter.getInstance(getActivity()).getCircleMemberAll(Integer.parseInt(id), pageIndex, PAGESIZE);
                position = intent.getIntExtra(getContext().getPackageName() + "position", -1);
            }
        }

        searchIcon = (RelativeLayout) viewRoot.findViewById(R.id.search_icon);
        searchCircleText = (EditText) viewRoot.findViewById(R.id.search_circle_text);
        searchCircleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    pageIndexSearch = 1;
                    searchKeyWord(searchCircleText.getText().toString());
                    Utils.hideInput(getContext());
                }
                return false;
            }
        });
        searchCircleText.addTextChangedListener(textWatcher);
        cancelIcon = (RelativeLayout) viewRoot.findViewById(R.id.cancel_icon);
        cancelIcon.setOnClickListener(onClickListener);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String temp = s.toString();
            if (!TextUtils.isEmpty(temp)) {
                LocalLog.d(TAG, "显示取消搜索界面");
                cancelIcon.setVisibility(View.VISIBLE);
                cancelIcon.setOnClickListener(onClickListener);
            } else {
                LocalLog.d(TAG, "隐藏搜索界面");
                cancelIcon.setVisibility(View.GONE);
                keyWord = "";
                pageCountSearch = 0;
                if (searchList.size() > 0 && isAdded()) {
                    searchList.clear();
                    normalRecyclerView.setAdapter(normalAdapter);
                }
            }
        }
    };

    @Override
    protected String title() {
        return "成员管理";
    }

    private void searchKeyWord(String keyWord) {
        this.keyWord = keyWord;
        if (Integer.parseInt(id) != -1) {
            Presenter.getInstance(getContext()).searchCircleMember(id, pageIndexSearch, PAGESIZE, keyWord, searchCallBack);
        }
    }

    private InnerCallBack searchCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (isAdded()) {
                if (object instanceof ErrorCode) {

                } else if (object instanceof CircleMemberResponse) {
                    if (((CircleMemberResponse) object).getError() == 0) {
                        LocalLog.d(TAG, "CircleMemberResponse() search " + ((CircleMemberResponse) object).toString());
                        pageCountSearch = ((CircleMemberResponse) object).getData().getPagenation().getTotalPage();
                        if (pageIndexSearch == 1) {
                            searchList.clear();
                        }
                        searchList.addAll(((CircleMemberResponse) object).getData().getData());
                        if (searchAdapter == null) {
                            searchAdapter = new MemberManagerAdapter(getContext(), searchList, opCallBackInterface);
                        } else {
                            searchAdapter.notifyDataSetChanged();
                        }
                        normalRecyclerView.setAdapter(searchAdapter);
                        pageIndexSearch++;
                    } else if (((CircleMemberResponse) object).getError() == 1) {
                        ToastUtils.showShortToast(getContext(), "没有相关成员");
                    } else if (((CircleMemberResponse) object).getError() == 100) {
                        exitTokenUnfect();
                    }
                }
            }
            isLoadingData = false;
        }
    };
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.delete_member_confim:
                    LocalLog.d(TAG, "删除成员!");
                    String idStr = "";
                    for (int i = 0; i < deleteArrList.size(); i++) {
                        if (i == (deleteArrList.size() - 1)) {
                            idStr += deleteArrList.get(i);
                        } else {
                            idStr += deleteArrList.get(i) + ",";
                        }
                    }
                    if (!TextUtils.isEmpty(idStr)) {
                        Presenter.getInstance(getContext()).deleteCircleMember(idStr);
                    }
                    break;
                case R.id.cancel_icon:
                    LocalLog.d(TAG, "取消搜索,显示原来的数据");
                    searchCircleText.setText(null);
                    keyWord = "";
                    pageCountSearch = 0;
                    pageIndexSearch = 1;
                    break;
            }
        }
    };

    private ToolBarListener toolBarListener = new ToolBarListener() {
        @Override
        public void clickLeft() {

        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "点击删除成员");
            deleteArrList.clear();
            for (int i = 0; i < adapterCallInterface.size(); i++) {
                adapterCallInterface.get(i).call();
            }
            if (deleteMemberConfim.getVisibility() == View.GONE) {
                deleteMemberConfim.setVisibility(View.VISIBLE);
            } else if (deleteMemberConfim.getVisibility() == View.VISIBLE) {
                deleteMemberConfim.setVisibility(View.GONE);
            }
        }
    };

    private ArrayList<CircleMemberResponse.DataBeanX.DataBean>[] getAdminList(CircleMemberResponse circleMemberResponse) {
        LocalLog.d(TAG, "getAdminList() enter");
        ArrayList<CircleMemberResponse.DataBeanX.DataBean>[] data = new ArrayList[3];
        data[0] = new ArrayList<>();
        data[1] = new ArrayList<>();
        data[2] = new ArrayList<>();
        for (int i = 0; i < circleMemberResponse.getData().getData().size(); i++) {
            if (circleMemberResponse.getData().getData().get(i).getIs_admin() == 2) {
                LocalLog.d(TAG, "主管理员");

                data[0].add(circleMemberResponse.getData().getData().get(i));
            } else if (circleMemberResponse.getData().getData().get(i).getIs_admin() == 1) {
                LocalLog.d(TAG, "普通管理员");

                data[1].add(circleMemberResponse.getData().getData().get(i));
            } else if (circleMemberResponse.getData().getData().get(i).getIs_admin() == 0) {
                LocalLog.d(TAG, "普通成员");
                data[2].add(circleMemberResponse.getData().getData().get(i));
            }
        }
        return data;
    }


    @Override
    public void response(CircleMemberResponse circleMemberResponse) {
        if (!isAdded()) {
            return;
        }
        if (circleMemberResponse.getError() == 0) {
            if (currentMember != 0 && circleMemberResponse.getData().getPagenation().getTotalCount() != currentMember) {
                Intent intent = new Intent();
                if (position != -1) {
                    intent.putExtra(getContext().getPackageName() + "memberNum", circleMemberResponse.getData().getPagenation().getTotalCount());
                    intent.putExtra(getContext().getPackageName() + "position", position);
                }
                getActivity().setResult(RESULT_OK, intent);
            }
            pageCount = circleMemberResponse.getData().getPagenation().getTotalPage();
            currentMember = circleMemberResponse.getData().getPagenation().getTotalCount();
            LocalLog.d(TAG, "circleMemberResponse() enter" + circleMemberResponse.toString());
            ArrayList<CircleMemberResponse.DataBeanX.DataBean>[] data = getAdminList(circleMemberResponse);
            /*MemberManagerAdapter adminAdapter = new MemberManagerAdapter(getContext(), data[0], data[1], opCallBackInterface);*/
            if (adminRecyclerView == null) {
                return;
            }
            if (pageIndex == 1) {
                mainAdminList.clear();
                normalMemberList.clear();
            }
            if (data[0] != null || data[1] != null) {
                ArrayList<CircleMemberResponse.DataBeanX.DataBean> toOneList = MemberManagerAdapter.toOneList(data[0], data[1]);
                mainAdminList.addAll(toOneList);
                if (pageIndex == 1) {
                    adminAdapter = new MemberManagerAdapter(getContext(), mainAdminList, null, opCallBackInterface);
                } else {
                    adminAdapter.notifyItemRangeInserted(mainAdminList.size() - toOneList.size(), toOneList.size());
                }
            }
            adminRecyclerView.setAdapter(adminAdapter);

            /*MemberManagerAdapter normalAdapter = new MemberManagerAdapter(getContext(), data[2], opCallBackInterface);*/
            if (data[2] != null) {
                normalMemberList.addAll(data[2]);
                if (pageIndex == 1) {
                    normalAdapter = new MemberManagerAdapter(getContext(), normalMemberList, opCallBackInterface);
                } else {
                    normalAdapter.notifyItemRangeInserted(normalMemberList.size() - data[2].size(), data[2].size());
                }
            }
            normalRecyclerView.setAdapter(normalAdapter);
            pageIndex++;
        } else if (circleMemberResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
        isLoadingData = false;
    }

    public interface OpCallBackInterface {
        public void opMemberOutInto(int id);

        public void opModifyDearName(int id);

        public void addDeleteAdmin(int id);

        public void opMemberIntoOut(CircleMemberBarAdapter.AdapterCallInterface adapterCallInterface);

        public void onLongClick();
    }

    private OpCallBackInterface opCallBackInterface = new OpCallBackInterface() {
        @Override
        public void opMemberOutInto(int id) {
            LocalLog.d(TAG, "opMemberOutInto() enter userId" + id);
            if (deleteArrList.contains(String.valueOf(id))) {
                LocalLog.d(TAG, "移除删除队列");
                deleteArrList.remove(String.valueOf(id));
            } else {
                LocalLog.d(TAG, "加入删除队列");
                deleteArrList.add(String.valueOf(id));
            }
        }

        @Override
        public void opMemberIntoOut(CircleMemberBarAdapter.AdapterCallInterface adapterCallInterface) {
            LocalLog.d(TAG, "opMemberIntoOut() enter");
            setAdapterCallInterface(adapterCallInterface);
        }

        @Override
        public void onLongClick() {
            LocalLog.d(TAG, "onLongClick() enter");
        }

        @Override
        public void opModifyDearName(int id) {
            LocalLog.d(TAG, "修改昵称 id  = " + id);
            Intent intent = new Intent();
            intent.setClass(getContext(), DearNameModifyActivity.class);
            intent.putExtra(getContext().getPackageName(), id);
            startActivityForResult(intent, DEAR_NAME_MODIFY);
        }

        @Override
        public void addDeleteAdmin(int id) {
            PutDearNameParam putDearNameParam = new PutDearNameParam();
            putDearNameParam
                    .setId(id)
                    .setAction("admin");
            Presenter.getInstance(getContext()).modifyDearName(putDearNameParam);
        }
    };

    public void setAdapterCallInterface(CircleMemberBarAdapter.AdapterCallInterface adapterCallInterface) {
        if (!this.adapterCallInterface.contains(adapterCallInterface)) {
            LocalLog.d(TAG, "setAdapterCallInterface() add a callback");
            this.adapterCallInterface.add(adapterCallInterface);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
        adapterCallInterface.clear();
        deleteArrList.clear();
    }

    @Override
    public void response(AddDeleteAdminResponse addDeleteAdminResponse) {
        LocalLog.d(TAG, "AddDeleteAdminResponse() enter " + addDeleteAdminResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (addDeleteAdminResponse.getError() == 0) {
            hasDelete = true;
            Toast.makeText(getContext(), addDeleteAdminResponse.getMessage(), Toast.LENGTH_SHORT).show();
            pageIndex = 1;
            Presenter.getInstance(getContext()).getCircleMemberAll(Integer.parseInt(id), pageIndex, PAGESIZE);
        } else if (addDeleteAdminResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(MemberDeleteResponse memberDeleteResponse) {
        LocalLog.d(TAG, "MemberDeleteResponse() enter" + memberDeleteResponse.toString());
        if (!isAdded()) {
            return;
        }
        //TODO 更新本地UI
        if (memberDeleteResponse.getError() == 0) {
            if (deleteMemberConfim == null) {
                return;
            }
            deleteMemberConfim.setVisibility(View.GONE);
            pageIndex = 1;
            Presenter.getInstance(getContext()).getCircleMemberAll(Integer.parseInt(id), pageIndex, PAGESIZE);
        } else if (memberDeleteResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocalLog.d(TAG, "resultCode = " + requestCode);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == DEAR_NAME_MODIFY) {
                    LocalLog.d(TAG, "昵称修改成功!");
                    Toast.makeText(getContext(), "昵称修改成功", Toast.LENGTH_SHORT).show();
                    pageIndex = 1;
                    Presenter.getInstance(getContext()).getCircleMemberAll(Integer.parseInt(id), pageIndex, PAGESIZE);
                    getActivity().setResult(RESULT_OK);
                    break;
                }
            default:
                LocalLog.d(TAG, "unknown");
                break;
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
