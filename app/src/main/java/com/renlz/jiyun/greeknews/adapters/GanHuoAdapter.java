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
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.beans.GanHuoList;
import com.renlz.jiyun.greeknews.beans.SisterList;
import com.renlz.jiyun.greeknews.itemtouchhelper.ItemTouch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/12/29.
 */

public class GanHuoAdapter extends XRecyclerView.Adapter implements ItemTouch {
    private Context mContext;
    private ArrayList<GanHuoList.ResultsBean> mList;
    private String mUrl;
    private final int ONE_VIEW_TYPE = 0;
    private final int TWO_VIEW_TYPE = 1;




    public GanHuoAdapter(Context context, ArrayList<GanHuoList.ResultsBean> list, ArrayList<SisterList.ResultsBean> list1) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ONE_VIEW_TYPE) {
            View view1 = View.inflate(mContext, R.layout.item_header_image, null);
            return new Headerview(view1);
        } else {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ganhuo, parent, false);
            return new MyHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Headerview) {
            Headerview holder1 = (Headerview) holder;
            Glide.with(mContext).load("http://ww1.sinaimg.cn/large/0065oQSqly1fsfq2pwt72j30qo0yg78u.jpg").into(holder1.mIm);
        } if(holder instanceof MyHolder){
            MyHolder holder1 = (MyHolder) holder;
            holder1.mTitle.setText(mList.get(position).getDesc());
            holder1.mNmae.setText(mList.get(position).getWho());
            holder1.mTime.setText(mList.get(position).getPublishedAt());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size()+1;
    }

    public void addList(List<GanHuoList.ResultsBean> list) {
        mList.addAll(list);
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

    public void addString(String url) {

        mUrl = url;
        notifyDataSetChanged();
    }


    class MyHolder extends XRecyclerView.ViewHolder {

        private final TextView mTitle;
        private final TextView mNmae;
        private final TextView mTime;

        public MyHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_title);
            mNmae = itemView.findViewById(R.id.tv_name);
            mTime = itemView.findViewById(R.id.tv_time);

        }
    }


    class Headerview extends XRecyclerView.ViewHolder {

        private final ImageView mIm;

        public Headerview(View itemView) {
            super(itemView);
            mIm = itemView.findViewById(R.id.header_im);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ONE_VIEW_TYPE;
        } else {
            return TWO_VIEW_TYPE;
        }
    }
}
