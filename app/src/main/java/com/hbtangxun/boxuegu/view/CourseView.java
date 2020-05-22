package com.hbtangxun.boxuegu.view;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.hbtangxun.boxuegu.R;
import com.hbtangxun.boxuegu.adapter.AdBannerAdapter;
import com.hbtangxun.boxuegu.adapter.CourseAdapter;
import com.hbtangxun.boxuegu.bean.CourseBean;

import java.util.List;

/**
 * 课程 界面
 */
public class CourseView {

    private FragmentActivity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;

    private GridView grid_course;
    private ViewPager adPager;// 广告
    private ViewPagerIndicator vpi;// 小圆点

    private CourseAdapter courseAdapter;
    private List<List<CourseBean>> cbl;
    private AdBannerAdapter adBannerAdapter;// 适配器
    private List<CourseBean> cadl;

    private View adBannerLay;// 广告条容器
    public static final int MSG_AD_SLID = 002;// 广告自动滑动

    private MHandler mHandler;// 事件捕获


    public CourseView(FragmentActivity mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    private void createView() {
        mHandler = new MHandler();
        initData();
        getCourseData();
        initView();
    }

    /**
     * 事件捕获
     */
    class MHandler extends Handler {
        @Override
        public void dispatchMessage(@NonNull Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_AD_SLID:
                    if (adBannerAdapter.getCount() > 0) {
                        adPager.setCurrentItem(adPager.getCurrentItem() + 1);
                    }
                    break;
            }
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mCurrentView = mInflater.inflate(R.layout.main_view_course, null);

        //课程
        grid_course = mCurrentView.findViewById(R.id.grid_course);
        courseAdapter = new CourseAdapter(mContext);
        courseAdapter.setData(cbl);
        grid_course.setAdapter(courseAdapter);

        //广告轮播图
        adPager = mCurrentView.findViewById(R.id.vp_advertBanner);
        adPager.setLongClickable(false);
        adBannerAdapter = new AdBannerAdapter(mContext.getSupportFragmentManager(), mHandler);
        adPager.setAdapter(adBannerAdapter);
        adPager.setOnTouchListener(adBannerAdapter);
        //小圆点
        vpi = mCurrentView.findViewById(R.id.vpi_advert_indicator);
        vpi.setCount(adBannerAdapter.getSize());

        adBannerLay = mCurrentView.findViewById(R.id.rl_adBanner);
    }

    private void initData() {

    }

    private void getCourseData() {

    }
}
