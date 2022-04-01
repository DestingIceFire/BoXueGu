package com.hbtangxun.boxuegu2022.view;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.adapter.AdBannerAdapter;
import com.hbtangxun.boxuegu2022.adapter.CourseAdapter;
import com.hbtangxun.boxuegu2022.bean.CourseBean;
import com.hbtangxun.boxuegu2022.utils.AnalysisUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 课程 模块
 */
public class CourseView {

    private FragmentActivity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;

    private GridView gird_course;
    private ViewPager adPager;
    private ViewPagerIndicator vpi; // 小圆点


    private CourseAdapter courseAdapter; // 课程适配器
    private AdBannerAdapter adBannerAdapter; //轮播图适配器
    private List<CourseBean> cb1;
    private List<CourseBean> cbBean;

    private View adBannerLay;
    public static final int MSG_AD_SLID = 002; //广告自动滑动

    private MHandler mHandler;

    public CourseView(FragmentActivity mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
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
     * 显示界面
     */
    public void createView() {
        mCurrentView = mInflater.inflate(R.layout.main_view_course, null);
        initView();
        initData();
    }

    /**
     * 加载控件
     */
    private void initView() {
        gird_course = mCurrentView.findViewById(R.id.gird_course);
        adPager = mCurrentView.findViewById(R.id.vp_advertBanner);
        vpi = mCurrentView.findViewById(R.id.vpi_advert_indicator);
    }

    /**
     * 加载数据
     */
    private void initData() {
        getCourseData();
        // 广告轮播图 adPager
        adPager.setLongClickable(false);
        adBannerAdapter = new AdBannerAdapter(mContext.getSupportFragmentManager(), mHandler);
        adPager.setAdapter(adBannerAdapter);
        adPager.setOnTouchListener(adBannerAdapter);

        // 小圆点
        vpi.setCount(adBannerAdapter.getSize()); // 获取长度

        // 课程列表

        courseAdapter = new CourseAdapter(mContext);
        courseAdapter.setData(cb1);
        gird_course.setAdapter(courseAdapter);

    }

    /**
     * 解析课程数据
     */
    private void getCourseData() {
        try {
            InputStream is = mContext.getResources().getAssets().open("chaptertitle.xml");
            cb1 = AnalysisUtils.getCoursesInfos(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前在底部导航栏上方对应的View
     *
     * @return
     */
    public View getView() {
        if (mCurrentView == null) {
            createView();
        }
        return mCurrentView;
    }

    /**
     * 获取当前在底部导航栏上方对应的View界面
     */
    public void showView() {
        if (mCurrentView == null) {
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }

}
