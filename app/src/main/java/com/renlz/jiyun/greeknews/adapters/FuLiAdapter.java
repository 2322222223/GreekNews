package com.renlz.jiyun.greeknews.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.beans.FuliList;
import com.renlz.jiyun.greeknews.myapp.MyApp;
import com.umeng.socialize.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/3.
 */

public class FuLiAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public ArrayList<FuliList.ResultsBean> mList;
    private OnItemClickListener mOnItemClicklistener;

    public FuLiAdapter(Context context, ArrayList<FuliList.ResultsBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_fuli, parent, false);
        return new MyHolser(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolser holder1 = (MyHolser) holder;
        WindowManager systemService = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = systemService.getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams imageLayoutParams = holder1.mIm.getLayoutParams();
        imageLayoutParams.width = width / 2;//获取实际展示的图片宽度
        imageLayoutParams.height = (int) (imageLayoutParams.width * 1.2);//获取最终图片高度
        holder1.mIm.setLayoutParams(imageLayoutParams);//应用高度到布局中
        Glide.with(mContext).load(mList.get(position).getUrl()).into(holder1.mIm);
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClicklistener != null) {
                    mOnItemClicklistener.OnItemClilck(position);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addList(List<FuliList.ResultsBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    class MyHolser extends RecyclerView.ViewHolder {

        private final ImageView mIm;

        public MyHolser(View itemView) {
            super(itemView);
            mIm = itemView.findViewById(R.id.im_fuli);
        }
    }

    public interface OnItemClickListener {
        void OnItemClilck(int position);
    }

    public void setOnItemClicklistener(OnItemClickListener onItemClicklistener) {

        mOnItemClicklistener = onItemClicklistener;
    }
}
