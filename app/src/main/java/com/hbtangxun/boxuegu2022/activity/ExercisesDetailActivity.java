package com.hbtangxun.boxuegu2022.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.adapter.ExercisesDetailAdapter;
import com.hbtangxun.boxuegu2022.bean.ExercisesBean;
import com.hbtangxun.boxuegu2022.utils.AnalysisUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 习题详情 界面
 */
public class ExercisesDetailActivity extends AppCompatActivity {

    // 返回键
    private TextView tv_back;
    //标题名
    private TextView title_text;
    private RelativeLayout rl_title_bar;

    private ListView lv_ex_list;

    private ExercisesDetailAdapter adapter;
    private List<ExercisesBean> eb1;

    private int id;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //习题的ID
        id = getIntent().getIntExtra("id", 0);
        //习题详情界面的标头
        title = getIntent().getStringExtra("title");

        eb1 = new ArrayList<>();

        initView();
        initData();
    }

    private void initView() {
        tv_back = findViewById(R.id.tv_back);
        title_text = findViewById(R.id.title_text);
        rl_title_bar = findViewById(R.id.rl_title_bar);
        lv_ex_list = findViewById(R.id.lv_ex_list);
    }

    private void initData() {
        // 标题栏
        title_text.setText(title);
        //标题设置背景颜色
        rl_title_bar.setBackgroundColor(Color.parseColor("#30b4ff"));

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new ExercisesDetailAdapter(this, new ExercisesDetailAdapter.OnSelectedListener() {
            @Override
            public void onSelectA(int position, ImageView iv_ex_a, ImageView iv_ex_b, ImageView iv_ex_c, ImageView iv_ex_d) {
                //判断答案不是A（也就是1）
                ExercisesBean bean = eb1.get(position);
                if (bean.getAnswer() != 1) {
                    bean.setSelect(1);
                } else {
                    bean.setSelect(0);
                }
                switch (bean.getAnswer()) {
                    case 1:
                        iv_ex_a.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 2:
                        iv_ex_a.setImageResource(R.drawable.exercises_error_icon);
                        iv_ex_b.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        iv_ex_a.setImageResource(R.drawable.exercises_error_icon);
                        iv_ex_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        iv_ex_a.setImageResource(R.drawable.exercises_error_icon);
                        iv_ex_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false, iv_ex_a, iv_ex_b, iv_ex_c, iv_ex_d);
            }

            @Override
            public void onSelectB(int position, ImageView iv_ex_a, ImageView iv_ex_b, ImageView iv_ex_c, ImageView iv_ex_d) {
                //判断答案不是B（也就是2）
                ExercisesBean bean = eb1.get(position);
                if (bean.getAnswer() != 2) {
                    bean.setSelect(2);
                } else {
                    bean.setSelect(0);
                }
                switch (bean.getAnswer()) {
                    case 1:
                        iv_ex_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_ex_b.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_ex_b.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        iv_ex_c.setImageResource(R.drawable.exercises_error_icon);
                        iv_ex_b.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        iv_ex_d.setImageResource(R.drawable.exercises_error_icon);
                        iv_ex_b.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false, iv_ex_a, iv_ex_b, iv_ex_c, iv_ex_d);
            }

            @Override
            public void onSelectC(int position, ImageView iv_ex_a, ImageView iv_ex_b, ImageView iv_ex_c, ImageView iv_ex_d) {
                //判断答案不是C（也就是3）
                ExercisesBean bean = eb1.get(position);
                if (bean.getAnswer() != 3) {
                    bean.setSelect(3);
                } else {
                    bean.setSelect(0);
                }
                switch (bean.getAnswer()) {
                    case 1:
                        iv_ex_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_ex_c.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_ex_b.setImageResource(R.drawable.exercises_right_icon);
                        iv_ex_c.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        iv_ex_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        iv_ex_c.setImageResource(R.drawable.exercises_error_icon);
                        iv_ex_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false, iv_ex_a, iv_ex_b, iv_ex_c, iv_ex_d);
            }

            @Override
            public void onSelectD(int position, ImageView iv_ex_a, ImageView iv_ex_b, ImageView iv_ex_c, ImageView iv_ex_d) {
                //判断答案不是D（也就是4）
                ExercisesBean bean = eb1.get(position);
                if (bean.getAnswer() != 4) {
                    bean.setSelect(4);
                } else {
                    bean.setSelect(0);
                }
                switch (bean.getAnswer()) {
                    case 1:
                        iv_ex_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_ex_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_ex_b.setImageResource(R.drawable.exercises_error_icon);
                        iv_ex_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        iv_ex_c.setImageResource(R.drawable.exercises_error_icon);
                        iv_ex_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        iv_ex_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false, iv_ex_a, iv_ex_b, iv_ex_c, iv_ex_d);
            }
        });


        //解析数据
        try {
            InputStream is = getResources().getAssets().open("chapter" + id + ".xml");
            eb1 = AnalysisUtils.getExercisesInfos(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter.setData(eb1);
        lv_ex_list.setAdapter(adapter);
    }
}
