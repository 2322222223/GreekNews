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
import com.renlz.jiyun.greeknews.activitys.ZlInfoActivity;
import com.renlz.jiyun.greeknews.beans.NewestNew;
import com.renlz.jiyun.greeknews.beans.ZhuanLanList;
import com.umeng.commonsdk.statistics.common.MLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/4.
 */

public class ZhuanLanListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public ArrayList<NewestNew.StoriesBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public ZhuanLanListAdapter(Context context, ArrayList<NewestNew.StoriesBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zhuanlanlist, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder holder1 = (MyHolder) holder;
        Glide.with(mContext).load(mList.get(position).getImages().get(0)).into(holder1.mIm);
        holder1.mTv.setText(mList.get(position).getTitle());
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.OnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addList(List<NewestNew.StoriesBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView mIm;
        private final TextView mTv;

        public MyHolder(View itemView) {
            super(itemView);
            mIm = itemView.findViewById(R.id.im_zl);
            mTv = itemView.findViewById(R.id.tv_zl);
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){

        mOnItemClickListener = onItemClickListener;
    }
}
