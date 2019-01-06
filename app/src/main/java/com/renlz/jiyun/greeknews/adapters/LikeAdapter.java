package com.renlz.jiyun.greeknews.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.InfoActivity;
import com.renlz.jiyun.greeknews.activitys.SistersActivity;
import com.renlz.jiyun.greeknews.activitys.WxInfoActivity;
import com.renlz.jiyun.greeknews.beandao.DataBean;
import com.renlz.jiyun.greeknews.interceptor.MyCache;
import com.renlz.jiyun.greeknews.itemtouchhelper.ItemTouch;
import com.renlz.jiyun.greeknews.utils.Constants;
import com.renlz.jiyun.greeknews.utils.DaoUtil;
import com.renlz.jiyun.greeknews.utils.Utils;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2019/1/1.
 */

public class LikeAdapter extends RecyclerView.Adapter implements ItemTouch {

    private Context mContext;
    private List<DataBean> mList;
    public static int ITEM_TYPE_ZHIHU = 1;
    public static int ITEM_TYPE_GRIL = 2;


    public LikeAdapter(Context context, List<DataBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_ZHIHU) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_like_zhihu, parent, false);
            return new ZhiHuViewHoler(inflate);
        } else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_like_gril, parent, false);
            return new GrilViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ZhiHuViewHoler) {
            int itemTpe = mList.get(position).getItemTpe();
            ZhiHuViewHoler holder1 = (ZhiHuViewHoler) holder;
            switch (itemTpe) {
                case Constants.TYPE_ZHIHU:
                    Glide.with(mContext).load(mList.get(position).getUrl()).into(holder1.mIm);
                    holder1.mTvtitle.setText(mList.get(position).getTitle());
                    holder1.mTv_from.setText("来自 知乎");
                    holder1.itemView.setOnClickListener(v -> {
                        gotozhihu(mList.get(position).getMid());
                    });
                    break;
                case Constants.TYPE_WEIXIN:
                    Glide.with(mContext).load(mList.get(position).getUrl()).into(holder1.mIm);
                    holder1.mTvtitle.setText(mList.get(position).getTitle());
                    holder1.mTv_from.setText("来自 微信");
                    holder1.itemView.setOnClickListener(v -> {
                        Intent intent = new Intent(mContext, WxInfoActivity.class);
                        intent.putExtra("image", mList.get(position).getUrl());
                        intent.putExtra("title", mList.get(position).getTitle());
                        intent.putExtra("type", Constants.TYPE_WEIXIN);
                        intent.putExtra("url", mList.get(position).getWebUrl());
                        mContext.startActivity(intent);
                    });
                    break;
                case Constants.TYPE_ANDROID:
                    Glide.with(mContext).load(mList.get(position).getUrl()).into(holder1.mIm);
                    holder1.mTvtitle.setText(mList.get(position).getTitle());
                    holder1.mTv_from.setText("来自 Android");
                    holder1.itemView.setOnClickListener(v -> {
                        gotoGanHuo(
                                mList.get(position).getTitle(),
                                mList.get(position).getUrl(),
                                Constants.TYPE_ANDROID,
                                mList.get(position).getWebUrl()
                        );
                    });
                    break;
                case Constants.TYPE_IOS:
                    Glide.with(mContext).load(mList.get(position).getUrl()).into(holder1.mIm);
                    holder1.mTvtitle.setText(mList.get(position).getTitle());
                    holder1.mTv_from.setText("来自 IOS");
                    holder1.itemView.setOnClickListener(v -> {
                        gotoGanHuo(
                                mList.get(position).getTitle(),
                                mList.get(position).getUrl(),
                                Constants.TYPE_IOS,
                                mList.get(position).getWebUrl()
                        );
                    });
                    break;
                case Constants.TYPE_FRONT:
                    Glide.with(mContext).load(mList.get(position).getUrl()).into(holder1.mIm);
                    holder1.mTvtitle.setText(mList.get(position).getTitle());
                    holder1.mTv_from.setText("来自 前端");
                    holder1.itemView.setOnClickListener(v -> {
                        gotoGanHuo(
                                mList.get(position).getTitle(),
                                mList.get(position).getUrl(),
                                Constants.TYPE_FRONT,
                                mList.get(position).getWebUrl()
                        );
                    });
                    break;
            }

        } else if (holder instanceof GrilViewHolder) {
            GrilViewHolder holder1 = (GrilViewHolder) holder;
            Glide.with(mContext).load(mList.get(position).getUrl()).into(holder1.mIm);
            holder1.mTv_from1.setText("来自 福利");
            holder1.itemView.setOnClickListener(v -> {
                gotoSisters(mList.get(position).getMid(), mList.get(position).getUrl());
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addList(List<DataBean> list) {
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
        DaoUtil.getInstance().dataDelete(mList.get(position));
        mList.remove(position);
        notifyDataSetChanged();
    }

    class ZhiHuViewHoler extends RecyclerView.ViewHolder {

        private final ImageView mIm;
        private final TextView mTvtitle;
        private final TextView mTv_from;

        public ZhiHuViewHoler(View itemView) {
            super(itemView);
            mTvtitle = itemView.findViewById(R.id.title_liek);
            mIm = itemView.findViewById(R.id.im_like_zhihu);
            mTv_from = itemView.findViewById(R.id.tv1_like_zhihu);
        }
    }

    class GrilViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIm;
        private final TextView mTv_from1;

        public GrilViewHolder(View itemView) {
            super(itemView);
            mIm = itemView.findViewById(R.id.im_like_gril);
            mTv_from1 = itemView.findViewById(R.id.tv_like_gril);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).getItemTpe() == Constants.TYPE_GRIL) {
            return ITEM_TYPE_GRIL;
        }
        return ITEM_TYPE_ZHIHU;
    }

    public void gotoSisters(int id, String url) {
        Intent intent = new Intent(mContext, SistersActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("sister", url);
        mContext.startActivity(intent);
    }

    public void gotozhihu(int id) {
        Intent intent = new Intent(mContext, InfoActivity.class);
        intent.putExtra("bean", id);
        mContext.startActivity(intent);
    }

    public void gotoGanHuo(String title, String image, int type, String url) {
        Intent intent = new Intent(mContext, WxInfoActivity.class);
        intent.putExtra("image", image);
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        intent.putExtra("url", url);
        mContext.startActivity(intent);
    }
}
