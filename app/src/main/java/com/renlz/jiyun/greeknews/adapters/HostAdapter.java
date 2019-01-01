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
import com.renlz.jiyun.greeknews.beans.HostList;
import com.renlz.jiyun.greeknews.itemtouchhelper.ItemTouch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/12/28.
 */

public class HostAdapter extends RecyclerView.Adapter implements ItemTouch{
    private Context mContext;
    private ArrayList<HostList.RecentBean> mList;

    public HostAdapter(Context context, ArrayList<HostList.RecentBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_host, parent, false);
        return new Myholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Myholder holder1 = (Myholder) holder;
        Glide.with(mContext).load(mList.get(position).getThumbnail()).into(holder1.mIm);
        holder1.mTv.setText(mList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addList(List<HostList.RecentBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void ItemMove(int fromposition, int toposition) {
        Collections.swap(mList,fromposition,toposition);
        notifyItemMoved(fromposition,toposition);
    }

    @Override
    public void ItemDelete(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    class Myholder extends RecyclerView.ViewHolder {

        private final ImageView mIm;
        private final TextView mTv;

        public Myholder(View itemView) {
            super(itemView);
            mIm = itemView.findViewById(R.id.im_host);
            mTv = itemView.findViewById(R.id.tv_host);
        }
    }
}
