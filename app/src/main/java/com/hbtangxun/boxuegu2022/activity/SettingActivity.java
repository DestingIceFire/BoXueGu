package com.hbtangxun.boxuegu2022.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.utils.AnalysisUtils;
import com.hbtangxun.boxuegu2022.utils.ToolUtils;

/**
 * 设置 界面
 */
public class SettingActivity extends Activity implements View.OnClickListener {

    //状态栏
    private TextView tv_back;
    private TextView title_text;
    private RelativeLayout rl_title_bar;

    //修改密码
    private TextView tv_modify_psw;
    //设置密保
    private TextView tv_security_parent;
    //退出登录
    private TextView tv_exit_login;

    public static SettingActivity instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        instance = this;

        initView();
        initData();
    }

    private void initView() {
        tv_back = findViewById(R.id.tv_back);
        title_text = findViewById(R.id.title_text);
        rl_title_bar = findViewById(R.id.rl_title_bar);
        tv_modify_psw = findViewById(R.id.tv_modify_psw);
        tv_security_parent = findViewById(R.id.tv_security_parent);
        tv_exit_login = findViewById(R.id.tv_exit_login);
    }

    private void initData() {
        // 标题栏
        title_text.setText("设 置");
        //标题设置背景颜色
        rl_title_bar.setBackgroundColor(Color.parseColor("#30b4ff"));
        //返回键
        tv_back.setOnClickListener(this);
        tv_modify_psw.setOnClickListener(this);
        tv_security_parent.setOnClickListener(this);
        tv_exit_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tv_back) {
            //返回键
            finish();
        } else if (v == tv_modify_psw) {
            //修改密码
            startActivity(new Intent(this, ModifyPswActivity.class));
        } else if (v == tv_security_parent) {
            //设置密保
            Intent intent = new Intent(this, FindPswActivity.class);
            intent.putExtra("from", "security");
            startActivity(intent);
        } else if (v == tv_exit_login) {
            //退出登录
            ToolUtils.showShortToast(this, "退出登录");
            //1、清除SP中登录状态和登录时的用户名
            AnalysisUtils.cleanLoginStatus(this);
            finish();
        }
    }
}
