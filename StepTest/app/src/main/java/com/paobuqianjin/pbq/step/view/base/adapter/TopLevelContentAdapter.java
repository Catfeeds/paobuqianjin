package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/31.
 */

public class TopLevelContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = TopLevelContentAdapter.class.getSimpleName();
    private final static int defaultCount = 7;
    @Bind(R.id.content_user_icon)
    CircleImageView contentUserIcon;
    @Bind(R.id.user_content_name)
    TextView userContentName;
    @Bind(R.id.user_content_ranka)
    TextView userContentRanka;
    @Bind(R.id.time_content_a)
    TextView timeContentA;
    @Bind(R.id.contend_all_recycler)
    RecyclerView contendAllRecycler;
    private Context context;

    public TopLevelContentAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopLevelViewHolder(LayoutInflater.from(context).inflate(
                R.layout.content_first_support, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return defaultCount;
    }

    public class TopLevelViewHolder extends RecyclerView.ViewHolder {
        private LinearLayoutManager layoutManager;

        public TopLevelViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            LocalLog.d(TAG, "");
            contendAllRecycler = (RecyclerView) view.findViewById(R.id.contend_all_recycler);
            layoutManager = new LinearLayoutManager(context);
            contendAllRecycler.setLayoutManager(layoutManager);
            contendAllRecycler.setAdapter(new MiddleContentAdapter(context));
        }
    }
}
