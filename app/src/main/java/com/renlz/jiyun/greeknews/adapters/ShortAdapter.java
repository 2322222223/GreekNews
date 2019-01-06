package com.renlz.jiyun.greeknews.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.beans.ShortCommentes;
import com.renlz.jiyun.greeknews.utils.DateUtil;
import com.renlz.jiyun.greeknews.utils.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/1/2.
 */

public class ShortAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<ShortCommentes.CommentsBean> mList;
    private String TAG = "          sdfsf";
    private OnClickListener mOnClickListener;
    boolean isclicks = false;

    public ShortAdapter(Context context, ArrayList<ShortCommentes.CommentsBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_short, null);
        return new Myholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Myholder holder1 = (Myholder) holder;
        Glide.with(mContext).load(mList.get(position).getAvatar()).apply(new RequestOptions().circleCrop()).into(holder1.mImtx);
        holder1.mName.setText(mList.get(position).getAuthor());
        holder1.mContent.setText(mList.get(position).getContent());
        if (mList.get(position).getReply_to() != null) {
            holder1.mNameto.setText("@" + mList.get(position).getReply_to().getAuthor() + ":");
            holder1.mContentto.setText(mList.get(position).getReply_to().getContent());
        }

        long time = (long)mList.get(position).getTime();
        holder1.mTime.setText(DateUtil.formatTime2String(mList.get(position).getTime()));
        holder1.mCount.setText(mList.get(position).getLikes() + "");

        if (holder1.mContentto.getLineCount() > 2) {
            holder1.mOpen.setVisibility(View.VISIBLE);
        } else {
            holder1.mOpen.setVisibility(View.GONE);
        }
        holder1.mOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isclicks) {
                    isclicks = true;
                    holder1.mContentto.setMaxLines(15);
                    holder1.mOpen.setText("收起");
                } else {
                    isclicks = false;
                    holder1.mContentto.setMaxLines(2);
                    holder1.mOpen.setText("展开");
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addList(List<ShortCommentes.CommentsBean> comments) {
        mList.addAll(comments);
        notifyDataSetChanged();
    }

    class Myholder extends RecyclerView.ViewHolder {

        private final ImageView mImtx;
        private final TextView mName;
        private final TextView mContent;
        private final TextView mNameto;
        private final TextView mTime;
        private final TextView mOpen;
        private final TextView mContentto;
        private final TextView mCount;

        public Myholder(View itemView) {
            super(itemView);
            mImtx = itemView.findViewById(R.id.im_short_hd);
            mName = itemView.findViewById(R.id.tv_short_name);
            mContent = itemView.findViewById(R.id.tv_content);
            mNameto = itemView.findViewById(R.id.tv_name_to);
            mContentto = itemView.findViewById(R.id.tv_content_to);
            mTime = itemView.findViewById(R.id.tv_time);
            mOpen = itemView.findViewById(R.id.tv_open);
            mCount = itemView.findViewById(R.id.like_count);
        }
    }

    interface OnClickListener {
        void OnClick(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {

        mOnClickListener = onClickListener;
    }
}
