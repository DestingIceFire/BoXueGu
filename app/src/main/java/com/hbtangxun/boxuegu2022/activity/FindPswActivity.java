package com.hbtangxun.boxuegu2022.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.utils.AnalysisUtils;
import com.hbtangxun.boxuegu2022.utils.MD5Utils;
import com.hbtangxun.boxuegu2022.utils.ToolUtils;

/**
 * 忘记密码 界面
 */
public class FindPswActivity extends AppCompatActivity implements View.OnClickListener {

    // 返回键
    private TextView tv_back;
    //标题名
    private TextView title_text;

    //-----
    private TextView tv_user_name;
    private EditText et_user_name;
    private TextView tv_user_name_1;
    private EditText et_user_name_1;
    private TextView tv_reset_psw;
    private Button btn_validate;

    //在这个activity中主要是根据从设置界面和登录界面传递过来的from参数的值来判断要跳转到哪个界面，
    //若值为security则处理的是设置密保的界面，否则处理的就是找回密码的界面。
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //from 是用来获取登录界面或者设置界面传递过来的数据
        from = getIntent().getStringExtra("from");
        initView();
        initData();
    }

    private void initView() {
        tv_back = findViewById(R.id.tv_back);
        title_text = findViewById(R.id.title_text);
        tv_user_name = findViewById(R.id.tv_user_name);
        et_user_name = findViewById(R.id.et_user_name);
        tv_user_name_1 = findViewById(R.id.tv_user_name_1);
        et_user_name_1 = findViewById(R.id.et_user_name_1);
        tv_reset_psw = findViewById(R.id.tv_reset_psw);
        btn_validate = findViewById(R.id.btn_validate);
    }

    private void initData() {

        if ("security".equals(from)) {
            title_text.setText("设置密保");
            btn_validate.setText("确  定");
        } else {
            title_text.setText("找回密码");
            tv_user_name.setVisibility(View.VISIBLE);
            et_user_name.setVisibility(View.VISIBLE);
        }

        tv_back.setOnClickListener(this);
        btn_validate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == tv_back) {
            finish();
        } else if (v == btn_validate) {
            validatePsw();
        }
    }

    private void validatePsw() {
        String yonghuming = et_user_name.getText().toString();
        String xingming = et_user_name_1.getText().toString();

        if ("security".equals(from)) {
            //设置密保
            if (TextUtils.isEmpty(xingming)) {
                ToolUtils.showShortToast(this, "请输入要密保姓名");
            } else {
                saveSecurity(xingming);
                finish();
            }
        } else {
            // 找回密码
            if (TextUtils.isEmpty(yonghuming)) {
                ToolUtils.showShortToast(this, "请输入您的用户名");
            } else if (TextUtils.isEmpty(xingming)) {
                ToolUtils.showShortToast(this, "请输入您的姓名");
            } else if (!isExistUserName(yonghuming)) {
                ToolUtils.showShortToast(this, "您输入的用户名不存在");
            } else if (!xingming.equals(readSecurity(yonghuming))) {
                ToolUtils.showShortToast(this, "您输入的姓名不正确");
            } else {
                tv_reset_psw.setVisibility(View.VISIBLE);
                tv_reset_psw.setText("已将密码初始化为：123456");
                savePsw(yonghuming);
            }
        }
    }

    /**
     * 将密码保存到SP中
     *
     * @param str
     */
    private void savePsw(String str) {
        String md5Psw = MD5Utils.md5("123456");
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(str, md5Psw);
        edit.commit();
    }

    /**
     * 判断输入的用户名是否在SP中
     *
     * @param str
     * @return
     */
    private boolean isExistUserName(String str) {
        boolean hasUserName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(str, "");
        if (!TextUtils.isEmpty(spPsw)) {
            hasUserName = true;
        }
        return hasUserName;
    }

    /**
     * 将密保保存到SP中
     *
     * @param str
     */
    private void saveSecurity(String str) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(AnalysisUtils.readLoginUserName(this) + "_security", str);
        edit.commit();
        ToolUtils.showShortToast(this, "密保设置成功");
    }

    /**
     * 从SP中读取密保
     *
     * @return
     */
    private String readSecurity(String yonghuming) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String str = sp.getString(yonghuming + "_security", "");
        return str;
    }
}
