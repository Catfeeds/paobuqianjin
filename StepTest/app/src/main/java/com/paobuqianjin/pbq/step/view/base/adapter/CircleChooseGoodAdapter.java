package com.paobuqianjin.pbq.step.view.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2017/12/13.
 */

public class CircleChooseGoodAdapter extends RecyclerView.Adapter<CircleChooseGoodAdapter.CircleChooseViewHolder> {
    private final static String TAG = CircleChooseGoodAdapter.class.getSimpleName();

    private Context mContext;
    private final static int defaultCount = 3;
    private ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> data = new ArrayList<>();
    private ChoiceCircleResponse.DataBeanX.DataBean tmpData;
    private JoinCircleParam joinCircleParam;
    private final static String ACTION_ENTER_ICON = "coma.paobuqian.pbq.step.ICON_ACTION";
    InOutCallBackInterface inOutCallBackInterface;
    CirclePassDialog circlePassDialog;

    public CircleChooseGoodAdapter(final Activity activity, Context context,
                                   ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> data, InOutCallBackInterface inOutCallBackInterface) {
        super();
        mContext = context;
        this.data = data;
        this.inOutCallBackInterface = inOutCallBackInterface;
    }

    @Override
    public CircleChooseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CircleChooseViewHolder holder = new CircleChooseViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.circle_choose_list,
                        parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(CircleChooseViewHolder holder, int position) {
        updateCircleList(holder, position);
    }

    private void updateCircleList(CircleChooseViewHolder holder, int position) {
        LocalLog.d(TAG, "updateCircleList() enter" + data.get(position).toString());

        tmpData = data.get(position);
        LocalLog.d(TAG, "city = " + tmpData.getCity() +
                ", name =" + tmpData.getName() + "logo url = " + tmpData.getLogo() + " ,member_number ="
                + tmpData.getMember_number());
        Presenter.getInstance(mContext).getImage(holder.circleLogo, tmpData.getLogo());
        holder.circleLocation.setText(tmpData.getCity());
        holder.circleName.setText(tmpData.getName());
        String sAgeFormat = mContext.getResources().getString(R.string.member_number);
        String sFinalMember = String.format(sAgeFormat, tmpData.getMember_number());
        holder.circleNumDesc.setText(sFinalMember);
        if (tmpData.getIs_pwd() == 1) {
            holder.lock.setVisibility(View.VISIBLE);
            holder.is_pwd = true;
        }
        if (tmpData.getIs_join() == 0) {
            LocalLog.d(TAG, "未在该圈可以加入");
            holder.circleJoin.setText("加入");
            holder.circleJoin.setTextColor(mContext.getResources().getColor(R.color.color_6c71c4));
            holder.circleJoin.setBackgroundResource(R.drawable.rectangle_four_dp);
            holder.circleJoin.setOnClickListener(holder.onClickListener);
        } else if (tmpData.getIs_join() == 1) {
            holder.circleJoin.setText("已加入");
            holder.circleJoin.setTextColor(mContext.getResources().getColor(R.color.color_9999));
            holder.circleJoin.setBackgroundResource(R.drawable.round5_gray999999);
            holder.is_join = true;
        }
        holder.setCircleId(tmpData.getCircleid());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    class CircleChooseViewHolder extends RecyclerView.ViewHolder {
        boolean getIs_pwd() {
            return is_pwd;
        }

        void setIs_pwd(boolean is_pwd) {
            this.is_pwd = is_pwd;
        }

        boolean is_pwd;

        public int getCircleId() {
            return circleId;
        }

        int circleId;

        ImageView circleLogo;

        TextView circleLocation;

        RelativeLayout circleLog;

        TextView circleName;

        TextView circleNumDesc;

        RelativeLayout circleChooseDesc;

        Button circleJoin;

        ImageView lock;
        boolean is_join;


        public CircleChooseViewHolder(View view) {
            super(view);
            init(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (is_join) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, CirCleDetailActivity.class);
                        intent.setAction(ACTION_ENTER_ICON);
                        intent.putExtra(mContext.getPackageName() + "circleid", getCircleId());
                        mContext.startActivity(intent);
                    } else {
                        if (is_pwd) {
                            if (joinCircleParam == null) {
                                joinCircleParam = new JoinCircleParam();
                            }
                            joinCircleParam.setCircleid(circleId);
                            popPassWordEdit();
                        } else {
                            Intent intent = new Intent();
                            intent.setClass(mContext, CirCleDetailActivity.class);
                            intent.setAction(ACTION_ENTER_ICON);
                            intent.putExtra(mContext.getPackageName() + "circleid", getCircleId());
                            mContext.startActivity(intent);
                        }
                    }
                }
            });
        }

        private void init(View view) {
            circleLogo = (ImageView) view.findViewById(R.id.circle_logo);
            circleLocation = (TextView) view.findViewById(R.id.circle_location);
            circleName = (TextView) view.findViewById(R.id.circle_name);
            circleNumDesc = (TextView) view.findViewById(R.id.circle_num_desc);
            circleJoin = (Button) view.findViewById(R.id.circle_join);
            lock = (ImageView) view.findViewById(R.id.lock);
        }


        public void popPassWordEdit() {
            if (circlePassDialog == null) {
                circlePassDialog = new CirclePassDialog(mContext);
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
                        if(TextUtils.isEmpty(passWord)){
                            return;
                        }
                        if (joinCircleParam == null) {
                            joinCircleParam = new JoinCircleParam();
                        }
                        joinCircleParam.setPassword(passWord);
                        Presenter.getInstance(mContext).attachUiInterface(joinCircleInterface);
                        Presenter.getInstance(mContext).joinCircle(joinCircleParam);
                    }
                });
            }
            if (!circlePassDialog.isShowing())
                circlePassDialog.show();
        }

        private JoinCircleInterface joinCircleInterface = new  JoinCircleInterface() {
            @Override
            public void response(JoinCircleResponse joinCircleResponse) {
                LocalLog.d(TAG, " JoinCircleResponse() " + joinCircleResponse.toString());
                if (joinCircleResponse.getError() == 0) {
                    LocalLog.d(TAG, "加入成功");
                    PaoToastUtils.showLongToast(mContext,"加入成功");
                    circleJoin.setText("已加入");
                    circleJoin.setTextColor(mContext.getResources().getColor(R.color.color_9999));
                    circleJoin.setBackgroundResource(R.drawable.round5_gray999999);
                    is_join = true;
                    if (inOutCallBackInterface != null) {
                        inOutCallBackInterface.inCallBack(circleId);
                    }
                } else if (joinCircleResponse.getError() == -1) {
                    Toast.makeText(mContext, joinCircleResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
                Presenter.getInstance(mContext).dispatchUiInterface(this);
            }

            @Override
            public void response(ErrorCode errorCode) {
                if (errorCode.getMessage().equals("请输入密码")) {
                    popPassWordEdit();
                } else {
                    Toast.makeText(mContext, errorCode.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };

        public JoinCircleInterface getJoinCircleInterface() {
            return joinCircleInterface;
        }

        private void setJoinCircleInterface(JoinCircleInterface joinCircleInterface) {
            this.joinCircleInterface = joinCircleInterface;
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.circle_join:
                        switch (circleJoin.getText().toString()) {
                            case "加入":
                                LocalLog.d(TAG, "点击加入+++");
                                if (joinCircleParam == null) {
                                    joinCircleParam = new JoinCircleParam();
                                }
                                joinCircleParam.setCircleid(circleId);
                                if (is_pwd) {
                                    LocalLog.d(TAG, "输入密码");
                                    popPassWordEdit();
                                    return;
                                } else {

                                }
                                Presenter.getInstance(mContext).attachUiInterface(joinCircleInterface);
                                Presenter.getInstance(mContext).joinCircle(joinCircleParam);
                                break;
                        }
                        break;
                    case R.id.confirm_text:
                        LocalLog.d(TAG, "确定");
                        break;
                    case R.id.cancel_text:
                        LocalLog.d(TAG, "取消");
                        break;
                    default:
                        break;
                }
            }
        };

        void setCircleId(int circleId) {
            this.circleId = circleId;
        }
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 0;
            } else {
                outRect.bottom = mSpace;
            }
            if (parent.getChildAdapterPosition(view) == defaultCount - 1) {
/*                outRect.bottom = 0;
                LocalLog.d(TAG, "getItemOffsets() last set");*/
            } else {
                outRect.bottom = mSpace;
            }
        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }
}
