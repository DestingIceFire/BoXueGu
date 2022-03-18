package com.hbtangxun.boxuegu2022.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.activity.LoginActivity;
import com.hbtangxun.boxuegu2022.utils.AnalysisUtils;
import com.hbtangxun.boxuegu2022.utils.ToolUtils;

/**
 * 我的 界面
 */
public class MyInfoView implements View.OnClickListener {

    private LinearLayout ll_head;
    private TextView tv_user_login;
    private TextView my_course_history;
    private TextView my_course_setting;


    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;

    /**
     * 构造器
     * @param context
     */
    public MyInfoView(Activity context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    /**
     * 显示界面
     */
    public void createView() {
        mCurrentView = mInflater.inflate(R.layout.main_view_myinfo, null);
        init();
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

    /**
     * 加载控件
     * 使用控件
     */
    private void init() {
        ll_head = mCurrentView.findViewById(R.id.ll_head);
        tv_user_login = mCurrentView.findViewById(R.id.tv_user_login);
        my_course_history = mCurrentView.findViewById(R.id.my_course_history);
        my_course_setting = mCurrentView.findViewById(R.id.my_course_setting);

        mCurrentView.setVisibility(View.VISIBLE);
        setLoginParams(readLoginStatus());

        // 登录 按钮
        ll_head.setOnClickListener(this);
        // 播放记录
        my_course_history.setOnClickListener(this);
        // 设置
        my_course_setting.setOnClickListener(this);
    }

    /**
     * 登录成功后设置 我的 界面的 用户名
     * @param isLogin
     */
    public void setLoginParams(boolean isLogin) {
        if (isLogin) {
            tv_user_login.setText(AnalysisUtils.readLoginUserName(mContext));
        } else {
            tv_user_login.setText("点击登录");
        }
    }

    /**
     * 获取当前登录状态
     *
     * @return
     */
    private boolean readLoginStatus() {
        SharedPreferences sp = mContext.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        return sp.getBoolean("isLogin", false);
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v == ll_head) {
            if (readLoginStatus()) {
                // 跳转到 个人资料 页面
                ToolUtils.showShortToast(mContext, "个人资料");
            } else {
                // 跳转到 登录 页面
                mContext.startActivityForResult(new Intent(mContext, LoginActivity.class), 1);
            }
        } else if (v == my_course_history) {
            if (readLoginStatus()) {
                // 跳转到 播放记录 页面
                ToolUtils.showShortToast(mContext, "播放记录");
            } else {
                ToolUtils.showShortToast(mContext, "您还未登录，请先登录");
            }
        } else if (v == my_course_setting) {
            if (readLoginStatus()) {
                // 跳转到 设置 页面
                ToolUtils.showShortToast(mContext, "设置");
            } else {
                ToolUtils.showShortToast(mContext, "您还未登录，请先登录");
            }
        }
    }
}
