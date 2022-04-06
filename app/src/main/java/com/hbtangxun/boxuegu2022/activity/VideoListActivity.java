package com.hbtangxun.boxuegu2022.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.adapter.VideoListAdapter;
import com.hbtangxun.boxuegu2022.bean.VideoBean;
import com.hbtangxun.boxuegu2022.utils.AnalysisUtils;
import com.hbtangxun.boxuegu2022.utils.DBUtils;
import com.hbtangxun.boxuegu2022.utils.OnClickItemListener;
import com.hbtangxun.boxuegu2022.utils.ToolUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class VideoListActivity extends AppCompatActivity implements View.OnClickListener {

    //标题栏
    private TextView tv_back;
    private TextView title_text;
    private RelativeLayout rl_title_bar;

    private TextView tv_intro, tv_video, tv_chapter_intro;
    private ListView lv_video_list;
    private ScrollView sv_chapter_intro;

    private List<VideoBean> beanList;
    private VideoListAdapter adapter;

    private DBUtils dbUtils;

    private String intro;
    private String title;
    private int chapterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //接收数据
        intro = getIntent().getStringExtra("intro");
        title = getIntent().getStringExtra("title");
        chapterId = getIntent().getIntExtra("id", 0);

        dbUtils = DBUtils.getInstance(this);

        initView();
        jsonData();
        initData();
    }


    private void initView() {
        tv_intro = findViewById(R.id.tv_intro);
        tv_video = findViewById(R.id.tv_video);
        tv_chapter_intro = findViewById(R.id.tv_chapter_intro);
        lv_video_list = findViewById(R.id.lv_video_list);
        sv_chapter_intro = findViewById(R.id.sv_chapter_intro);
        tv_back = findViewById(R.id.tv_back);
        title_text = findViewById(R.id.title_text);
        rl_title_bar = findViewById(R.id.rl_title_bar);
        // 标题栏
        title_text.setText(title);
        //标题设置背景颜色
        rl_title_bar.setBackgroundColor(Color.parseColor("#30b4ff"));
        //显示简介
        tv_chapter_intro.setText(intro);
    }

    private void initData() {

        //视频Adapter
        adapter = new VideoListAdapter(this, new OnClickItemListener() {
            @Override
            public void onItemClick(int position, View view) {
                adapter.setClickPosition(position);
                VideoBean bean = beanList.get(position);
                String videoPath = bean.getVideoPath();
                adapter.notifyDataSetChanged();

                if (TextUtils.isEmpty(videoPath)) {
                    ToolUtils.showShortToast(VideoListActivity.this, "本地没有视频，暂时无法播放");
                } else {
                    if(AnalysisUtils.readLoginStatus(VideoListActivity.this)) {
                        String name = AnalysisUtils.readLoginUserName(VideoListActivity.this);
                        dbUtils.saveVideoPlayList(bean, name);
                    }
                    //跳转到播放视频的页面
                }

            }
        });
        lv_video_list.setAdapter(adapter);
        adapter.setData(beanList);
        tv_intro.setOnClickListener(this);
        tv_video.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    /**
     * 解析JSON数据
     */
    private void jsonData() {
        JSONArray jsonArray;
        InputStream is;
        try {
            is = getResources().getAssets().open("data.json");

            jsonArray = new JSONArray(read(is));
            beanList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                VideoBean bean = new VideoBean();
                JSONObject jo = jsonArray.getJSONObject(i);
                // 如果视频的ID等于视频详情的ID
                if (jo.getInt("chapterId") == chapterId) {
                    bean.setChapterId(jo.getInt("chapterId"));
                    bean.setVideoId(jo.getString("videoId"));
                    bean.setTitle(jo.getString("title"));
                    bean.setSecondTitle(jo.getString("secondTitle"));
                    bean.setVideoPath(jo.getString("videoPath"));
                    beanList.add(bean);
                }
                bean = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取数据流
     *
     * @param is
     * @return
     */
    private String read(InputStream is) {
        BufferedReader reader = null;
        StringBuilder sb = null;
        String line = null;
        try {
            sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_intro:
                lv_video_list.setVisibility(View.GONE);
                sv_chapter_intro.setVisibility(View.VISIBLE);
                tv_intro.setTextColor(Color.parseColor("#ffffff"));
                tv_video.setTextColor(Color.parseColor("#000000"));
                tv_intro.setBackgroundColor(Color.parseColor("#30b4ff"));
                tv_video.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case R.id.tv_video:
                lv_video_list.setVisibility(View.VISIBLE);
                sv_chapter_intro.setVisibility(View.GONE);
                tv_intro.setTextColor(Color.parseColor("#000000"));
                tv_video.setTextColor(Color.parseColor("#ffffff"));
                tv_intro.setBackgroundColor(Color.parseColor("#ffffff"));
                tv_video.setBackgroundColor(Color.parseColor("#30b4ff"));
                break;
        }
    }
}
