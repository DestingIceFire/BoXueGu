package com.hbtangxun.boxuegu2022.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 欢迎 界面
 */
public class SplashActivity extends Activity {

    private TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // 设置此界面为竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        tv_version = findViewById(R.id.tv_version);
        init();

    }

    private void init() {
        //获取程序的信息--版本号
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            tv_version.setText("V"+packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //延迟3秒跳转
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(task,3000);
    }
}
