package com.renlz.jiyun.greeknews.fragments.zhihu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.activitys.CalenderActivity;
import com.renlz.jiyun.greeknews.activitys.InfoActivity;
import com.renlz.jiyun.greeknews.activitys.MainActivity;
import com.renlz.jiyun.greeknews.adapters.RiBaoAdapter;
import com.renlz.jiyun.greeknews.base.fragment.BaseFragment;
import com.renlz.jiyun.greeknews.base.fragment.SimpleFragment;
import com.renlz.jiyun.greeknews.beans.BeforeNews;
import com.renlz.jiyun.greeknews.beans.NewestNew;
import com.renlz.jiyun.greeknews.itemtouchhelper.MyItemTouchHelperAdapter;
import com.renlz.jiyun.greeknews.myapp.MyApp;
import com.renlz.jiyun.greeknews.myenums.EnumApi;
import com.renlz.jiyun.greeknews.presenter.ZhiHuPresenter;
import com.renlz.jiyun.greeknews.utils.C;
import com.renlz.jiyun.greeknews.utils.Event;
import com.renlz.jiyun.greeknews.utils.Utils;
import com.renlz.jiyun.greeknews.view.ZhiHuView;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.HEAD;

/**
 * Created by Administrator on 2018/12/27.
 */


public class RiBaoFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView, View.OnClickListener, XRecyclerView.LoadingListener {

    private ZhiHuPresenter<ZhiHuView> mPresenter;
    private View view;
    private XRecyclerView mRlvRb;
    private String TAG = "RiBaoFragment";
    private ArrayList<NewestNew.StoriesBean> mList = new ArrayList<>();
    private RiBaoAdapter mAdapter;
    private MainActivity mActivity;
    private Context mContext;
    private FloatingActionButton mCalender;
    private LinearLayout mLayRibao;
    private String mDate;


    @Override
    public void showProgressBar() {
        ((MainActivity) mActivity).showProgressAnim();
    }

    @Override
    public void hideProGressBar() {
        ((MainActivity) mActivity).hideProgressAnim();
    }

    @Override
    public void showError(String error) {
        Utils.getInstance().getToast(1, error);
    }

    @Override
    public void getNewData(Object o, Object o1) {
        EnumApi enumApi = (EnumApi) o1;
        switch (enumApi) {
            case NEWSETNEW:
                NewestNew newestNew = (NewestNew) o;
                List<NewestNew.StoriesBean> list = newestNew.getStories();
                mAdapter.addList(list);
                setBanner(list);
                break;
            case BEFORELIST:
                NewestNew beforeNews = (NewestNew) o;
                List<NewestNew.StoriesBean> bflist = beforeNews.getStories();
                mAdapter.setList(bflist);
                break;
        }
    }

    private void setBanner(List<NewestNew.StoriesBean> list) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_banner, mRlvRb, false);
        Banner banner = inflate.findViewById(R.id.banner);
        final ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list1.add(list.get(i).getImages().get(0));
        }
        mAdapter.addHeader(inflate);
        mAdapter.notifyDataSetChanged();

        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {

                Glide.with(MyApp.sMyApp).load(path).into(imageView);

            }
        });
        banner.setImages(list1);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.start();
    }

    @Override
    protected ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
    }

    @Override
    protected void initListener() {
        MyItemTouchHelperAdapter helperAdapter = new MyItemTouchHelperAdapter(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(helperAdapter);
        touchHelper.attachToRecyclerView(mRlvRb);

        if (mAdapter != null) {
            mAdapter.setOnItemClickListener(new RiBaoAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                    NewestNew.StoriesBean bean = mAdapter.mList.get(position);
                    Intent in = new Intent(mContext, InfoActivity.class);
                    in.putExtra("bean", bean.getId());
                    startActivity(in);
                }

                @Override
                public void OnItemLongClick(int position) {

                }
            });
        }
        mRlvRb.setLoadingListener(this);
    }

    @Override
    protected void initAdapter() {
        mAdapter = new RiBaoAdapter(mContext, mList);
        mRlvRb.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        mPresenter.setNewsData(null, null, EnumApi.NEWSETNEW);
    }

    @Override
    protected void receiveStickyEvent(Event event) {
        super.receiveStickyEvent(event);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventReceived(Event<String> event) {
        if (event != null && event.getCode() == C.EventCode.A) {
            mDate = event.getData();
            mPresenter.setNewsData("", mDate, EnumApi.BEFORELIST);
        }
    }


    @Override
    protected void initView(View view) {
        mRlvRb = (XRecyclerView) view.findViewById(R.id.rlv_rb);
        mRlvRb.setLayoutManager(new LinearLayoutManager(mContext));
        mActivity = (MainActivity) getActivity();
        mContext = mActivity.getBaseContext();
        mCalender = (FloatingActionButton) view.findViewById(R.id.calender);
        mCalender.setOnClickListener(this);
        mLayRibao = (LinearLayout) view.findViewById(R.id.lay_ribao);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_ribao;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.calender:
                startActivityForResult(new Intent(mContext, CalenderActivity.class), 2018);
                break;
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onRefresh() {
        mRlvRb.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        mRlvRb.loadMoreComplete();
    }
}
