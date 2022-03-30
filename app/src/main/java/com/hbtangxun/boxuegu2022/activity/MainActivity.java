package com.hbtangxun.boxuegu2022.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.utils.AnalysisUtils;
import com.hbtangxun.boxuegu2022.utils.ToolUtils;
import com.hbtangxun.boxuegu2022.view.ExercisesView;
import com.hbtangxun.boxuegu2022.view.MyInfoView;

public class MainActivity extends Activity implements View.OnClickListener {

    //标题栏
    private TextView tv_back;
    private TextView title_text;
    private RelativeLayout rl_title_bar;
    // 中间部分
    private FrameLayout mBodyLayout;

    // 底部按钮栏
    private LinearLayout mBottomLayout;

    // 底部按钮
    private TextView bottom_bar_text_course;
    private TextView bottom_bar_text_exercises;
    private TextView bottom_bar_text_my;
    private ImageView bottom_bar_img_course;
    private ImageView bottom_bar_img_exercises;
    private ImageView bottom_bar_img_my;
    private View mCourseBtn;
    private View mExercisesBtn;
    private View mMyBtn;

    // 我的 界面
    private MyInfoView myInfoView;

    // 习题 界面
    private ExercisesView exercisesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();
        initBottomBar();
        setListener();
        setInitStatus();
    }

    /**
     * 获取界面上的UI
     */
    private void init() {
        tv_back = findViewById(R.id.tv_back);
        title_text = findViewById(R.id.title_text);
        rl_title_bar = findViewById(R.id.rl_title_bar);
        //隐藏返回键
        tv_back.setVisibility(View.GONE);
        // 标题栏
        title_text.setText("博学谷课程");
        //标题设置背景颜色
        rl_title_bar.setBackgroundColor(Color.parseColor("#30b4ff"));
        initBodyLayout();
    }

    /**
     * 获取底部导航栏控件
     */
    private void initBottomBar() {
        mBottomLayout = findViewById(R.id.main_bottom_bar);
        mCourseBtn = findViewById(R.id.bottom_bar_rl_course_btn);
        mExercisesBtn = findViewById(R.id.bottom_bar_rl_exercises_btn);
        mMyBtn = findViewById(R.id.bottom_bar_rl_my_btn);
        bottom_bar_text_course = findViewById(R.id.bottom_bar_text_course);
        bottom_bar_text_exercises = findViewById(R.id.bottom_bar_text_exercises);
        bottom_bar_text_my = findViewById(R.id.bottom_bar_text_my);
        bottom_bar_img_course = findViewById(R.id.bottom_bar_img_course);
        bottom_bar_img_exercises = findViewById(R.id.bottom_bar_img_exercises);
        bottom_bar_img_my = findViewById(R.id.bottom_bar_img_my);
    }

    /**
     * 页面中间部分
     */
    private void initBodyLayout() {
        mBodyLayout = findViewById(R.id.main_frame);
    }


    /**
     * 设置底部三个按钮的点击监听事件
     */
    private void setListener() {
        for (int i = 0; i < mBottomLayout.getChildCount(); i++) {
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 课程点击事件
            case R.id.bottom_bar_rl_course_btn:
                clearBottomImageState();
                selectDisplayView(0);
                break;
            // 习题的点击事件
            case R.id.bottom_bar_rl_exercises_btn:
                clearBottomImageState();
                selectDisplayView(1);
                break;
            // 我的点击事件
            case R.id.bottom_bar_rl_my_btn:
                clearBottomImageState();
                selectDisplayView(2);
                break;
            default:
                break;
        }
    }

    /**
     * 显示对应的界面
     *
     * @param index
     */
    private void selectDisplayView(int index) {
        removeAllView();
        createView(index);
        setSelectedStatus(index);
    }

    /**
     * 移除不需要的视图
     */
    private void removeAllView() {
        for (int i = 0; i < mBodyLayout.getChildCount(); i++) {
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }

    /**
     * 设置底部按钮的选中状态
     *
     * @param index
     */
    private void setSelectedStatus(int index) {
        switch (index) {
            case 0:
                mCourseBtn.setSelected(true);
                bottom_bar_img_course.setImageResource(R.drawable.main_course_icon_selected);
                bottom_bar_text_course.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                title_text.setText("博学谷课程");
                break;
            case 1:
                mExercisesBtn.setSelected(true);
                bottom_bar_img_exercises.setImageResource(R.drawable.main_exercises_icon_selected);
                bottom_bar_text_exercises.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                title_text.setText("博学谷习题");
                break;
            case 2:
                mMyBtn.setSelected(true);
                bottom_bar_img_my.setImageResource(R.drawable.main_my_icon_selected);
                bottom_bar_text_my.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 清除底部按钮的选中状态
     */
    private void clearBottomImageState() {
        bottom_bar_text_course.setTextColor(Color.parseColor("#666666"));
        bottom_bar_text_exercises.setTextColor(Color.parseColor("#666666"));
        bottom_bar_text_my.setTextColor(Color.parseColor("#666666"));
        bottom_bar_img_course.setImageResource(R.drawable.main_course_icon);
        bottom_bar_img_exercises.setImageResource(R.drawable.main_exercises_icon);
        bottom_bar_img_my.setImageResource(R.drawable.main_my_icon);
        for (int i = 0; i < mBottomLayout.getChildCount(); i++) {
            mBottomLayout.getChildAt(i).setSelected(false);
        }
    }

    /**
     * 设置界面View初始化状态
     */
    private void setInitStatus() {
        clearBottomImageState();
        selectDisplayView(0);
        createView(0);
    }

    /**
     * 选择对应界面
     *
     * @param index
     */
    private void createView(int index) {
        switch (index) {
            case 0:
                // 课程界面
                Log.e("TAG", "MainActivity createView --- 0");
                break;
            case 1:
                // 习题界面
                Log.e("TAG", "MainActivity createView --- 1");
                if(exercisesView == null) {
                    exercisesView = new ExercisesView(this);
                    mBodyLayout.addView(exercisesView.getView());
                }else {
                    exercisesView.getView();
                }
                exercisesView.showView();
                break;
            case 2:
                // 我的界面
                Log.e("TAG", "MainActivity createView --- 2");
                if (myInfoView == null) {
                    // 初始化
                    myInfoView = new MyInfoView(this);
                    //加载布局
                    mBodyLayout.addView(myInfoView.getView());
                } else {
                    //得到视图View
                    myInfoView.getView();
                }
                // 显示视图View
                myInfoView.showView();
                break;
            default:
                break;
        }
    }

    private long exitTime;

    /**
     * 功能：再按一次退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToolUtils.showShortToast(this, "再按一次退出");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                if (readLoginStatus()) {
                    AnalysisUtils.cleanLoginStatus(this);
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 获取SP中登录状态
     *
     * @return
     */
    private boolean readLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean isLogin = readLoginStatus();
        if (myInfoView != null) {
            myInfoView.setLoginParams(isLogin);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            boolean isLogin = data.getBooleanExtra("isLogin", false);
            if (isLogin) {
                clearBottomImageState();
                selectDisplayView(0);
            }
        }
    }
}
