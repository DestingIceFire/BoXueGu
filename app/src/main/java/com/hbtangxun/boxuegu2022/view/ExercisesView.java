package com.hbtangxun.boxuegu2022.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.adapter.ExercisesAdapter;
import com.hbtangxun.boxuegu2022.bean.ExercisesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 习题 界面
 */
public class ExercisesView {

    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;

    private ListView lv_list;

    private ExercisesAdapter adapter;
    private List<ExercisesBean> beans;

    /**
     * 构造器
     *
     * @param activity
     */
    public ExercisesView(Activity activity) {
        this.mContext = activity;
        mInflater = LayoutInflater.from(mContext);
    }

    /**
     * 显示界面
     */
    public void createView() {
        mCurrentView = mInflater.inflate(R.layout.main_view_exercises, null);
        init();
    }

    /**
     * 加载视图
     */
    private void init() {
        lv_list = mCurrentView.findViewById(R.id.lv_list);
        adapter = new ExercisesAdapter(mContext);
        addListData();
        //加载数据
        adapter.setData(beans);
        lv_list.setAdapter(adapter);
    }

    /**
     * 添加列表数据
     * 静态数据 -- 只有这么多且固定的数据
     */
    private void addListData() {
        beans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExercisesBean bean = new ExercisesBean();
            bean.setId(i + 1);
            switch (i) {
                case 0:
                    bean.setTitle("第1章 Android基础入门");
                    bean.setContent("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_1);
                    break;
                case 1:
                    bean.setTitle("第2章 Android UI开发");
                    bean.setContent("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_2);
                    break;
                case 2:
                    bean.setTitle("第3章 Activity");
                    bean.setContent("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_3);
                    break;
                case 3:
                    bean.setTitle("第4章 数据存储");
                    bean.setContent("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_4);
                    break;
                case 4:
                    bean.setTitle("第5章 SQLite数据库");
                    bean.setContent("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_1);
                    break;
                case 5:
                    bean.setTitle("第6章 广播接受者");
                    bean.setContent("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_2);
                    break;
                case 6:
                    bean.setTitle("第7章 服务");
                    bean.setContent("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_3);
                    break;
                case 7:
                    bean.setTitle("第8章 内容提供者");
                    bean.setContent("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_4);
                    break;
                case 8:
                    bean.setTitle("第9章 网络编程");
                    bean.setContent("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_1);
                    break;
                case 9:
                    bean.setTitle("第10章 高级编程");
                    bean.setContent("共计5题");
                    bean.setBackground(R.drawable.exercises_bg_2);
                    break;
            }
            beans.add(bean);
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
