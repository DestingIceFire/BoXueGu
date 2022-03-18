package com.hbtangxun.boxuegu2022.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.utils.MD5Utils;
import com.hbtangxun.boxuegu2022.utils.ToolUtils;

/**
 * 注册页面
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    // 返回键
    private TextView tv_back;
    //标题名
    private TextView title_text;

    // 文本框：用户名 、 密码 、再次输入密码
    private EditText et_user_name, et_user_psw, et_user_psw_again;
    // 按钮：注册
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initView();
        initData();
    }

    /**
     * findViewById
     */
    private void initView() {
        tv_back = findViewById(R.id.tv_back);
        title_text = findViewById(R.id.title_text);
        et_user_name = findViewById(R.id.et_user_name);
        et_user_psw = findViewById(R.id.et_user_psw);
        et_user_psw_again = findViewById(R.id.et_user_psw_again);
        btn_register = findViewById(R.id.btn_register);
    }

    /**
     * 逻辑内容
     * 交互内容
     */
    private void initData() {
        // 设置标题名字
        title_text.setText("注册");
        // 返回键 点击效果 返回上一层
        tv_back.setOnClickListener(this);
        // 注册按钮 点击效果 注册
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == tv_back) {
            finish();
        } else if (view == btn_register) {
            //1.获取输入文本
            String userName = et_user_name.getText().toString();
            String psw = et_user_psw.getText().toString();
            String againPsw = et_user_psw_again.getText().toString();
            //2.判断
            if (TextUtils.isEmpty(userName)) {
                ToolUtils.showShortToast(this, "请输入用户名");
                return;
            } else if (TextUtils.isEmpty(psw)) {
                ToolUtils.showShortToast(this, "请输入密码");
                return;
            } else if (TextUtils.isEmpty(againPsw)) {
                ToolUtils.showShortToast(this, "请再次输入密码");
                return;
            } else if (!psw.equals(againPsw)) {
                ToolUtils.showShortToast(this, "两次输入的密码不一致");
                return;
            } else if (isExistUserName(userName)) {
                ToolUtils.showShortToast(this, "此账号已存在");
                return;
            } else {
                //将账号、密码存入SP中
                saveRegisterInfo(userName, psw);
                ToolUtils.showShortToast(this, "注册成功");

                //将账号传给LoginActivity
                Intent data = new Intent();
                data.putExtra("userName", userName);
                setResult(RESULT_OK, data);
                finish();
            }
        }

    }

    /**
     * 将账号密码保存到SP中
     *
     * @param userName
     * @param psw
     */
    private void saveRegisterInfo(String userName, String psw) {
        //对密码进行MD5加密
        String md5Psw = MD5Utils.md5(psw);
        //存储SP
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(userName, md5Psw);
        edit.commit();
    }

    /**
     * 从Sp中读取输入的用户名，并判断此用户名是否存在
     *
     * @param userName
     * @return
     */
    private boolean isExistUserName(String userName) {
        boolean has_userName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");
        if (!TextUtils.isEmpty(spPsw)) {
            has_userName = true;
        }
        return has_userName;
    }
}
