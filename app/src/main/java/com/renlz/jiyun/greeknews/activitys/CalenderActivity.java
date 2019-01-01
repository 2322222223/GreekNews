package com.renlz.jiyun.greeknews.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.base.activity.SimpleActivity;
import com.renlz.jiyun.greeknews.utils.C;
import com.renlz.jiyun.greeknews.utils.Event;
import com.renlz.jiyun.greeknews.utils.EventBusUtil;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/28.
 */

public class CalenderActivity extends SimpleActivity implements View.OnClickListener {
    private Toolbar mToolBar;
    private MaterialCalendarView mViewCalender;
    /**
     * 确定
     */
    private TextView mTvCalenderEnter;
    private CalendarDay mCurrentDate;
    private String TAG = "data";

    @Override
    protected void initListener() {
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mViewCalender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Date data1 = mViewCalender.getSelectedDate().getDate();
            }
        });
    }


    private String getSelectedDatesString() {
        CalendarDay date = mViewCalender.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String data = sdf.format(date.getDate());
        return data;

    }


    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mViewCalender.getCurrentDate();
    }

    @Override
    protected void initView() {
        mToolBar = (Toolbar) findViewById(R.id.tool_bar1);
        mViewCalender = (MaterialCalendarView) findViewById(R.id.view_calender);
        mTvCalenderEnter = (TextView) findViewById(R.id.tv_calender_enter);
        mTvCalenderEnter.setOnClickListener(this);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_calender;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_calender_enter:
                String date = getSelectedDatesString();
                Log.d(TAG, "onClick: "+date);
                String Data = "";
                if (date != null && !"".equals(date)) {
                    for (int i = 0; i < date.length(); i++) {
                        if (date.charAt(i) >= 48 && date.charAt(i) <= 57) {
                            Data += date.charAt(i);
                        }
                    }

                    Event<String> event = new Event<>(C.EventCode.A,Data);
                    EventBusUtil.sendStickyEvent(event);
                    finish();
                    break;
                }
        }
    }
}
