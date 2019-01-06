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
    public ArrayList<GanHuoList.ResultsBean> mList;
    private final int ONE_VIEW_TYPE = 0;
    private final int TWO_VIEW_TYPE = 1;
    private OnItemClickListener mOnItemClickListener;


    public GanHuoAdapter(Context context, ArrayList<GanHuoList.ResultsBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ONE_VIEW_TYPE) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_image, parent, false);
            return new Headerview(inflate);
        } else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_ganhuo, null);
            return new MyHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Headerview) {

        } else if (holder instanceof MyHolder) {
            MyHolder holder1 = (MyHolder) holder;
            holder1.mTitle.setText(mList.get(position).getDesc());
            holder1.mNmae.setText(mList.get(position).getWho());
            holder1.mTime.setText(mList.get(position).getPublishedAt());
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.OnItemClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
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

        public Headerview(View itemView) {
            super(itemView);

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

    public interface OnItemClickListener {
        void OnItemClick(int poisiton);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        mOnItemClickListener = onItemClickListener;
    }
}


