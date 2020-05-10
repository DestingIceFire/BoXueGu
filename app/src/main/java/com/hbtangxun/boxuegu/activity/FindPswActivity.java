package com.hbtangxun.boxuegu.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbtangxun.boxuegu.R;
import com.hbtangxun.boxuegu.utils.AnalysisUtils;
import com.hbtangxun.boxuegu.utils.MD5Utils;
import com.hbtangxun.boxuegu.utils.ToolUtils;

/**
 * 设置密保和找回密码
 */
public class FindPswActivity extends Activity implements View.OnClickListener {

    //标头
    //标题栏的返回键
    private ImageView iv_back_title;
    //标题栏的文本
    private TextView title_text;
    private RelativeLayout rl_title_bar;

    //--------
    private TextView tv_user_name, tv_validate_name;
    private TextView tv_reset_psw;
    private EditText et_user_name, et_validate_name;
    private Button btn_validate;
    //from为security时是从设置密保界面跳转过来的，否则就是从登录界面跳转过来的
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //from  获取从登录界面和设置界面传递过来的数据
        from = getIntent().getStringExtra("from");
        initView();
        initData();

    }

    private void initView() {
        iv_back_title = findViewById(R.id.iv_back_title);
        title_text = findViewById(R.id.title_text);
        tv_user_name = findViewById(R.id.tv_user_name);
        et_user_name = findViewById(R.id.et_user_name);
        tv_validate_name = findViewById(R.id.tv_validate_name);
        et_validate_name = findViewById(R.id.et_validate_name);
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
        iv_back_title.setOnClickListener(this);
        btn_validate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == iv_back_title) {
            finish();
        } else if (v == btn_validate) {
            validatePsw();
        }
    }

    private void validatePsw() {

        String validateName = et_validate_name.getText().toString().trim();
        String userName = et_user_name.getText().toString().trim();

        if ("security".equals(from)) {//设置密保
            if (TextUtils.isEmpty(validateName)) {
                ToolUtils.showToastShort(this, "请输入要验证的姓名");
            } else {
                //把密保保存到SP中
                saveSecurity(validateName);
                finish();
            }

        } else {//找回密码
            String sp_security = readSecurity(userName);
            if (TextUtils.isEmpty(userName)) {
                ToolUtils.showToastShort(this, "请输入您的用户名");
                return;
            } else if (!isExistUserName(userName)) {
                ToolUtils.showToastShort(this, "您输入的用户名不存在");
                return;
            } else if (TextUtils.isEmpty(validateName)) {
                ToolUtils.showToastShort(this, "请输入您的姓名");
                return;
            } else if (!validateName.equals(sp_security)) {
                ToolUtils.showToastShort(this, "您输入的密保不正确");
                return;
            } else {
                tv_reset_psw.setVisibility(View.VISIBLE);
                tv_reset_psw.setText("初始密码是：123456");
                savePsw(userName);
            }

        }
    }

    /**
     * 保存初始密码
     *
     * @param userName
     */
    private void savePsw(String userName) {
        String md5Psw = MD5Utils.md5("123456");
        SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(userName, md5Psw);
        edit.commit();
    }

    /**
     * 从Sp中读取密保
     *
     * @param userName
     * @return
     */
    private String readSecurity(String userName) {
        SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String security = sp.getString(userName + "_security", "");
        return security;
    }

    /**
     * 从Sp中根据用户输入用户名来判断是否有此用户
     *
     * @param userName
     * @return
     */
    private boolean isExistUserName(String userName) {
        boolean hasUserName = false;
        SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");
        if (!TextUtils.isEmpty(spPsw)) {
            hasUserName = true;
        }
        return hasUserName;
    }

    /**
     * 保存密保到Sp中
     *
     * @param validateName
     */
    private void saveSecurity(String validateName) {
        SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        //存入用户对应的密保
        edit.putString(AnalysisUtils.readLoginUserName(this) + "_security", validateName);
        edit.commit();
        ToolUtils.showToastShort(this, "密保设置完成");
    }
}
