package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

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

    public CircleChooseGoodAdapter(Context context, ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> data) {
        super();
        mContext = context;
        this.data = data;
    }

    @Override
    public CircleChooseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CircleChooseViewHolder holder = new CircleChooseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.circle_choose_list, parent, false));
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
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    class CircleChooseViewHolder extends RecyclerView.ViewHolder {

        ImageView circleLogo;

        TextView circleLocation;

        RelativeLayout circleLog;

        TextView circleName;

        TextView circleNumDesc;

        RelativeLayout circleChooseDesc;

        Button circleJoin;

        ImageView lock;


        public CircleChooseViewHolder(View view) {
            super(view);
            init(view);
        }

        private void init(View view) {
            circleLogo = (ImageView) view.findViewById(R.id.circle_logo);
            circleLocation = (TextView) view.findViewById(R.id.circle_location);
            circleName = (TextView) view.findViewById(R.id.circle_name);
            circleNumDesc = (TextView) view.findViewById(R.id.circle_num_desc);
            circleJoin = (Button) view.findViewById(R.id.circle_join);
            circleJoin.setOnClickListener(onClickListener);
            lock = (ImageView) view.findViewById(R.id.lock);

        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.circle_join:
                        LocalLog.d(TAG, "点击加入");
                        break;
                }
            }
        };
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
                outRect.bottom = 0;
                LocalLog.d(TAG, "getItemOffsets() last set");
            } else {
                outRect.bottom = mSpace;
            }
        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }
}
