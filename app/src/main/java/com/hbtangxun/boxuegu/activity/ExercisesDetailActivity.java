package com.hbtangxun.boxuegu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbtangxun.boxuegu.R;
import com.hbtangxun.boxuegu.adapter.ExercisesDetailAdapter;
import com.hbtangxun.boxuegu.bean.ExercisesBean;
import com.hbtangxun.boxuegu.utils.AnalysisUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 习题详情
 */

public class ExercisesDetailActivity extends AppCompatActivity {

    //标题栏的返回键
    private ImageView iv_back_title;
    //标题栏的文本
    private TextView title_text;
    private RelativeLayout rl_title_bar;

    private ListView lv_ex_list;

    private ExercisesDetailAdapter adapter;
    private List<ExercisesBean> eb1;

    private String title;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //习题的ID
        id = getIntent().getIntExtra("id", 0);
        //习题的title
        title = getIntent().getStringExtra("title");

        eb1 = new ArrayList<>();

        initData();
        initView();

    }

    private void initView() {
        iv_back_title = findViewById(R.id.iv_back_title);
        title_text = findViewById(R.id.title_text);
        rl_title_bar = findViewById(R.id.rl_title_bar);
        lv_ex_list = findViewById(R.id.lv_ex_list);

        //修改标题栏
        title_text.setText(title);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        iv_back_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView textView = new TextView(this);
        textView.setText("一、选择题");
        textView.setTextSize(16.0f);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setPadding(10, 15, 0, 10);
        lv_ex_list.addHeaderView(textView);

        adapter = new ExercisesDetailAdapter(this, new ExercisesDetailAdapter.OnSelectedListener() {
            @Override
            public void onSelectA(int position, ImageView iv_ex_a, ImageView iv_ex_b, ImageView iv_ex_c, ImageView iv_ex_d) {
                //判断如果答案不是A（也就是1）
                //position 代表点击的第几个
                if (eb1.get(position).getAnswer() != 1) {
                    eb1.get(position).setSelect(1);
                } else {
                    eb1.get(position).setSelect(0);
                }

                switch (eb1.get(position).getAnswer()) {
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
                //判断如果答案不是B（也就是2）
                if (eb1.get(position).getAnswer() != 2) {
                    eb1.get(position).setSelect(2);
                } else {
                    eb1.get(position).setSelect(0);
                }

                switch (eb1.get(position).getAnswer()) {
                    case 1:
                        iv_ex_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_ex_b.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_ex_b.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        iv_ex_b.setImageResource(R.drawable.exercises_error_icon);
                        iv_ex_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        iv_ex_b.setImageResource(R.drawable.exercises_error_icon);
                        iv_ex_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }

                AnalysisUtils.setABCDEnable(false, iv_ex_a, iv_ex_b, iv_ex_c, iv_ex_d);
            }

            @Override
            public void onSelectC(int position, ImageView iv_ex_a, ImageView iv_ex_b, ImageView iv_ex_c, ImageView iv_ex_d) {
                //判断如果答案不是C（也就是3）
                if (eb1.get(position).getAnswer() != 3) {
                    eb1.get(position).setSelect(3);
                } else {
                    eb1.get(position).setSelect(0);
                }

                switch (eb1.get(position).getAnswer()) {
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
                //判断如果答案不是D（也就是4）
                if (eb1.get(position).getAnswer() != 4) {
                    eb1.get(position).setSelect(4);
                } else {
                    eb1.get(position).setSelect(0);
                }

                switch (eb1.get(position).getAnswer()) {
                    case 1:
                        iv_ex_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_ex_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_ex_b.setImageResource(R.drawable.exercises_right_icon);
                        iv_ex_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        iv_ex_c.setImageResource(R.drawable.exercises_right_icon);
                        iv_ex_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        iv_ex_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }

                AnalysisUtils.setABCDEnable(false, iv_ex_a, iv_ex_b, iv_ex_c, iv_ex_d);
            }
        });

        adapter.setData(eb1);
        lv_ex_list.setAdapter(adapter);

    }

    private void initData() {
        //解析XML数据
        try {
            InputStream is = getResources().getAssets().open("chapter" + id + ".xml");
            eb1 = AnalysisUtils.getExercisesInfos(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
