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
import com.renlz.jiyun.greeknews.beans.SectionList;
import com.renlz.jiyun.greeknews.itemtouchhelper.ItemTouch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/12/27.
 */

public class ZhuanLanAdapter extends XRecyclerView.Adapter implements ItemTouch {
    private Context mContext;
    public ArrayList<SectionList.DataBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public ZhuanLanAdapter(Context context, ArrayList<SectionList.DataBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zhuanlan, parent, false);
        return new Myolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Myolder holder1 = (Myolder) holder;
        Glide.with(mContext).load(mList.get(position).getThumbnail()).into(holder1.mIm);
        holder1.mTitle.setText(mList.get(position).getDescription());
        holder1.mType.setText(mList.get(position).getName());
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

    public void addList(List<SectionList.DataBean> list) {
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

    class Myolder extends XRecyclerView.ViewHolder {

        private final ImageView mIm;
        private final TextView mTitle;
        private final TextView mType;

        public Myolder(View itemView) {
            super(itemView);
            mIm = itemView.findViewById(R.id.im_zhuanlan);
            mTitle = itemView.findViewById(R.id.title_zhuanlan);
            mType = itemView.findViewById(R.id.type_zhuanlan);
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        mOnItemClickListener = onItemClickListener;
    }
}
