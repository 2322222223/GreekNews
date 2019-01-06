package com.renlz.jiyun.greeknews.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.adapters.ZhanShiAdapter;
import com.renlz.jiyun.greeknews.base.activity.SimpleActivity;
import com.renlz.jiyun.greeknews.beans.TabBean;
import com.renlz.jiyun.greeknews.fragments.xitu.XiTuFragment;
import com.renlz.jiyun.greeknews.itemtouchhelper.MyItemTouchHelperAdapter;
import com.renlz.jiyun.greeknews.utils.C;
import com.renlz.jiyun.greeknews.utils.Event;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/29.
 */

public class ZhanShiActivity extends SimpleActivity {
    private PullLoadMoreRecyclerView mZsRecyclerview;
    private List<String> mList;
    /**
     * 特别展示页
     */
    private TextView mToolbarTitle;
    private Toolbar mToolbarZhanshi;
    private String TAG = "ZhanShiActivity";
    private ZhanShiAdapter mZhanShiAdapter;
    private SharedPreferences mSp;
    private ArrayList<String> mList1;
    private boolean isCb;
    private ArrayList<TabBean> mbeanlist;
    public static getListString mGetListString;


    @Override
    protected void initListener() {
        mToolbarZhanshi.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mGetListString!=null){
                    mGetListString.getlist(mbeanlist);
                }
                finish();
            }
        });
    }


     public interface getListString{
        void getlist(ArrayList<TabBean> list);
    }
    public static void getlist(getListString getListString){
        mGetListString = getListString;
    }

    @Override
    protected void initAdapter() {

        MyItemTouchHelperAdapter callback = new MyItemTouchHelperAdapter(mZhanShiAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mZsRecyclerview.getRecyclerView());
    }


    @Override
    protected void initData() {
        mToolbarZhanshi.setTitle("");
        setSupportActionBar(mToolbarZhanshi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        mList = (ArrayList<String>) intent.getSerializableExtra("list");
        mList1 = (ArrayList<String>) intent.getSerializableExtra("list1");

        mbeanlist = new ArrayList<>();

        for (int i = 0; i < mList.size(); i++) {
            isCb = false;
            for (int j = 0; j < mList1.size(); j++) {
                if (mList.get(i).equalsIgnoreCase(mList1.get(j))) {
                    isCb = true;
                }
            }
            mbeanlist.add(new TabBean(mList.get(i), isCb));
        }
        mZhanShiAdapter = new ZhanShiAdapter(this, mbeanlist);
        mZsRecyclerview.setAdapter(mZhanShiAdapter);
        mZhanShiAdapter.setOnClickListener(new ZhanShiAdapter.OnClickListener() {
            @Override
            public void OnClick(int position, boolean isck) {
                mbeanlist.get(position).setIsck(isck);
            }

        });
    }

    @Override
    protected void initView() {

        mZsRecyclerview = (PullLoadMoreRecyclerView) findViewById(R.id.zsRecyclerview);
        mZsRecyclerview.setLinearLayout();
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarZhanshi = (Toolbar) findViewById(R.id.toolbar_zhanshi);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_zhanshi;
    }

}
