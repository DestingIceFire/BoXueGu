package com.hbtangxun.boxuegu2022.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.utils.ToolUtils;

public class VideoPlayActivity extends AppCompatActivity {

    private VideoView video_view;
    //媒体播放器
    private MediaController controller;
    //播放视频地址
    private String videoPath;
    //传递视频详情界面的点击位置
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        //设置全屏播放
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        videoPath = getIntent().getStringExtra("videoPath");
        position = getIntent().getIntExtra("position", 0);

        initView();
        initData();
    }

    private void initView() {
        video_view = findViewById(R.id.video_view);
        controller = new MediaController(this);
        video_view.setMediaController(controller);

    }

    private void initData() {
        //播放视频
        if (TextUtils.isEmpty(videoPath)) {
            ToolUtils.showShortToast(this, "本地没有视频，暂时无法播放");
            return;
        }
        //视频地址,播放本地内部资源视频文件
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video11;
        video_view.setVideoPath(videoPath);
        video_view.start();
    }

    /**
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = new Intent();
        intent.putExtra("position", position);
        setResult(RESULT_OK, intent);
        return super.onKeyDown(keyCode, event);
    }
}
