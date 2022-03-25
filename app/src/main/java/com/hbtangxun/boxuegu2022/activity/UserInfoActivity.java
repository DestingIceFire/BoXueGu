package com.hbtangxun.boxuegu2022.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;

public class UserInfoActivity extends AppCompatActivity {
    //标题栏
    private TextView tv_back;
    private TextView title_text;
    private RelativeLayout rl_title_bar;

    private RelativeLayout rl_nick_name, rl_sex, rl_signature;
    private TextView tv_nick_name, tv_userinfo_sex, tv_signature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initView();
        initData();

    }

    private void initView() {
        tv_back = findViewById(R.id.tv_back);
        title_text = findViewById(R.id.title_text);
        rl_title_bar = findViewById(R.id.rl_title_bar);
        rl_nick_name = findViewById(R.id.rl_nick_name);
        rl_sex = findViewById(R.id.rl_sex);
        rl_signature = findViewById(R.id.rl_signature);
        tv_nick_name = findViewById(R.id.tv_nick_name);
        tv_userinfo_sex = findViewById(R.id.tv_userinfo_sex);
        tv_signature = findViewById(R.id.tv_signature);
    }

    private void initData() {
        title_text.setText("个人资料");
        //标题设置背景颜色
        rl_title_bar.setBackgroundColor(Color.parseColor("#30b4ff"));

        initSQLData();
    }

    /**
     * 操作SQLite数据库
     */
    private void initSQLData() {

    }
}
