package com.hbtangxun.boxuegu2022.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hbtangxun.boxuegu2022.bean.CourseBean;
import com.hbtangxun.boxuegu2022.fragment.AdBannerFragment;
import com.hbtangxun.boxuegu2022.view.CourseView;

import java.util.ArrayList;
import java.util.List;

/**
 * 对ViewPager（广告轮播图）进行数据适配器
 */
public class AdBannerAdapter extends FragmentPagerAdapter implements View.OnTouchListener{

    private Handler handler;
    private List<CourseBean> beans;

    public AdBannerAdapter(FragmentManager fm) {
        super(fm);
        beans = new ArrayList<>();
    }

    public AdBannerAdapter(FragmentManager fm, Handler mHandler) {
        super(fm);
        this.handler = mHandler;
        beans = new ArrayList<>();
    }

    public void setData(List<CourseBean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        if (beans.size() > 0) {
            args.putString("ad", beans.get(position % beans.size()).getIcon());
        }
        return AdBannerFragment.newInstance(args);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * 返回数据集的真实容量大小
     *
     * @return
     */
    public int getSize() {
        return beans == null ? 0 : beans.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        // 防止刷新结果显示列表是出现缓存数据，重载这个函数，让它的返回POSITION_NONE
        return POSITION_NONE;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        handler.removeMessages(CourseView.MSG_AD_SLID);
        return false;
    }
}
