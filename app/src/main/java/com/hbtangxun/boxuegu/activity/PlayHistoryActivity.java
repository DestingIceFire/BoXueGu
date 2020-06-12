package com.hbtangxun.boxuegu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbtangxun.boxuegu.R;
import com.hbtangxun.boxuegu.adapter.PlayHistoryAdapter;
import com.hbtangxun.boxuegu.bean.VideoBean;
import com.hbtangxun.boxuegu.utils.AnalysisUtils;
import com.hbtangxun.boxuegu.utils.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayHistoryActivity extends AppCompatActivity {

    //标头
    //标题栏的返回键
    private ImageView iv_back_title;
    //标题栏的文本
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
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        db = DBUtils.getInstance(this);
        vbl = new ArrayList<>();
        vbl = db.getVideoHistory(AnalysisUtils.readLoginUserName(this));
        initView();
        initData();
    }

    private void initView() {
        iv_back_title = findViewById(R.id.iv_back_title);
        title_text = findViewById(R.id.title_text);
        rl_title_bar = findViewById(R.id.rl_title_bar);
        lv_history = findViewById(R.id.lv_history);
        tv_his_none = findViewById(R.id.tv_his_none);
    }

    private void initData() {
        title_text.setText("播放记录");
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        iv_back_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (vbl.size() == 0) {
            tv_his_none.setVisibility(View.VISIBLE);
        }

        adapter = new PlayHistoryAdapter(this);
        adapter.setData(vbl);
        lv_history.setAdapter(adapter);
    }
}
