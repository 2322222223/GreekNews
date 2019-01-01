package com.renlz.jiyun.greeknews.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.beans.WxNews;
import com.renlz.jiyun.greeknews.itemtouchhelper.ItemTouch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/12/28.
 */

public class WxnewAdapter extends RecyclerView.Adapter implements ItemTouch{
    private Context mContext;
    public ArrayList<WxNews.NewslistBean> mList;
    private onItemClickListener mClickListener;


    public WxnewAdapter(Context context, ArrayList<WxNews.NewslistBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weixin, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyHolder holder1 = (MyHolder) holder;
        Glide.with(mContext).load(mList.get(position).getPicUrl()).into(holder1.mIm);
        holder1.mTitle.setText(mList.get(position).getTitle());
        holder1.mType.setText(mList.get(position).getDescription());
        holder1.mTime.setText(mList.get(position).getCtime());

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.ItemClick(position);
                }
            }
        });
    }

    public void setFilter(ArrayList<WxNews.NewslistBean> locationListModels ) {
        mList .addAll( locationListModels );
        notifyDataSetChanged();

    }




    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addList(List<WxNews.NewslistBean> list) {
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


    class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView mIm;
        private final TextView mTitle;
        private final TextView mType;
        private final TextView mTime;

        public MyHolder(View itemView) {
            super(itemView);
            mIm = itemView.findViewById(R.id.im_wx);
            mTitle = itemView.findViewById(R.id.tv_wxtitle);
            mType = itemView.findViewById(R.id.tv_wxtype);
            mTime = itemView.findViewById(R.id.tv_wxtime);
        }
    }

    public interface onItemClickListener {
        void ItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener ClickListener) {

        mClickListener = ClickListener;
    }
}
