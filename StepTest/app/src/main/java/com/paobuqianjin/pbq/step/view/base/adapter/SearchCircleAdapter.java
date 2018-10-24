package com.paobuqianjin.pbq.step.view.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CirclePassDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.param.JoinCircleParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.JoinCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InOutCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.JoinCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.CirCleDetailActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pbq on 2017/12/15.
 */

public class SearchCircleAdapter extends RecyclerView.Adapter<SearchCircleAdapter.SearchCirCleViewHolder> {
    private final static String TAG = SearchCircleAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> data;
    private JoinCircleParam joinCircleParam;
    InOutCallBackInterface inOutCallBackInterface;
    private final static int REQUEST_DETAIL = 278;
    private final static String ACTION_ENTER_ICON = "coma.paobuqian.pbq.step.ICON_ACTION";
    private Fragment fragment;
    private CirclePassDialog circlePassDialog;

    public SearchCircleAdapter(Context context, final Activity activity, InOutCallBackInterface inOutCallBackInterface, final Fragment fragment) {
        super();
        mContext = context;
        this.inOutCallBackInterface = inOutCallBackInterface;
        this.fragment = fragment;
    }


    public void notifyDataSetChanged(ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> data) {
        this.data = data;
        super.notifyDataSetChanged();
    }

    @Override
    public SearchCirCleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SearchCirCleViewHolder holder = new SearchCirCleViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.circle_kan_ban_list, parent, false));
        return holder;
    }

    @Override
    public int getItemCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(SearchCirCleViewHolder holder, int position) {
        updateCircleList(holder, position);
    }

    private void updateCircleList(SearchCirCleViewHolder holder, int position) {
        if (data.get(position) instanceof ChoiceCircleResponse.DataBeanX.DataBean) {
            Presenter.getInstance(mContext).getImage(holder.circleLogoSearch, data.get(position).getLogo());
            holder.locationDescSearchList.setText(data.get(position).getCity());
            holder.searchCircleDesListName.setText(data.get(position).getName());
            String sAgeFormat = mContext.getResources().getString(R.string.member_number);
            String sFinalMember = String.format(sAgeFormat, data.get(position).getMember_number());
            holder.searchCircleDesListNum.setText(sFinalMember);
            holder.circleMember = data.get(position).getMember_number();
            if (data.get(position).getIs_pwd() == 1) {
                holder.lock.setVisibility(View.VISIBLE);
                holder.needPass = true;
            } else {
                holder.lock.setVisibility(View.GONE);
                holder.needPass = false;
            }
            if (data.get(position).getIs_join() == 0) {
                holder.joinIn.setText("加入");
                holder.isJoin = false;
                holder.joinIn.setBackground(ContextCompat.getDrawable(mContext, R.drawable.rectangle_four_dp));
            } else if (data.get(position).getIs_join() == 1) {
                holder.joinIn.setText("已加入");
                holder.isJoin = true;
                holder.joinIn.setBackground(ContextCompat.getDrawable(mContext, R.drawable.round5_gray999999));
            }
            holder.circleId = data.get(position).getCircleid();

            if (data.get(position).getIs_pwd() == 1) {
                holder.is_password = true;
            } else {
                holder.is_password = false;
            }
        }
    }


    class SearchCirCleViewHolder extends RecyclerView.ViewHolder {
        boolean needPass;
        ImageView circleLogoSearch;
        TextView searchCircleDesListName;
        ImageView lock;
        TextView searchCircleDesListNum;
        TextView locationDescSearchList;
        Button joinIn;
        int circleId;
        boolean is_password;
        int circleMember = 0;
        boolean isJoin = false;
        RelativeLayout item;

        public SearchCirCleViewHolder(View view) {
            super(view);
            init(view);
        }


        private JoinCircleInterface joinCircleInterface = new JoinCircleInterface() {
            @Override
            public void response(JoinCircleResponse joinCircleResponse) {
                if (joinCircleResponse.getError() == 0) {
                    LocalLog.d(TAG, "加入成功");
                    PaoToastUtils.showLongToast(mContext, "加入成功");
                    joinIn.setText("已加入");
                    isJoin = true;
                    data.get(getAdapterPosition()).setIs_join(1);
                    if (inOutCallBackInterface != null) {
                        inOutCallBackInterface.inCallBack(circleId);
                    }
                    int totalMember = data.get(getAdapterPosition()).getMember_number();
                    data.get(getAdapterPosition()).setMember_number(totalMember + 1);
                    String sAgeFormat = mContext.getResources().getString(R.string.member_number);
                    String sFinalMember = String.format(sAgeFormat, data.get(getAdapterPosition()).getMember_number());
                    searchCircleDesListNum.setText(sFinalMember);
                    joinIn.setBackground(ContextCompat.getDrawable(mContext, R.drawable.round5_gray999999));
                } else if (joinCircleResponse.getError() == -1) {
                    Toast.makeText(mContext, joinCircleResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }

                Presenter.getInstance(mContext).dispatchUiInterface(joinCircleInterface);
            }

            @Override
            public void response(ErrorCode errorCode) {
                if (errorCode.getMessage().equals("请输入密码")) {
                    popPassWordEdit(circleId);
                } else {
                    Toast.makeText(mContext, errorCode.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };

        private void init(View view) {
            item = (RelativeLayout) view.findViewById(R.id.list_item_search);
            circleLogoSearch = (ImageView) view.findViewById(R.id.circle_logo_search);
            circleLogoSearch.setOnClickListener(onClickListener);
            searchCircleDesListName = (TextView) view.findViewById(R.id.search_circle_des_list_name);
            lock = (ImageView) view.findViewById(R.id.lock);
            searchCircleDesListNum = (TextView) view.findViewById(R.id.search_circle_des_list_num);
            locationDescSearchList = (TextView) view.findViewById(R.id.location_desc_search_list);
            joinIn = (Button) view.findViewById(R.id.join_in);
            joinIn.setOnClickListener(onClickListener);
            item.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.list_item_search:
                        if (is_password && !isJoin) {
                            LocalLog.d(TAG, "需要密码");
                            popPassWordEdit(circleId);
                            return;
                        }
                        Intent intent = new Intent();
                        intent.setClass(mContext, CirCleDetailActivity.class);
                        intent.setAction(ACTION_ENTER_ICON);
                        LocalLog.d(TAG, "getAdapterPosition() = " + getAdapterPosition());
                        intent.putExtra(mContext.getPackageName() + "circleid", circleId);
                        intent.putExtra(mContext.getPackageName() + "position", getAdapterPosition());
                        fragment.startActivityForResult(intent, REQUEST_DETAIL);
                        break;
                    case R.id.join_in:
                        switch (joinIn.getText().toString()) {
                            case "加入":
                                LocalLog.d(TAG, "加入圈子");
                                joinIn.setEnabled(false);
                                joinIn.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        joinIn.setEnabled(true);
                                    }
                                }, 1000);
                                joinCircleParam = new JoinCircleParam();
                                joinCircleParam.setCircleid(circleId);
                                LocalLog.d(TAG, "circleId 1= " + circleId);
                                if (is_password) {
                                    LocalLog.d(TAG, "需要密码");
                                    popPassWordEdit(circleId);
                                    return;
                                }
                                Presenter.getInstance(mContext).attachUiInterface(joinCircleInterface);
                                Presenter.getInstance(mContext).joinCircle(joinCircleParam);
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        };


        public void popPassWordEdit(final int circleId) {
            LocalLog.d(TAG, "circleId = " + circleId);
            if (circlePassDialog == null) {
                circlePassDialog = new CirclePassDialog(mContext);
            }
            circlePassDialog.setNoOnclickListener(new CirclePassDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    circlePassDialog.dismiss();
                }
            });

            circlePassDialog.setYesOnclickListener(new CirclePassDialog.onYesOnclickListener() {
                @Override
                public void onYesClick(String passWord) {
                    circlePassDialog.dismiss();
                    if (TextUtils.isEmpty(passWord)) {
                        return;
                    }
                    joinCircleParam = new JoinCircleParam();
                    joinCircleParam.setPassword(passWord);
                    joinCircleParam.setCircleid(circleId);
                    Presenter.getInstance(mContext).attachUiInterface(joinCircleInterface);
                    Presenter.getInstance(mContext).joinCircle(joinCircleParam);
                }
            });
            if (!circlePassDialog.isShowing())
                circlePassDialog.show();
        }

    }

}
