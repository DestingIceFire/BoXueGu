package com.hbtangxun.boxuegu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.hbtangxun.boxuegu.R;

public class VideoPlayActivity extends AppCompatActivity {

    private VideoView video_view;
    private MediaController controller;
    private String videoPath; //本地视频的地址
    private int position; //传递视频详情界面点击的位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏播放
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_video_play);

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

        play();
    }

    /**
     * 播放视频
     */
    private void play() {

        if (TextUtils.isEmpty(videoPath)) {
            Toast.makeText(VideoPlayActivity.this, "本地没有视频，暂时无法播放", Toast.LENGTH_SHORT).show();
            return;
        }
        //视频地址
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.video11;
        video_view.setVideoPath(uri);
        video_view.start();
    }

    /**
     * 点击后退按钮
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //把视频详情传递过得来的被点击视频的位置传递回去
        Intent intent = new Intent();
        intent.putExtra("position", position);
        setResult(RESULT_OK, intent);
        return super.onKeyDown(keyCode, event);
    }
}
