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
import com.renlz.jiyun.greeknews.beans.NewestNew;
import com.renlz.jiyun.greeknews.itemtouchhelper.ItemTouch;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/12/27.
 */

public class RiBaoAdapter extends XRecyclerView.Adapter implements ItemTouch {
    private Context mContext;
    public ArrayList<NewestNew.StoriesBean> mList;
    private View mHeader;
    private OnItemClickListener mOnItemClickListener;

    public RiBaoAdapter(Context context, ArrayList<NewestNew.StoriesBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        XRecyclerView.ViewHolder holder = null;
        if (mHeader != null && viewType == 0) {
            holder = new Headerview(mHeader);
            return holder;
        } else {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ribao, parent, false);
            holder = new MyHolder(inflate);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == 0) {
            if (holder instanceof Headerview) {
                Headerview holder1 = (Headerview) holder;
                holder1.mTvbanner.setText(mList.get(position).getTitle());
            }
        } else {
            if (holder instanceof MyHolder) {
                MyHolder holder1 = (MyHolder) holder;
                Glide.with(mContext).load(mList.get(position).getImages().get(0)).into(holder1.mIm);
                holder1.mTv.setText(mList.get(position).getTitle());
                holder1.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.OnItemClick(position);
                        }
                    }
                });

                holder1.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.OnItemLongClick(position);
                        }
                        return false;
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addList(List<NewestNew.StoriesBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addHeader(View header) {

        mHeader = header;
        notifyItemInserted(0);
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

    public void setList(List<NewestNew.StoriesBean> list) {
        mList.clear();
        mList = (ArrayList<NewestNew.StoriesBean>) list;
        notifyDataSetChanged();
    }

    class MyHolder extends XRecyclerView.ViewHolder {

        private ImageView mIm;
        private TextView mTv;

        public MyHolder(View itemView) {
            super(itemView);
            mIm = itemView.findViewById(R.id.im_ribao);
            mTv = itemView.findViewById(R.id.tv_ribao);
        }
    }


    class Headerview extends XRecyclerView.ViewHolder {

        private TextView mTvbanner;

        public Headerview(View itemView) {
            super(itemView);
            if (itemView == mHeader) {
                mTvbanner = itemView.findViewById(R.id.tv_banner);
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);

        void OnItemLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        mOnItemClickListener = onItemClickListener;
    }
}
