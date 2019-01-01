package com.renlz.jiyun.greeknews.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.ZhanShiActivity;
import com.renlz.jiyun.greeknews.beandao.IsChecks;
import com.renlz.jiyun.greeknews.beans.TabBean;
import com.renlz.jiyun.greeknews.itemtouchhelper.ItemTouch;
import com.renlz.jiyun.greeknews.utils.DaoUtil;
import com.renlz.jiyun.greeknews.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/12/29.
 */

public class ZhanShiAdapter extends RecyclerView.Adapter implements ItemTouch {
    private Context mContext;
    private ArrayList<TabBean> mList;
    private OnClickListener mOnClickListener;

    public ZhanShiAdapter(Context context, ArrayList<TabBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_zhanshi, null);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyHolder holder1 = (MyHolder) holder;
        holder1.mTv.setText(mList.get(position).getTitle());
        holder1.mSw.setOnCheckedChangeListener(null);
        if (mList.get(position).isIsck()) {
            holder1.mSw.setChecked(true);
        } else {
            holder1.mSw.setChecked(false);
        }

        holder1.mSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mList.get(position).setIsck(isChecked);
            }
        });

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.OnClick(position, holder1.mSw.isChecked());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void ItemMove(int fromposition, int toposition) {
        Collections.swap(mList, fromposition, toposition);
        notifyItemMoved(fromposition, toposition);
    }

    @Override
    public void ItemDelete(int position) {

    }


    class MyHolder extends RecyclerView.ViewHolder {

        private final TextView mTv;
        private final SwitchCompat mSw;

        public MyHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv_zhanshi);
            mSw = itemView.findViewById(R.id.sw_zhanshi);
        }
    }

    public interface OnClickListener {
        void OnClick(int position, boolean isck);
    }

    public void setOnClickListener(OnClickListener onClickListener) {

        mOnClickListener = onClickListener;
    }
}
