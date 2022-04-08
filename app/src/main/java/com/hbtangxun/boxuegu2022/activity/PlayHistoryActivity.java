package com.hbtangxun.boxuegu2022.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.adapter.PlayHistoryAdapter;
import com.hbtangxun.boxuegu2022.bean.VideoBean;
import com.hbtangxun.boxuegu2022.utils.AnalysisUtils;
import com.hbtangxun.boxuegu2022.utils.DBUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 播放记录 界面
 */
public class PlayHistoryActivity extends AppCompatActivity {

    //标题栏
    private TextView tv_back;
    private TextView title_text;
    private RelativeLayout rl_title_bar;

    private ListView lv_history;
    private TextView tv_his_none;

    private PlayHistoryAdapter adapter;
    private List<VideoBean> vbl;
    private DBUtils db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_history);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initView();
        initData();

    }

    private void initView() {
        tv_back = findViewById(R.id.tv_back);
        title_text = findViewById(R.id.title_text);
        rl_title_bar = findViewById(R.id.rl_title_bar);
        lv_history = findViewById(R.id.lv_history);
        tv_his_none = findViewById(R.id.tv_his_none);

        adapter = new PlayHistoryAdapter(this);
        //链接数据库
        db = DBUtils.getInstance(this);
        vbl = new ArrayList<>();
    }

    private void initData() {
        //获取数据库中的播放记录的数据
        vbl = db.getVideoHistory(AnalysisUtils.readLoginUserName(this));
        // 标题栏
        title_text.setText("播放记录");
        //标题设置背景颜色
        rl_title_bar.setBackgroundColor(Color.parseColor("#30b4ff"));

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //如果数据库中没有数据
        if (vbl.size() == 0) {
            lv_history.setVisibility(View.GONE);
            tv_his_none.setVisibility(View.VISIBLE);
        }

        adapter.setData(vbl);
        lv_history.setAdapter(adapter);

    }
}
