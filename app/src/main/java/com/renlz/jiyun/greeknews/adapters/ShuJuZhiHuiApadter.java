package com.renlz.jiyun.greeknews.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.beans.ShuJuZhiHuiList;
import com.renlz.jiyun.greeknews.itemtouchhelper.ItemTouch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public class ShuJuZhiHuiApadter extends RecyclerView.Adapter implements ItemTouch {
    private Context mContext;
    public ArrayList<ShuJuZhiHuiList.RESULTBean.NewsListBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public ShuJuZhiHuiApadter(Context context, ArrayList<ShuJuZhiHuiList.RESULTBean.NewsListBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ribao, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder holder1 = (MyHolder) holder;
        Glide.with(mContext).load(mList.get(position).getNewsImg()).into(holder1.mIm);
        holder1.mTv.setText(mList.get(position).getTitle());
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.OnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addList(List<ShuJuZhiHuiList.RESULTBean.NewsListBean> newsList) {
        mList.addAll(newsList);
        notifyDataSetChanged();
    }

    @Override
    public void ItemMove(int fromposition, int toposition) {
        Collections.swap(mList, fromposition, toposition);
        notifyItemMoved(fromposition, toposition);
    }

    @Override
    public void ItemDelete(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView mIm;
        private final TextView mTv;

        public MyHolder(View itemView) {
            super(itemView);
            mIm = itemView.findViewById(R.id.im_ribao);
            mTv = itemView.findViewById(R.id.tv_ribao);
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        mOnItemClickListener = onItemClickListener;
    }
}
