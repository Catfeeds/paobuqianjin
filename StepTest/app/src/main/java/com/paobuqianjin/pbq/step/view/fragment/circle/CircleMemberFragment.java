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

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MemberDeleteResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.DearNameModifyActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.CircleMemberBarAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.MemberManagerAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static com.paobuqianjin.pbq.step.utils.NetApi.urlMemberSearch;

/**
 * Created by pbq on 2018/11/5.
 */

public class CircleMemberFragment extends BaseBarImageViewFragment {
    private final static String TAG = CircleMemberFragment.class.getSimpleName();
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
    @Bind(R.id.admin_line)
    ImageView adminLine;
    @Bind(R.id.delete_member_confim)
    Button deleteMemberConfim;
    @Bind(R.id.cancel_icon)
    RelativeLayout cancelIcon;

    private SwipeMenuRecyclerView adminRecyclerView, normalRecyclerView;
    private LinearLayoutManager adminManager, normalManager;
    private final static String MEMBER_MANANGER_ACTION = "android.intent.action.MAMBER_MANAGER_ACTION";
    private String id;
    private final static String CIRCLE_ID = "id";
    ArrayList<CircleMemberBarAdapter.AdapterCallInterface> adapterCallInterface;
    ArrayList<String> deleteArrList;
    private static final int DEAR_NAME_MODIFY = 0;
    private int pageIndex = 1, pageCount = 0, pageIndexSearch = 1, pageCountSearch = 0;
    private final static int PAGESIZE = 10;
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
        return R.layout.circle_member_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
        adminRecyclerView = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.circle_admin_span);
        normalRecyclerView = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.all_member_span);
        adminManager = new LinearLayoutManager(getActivity()) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        normalManager = new LinearLayoutManager(getActivity()) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
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
        normalAdapter = new MemberManagerAdapter(getContext(), normalMemberList, opCallBackInterface);
        normalRecyclerView.setAdapter(normalAdapter);
        adminAdapter = new MemberManagerAdapter(getContext(), mainAdminList, null, opCallBackInterface);
        adminRecyclerView.setAdapter(adminAdapter);
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getActivity());
        normalRecyclerView.addFooterView(loadMoreView); // 添加为Footer。
        normalRecyclerView.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        normalRecyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (MEMBER_MANANGER_ACTION.equals(intent.getAction())) {
                Bundle bundle = intent.getBundleExtra(getContext().getPackageName());
                id = bundle.getString(CIRCLE_ID, "");
                LocalLog.d(TAG, "成员管理 circleid = " + id);
                getAllMember(1);
                position = intent.getIntExtra(getContext().getPackageName() + "position", -1);
            }
        }

        searchIcon = (RelativeLayout) viewRoot.findViewById(R.id.search_icon);
        searchCircleText = (EditText) viewRoot.findViewById(R.id.search_circle_text);
        searchCircleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    searchCircleMember(searchCircleText.getText().toString(), 1);
                    Utils.hideInput(getContext());
                }
                return false;
            }
        });
        searchCircleText.addTextChangedListener(textWatcher);
        cancelIcon = (RelativeLayout) viewRoot.findViewById(R.id.cancel_icon);
        cancelIcon.setOnClickListener(onClickListener);
        normalRecyclerView.loadMoreFinish(false, true);
    }


    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            LocalLog.d(TAG, "加载更多!");
            if (!isAdded())
                return;
            if (!TextUtils.isEmpty(keyWord)) {
                if (pageIndexSearch > pageCountSearch) {
                    PaoToastUtils.showLongToast(getContext(), "没有更多内容");
                    normalRecyclerView.loadMoreFinish(false, true);
                    normalRecyclerView.setLoadMoreView(null);
                    normalRecyclerView.setLoadMoreListener(null);
                    return;
                } else {
                    searchCircleMember(keyWord, pageIndexSearch + 1);
                }
            } else {
                if (pageIndex > pageCount) {
                    PaoToastUtils.showLongToast(getContext(), "没有更多内容");
                    normalRecyclerView.loadMoreFinish(false, true);
                    normalRecyclerView.setLoadMoreView(null);
                    normalRecyclerView.setLoadMoreListener(null);
                    return;
                } else {
                    getAllMember(pageIndex + 1);
                }
            }
        }
    };
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

    private void getAllMember(final int page) {
        pageIndex = page;
        if (!TextUtils.isEmpty(id) && Integer.parseInt(id) > 0) {
            String url = NetApi.urlCircleMember + "/" + String.valueOf(id)
                    + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(PAGESIZE);
            Presenter.getInstance(getContext()).getPaoBuSimple(url, null, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    if (!isAdded()) return;
                    try {
                        CircleMemberResponse circleMemberResponse = new Gson().fromJson(s, CircleMemberResponse.class);
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
                        normalRecyclerView.loadMoreFinish(false, true);
                        ArrayList<CircleMemberResponse.DataBeanX.DataBean>[] data = getAdminList(circleMemberResponse);
                        if (page == 1) {
                            mainAdminList.clear();
                            normalMemberList.clear();
                        }
                        if (data[0] != null || data[1] != null) {
                            ArrayList<CircleMemberResponse.DataBeanX.DataBean> toOneList = MemberManagerAdapter.toOneList(data[0], data[1]);
                            mainAdminList.addAll(toOneList);
                            if (page == 1) {
                                adminAdapter.notifyDataSetChanged();
                            } else {
                                adminAdapter.notifyItemRangeInserted(mainAdminList.size() - toOneList.size(), toOneList.size());
                            }
                        }

            /*MemberManagerAdapter normalAdapter = new MemberManagerAdapter(getContext(), data[2], opCallBackInterface);*/
                        if (data[2] != null) {
                            normalMemberList.addAll(data[2]);
                            if (page == 1) {
                                normalAdapter.notifyDataSetChanged();
                            } else {
                                normalAdapter.notifyItemRangeInserted(normalMemberList.size() - data[2].size(), data[2].size());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                }
            });
        } else {
            getActivity().finish();
        }
    }


    private void searchCircleMember(String keyWord, final int page) {
        this.keyWord = keyWord;
        pageIndexSearch = page;
        if (Integer.parseInt(id) > 0 && !TextUtils.isEmpty(keyWord)) {
            String ulr = urlMemberSearch + id + "&page=" + String.valueOf(page) + "&pagesize=" + String.valueOf(PAGESIZE) + "&keyword=" + keyWord;
            Presenter.getInstance(getContext()).getPaoBuSimple(ulr, null, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    try {
                        CircleMemberResponse circleMemberResponse = new Gson().fromJson(s, CircleMemberResponse.class);
                        pageCountSearch = circleMemberResponse.getData().getPagenation().getTotalPage();
                        if (page == 1) {
                            searchList.clear();
                        }
                        searchList.addAll(circleMemberResponse.getData().getData());
                        if (searchAdapter == null) {
                            searchAdapter = new MemberManagerAdapter(getContext(), searchList, opCallBackInterface);
                        } else {
                            searchAdapter.notifyDataSetChanged();
                        }
                        normalRecyclerView.setAdapter(searchAdapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                }
            });
        }
    }


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
                        deleteMember(idStr);
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

    private void deleteMember(final String idStr) {
        if (!TextUtils.isEmpty(idStr)) {
            String url = NetApi.urlCircleMember + "/" + idStr;
            Presenter.getInstance(getContext()).deletePaoBuSimple(url, null, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    if (!isAdded()) return;
                    try {
                        MemberDeleteResponse memberDeleteResponse = new Gson().fromJson(s, MemberDeleteResponse.class);
                        if (deleteMemberConfim == null) {
                            return;
                        }
                        deleteMemberConfim.setVisibility(View.GONE);
                        PaoToastUtils.showLongToast(getActivity(), "删除成功");
                        getAllMember(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                    if (errorBean != null) {
                        PaoToastUtils.showLongToast(getActivity(), errorBean.getMessage());
                    }
                }
            });
        }
    }

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
            setAdmin(id);
        }
    };


    private void setAdmin(final int id) {
        String url = NetApi.urlCircleMember + "/" + String.valueOf(id);
        Map<String, String> param = new HashMap<>();
        param.put("action", "admin");
        Presenter.getInstance(getContext()).putPaoBuSimple(url, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    hasDelete = true;
                    getAllMember(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(getActivity(), errorBean.getMessage());
                }
            }
        });
    }

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
        adapterCallInterface.clear();
        deleteArrList.clear();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocalLog.d(TAG, "resultCode = " + requestCode);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == DEAR_NAME_MODIFY) {
                    LocalLog.d(TAG, "昵称修改成功!");
                    PaoToastUtils.showLongToast(getContext(), "昵称修改成功");
                    getAllMember(1);
                    getActivity().setResult(RESULT_OK);
                    break;
                }
            default:
                LocalLog.d(TAG, "unknown");
                break;
        }
    }

}
