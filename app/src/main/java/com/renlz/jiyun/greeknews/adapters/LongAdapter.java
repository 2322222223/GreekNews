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
import com.bumptech.glide.request.RequestOptions;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.beans.LongComments;
import com.renlz.jiyun.greeknews.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/3.
 */

public class LongAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<LongComments.CommentsBean> mList;

    public LongAdapter(Context context, ArrayList<LongComments.CommentsBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_long, null);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder holder1 = (MyHolder) holder;
        Glide.with(mContext).load(mList.get(position).getAvatar()).apply(new RequestOptions().circleCrop()).into(holder1.mIm);
        holder1.mTvcontent.setText(mList.get(position).getContent());
        holder1.mTvname.setText(mList.get(position).getAuthor());
        holder1.mTvtime.setText(DateUtil.formatTime2String(mList.get(position).getTime()));
        holder1.mTvcount.setText(mList.get(position).getLikes()+"");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addList(List<LongComments.CommentsBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView mIm;
        private final TextView mTvcontent;
        private final TextView mTvtime;
        private final TextView mTvcount;
        private final TextView mTvname;

        public MyHolder(View itemView) {
            super(itemView);
            mIm = itemView.findViewById(R.id.im_long_hd);
            mTvcontent = itemView.findViewById(R.id.tv_long_content);
            mTvname = itemView.findViewById(R.id.tv_long_name);
            mTvtime = itemView.findViewById(R.id.tv_long_time);
            mTvcount = itemView.findViewById(R.id.long_likecount);
        }
    }
}
