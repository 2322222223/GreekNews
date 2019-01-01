package com.renlz.jiyun.greeknews.itemtouchhelper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Administrator on 2018/12/28.
 */

public class MyItemTouchHelperAdapter extends ItemTouchHelper.Callback {


    private ItemTouch mItemTouch;

    public MyItemTouchHelperAdapter(ItemTouch itemTouch){
        mItemTouch = itemTouch;
    }
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int flagsX = ItemTouchHelper.LEFT;
        int flagsY = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(flagsY,flagsX);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mItemTouch.ItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mItemTouch.ItemDelete(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
}
