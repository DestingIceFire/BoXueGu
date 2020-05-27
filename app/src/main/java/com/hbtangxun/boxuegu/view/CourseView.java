package com.hbtangxun.boxuegu.view;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.hbtangxun.boxuegu.R;
import com.hbtangxun.boxuegu.adapter.AdBannerAdapter;
import com.hbtangxun.boxuegu.adapter.CourseAdapter;
import com.hbtangxun.boxuegu.bean.CourseBean;
import com.hbtangxun.boxuegu.utils.AnalysisUtils;

import java.io.InputStream;
import java.util.ArrayList;
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
    private AdBannerAdapter adBannerAdapter;// 适配器
    private List<CourseBean> cbl;
    private List<CourseBean> cblBean;

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
        new ADAutoSlidThread().start();
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
     * 广告自动滑动
     */
    class ADAutoSlidThread extends Thread {
        @Override
        public void run() {
            super.run();

            while (true) {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mHandler != null) {
                    mHandler.sendEmptyMessage(MSG_AD_SLID);
                }
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
        adPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (adBannerAdapter.getSize() > 0) {
                    vpi.setCurrentPosition(position % adBannerAdapter.getSize());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        resetSize();

        if (cblBean != null) {
            if (cblBean.size() > 0) {
                vpi.setCount(cblBean.size());
                vpi.setCurrentPosition(0);
            }
            adBannerAdapter.setDatas(cblBean);
        }
    }

    /**
     * 计算控件大小
     */
    private void resetSize() {
        int sw = getScreenWidth(mContext);
        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();
        adlp.width = sw;
        adlp.height = sw / 2;
        adBannerLay.setLayoutParams(adlp);
    }

    /**
     * 读取屏幕宽
     */
    public static int getScreenWidth(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = context.getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }

    private void initData() {
        cbl = new ArrayList<>();
        cblBean = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CourseBean courseBean = new CourseBean();
            courseBean.setIndex(i + 1);
            switch (i) {
                case 0:
                    courseBean.setIcon("banner_1");
                    break;
                case 1:
                    courseBean.setIcon("banner_2");
                    break;
                case 2:
                    courseBean.setIcon("banner_3");
                    break;
            }
            cblBean.add(courseBean);
        }

    }

    private void getCourseData() {
        //解析XML数据
        try {
            InputStream is = mContext.getResources().getAssets().open("chaptertitle.xml");
            cbl = AnalysisUtils.getCourseInfos(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前在导航栏上方对应的View
     */
    public View getView() {
        if (mCurrentView == null) {
            createView();
        }
        return mCurrentView;
    }

    /**
     * 获取当前导航栏上方所对应的View界面
     */
    public void showView() {
        if (mCurrentView == null) {
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }

}
