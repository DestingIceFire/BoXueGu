package com.hbtangxun.boxuegu.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hbtangxun.boxuegu.R;
import com.hbtangxun.boxuegu.adapter.VideoListAdapter;
import com.hbtangxun.boxuegu.bean.VideoBean;
import com.hbtangxun.boxuegu.utils.AnalysisUtils;
import com.hbtangxun.boxuegu.utils.DBUtils;
import com.hbtangxun.boxuegu.utils.ToolUtils;
import com.hbtangxun.boxuegu.utils.onClickItemListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程详情
 */

public class VideoListActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_intro, tv_video, tv_chapter_intro;
    private ListView lv_video_list;
    private ScrollView sv_chapter_intro;

    private List<VideoBean> beanList;

    private String intro;
    private int chapterId;
    private DBUtils dbUtils;

    private VideoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //从课程界面传递过来的章节ID
        chapterId = getIntent().getIntExtra("id", 0);
        //从课程界面传递过来的章节简介
        intro = getIntent().getStringExtra("intro");
        //创建数据库工具的对象
        dbUtils = DBUtils.getInstance(this);

        initData();
        initView();

    }

    private void initView() {
        tv_intro = findViewById(R.id.tv_intro);
        tv_video = findViewById(R.id.tv_video);
        tv_chapter_intro = findViewById(R.id.tv_chapter_intro);
        lv_video_list = findViewById(R.id.lv_video_list);
        sv_chapter_intro = findViewById(R.id.sv_chapter_intro);

        adapter = new VideoListAdapter(this, new onClickItemListener() {
            @Override
            public void onItemClick(int position, View View) {
                adapter.setClickPosition(position);
                VideoBean bean = beanList.get(position);
                String videoPath = bean.getVideoPath();
                adapter.notifyDataSetChanged();

                if (TextUtils.isEmpty(videoPath)) {
                    ToolUtils.showToastShort(VideoListActivity.this, "本地没有视频，暂时无法播放");
                    return;
                } else {
                    if (readLoginStatus()) {
                        //如果登录，就把数据添加到数据库里
                        String name = AnalysisUtils.readLoginUserName(VideoListActivity.this);
                        dbUtils.saveVideoPlayList(beanList.get(position), name);
                    }
                    //跳转到视频界面
                    Intent intent = new Intent(VideoListActivity.this, VideoPlayActivity.class);
                    intent.putExtra("videoPath", videoPath);
                    intent.putExtra("position", position);
                    startActivityForResult(intent, 1);
                }
            }
        });

        lv_video_list.setAdapter(adapter);

        //点击事件
        tv_intro.setOnClickListener(this);
        tv_video.setOnClickListener(this);
        adapter.setData(beanList);
        tv_chapter_intro.setText(intro);
        tv_intro.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_video.setBackgroundColor(Color.parseColor("#ffffff"));
        tv_intro.setTextColor(Color.parseColor("#FFFFFF"));
        tv_video.setTextColor(Color.parseColor("#000000"));

    }

    /**
     * 使用JSONArray解析Json数据
     */
    private void initData() {
        JSONArray jsonArray;
        InputStream is = null;
        try {
            is = getResources().getAssets().open("data.json");
            jsonArray = new JSONArray(read(is));
            beanList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                VideoBean bean = new VideoBean();
                JSONObject jo = jsonArray.getJSONObject(i);
                if (jo.getInt("chapterId") == chapterId) {
                    bean.setChapterId(jo.getInt("chapterId"));
                    bean.setVideoId(Integer.parseInt(jo.getString("videoId")));
                    bean.setTitle(jo.getString("title"));
                    bean.setSecondTitle(jo.getString("secondTitle"));
                    bean.setVideoPath(jo.getString("videoPath"));
                    beanList.add(bean);

                    Log.e("TAG", "beanList == " + beanList.toString());
                    Log.e("TAG", "bean == " + bean.toString());
                }
                bean = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取数据流，参数 is 是数据流
     *
     * @param is
     * @return
     */
    private String read(InputStream is) {
        BufferedReader reader = null;
        StringBuilder sb = null;
        String line = null;

        try {
            sb = new StringBuilder();//实例化StringBuilder
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (is != null)
                    is.close();
                if (reader != null)
                    reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 判断用户是否登录
     *
     * @return
     */
    private boolean readLoginStatus() {
        SharedPreferences sp = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //简介
            case R.id.tv_intro:
                lv_video_list.setVisibility(View.GONE);
                sv_chapter_intro.setVisibility(View.VISIBLE);
                tv_intro.setBackgroundColor(Color.parseColor("#30B4FF"));
                tv_video.setBackgroundColor(Color.parseColor("#ffffff"));
                tv_intro.setTextColor(Color.parseColor("#FFFFFF"));
                tv_video.setTextColor(Color.parseColor("#000000"));
                break;
            //视频
            case R.id.tv_video:
                lv_video_list.setVisibility(View.VISIBLE);
                sv_chapter_intro.setVisibility(View.GONE);
                tv_video.setBackgroundColor(Color.parseColor("#30B4FF"));
                tv_intro.setBackgroundColor(Color.parseColor("#ffffff"));
                tv_video.setTextColor(Color.parseColor("#FFFFFF"));
                tv_intro.setTextColor(Color.parseColor("#000000"));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            //接收播放界面穿过的被选中的视频位置
            int position = data.getIntExtra("position", 0);
            adapter.setClickPosition(position); //设置被选中的位置
            lv_video_list.setVisibility(View.VISIBLE);
            sv_chapter_intro.setVisibility(View.GONE);
            tv_video.setBackgroundColor(Color.parseColor("#30B4FF"));
            tv_intro.setBackgroundColor(Color.parseColor("#ffffff"));
            tv_video.setTextColor(Color.parseColor("#FFFFFF"));
            tv_intro.setTextColor(Color.parseColor("#000000"));
        }
    }
}
