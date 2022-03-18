package com.hbtangxun.boxuegu2022.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.utils.MD5Utils;
import com.hbtangxun.boxuegu2022.utils.ToolUtils;

/**
 * 登录页面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // 返回键
    private TextView tv_back;
    //标题名
    private TextView title_text;

    private EditText et_login_name;
    private EditText et_login_psw;
    private Button btn_login;

    private TextView login_register;
    private TextView login_find_psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initView();
        initData();

    }

    private void initView() {
        tv_back = findViewById(R.id.tv_back);
        title_text = findViewById(R.id.title_text);
        et_login_name = findViewById(R.id.et_login_name);
        et_login_psw = findViewById(R.id.et_login_psw);
        btn_login = findViewById(R.id.btn_login);
        login_register = findViewById(R.id.login_register);
        login_find_psw = findViewById(R.id.login_find_psw);
    }

    private void initData() {

        title_text.setText("登 录");

        tv_back.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        login_register.setOnClickListener(this);
        login_find_psw.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == tv_back) {
            finish();
        } else if (v == btn_login) {
            btnLogin();
        } else if (v == login_register) {
            //跳转到注册页面
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivityForResult(intent, 1);
        } else if (v == login_find_psw) {
            //跳转到 忘记密码 界面
            startActivity(new Intent(this, FindPswActivity.class));
        }

    }

    /**
     * 登录
     */
    private void btnLogin() {

        String userName = et_login_name.getText().toString();
        String psw = et_login_psw.getText().toString();

        String md5Psw = MD5Utils.md5(psw);
        String spPsw = readSP(userName);
        if (TextUtils.isEmpty(userName)) {
            ToolUtils.showShortToast(this, "请输入用户名");
        } else if (TextUtils.isEmpty(psw)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        } else if (md5Psw.equals(spPsw)) {
            //比较输入密码与数据的密码是否一致
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            //保存 登录状态
            saveLoginStatus(true, userName);
            //把登录成功状态传递给MainActivity中
            Intent data = new Intent();
            data.putExtra("isLogin", true);
            setResult(RESULT_OK, data);
            finish();
        } else if (!TextUtils.isEmpty(spPsw) && !md5Psw.equals(spPsw)) {
            Toast.makeText(LoginActivity.this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 保存登录状态和登录的用户名到SP中
     *
     * @param status
     * @param userName
     */
    private void saveLoginStatus(boolean status, String userName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isLogin", status);
        edit.putString("loginUserName", userName);
        edit.commit();
    }

    /**
     * 根据用户名从SP中读取密码
     *
     * @param userName
     * @return
     */
    private String readSP(String userName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName, "");
    }

    /**
     * 由注册页面传过来的userName
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String name = data.getStringExtra("userName");

            if (!TextUtils.isEmpty(name)) {
                et_login_name.setText(name);
                et_login_name.setSelection(name.length());
            }
        }
    }
}
