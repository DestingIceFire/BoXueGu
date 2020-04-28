package com.hbtangxun.boxuegu.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.hbtangxun.boxuegu.R;
import com.hbtangxun.boxuegu.adapter.ExercisesAdapter;
import com.hbtangxun.boxuegu.bean.ExercisesBean;

import java.util.ArrayList;
import java.util.List;

public class ExercisesView {
    private ListView lv_list;
    private ExercisesAdapter adapter;
    private Activity mContent;
    private LayoutInflater mInflater;
    private View mCurrentView;

    private List<ExercisesBean> ebl;

    public ExercisesView(Activity mContent) {
        this.mContent = mContent;
        mInflater = LayoutInflater.from(mContent);
    }

    private void createView() {
        initView();
    }

    private void initView() {
        mCurrentView = mInflater.inflate(R.layout.main_view_exercises, null);
        lv_list = mCurrentView.findViewById(R.id.lv_list);
        adapter = new ExercisesAdapter(mContent);
        initData();
        adapter.setData(ebl);
        lv_list.setAdapter(adapter);
    }

    /**
     * 设置数据
     */
    private void initData() {
        ebl = new ArrayList<ExercisesBean>();
        for (int i = 0; i < 10; i++) {
            ExercisesBean bean = new ExercisesBean();
            bean.setId(i + 1);
            switch (i) {
                case 0:
                    bean.setTitle("第1章 Android基础入门");
                    bean.setContent("共计5题");
                    bean.setBackground((R.drawable.exercises_bg_1));
                    break;
                case 1:
                    bean.setTitle("第2章 Android UI开发");
                    bean.setContent("共计5题");
                    bean.setBackground((R.drawable.exercises_bg_2));
                    break;
                case 2:
                    bean.setTitle("第3章 Activity");
                    bean.setContent("共计5题");
                    bean.setBackground((R.drawable.exercises_bg_3));
                    break;
                case 3:
                    bean.setTitle("第4章 数据存储");
                    bean.setContent("共计5题");
                    bean.setBackground((R.drawable.exercises_bg_4));
                    break;
                case 4:
                    bean.setTitle("第5章 SQLite数据库");
                    bean.setContent("共计5题");
                    bean.setBackground((R.drawable.exercises_bg_1));
                    break;
                case 5:
                    bean.setTitle("第6章 广播接收者");
                    bean.setContent("共计5题");
                    bean.setBackground((R.drawable.exercises_bg_2));
                    break;
                case 6:
                    bean.setTitle("第7章 服务");
                    bean.setContent("共计5题");
                    bean.setBackground((R.drawable.exercises_bg_3));
                    break;
                case 7:
                    bean.setTitle("第8章 内容提供者");
                    bean.setContent("共计5题");
                    bean.setBackground((R.drawable.exercises_bg_4));
                    break;
                case 8:
                    bean.setTitle("第9章 网络编程");
                    bean.setContent("共计5题");
                    bean.setBackground((R.drawable.exercises_bg_1));
                    break;
                case 9:
                    bean.setTitle("第10章 高级编程");
                    bean.setContent("共计5题");
                    bean.setBackground((R.drawable.exercises_bg_2));
                    break;
                default:
                    break;
            }
            ebl.add(bean);
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
